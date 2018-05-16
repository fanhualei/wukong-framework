


DROP TABLE IF EXISTS wk_user_role;
DROP TABLE IF EXISTS wk_role_resource;

DROP TABLE IF EXISTS wk_resource;
DROP TABLE IF EXISTS wk_role;
DROP TABLE IF EXISTS wk_user_ex;
DROP TABLE IF EXISTS wk_user;

create table wk_user (
  user_id     int            NOT NULL AUTO_INCREMENT    COMMENT '用户id',
  username    varchar(255)   NOT NULL                   COMMENT '用户名',
  password    varchar(255)   NOT NULL                   COMMENT '密码',
  enabled     boolean        DEFAULT TRUE               COMMENT '是否有效',
  phone       varchar(255)                              COMMENT '手机号码',
  email       varchar(255)                              COMMENT '邮箱地址',
  pwResetDate DATETIME       DEFAULT CURRENT_TIMESTAMP  COMMENT '最后修改密码的时间',

  PRIMARY KEY (user_id),
  UNIQUE KEY `uq_user_username` (`username`),
  UNIQUE KEY `uq_user_phone`    (`phone`),
  UNIQUE KEY `uq_user_email`    (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='用户表';


-- 用 BCrypt 进行加密 ,admin密码=admin  user1密码=user1

INSERT INTO wk_user(user_id,username,password,enabled) VALUES (1,'admin','$2a$10$iWIebpXWvbLyu4jYaDthdOfGcuQ99IgQBTkizHvVn6YwO94qjN9vq',true);
INSERT INTO wk_user(user_id,username,password,enabled) VALUES (2,'user1','$2a$10$OVNco4o7D4PEnsSpGSxvUOMCLdb2FfEli26ccaGI7XDbK/OOx2h5q',true);



UPDATE  wk_user SET password='$2a$10$iWIebpXWvbLyu4jYaDthdOfGcuQ99IgQBTkizHvVn6YwO94qjN9vq' WHERE user_id=1 ;
UPDATE  wk_user SET password='$2a$10$OVNco4o7D4PEnsSpGSxvUOMCLdb2FfEli26ccaGI7XDbK/OOx2h5q' WHERE user_id=2 ;

-- UPDATE  wk_user SET password='{noop}admin' WHERE user_id=1 ;
-- UPDATE  wk_user SET password='{noop}user1' WHERE user_id=2 ;

# select * from wk_user;


create table wk_user_ex (
  user_id   int            NOT NULL                 COMMENT '用户id',
  nickname  varchar(255)   DEFAULT ''               COMMENT '昵称',
  signature varchar(255)   DEFAULT ''               COMMENT '个性签名',
  qq        varchar(50)    DEFAULT ''               COMMENT 'QQ号码',
  weixin    varchar(50)    DEFAULT ''               COMMENT '微信号码',
  weibo     varchar(50)    DEFAULT ''               COMMENT '微博号码',
  avatar    varchar(500)   DEFAULT ''               COMMENT '头像图片路径',
  PRIMARY KEY (user_id),
  CONSTRAINT fk_user_ex_user FOREIGN KEY (user_id) REFERENCES wk_user (user_id)
);





CREATE TABLE wk_role (
  role_id       int            NOT NULL AUTO_INCREMENT  COMMENT '权限编号',
  rolename      varchar(255)   NOT NULL                 COMMENT '权限名称',
  description   varchar(255)                            COMMENT '中文描述',
  sort          int            DEFAULT 0                COMMENT '排序号，越大越靠前',
  PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='权限字典表';



INSERT INTO wk_role(role_id,rolename,description) VALUES(1,"ROLE_ADMIN" ,"管理员");
INSERT INTO wk_role(role_id,rolename,description) VALUES(2,"ROLE_USER"  ,"普通用户");



CREATE TABLE wk_user_role (
  user_role_id        int      NOT NULL AUTO_INCREMENT,
  user_id             int      NOT NULL,
  role_id             int      NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES wk_user (user_id),
  CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES wk_role (role_id)

) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;


INSERT INTO wk_user_role(user_role_id, user_id, role_id) VALUES (1,1,1);
INSERT INTO wk_user_role(user_role_id, user_id, role_id) VALUES (2,2,2);




CREATE TABLE wk_resource (
  resource_id      int          NOT NULL AUTO_INCREMENT,
  resource_name    varchar(255) NOT NULL                  COMMENT '权限名称',
  url              varchar(255) DEFAULT ''                COMMENT 'API RUL',
  description      varchar(255) DEFAULT ''                COMMENT '资源描述',
  sort             int          DEFAULT 0                 COMMENT '排序号，越大越靠前',
  parent_id        int          NOT NULL DEFAULT 0        COMMENT '父ID',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8  COMMENT='资源（权限）表';


INSERT INTO wk_resource(resource_id, resource_name, url) VALUES (1,'欢迎页'   ,'/hello/**');
INSERT INTO wk_resource(resource_id, resource_name, url) VALUES (2,'Mybatis' ,'/mybatis/**');



CREATE TABLE wk_role_resource (
  role_resource_id    int NOT NULL AUTO_INCREMENT,
  role_id             int DEFAULT NULL,
  resource_id         int DEFAULT NULL,
  PRIMARY KEY (role_resource_id),
  CONSTRAINT fk_role_resource_role      FOREIGN KEY (role_id)     REFERENCES wk_role     (role_id),
  CONSTRAINT fk_role_resource_resource  FOREIGN KEY (resource_id) REFERENCES wk_resource (resource_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;


INSERT INTO wk_role_resource(role_resource_id,role_id,resource_id) VALUES(1,1,1);
INSERT INTO wk_role_resource(role_resource_id,role_id,resource_id) VALUES(2,1,2);
INSERT INTO wk_role_resource(role_resource_id,role_id,resource_id) VALUES(3,2,1);

