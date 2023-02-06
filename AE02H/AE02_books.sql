-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 27, 2022 at 10:42 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6
CREATE DATABASE dam2 CHARACTER SET UTF8;

USE dam2;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `books`
--
SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_NAME = "authors";

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `birth` int(4) NOT NULL,
  `biography` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`id`, `name`, `birth`, `biography`) VALUES
(1, 'J.R.R. Tolkien', 1890, 'John Ronald Reuel Tolkien, a menudo citado como J. R. R. Tolkien o JRRT, fue un escritor, poeta, filólogo, lingüista y profesor universitario británico, conocido principalmente por ser el autor de las novelas clásicas de fantasía heroica El hobbit, El Silmarillion y El Señor de los Anillos.'),
(2, 'Orson Scott Card', 1951, 'Orson Scott Card es un escritor estadounidense. Escribe ciencia ficción y otros géneros literarios. Su obra más conocida es El juego de Ender. Nacido en Richland, Washington, Card creció en California, Arizona y Utah.'),
(3, 'Anónimo', 0, 'Desconocido'),
(4, 'John Steinbeck', 1902, 'John Ernst Steinbeck, Jr. ​ fue un escritor estadounidense ganador del Premio Nobel de Literatura y autor de conocidas novelas como De ratones y hombres, Las uvas de la ira, La perla y Al este del Edén.'),
(5, 'Alan Moore', 1953, 'Alan Moore es un escritor británico y guionista de cómics; en esa última labor es en la que ha destacado por sus trabajos reconocidos por la crítica y popularmente aclamados como Watchmen, V de Vendetta, From Hell y The League of Extraordinary Gentlemen y la Cosa del Pantano.'),
(6, 'Tom Wolfe', 1930, 'Thomas Clayton Wolfe fue un escritor estadounidense del siglo XX. Escribió cuatro novelas largas, muchos cuentos, poesía, obras dramáticas y fragmentos de novelas. Su prosa destila poesía, es muy descriptiva, con argumentos en gran parte basados en su vida.'),
(7, 'Camilo José Cela', 1916, 'Camilo José Cela y Trulock fue un escritor español. Autor prolífico y representante de la literatura de posguerra, ejerció como novelista, periodista, ensayista, editor de revistas literarias y conferenciante.'),
(8, 'William Golding', 1911, 'William Gerald Golding fue un novelista y poeta británico, galardonado con el premio Nobel de literatura en 1983, conocido especialmente por su obra El señor de las moscas.'),
(9, 'Eduardo Mendoza', 1943, 'Eduardo Mendoza Garriga es un escritor español.​ Su estilo narrativo es sencillo y directo, sin hacer abandono del uso de cultismos, arcaísmos así como del lenguaje popular en su más pura expresión.'),
(10, 'José Saramago', 1922, 'José de Sousa Saramago fue un escritor portugués. En 1998 se le otorgó el Premio Nobel de Literatura. La Academia Sueca destacó su capacidad para «volver comprensible una realidad huidiza, con parábolas sostenidas por la imaginación, la compasión y la ironía».'),
(11, 'Paco Roca', 1969, 'Francisco Martínez Roca, conocido como Paco Roca, ​ es un historietista español, adscrito al movimiento de la novela gráfica, que produce para el mercado global.​ Además de crear cómics, se dedica a la ilustración publicitaria.'),
(12, 'Giles Tremlett', 1962, 'Giles E.H. Tremlett es historiador, autor y periodista afincado en Madrid, España. Tremlett es miembro visitante del Centro Cañada Blanch de la London School of Economics y es autor de cuatro obras de historia y no ficción que se han traducido a media docena de idiomas.'),
(13, 'Arturo Pérez Reverte', 1951, 'Arturo Pérez-Reverte Gutiérrez es un escritor y periodista español, miembro de la Real Academia Española desde 2003. Antiguo corresponsal de RTVE y reportero destacado en diversos conflictos armados y guerras, es el autor, entre otras, de la saga Las aventuras del capitán Alatriste y la trilogía de Falcó.'),
(14, 'Frank Herbert', 1920, 'Franklin Patrick Herbert Jr.​, más conocido como Frank Herbert, fue un escritor estadounidense de ciencia ficción, famoso por la novela de 1965 Dune y sus cinco secuelas.');

-- --------------------------------------------------------

--
-- Table structure for table `editorials`
--

CREATE TABLE `editorials` (
  `id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `web` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `editorials`
--

INSERT INTO `editorials` (`id`, `name`, `web`) VALUES
(1, 'Minotauro', 'https://www.planetadelibros.com/editorial/minotauro/21'),
(2, 'Ediciones B', 'https://www.penguinlibros.com/es/11379-ediciones-b'),
(3, 'Anaya', 'https://www.grupoanaya.es\r\n'),
(4, 'Alianza', 'https://www.alianzaeditorial.es/'),
(5, 'ECC', 'https://www.ecccomics.com/'),
(6, 'Anagrama', 'https://www.anagrama-ed.es/'),
(7, 'Destino', 'https://www.planetadelibros.com/editorial/ediciones-destino/7'),
(8, 'Seix Barral', 'https://www.planetadelibros.com/editorial/seix-barral/9'),
(9, 'Santillana', 'https://santillana.es/'),
(10, 'Astiberri', 'https://www.astiberri.com/'),
(11, 'Faber & Faber', 'https://www.faber.co.uk/'),
(12, 'Penguin', 'https://www.penguinrandomhousegrupoeditorial.com/'),
(13, 'Acervo', 'http://editorialacervo.es/');

-- --------------------------------------------------------

--
-- Table structure for table `titles`
--

CREATE TABLE `titles` (
  `id` int(11) NOT NULL,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `author` int(4) NOT NULL,
  `year` int(4) NOT NULL,
  `editorial` int(4) NOT NULL,
  `pages` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `titles`
--

INSERT INTO `titles` (`id`, `title`, `author`, `year`, `editorial`, `pages`) VALUES
(1, 'El señor de los anillos', 1, 1950, 1, 1392),
(2, 'El juego de Ender', 2, 1977, 2, 509),
(3, 'Lazarillo de Tormes', 3, 0, 3, 150),
(4, 'Las uvas de la ira', 4, 1939, 4, 619),
(7, 'Watchmen', 5, 1980, 5, 416),
(8, 'La hoguera de las vanidades', 6, 1980, 6, 636),
(9, 'La familia de Pascual Duarte', 7, 1942, 7, 165),
(10, 'El señor de las moscas', 8, 1972, 4, 236),
(11, 'La ciudad de los prodigios', 9, 1986, 8, 541),
(12, 'Ensayo sobre la ceguera', 10, 1995, 9, 439),
(13, 'Los surcos del azar', 11, 2013, 10, 349),
(14, 'Ghosts of Spain', 12, 2006, 11, 468),
(15, 'Sidi', 13, 2019, 12, 369),
(16, 'Dune', 14, 1965, 13, 741);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pass` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user`, `pass`) VALUES
(1, 'roberto', 'c1bfc188dba59d2681648aa0e6ca8c8e');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `editorials`
--
ALTER TABLE `editorials`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `titles`
--
ALTER TABLE `titles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `editorials`
--
ALTER TABLE `editorials`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `titles`
--
ALTER TABLE `titles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
