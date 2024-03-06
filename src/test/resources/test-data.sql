CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS lotto (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     number_1 INT,
                                     number_2 INT,
                                     number_3 INT,
                                     number_4 INT,
                                     number_5 INT,
                                     number_6 INT
);

CREATE TABLE IF NOT EXISTS winner (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      lotto_id BIGINT,
                                      rank INT,
                                      FOREIGN KEY (lotto_id) REFERENCES lotto(id)
);

INSERT INTO lotto (id, number_1, number_2, number_3, number_4, number_5, number_6) VALUES (1, 7, 28, 33, 2, 45, 19);
INSERT INTO lotto (id, number_1, number_2, number_3, number_4, number_5, number_6) VALUES (2, 26, 14, 41, 3, 22, 35);
INSERT INTO lotto (id, number_1, number_2, number_3, number_4, number_5, number_6) VALUES (3, 15, 29, 38, 6, 44, 21);
INSERT INTO lotto (id, number_1, number_2, number_3, number_4, number_5, number_6) VALUES (4, 31, 16, 42, 9, 23, 36);
INSERT INTO lotto (id, number_1, number_2, number_3, number_4, number_5, number_6) VALUES (5, 17, 30, 39, 10, 45, 24);
