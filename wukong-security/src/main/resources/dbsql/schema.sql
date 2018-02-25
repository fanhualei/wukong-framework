
DROP TABLE IF EXISTS `wk_users`;

create table wk_users (
  username varchar(256) NOT NULL PRIMARY KEY,
  password varchar(256) NOT NULL,
  enabled boolean DEFAULT TRUE
);

DROP TABLE IF EXISTS `wk_authorities`;

create table wk_authorities (
  username varchar(256),
  authority varchar(256),
  CONSTRAINT wk_authorities_pk PRIMARY KEY (username, authority),
  CONSTRAINT wk_authorities_wk_users_fk FOREIGN KEY (username) REFERENCES wk_users (username);
);