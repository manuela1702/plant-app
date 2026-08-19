-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.37 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for plant
CREATE DATABASE IF NOT EXISTS `plant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `plant`;

-- Dumping structure for table plant.my_plant
CREATE TABLE IF NOT EXISTS `my_plant` (
  `my_plant_id` int unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `planting_date` date NOT NULL,
  `plant_species_id` int unsigned NOT NULL,
  PRIMARY KEY (`my_plant_id`),
  KEY `fk_my_plant_plant_species_id` (`plant_species_id`),
  CONSTRAINT `fk_my_plant_plant_species_id` FOREIGN KEY (`plant_species_id`) REFERENCES `plant_species` (`plant_species_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- Dumping data for table plant.my_plant: ~2 rows (approximately)
INSERT INTO `my_plant` (`my_plant_id`, `nickname`, `planting_date`, `plant_species_id`) VALUES
	(1, 'My Monstera', '2026-06-23', 2),
	(20, 'test 22', '2026-08-17', 1),
	(21, 'test', '2026-08-17', 8);

-- Dumping structure for table plant.plant_activity
CREATE TABLE IF NOT EXISTS `plant_activity` (
  `plant_activity_id` int unsigned NOT NULL AUTO_INCREMENT,
  `activity_date` date NOT NULL,
  `activity_type` enum('WATERING','FERTILIZING','REPOTTING') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `my_plant_id` int unsigned NOT NULL,
  PRIMARY KEY (`plant_activity_id`),
  KEY `fk_plant_activity_my_plant_id` (`my_plant_id`),
  CONSTRAINT `fk_plant_activity_my_plant_id` FOREIGN KEY (`my_plant_id`) REFERENCES `my_plant` (`my_plant_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- Dumping data for table plant.plant_activity: ~1 rows (approximately)
INSERT INTO `plant_activity` (`plant_activity_id`, `activity_date`, `activity_type`, `my_plant_id`) VALUES
	(13, '2026-08-05', 'WATERING', 1),
	(15, '2026-07-27', 'REPOTTING', 1),
	(16, '2026-08-17', 'REPOTTING', 1),
	(18, '2026-07-26', 'FERTILIZING', 20),
	(20, '2026-08-17', 'REPOTTING', 20),
	(22, '2026-08-04', 'WATERING', 21),
	(23, '2026-08-17', 'WATERING', 21);

-- Dumping structure for table plant.plant_species
CREATE TABLE IF NOT EXISTS `plant_species` (
  `plant_species_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `scientific_name` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `sunlight` enum('LOW','HIGH') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `watering` enum('LOW','HIGH') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `watering_interval` int NOT NULL,
  `fertilizing_interval` int NOT NULL,
  `repotting_interval` int NOT NULL,
  PRIMARY KEY (`plant_species_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- Dumping data for table plant.plant_species: ~9 rows (approximately)
INSERT INTO `plant_species` (`plant_species_id`, `name`, `scientific_name`, `sunlight`, `watering`, `image_url`, `description`, `watering_interval`, `fertilizing_interval`, `repotting_interval`) VALUES
	(1, 'Snake Plant', 'Sansevieria trifasciata', 'LOW', 'LOW', '/images/plants/snake-plant.jpg', 'Snake Plant is one of the easiest indoor plants to grow. It tolerates low light and requires little watering.', 14, 30, 730),
	(2, 'Monstera', 'Monstera deliciosa', 'HIGH', 'HIGH', '/images/plants/monstera.jpg', 'Monstera is a tropical houseplant known for its large split leaves and fast growth.', 7, 30, 730),
	(3, 'Spider Plant', 'Chlorophytum comosum', 'HIGH', 'HIGH', '/images/plants/spider-plant.jpg', 'Spider Plant is an easy-care indoor plant that produces long arching leaves and baby plantlets.', 7, 30, 650),
	(4, 'Aloe Vera', 'Aloe vera', 'HIGH', 'LOW', '/images/plants/aloe-vera.jpg', 'Aloe Vera is a succulent plant famous for its medicinal gel and drought tolerance.', 14, 90, 730),
	(5, 'Rubber Plant', 'Ficus elastica', 'HIGH', 'HIGH', '/images/plants/rubber-plant.jpg', 'Rubber Plant is known for its glossy dark green leaves and elegant appearance.', 7, 30, 730),
	(6, 'Chinese Evergreen', 'Aglaonema commutatum', 'LOW', 'LOW', '/images/plants/chinese-evergreen.jpg', 'Chinese Evergreen is a popular indoor plant with attractive patterned leaves.', 10, 60, 730),
	(7, 'Dracaena', 'Dracaena fragrans', 'LOW', 'LOW', '/images/plants/dracaena.jpg', 'Dracaena is a slow-growing plant that tolerates low light and infrequent watering.', 10, 30, 730),
	(8, 'Calathea', 'Calathea orbifolia', 'LOW', 'HIGH', '/images/plants/calathea.jpg', 'Calathea is admired for its decorative leaves and prefers consistently moist soil.', 7, 30, 365),
	(9, 'Orchid', 'Phalaenopsis', 'HIGH', 'HIGH', '/images/plants/orchid.jpg', 'Orchid is one of the most popular flowering houseplants. It produces elegant blooms and thrives in bright indirect light with regular watering.', 7, 14, 730);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
