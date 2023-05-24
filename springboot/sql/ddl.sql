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

