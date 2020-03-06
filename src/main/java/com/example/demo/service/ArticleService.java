package com.example.demo.service;

import com.example.demo.constants.MariadbConstants;
import com.example.demo.model.Article;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public void createTable(){
        try(Connection con=DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="CREATE TABLE IF NOT EXISTS articles(\n" +
                    "    id int NOT NULL AUTO_INCREMENT,\n" +
                    "    userId int NOT NULL,\n" +
                    "    title varchar(255),\n" +
                    "    description varchar(255),\n" +
                    "    date varchar(255),\n" +
                    "    PRIMARY KEY (id),\n" +
                    "    FOREIGN KEY (userId) REFERENCES users(id)\n" +
                    ")";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public int createArticle(Article article){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="INSERT INTO articles (title,description,userId) VALUES (?,?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getDescription());
            preparedStatement.setInt(3,article.getUserId());
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateArticle(Article article,int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="UPDATE articles SET userId=(?), title=(?),description=(?), date=(?) WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,article.getUserId());
            preparedStatement.setString(2,article.getTitle());
            preparedStatement.setString(3,article.getDescription());
            preparedStatement.setString(4,article.getDate());
            preparedStatement.setInt(5,id);
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteArticle(int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query = "DELETE FROM articles WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public Article findArticleById(int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            List<Article> all = findAll();
            for (Article article : all) {
                if (article.getId() == id) {
                    return article;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Article> findAll(){
        List<Article> articles=new ArrayList<>();
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="SELECT * FROM articles";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                int userId=resultSet.getInt("userId");
                String title=resultSet.getString("title");
                String description=resultSet.getString("description");
                String date=resultSet.getString("date");
                Article article=new Article(id,userId,title,description,date);
                articles.add(article);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return articles;
    }
}
