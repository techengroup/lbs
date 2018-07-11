/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lbs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-11 10:28:45
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
  `Negative_Energy0` double(12,4) DEFAULT NULL,
  `Negative_Energy1` double(12,4) DEFAULT NULL,
  `Negative_Energy2` double(12,4) DEFAULT NULL,
  `Negative_Energy3` double(12,4) DEFAULT NULL,
  `Negative_Energy4` double(12,4) DEFAULT NULL,
  `SaveTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MeterID`,`FrozenTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of data_energy_month
-- ----------------------------
INSERT INTO `data_energy_month` VALUES ('1', '2018-07-01 00:00:00', '1.0000', '1.0000', '1.0000', '1.0000', '1.0000', null, null, null, null, null, '2018-07-07 16:10:48');

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
INSERT INTO `knl_fn` VALUES ('0', '0', '01', '2', 'Short Address', 'BCD_STRING,2,xxxx,1', 'Short address', null, '2018-06-30 13:59:53');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '1', 'Long Address', 'BCD_STRING,6,xxxx,1', 'Long Address', null, '2018-06-30 13:59:55');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '2', 'Short Address', 'BCD_STRING,2,xxxx,1', 'Short Address', null, '2018-06-30 13:59:57');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '3', 'Channel', 'OCT_STRING,1,xx,1', 'Channel', null, '2018-06-30 13:59:59');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '4', 'Long Address,Short Address,Channel', 'STRUCT,3,BCD_STRING,6,xxxx,1,BCD_STRING,2,xxxx,1,OCT_STRING,1,xx,1', 'Long Address,Short Address,Channel', null, '2018-06-30 14:00:01');
INSERT INTO `knl_fn` VALUES ('0', '0', '05', '6', 'Transfer Bytes', 'BYTE_ARRAY,1,xx,1', 'Transfer Bytes', '0', '2018-06-30 14:00:18');
INSERT INTO `knl_fn` VALUES ('0', '1', '02', '5', 'Event Class', 'BIT_STRING,4,xxxx,1', 'Event Class', null, '2018-06-30 14:00:03');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '1', 'Short Address', 'BCD_STRING,2,xxxx,1', 'Short address', null, '2018-06-30 14:00:05');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '2', 'Long Address', 'BCD_STRING,6,xxxx,1', 'Long Address', null, '2018-06-30 14:00:07');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '3', 'Channel', 'OCT_STRING,1,xx,1', 'Channel', '0', '2018-06-30 14:00:09');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '4', 'Long Address,Short Address,Channel', 'STRUCT,3,BCD_STRING,6,xxxx,1,BCD_STRING,2,xxxx,1,OCT_STRING,1,xx,1', 'Long Address,Short Address,Channel', '0', '2018-06-30 14:00:12');
INSERT INTO `knl_fn` VALUES ('0', '1', '05', '6', 'Transfer Bytes', 'BYTE_ARRAY,1,xx,1', 'Transfer Bytes', '0', '2018-06-30 14:00:14');
INSERT INTO `knl_fn` VALUES ('0', '1', '06', '6', 'Test', '3/', null, '0', '2018-06-30 14:00:21');
INSERT INTO `knl_fn` VALUES ('1', '0', '11', '9110', null, null, null, null, '2018-06-30 13:44:44');
INSERT INTO `knl_fn` VALUES ('1', '0', '11', 'C42A', 'Event Tamper', null, 'Event Tamper', null, '2018-07-06 16:49:07');
INSERT INTO `knl_fn` VALUES ('1', '1', '11', '901F', 'Current Active Energy', 'STRUCT,5,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1,OCT_STRING,4,xxxxxx.xx,1', 'Current Active Energy', null, '2018-07-09 15:53:54');
INSERT INTO `knl_fn` VALUES ('1', '1', '11', 'C42A', 'Event Tamper', 'ARRAY_645_EVENT,3,STRUCT,3,BCD_STRING,1,xx,1,OCT_STRING,1,xx,1,DATE_TIME,6,xxxx,1', 'Event Tamper', null, '2018-07-07 10:12:34');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '10', 'Meter Archive', 'ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,BIT_STRING,1,3:5,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,OCT_STRING,1,%s,0,BIT_STRING,1,4:2:2,0,BIT_STRING,1,4:4,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Meter Archive', null, '2018-07-10 19:51:04');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '3', 'IP Port APN', 'STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,ASCII,16,xxxx,1', 'IP Port APN', null, '2018-07-10 14:53:39');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '89', 'Address', 'ADDRESS,4,XXXX,0', 'Address', null, '2018-07-10 19:50:55');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '90', 'Location', 'STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Location', null, '2018-07-10 19:50:46');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '91', 'Channel', 'OCT_STRING,1,%d,0', 'Channel', null, '2018-07-10 19:50:40');
INSERT INTO `knl_fn` VALUES ('100', '0', '0A', '10', 'Meter Archive', 'ARRAY,2,OCT_STRING,3,%s,0', 'Meter Archive', null, '2018-07-11 09:52:15');
INSERT INTO `knl_fn` VALUES ('100', '1', '02', '1', 'Login', null, 'Login', null, '2018-06-30 13:44:47');
INSERT INTO `knl_fn` VALUES ('100', '1', '02', '3', 'Heartbeat', 'DATE_TIME,6,xxxx,1', 'Heartbeat', null, '2018-06-30 13:44:49');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '10', 'Meter Archive', 'ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,BIT_STRING,1,3:5,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,OCT_STRING,1,%s,0,BIT_STRING,1,4:2:2,0,BIT_STRING,1,4:4,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Meter Archive', null, '2018-07-10 19:51:13');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '3', 'IP Port APN', 'STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,ASCII,16,xxxx,1', 'IP Port APN', null, '2018-07-10 17:59:09');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '89', 'Address', 'ADDRESS,4,XXXX,0', 'Address', null, '2018-07-10 19:51:24');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '90', 'Location', 'STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Location', null, '2018-07-10 19:51:31');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '91', 'Channel', 'OCT_STRING,1,%d,0', 'Channel', null, '2018-07-10 19:51:44');

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
INSERT INTO `knl_lbs` VALUES ('0', '00000006', '100', '00000003', '0000', '0', '120.0721400000', '30.3187280000', '5', '10.157.1.238', '9012', '10.157.1.238', '9012', 'CMNET\0\0\0\0\0\0\0\0\0\0\0', '2018-04-25 19:39:42', '2018-07-11 09:35:28');

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
INSERT INTO `log_exit` VALUES ('1', '1', '000000000002', '-1', '0002', '1', '0', '0', null, '3', '4', '2', null, '3', '119.4670000000', '30.3130000000', '58175.00', '180.802', '13', '1163', '0', '0', '0', '0', '0', '0/', '0000000006,000000000002', '3', '2018-07-07 15:16:43', '2018-07-07 15:16:43');

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
INSERT INTO `log_network` VALUES ('1', '2018-07-05 09:37:07', '2018-07-05 09:37:26', '000000000002', '00000006,000000000002', null, '0', '2018-07-05 09:37:26', '0');
INSERT INTO `log_network` VALUES ('1', '2018-07-05 09:37:26', '2018-07-05 09:38:00', '000000000002', '00000006,000000000002', null, '0', '2018-07-05 09:38:00', '0');
INSERT INTO `log_network` VALUES ('1', '2018-07-05 09:38:07', '2018-07-05 09:38:26', '000000000002', '00000006,000000000002', null, '1', '2018-07-07 16:35:15', '0');
INSERT INTO `log_network` VALUES ('1', '2018-07-05 09:38:26', '2018-07-05 09:38:46', '000000000002', '0000000006,000000000002', null, '1', '2018-07-07 15:16:37', '0');

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
INSERT INTO `prm_meter` VALUES ('1', '1', '000000000002', '0', '0002', '1', '0', '0', null, '3', '4', '2', null, '3', '119.4670000000', '30.3130000000', '58175.00', '180.802', '13', '1163', '0', null, '0', '0', '0', '0/', '7', '0', '2018-06-12 17:59:56', '2018-07-05 09:35:57', '2018-07-02 13:36:13', '2018-07-10 11:40:01');
INSERT INTO `prm_meter` VALUES ('3', '3', '000001001402', '-1', null, '30', '0', '1', '20', '4', '2', '1', '5', '1', '120.2039400000', '30.2372330000', null, null, null, null, null, '0', '0', '0', '0', '0/', '0', '0', '2018-07-10 17:35:06', '2018-07-10 17:35:06', null, null);

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
