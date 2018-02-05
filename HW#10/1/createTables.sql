CREATE TABLE `test`.`product` (
  `maker` VARCHAR(10) NULL,
  `model` VARCHAR(50) NOT NULL,
  `type` ENUM('pc', 'printer', 'laptop') NULL DEFAULT NULL,
  PRIMARY KEY (`model`));
    
CREATE TABLE `test`.`laptop` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NULL,
  `speed` SMALLINT NULL,
  `ram` SMALLINT NULL,
  `hd` REAL NULL,
  `price` INT NULL,
  `screen` TINYINT NULL,
  PRIMARY KEY (`code`));
  
  
ALTER TABLE `test`.`laptop` 
ADD CONSTRAINT `LaptopModelPK`
  FOREIGN KEY (`model`)
  REFERENCES `test`.`product` (`model`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
ALTER TABLE `test`.`laptop` 
ADD INDEX `LaptopModelPK_idx` (`model` ASC);


CREATE TABLE `test`.`pc` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NULL,
  `speed` SMALLINT NULL,
  `ram` SMALLINT NULL,
  `hd` REAL NULL,
  `cd` VARCHAR(10) NULL,
  `price` INT NULL,
  PRIMARY KEY (`code`),
  INDEX `PcProdFK_idx` (`model` ASC),
  CONSTRAINT `PcProdFK`
    FOREIGN KEY (`model`)
    REFERENCES `test`.`product` (`model`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
CREATE TABLE `senla_firstprinter`.`printer` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NULL,
  `color` VARCHAR(1) NULL,
  `type` VARCHAR(10) NULL,
  `price` INT NULL,
  INDEX `PrinterProductPK_idx` (`model` ASC),
  CONSTRAINT `PrinterProductPK`
    FOREIGN KEY (`model`)
    REFERENCES `test`.`product` (`model`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
