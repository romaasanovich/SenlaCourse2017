create schema `mydb`;
CREATE TABLE IF NOT EXISTS `mydb`.`Place` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL,
  `isBusy` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`Work` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nameOfService` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `idMaster` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`Master` (
    `id` INT NOT NULL AUTO_INCREMENT ,
    `name` VARCHAR(45) NOT NULL,
    `isWork` TINYINT NOT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB;



CREATE TABLE IF NOT EXISTS `mydb`.`Order` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `idService` INT NOT NULL,
  `idMaster` INT NULL,
  `idPlace` INT NOT NULL,
  `status` ENUM('Broned', 'Closed', 'Opened', 'Deleted', 'Canceled') NOT NULL,
  `orderDate` DATETIME NOT NULL,
  `plannedStartDate` DATETIME NOT NULL,
  `completionDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
  
ENGINE = InnoDB;

ALTER table `mydb`.`Order`
ADD INDEX `fk_Order_Work1_idx` (`idService` ASC),
ADD   INDEX `fk_Order_Place1_idx` (`idPlace` ASC),
 ADD  INDEX `fk_Order_Master1_idx` (`idMaster` ASC),
  ADD CONSTRAINT `fk_Order_Work1`
    FOREIGN KEY (`idService`)
    REFERENCES `mydb`.`Work` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 ADD  CONSTRAINT `fk_Order_Place1`
    FOREIGN KEY (`idPlace`)
    REFERENCES `mydb`.`Place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Order_Master1`
    FOREIGN KEY (`idMaster`)
    REFERENCES `mydb`.`Master` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER table `mydb`.`work`
ADD INDEX `fk_Work_Master_idx` (`idMaster` ASC),
  ADD CONSTRAINT `fk_Work_Master_idx`
    FOREIGN KEY (`idMaster`)
    REFERENCES `mydb`.`master` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;