CREATE TABLE `adopter` (
  `username` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `adopter_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`adopter_id`),
  UNIQUE KEY `adopter_id_UNIQUE` (`adopter_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `adoption_application` (
  `app_id` bigint unsigned NOT NULL,
  `adopter_firstname` varchar(20) NOT NULL,
  `adopter_lastname` varchar(20) NOT NULL,
  `adopter_phone` varchar(15) NOT NULL,
  `adopter_address` varchar(100) NOT NULL,
  `status` enum('approved','rejected','pending') NOT NULL,
  `pet_id` bigint NOT NULL,
  `adopter_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idadoption_application_UNIQUE` (`app_id`),
  KEY `fk_adoption_application_pet1_idx` (`pet_id`),
  KEY `fk_adoption_application_adopter1_idx` (`adopter_id`),
  CONSTRAINT `fk_adoption_application_adopter1` FOREIGN KEY (`adopter_id`) REFERENCES `adopter` (`adopter_id`),
  CONSTRAINT `fk_adoption_application_pet1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`idpet`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `adoption_record` (
  `adopting_family` varchar(45) NOT NULL,
  `pet_id` bigint NOT NULL,
  PRIMARY KEY (`pet_id`),
  CONSTRAINT `fk_adoption_record_pet1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`idpet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `app_notify` (
  `adopter_id` bigint unsigned NOT NULL,
  `app_id` bigint unsigned NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`adopter_id`),
  KEY `fk_app_notify_adopter1_idx` (`adopter_id`),
  KEY `fk_app_notify_adoption_application1_idx` (`app_id`),
  CONSTRAINT `fk_app_notify_adopter1` FOREIGN KEY (`adopter_id`) REFERENCES `adopter` (`adopter_id`),
  CONSTRAINT `fk_app_notify_adoption_application1` FOREIGN KEY (`app_id`) REFERENCES `adoption_application` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `documents` (
  `path` varchar(100) NOT NULL,
  `type` enum('pdf','image') NOT NULL,
  `pet_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pet_id`),
  CONSTRAINT `fk_documents_pet1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`idpet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `pet` (
  `idpet` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `species` varchar(45) NOT NULL,
  `breed` varchar(45) DEFAULT NULL,
  `date_of_birth` date NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `health_status` varchar(100) DEFAULT NULL,
  `behavior` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `house_training` enum('trained','not_trained') NOT NULL,
  `neuturing_status` tinyint DEFAULT NULL,
  `shelter_id` int NOT NULL,
  `join_date` date NOT NULL,
  PRIMARY KEY (`idpet`),
  KEY `fk_pet_shelter1_idx` (`shelter_id`),
  CONSTRAINT `fk_pet_shelter1` FOREIGN KEY (`shelter_id`) REFERENCES `shelter` (`idshelter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `shelter` (
  `idshelter` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `location` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `manager_id` int NOT NULL,
  PRIMARY KEY (`idshelter`),
  KEY `fk_shelter_staff1_idx` (`manager_id`),
  CONSTRAINT `fk_shelter_staff1` FOREIGN KEY (`manager_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `shelter_id` int DEFAULT NULL,
  `staffcol` tinyint NOT NULL,
  `is_admin` tinyint DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `fk_staff_shelter_idx` (`shelter_id`),
  CONSTRAINT `fk_staff_shelter` FOREIGN KEY (`shelter_id`) REFERENCES `shelter` (`idshelter`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `vaccinated` (
  `vaccine` varchar(45) NOT NULL,
  `pet_id` bigint NOT NULL,
  PRIMARY KEY (`vaccine`),
  KEY `fk_vaccinated_pet1_idx` (`pet_id`),
  CONSTRAINT `fk_vaccinated_pet1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`idpet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;