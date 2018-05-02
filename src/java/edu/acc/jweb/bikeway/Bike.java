package edu.acc.jweb.bikeway;

import java.util.Date;

public class Bike {
    public int id;
    public String model;
    public String manufacturer;
    public String name;
    public String type;
    public Date date;
    
    public Bike (String model, String manufacturer, String name, String type) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.name = name;
        this.type = type;
}
    
    public Bike () {
    }
}
