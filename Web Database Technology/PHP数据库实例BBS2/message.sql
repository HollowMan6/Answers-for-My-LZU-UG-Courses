-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2020-11-10 05:05:47
-- 服务器版本： 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `my_db`
--

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `ip` varchar(45) NOT NULL,
  `ptime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`id`, `title`, `author`, `content`, `ip`, `ptime`) VALUES
(7, 'hi', 'hollowman', 'Hello!', '::1', '2020-11-03 12:02:54'),
(9, 'h12', 'hahaha', 'ä½ å¥½!', '::1', '2020-11-10 10:53:59'),
(10, 'hahaha', 'hi', '!!!', '::1', '2020-11-10 10:54:12'),
(12, 'sdfdsfsd', '234234324', 'fgdfgdfgdfg', '::1', '2020-11-10 10:54:40'),
(13, 'dfgssdfgdsfg', '4365345345', 'dfsgdsfgsdfg', '::1', '2020-11-10 10:54:47'),
(14, 'cvbcxvgfdcv b', '45645543', 'dsfsafadsfasf', '::1', '2020-11-10 10:54:56'),
(15, 'dfgdsfg', 'adsasd', '54654rtsg', '::1', '2020-11-10 10:55:03'),
(16, 'retretgfd', 'asfdasd', 'tergdfgfdgdsfdg', '::1', '2020-11-10 10:55:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
