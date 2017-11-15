-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema health_center
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema health_center
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `health_center` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci ;
USE `health_center` ;

-- -----------------------------------------------------
-- Table `health_center`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `pesel` INT(11) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `birthDate` DATE NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `streetAddress` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `postalCode` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(254) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'ROLE_USER',
  `enabled` INT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`doctor` (
  `ID` INT NOT NULL,
  `specialization_1` VARCHAR(45) NULL,
  `specialization_2` VARCHAR(45) NULL,
  `specialization_3` VARCHAR(45) NULL,
  `medical_title` VARCHAR(45) NULL,
  `information` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_doctor_user1`
    FOREIGN KEY (`ID`)
    REFERENCES `health_center`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`duty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`duty` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `doctor_ID` INT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `room` VARCHAR(45) NOT NULL,
  `free_slots` INT(2) NOT NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_duty_doctor1_idx` (`doctor_ID` ASC),
  CONSTRAINT `fk_duty_doctor1`
    FOREIGN KEY (`doctor_ID`)
    REFERENCES `health_center`.`doctor` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`work_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`work_schedule` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `doctor_ID` INT NOT NULL,
  `day` VARCHAR(45) NOT NULL,
  `room` VARCHAR(45) NOT NULL,
  `start` INT(2) NOT NULL,
  `end` INT(2) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_work_schedule_doctor1_idx` (`doctor_ID` ASC),
  CONSTRAINT `fk_work_schedule_doctor1`
    FOREIGN KEY (`doctor_ID`)
    REFERENCES `health_center`.`doctor` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`patient` (
  `ID` INT NOT NULL,
  `sensitizations` VARCHAR(400) NULL,
  `chronic_diseases` VARCHAR(300) NULL,
  `solid_drugs` VARCHAR(500) NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_patient_user1`
    FOREIGN KEY (`ID`)
    REFERENCES `health_center`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`visit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`visit` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `patient_ID` INT NOT NULL,
  `duty_ID` INT NOT NULL,
  `presence` INT(1) NOT NULL DEFAULT 3,
  `position_in_queue` INT(3) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_visits_duty1_idx` (`duty_ID` ASC),
  INDEX `fk_visit_patient1_idx` (`patient_ID` ASC),
  CONSTRAINT `fk_visits_duty1`
    FOREIGN KEY (`duty_ID`)
    REFERENCES `health_center`.`duty` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_visit_patient1`
    FOREIGN KEY (`patient_ID`)
    REFERENCES `health_center`.`patient` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`patient_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`patient_card` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `patient_ID` INT NOT NULL,
  `visit_ID` INT NOT NULL,
  `comments` VARCHAR(1000) NULL,
  `diagnosis` VARCHAR(200) NULL,
  `prescribed_medicines` VARCHAR(400) NULL,
  `recommendations` VARCHAR(400) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_patient_card_visit1_idx` (`visit_ID` ASC),
  INDEX `fk_patient_card_patient1_idx` (`patient_ID` ASC),
  CONSTRAINT `fk_patient_card_visit1`
    FOREIGN KEY (`visit_ID`)
    REFERENCES `health_center`.`visit` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patient_card_patient1`
    FOREIGN KEY (`patient_ID`)
    REFERENCES `health_center`.`patient` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`Uploads`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`Uploads` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(100) NOT NULL,
  `file` LONGBLOB NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `file_name_UNIQUE` (`file_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`doctor_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`doctor_rating` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `doctor_ID` INT NOT NULL,
  `user_ID` INT NOT NULL,
  `comment` VARCHAR(1000) NOT NULL,
  `rating` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_doctor_rating_doctor1_idx` (`doctor_ID` ASC),
  INDEX `fk_doctor_rating_user1_idx` (`user_ID` ASC),
  CONSTRAINT `fk_doctor_rating_doctor1`
    FOREIGN KEY (`doctor_ID`)
    REFERENCES `health_center`.`doctor` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_rating_user1`
    FOREIGN KEY (`user_ID`)
    REFERENCES `health_center`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
