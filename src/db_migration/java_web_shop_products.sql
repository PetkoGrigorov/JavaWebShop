CREATE DATABASE  IF NOT EXISTS `java_web_shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `java_web_shop`;
-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: java_web_shop
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'dog','human best friend',2),(2,'cat','mouse eating animal',1),(3,'spider','eight legs insect',1),(4,'horse','working mashine',12),(5,'camel','desert ship',25),(6,'seal','AFEGGEGV E EF',11),(7,'fox','r r r r r r dryj',17),(8,'rabbit','asetrunnwvujm',8),(9,'turtle','arhtag vshc5h wtsyjny',5),(10,'horse2','working mashine',12),(11,'camel2','desert ship',25),(12,'seal2','AFEGGEGV E EF',11),(13,'fox2','r r r r r r dryj',17),(14,'rabbit2','asetrunnwvujm',8),(15,'turtle2','arhtag vshc5h wtsyjny',5),(16,'horse3','working mashine',12),(17,'camel3','desert ship',25),(18,'seal3','AFEGGEGV E EF',11),(19,'fox3','r r r r r r dryj',17),(20,'rabbit3','asetrunnwvujm',8),(21,'turtle3','arhtag vshc5h wtsyjny',5),(22,'horse4','working mashine',12),(23,'camel4','desert ship',25),(24,'seal4','AFEGGEGV E EF',11),(25,'fox4','r r r r r r dryj',17),(26,'rabbit4','asetrunnwvujm',8),(27,'turtle4','arhtag vshc5h wtsyjny',5),(28,'horse5','working mashine',12),(29,'camel5','desert ship',25),(30,'seal5','AFEGGEGV E EF',11),(31,'fox5','r r r r r r dryj',17),(32,'rabbit5','asetrunnwvujm',8),(33,'turtle5','arhtag vshc5h wtsyjny',5);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-20 19:13:35
