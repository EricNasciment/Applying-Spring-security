CREATE TABLE user_role(

      user_id BIGINT NOT NULL,
      role VARCHAR(50) NOT NULL,
      CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_user(id) ON  DELETE CASCADE
       );