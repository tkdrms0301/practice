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
-- Table structure for table `guideline`
--

DROP TABLE IF EXISTS `guideline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guideline` (
  `guideLineNum` int NOT NULL AUTO_INCREMENT,
  `vulnerabilityType` char(20) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`guideLineNum`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guideline`
--

LOCK TABLES `guideline` WRITE;
/*!40000 ALTER TABLE `guideline` DISABLE KEYS */;
INSERT INTO `guideline` VALUES (1,'sqlInjection','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\SqlInjection_GuideLine.pdf','2022-05-25'),(2,'xss','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\XSS_GuideLine.pdf','2022-05-25'),(3,'osCommand','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\osCommand_GuideLine.pdf','2022-05-25'),(4,'directoryIndexing','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\DirectoryIndexing_GuideLine.pdf','2022-05-25'),(5,'plainText','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\PlainText_GuideLine.pdf','2022-05-25'),(6,'locationDisclosure','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\LocationDisclosure_GuideLine.pdf','2022-05-25'),(7,'adminExpose','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\AdminExpose_GuideLine.pdf','2022-05-25'),(8,'pathTracking','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\PathTracking_GuideLine.pdf','2022-05-25'),(9,'informationLeakage','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\InformarionLeakage_GuideLine.pdf','2022-05-25'),(10,'webMethod','D:\\workspace\\WVATTest\\WVAT\\src\\main\\java\\GuidePath\\WebMethod_Option_GuidLine.pdf','2022-05-25');
/*!40000 ALTER TABLE `guideline` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-22 11:30:52
