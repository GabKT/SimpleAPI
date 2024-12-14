package com.gabkt.WebService_Angular1.repository.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gabkt.WebService_Angular1.model.Roupa;

public class RoupaRowMapper implements RowMapper<Roupa> {
    @Override
    public Roupa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Roupa roupa = new Roupa();
        roupa.setId(rs.getInt("id"));
        roupa.setNome(rs.getString("nome"));
        roupa.setCategoria(rs.getString("categoria"));
        roupa.setPreco(rs.getDouble("preco"));
        roupa.setTamanho(rs.getString("tamanho"));
        return roupa;
    }
}
