-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: delivery
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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `fno` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `path` text,
  PRIMARY KEY (`fno`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'가오리찜','rayjjim.jpg'),(2,'간장게장','Ganjanggejang.jpg'),(3,'간장치킨','Ganjangchicken.jpg'),(4,'간짜장','ganzhajiang.jpg'),(5,'갈비찜','galbijjim.jpg'),(6,'감바스','gambas.jpg'),(7,'감자탕','gamjatang.jpg'),(8,'고구마돈까스','gogumatonkasu.jpg'),(9,'고구마피자','gogumapizza.jpg'),(10,'고추바사삭','gochubasasak.jpg'),(11,'고추잡채','gochujapchae.jpg'),(12,'곱창','gopchang.jpg'),(13,'김밥','Gimbap.jpg'),(14,'김치볶음밥','kimchibokkeumbap.jpg'),(15,'김치찌개','kimchistew.jpg'),(16,'김치찜','kimchijjim.jpg'),(17,'깐쇼새우','kkanshoShrimp.jpg'),(18,'깐풍기','kkanpunggi.jpg'),(19,'꿔바로우','guobaorou.jpg'),(20,'낙곱새','nakgopsae.jpg'),(21,'닭갈비','dakgalbi.jpg'),(22,'돈까스','tonkasu.jpg'),(23,'돼지국밥','porkgukbap.jpg'),(24,'떡볶이','Tteokbokki.jpg'),(25,'라떼','Latte.jpg'),(26,'라멘','Ramen.jpg'),(27,'레드콤보','Red_Combo.jpg'),(28,'로제떡볶이','Rose_Tteokbokki.jpg'),(29,'로제파스타','Rose_Pasta.jpg'),(30,'롯데리아','Lotteria.jpg'),(31,'마라탕','Marathon.jpg'),(32,'마카롱','Macaroon.jpg'),(33,'마파두부','Mapo_Tofu.jpg'),(34,'만두','Dumpling.jpg'),(35,'만둣국','Dumpling_Soup.jpg'),(36,'매운갈비찜','Spicy_Galbi_Stew.jpg'),(37,'매운돈까스','Spicy_Pork_Cutlets.png'),(38,'매운족발','Spicy_Pork_Feet.jpg'),(39,'맥도날드','McDonalds.png'),(40,'핫도그','Hot_Dog.jpg'),(41,'물냉면','Mulnaengmyeon.jpg'),(42,'배떡','Baedduck_Tteokbokki.jpg'),(43,'버거킹','BurgerKing.jpg'),(44,'보쌈','Bossam.jpg'),(45,'볶음밥','Fried_Rice.jpg'),(46,'밥버거','Rice_Bugger.jpg'),(47,'불고기피자','Bulgogi_Pizza.jpg'),(48,'불닭치킨','Buldak_Chicken.png'),(49,'브라우니','Brownie.jpg'),(50,'블랙알리오','BlackAlioOlio_Chicken.jpg'),(51,'비빔냉면','Bibim_Naengmyeon.jpg'),(52,'빙수','Shaved_Ice.jpg'),(53,'빠네','Penet_Pasta.jpg'),(54,'뿌링클','Purrinkle.png'),(55,'삼겹살','Pork_Belly.jpg'),(56,'샤브샤브','Shabu.jpg'),(57,'서브웨이','Subway.png'),(58,'석쇠구이','Grilled.jpg'),(59,'소바','Soba.png'),(60,'순대','Sundae.jpg'),(61,'순대국밥','Sundae_Gukbap.jpg'),(62,'쉑쉑','ShackShack_Burger.jpg'),(63,'쉬림프피자','Shrimp_Pizza.png'),(64,'스무디','Smoothie.jpg'),(65,'스테이크','steak.jpg'),(66,'스테이크피자','Steak_Pizza.jpg'),(67,'신전','Sinjeon_Tteokbokki.jpg'),(68,'싸이버거','Thigh_Burger.jpg'),(69,'쌀국수','Rice_Noodles.jpg'),(70,'아귀찜','Agu_Steamed.jpg'),(71,'아메리카노','Americano.jpg'),(72,'아이스크림','Ice_Cream.jpg'),(73,'알리오올리오','AlioOlio_Pasta.jpg'),(74,'양념게장','Seasoned_Crab.jpg'),(75,'양념치킨','Seasoned_Chicken.jpg'),(76,'양장피','yangjangpi.jpg'),(77,'엽떡','Yupdduck_Tteokbokki.png'),(78,'오뎅','Fish_Cake.jpg'),(79,'와플','Waffle.jpg'),(80,'우동','Udon.png'),(81,'유산슬','Yoosanseul.jpg'),(82,'육회','Raw_meat.jpg'),(83,'토스트','Toast.jpg'),(84,'잡채','Japchae.jpg'),(85,'잡채밥','Japchae_Rice.jpg'),(86,'족발','Pork_Feet.jpg'),(87,'지코바','Zikova.jpg'),(88,'짜장면','Jajangmyeon.jpg'),(89,'짬뽕','Champon.jpg'),(90,'쫄면','jjolmyeon.jpg'),(91,'주꾸미','Webfoot_Octopus.jpg'),(92,'찜닭','Steamed_Chicken.jpg'),(93,'차돌박이짬뽕','Marbled_Champon.jpg'),(94,'초밥','Sushi.jpg'),(95,'치즈돈까스','Cheese_Pork_Cutlets.jpg'),(96,'치즈피자','Cheese_Pizza.jpg'),(97,'카레','Curry.png'),(98,'케이크','Cake.jpg'),(99,'콤비네이션피자','Combination_Pizza.jpg'),(100,'크로플','Cropple.jpg'),(101,'크림파스타','Creamy_Pasta.jpg'),(102,'타르트','Tart.jpg'),(103,'타코','Tacos.png'),(104,'타코야키','Takoyaki.jpg'),(105,'탕수육','Sweet_sour_pork.jpg'),(106,'토마토파스타','Tomato_Pasta.jpg'),(107,'튀김','Fried_Food.jpg'),(108,'파닭','Sliced_Leek_Chicken.jpg'),(109,'팔보채','Palbochae.jpg'),(110,'페퍼로니피자','Pepperoni_Pizza.jpg'),(111,'포테이토피자','Potato_Pizza.jpg'),(112,'하와이안피자','Hawaiian_Pizza.jpg'),(113,'해물찜','Seafood_Stew.jpg'),(114,'해물탕','Seafood_Soup.jpg'),(115,'허니브레드','Honey_Bread.jpg'),(116,'허니콤보','Honey_Combo.jpg'),(117,'황금올리브','Golden_Olive.jpg'),(118,'회','Sashimi.jpg'),(119,'후라이드치킨','Fried_Chicken.jpg'),(120,'KFC','KFC.png');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-22 11:30:48
