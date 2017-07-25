
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table db_portfolio_management_service.tbl_agent
CREATE TABLE IF NOT EXISTS `tbl_agent` (
  `agent_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `agent_name` varchar(255) DEFAULT NULL,
  `agent_firstname` varchar(255) DEFAULT NULL,
  `agent_lastname` varchar(255) DEFAULT NULL,
  `agent_email` varchar(255) DEFAULT NULL,
  `agent_birthdate` datetime DEFAULT NULL,
  `agent_gender` varchar(255) DEFAULT NULL,
  `agent_street` varchar(255) DEFAULT NULL,
  `agent_housenumber` varchar(255) DEFAULT NULL,
  `agent_zipcode` varchar(255) DEFAULT NULL,
  `agent_city` varchar(255) DEFAULT NULL,
  `agent_country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`agent_id`),
  UNIQUE KEY `agent_id_UNIQUE` (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_customer
CREATE TABLE IF NOT EXISTS `tbl_customer` (
  `customer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_username` varchar(255) DEFAULT NULL,
  `customer_firstname` varchar(255) DEFAULT NULL,
  `customer_lastname` varchar(255) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_birthdate` datetime DEFAULT NULL,
  `customer_gender` varchar(255) DEFAULT NULL,
  `customer_street` varchar(255) DEFAULT NULL,
  `customer_housenumber` varchar(255) DEFAULT NULL,
  `customer_zipcode` varchar(255) DEFAULT NULL,
  `customer_city` varchar(255) DEFAULT NULL,
  `customer_country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_document
CREATE TABLE IF NOT EXISTS `tbl_document` (
  `document_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `document_name` varchar(45) DEFAULT NULL,
  `document_url` varchar(255) DEFAULT NULL,
  `signed_by` varchar(45) DEFAULT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `approval_status` varchar(15) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `approval_date` datetime DEFAULT NULL,
  `document_link_service_request_id` int(11) unsigned DEFAULT NULL,
  `document_link_document_type_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  KEY `fk_document_service_request` (`document_link_service_request_id`),
  KEY `fk_document_document_type` (`document_link_document_type_id`),
  CONSTRAINT `fk_document_service_request` FOREIGN KEY (`document_link_service_request_id`) REFERENCES `tbl_service_request` (`service_request_id`),
  CONSTRAINT `fk_document_document_type` FOREIGN KEY (`document_link_document_type_id`) REFERENCES `tbl_document_type` (`document_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_document_type
CREATE TABLE IF NOT EXISTS `tbl_document_type` (
  `document_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `document_type_code` varchar(10) NOT NULL,
  `document_type_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`document_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_outlet
CREATE TABLE IF NOT EXISTS `tbl_outlet` (
  `outlet_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `outlet_link_outlet_type_id` int(10) unsigned NOT NULL,
  `outlet_name` varchar(45) DEFAULT NULL,
  `outlet_address` varchar(500) DEFAULT NULL,
  `outlet_city` varchar(15) DEFAULT NULL,
  `outlet_country` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`outlet_id`),
  KEY `fk_outlet_outlet_type` (`outlet_link_outlet_type_id`),
  CONSTRAINT `fk_outlet_outlet_type` FOREIGN KEY (`outlet_link_outlet_type_id`) REFERENCES `tbl_outlet_type` (`outlet_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_outlet_type
CREATE TABLE IF NOT EXISTS `tbl_outlet_type` (
  `outlet_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `outlet_type_code` varchar(10) NOT NULL,
  `outlet_type_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`outlet_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_promotions
CREATE TABLE IF NOT EXISTS `tbl_promotion` (
  `promotion_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `promotions_link_service_id` int(10) unsigned DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`promotion_id`),
  KEY `fk_promotions_service` (`promotions_link_service_id`),
  CONSTRAINT `fk_promotions_service` FOREIGN KEY (`promotions_link_service_id`) REFERENCES `tbl_service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_service
CREATE TABLE IF NOT EXISTS `tbl_service` (
  `service_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_code` varchar(255) DEFAULT NULL,
  `service_description` varchar(255) DEFAULT NULL,
  `service_link_service_provider_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `fk_service_service_provider_idx` (`service_link_service_provider_id`),
  CONSTRAINT `fk_service_service_provider` FOREIGN KEY (`service_link_service_provider_id`) REFERENCES `tbl_service_provider` (`service_provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_service_provider
CREATE TABLE IF NOT EXISTS `tbl_service_provider` (
  `service_provider_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_provider_name` varchar(45) DEFAULT NULL,
  `service_provider_region` varchar(45) DEFAULT NULL,
  `service_provider_domain` varchar(45) DEFAULT NULL,
  `service_provider_registration_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`service_provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_service_request
CREATE TABLE IF NOT EXISTS `tbl_service_request` (
  `service_request_id` int(10) unsigned NOT NULL,
  `service_request_link_customer_id` int(10) unsigned DEFAULT NULL,
  `service_request_link_outlet_id` int(10) unsigned DEFAULT NULL,
  `service_request_link_service_id` int(10) unsigned DEFAULT NULL,
  `service_request_link_agent_id` int(10) unsigned DEFAULT NULL,
  `service_request_link_service_status_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`service_request_id`),
  KEY `fk_service_request_service` (`service_request_link_service_id`),
  KEY `fk_service_request_customer` (`service_request_link_customer_id`),
  KEY `fk_service_request_outlet` (`service_request_link_outlet_id`),
  KEY `fk_service_request_service_status` (`service_request_link_service_status_id`),
  KEY `fk_service_request_agent` (`service_request_link_agent_id`),
  CONSTRAINT `fk_service_request_agent` FOREIGN KEY (`service_request_link_agent_id`) REFERENCES `tbl_agent` (`agent_id`),
  CONSTRAINT `fk_service_request_customer` FOREIGN KEY (`service_request_link_customer_id`) REFERENCES `tbl_customer` (`customer_id`),
  CONSTRAINT `fk_service_request_outlet` FOREIGN KEY (`service_request_link_outlet_id`) REFERENCES `tbl_outlet` (`outlet_id`),
  CONSTRAINT `fk_service_request_service` FOREIGN KEY (`service_request_link_service_id`) REFERENCES `tbl_service` (`service_id`),
  CONSTRAINT `fk_service_request_service_status` FOREIGN KEY (`service_request_link_service_status_id`) REFERENCES `tbl_service_status` (`service_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_service_status
CREATE TABLE IF NOT EXISTS `tbl_service_status` (
  `service_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_status_code` varchar(10) NOT NULL,
  `service_status_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`service_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_subscription
CREATE TABLE IF NOT EXISTS `tbl_subscription` (
  `subscription_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subscription_name` varchar(45) DEFAULT NULL,
  `subscription_link_service_id` int(10) unsigned DEFAULT NULL,
  `subscription_link_customer_id` int(10) unsigned DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `subscription_link_subscription_type_id` int(10) unsigned DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `renewal_date` datetime DEFAULT NULL,
  PRIMARY KEY (`subscription_id`),
  KEY `fk_subscription_customer` (`subscription_link_customer_id`),
  KEY `fk_subscription_subscription_type` (`subscription_link_subscription_type_id`),
  KEY `fk_subscription_service` (`subscription_link_service_id`),
  CONSTRAINT `fk_subscription_customer` FOREIGN KEY (`subscription_link_customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_subscription_subscription_type` FOREIGN KEY (`subscription_link_subscription_type_id`) REFERENCES `tbl_subscription_type` (`subscription_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_subscription_service` FOREIGN KEY (`subscription_link_service_id`) REFERENCES `tbl_service` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_portfolio_management_service.tbl_subscription_type
CREATE TABLE IF NOT EXISTS `tbl_subscription_type` (
  `subscription_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subscription_type_code` varchar(10) NOT NULL,
  `subscription_type_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`subscription_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;