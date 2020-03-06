package com.example.demo.service;

import com.example.demo.constants.MariadbConstants;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public void createTable(){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="CREATE TABLE IF NOT EXISTS users(\n" +
                    "    id int NOT NULL AUTO_INCREMENT,\n" +
                    "    name varchar(255),\n" +
                    "    surname varchar(255),\n" +
                    "    PRIMARY KEY (id)\n" +
                    ")";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public int createUser(User user){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="INSERT INTO users (name,surname) VALUES (?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateUser(User user,int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="UPDATE users SET name=(?),surname=(?) WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setInt(3,id);
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteUser(int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="DELETE FROM users WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public User getUserById(int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            List<User> all = findAll();
            for (User user : all) {
                if (user.getId() == id) {
                    return user;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<User> findAll(){
        List<User> users=new ArrayList<>();
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="SELECT * FROM users";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String surname=resultSet.getString("surname");
                User user=new User(id,name,surname);
                users.add(user);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return users;
    }

}
