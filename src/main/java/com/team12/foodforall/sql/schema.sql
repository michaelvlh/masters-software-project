-- Delete all tables firstly
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;

-- Create Project Table


CREATE TABLE projects
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    title           VARCHAR(255),
    content         VARCHAR(255),
    img             CLOB,
    achievedmeals   INT    DEFAULT 0      NOT NULL,
    targetmeals     INT,
    current_revenue VARCHAR(10) DEFAULT 0      NOT NULL,
    progress        VARCHAR(10)  DEFAULT 0      NOT NULL,
    position_name   VARCHAR(255),
    lat             FLOAT,
    lng             FLOAT,
    price           VARCHAR(10),
    currency        VARCHAR(10) DEFAULT 'USD' NOT NULL,
    user_id         BIGINT,
    CONSTRAINT pk_projects PRIMARY KEY (id)
);


CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    first_name   VARCHAR(20)           NOT NULL,
    last_name    VARCHAR(20)           NOT NULL,
    email        VARCHAR(45)           NOT NULL,
    password     VARCHAR(64)           NOT NULL,
    charity_name VARCHAR(20)           NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE projects
    ADD CONSTRAINT FK_PROJECTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
