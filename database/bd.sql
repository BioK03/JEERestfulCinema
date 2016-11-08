-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mer 30 Novembre 2011 �  19:32
-- Version du serveur: 5.5.8
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

CREATE DATABASE IF NOT EXISTS `cinema_jee`;

use `cinema_jee`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: 'cinema'
--

-- --------------------------------------------------------

--
-- Structure de la table 'acteur'
--

CREATE TABLE IF NOT EXISTS actor (
  NoAct int(4) NOT NULL AUTO_INCREMENT,
  LastnameAct varchar(20) NOT NULL,
  FirstnameAct varchar(20) DEFAULT NULL,
  Birthdate date DEFAULT NULL,
  Deathdate date DEFAULT NULL,
  Picture varchar(250) DEFAULT NULL,
  PRIMARY KEY (NoAct)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'acteur'
--

INSERT INTO actor (LastnameAct, FirstnameAct, Birthdate, Deathdate, Picture) VALUES
('Reno', 'Jean', '1948-07-30', NULL, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Jean_Reno_Cannes_2016.jpg/422px-Jean_Reno_Cannes_2016.jpg"),
('Portman', 'Natalie', '1981-06-09', NULL, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Natalie_Portman_Cannes_2015_5.jpg/424px-Natalie_Portman_Cannes_2015_5.jpg"),
('Dujardin', 'Jean', '1972-06-19', NULL, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Jean_Dujardin_2016.jpg/220px-Jean_Dujardin_2016.jpg"),
('Bourvil', '', '1917-07-27', '1970-09-23', "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Bourvil2.jpg/220px-Bourvil2.jpg"),
('De Funes', 'Louis', '1914-07-31', '1983-01-27', "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Louis_de_funes_1978_ws_1-zoom.jpg/220px-Louis_de_funes_1978_ws_1-zoom.jpg"),
('Anglade', 'Jean-Hugues', '1955-07-29', NULL, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/84/Jean-Hugues_Anglade_C%C3%A9sars_2014_5.jpg/220px-Jean-Hugues_Anglade_C%C3%A9sars_2014_5.jpg"),
('Lambert', 'Christophe', '1957-03-29', NULL, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Christophe_Lambert_Cabourg_2012.jpg/220px-Christophe_Lambert_Cabourg_2012.jpg");

-- --------------------------------------------------------

--
-- Structure de la table 'categorie'
--

CREATE TABLE IF NOT EXISTS category (
  CatCode varchar(2) NOT NULL,
  Wording varchar(20) NOT NULL,
  Picture varchar(250) DEFAULT NULL,
  PRIMARY KEY (CatCode)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'categorie'
--

INSERT INTO category (CatCode, Wording, Picture) VALUES
('AC', 'Action', "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/M9-pistolet.jpg/220px-M9-pistolet.jpg"),
('CO', 'Comédie', "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Eduard_von_Gr%C3%BCtzner_Falstaff.jpg/220px-Eduard_von_Gr%C3%BCtzner_Falstaff.jpg"),
('PO', 'Policier', "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Police-IMG_4105.jpg/300px-Police-IMG_4105.jpg"),
('WE', 'Western', "https://upload.wikimedia.org/wikipedia/commons/5/53/Buffalo_Bills_Wild_West_Show%2C_1890.jpg");

-- --------------------------------------------------------

--
-- Structure de la table 'film'
--

CREATE TABLE IF NOT EXISTS movie (
  NoMovie int(4) NOT NULL AUTO_INCREMENT,
  Title varchar(30) NOT NULL,
  Duration int(3) NOT NULL,
  ReleaseDate date NOT NULL,
  Budget int(8) NOT NULL,
  Incomings int(8) NOT NULL,
  NoRea int(2) NOT NULL,
  CatCode varchar(2) NOT NULL,
  Picture varchar(250) DEFAULT NULL,
  AllocineLink varchar(250) DEFAULT NULL,
  PRIMARY KEY (NoMovie),
  KEY NoRea (NoRea),
  KEY CatCode (CatCode)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'film'
--

INSERT INTO movie (Title, Duration, ReleaseDate, Budget, Incomings, NoRea, CatCode, Picture, AllocineLink) VALUES
('Léon', 110, '1994-04-14', 17531000, 69250000, 3, 'PO', "http://fr.web.img3.acsta.net/c_215_290/pictures/14/08/21/14/15/233032.jpg", "http://www.allocine.fr/film/fichefilm_gen_cfilm=9684.html"),
('Cash', 100, '2008-04-23', 18340000, 60340000, 4, 'PO', "http://fr.web.img5.acsta.net/c_215_290/medias/nmedia/18/64/53/28/18943501.jpg", "http://www.allocine.fr/film/fichefilm_gen_cfilm=126887.html"),
('La grande vadrouille', 132, '1966-12-01', 7227000, 51258000, 2, 'AC', "http://fr.web.img6.acsta.net/c_215_290/pictures/16/06/16/12/01/072037.jpg", "http://www.allocine.fr/film/fichefilm_gen_cfilm=4307.html"),
('Subway', 104, '1985-04-10', 10567000, 70500000, 3, 'PO', "http://fr.web.img6.acsta.net/c_215_290/pictures/14/08/21/14/04/441036.jpg", "http://www.allocine.fr/film/fichefilm_gen_cfilm=306.html");

-- --------------------------------------------------------

--
-- Structure de la table 'personnage'
--

CREATE TABLE IF NOT EXISTS personage (
  NoMovie int(4) NOT NULL,
  NoAct int(4) NOT NULL,
  PersName varchar(30) NOT NULL,
  PRIMARY KEY (NoMovie,NoAct),
  KEY NoMovie (NoMovie),
  KEY NoAct (NoAct)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'personnage'
--

INSERT INTO personage (NoMovie, NoAct, PersName) VALUES
(1, 1, 'Léon'),
(1, 2, 'Mathilda'),
(2, 1, 'Maxime Dubreuil'),
(2, 3, 'Cash'),
(3, 4, 'Augustin Bouvet'),
(3, 5, 'Stanislas Lefort'),
(4, 1, 'Le Batteur'),
(4, 6, 'Le Roller'),
(4, 7, 'Fred');

-- --------------------------------------------------------

--
-- Structure de la table 'realisateur'
--

CREATE TABLE IF NOT EXISTS director (
  NoRea int(2) NOT NULL AUTO_INCREMENT,
  LastnameRea varchar(20) NOT NULL,
  FirstnameRea varchar(20) NOT NULL,
  Picture varchar(250) DEFAULT NULL,
  PRIMARY KEY (NoRea)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'realisateur'
--

INSERT INTO director (LastnameRea, FirstnameRea, Picture) VALUES
('Oury', 'Gérard', "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/G%C3%A9rard_Oury_Mich%C3%A8le_Morgan.jpg/220px-G%C3%A9rard_Oury_Mich%C3%A8le_Morgan.jpg"),
('Chabrol', 'Claude', "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Claude_Chabrol_%28Amiens_nov._2008%29_14b.jpg/220px-Claude_Chabrol_%28Amiens_nov._2008%29_14b.jpg"),
('Besson', 'Luc', "https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/Luc_Besson_Cannes_2016.jpg/220px-Luc_Besson_Cannes_2016.jpg"),
('Besnard', 'Eric', "http://fr.web.img2.acsta.net/cx_160_213/b_1_d6d6d6/medias/nmedia/18/66/30/25/19459675.jpg");

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `film`
--
ALTER TABLE `movie`
  ADD CONSTRAINT movie_ibfk_1 FOREIGN KEY (NoRea) REFERENCES director (NoRea),
  ADD CONSTRAINT movie_ibfk_2 FOREIGN KEY (CatCode) REFERENCES category (CatCode);

--
-- Contraintes pour la table `personnage`
--
ALTER TABLE `personage`
  ADD CONSTRAINT personage_ibfk_1 FOREIGN KEY (NoMovie) REFERENCES movie (NoMovie),
  ADD CONSTRAINT personage_ibfk_2 FOREIGN KEY (NoAct) REFERENCES actor (NoAct);