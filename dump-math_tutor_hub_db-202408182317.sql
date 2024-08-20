-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: math_tutor_hub_db
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question_text` text NOT NULL,
  `image_url` text,
  `difficulty` enum('easy','medium','hard') DEFAULT NULL,
  `options` json DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What is the value of 5 + 7?',NULL,'easy','{\"A\": \"10\", \"B\": \"12\", \"C\": \"14\", \"D\": \"16\"}','2024-08-17 10:58:27','2024-08-17 10:58:27'),(2,'Solve the equation: 2x + 3 = 7',NULL,'medium','{\"A\": \"x = 1\", \"B\": \"x = 2\", \"C\": \"x = 3\", \"D\": \"x = 4\"}','2024-08-17 10:58:27','2024-08-17 10:58:27'),(3,'What is the derivative of sin(x)?',NULL,'hard','{\"A\": \"cos(x)\", \"B\": \"-cos(x)\", \"C\": \"sin(x)\", \"D\": \"-sin(x)\"}','2024-08-17 10:58:27','2024-08-17 10:58:27'),(4,'Identify the triangle type in the image','http://example.com/triangle_image.jpg','medium','{\"A\": \"Equilateral\", \"B\": \"Isosceles\", \"C\": \"Scalene\", \"D\": \"Right\"}','2024-08-17 10:58:27','2024-08-17 10:58:27');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionYearTopic`
--

DROP TABLE IF EXISTS `QuestionYearTopic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QuestionYearTopic` (
  `question_id` int NOT NULL,
  `year_topic_id` int NOT NULL,
  PRIMARY KEY (`question_id`,`year_topic_id`),
  KEY `year_topic_id` (`year_topic_id`),
  CONSTRAINT `questionyeartopic_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `questionyeartopic_ibfk_2` FOREIGN KEY (`year_topic_id`) REFERENCES `year_topic` (`year_topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionYearTopic`
--

LOCK TABLES `QuestionYearTopic` WRITE;
/*!40000 ALTER TABLE `QuestionYearTopic` DISABLE KEYS */;
INSERT INTO `QuestionYearTopic` VALUES (1,1),(1,2),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `QuestionYearTopic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_topic`
--

DROP TABLE IF EXISTS `request_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_topic` (
  `request_id` int NOT NULL,
  `topic` varchar(255) NOT NULL,
  PRIMARY KEY (`request_id`,`topic`),
  CONSTRAINT `request_topic_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `tutoring_request` (`request_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_topic`
--

LOCK TABLES `request_topic` WRITE;
/*!40000 ALTER TABLE `request_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor` (
  `tutor_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `postcode` varchar(20) DEFAULT NULL,
  `is_qualified` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`tutor_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES (1,'John','Doe','john.doe@example.com','123-456-7890','12345',1,'2024-08-16 11:34:19','2024-08-16 11:34:19',1),(2,'Jane','Smith','jane.smith@example.com','987-654-3210','54321',1,'2024-08-16 11:34:19','2024-08-16 11:34:19',1),(3,'Emily','Johnson','emily.johnson@example.com','555-555-5555','67890',0,'2024-08-16 11:34:19','2024-08-16 11:34:19',1),(4,'Michael','Brown','michael.brown@example.com','444-444-4444','11223',1,'2024-08-16 11:34:19','2024-08-16 11:34:19',1),(5,'Sarah','Davis','sarah.davis@example.com','333-333-3333','44556',0,'2024-08-16 11:34:19','2024-08-16 11:34:19',1);
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor_years`
--

DROP TABLE IF EXISTS `tutor_years`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor_years` (
  `tutor_id` int NOT NULL,
  `tutoring_year` int NOT NULL,
  PRIMARY KEY (`tutor_id`,`tutoring_year`),
  KEY `tutoring_year` (`tutoring_year`),
  CONSTRAINT `tutor_years_ibfk_1` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`) ON DELETE CASCADE,
  CONSTRAINT `tutor_years_ibfk_2` FOREIGN KEY (`tutoring_year`) REFERENCES `year_topic` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor_years`
--

LOCK TABLES `tutor_years` WRITE;
/*!40000 ALTER TABLE `tutor_years` DISABLE KEYS */;
INSERT INTO `tutor_years` VALUES (1,6),(4,6),(1,7),(2,7),(3,8);
/*!40000 ALTER TABLE `tutor_years` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutoring_assignments`
--

DROP TABLE IF EXISTS `tutoring_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutoring_assignments` (
  `assignment_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int DEFAULT NULL,
  `tutor_id` int DEFAULT NULL,
  `assigned_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`assignment_id`),
  KEY `request_id` (`request_id`),
  KEY `tutor_id` (`tutor_id`),
  CONSTRAINT `tutoring_assignments_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `tutoring_request` (`request_id`) ON DELETE CASCADE,
  CONSTRAINT `tutoring_assignments_ibfk_2` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutoring_assignments`
--

LOCK TABLES `tutoring_assignments` WRITE;
/*!40000 ALTER TABLE `tutoring_assignments` DISABLE KEYS */;
INSERT INTO `tutoring_assignments` VALUES (5,1,1,'2024-08-17 11:17:22',1),(6,2,2,'2024-08-17 11:17:22',1);
/*!40000 ALTER TABLE `tutoring_assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutoring_request`
--

DROP TABLE IF EXISTS `tutoring_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutoring_request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(100) NOT NULL,
  `student_year` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `parent_mobile` varchar(15) DEFAULT NULL,
  `mode_of_teaching` enum('Online','Face to Face') NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('Pending','Completed') DEFAULT 'Pending',
  `additional_notes` text,
  PRIMARY KEY (`request_id`),
  KEY `user_id` (`user_id`),
  KEY `student_year` (`student_year`),
  CONSTRAINT `tutoring_request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL,
  CONSTRAINT `tutoring_request_ibfk_2` FOREIGN KEY (`student_year`) REFERENCES `year_topic` (`year`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutoring_request`
--

LOCK TABLES `tutoring_request` WRITE;
/*!40000 ALTER TABLE `tutoring_request` DISABLE KEYS */;
INSERT INTO `tutoring_request` VALUES (1,'John Doe',7,1,'+1234567890','Online','2024-08-17 11:12:03','Pending','Requires extra help with decimals'),(2,'Jane Smith',8,2,'+1987654321','Face to Face','2024-08-17 11:12:03','Pending','Prefers weekend sessions');
/*!40000 ALTER TABLE `tutoring_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutors_availability`
--

DROP TABLE IF EXISTS `tutors_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutors_availability` (
  `tutor_id` int NOT NULL,
  `day_of_week` enum('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') NOT NULL,
  PRIMARY KEY (`tutor_id`,`day_of_week`),
  CONSTRAINT `tutors_availability_ibfk_1` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutors_availability`
--

LOCK TABLES `tutors_availability` WRITE;
/*!40000 ALTER TABLE `tutors_availability` DISABLE KEYS */;
INSERT INTO `tutors_availability` VALUES (1,'Monday'),(1,'Wednesday'),(2,'Tuesday'),(2,'Thursday'),(3,'Friday'),(4,'Saturday'),(4,'Sunday');
/*!40000 ALTER TABLE `tutors_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `phone_no` varchar(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'STUDENT',
  `password` char(64) NOT NULL,
  `salt` char(16) NOT NULL,
  `subscription_tier` varchar(50) DEFAULT 'FREE',
  `user_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password_last_changed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'john.doe@example.com','John','Doe','123-456-7890','123 Elm Street','12345','STUDENT','e3afed0047b08059d0fada10f400c1e5','a1b2c3d4e5f6g7h8','FREE','2024-01-01 10:00:00','2024-01-01 10:00:00',NULL),(2,'jane.smith@example.com','Jane','Smith','098-765-4321','456 Oak Avenue','67890','TEACHER','a3c8e77f5c3fd0a33f4b2e3e0f1234cd','i9j0k1l2m3n4o5p6','PREMIUM','2024-02-01 11:00:00','2024-02-01 11:00:00','2024-03-01 12:00:00'),(3,'alice.johnson@example.com','Alice','Johnson','555-555-5555','789 Pine Road','54321','ADMIN','d5f2e78c8a4a2f0f5a9b1e2c7d6b8f4e','p7q8r9s0t1u2v3w4','FREE','2024-03-01 12:30:00','2024-03-01 12:30:00','2024-04-01 12:30:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `year_topic`
--

DROP TABLE IF EXISTS `year_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `year_topic` (
  `year_topic_id` int NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL,
  `topic` varchar(50) NOT NULL,
  PRIMARY KEY (`year_topic_id`),
  UNIQUE KEY `year` (`year`,`topic`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `year_topic`
--

LOCK TABLES `year_topic` WRITE;
/*!40000 ALTER TABLE `year_topic` DISABLE KEYS */;
INSERT INTO `year_topic` VALUES (2,6,'Money'),(1,6,'Percents'),(4,7,'Add and subtract decimals'),(3,7,'Multiply decimals'),(8,8,'Add and subtract decimals'),(6,8,'Money'),(7,8,'Multiply decimals'),(5,8,'Percents');
/*!40000 ALTER TABLE `year_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'math_tutor_hub_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-18 23:17:44
