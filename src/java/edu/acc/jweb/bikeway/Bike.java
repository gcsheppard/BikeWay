package edu.acc.jweb.bikeway;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bike {
    private int id;
    private String model;
    private String manufacturer;
    private String name;
    private String type;
    private Date created;
    
    public Bike (String model, String manufacturer, String name, String type) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.name = name;
        this.type = type;
}
    
    public Bike () {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCreated() {
        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        String string = formatter.format(this.created);
        return string;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    
}
