CREATE TABLE student
  (
     id        BIGINT(20) UNSIGNED NOT NULL auto_increment,
     name      VARCHAR(200),
     dob       DATE,
     gender    VARCHAR(10),
     mailid    VARCHAR(100),
     mobile_no VARCHAR(12),
     password  VARCHAR(200),
     program    VARCHAR(100),
     branch    VARCHAR(100),
     semester  INT(2),
     add_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
     PRIMARY KEY (id),
     UNIQUE KEY (mailid),
     UNIQUE KEY (mobile_no)
  )
engine=innodb
DEFAULT charset=utf8; 