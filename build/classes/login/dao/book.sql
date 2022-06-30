CREATE TABLE book
  (
     id        BIGINT(20) UNSIGNED NOT NULL auto_increment,
     title      VARCHAR(200),
     author    VARCHAR(200),
     category    VARCHAR(100),
     PRIMARY KEY (id)
  )
engine=innodb
DEFAULT charset=utf8; 