CREATE TABLE customer (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(150),
    password VARCHAR(250),
    role VARCHAR(100),
    PRIMARY KEY (id)
);