/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lbs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-09 16:50:22
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
  `Negative_Energy0` double(12,4) DEFAULT NULL,
  `Negative_Energy1` double(12,4) DEFAULT NULL,
  `Negative_Energy2` double(12,4) DEFAULT NULL,
  `Negative_Energy3` double(12,4) DEFAULT NULL,
  `Negative_Energy4` double(12,4) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`FrozenTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for data_event
-- ----------------------------
DROP TABLE IF EXISTS `data_event`;
CREATE TABLE `data_event` (
  `MeterID` int(8) NOT NULL,
  `EventID` int(3) NOT NULL,
  `OccurTime` datetime NOT NULL,
  `Remark` varchar(200) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`EventID`,`OccurTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for knl_fn
-- ----------------------------
DROP TABLE IF EXISTS `knl_fn`;
CREATE TABLE `knl_fn` (
  `Protocol` int(3) NOT NULL,
  `Direction` int(1) NOT NULL,
  `Operation` varchar(2) NOT NULL,
  `Fn` varchar(8) NOT NULL,
  `Name` varchar(40) DEFAULT NULL,
  `Elements` varchar(2000) DEFAULT NULL,
  `Titles` varchar(200) DEFAULT NULL,
  `Remark` varchar(200) DEFAULT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Protocol`,`Direction`,`Operation`,`Fn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for knl_lbs
-- ----------------------------
DROP TABLE IF EXISTS `knl_lbs`;
CREATE TABLE `knl_lbs` (
  `ID` int(1) NOT NULL DEFAULT '0',
  `CommAddr` varchar(20) NOT NULL,
  `Protocol` int(2) NOT NULL DEFAULT '100',
  `ModuleAddr` varchar(20) NOT NULL,
  `LogicAddr` varchar(20) DEFAULT NULL,
  `ModuleProtocol` int(2) NOT NULL DEFAULT '0',
  `Longitude` double(16,10) DEFAULT NULL,
  `Latitude` double(16,10) DEFAULT NULL,
  `Channel` int(2) NOT NULL,
  `IP` varchar(20) NOT NULL,
  `Port` int(6) NOT NULL,
  `IP1` varchar(20) DEFAULT NULL,
  `Port1` int(6) DEFAULT NULL,
  `APN` varchar(20) DEFAULT NULL,
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for log_exit
-- ----------------------------
DROP TABLE IF EXISTS `log_exit`;
CREATE TABLE `log_exit` (
  `MeterID` int(8) unsigned NOT NULL,
  `PointNo` int(8) unsigned NOT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Status` int(2) DEFAULT '-1',
  `LogicAddr` varchar(20) DEFAULT NULL,
  `Protocol` int(2) DEFAULT NULL,
  `ModuleProtocol` int(2) DEFAULT '0',
  `Baudrate` int(8) DEFAULT NULL,
  `Port` int(2) DEFAULT NULL,
  `TariffCount` int(2) DEFAULT NULL,
  `IntegerCount` int(2) DEFAULT NULL,
  `DecimalCount` int(2) DEFAULT NULL,
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
  `Route` varchar(255) DEFAULT NULL,
  `FailTimes` int(6) DEFAULT '0',
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `EXITOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`PointNo`,`CommAddr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for log_network
-- ----------------------------
DROP TABLE IF EXISTS `log_network`;
CREATE TABLE `log_network` (
  `MeterID` int(8) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime DEFAULT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Route` varchar(255) NOT NULL,
  `SignalStrength` int(4) DEFAULT '0',
  `Result` int(1) NOT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `RelayID` int(8) DEFAULT NULL,
  PRIMARY KEY (`MeterID`,`StartTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_report
-- ----------------------------
DROP TABLE IF EXISTS `log_report`;
CREATE TABLE `log_report` (
  `MeterID` int(8) NOT NULL,
  `Status` int(1) NOT NULL DEFAULT '-1',
  `CommAddr` varchar(20) NOT NULL,
  `Route` varchar(255) NOT NULL,
  `SignalStrength` int(4) DEFAULT '0',
  `Content` varchar(40) NOT NULL,
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prm_meter
-- ----------------------------
DROP TABLE IF EXISTS `prm_meter`;
CREATE TABLE `prm_meter` (
  `ID` int(8) unsigned NOT NULL,
  `PointNo` int(8) unsigned NOT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Status` int(2) DEFAULT '-1',
  `LogicAddr` varchar(20) DEFAULT NULL,
  `Protocol` int(2) DEFAULT NULL,
  `ModuleProtocol` int(2) DEFAULT '0',
  `Baudrate` int(8) DEFAULT NULL,
  `Port` int(2) DEFAULT NULL,
  `TariffCount` int(2) DEFAULT NULL,
  `IntegerCount` int(2) DEFAULT NULL,
  `DecimalCount` int(2) DEFAULT NULL,
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
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REGOn` timestamp NULL DEFAULT NULL,
  `UNREGOn` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
