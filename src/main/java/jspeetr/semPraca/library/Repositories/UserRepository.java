package jspeetr.semPraca.library.Repositories;

import jspeetr.semPraca.library.Records.Users;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    //--------------------GET METHODS--------------------------------------------------------------------//
    public List<Users> findAll() {
        return jdbcClient.sql("SELECT * FROM Users")
                .query(Users.class)
                .list();
    }

    public Optional<Users> findById(int id) {
        return jdbcClient.sql("SELECT * FROM Users WHERE user_id = ?")
                .param(id)
                .query(Users.class)
                .optional();
    }


    //--------------------CREATE METHODS--------------------------------------------------------------------//
    public void create(Users user) {
        var updated = jdbcClient.sql("INSERT INTO Users (username, password, role_id, email) VALUES (?, ?, ?, ?)")
                .params(List.of(user.username(), user.password(), user.role_id(), user.email()))
                .update();
        Assert.state(updated == 1, "Failed create user " + user.username());
    }


    //--------------------DELETE METHODS--------------------------------------------------------------------//
    public void delete(int userId) {
        var updated = jdbcClient.sql("DELETE FROM Users WHERE user_id = ?")
                .param(userId)
                .update();
        Assert.state(updated == 1, "Failed delete user " + userId);
    }
}
