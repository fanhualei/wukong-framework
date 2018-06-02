use wukong_write;

DROP TABLE IF EXISTS wk_field_type;


-- sql的类型 field_type

create table wk_field_type (
  field_type_id     bigint unsigned            NOT NULL AUTO_INCREMENT    COMMENT '用户id',

  tinyintf           tinyint        ,
  tinyint_unsignedf  tinyint unsigned,
  smallintf          smallint,
  smallint_unsignedf smallint unsigned,
  mediumintf         mediumint,
  mediumint_unsignedf mediumint unsigned,
  intf               int,
  int_unsignedf      int unsigned,
  bigintf            bigint         ,
  bigint_unsignedf   bigint unsigned,

  decimalf    decimal(15,4),
  varcharf     varchar(30),
  datef       date,
  timef       time,
  datetimef   datetime,



  PRIMARY KEY (field_type_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='测试类型数据表';