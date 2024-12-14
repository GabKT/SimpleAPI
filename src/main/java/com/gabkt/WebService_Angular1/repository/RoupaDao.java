package com.gabkt.WebService_Angular1.repository;

import java.util.List;

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

    public Roupa excluirRoupa(int id) {
        String query = "DELETE FROM roupa WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(), id);
    }

    public List<Roupa> listarTodasRoupas() {
        String query = "SELECT * FROM roupa";
        return jdbcTemplate.query(query, new RoupaRowMapper());
    }
}
