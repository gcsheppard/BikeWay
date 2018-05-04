DROP TABLE Bikes;

CREATE TABLE Bikes (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    model VARCHAR(32),
    manufacturer VARCHAR(64),
    name VARCHAR(128),
    type VARCHAR(64),
    created DATE NOT NULL WITH DEFAULT CURRENT DATE
);

INSERT INTO Bikes(model, manufacturer, name, type) 
    VALUES('M101', 'Specialized', 'Rockhopper', 'Mountain');
INSERT INTO Bikes(model, manufacturer, name, type) 
    VALUES('M102', 'Specialized', 'Stumpjumper', 'Mountain');
INSERT INTO Bikes(model, manufacturer, name, type) 
    VALUES('M102B', 'Specialized', 'Stumpjumper Sport', 'Mountain');
INSERT INTO Bikes(model, manufacturer, name, type) 
    VALUES('M103', 'Specialized', 'Hardtail', 'Mountain');
INSERT INTO Bikes(model, manufacturer, name, type) 
    VALUES('M104', 'Specialized', 'Enduro', 'Mountain');
