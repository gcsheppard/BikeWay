package edu.acc.jweb.bikeway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sql.DataSource;

public class BikeManager {
    private final DataSource dataSource;
    
    public BikeManager (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addBike(String model, String manufacturer, String name, String type) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO Bikes (model, manufacturer, name, type) values (?,?,?,?)");
            statement.setString(1, model);
            statement.setString(2, manufacturer);
            statement.setString(3, name);
            statement.setString(4, type);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            try {
                if (statement != null) { 
                    statement.close();
                } 
                if (connection != null) { 
                    connection.close();
                } 
            } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        }   
    }
    
    public void updateBike(Integer id, String manufacturer, String name, String type) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE Bikes set manufacturer = ?, name = ?, type = ? WHERE id = ?");
            statement.setString(1, manufacturer);
            statement.setString(2, name);
            statement.setString(3, type);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            try {
                if (statement != null) { 
                    statement.close();
                } 
                if (connection != null) { 
                    connection.close();
                } 
            } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        }
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
            statement.executeUpdate(sql);
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
    
    public Bike findBikeByModel(String model) {
        Bike bike = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from Bikes where model = ?");
            statement.setString(1, model);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bike = bikeFromDB(resultSet);
            }
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch(SQLException sqle) {
                        throw new RuntimeException(sqle);
                    }
                }
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
        return bike;
    }
    
    public HashMap<String,String> validBike(Bike bike, String op) {
        HashMap<String,String> errors = new HashMap<>();
        
        if (op.equals("new")) {
            Bike found = findBikeByModel(bike.model);
            if (found != null) {
                errors.put("model", "Model already in use.");
            } else {
                if (bike.model.isEmpty()) errors.put("model", "Model not entered.");
            }
        }
        
        if (bike.model.isEmpty()) errors.put("model", "Model not entered.");
        if (bike.manufacturer.isEmpty()) errors.put("manufacturer", "Manufacturer not entered.");
        if (bike.name.isEmpty()) errors.put("name", "Name not entered.");
        if (bike.type.isEmpty()) errors.put("type", "Type not entered.");
        return errors;
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
