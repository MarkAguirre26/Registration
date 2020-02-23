/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : barangay409

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-02-24 00:55:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `RecId` int(10) NOT NULL AUTO_INCREMENT,
  `userid` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `position` varchar(30) NOT NULL,
  `usertype` varchar(30) NOT NULL,
  PRIMARY KEY (`RecId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('25', 'Admin', 'Admin', '-', 'Official');
INSERT INTO `accounts` VALUES ('26', 'Markoi', 'aaaaaaaaa', '-', 'Official');
INSERT INTO `accounts` VALUES ('27', 'mark', 'aaaaaaaaaaa', 'ChairPerson', 'Official');
INSERT INTO `accounts` VALUES ('28', 'Mark', 'Matk', 'ChairPerson', 'Official');

-- ----------------------------
-- Table structure for events
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `eventname` longtext NOT NULL,
  `eventcontent` longtext NOT NULL,
  `eventimage` longtext NOT NULL,
  `date` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of events
-- ----------------------------
INSERT INTO `events` VALUES ('1', 'asdasdas', 'asdasdasdasd', 'apple_logo_10-wallpaper-1920x1080.jpg', '2019-01-31');
INSERT INTO `events` VALUES ('2', 'sdsdsds', 'asdsadas', 'if_admin_87402.png', '2019-01-31');

-- ----------------------------
-- Table structure for new
-- ----------------------------
DROP TABLE IF EXISTS `new`;
CREATE TABLE `new` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `headlines` longtext NOT NULL,
  `imges` longtext NOT NULL,
  `content` longtext NOT NULL,
  `date` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of new
-- ----------------------------
INSERT INTO `new` VALUES ('5', 'jklljjljlj', '46503205_327149404767545_3925838745516900352_n.jpg', 'jlkjlkjlkjkl', '2019-02-13');

-- ----------------------------
-- Table structure for residents
-- ----------------------------
DROP TABLE IF EXISTS `residents`;
CREATE TABLE `residents` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `REG_NUMBER` varchar(254) DEFAULT NULL,
  `Lastname` varchar(40) NOT NULL,
  `Firstname` varchar(30) NOT NULL,
  `Middlename` varchar(30) NOT NULL,
  `Street` varchar(15) NOT NULL,
  `Zone` varchar(100) NOT NULL,
  `Placeofbirth` varchar(20) NOT NULL,
  `Birthdate` date NOT NULL,
  `Gender` varchar(15) NOT NULL,
  `Civilstatus` varchar(20) NOT NULL,
  `Citizenship` varchar(25) NOT NULL,
  `Cccupation` varchar(50) NOT NULL,
  `Voter` varchar(20) NOT NULL,
  `Other` varchar(254) DEFAULT NULL,
  `Province` varchar(254) DEFAULT NULL,
  `Municipality` varchar(254) DEFAULT NULL,
  `Barangay` varchar(254) DEFAULT NULL,
  `HouseHoldNo` varchar(254) DEFAULT NULL,
  `Photo` varchar(100) DEFAULT NULL,
  `Remark` varchar(100) NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of residents
-- ----------------------------

-- ----------------------------
-- Table structure for seniorcitizen
-- ----------------------------
DROP TABLE IF EXISTS `seniorcitizen`;
CREATE TABLE `seniorcitizen` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `age` int(255) NOT NULL,
  `dateofbday` varchar(200) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of seniorcitizen
-- ----------------------------
INSERT INTO `seniorcitizen` VALUES ('3', 'asdsad', '60', '2019-01-14', 'live');

-- ----------------------------
-- Table structure for voters
-- ----------------------------
DROP TABLE IF EXISTS `voters`;
CREATE TABLE `voters` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `age` varchar(250) NOT NULL,
  `gender` varchar(250) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of voters
-- ----------------------------
INSERT INTO `voters` VALUES ('2', 'dasdas', '60', 'male', 'active');
