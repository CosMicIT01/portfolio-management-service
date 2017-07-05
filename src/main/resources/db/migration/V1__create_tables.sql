SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `tbl_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_user` (
    `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_username` VARCHAR (255) NULL,
    `user_firstname` VARCHAR (255) NULL,
    `user_lastname` VARCHAR (255) NULL,
    `user_email` VARCHAR (255) NULL,
    `user_birthdate` DATETIME NULL,
    `user_gender` VARCHAR (255) NULL,
    `user_street` VARCHAR (255) NULL,
    `user_housenumber` VARCHAR (255) NULL,
    `user_zipcode` VARCHAR (255) NULL,
    `user_city` VARCHAR (255) NULL,
    `user_country` VARCHAR (255) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_agent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_agent` (
    `agent_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `agent_name` VARCHAR (255) NULL,
    `agent_firstname` VARCHAR (255) NULL,
    `agent_lastname` VARCHAR (255) NULL,
    `agent_email` VARCHAR (255) NULL,
    `agent_birthdate` DATETIME NULL,
    `agent_gender` VARCHAR (255) NULL,
    `agent_street` VARCHAR (255) NULL,
    `agent_housenumber` VARCHAR (255) NULL,
    `agent_zipcode` VARCHAR (255) NULL,
    `agent_city` VARCHAR (255) NULL,
    `agent_country` VARCHAR (255) NULL,
  PRIMARY KEY (`agent_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_serviceprovider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_serviceprovider` (
    `serviceprovider_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `serviceprovider_name` VARCHAR (255) NULL,
    `serviceprovider_email` VARCHAR (255) NULL,
    `serviceprovider_street` VARCHAR (255) NULL,
    `serviceprovider_housenumber` VARCHAR (255) NULL,
    `serviceprovider_zipcode` VARCHAR (255) NULL,
    `serviceprovider_city` VARCHAR (255) NULL,
    `serviceprovider_country` VARCHAR (255) NULL,
    `serviceprovider_companyurl` VARCHAR (255) NULL,
  PRIMARY KEY (`serviceprovider_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_service` (
    `service_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `service_code` VARCHAR (255) NULL,
    `service_description` VARCHAR (255) NULL,
  PRIMARY KEY (`service_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_servicecontract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_servicecontract` (
    `servicecontract_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `servicecontract_code` VARCHAR (255) NULL,
    `servicecontract_servicename` VARCHAR (255) NULL,
    `servicecontract_expirydate` DATETIME NULL,
  PRIMARY KEY (`servicecontract_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;