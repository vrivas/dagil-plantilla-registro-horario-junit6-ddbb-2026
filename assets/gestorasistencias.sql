-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 03-04-2026 a las 13:17:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestorasistencias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registrohorario`
--

CREATE TABLE `registrohorario` (
  `id_persona` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `tipo_evento` varchar(10) NOT NULL,
  `fecha_hora` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registrohorario`
--

INSERT INTO `registrohorario` (`id_persona`, `id_empresa`, `tipo_evento`, `fecha_hora`) VALUES
(1, 101, 'ENTRADA', '2026-03-02 19:00:00'),
(1, 101, 'SALIDA', '2026-03-02 12:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `registrohorario`
--
ALTER TABLE `registrohorario`
  ADD PRIMARY KEY (`id_persona`,`id_empresa`,`tipo_evento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
