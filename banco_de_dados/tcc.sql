-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tcc
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `alunos`
--

DROP TABLE IF EXISTS `alunos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alunos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `data_nascimento` date NOT NULL,
  `ra` varchar(15) NOT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `rg` varchar(11) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `tel1` char(11) NOT NULL,
  `tel2` char(11) DEFAULT NULL,
  `nome_resp` varchar(150) NOT NULL,
  `cel_pai` char(11) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `biometria` longtext,
  `turma` int(11) NOT NULL,
  `curso` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `turma_idx` (`turma`),
  KEY `id _idx` (`turma`),
  KEY `curso_idx` (`curso`),
  CONSTRAINT `curso` FOREIGN KEY (`curso`) REFERENCES `curso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `turma` FOREIGN KEY (`turma`) REFERENCES `turma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunos`
--

LOCK TABLES `alunos` WRITE;
/*!40000 ALTER TABLE `alunos` DISABLE KEYS */;
/*!40000 ALTER TABLE `alunos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_curso` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diario`
--

DROP TABLE IF EXISTS `diario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_aluno` varchar(255) DEFAULT NULL,
  `A1` char(1) DEFAULT NULL,
  `A2` char(1) DEFAULT NULL,
  `A3` char(1) DEFAULT NULL,
  `A4` char(1) DEFAULT NULL,
  `A5` char(1) DEFAULT NULL,
  `A6` char(1) DEFAULT NULL,
  `A7` char(1) DEFAULT NULL,
  `A8` char(1) DEFAULT NULL,
  `A9` char(1) DEFAULT NULL,
  `A10` char(1) DEFAULT NULL,
  `A11` char(1) DEFAULT NULL,
  `A12` char(1) DEFAULT NULL,
  `A13` char(1) DEFAULT NULL,
  `A14` char(1) DEFAULT NULL,
  `A15` char(1) DEFAULT NULL,
  `A16` char(1) DEFAULT NULL,
  `A17` char(1) DEFAULT NULL,
  `A18` char(1) DEFAULT NULL,
  `A19` char(1) DEFAULT NULL,
  `A20` char(1) DEFAULT NULL,
  `A21` char(1) DEFAULT NULL,
  `A22` char(1) DEFAULT NULL,
  `A23` char(1) DEFAULT NULL,
  `A24` char(1) DEFAULT NULL,
  `A25` char(1) DEFAULT NULL,
  `A26` char(1) DEFAULT NULL,
  `A27` char(1) DEFAULT NULL,
  `A28` char(1) DEFAULT NULL,
  `A29` char(1) DEFAULT NULL,
  `A30` char(1) DEFAULT NULL,
  `A31` char(1) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `ano` int(11) DEFAULT NULL,
  `id_turma` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diario`
--

LOCK TABLES `diario` WRITE;
/*!40000 ALTER TABLE `diario` DISABLE KEYS */;
/*!40000 ALTER TABLE `diario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequencia`
--

DROP TABLE IF EXISTS `frequencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_aluno` int(11) NOT NULL,
  `data` varchar(10) NOT NULL,
  `id_materia` int(11) DEFAULT NULL,
  `aula` int(11) DEFAULT NULL,
  `falta` char(1) DEFAULT NULL,
  `presenca` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id_aluno`),
  KEY `id_materia_idx` (`id_materia`),
  KEY `id_aluno_idx` (`id_aluno`),
  CONSTRAINT `id_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `alunos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequencia`
--

LOCK TABLES `frequencia` WRITE;
/*!40000 ALTER TABLE `frequencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `nome_prof` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semana`
--

DROP TABLE IF EXISTS `semana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semana` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_turma` int(11) NOT NULL,
  `aula` int(11) NOT NULL,
  `segunda` int(11) NOT NULL,
  `terca` int(11) NOT NULL,
  `quarta` int(11) NOT NULL,
  `quinta` int(11) NOT NULL,
  `sexta` int(11) NOT NULL,
  `sabado` int(11) DEFAULT NULL,
  `diasSemana` int(11) NOT NULL,
  `numAulas` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id_turma`),
  CONSTRAINT `id_turma` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semana`
--

LOCK TABLES `semana` WRITE;
/*!40000 ALTER TABLE `semana` DISABLE KEYS */;
/*!40000 ALTER TABLE `semana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie` varchar(50) NOT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `max_alunos` int(11) NOT NULL,
  `periodo` varchar(50) NOT NULL,
  `primeiroDiaAula` date NOT NULL,
  `ultimoDiaAula` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_curso_idx` (`id_curso`),
  KEY `id_curso_idx1` (`id_curso`),
  KEY `id_curso_idx2` (`id_curso`),
  CONSTRAINT `id_curso` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-30 20:52:17
