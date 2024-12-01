-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: steri_emr
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `triage_form`
--

DROP TABLE IF EXISTS `triage_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `triage_form` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `title` varchar(55) DEFAULT NULL,
  `date` char(10) DEFAULT NULL,
  `time` char(10) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `physician` varchar(30) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `middlename` varchar(20) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(15) DEFAULT NULL,
  `addresss` varchar(200) DEFAULT NULL,
  `insurance_company` varchar(30) DEFAULT NULL,
  `insured_name` varchar(30) DEFAULT NULL,
  `memberid` varchar(15) DEFAULT NULL,
  `groupid` varchar(15) DEFAULT NULL,
  `bp` varchar(10) DEFAULT NULL,
  `hr` int DEFAULT NULL,
  `o2` float DEFAULT NULL,
  `temp` float DEFAULT NULL,
  `othervitals` varchar(70) DEFAULT NULL,
  `patienthistory` varchar(300) DEFAULT NULL,
  `medications` varchar(300) DEFAULT NULL,
  `allergies` varchar(150) DEFAULT NULL,
  `complaints` varchar(300) DEFAULT NULL,
  `pain` int DEFAULT NULL,
  `refill` varchar(300) DEFAULT NULL,
  `diagnosis` varchar(300) DEFAULT NULL,
  `referal` tinyint(1) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `assignToId` int DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `triage_form`
--

LOCK TABLES `triage_form` WRITE;
/*!40000 ALTER TABLE `triage_form` DISABLE KEYS */;
INSERT INTO `triage_form` VALUES (1,'Test','2024-02-29','1600','Chicago','Doogie Howswer','Sheri','L',NULL,'123','555-1212','steri@test.com','123 Main','Allstate','Ster','987','654','120/70',60,96,97,'none','none','none','hayfever','headache',5,'Yes','working too much',NULL,0,NULL),(2,'BP Test','4/10/24','10am','Chicago','Doogie Howser','Mickey','M','Mouse','4654','555-1212','mickey@test.com','123 main		','Blue Cross','Minnie Mouse','45664','64654','120/60',60,99,98,'none','eats too much cheese	','lipitor	','cats','too many cats',4,'none	','eats too much cheese',0,NULL,4);
/*!40000 ALTER TABLE `triage_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `triage_template`
--

DROP TABLE IF EXISTS `triage_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `triage_template` (
  `title` varchar(60) DEFAULT NULL,
  `datetime` tinyint(1) DEFAULT NULL,
  `ci` tinyint(1) DEFAULT NULL,
  `insurance` tinyint(1) DEFAULT NULL,
  `vitals` tinyint(1) DEFAULT NULL,
  `history` tinyint(1) DEFAULT NULL,
  `currentmeds` tinyint(1) DEFAULT NULL,
  `allergies` tinyint(1) DEFAULT NULL,
  `maincomplaint` tinyint(1) DEFAULT NULL,
  `pain` tinyint(1) DEFAULT NULL,
  `medrefill` tinyint(1) DEFAULT NULL,
  `diagnosis` tinyint(1) DEFAULT NULL,
  `mhreferal` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `triage_template`
--

LOCK TABLES `triage_template` WRITE;
/*!40000 ALTER TABLE `triage_template` DISABLE KEYS */;
INSERT INTO `triage_template` VALUES ('BP Test',1,1,1,1,1,1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `triage_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `specialty` varchar(45) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'medstaff1','Test1234','Mary Sloan','med1@staff.com','nursing',2,1,'2024-02-05 03:01:07','555-1212'),(3,'pharma1','Test1234','Peter Pan','one@pharma.com','drugs',3,1,'2024-02-05 03:05:55','555-1212'),(4,'tuesday','Test1234','Tuesday Bob','test@tuesday.com','testing',1,1,'2024-02-06 20:40:11','555-1212'),(5,'mickey','Cheese1','Mickey Mouse','mickey@moust.com','eating',2,1,'2024-02-06 20:41:37','333-4444');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-09  8:57:02
