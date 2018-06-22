/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lbs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-22 17:04:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_energy_day
-- ----------------------------
DROP TABLE IF EXISTS `data_energy_day`;
CREATE TABLE `data_energy_day` (
  `Meter_ID` int(8) NOT NULL,
  `FrozenTime` datetime NOT NULL,
  `Active_Energy0` double(12,4) DEFAULT NULL,
  `Active_Energy1` double(12,4) DEFAULT NULL,
  `Active_Energy2` double(12,4) DEFAULT NULL,
  `Active_Energy3` double(12,4) DEFAULT NULL,
  `Active_Energy4` double(12,4) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Meter_ID`,`FrozenTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of data_energy_day
-- ----------------------------

-- ----------------------------
-- Table structure for data_energy_month
-- ----------------------------
DROP TABLE IF EXISTS `data_energy_month`;
CREATE TABLE `data_energy_month` (
  `MeterID` int(8) NOT NULL,
  `FrozenTime` datetime NOT NULL,
  `Active_Energy0` double(12,4) DEFAULT NULL,
  `Active_Energy1` double(12,4) DEFAULT NULL,
  `Active_Energy2` double(12,4) DEFAULT NULL,
  `Active_Energy3` double(12,4) DEFAULT NULL,
  `Active_Energy4` double(12,4) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`FrozenTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of data_energy_month
-- ----------------------------

-- ----------------------------
-- Table structure for data_event
-- ----------------------------
DROP TABLE IF EXISTS `data_event`;
CREATE TABLE `data_event` (
  `MeterID` int(8) NOT NULL,
  `SubClass` int(3) NOT NULL,
  `OccurTime` datetime NOT NULL,
  `Remark` varchar(200) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`SubClass`,`OccurTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of data_event
-- ----------------------------

-- ----------------------------
-- Table structure for log_networking
-- ----------------------------
DROP TABLE IF EXISTS `log_networking`;
CREATE TABLE `log_networking` (
  `MeterID` int(8) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime DEFAULT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Route` varchar(255) NOT NULL,
  `SignalStrength` int(4) DEFAULT '0',
  `Result` int(1) NOT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RelayID` int(8) DEFAULT NULL,
  PRIMARY KEY (`MeterID`,`StartTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_networking
-- ----------------------------

-- ----------------------------
-- Table structure for log_report
-- ----------------------------
DROP TABLE IF EXISTS `log_report`;
CREATE TABLE `log_report` (
  `MeterID` int(8) NOT NULL,
  `ReportTime` datetime NOT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Route` varchar(255) DEFAULT NULL,
  `SignalStrength` int(4) DEFAULT '0',
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Result` int(1) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`MeterID`,`ReportTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_report
-- ----------------------------
INSERT INTO `log_report` VALUES ('1', '2018-06-11 00:00:00', '1', null, '0', '2018-06-19 14:08:12', '-1');
INSERT INTO `log_report` VALUES ('2', '2018-06-11 00:00:00', '1', null, '0', '2018-06-19 14:11:23', '-1');
INSERT INTO `log_report` VALUES ('4', '2018-06-11 00:00:00', '1', null, '0', '2018-06-19 15:23:07', '-1');

-- ----------------------------
-- Table structure for prm_lbs
-- ----------------------------
DROP TABLE IF EXISTS `prm_lbs`;
CREATE TABLE `prm_lbs` (
  `ID` int(1) NOT NULL DEFAULT '0',
  `CommAddr` varchar(20) NOT NULL,
  `ModuleAddr` varchar(20) DEFAULT NULL,
  `LogicAddr` varchar(20) DEFAULT NULL,
  `Protocol` int(2) NOT NULL DEFAULT '100',
  `LoraProtocol` int(2) NOT NULL DEFAULT '0',
  `Longitude` double(16,10) DEFAULT NULL,
  `Latitude` double(16,10) DEFAULT NULL,
  `Channel` int(2) DEFAULT NULL,
  `IP` varchar(20) DEFAULT NULL,
  `Port` int(6) DEFAULT NULL,
  `IP1` varchar(20) DEFAULT NULL,
  `Port1` int(6) DEFAULT NULL,
  `APN` varchar(20) DEFAULT NULL,
  `CRTOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of prm_lbs
-- ----------------------------
INSERT INTO `prm_lbs` VALUES ('0', '00000006', '00000003', '0000', '100', '0', '120.0723150821', '30.3209937953', '0', '10.157.1.238', '9012', '10.157.1.238', '9012', null, '2018-04-25 19:39:42', '2018-04-25 19:39:45');

-- ----------------------------
-- Table structure for prm_meter
-- ----------------------------
DROP TABLE IF EXISTS `prm_meter`;
CREATE TABLE `prm_meter` (
  `ID` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `SerialNo` int(8) NOT NULL,
  `PointNo` int(8) NOT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Status` int(2) DEFAULT '-1',
  `LogicAddr` varchar(20) DEFAULT NULL,
  `Protocol` int(2) DEFAULT NULL,
  `ModuleProtocol` int(2) DEFAULT '0',
  `IntegerNumber` int(2) DEFAULT NULL,
  `DecimalNumber` int(2) DEFAULT NULL,
  `CustomerClass` int(3) DEFAULT NULL,
  `CustomerSubClass` int(3) DEFAULT NULL,
  `Longitude` double(16,10) DEFAULT NULL,
  `Latitude` double(16,10) DEFAULT NULL,
  `Distance` double(10,2) DEFAULT NULL,
  `Angle` float(6,3) DEFAULT NULL,
  `Sector` int(3) DEFAULT NULL,
  `DistrictX` int(1) DEFAULT NULL,
  `DistrictY` int(4) DEFAULT NULL,
  `SignalStrength` int(4) DEFAULT '0',
  `Relay` int(1) DEFAULT '0',
  `Grade` int(2) DEFAULT '0',
  `Parent` int(8) DEFAULT '0',
  `Path` varchar(200) DEFAULT '0/',
  `FailTimes` int(6) DEFAULT '0',
  `PathType` int(1) DEFAULT '0',
  `CRTOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` datetime DEFAULT NULL,
  `REGOn` datetime DEFAULT NULL,
  `UNREGOn` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of prm_meter
-- ----------------------------
INSERT INTO `prm_meter` VALUES ('63', '1', '1', '180503200079', '-1', '0007', '0', '0', null, null, null, null, '120.0274500000', '30.3132270000', '4397.00', '187.870', '13', '87', '1', '0', '0', '0', '0', '0/', '0', '0', '2018-05-31 13:36:58', null, null, null);
INSERT INTO `prm_meter` VALUES ('67', '1', '1', '00000002', '-1', '0002', '0', '0', '1', '2', null, '3', '119.4670000000', '30.3130000000', '58175.00', '180.802', '13', '1163', '0', '0', '0', '0', '0', '0/', '0', '0', '2018-06-12 17:59:56', null, null, null);
INSERT INTO `prm_meter` VALUES ('68', '1', '1', '00000003', '-1', '0003', '0', '0', '1', null, null, null, '120.0270000000', '29.6250000000', '77600.00', '278.132', '19', '1552', '1', '0', '0', '0', '0', '0/', '0', '0', '2018-06-12 18:01:20', null, null, null);
INSERT INTO `prm_meter` VALUES ('69', '1', '1', '00000005', '-1', '0004', '1', '0', null, null, null, null, '120.1240000000', '29.6250000000', '77637.00', '288.823', '20', '1552', '0', '0', '0', '0', '0', '0/', '0', '0', '2018-06-12 18:12:27', null, null, null);

-- ----------------------------
-- Table structure for ptl_fn
-- ----------------------------
DROP TABLE IF EXISTS `ptl_fn`;
CREATE TABLE `ptl_fn` (
  `Protocol` int(3) NOT NULL,
  `Code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `SqlTemplate` varchar(200) DEFAULT NULL,
  `Elements` varchar(200) NOT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Protocol`,`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ptl_fn
-- ----------------------------
INSERT INTO `ptl_fn` VALUES ('0', '1:2:0', null, '2/', '2018-06-22 15:39:57');
INSERT INTO `ptl_fn` VALUES ('0', '2:5:1', null, '4/', '2018-06-22 16:06:06');
INSERT INTO `ptl_fn` VALUES ('0', '3:1:1', null, '1/', '2018-06-22 16:07:40');
INSERT INTO `ptl_fn` VALUES ('0', '3:2:1', null, '2/', '2018-06-22 16:08:13');
INSERT INTO `ptl_fn` VALUES ('0', '3:3:1', null, '3/', '2018-06-22 16:08:44');
INSERT INTO `ptl_fn` VALUES ('0', '3:4:1', null, '1/2/3/', '2018-06-22 16:13:20');
INSERT INTO `ptl_fn` VALUES ('0', '4:1:0', null, '1/', '2018-06-22 16:13:15');
INSERT INTO `ptl_fn` VALUES ('0', '4:2:0', null, '2/', '2018-06-22 16:13:49');
INSERT INTO `ptl_fn` VALUES ('0', '4:3:0', null, '3/', '2018-06-22 16:14:20');
INSERT INTO `ptl_fn` VALUES ('0', '4:4:0', null, '1/2/3/', '2018-06-22 16:14:49');
INSERT INTO `ptl_fn` VALUES ('100', '0A:3:0', 'select ip, port, ip1, port1, apn from prm_lbs where id=%s', '1/', '2018-06-22 15:33:46');
INSERT INTO `ptl_fn` VALUES ('100', '0A:3:1', 'select ip, port, ip1, port1, apn from prm_lbs', '2/', '2018-06-22 15:33:49');

-- ----------------------------
-- Table structure for ptl_fn_element
-- ----------------------------
DROP TABLE IF EXISTS `ptl_fn_element`;
CREATE TABLE `ptl_fn_element` (
  `ID` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(60) NOT NULL,
  `Length` int(3) NOT NULL DEFAULT '0',
  `Format` varchar(200) NOT NULL,
  `Rank` int(1) NOT NULL DEFAULT '1',
  `Display` varchar(60) NOT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ptl_fn_element
-- ----------------------------
INSERT INTO `ptl_fn_element` VALUES ('1', 'BCD_STRING', '4', 'xxxxxxxx', '1', 'Long address', '2018-06-22 16:01:26');
INSERT INTO `ptl_fn_element` VALUES ('2', 'BCD_STRING', '2', 'xxxx', '1', 'Short address', '2018-06-22 16:02:19');
INSERT INTO `ptl_fn_element` VALUES ('3', 'OCT_STRING', '1', 'xx', '1', 'Channel', '2018-06-22 16:03:12');
INSERT INTO `ptl_fn_element` VALUES ('4', 'BIT_STRING', '4', 'xxxx', '1', 'Envent Code', '2018-06-22 16:05:50');

-- ----------------------------
-- Table structure for run_param
-- ----------------------------
DROP TABLE IF EXISTS `run_param`;
CREATE TABLE `run_param` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Key` varchar(20) DEFAULT NULL,
  `Value` varchar(20) DEFAULT NULL,
  `DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of run_param
-- ----------------------------
INSERT INTO `run_param` VALUES ('1', 'SectorDegree', '5', 'Sector Degree');
