CREATE TABLE `driving` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `차량번호` VARCHAR(45) NULL,
           `년` VARCHAR(2) NULL,
           `월` VARCHAR(2) NULL,
           `일` VARCHAR(2) NULL,
           `시` VARCHAR(2) NULL,
           `시분초` VARCHAR(10) NULL,
           `차량속도` INT NULL,
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

create table member (
                        member_id bigint not null auto_increment,
                        email varchar(255),
                        password varchar(255),
                        roles varchar(255),
                        userid varchar(255),
                        username varchar(255),
                        created_at datetime(6),
                        modified_at datetime(6),
                        primary key (member_id)
)

create table my_file_list (
                              file_id bigint not null auto_increment,
                              file_extension varchar(255),
                              file_size bigint not null,
                              new_name varchar(255),
                              origin_name varchar(255),
                              upload_id bigint,
                              created_at datetime(6),
                              modified_at datetime(6),
                              primary key (file_id)
)

create table upload_list (
                             upload_id bigint not null auto_increment,
                             member_id bigint,
                             created_at datetime(6),
                             modified_at datetime(6),
                             primary key (upload_id)
)