-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Client :  h2mysql24
-- Généré le :  Ven 08 Décembre 2017 à 06:19
-- Version du serveur :  5.6.35-log
-- Version de PHP :  7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pjkw_gpsam`
--

-- --------------------------------------------------------

--
-- Structure de la table `InfoRoute`
--

CREATE TABLE `InfoRoute` (
  `id` int(10) UNSIGNED NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  `type` varchar(50) NOT NULL,
  `dateCreation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Sam`
--

CREATE TABLE `Sam` (
  `id` int(10) UNSIGNED NOT NULL,
  `horaireDepart` date NOT NULL,
  `longitudeDepart` float NOT NULL,
  `latitudeDepart` float NOT NULL,
  `longitudeArrivee` float NOT NULL,
  `latitudeArrivee` float NOT NULL,
  `nbPlace` smallint(6) NOT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `TypeInfoRoute`
--

CREATE TABLE `TypeInfoRoute` (
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userMail` varchar(64) NOT NULL,
  `userLName` varchar(64) DEFAULT NULL,
  `userFName` varchar(64) DEFAULT NULL,
  `userPassword` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `InfoRoute`
--
ALTER TABLE `InfoRoute`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type` (`type`);

--
-- Index pour la table `Sam`
--
ALTER TABLE `Sam`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `TypeInfoRoute`
--
ALTER TABLE `TypeInfoRoute`
  ADD PRIMARY KEY (`type`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `InfoRoute`
--
ALTER TABLE `InfoRoute`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `Sam`
--
ALTER TABLE `Sam`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `InfoRoute`
--
ALTER TABLE `InfoRoute`
  ADD CONSTRAINT `InfoRoute_ibfk_1` FOREIGN KEY (`type`) REFERENCES `TypeInfoRoute` (`type`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
