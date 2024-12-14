package com.gabkt.WebService_Angular1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gabkt.WebService_Angular1.model.Roupa;
import com.gabkt.WebService_Angular1.repository.rowMapper.RoupaRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class RoupaDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Roupa inserirRoupa(Roupa roupa) {
        String query = "INSERT INTO roupa(nome, categoria, preco, tamanho) VALUES (?, ?, ?, ?) RETURNING *";
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(),
                new Object[] { roupa.getNome(), roupa.getCategoria(), roupa.getPreco(), roupa.getTamanho() });
    }

    public Roupa buscarRoupaPorId(int id) {
        String query = "SELECT * FROM roupa WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(), id);
    }

    public Roupa atualizarRoupa(Roupa roupa) {
        String query = "UPDATE roupa SET nome = ?, categoria = ?, preco = ?, tamanho = ? WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(query, new RoupaRowMapper(),
                new Object[] { roupa.getNome(), roupa.getCategoria(), roupa.getPreco(), roupa.getTamanho(),
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
