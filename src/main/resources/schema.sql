DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;

CREATE TABLE product (
                      id INT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      description VARCHAR(100) NOT NULL,
                      price DECIMAL NOT NULL,
                      weight DOUBLE NOT NULL,
                      category INT NOT NULL,
                      supplier INT NOT NULL,
                      PRIMARY KEY (id)
                      UNIQUE KEY(name));


CREATE TABLE category (
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        description VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id)
                        UNIQUE KEY(name));