package org.example.JDBC;

import org.example.database.DatabaseFactory;
import org.example.database.contract.Database;
import org.example.entities.Article;
import org.example.repositories.contract.ArticleRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
@Qualifier("articleRepositoryJdbc")
public class ArticleRepositoryJdbc extends JdbcRepository<Article, Long> implements ArticleRepositoryI {
@Autowired
    public ArticleRepositoryJdbc(Database databaseFactory) {
        super(databaseFactory, Article.class);
        this.table = "articles";
    }
    public List<Article> findByTitle(String title) {
        String sql = "SELECT * FROM " + table + " WHERE title = ?";
        List<Article> articles = new ArrayList<>();
        try (ResultSet rs = databaseFactory.executeQuery(sql, title)) {
            while (rs.next()) {
                Article article = createEntityFromResultSet(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
