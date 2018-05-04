package edu.acc.jweb.bikeway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

public class BikeManager {
    private final DataSource dataSource;
    
    public BikeManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ArrayList<Bike> getBikes() {
        ArrayList<Bike> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Bikes ORDER BY model");
            ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Bike bike = bikeFromDB(resultSet);
                list.add(bike);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } 
        return list;
    }
    
    public void deleteBikeById(Integer id) {
        String sql = "DELETE FROM Bikes WHERE id = " + id;
        Statement statement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            int executeUpdate = statement.executeUpdate(sql);
            } catch(SQLException sqle) {
                throw new RuntimeException(sqle);
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch(SQLException sqle) {
                        throw new RuntimeException(sqle);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch(SQLException sqle) {
                        throw new RuntimeException(sqle);
                    }
                }
            }
    }
    
    public Bike findBikeById(Integer id) {
        Bike bike = null;
        String sql = "select * from Bikes where id = " + id;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                bike = bikeFromDB(resultSet);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        }
        return bike;
    }
    
    private Bike bikeFromDB(ResultSet resultSet) {
        Bike bike = new Bike();
        try {
            bike.setId(resultSet.getInt("id"));
            bike.setModel(resultSet.getString("model"));
            bike.setManufacturer(resultSet.getString("manufacturer"));
            bike.setName(resultSet.getString("name"));
            bike.setType(resultSet.getString("type"));
            bike.setCreated(resultSet.getDate("created"));
            return bike;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    
}
