CREATE SCHEMA `3_task` ;
CREATE TABLE IF NOT EXISTS `3_task`.`Master` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `isWork` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `3_task`.`Work` (
  `id` INT NOT NULL,
  `nameOfService` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `3_task`.`Place` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `isBusy` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `3_task`.`Order` (
  `id` INT NOT NULL,
  `id_service` INT NOT NULL,
  `id_master` INT NULL,
  `id_place` INT NOT NULL,
  `status` ENUM('Broned', 'Closed', 'Opened', 'Deleted', 'Canceled') NOT NULL,
  `orderDate` DATETIME NOT NULL,
  `plannedStartDate` DATETIME NOT NULL,
  `completionDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Order_Work1_idx` (`id_service` ASC),
  INDEX `fk_Order_Place1_idx` (`id_place` ASC),
  INDEX `fk_Order_Master1_idx` (`id_master` ASC),
  CONSTRAINT `fk_Order_Work1`
    FOREIGN KEY (`id_service`)
    REFERENCES `3_task`.`Work` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Place1`
    FOREIGN KEY (`id_place`)
    REFERENCES `3_task`.`Place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Master1`
    FOREIGN KEY (`id_master`)
    REFERENCES `3_task`.`Master` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

