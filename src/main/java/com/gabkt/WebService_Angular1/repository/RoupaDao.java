package com.gabkt.WebService_Angular1.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gabkt.WebService_Angular1.Utils.ImageUtils;
import com.gabkt.WebService_Angular1.model.Roupa;
import com.gabkt.WebService_Angular1.repository.rowMapper.RoupaRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

/* 
 CREATE TABLE roupa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    categoria VARCHAR(50),
    preco DECIMAL(10, 2),
    tamanho VARCHAR(10),
    imagem BYTEA
);
 */

@Repository
public class RoupaDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Roupa inserirRoupa(Roupa roupa) {
        String query = "INSERT INTO roupa(nome, categoria, preco, tamanho, imagem) VALUES (?, ?, ?, ?, ?) RETURNING *";
        byte[] imagemBytes = ImageUtils.convertToByte(roupa.getImagem());
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(),
                new Object[] { roupa.getNome(), roupa.getCategoria(), roupa.getPreco(), roupa.getTamanho(),
                        imagemBytes });
    }

    public Roupa buscarRoupaPorId(int id) {
        String query = "SELECT * FROM roupa WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(), id);
    }

    public Roupa atualizarRoupa(Roupa roupa) {
        String query = "UPDATE roupa SET nome = ?, categoria = ?, preco = ?, tamanho = ?, imagem = ? WHERE id = ? RETURNING *";
        byte[] imagemBytes = ImageUtils.convertToByte(roupa.getImagem());
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(),
                new Object[] { roupa.getNome(), roupa.getCategoria(), roupa.getPreco(), roupa.getTamanho(), imagemBytes,
                        roupa.getId() });
    }

    public List<Roupa> listarTodasRoupas() {
        String query = "SELECT * FROM roupa";
        return jdbcTemplate.query(query, new RoupaRowMapper());
    }

    public List<Integer> deletarRoupas(List<Integer> listOfIds) {
        String selectQuery = "SELECT id FROM roupa WHERE id IN (" +
                listOfIds.stream().map(id -> "?").collect(Collectors.joining(", ")) + ")";
        List<Integer> existingIds = jdbcTemplate.queryForList(selectQuery, Integer.class, listOfIds.toArray());

        if (existingIds.isEmpty()) {
            return List.of();
        }

        String placeholder = existingIds.stream().map(element -> "?").collect(Collectors.joining(", "));
        String query = "DELETE FROM roupa WHERE id IN (" + placeholder + ")";
        int rowsDeleted = jdbcTemplate.update(query, existingIds.toArray());

        if (rowsDeleted == existingIds.size()) {
            return existingIds;
        } else {
            throw new RuntimeException(
                    "Erro ao deletar alguns registros, quantidade de linhas afetadas: " + rowsDeleted);
        }
    }
}
