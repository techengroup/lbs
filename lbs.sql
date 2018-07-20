/*
Navicat MySQL Data Transfer

Source Server         : 220
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : lbs

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-20 16:52:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `EventID` int(3) NOT NULL,
  `OccurTime` datetime NOT NULL,
  `Content` varchar(200) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`EventID`,`OccurTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of data_event
-- ----------------------------

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
-- Records of knl_fn
-- ----------------------------
INSERT INTO `knl_fn` VALUES ('0', '0', '01', '2', 'Short Address', 'OCT_STRING,2,xxxx,1', 'Short address', null, '2018-07-19 15:47:44');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '1', 'Long Address', 'BCD_STRING,6,xxxx,1', 'Long Address', null, '2018-06-30 13:59:55');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '2', 'Short Address', 'OCT_STRING,2,xxxx,1', 'Short Address', null, '2018-07-19 15:47:49');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '3', 'Channel', 'OCT_STRING,1,xx,1', 'Channel', null, '2018-06-30 13:59:59');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '4', 'Long Address,Short Address,Channel', 'STRUCT,3,BCD_STRING,6,xxxx,1,OCT_STRING,2,xxxx,1,OCT_STRING,1,xx,1', 'Long Address,Short Address,Channel', null, '2018-07-19 15:47:52');
INSERT INTO `knl_fn` VALUES ('0', '0', '05', '6', 'Transfer Bytes', 'BYTE_ARRAY,1,xx,1', 'Transfer Bytes', '0', '2018-06-30 14:00:18');
INSERT INTO `knl_fn` VALUES ('0', '1', '02', '5', 'Event Status Words', 'BIT_STRING,4,1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1,1', 'Event Class', null, '2018-07-12 09:46:48');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '1', 'Short Address', 'BCD_STRING,2,xxxx,1', 'Short address', null, '2018-06-30 14:00:05');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '2', 'Long Address', 'BCD_STRING,6,xxxx,1', 'Long Address', null, '2018-06-30 14:00:07');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '3', 'Channel', 'OCT_STRING,1,xx,1', 'Channel', '0', '2018-06-30 14:00:09');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '4', 'Long Address,Short Address,Channel', 'STRUCT,3,BCD_STRING,6,xxxx,1,BCD_STRING,2,xxxx,1,OCT_STRING,1,xx,1', 'Long Address,Short Address,Channel', '0', '2018-06-30 14:00:12');
INSERT INTO `knl_fn` VALUES ('0', '1', '05', '6', 'Transfer Bytes', 'BYTE_ARRAY,1,xx,1', 'Transfer Bytes', '0', '2018-06-30 14:00:14');
INSERT INTO `knl_fn` VALUES ('30', '0', '11', 'C42A', 'Event Tamper', null, 'Event Tamper', '1', '2018-07-20 11:07:47');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', '9010', 'Current Active Energy', 'OCT_STRING,4,xxxxxx.xx,1', 'Current Active Energy', '1', '2018-07-20 11:07:48');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', '901F', 'Current Active Energy', 'STRUCT,5,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1', 'Current Active Energy', '1', '2018-07-20 11:07:48');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', '903F', 'Current Negative Energy', 'STRUCT,5,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1', 'Current Negative Energy', '1', '2018-07-20 11:07:49');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', 'C42A', 'Event Tamper', 'ARRAY_645_EVENT,3,STRUCT,3,BCD_STRING,1,xx,1,OCT_STRING,1,xx,1,DATE_TIME,6,yyMMddHHmmss,1', 'Event Tamper', '1', '2018-07-20 11:52:00');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '10', 'Meter Archive', 'ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,BIT_STRING,1,3:5,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,OCT_STRING,1,%s,0,BIT_STRING,1,4:2:2,0,BIT_STRING,1,4:4,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Meter Archive', null, '2018-07-10 19:51:04');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '3', 'IP Port APN', 'STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,ASCII,16,xxxx,1', 'IP Port APN', null, '2018-07-10 14:53:39');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '89', 'Address', 'ADDRESS,4,XXXX,0', 'Address', null, '2018-07-10 19:50:55');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '90', 'Location', 'STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Location', null, '2018-07-10 19:50:46');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '91', 'Channel', 'OCT_STRING,1,%d,0', 'Channel', null, '2018-07-10 19:50:40');
INSERT INTO `knl_fn` VALUES ('100', '0', '0A', '10', 'Meter Archive', 'ARRAY,2,OCT_STRING,3,%s,0', 'Meter Archive', null, '2018-07-11 09:52:15');
INSERT INTO `knl_fn` VALUES ('100', '0', '0D', '177', 'Month Freeze', 'DATE_TIME,2,MMyy,1', 'Month Freeze', null, '2018-07-11 15:49:36');
INSERT INTO `knl_fn` VALUES ('100', '1', '02', '1', 'Login', null, 'Login', null, '2018-06-30 13:44:47');
INSERT INTO `knl_fn` VALUES ('100', '1', '02', '3', 'Heartbeat', 'DATE_TIME_W,6,xxxx,1', 'Heartbeat', null, '2018-07-11 15:46:31');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '10', 'Meter Archive', 'ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,BIT_STRING,1,3:5,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,OCT_STRING,1,%s,0,BIT_STRING,1,4:2:2,0,BIT_STRING,1,4:4,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Meter Archive', null, '2018-07-10 19:51:13');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '3', 'IP Port APN', 'STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,ASCII,16,xxxx,1', 'IP Port APN', null, '2018-07-10 17:59:09');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '89', 'Address', 'ADDRESS,4,XXXX,0', 'Address', null, '2018-07-10 19:51:24');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '90', 'Location', 'STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Location', null, '2018-07-10 19:51:31');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '91', 'Channel', 'OCT_STRING,1,%d,0', 'Channel', null, '2018-07-10 19:51:44');
INSERT INTO `knl_fn` VALUES ('100', '1', '0D', '177', 'Month Freeze', 'STRUCT,7,DATE_TIME,2,MMyy,1,DATE_TIME,5,mmHHddMMyy,1,OCT_STRING,1,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0', 'Month Freeze', null, '2018-07-11 16:27:21');

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
-- Records of knl_lbs
-- ----------------------------
INSERT INTO `knl_lbs` VALUES ('0', '00000006', '100', '00000003', '0000', '0', null, null, '0', '10.157.1.238', '9012', '10.157.1.238', '9012', 'CMNET\0\0\0\0\0\0\0\0\0\0\0', '2018-04-25 19:39:42', '2018-07-20 16:44:15');

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
-- Records of log_exit
-- ----------------------------

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
-- Records of log_network
-- ----------------------------

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
  `Content` varchar(200) NOT NULL,
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_report
-- ----------------------------

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
-- Records of prm_meter
-- ----------------------------

-- ----------------------------
-- Table structure for run_param
-- ----------------------------
DROP TABLE IF EXISTS `run_param`;
CREATE TABLE `run_param` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Key0` varchar(20) NOT NULL,
  `Value0` varchar(40) NOT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of run_param
-- ----------------------------
INSERT INTO `run_param` VALUES ('2', 'LastEventIndex', '0', 'Last Event Index', '2018-07-18 16:21:11');
INSERT INTO `run_param` VALUES ('3', 'LoraSignalThreshold', '50', 'Lora Signal Threshold', '2018-07-19 11:38:37');
