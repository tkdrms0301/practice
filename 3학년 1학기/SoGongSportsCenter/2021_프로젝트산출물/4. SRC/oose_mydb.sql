-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 218.50.198.5    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `asset` (
  `ass_number` int NOT NULL,
  `ass_name` varchar(45) NOT NULL,
  `loss` varchar(45) NOT NULL,
  `sell` varchar(45) NOT NULL,
  `ass_present` varchar(45) NOT NULL,
  `turn_in` varchar(45) NOT NULL,
  PRIMARY KEY (`ass_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES (1,'명세서1','3','2','1','2020-01-12'),(2,'명세서2','5','3','2','2020-03-07'),(3,'명세서3','2','2','0','2020-04-16'),(4,'명세서4','10','3','7','2021-03-28'),(5,'명세서5','5','1','4','2021-07-30');
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assstatement`
--

DROP TABLE IF EXISTS `assstatement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `assstatement` (
  `assets_number` int NOT NULL,
  `assets_name` varchar(45) NOT NULL,
  `price` varchar(45) NOT NULL,
  `amount` varchar(45) NOT NULL,
  `due_return` varchar(45) NOT NULL,
  `partname` varchar(45) NOT NULL,
  PRIMARY KEY (`assets_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assstatement`
--

LOCK TABLES `assstatement` WRITE;
/*!40000 ALTER TABLE `assstatement` DISABLE KEYS */;
INSERT INTO `assstatement` VALUES (1,'모니터','50만원','5개','2020-01-12','드림팀'),(2,'본체','80만원','5개','2020-03-07','사무팀'),(3,'마우스','15만원','10개','2020-09-16','총무팀'),(4,'키보드','30만원','13개','2020-12-30','인사팀');
/*!40000 ALTER TABLE `assstatement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budgeting`
--

DROP TABLE IF EXISTS `budgeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `budgeting` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `BusinessCode` int NOT NULL,
  `Employee` char(10) NOT NULL,
  `ApprovalType` char(10) NOT NULL,
  `Detail` char(255) NOT NULL,
  `OrganizationBudget` int NOT NULL,
  `ExecutionDetails` char(255) NOT NULL,
  `approval` char(10) DEFAULT NULL,
  `Reason` char(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgeting`
--

LOCK TABLES `budgeting` WRITE;
/*!40000 ALTER TABLE `budgeting` DISABLE KEYS */;
INSERT INTO `budgeting` VALUES (1,1,'박민재','변경','교육사업',5000000,'학습교재','변경',NULL),(2,2,'김규철','예산신청','환경사업',5000000,'환경미화','승인',NULL),(3,1,'박민재','변경신청','교육사업',4000000,'학습교재','승인대기','예산부족'),(4,3,'박민재','예산신청','복지사업',80000000,'노후장비교체','승인대기',NULL);
/*!40000 ALTER TABLE `budgeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `career`
--

DROP TABLE IF EXISTS `career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `career` (
  `career_id` int NOT NULL,
  `Employee_employee_id` int NOT NULL,
  `education` varchar(20) NOT NULL,
  `performed` varchar(100) NOT NULL,
  `punishiment` varchar(100) DEFAULT NULL,
  `longevity` date DEFAULT NULL,
  `training` varchar(100) DEFAULT NULL,
  `certificate` varchar(100) DEFAULT NULL,
  `prize` varchar(100) DEFAULT NULL,
  `task` varchar(50) DEFAULT NULL,
  `jop_experience` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`career_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `career`
--

LOCK TABLES `career` WRITE;
/*!40000 ALTER TABLE `career` DISABLE KEYS */;
/*!40000 ALTER TABLE `career` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contract` (
  `contract_id` int NOT NULL AUTO_INCREMENT,
  `representative` varchar(20) NOT NULL,
  `contract_name` varchar(40) NOT NULL,
  `contract_money` bigint NOT NULL,
  `contract_start_date` date NOT NULL,
  `contract_end_date` date NOT NULL,
  `region` varchar(50) NOT NULL,
  `approval_state` varchar(10) DEFAULT '미결재',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`contract_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'홍길삼','오사카소공업협력체결',100000000,'2021-06-24','2021-06-30','Gyeongsangbuk-do','미결재','');
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_request`
--

DROP TABLE IF EXISTS `contract_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contract_request` (
  `contract_request_id` int NOT NULL AUTO_INCREMENT,
  `contract_name` varchar(20) NOT NULL,
  `request_date` date NOT NULL,
  `contract_type` varchar(20) NOT NULL,
  `reannouncement` tinyint(1) DEFAULT NULL,
  `new_bid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`contract_request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_request`
--

LOCK TABLES `contract_request` WRITE;
/*!40000 ALTER TABLE `contract_request` DISABLE KEYS */;
INSERT INTO `contract_request` VALUES (1,'포스코하청공동분배','2021-06-27','단기',0,1);
/*!40000 ALTER TABLE `contract_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL,
  `name` varchar(10) NOT NULL,
  `phone_number` int NOT NULL,
  `position` int NOT NULL,
  `employee_classification` varchar(10) NOT NULL,
  `affiliation` varchar(10) NOT NULL,
  `age` int NOT NULL,
  `gender` tinyint NOT NULL,
  `working_status` varchar(10) NOT NULL,
  `major` varchar(20) NOT NULL,
  `disabled` tinyint NOT NULL,
  `rewarding_patriotism` tinyint NOT NULL,
  `salary_peak` tinyint NOT NULL,
  `recruitment` tinyint NOT NULL,
  `entered_date` date NOT NULL,
  `Employeecol` varchar(45) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'홍길동',111,1,'ㅁㅁㅁ','ㅁㅁ',23,0,'ㅁㅁ','ㅁㅁ',0,0,0,0,'2021-06-14','asd');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee1`
--

DROP TABLE IF EXISTS `employee1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee1` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `employee_classification` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `affiliation` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `age` int NOT NULL,
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `working_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `assignment` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `disabled` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rewarding_patriotism` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salary_peak` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `recruitment` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `entered_date` int NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee1`
--

LOCK TABLES `employee1` WRITE;
/*!40000 ALTER TABLE `employee1` DISABLE KEYS */;
INSERT INTO `employee1` VALUES (1,'홍길동','01012345678','직원','계약직','인사과',25,'남','휴직','보직','고등학교 졸업','컴퓨터 소프트웨어 공학과','대상아님','대상아님','대상아님','신규채용',20200101),(2,'김영희','01011223344','실장','정직원','인사과',27,'여','재직','보직','대학교 졸업','기계공학과','대상아님','보훈','대상아님','대상안님',20210321),(3,'김철수','01098883344','직원','정직원','예산과',23,'남','재직','보직','대학교 졸업','컴퓨터 공학과','장애인','대상아님','대상아님','대상아님',20210213),(4,'김민지','01012383324','팀장','정직원','급여과',29,'여','재직','보직','대학교 졸업','컴퓨터 공학과','대상아님','대상아님','대상아님','대상아님',20171213),(127,'이어진','01044998126','직원','정직원','인사과',25,'남','휴직','보직','대학교졸업','전자공학과','해당없음','해당없음','해당없음','신규채용',20201123);
/*!40000 ALTER TABLE `employee1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laborcost`
--

DROP TABLE IF EXISTS `laborcost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `laborcost` (
  `laborcost_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `payment` varchar(15) NOT NULL,
  `date` timestamp NOT NULL,
  `isRemoved` int DEFAULT NULL,
  PRIMARY KEY (`laborcost_id`),
  UNIQUE KEY `laborcost_id_UNIQUE` (`laborcost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laborcost`
--

LOCK TABLES `laborcost` WRITE;
/*!40000 ALTER TABLE `laborcost` DISABLE KEYS */;
INSERT INTO `laborcost` VALUES (1,1,'5000','2021-06-20 17:09:11',0),(2,2,'4000','2021-06-20 17:09:21',0),(3,3,'3000','2021-06-20 17:09:27',0),(4,4,'5000','2021-06-20 17:09:33',0);
/*!40000 ALTER TABLE `laborcost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_of_absence`
--

DROP TABLE IF EXISTS `leave_of_absence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `leave_of_absence` (
  `leave_of_absence_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `position` varchar(10) NOT NULL,
  `department` varchar(20) NOT NULL,
  `leaveState` tinyint NOT NULL,
  `startPeriodDay` date NOT NULL,
  `endPeriodDay` date DEFAULT NULL,
  `leaveReason` varchar(1000) NOT NULL,
  `leaveSignificant` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`leave_of_absence_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_of_absence`
--

LOCK TABLES `leave_of_absence` WRITE;
/*!40000 ALTER TABLE `leave_of_absence` DISABLE KEYS */;
INSERT INTO `leave_of_absence` VALUES (1,'김현수','부장','인사과',0,'2020-03-07','2021-03-07','너무 힘들어서','신난당'),(2,'홍길동','대리','총무과',1,'2021-05-05',NULL,'배고파서','gd'),(3,'한마음','대리','인사과',0,'2020-01-01','2021-07-01','정말정말 일하기 싫어서','만년 대리 한마음'),(4,'잠온다','사원','영업팀',1,'2020-07-07',NULL,'울지마 바보야',''),(5,'종강줘','인턴','총무과',0,'2018-01-01','2018-08-07','나만 종강 젤 늦게해','종강일 : 6 / 25 '),(6,'끝내자','과장','영업팀',0,'2021-09-08','2021-10-10','언젠가 휴직할 예정','');
/*!40000 ALTER TABLE `leave_of_absence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participrate`
--

DROP TABLE IF EXISTS `participrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participrate` (
  `participRate_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `rate` int NOT NULL,
  `isRemoved` int DEFAULT NULL,
  PRIMARY KEY (`participRate_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participrate`
--

LOCK TABLES `participrate` WRITE;
/*!40000 ALTER TABLE `participrate` DISABLE KEYS */;
INSERT INTO `participrate` VALUES (6,2,30,NULL),(7,3,20,NULL),(8,4,10,NULL),(20,1,100,NULL);
/*!40000 ALTER TABLE `participrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payitem`
--

DROP TABLE IF EXISTS `payitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payitem` (
  `payItem_id` int NOT NULL AUTO_INCREMENT,
  `paymentOrDeduction` varchar(10) NOT NULL,
  `salaryBudgetCode` varchar(10) NOT NULL,
  `payItemCode` varchar(10) NOT NULL,
  `price` int NOT NULL,
  `itemDetails` varchar(50) NOT NULL,
  PRIMARY KEY (`payItem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payitem`
--

LOCK TABLES `payitem` WRITE;
/*!40000 ALTER TABLE `payitem` DISABLE KEYS */;
INSERT INTO `payitem` VALUES (1,'지급','DOA001','PAY001',190000,'워크숍사전답사'),(2,'공제','QOC001','EAO001',120000,'고용보험료'),(3,'지급','DOA002','PAY002',300000,'명절 상여금');
/*!40000 ALTER TABLE `payitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permission` (
  `business_number` int NOT NULL,
  `business_name` varchar(10) NOT NULL,
  PRIMARY KEY (`business_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'모니터교체'),(2,'무선마우스'),(3,'330 실습실 정비'),(4,'콘센트설치'),(5,'사물함 설치'),(6,'본체 교체');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnel_appointment`
--

DROP TABLE IF EXISTS `personnel_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `personnel_appointment` (
  `personnel_appointment_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `division` varchar(20) NOT NULL,
  `position` varchar(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `department` varchar(20) NOT NULL,
  `personnel_appointment` varchar(20) NOT NULL,
  `remarks` varchar(20) NOT NULL,
  PRIMARY KEY (`personnel_appointment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnel_appointment`
--

LOCK TABLES `personnel_appointment` WRITE;
/*!40000 ALTER TABLE `personnel_appointment` DISABLE KEYS */;
INSERT INTO `personnel_appointment` VALUES (1,1,'dasdasd','asdasd','aaa','aa','qq','ww'),(2,1,'dasdasd','asdasd','aaa','aa','qq','ww'),(3,1,'dasdasd','asdasd','aaa','aa','qq','ww');
/*!40000 ALTER TABLE `personnel_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salarymapping`
--

DROP TABLE IF EXISTS `salarymapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `salarymapping` (
  `mapping_id` int NOT NULL AUTO_INCREMENT,
  `participatingCode` varchar(10) NOT NULL,
  `salaryBudgetCode` varchar(10) NOT NULL,
  `payItemCode` varchar(10) NOT NULL,
  `paymentOrDeduction` varchar(10) NOT NULL,
  `price` int NOT NULL,
  `projectParticipatingRate` int NOT NULL,
  `payItem_id` int NOT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`mapping_id`,`payItem_id`,`employee_id`),
  KEY `fk_SalaryMapping_Info_PayItem1_idx` (`payItem_id`),
  KEY `fk_salarymapping_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_salarymapping_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `fk_SalaryMapping_Info_PayItem1` FOREIGN KEY (`payItem_id`) REFERENCES `payitem` (`payItem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salarymapping`
--

LOCK TABLES `salarymapping` WRITE;
/*!40000 ALTER TABLE `salarymapping` DISABLE KEYS */;
INSERT INTO `salarymapping` VALUES (2,'PART001','DOA001','PAY001','지급',190000,40,1,1),(4,'BASE001','QOC001','EAO001','공제',120000,100,2,1);
/*!40000 ALTER TABLE `salarymapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `working_hours`
--

DROP TABLE IF EXISTS `working_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `working_hours` (
  `childcare_apply_set_starting_date` date DEFAULT NULL,
  `childcare_apply_set_quitting_date` date DEFAULT NULL,
  `overtime_apply_set_starting_time` time DEFAULT NULL,
  `overtime_apply_set_quitting_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `working_hours`
--

LOCK TABLES `working_hours` WRITE;
/*!40000 ALTER TABLE `working_hours` DISABLE KEYS */;
INSERT INTO `working_hours` VALUES ('2021-06-02','2021-06-30','15:23:00','18:23:00');
/*!40000 ALTER TABLE `working_hours` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-25 10:57:18
