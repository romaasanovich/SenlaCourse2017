create schema `autoserviceDB`;
CREATE TABLE IF NOT EXISTS `autoserviceDB`.`Place` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `isBusy` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `autoserviceDB`.`Work` (
  `id` INT NOT NULL,
  `nameOfService` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `autoserviceDB`.`Master` (
    `id` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `id_works` INT NOT NULL,
    `id_orders` INT NOT NULL,
    `isWork` TINYINT NOT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB;



CREATE TABLE IF NOT EXISTS `autoserviceDB`.`Order` (
  `id` INT NOT NULL,
  `id_service` INT NOT NULL,
  `id_master` INT NULL,
  `id_place` INT NOT NULL,
  `status` ENUM('Broned', 'Closed', 'Opened', 'Deleted', 'Canceled') NOT NULL,
  `orderDate` DATETIME NOT NULL,
  `plannedStartDate` DATETIME NOT NULL,
  `completionDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
  
ENGINE = InnoDB;


ALTER table `autoserviceDB`.`Master`
add  INDEX `fk_Master_Work1_idx` (`id_works` ASC),
 add INDEX `fk_Master_Order1_idx` (`id_orders` ASC),
 Add CONSTRAINT `fk_Master_Work1`
    FOREIGN KEY (`id_works`)
    REFERENCES `autoserviceDB`.`Work` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 add CONSTRAINT `fk_Master_Order1`
    FOREIGN KEY (`id_orders`)
    REFERENCES `autoserviceDB`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER table `autoserviceDB`.`Order`
ADD INDEX `fk_Order_Work1_idx` (`id_service` ASC),
ADD   INDEX `fk_Order_Place1_idx` (`id_place` ASC),
 ADD  INDEX `fk_Order_Master1_idx` (`id_master` ASC),
  ADD CONSTRAINT `fk_Order_Work1`
    FOREIGN KEY (`id_service`)
    REFERENCES `autoserviceDB`.`Work` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 ADD  CONSTRAINT `fk_Order_Place1`
    FOREIGN KEY (`id_place`)
    REFERENCES `autoserviceDB`.`Place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Order_Master1`
    FOREIGN KEY (`id_master`)
    REFERENCES `autoserviceDB`.`Master` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;