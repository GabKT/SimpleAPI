package com.gabkt.WebService_Angular1.repository.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gabkt.WebService_Angular1.model.User;
import com.gabkt.WebService_Angular1.model.UserRole;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        String roleString = rs.getString("role");
        UserRole role = UserRole.fromRole(roleString);
        user.setRole(role);

        return user;
    }

}
