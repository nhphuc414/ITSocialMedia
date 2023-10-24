CREATE DATABASE  IF NOT EXISTS `itsocial` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_vi_0900_as_cs */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `itsocial`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: itsocial
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'helue',NULL,'2023-10-13 16:22:47',NULL,'PUBLIC',8,14,NULL),(2,'haha',NULL,'2023-10-14 07:16:17',NULL,'PUBLIC',8,17,NULL),(3,'hehe',NULL,'2023-10-14 07:16:17',NULL,'PUBLIC',8,17,NULL),(4,'hoho',NULL,'2023-10-14 07:16:17',NULL,'PUBLIC',5,17,NULL),(5,'hihi',NULL,'2023-10-14 07:16:17',NULL,'PUBLIC',3,17,NULL),(6,'test',NULL,'2023-10-14 16:08:19',NULL,'PUBLIC',8,21,NULL),(7,'haha',NULL,'2023-10-14 16:08:32',NULL,'PUBLIC',8,21,NULL),(8,'hoho',NULL,'2023-10-14 16:08:43',NULL,'PUBLIC',8,21,NULL),(9,'good',NULL,'2023-10-14 20:07:31',NULL,'PUBLIC',8,1,NULL),(10,'abc',NULL,'2023-10-15 03:09:04',NULL,'PUBLIC',8,13,NULL),(11,'cde',NULL,'2023-10-15 03:09:21',NULL,'PUBLIC',8,13,NULL),(12,'alo',NULL,'2023-10-15 05:41:16',NULL,'PUBLIC',8,21,NULL),(13,'good',NULL,'2023-10-15 21:02:55',NULL,'PUBLIC',8,17,NULL),(14,'good',NULL,'2023-10-15 21:03:13',NULL,'PUBLIC',8,23,NULL),(15,'helu',NULL,'2023-10-24 17:47:30',NULL,'PUBLIC',19,26,NULL),(16,'halo',NULL,'2023-10-24 17:47:34',NULL,'PUBLIC',19,26,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `community`
--

LOCK TABLES `community` WRITE;
/*!40000 ALTER TABLE `community` DISABLE KEYS */;
/*!40000 ALTER TABLE `community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `community_user`
--

LOCK TABLES `community_user` WRITE;
/*!40000 ALTER TABLE `community_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `community_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification_community`
--

LOCK TABLES `notification_community` WRITE;
/*!40000 ALTER TABLE `notification_community` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification_user`
--

LOCK TABLES `notification_user` WRITE;
/*!40000 ALTER TABLE `notification_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'helu',NULL,'2023-10-13 08:45:26',NULL,'PUBLIC',1,NULL),(3,'helu',NULL,'2023-10-13 08:48:06',NULL,'PUBLIC',8,NULL),(4,'helu',NULL,'2023-10-13 08:51:29',NULL,'PUBLIC',8,NULL),(5,'helu',NULL,'2023-10-13 08:53:07',NULL,'PUBLIC',8,NULL),(6,'helu',NULL,'2023-10-13 08:56:38',NULL,'PUBLIC',8,NULL),(7,'helu',NULL,'2023-10-13 08:58:41',NULL,'PUBLIC',8,NULL),(8,'helu',NULL,'2023-10-13 08:58:48',NULL,'PUBLIC',8,NULL),(9,'helu',NULL,'2023-10-13 09:00:11',NULL,'PUBLIC',8,NULL),(10,'helu',NULL,'2023-10-13 09:01:02','2023-10-15 00:27:44','PUBLIC',3,NULL),(11,'helu',NULL,'2023-10-13 09:31:24','2023-10-15 00:27:44','PUBLIC',5,NULL),(12,'helu',NULL,'2023-10-13 09:34:05','2023-10-15 00:27:44','PUBLIC',6,NULL),(13,'HELOOO',NULL,'2023-10-13 09:37:43','2023-10-15 00:28:43','READONLY',16,NULL),(14,'helu',NULL,'2023-10-13 09:37:50',NULL,'PUBLIC',8,NULL),(17,'Aloooo','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697166673/xd983xp0ddikb4v3a8fg.jpg','2023-10-13 10:11:13','2023-10-14 07:15:32','PUBLIC',8,NULL),(21,'123','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697273301/iqfkml9bqjgiqti04txg.png','2023-10-14 15:48:21',NULL,'PUBLIC',8,NULL),(22,'yolo',NULL,'2023-10-15 05:42:27',NULL,'PUBLIC',8,NULL),(23,'a','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697323364/frvfec0u9dgusest1taw.png','2023-10-15 05:42:44',NULL,'PUBLIC',8,NULL),(25,'yolo',NULL,'2023-10-15 05:44:01',NULL,'PUBLIC',18,NULL),(26,'My Diagram','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697378407/dozxuejdg5nzboo4ojem.png','2023-10-15 21:00:07',NULL,'PUBLIC',8,NULL),(27,'This is my Project','https://res.cloudinary.com/dm5nn54wh/image/upload/v1698144507/pjdoz61xqv2nmyzu89f4.jpg','2023-10-24 17:48:26',NULL,'PUBLIC',19,NULL),(28,'abc',NULL,'2023-10-24 18:09:42',NULL,'PUBLIC',19,NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reaction`
--

LOCK TABLES `reaction` WRITE;
/*!40000 ALTER TABLE `reaction` DISABLE KEYS */;
INSERT INTO `reaction` VALUES (56,'LIKE','2023-10-14 19:54:05',8,NULL,17),(58,'LIKE','2023-10-14 20:07:27',8,NULL,1),(60,'LIKE','2023-10-14 23:29:32',8,NULL,21),(66,'LIKE','2023-10-15 03:23:04',18,NULL,13),(67,'LIKE','2023-10-15 05:41:17',8,NULL,21),(68,'LIKE','2023-10-15 05:41:20',8,NULL,17),(69,'LIKE','2023-10-15 05:41:23',8,NULL,14),(70,'LIKE','2023-10-15 05:42:53',8,NULL,21),(71,'LIKE','2023-10-15 05:42:55',8,NULL,17),(72,'LIKE','2023-10-15 05:42:56',8,NULL,14),(73,'LIKE','2023-10-15 05:42:57',8,NULL,14),(74,'LIKE','2023-10-15 05:42:58',8,NULL,14),(75,'LIKE','2023-10-15 05:42:58',8,NULL,14),(76,'LIKE','2023-10-15 05:42:59',8,NULL,14),(77,'LIKE','2023-10-15 05:42:59',8,NULL,14),(78,'LIKE','2023-10-15 05:43:35',8,NULL,23),(84,'LIKE','2023-10-15 06:16:41',18,NULL,23),(92,'LIKE','2023-10-24 17:47:37',19,NULL,26);
/*!40000 ALTER TABLE `reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag_post`
--

LOCK TABLES `tag_post` WRITE;
/*!40000 ALTER TABLE `tag_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hello','usn','$2a$10$z0yinHpHnh7RiRkH3pAqYuNAQCleqZ2WhrYoXal2VYdhgC7TTG6UG','nhphuc414@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697113853/fuynunzh804q1s2fqy15.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697113854/lzvub5tawlg4czvzojdt.jpg','2023-10-12 19:46:17','2023-10-17 06:27:14','USER'),(3,'hello','usn1','$2a$10$gGDCFm7KRloKTQ1nuIZFIOEmKz3.BBDOt/cm5fdA3Uz50gGue5piO','nhphuc415@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697114340/cszmmlevzlxgofsjadf2.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697114341/rvoxzmmnt20tjh5nvtjr.jpg','2023-10-12 19:46:17','2023-10-17 06:27:14','USER'),(5,'hello','usn2','$2a$10$/lA5LgSPlmBBUfNW3wYKuOoE9eize2AilntAgk0Gux4v7exGJak9a','nhphuc416@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697114773/ovabl4ukytumen5mijic.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697114777/xd4uloboyewmfdbe22h0.jpg','2023-10-12 19:46:17',NULL,'USER'),(6,'hello','usn3','$2a$10$as705/KBT3AJ4IaIaAsEI.aGwod/V2o31WS0bqk5JwWMmSsyejoQS','nhphuc417@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697115188/uztl1f7ofm3vmfomywgy.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697115190/fiz16qenimbho8ynahtk.jpg','2023-10-12 19:53:10',NULL,'USER'),(8,'Test API','usn4','$2a$10$hDgHcB2dymm/KdW4AlZ1juVaPQQYAfeG9spsBXmVxuY4.AYtuebSS','nhphuc418@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697115473/qfqffdlzxw1gzgvmlyns.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697115475/d2im1sjxoufoflkphu4p.jpg','2023-10-12 19:57:55','2023-10-16 03:25:24','ADMIN'),(11,'ABCD','abcdef','$2a$10$dTZUQv3dE2ap8Wlw4N.DUOFLgOt9NXlT7w/E.eAkQ4KGUlCIAoRci','admin76@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697130605/i6l50xglb3t3epqba431.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697130606/u9fgpu6ladnvszandfsh.jpg','2023-10-13 00:10:06',NULL,'USER'),(16,'ABCD','abcdeff','$2a$10$IelcTKntAWaPnYoqC07HHuE7R13nRgRWZqcnYsNdwUsv.y811VXR2','admin7@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697161732/uocwcutgljnvizslnhdg.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697161733/rhnbi5ghky7nulmdqv1o.jpg','2023-10-13 08:48:53',NULL,'USER'),(17,'Test','usn5','$2a$10$4xEsJsFTl7S896hJ1XsDm.eOMHrQjMQUhndhKacWa7W1DiXvKiwAO','nhphuc413@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202418/qkhz0wz7getw9vqvex2w.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202421/rveknrmarmysphqdeahm.jpg','2023-10-13 20:07:03',NULL,'USER'),(18,'nhphuc','jokers1404','$2a$10$QZsh/lg9itB7uNM8b5HEDuop.TvabiJYIwMUV5SbbdepokLzL/fq2','2051052103phuc@ou.edu.vn','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697235407/smunek3ifvecz5vfty0n.png','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697235409/uvfes215fndrxaay0cto.png','2023-10-14 05:16:50',NULL,'USER'),(19,'123','cobrac','$2a$10$3rcZQmI.RjZhT89l7zldseU86UZOdmVuP5p5FO8qnipaVQRBJa7au','jokers1404@gmail.com','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697243818/bhnuz9otrh4r2reiwma5.png','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697243820/gl0jhbgsd6zfj9twjnei.png','2023-10-14 07:37:00',NULL,'USER'),(24,'nhphuc','dragonfly','$2a$10$6zmw.blpjh74EQnMwYiT/.xVPM.62747398Wnn93sMUJi7d5MZn36','20510521033phuc@ou.edu.vn','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202418/qkhz0wz7getw9vqvex2w.jpg','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202421/rveknrmarmysphqdeahm.jpg','2023-10-16 20:44:54',NULL,'USER'),(25,'RvfwvDbwfv','buffnhuma17@gmail.com','$2a$10$1p16ADWkA5/xfaQEzJ4kje7VMO/CtJL32VJg5lMwvqtg2SVhNQYjK','buffnhuma17@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocL6fHVyJqK5WkrIUW_BZxJw1xhHlI3geEp2mYgW73Gc=s96-c','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202421/rveknrmarmysphqdeahm.jpg','2023-10-16 21:01:16',NULL,'USER'),(26,'Nguyễn HồngPhúc','1404.nhphuc@gmail.com','$2a$10$Kdr9QC8Zc5nEocGhbhvfGOYOH9SAFEgZK2jQvaXrqUW3jkgDRQfRO','1404.nhphuc@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocJS9ndqEz3KXWUpw1RAwTUqn2CQLwBNdf2F00Zt-IZx=s96-c','https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202421/rveknrmarmysphqdeahm.jpg','2023-10-17 02:46:22',NULL,'USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-24 18:21:28
