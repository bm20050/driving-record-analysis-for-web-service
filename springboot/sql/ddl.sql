CREATE TABLE `driving` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `차량번호` VARCHAR(45) NULL,
           `년` VARCHAR(2) NULL,
           `월` VARCHAR(2) NULL,
           `일` VARCHAR(2) NULL,
           `시` VARCHAR(2) NULL,
           `시분초` VARCHAR(10) NULL,
           `속도` INT NULL,
           `가속도` INT NULL,
           `RPM` INT NULL,
           `급가속` INT DEFAULT 0,
           `급출발` INT DEFAULT 0,
           `급감속` INT DEFAULT 0,
           `급정지` INT DEFAULT 0,
           `GPS_X` VARCHAR(45) NULL,
           `GPS_Y` VARCHAR(45) NULL,
           PRIMARY KEY (`id`)
);


CREATE TABLE `reqlog` (
          `seq` INT NOT NULL AUTO_INCREMENT,
          `요청일시` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
          `차량번호` VARCHAR(45) NULL,
          `운행일시` VARCHAR(45) NULL,
          `운행회차` VARCHAR(45) NULL,
          `요청메뉴` VARCHAR(45) NULL,
          `응답메시지` VARCHAR(45) NULL,
          PRIMARY KEY (`seq`)
);

Hibernate:

create table member (
                        member_id bigint not null auto_increment,
                        email varchar(255),
                        password varchar(255),
                        roles varchar(255),
                        userid varchar(255),
                        username varchar(255),
                        primary key (member_id)
) engine=InnoDB
Hibernate:

create table my_file_list (
                              file_id bigint not null auto_increment,
                              file_extension varchar(255),
                              file_size bigint not null,
                              new_name varchar(255),
                              origin_name varchar(255),
                              upload_id bigint,
                              primary key (file_id)
) engine=InnoDB
Hibernate:

create table upload_list (
                             upload_id bigint not null auto_increment,
                             request_date datetime(6),
                             member_id bigint,
                             primary key (upload_id)
) engine=InnoDB
Hibernate:

alter table member
drop index UK_6yhxjegychh1rq9jfynisnhro
    Hibernate:

alter table member
    add constraint UK_6yhxjegychh1rq9jfynisnhro unique (userid)
    Hibernate:

alter table my_file_list
    add constraint FK8kedpihxotws42foqryh0hqrk
        foreign key (upload_id)
            references upload_list (upload_id)
    Hibernate:

alter table upload_list
    add constraint FKger9h8khg4egenfr4v93wohxn
        foreign key (member_id)
            references member (member_id)
