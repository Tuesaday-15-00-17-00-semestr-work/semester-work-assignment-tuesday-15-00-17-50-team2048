package jspeetr.semPraca.library;

import org.apache.catalina.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Users> findAll() {
        return jdbcClient.sql("SELECT * FROM Users")
                .query(Users.class)
                .list();
    }

    public void create(Users user) {
        var updated = jdbcClient.sql("INSERT INTO Users (username, password, role_id, email) VALUES (?, ?, ?, ?)")
                .params(List.of(user.username(), user.password(), user.role_id(), user.email()))
                .update();
        Assert.state(updated == 1, "Failed create user " + user.username());
    }
}
