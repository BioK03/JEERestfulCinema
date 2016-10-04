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
  NoAct int(4) NOT NULL,
  LastnameAct varchar(20) NOT NULL,
  FirstnameAct varchar(20) DEFAULT NULL,
  Birthdate date DEFAULT NULL,
  Deathdate date DEFAULT NULL,
  Picture varchar(50) DEFAULT NULL,
  PRIMARY KEY (NoAct)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'acteur'
--

INSERT INTO actor (NoAct, LastnameAct, FirstnameAct, Birthdate, Deathdate, Picture) VALUES
(1, 'Reno', 'Jean', '1948-07-30', NULL, NULL),
(5, 'Portman', 'Natalie', '1981-06-09', NULL, NULL),
(7, 'Dujardin', 'Jean', '1972-06-19', NULL, NULL),
(8, 'Bourvil', '', '1917-07-27', '1970-09-23', NULL),
(12, 'De Funes', 'Louis', '1914-07-31', '1983-01-27', NULL),
(13, 'Anglade', 'Jean-Hugues', '1955-07-29', NULL, NULL),
(15, 'Lambert', 'Christophe', '1957-03-29', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table 'categorie'
--

CREATE TABLE IF NOT EXISTS category (
  CatCode varchar(2) NOT NULL,
  Wording varchar(20) NOT NULL,
  Picture varchar(50) DEFAULT NULL,
  PRIMARY KEY (CatCode)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'categorie'
--

INSERT INTO category (CatCode, Wording, Picture) VALUES
('AC', 'Action', NULL),
('CO', 'Comédie', NULL),
('PO', 'Policier', NULL),
('WE', 'Western', NULL);

-- --------------------------------------------------------

--
-- Structure de la table 'film'
--

CREATE TABLE IF NOT EXISTS movie (
  NoMovie int(4) NOT NULL,
  Title varchar(30) NOT NULL,
  Duration int(3) NOT NULL,
  ReleaseDate date NOT NULL,
  Budget int(8) NOT NULL,
  Incomings int(8) NOT NULL,
  NoRea int(2) NOT NULL,
  CatCode varchar(2) NOT NULL,
  Picture varchar(50) DEFAULT NULL,
  AllocineLink varchar(150) DEFAULT NULL,
  PRIMARY KEY (NoMovie),
  KEY NoRea (NoRea),
  KEY CatCode (CatCode)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'film'
--

INSERT INTO movie (NoMovie, Title, Duration, ReleaseDate, Budget, Incomings, NoRea, CatCode, Picture, AllocineLink) VALUES
(1, 'Léon', 110, '1994-04-14', 17531000, 69250000, 3, 'PO', NULL, NULL),
(2, 'Cash', 100, '2008-04-23', 18340000, 60340000, 4, 'PO', NULL, NULL),
(3, 'La grande vadrouille', 132, '1966-12-01', 7227000, 51258000, 2, 'AC', NULL, NULL),
(4, 'Subway', 104, '1985-04-10', 10567000, 70500000, 3, 'PO', NULL, NULL);

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
(1, 5, 'Mathilda'),
(2, 1, 'Maxime Dubreuil'),
(2, 7, 'Cash'),
(3, 8, 'Augustin Bouvet'),
(3, 12, 'Stanislas Lefort'),
(4, 1, 'Le Batteur'),
(4, 13, 'Le Roller'),
(4, 15, 'Fred');

-- --------------------------------------------------------

--
-- Structure de la table 'realisateur'
--

CREATE TABLE IF NOT EXISTS director (
  NoRea int(2) NOT NULL,
  LastnameRea varchar(20) NOT NULL,
  FirstnameRea varchar(20) NOT NULL,
  Picture varchar(50) DEFAULT NULL,
  PRIMARY KEY (NoRea)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'realisateur'
--

INSERT INTO director (NoRea, LastnameRea, FirstnameRea, Picture) VALUES
(1, 'Oury', 'Gérard', NULL),
(2, 'Chabrol', 'Claude', NULL),
(3, 'Besson', 'Luc', NULL),
(4, 'Besnard', 'Eric', NULL);

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