-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wvat
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `inspectionrecords`
--

DROP TABLE IF EXISTS `inspectionrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspectionrecords` (
  `domainNum` int NOT NULL AUTO_INCREMENT,
  `domain` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `osCommandInput` text,
  `sqlInjectionInput` text,
  `cveInput` text,
  `directoryIndexingInput` text,
  `informationLeakageInput` text,
  `xssInput` text,
  `pathTrackingInput` text,
  `adminExposeInput` text,
  `locationDisclosureInput` text,
  `plainTextInput` text,
  `webMethodInput` text,
  `osCommandOutput` text,
  `sqlInjectionOutput` text,
  `cveOutput` text,
  `directoryIndexingOutput` text,
  `informationLeakageOutput` text,
  `xssOutput` text,
  `pathTrackingOutput` text,
  `adminExposeOutput` text,
  `locationDisclosureOutput` text,
  `plainTextOutput` text,
  `webMethodOutput` text,
  PRIMARY KEY (`domainNum`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspectionrecords`
--

LOCK TABLES `inspectionrecords` WRITE;
/*!40000 ALTER TABLE `inspectionrecords` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspectionrecords` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-22 11:30:53
