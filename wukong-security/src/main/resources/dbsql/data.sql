insert into wk_users (username, password, enabled) values ('user', '{noop}user', true);

insert into wk_authorities (username, authority) values ('user', 'ROLE_ADMIN');