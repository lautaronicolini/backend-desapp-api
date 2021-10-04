//this file contains all the table definitions

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    user_id  BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(30) NOT NULL,
    password VARCHAR(256) NOT NULL,
    CVU VARCHAR(22) NOT NULL,
    walletAddress VARCHAR(8) NOT NULL
);

