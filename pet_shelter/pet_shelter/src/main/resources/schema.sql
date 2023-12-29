-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`adopter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`adopter` (
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `join_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `adopter_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(20) NOT NULL,
  `secondName` VARCHAR(20) NOT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  `address` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`adopter_id`),
  UNIQUE INDEX `adopter_id_UNIQUE` (`adopter_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`staff` (
  `staff_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `shelter_id` INT UNSIGNED NULL DEFAULT NULL,
  `is_admin` TINYINT NULL DEFAULT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `staff_id_UNIQUE` (`staff_id` ASC) VISIBLE,
  INDEX `fk_staff_shelter_idx` (`shelter_id` ASC) VISIBLE,
  CONSTRAINT `fk_staff_shelter`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `mydb`.`shelter` (`idshelter`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`shelter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`shelter` (
  `idshelter` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `manager_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idshelter`),
  UNIQUE INDEX `idshelter_UNIQUE` (`idshelter` ASC) VISIBLE,
  INDEX `fk_shelter_staff1_idx` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `fk_shelter_staff1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `mydb`.`staff` (`staff_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pet` (
  `idpet` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `species` VARCHAR(45) NOT NULL,
  `breed` VARCHAR(45) NULL DEFAULT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` ENUM('male', 'female') NOT NULL,
  `health_status` VARCHAR(100) NULL DEFAULT NULL,
  `behavior` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `house_training` ENUM('trained', 'not_trained') NOT NULL,
  `neutering_status` TINYINT(1) NULL DEFAULT NULL,
  `shelter_id` INT(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idpet`),
  INDEX `fk_pet_shelter1_idx` (`shelter_id` ASC) VISIBLE,
  CONSTRAINT `fk_pet_shelter1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `mydb`.`shelter` (`idshelter`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`adoption_application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`adoption_application` (
  `app_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` ENUM('approved', 'rejected', 'pending') NOT NULL DEFAULT 'pending',
  `pet_id` BIGINT UNSIGNED NOT NULL,
  `adopter_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`app_id`),
  UNIQUE INDEX `app_id_UNIQUE` (`app_id` ASC) VISIBLE,
  INDEX `fk_adoption_application_pet1_idx` (`pet_id` ASC) VISIBLE,
  INDEX `fk_adoption_application_adopter1_idx` (`adopter_id` ASC) VISIBLE,
  CONSTRAINT `fk_adoption_application_adopter1`
    FOREIGN KEY (`adopter_id`)
    REFERENCES `mydb`.`adopter` (`adopter_id`),
  CONSTRAINT `fk_adoption_application_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`idpet`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`adoption_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`adoption_record` (
  `adopting_family` VARCHAR(45) NOT NULL,
  `pet_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`pet_id`),
  CONSTRAINT `fk_adoption_record_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`idpet`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`app_notify`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`app_notify` (
  `app_id` BIGINT UNSIGNED NOT NULL,
  `adopter_id` BIGINT UNSIGNED NOT NULL,
  `not_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`app_id`),
  UNIQUE INDEX `app_id_UNIQUE` (`app_id` ASC) VISIBLE,
  INDEX `fk_app_notify_adoption_application1_idx` (`app_id` ASC) VISIBLE,
  INDEX `fk_app_notify_adopter1_idx` (`adopter_id` ASC) VISIBLE,
  CONSTRAINT `fk_app_notify_adopter1`
    FOREIGN KEY (`adopter_id`)
    REFERENCES `mydb`.`adopter` (`adopter_id`),
  CONSTRAINT `fk_app_notify_adoption_application1`
    FOREIGN KEY (`app_id`)
    REFERENCES `mydb`.`adoption_application` (`app_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`documents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`documents` (
  `path` VARCHAR(100) NOT NULL,
  `type` ENUM('pdf', 'image') NOT NULL,
  `pet_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pet_id`),
  CONSTRAINT `fk_documents_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`idpet`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`vaccinated`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vaccinated` (
  `vaccine` VARCHAR(45) NOT NULL,
  `pet_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`vaccine`),
  UNIQUE INDEX `vaccine_UNIQUE` (`vaccine` ASC) VISIBLE,
  INDEX `fk_vaccinated_pet1_idx` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `fk_vaccinated_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`idpet`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
