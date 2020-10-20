-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2020-10-20 05:55:18
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
-- 表的结构 `departments`
--

CREATE TABLE `departments` (
  `DepId` int(11) NOT NULL COMMENT '部门编号，主键，自动增加',
  `DepName` varchar(50) NOT NULL COMMENT '部门名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `departments`
--

INSERT INTO `departments` (`DepId`, `DepName`) VALUES
(1, '人事部'),
(2, '开发部'),
(3, '服务部'),
(4, '财务部');

-- --------------------------------------------------------

--
-- 表的结构 `employees`
--

CREATE TABLE `employees` (
  `EmpId` int(11) NOT NULL COMMENT '员工编号，设置为主键和自动递增列',
  `EmpName` varchar(50) NOT NULL COMMENT '员工姓名',
  `DepId` int(11) NOT NULL COMMENT '所属部门编号',
  `Title` varchar(50) NOT NULL COMMENT '职务',
  `Salary` int(11) NOT NULL COMMENT '工资'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `employees`
--

INSERT INTO `employees` (`EmpId`, `EmpName`, `DepId`, `Title`, `Salary`) VALUES
(1, '张三', 1, '部门经理', 6000),
(2, '李四', 1, '职员', 3000),
(3, '王五', 1, '职员', 3500),
(4, '赵六', 2, '部门经理', 6500),
(5, '高七', 2, '职员', 2500),
(6, '马八', 2, '职员', 3100),
(7, '钱九', 3, '部门经理', 5000),
(8, '孙十', 3, '职员', 2800);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`DepId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`EmpId`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `departments`
--
ALTER TABLE `departments`
  MODIFY `DepId` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号，主键，自动增加', AUTO_INCREMENT=5;
--
-- 使用表AUTO_INCREMENT `employees`
--
ALTER TABLE `employees`
  MODIFY `EmpId` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号，设置为主键和自动递增列', AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
