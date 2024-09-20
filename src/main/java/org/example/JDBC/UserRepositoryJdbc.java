package org.example.JDBC;

import org.example.database.DatabaseFactory;
import org.example.database.contract.Database;
import org.example.entities.User;
import org.example.repositories.contract.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("userRepositoryJdbc")
public class UserRepositoryJdbc extends JdbcRepository<User, Long> implements UserRepositoryI {

    @Autowired
    public UserRepositoryJdbc(Database databaseFactory) {
        super(databaseFactory, User.class);
        this.table = "users";
    }

}
