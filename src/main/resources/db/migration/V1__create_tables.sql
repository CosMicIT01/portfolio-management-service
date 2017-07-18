-- MySQL Script generated by MySQL Workbench
-- 07/10/17 19:38:45
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_customer` (
  `customer_id`  INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_username` VARCHAR(255) NULL,
  `customer_firstname` VARCHAR(255) NULL DEFAULT NULL,
  `customer_lastname` VARCHAR(255) NULL DEFAULT NULL,
  `customer_email` VARCHAR(255) NULL DEFAULT NULL,
  `customer_birthdate` DATETIME NULL DEFAULT NULL,
  `customer_gender` VARCHAR(255) NULL DEFAULT NULL,
  `customer_street` VARCHAR(255) NULL DEFAULT NULL,
  `customer_housenumber` VARCHAR(255) NULL DEFAULT NULL,
  `customer_zipcode` VARCHAR(255) NULL DEFAULT NULL,
  `customer_city` VARCHAR(255) NULL DEFAULT NULL,
  `customer_country` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_id_UNIQUE` (`customer_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = '	';


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_service_provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_service_provider` (
  `service_provider_id` INT UNSIGNED NOT NULL,
  `service_id` INT UNSIGNED NULL,
  `service_provider_name` VARCHAR(45) NULL,
  PRIMARY KEY (`service_provider_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_service` (
  `service_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `service_code` VARCHAR(255) NULL DEFAULT NULL,
  `service_description` VARCHAR(255) NULL DEFAULT NULL,
  `service_service_provider_id` INT UNSIGNED NULL,
  `tbl_servicecol` VARCHAR(45) NULL,
  PRIMARY KEY (`service_id`),
  INDEX `fk_service_service_provider_idx` (`service_service_provider_id` ASC),
  CONSTRAINT `fk_service_service_provider`
    FOREIGN KEY (`service_service_provider_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_service_provider` (`service_provider_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_outlet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_outlet` (
  `outlet_id` INT UNSIGNED NOT NULL,
  `outlet_name` VARCHAR(45) NULL,
  PRIMARY KEY (`outlet_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_service_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_service_status` (
  `service_status_code` VARCHAR(10) NOT NULL,
  `service_status_description` VARCHAR(45) NULL,
  PRIMARY KEY (`service_status_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_service_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_service_request` (
  `service_request_id` INT NOT NULL,
  `customer_id` INT UNSIGNED NULL,
  `outlet_id` INT UNSIGNED NULL,
  `service_id` INT UNSIGNED NULL,
  `agent_id` INT UNSIGNED NULL,
  `status_code` VARCHAR(45) NULL,
  PRIMARY KEY (`service_request_id`),
  UNIQUE INDEX `service_request_id_UNIQUE` (`service_request_id` ASC),
  INDEX `fk_service_idx` (`service_id` ASC),
  INDEX `fk_service_request_customer_idx` (`customer_id` ASC),
  INDEX `fk_service_request_outlet_idx` (`outlet_id` ASC),
  INDEX `fk_service_request_service_status_idx` (`status_code` ASC),
  CONSTRAINT `fk_service_request_service`
    FOREIGN KEY (`service_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_request_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_request_outlet`
    FOREIGN KEY (`outlet_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_outlet` (`outlet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_request_service_status`
    FOREIGN KEY (`status_code`)
    REFERENCES `db_portfolio_management_service`.`tbl_service_status` (`service_status_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_outlet_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_outlet_type` (
  `outlet_type_code` VARCHAR(10) NOT NULL,
  `outlet_type_description` VARCHAR(255) NULL,
  `tbl_outlet_outlet_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`outlet_type_code`, `tbl_outlet_outlet_id`),
  INDEX `fk_tbl_outlet_type_tbl_outlet1_idx` (`tbl_outlet_outlet_id` ASC),
  CONSTRAINT `fk_tbl_outlet_type_tbl_outlet1`
    FOREIGN KEY (`tbl_outlet_outlet_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_outlet` (`outlet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_document_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_document_type` (
  `document_type_code` VARCHAR(10) NOT NULL,
  `document_type_description` VARCHAR(45) NULL,
  PRIMARY KEY (`document_type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_notification` (
  `notification_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT UNSIGNED NULL,
  `service_id` INT UNSIGNED NULL,
  `tbl_notificationcol` VARCHAR(45) NULL,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_notification_service_idx` (`service_id` ASC),
  INDEX `fk_notification_customer_idx` (`customer_id` ASC),
  CONSTRAINT `fk_notification_service`
    FOREIGN KEY (`service_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_document` (
  `document_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `document_name` VARCHAR(45) NULL,
  `signed_by` VARCHAR(45) NULL,
  `creation_date` DATETIME NULL,
  `service_request_id` INT NULL,
  `document_type_code` VARCHAR(10) NULL,
  PRIMARY KEY (`document_id`),
  INDEX `fk_document_service_request_idx` (`service_request_id` ASC),
  INDEX `fk_document_document_type_idx` (`document_type_code` ASC),
  CONSTRAINT `fk_document_service_request`
    FOREIGN KEY (`service_request_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_service_request` (`service_request_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_document_type`
    FOREIGN KEY (`document_type_code`)
    REFERENCES `db_portfolio_management_service`.`tbl_document_type` (`document_type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_tariff_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_tariff_type` (
  `tariff_type_code` VARCHAR(10) NOT NULL,
  `tariff_type_description` VARCHAR(45) NULL,
  PRIMARY KEY (`tariff_type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_tariff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_tariff` (
  `tariff_id` INT UNSIGNED NOT NULL,
  `service_id` INT UNSIGNED NULL,
  `effective_from` DATETIME NULL,
  `tariff_type_code` VARCHAR(45) NULL,
  PRIMARY KEY (`tariff_id`),
  INDEX `fk_tariff_service_idx` (`service_id` ASC),
  INDEX `fk_tariff_tariff_type_idx` (`tariff_type_code` ASC),
  CONSTRAINT `fk_tariff_service`
    FOREIGN KEY (`service_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tariff_tariff_type`
    FOREIGN KEY (`tariff_type_code`)
    REFERENCES `db_portfolio_management_service`.`tbl_tariff_type` (`tariff_type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_subscription_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_subscription_type` (
  `subscription_type_code` VARCHAR(10) NOT NULL,
  `subscription_type_description` VARCHAR(45) NULL,
  PRIMARY KEY (`subscription_type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_subscription` (
  `subscription_id` INT NOT NULL AUTO_INCREMENT,
  `service_id` INT UNSIGNED NULL,
  `customer_id` INT UNSIGNED NULL,
  `expiry_date` DATETIME NULL,
  `subscription_type_code` VARCHAR(10) NULL,
  PRIMARY KEY (`subscription_id`),
  INDEX `fk_subscription_customer_idx` (`customer_id` ASC),
  INDEX `fk_subscription_subscription_type_idx` (`subscription_type_code` ASC),
  CONSTRAINT `fk_subscription_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscription_subscription_type`
    FOREIGN KEY (`subscription_type_code`)
    REFERENCES `db_portfolio_management_service`.`tbl_subscription_type` (`subscription_type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_promotions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_promotions` (
  `promotion_id` INT NOT NULL,
  `service_id` INT UNSIGNED NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`promotion_id`),
  INDEX `fk_promotions_service_idx` (`service_id` ASC),
  CONSTRAINT `fk_promotions_service`
    FOREIGN KEY (`service_id`)
    REFERENCES `db_portfolio_management_service`.`tbl_service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_portfolio_management_service`.`tbl_agent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_portfolio_management_service`.`tbl_agent` (
  `agent_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `agent_name` VARCHAR(255) NULL,
  `agent_firstname` VARCHAR(255) NULL DEFAULT NULL,
  `agent_lastname` VARCHAR(255) NULL DEFAULT NULL,
  `agent_email` VARCHAR(255) NULL DEFAULT NULL,
  `agent_birthdate` DATETIME NULL DEFAULT NULL,
  `agent_gender` VARCHAR(255) NULL DEFAULT NULL,
  `agent_street` VARCHAR(255) NULL DEFAULT NULL,
  `agent_housenumber` VARCHAR(255) NULL DEFAULT NULL,
  `agent_zipcode` VARCHAR(255) NULL DEFAULT NULL,
  `agent_city` VARCHAR(255) NULL DEFAULT NULL,
  `agent_country` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`agent_id`),
  UNIQUE INDEX `agent_id_UNIQUE` (`agent_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = '	';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
