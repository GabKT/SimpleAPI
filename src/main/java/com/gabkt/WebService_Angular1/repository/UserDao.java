package com.gabkt.WebService_Angular1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.gabkt.WebService_Angular1.model.User;
import com.gabkt.WebService_Angular1.repository.rowMapper.UserRowMapper;

/*
 CREATE TABLE users (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 name VARCHAR(100) NOT NULL,
 email VARCHAR(100) NOT NULL UNIQUE,
 password VARCHAR(250) NOT NULL,
 role VARCHAR(250) NOT NULL
 )
 */

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User buscarPorId(String id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserDetails buscarPorEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserRowMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User inserirUser(User user) {
        String query = "INSERT INTO users(name, email, password, role) VALUES (?, ?, ?, ?) RETURNING *";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), new Object[] { user.getName(), user.getEmail(),
                user.getPassword(), user.getRole().getRole() });
    }

    public User atualizarUser(User user) {
        String query = "UPDATE users SET nome=?, email=?, password=?, role=? WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getName(), user.getEmail(),
                user.getPassword(), user.getId(), user.getRole().getRole());
    }

    public int deletarUser(User user) {
        String query = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(query, user.getId());
    }
}
