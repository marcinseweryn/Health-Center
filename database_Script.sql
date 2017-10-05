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
-- Table `health_center`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`users` (
  `pesel` INT(11) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `birthDate` DATE NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `streetAddress` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postalCode` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'ROLE_USER',
  `enabled` INT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`pesel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`doctors` (
  `pesel` INT(11) NOT NULL,
  `specialization_1` VARCHAR(45) NULL,
  `specialization_2` VARCHAR(45) NULL,
  `specialization_3` VARCHAR(45) NULL,
  `information` VARCHAR(45) NULL,
  PRIMARY KEY (`pesel`),
  CONSTRAINT `fk_doctors_users`
    FOREIGN KEY (`pesel`)
    REFERENCES `health_center`.`users` (`pesel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`duty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`duty` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `doctor_ID` INT(11) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `room` VARCHAR(45) NOT NULL,
  `free_slots` INT(2) NOT NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_duty_doctors1_idx` (`doctor_ID` ASC),
  CONSTRAINT `fk_duty_doctors1`
    FOREIGN KEY (`doctor_ID`)
    REFERENCES `health_center`.`doctors` (`pesel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`work_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`work_schedule` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `day` VARCHAR(45) NOT NULL,
  `room` VARCHAR(45) NOT NULL,
  `start` INT(2) NOT NULL,
  `end` INT(2) NOT NULL,
  `pesel` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_work_schedule_doctors1_idx` (`pesel` ASC),
  CONSTRAINT `fk_work_schedule_doctors1`
    FOREIGN KEY (`pesel`)
    REFERENCES `health_center`.`doctors` (`pesel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_center`.`visits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_center`.`visits` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `duty_ID` INT NOT NULL,
  `patient_pesel` INT(11) NOT NULL,
  `presence` INT(1) NOT NULL DEFAULT 3,
  `position_in_queue` INT(3) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_visits_duty1_idx` (`duty_ID` ASC),
  INDEX `fk_visits_users1_idx` (`patient_pesel` ASC),
  CONSTRAINT `fk_visits_duty1`
    FOREIGN KEY (`duty_ID`)
    REFERENCES `health_center`.`duty` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_visits_users1`
    FOREIGN KEY (`patient_pesel`)
    REFERENCES `health_center`.`users` (`pesel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
