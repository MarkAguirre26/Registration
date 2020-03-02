/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : brg_104

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-03-02 15:25:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `RecId` int(100) NOT NULL AUTO_INCREMENT,
  `userid` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `position` varchar(30) NOT NULL,
  `usertype` varchar(30) NOT NULL,
  PRIMARY KEY (`RecId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('8', 'Mark', 'aaaa', 'ChairPerson', 'Official');

-- ----------------------------
-- Table structure for brgy_certificate
-- ----------------------------
DROP TABLE IF EXISTS `brgy_certificate`;
CREATE TABLE `brgy_certificate` (
  `control_number` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(70) NOT NULL,
  `purpose` varchar(100) NOT NULL,
  `date_issued` varchar(50) NOT NULL,
  PRIMARY KEY (`control_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of brgy_certificate
-- ----------------------------

-- ----------------------------
-- Table structure for certificate_indigency
-- ----------------------------
DROP TABLE IF EXISTS `certificate_indigency`;
CREATE TABLE `certificate_indigency` (
  `name` varchar(50) NOT NULL,
  `address` varchar(60) NOT NULL,
  `purpose` varchar(70) NOT NULL,
  `date_issued` varchar(50) NOT NULL,
  `id` int(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of certificate_indigency
-- ----------------------------

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
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of events
-- ----------------------------
INSERT INTO `events` VALUES ('20', 'Job Fair', 'Jobs', 'images.jpeg', '2020-02-24');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `userid` varchar(50) NOT NULL,
  `date` varchar(90) NOT NULL,
  `time` varchar(20) NOT NULL,
  `action` varchar(250) NOT NULL,
  `module` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('', 'Jan/22/2020', '10:00 am', '', 'Event Module');
INSERT INTO `logs` VALUES ('', 'Jan/22/2020', '10:02 am', 'Added an Event', 'Event Module');
INSERT INTO `logs` VALUES ('', 'Jan/22/2020', '10:02 am', 'Deleted an Event', 'Event Module');
INSERT INTO `logs` VALUES ('Admin409', 'Jan/29/2020', '03:47 pm', 'Added News', 'News Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/01/2020', '01:37 pm', 'Added an Account', 'Accoount Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/01/2020', '03:31 pm', 'Added an Account with userID 18-666', 'Accoount Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '04:37 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '02:19 pm', 'Deleted an Event h', 'Event Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '02:24 pm', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '02:28 pm', 'Deleted an Event m', 'Event Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '02:29 pm', 'Added an Event \'', 'Event Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '02:31 pm', 'Added an Event \'', 'Event Module');
INSERT INTO `logs` VALUES ('Admin409', 'Feb/02/2020', '02:41 pm', 'Added an Account with userID Admin409s', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '02:58 pm', 'Added News', 'News Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '03:06 pm', 'Added an Account with userID Admin409,s', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '03:14 pm', 'Added an Account with userID Admin409jj', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '03:19 pm', 'Added an Account with userID Admin409fsdfs', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '03:20 pm', 'Added an Account with userID Admin409e', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '03:30 pm', 'Deleted an Account with Userid Admin409e', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '03:30 pm', 'Deleted an Account with Userid Admin409', 'Account Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '04:18 pm', 'Deleted an Event aaa', 'Event Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '04:20 pm', 'Added News', 'News Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-02', '04:23 pm', 'Added News with headline topic', 'News Module');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-04', '10:08 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-04', '12:45 pm', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-04', '12:54 pm', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-05', '01:21 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-05', '01:24 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-05', '01:35 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-16', '10:43 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-16', '11:24 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-19', '05:39 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-19', '05:44 am', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-19', '02:44 pm', 'Added User', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-23', '03:17 am', 'Added Resident', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-23', '05:27 am', 'Added Resident', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-23', '05:28 am', 'Added Resident', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-23', '05:30 am', 'Added Resident', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-23', '05:32 am', 'Added Resident', 'Account Management');
INSERT INTO `logs` VALUES ('Admin409', '2020-02-23', '12:37 pm', 'Added Resident', 'Account Management');
INSERT INTO `logs` VALUES ('admin', '2020-02-24', '12:26 am', 'Added an Account with userID user', 'Account Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:12 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:12 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:13 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:13 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:13 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:13 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:21 am', 'Added News with headline fdsf', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:21 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:21 am', 'Added News with headline ', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:22 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:22 am', 'Added News with headline ', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:24 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:24 am', 'Deleted News', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:24 am', 'Added News with headline Officials Meeting', 'News Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event mm', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event mamam', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event nanan', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event kkkk', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event klkk', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event ng', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event class', 'Event Module');
INSERT INTO `logs` VALUES ('', '2020-02-24', '01:34 am', 'Deleted an Event sca', 'Event Module');

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
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of new
-- ----------------------------
INSERT INTO `new` VALUES ('27', 'Officials Meeting', '09_Dec_ClassPicture.jpg', 'meeting', '2020-02-24');

-- ----------------------------
-- Table structure for residents
-- ----------------------------
DROP TABLE IF EXISTS `residents`;
CREATE TABLE `residents` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `REG_NUMBER` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `middlename` varchar(100) NOT NULL,
  `house_no` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `zone` varchar(100) NOT NULL,
  `placeofbirth` varchar(100) NOT NULL,
  `birthdate` varchar(100) NOT NULL,
  `age` varchar(100) NOT NULL,
  `sex` varchar(100) NOT NULL,
  `civilstatus` varchar(100) NOT NULL,
  `citizenship` varchar(100) NOT NULL,
  `occupation` varchar(100) NOT NULL,
  `senior` int(100) NOT NULL,
  `voter` int(100) NOT NULL,
  `SK` int(100) NOT NULL,
  `4Ps` int(100) NOT NULL,
  `status` int(100) NOT NULL DEFAULT '0',
  `activate` int(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of residents
-- ----------------------------
INSERT INTO `residents` VALUES ('6', '201250005', 'Gy', 'Gg', 'Ff', 'Yy', 'Yy Purok Camia', 'Purok Camia', 'Bb', '2/3/2020', '0', 'Male', 'Widowed', 'Gg', 'Gh', '0', '0', '0', '1', '0', '0');

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
