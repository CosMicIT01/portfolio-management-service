SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `tbl_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_linkagentid` INT(10) UNSIGNED NOT NULL,
  `user_name` VARCHAR(255) NULL,
  `user_address` VARCHAR(255) NULL,
  `user_city` VARCHAR(255) NULL,
  PRIMARY KEY (`user_id`),
  INDEX `idx_user_linkagentid` (`user_linkagentid` ASC))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;