/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lbs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-19 16:55:22
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
INSERT INTO `data_energy_month` VALUES ('2', '2018-10-01 00:00:00', '1.2100', null, null, null, null, null, null, null, null, null, '2018-10-19 14:56:56');
INSERT INTO `data_energy_month` VALUES ('3', '2018-10-01 00:00:00', '0.8800', null, null, null, null, null, null, null, null, null, '2018-10-19 14:57:25');
INSERT INTO `data_energy_month` VALUES ('4', '2018-10-01 00:00:00', '0.6600', null, null, null, null, null, null, null, null, null, '2018-10-19 14:57:32');
INSERT INTO `data_energy_month` VALUES ('5', '2018-10-01 00:00:00', '0.7600', null, null, null, null, null, null, null, null, null, '2018-10-19 15:08:28');
INSERT INTO `data_energy_month` VALUES ('6', '2018-10-01 00:00:00', '1.1800', null, null, null, null, null, null, null, null, null, '2018-10-19 14:58:46');
INSERT INTO `data_energy_month` VALUES ('8', '2018-10-01 00:00:00', '0.9100', null, null, null, null, null, null, null, null, null, '2018-10-19 15:47:38');
INSERT INTO `data_energy_month` VALUES ('9', '2018-10-01 00:00:00', '1.1800', null, null, null, null, null, null, null, null, null, '2018-10-19 14:53:49');
INSERT INTO `data_energy_month` VALUES ('10', '2018-10-01 00:00:00', '0.8700', null, null, null, null, null, null, null, null, null, '2018-10-19 14:54:15');
INSERT INTO `data_energy_month` VALUES ('11', '2018-10-01 00:00:00', '1.3200', null, null, null, null, null, null, null, null, null, '2018-10-19 14:54:20');
INSERT INTO `data_energy_month` VALUES ('12', '2018-10-01 00:00:00', '0.7500', null, null, null, null, null, null, null, null, null, '2018-10-19 14:54:24');
INSERT INTO `data_energy_month` VALUES ('13', '2018-10-01 00:00:00', '0.6900', null, null, null, null, null, null, null, null, null, '2018-10-19 15:16:39');
INSERT INTO `data_energy_month` VALUES ('14', '2018-10-01 00:00:00', '0.6900', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:09');
INSERT INTO `data_energy_month` VALUES ('16', '2018-10-01 00:00:00', '0.7600', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:14');
INSERT INTO `data_energy_month` VALUES ('17', '2018-10-01 00:00:00', '0.6900', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:18');
INSERT INTO `data_energy_month` VALUES ('18', '2018-10-01 00:00:00', '0.6700', null, null, null, null, null, null, null, null, null, '2018-10-19 14:59:34');
INSERT INTO `data_energy_month` VALUES ('19', '2018-10-01 00:00:00', '3.6500', null, null, null, null, null, null, null, null, null, '2018-10-19 14:59:36');
INSERT INTO `data_energy_month` VALUES ('20', '2018-10-01 00:00:00', '1.0900', null, null, null, null, null, null, null, null, null, '2018-10-19 15:00:03');
INSERT INTO `data_energy_month` VALUES ('21', '2018-10-01 00:00:00', '0.7700', null, null, null, null, null, null, null, null, null, '2018-10-19 15:00:14');
INSERT INTO `data_energy_month` VALUES ('22', '2018-10-01 00:00:00', '1.1800', null, null, null, null, null, null, null, null, null, '2018-10-19 15:38:01');
INSERT INTO `data_energy_month` VALUES ('23', '2018-10-01 00:00:00', '0.8700', null, null, null, null, null, null, null, null, null, '2018-10-19 15:01:10');
INSERT INTO `data_energy_month` VALUES ('24', '2018-10-01 00:00:00', '1.1400', null, null, null, null, null, null, null, null, null, '2018-10-19 15:01:38');
INSERT INTO `data_energy_month` VALUES ('26', '2018-10-01 00:00:00', '0.7000', null, null, null, null, null, null, null, null, null, '2018-10-19 15:06:12');
INSERT INTO `data_energy_month` VALUES ('27', '2018-10-01 00:00:00', '0.6800', null, null, null, null, null, null, null, null, null, '2018-10-19 15:06:14');
INSERT INTO `data_energy_month` VALUES ('28', '2018-10-01 00:00:00', '0.6700', null, null, null, null, null, null, null, null, null, '2018-10-19 15:06:17');
INSERT INTO `data_energy_month` VALUES ('29', '2018-10-01 00:00:00', '0.7400', null, null, null, null, null, null, null, null, null, '2018-10-19 15:13:28');
INSERT INTO `data_energy_month` VALUES ('30', '2018-10-01 00:00:00', '0.0000', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:22');
INSERT INTO `data_energy_month` VALUES ('31', '2018-10-01 00:00:00', '0.7400', null, null, null, null, null, null, null, null, null, '2018-10-19 15:02:09');
INSERT INTO `data_energy_month` VALUES ('32', '2018-10-01 00:00:00', '0.9300', null, null, null, null, null, null, null, null, null, '2018-10-19 15:11:13');
INSERT INTO `data_energy_month` VALUES ('33', '2018-10-01 00:00:00', '0.8900', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:37');
INSERT INTO `data_energy_month` VALUES ('34', '2018-10-01 00:00:00', '0.7600', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:41');
INSERT INTO `data_energy_month` VALUES ('35', '2018-10-01 00:00:00', '0.7500', null, null, null, null, null, null, null, null, null, '2018-10-19 14:55:46');
INSERT INTO `data_energy_month` VALUES ('36', '2018-10-01 00:00:00', '0.8000', null, null, null, null, null, null, null, null, null, '2018-10-19 15:02:36');
INSERT INTO `data_energy_month` VALUES ('39', '2018-10-01 00:00:00', '0.7000', null, null, null, null, null, null, null, null, null, '2018-10-19 15:03:10');

-- ----------------------------
-- Table structure for data_event
-- ----------------------------
DROP TABLE IF EXISTS `data_event`;
CREATE TABLE `data_event` (
  `MeterID` int(8) NOT NULL,
  `EventID` int(3) NOT NULL,
  `OccurTime` datetime NOT NULL,
  `Content` varchar(200) DEFAULT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '1', 'Long Address', 'BCD_STRING,6,6:0,1', 'Long Address', null, '2018-07-24 19:22:12');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '2', 'Short Address', 'OCT_STRING,2,xxxx,1', 'Short Address', null, '2018-07-19 15:47:49');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '3', 'Channel', 'OCT_STRING,1,xx,1', 'Channel', null, '2018-06-30 13:59:59');
INSERT INTO `knl_fn` VALUES ('0', '0', '04', '4', 'Long Address,Short Address,Channel', 'STRUCT,3,BCD_STRING,6,6:0,1,OCT_STRING,2,xxxx,1,OCT_STRING,1,xx,1', 'Long Address,Short Address,Channel', null, '2018-07-24 19:22:41');
INSERT INTO `knl_fn` VALUES ('0', '0', '05', '6', 'Transfer Bytes', 'BYTE_ARRAY,1,xx,1', 'Transfer Bytes', '0', '2018-06-30 14:00:18');
INSERT INTO `knl_fn` VALUES ('0', '1', '02', '5', 'Event Status Words', 'BIT_STRING,4,1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1,1', 'Event Class', null, '2018-07-12 09:46:48');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '1', 'Short Address', 'OCT_STRING,2,xxxx,1', 'Short address', null, '2018-07-24 19:23:13');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '2', 'Long Address', 'BCD_STRING,6,6:0,1', 'Long Address', null, '2018-07-24 19:23:20');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '3', 'Channel', 'OCT_STRING,1,xx,1', 'Channel', '0', '2018-06-30 14:00:09');
INSERT INTO `knl_fn` VALUES ('0', '1', '03', '4', 'Long Address,Short Address,Channel', 'STRUCT,3,BCD_STRING,6,6:0,1,OCT_STRING,2,xxxx,1,OCT_STRING,1,xx,1', 'Long Address,Short Address,Channel', '0', '2018-07-24 19:23:49');
INSERT INTO `knl_fn` VALUES ('0', '1', '05', '6', 'Transfer Bytes', 'BYTE_ARRAY,1,xx,1', 'Transfer Bytes', '0', '2018-06-30 14:00:14');
INSERT INTO `knl_fn` VALUES ('81', '0', '11', 'C42A', 'Tamper Event ', null, 'Tamper Event', '1', '2018-10-18 10:55:24');
INSERT INTO `knl_fn` VALUES ('81', '1', '11', '9010', 'Current Active Energy', 'BCD_STRING,4,3:1,1', 'Current Active Energy', '1', '2018-10-18 10:55:28');
INSERT INTO `knl_fn` VALUES ('81', '1', '11', '901F', 'Current Active Energy', 'STRUCT,5,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1', 'Current Active Energy', '1', '2018-10-18 10:55:32');
INSERT INTO `knl_fn` VALUES ('81', '1', '11', '903F', 'Current Negative Energy', 'STRUCT,5,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1', 'Current Negative Energy', '1', '2018-10-18 10:55:35');
INSERT INTO `knl_fn` VALUES ('81', '1', '11', 'C42A', 'Tamper Event', 'ARRAY_645_EVENT,3,STRUCT,3,BCD_STRING,1,1:0,1,OCT_STRING,1,xx,1,DATE_TIME,6,yyMMddHHmmss,1', 'Tamper Event', '1', '2018-10-18 10:55:38');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '10', 'Meter Archive', 'ARRAY,2,STRUCT,10,OCT_STRING,3,xx,0,OCT_STRING,3,xx,0,BIT_STRING,1,3:5,0,OCT_STRING,1,xx,0,BCD_STRING,6,6:0,0,OCT_STRING,1,xx,0,BIT_STRING,1,4:2:2,0,BIT_STRING,1,4:4,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Meter Archive', null, '2018-07-24 19:27:24');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '200', 'Repeater Archive', 'ARRAY,2,STRUCT,7,OCT_STRING,3,xx,0,OCT_STRING,3,xx,0,BIT_STRING,1,3:5,0,OCT_STRING,1,xx,0,BCD_STRING,6,6:0,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Repeater Archive', '', '2018-10-10 10:47:07');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '3', 'IP Port APN', 'STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xx,0,ASCII,16,xxxx,1', 'IP Port APN', null, '2018-07-24 19:27:43');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '89', 'Address', 'ADDRESS,4,XXXX,0', 'Address', null, '2018-07-10 19:50:55');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '90', 'Location', 'STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Location', null, '2018-07-10 19:50:46');
INSERT INTO `knl_fn` VALUES ('100', '0', '04', '91', 'Channel', 'OCT_STRING,1,xx,0', 'Channel', null, '2018-07-24 19:27:54');
INSERT INTO `knl_fn` VALUES ('100', '0', '0A', '10', 'Meter Archive', 'ARRAY,2,OCT_STRING,3,%s,0', 'Meter Archive', null, '2018-07-24 20:11:19');
INSERT INTO `knl_fn` VALUES ('100', '0', '0A', '200', 'Repeater Archive', 'ARRAY,2,OCT_STRING,3,%s,0', 'Repeater Archive', '', '2018-10-10 10:47:15');
INSERT INTO `knl_fn` VALUES ('100', '0', '0D', '177', 'Month Frozen Active', 'DATE_TIME,2,MMyy,1', 'Month Freeze', null, '2018-07-25 11:49:53');
INSERT INTO `knl_fn` VALUES ('100', '0', '0D', '178', 'Month Frozen Negative', 'DATE_TIME,2,MMyy,1', 'Month Freeze', '', '2018-07-25 11:50:03');
INSERT INTO `knl_fn` VALUES ('100', '0', '0E', '1', 'Event Log', 'STRUCT,2,OCT_STRING,2,xx,0,OCT_STRING,2,xx,0', 'Event Log', '', '2018-07-24 19:31:56');
INSERT INTO `knl_fn` VALUES ('100', '0', '10', '1', 'Transfer Data', 'BYTE_ARRAY,2,xx,1', 'Transfer Data', null, '2018-10-10 16:06:59');
INSERT INTO `knl_fn` VALUES ('100', '1', '02', '1', 'Login', null, 'Login', null, '2018-06-30 13:44:47');
INSERT INTO `knl_fn` VALUES ('100', '1', '02', '3', 'Heartbeat', 'DATE_TIME_W,6,xxxx,1', 'Heartbeat', null, '2018-07-11 15:46:31');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '10', 'Meter Archive', 'ARRAY,2,STRUCT,10,OCT_STRING,3,xx,0,OCT_STRING,3,xx,0,BIT_STRING,1,3:5,0,OCT_STRING,1,xx,0,BCD_STRING,6,6:0,0,OCT_STRING,1,xx,0,BIT_STRING,1,4:2:2,0,BIT_STRING,1,4:4,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Meter Archive', null, '2018-07-24 19:29:59');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '200', 'Repeater Archive', 'ARRAY,2,STRUCT,7,OCT_STRING,3,xx,0,OCT_STRING,3,xx,0,BIT_STRING,1,3:5,0,OCT_STRING,1,xx,0,BCD_STRING,6,6:0,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Repeater Archive', '', '2018-10-10 10:47:09');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '3', 'IP Port APN', 'STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xx,0,ASCII,16,xxxx,1', 'IP Port APN', null, '2018-07-24 19:30:07');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '89', 'Address', 'ADDRESS,4,XXXX,0', 'Address', null, '2018-07-10 19:51:24');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '90', 'Location', 'STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0', 'Location', null, '2018-07-10 19:51:31');
INSERT INTO `knl_fn` VALUES ('100', '1', '0A', '91', 'Channel', 'OCT_STRING,1,xx,0', 'Channel', null, '2018-07-24 19:31:08');
INSERT INTO `knl_fn` VALUES ('100', '1', '0D', '177', 'Month Frozen Active', 'STRUCT,8,DATE_TIME,2,MMyy,1,DATE_TIME,5,mmHHddMMyy,1,OCT_STRING,1,xx,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0', 'Month Freeze', null, '2018-07-25 11:49:55');
INSERT INTO `knl_fn` VALUES ('100', '1', '0D', '178', 'Month Frozen Negative', 'STRUCT,8,DATE_TIME,2,MMyy,1,DATE_TIME,5,mmHHddMMyy,1,OCT_STRING,1,xx,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0', 'Month Freeze', '', '2018-07-26 10:32:44');
INSERT INTO `knl_fn` VALUES ('100', '1', '0E', '1', 'Event Log', 'ARRAY,2,STRUCT,4,OCT_STRING,1,xx,0,BCD_STRING,6,6:0,0,DATE_TIME,6,ssmmHHddMMyy,1,OCT_STRING,16,xx,0', 'Event Log', '', '2018-07-24 19:33:13');
INSERT INTO `knl_fn` VALUES ('100', '1', '10', '1', 'Transfer Data', 'STRUCT,2,OCT_STRING,1,xx,0,BYTE_ARRAY,2,xx,1', null, null, '2018-10-10 16:07:13');

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
  `Latitude` double(16,10) DEFAULT NULL,
  `Longitude` double(16,10) DEFAULT NULL,
  `Channel` int(2) DEFAULT NULL,
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
INSERT INTO `knl_lbs` VALUES ('0', '00000006', '100', '00000006', '0000', '0', '30.3132490000', '120.0661100000', '0', '10.157.1.238', '9012', '10.157.1.238', '9012', 'CMNET\0\0\0\0\0\0\0\0\0\0\0', '2018-04-25 19:39:42', '2018-10-15 16:37:18');

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
  `NodeID` int(8) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime DEFAULT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `Route` varchar(255) NOT NULL,
  `SignalStrength` int(4) DEFAULT '0',
  `Result` int(1) NOT NULL,
  `SaveTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `RelayID` int(8) DEFAULT NULL,
  PRIMARY KEY (`NodeID`,`StartTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_network
-- ----------------------------
INSERT INTO `log_network` VALUES ('2', '2018-10-19 14:56:07', '2018-10-19 14:56:12', '980799600059', '00000006,980799600060,980799600059', '77', '1', '2018-10-19 14:56:11', '7');
INSERT INTO `log_network` VALUES ('3', '2018-10-19 14:56:12', '2018-10-19 14:56:16', '980799600013', '00000006,980799600060,980799600013', '82', '1', '2018-10-19 14:56:15', '7');
INSERT INTO `log_network` VALUES ('4', '2018-10-19 14:56:16', '2018-10-19 14:56:20', '980799600010', '00000006,980799600060,980799600010', '73', '1', '2018-10-19 14:56:19', '7');
INSERT INTO `log_network` VALUES ('5', '2018-10-19 14:55:20', '2018-10-19 14:55:34', '980799600014', '00000006,980799600014', '60', '1', '2018-10-19 14:55:34', '0');
INSERT INTO `log_network` VALUES ('6', '2018-10-19 14:55:35', '2018-10-19 14:55:39', '980799600067', '00000006,980799600067', '92', '1', '2018-10-19 14:55:38', '0');
INSERT INTO `log_network` VALUES ('7', '2018-10-19 14:55:39', '2018-10-19 14:55:43', '980799600060', '00000006,980799600060', '77', '1', '2018-10-19 14:55:43', '0');
INSERT INTO `log_network` VALUES ('8', '2018-10-19 14:55:43', '2018-10-19 14:55:56', '980799600048', '00000006,980799600048', '51', '1', '2018-10-19 14:55:55', '0');
INSERT INTO `log_network` VALUES ('9', '2018-10-19 14:52:49', '2018-10-19 14:52:51', '980799600043', '00000006,980799600043', '92', '1', '2018-10-19 14:52:51', '0');
INSERT INTO `log_network` VALUES ('10', '2018-10-19 14:52:52', '2018-10-19 14:52:54', '980799600012', '00000006,980799600012', '80', '1', '2018-10-19 14:52:53', '0');
INSERT INTO `log_network` VALUES ('11', '2018-10-19 14:52:54', '2018-10-19 14:52:56', '980799600066', '00000006,980799600066', '89', '1', '2018-10-19 14:52:55', '0');
INSERT INTO `log_network` VALUES ('12', '2018-10-19 14:52:56', '2018-10-19 14:52:58', '980799600064', '00000006,980799600064', '97', '1', '2018-10-19 14:52:57', '0');
INSERT INTO `log_network` VALUES ('13', '2018-10-19 14:52:58', '2018-10-19 14:53:10', '980799600006', '00000006,980799600006', '63', '1', '2018-10-19 14:53:10', '0');
INSERT INTO `log_network` VALUES ('14', '2018-10-19 14:53:10', '2018-10-19 14:53:22', '980799600009', '00000006,980799600009', '92', '1', '2018-10-19 14:53:22', '0');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:32:37', '2018-10-19 16:33:06', '980799600058', '00000006,980799600058', null, '0', '2018-10-19 16:33:06', '0');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:33:06', '2018-10-19 16:33:30', '980799600058', '00000006,980799600058', null, '0', '2018-10-19 16:33:30', '0');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:33:37', '2018-10-19 16:34:01', '980799600058', '00000006,980799600058', null, '0', '2018-10-19 16:34:00', '0');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:34:01', '2018-10-19 16:34:25', '980799600058', '00000006,980799600058', null, '0', '2018-10-19 16:34:25', '0');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:36:37', '2018-10-19 16:36:47', '980799600058', '00000006,980799600060,980799600058', null, '0', '2018-10-19 16:36:46', '7');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:36:47', '2018-10-19 16:36:57', '980799600058', '00000006,980799600060,980799600058', null, '0', '2018-10-19 16:36:57', '7');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:39:37', '2018-10-19 16:39:47', '980799600058', '00000006,980799600064,980799600058', null, '0', '2018-10-19 16:39:46', '12');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:39:47', '2018-10-19 16:39:57', '980799600058', '00000006,980799600064,980799600058', null, '0', '2018-10-19 16:39:57', '12');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:40:37', '2018-10-19 16:40:47', '980799600058', '00000006,980799600006,980799600058', null, '0', '2018-10-19 16:40:46', '13');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:40:47', '2018-10-19 16:40:57', '980799600058', '00000006,980799600006,980799600058', null, '0', '2018-10-19 16:40:57', '13');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:43:37', '2018-10-19 16:43:47', '980799600058', '00000006,980799600009,980799600058', null, '0', '2018-10-19 16:43:46', '14');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:43:47', '2018-10-19 16:43:57', '980799600058', '00000006,980799600009,980799600058', null, '0', '2018-10-19 16:43:57', '14');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:46:37', '2018-10-19 16:46:47', '980799600058', '00000006,980799600043,980799600058', null, '0', '2018-10-19 16:46:46', '9');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:46:47', '2018-10-19 16:46:57', '980799600058', '00000006,980799600043,980799600058', null, '0', '2018-10-19 16:46:57', '9');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:47:37', '2018-10-19 16:47:47', '980799600058', '00000006,980799600012,980799600058', null, '0', '2018-10-19 16:47:46', '10');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:47:47', '2018-10-19 16:47:58', '980799600058', '00000006,980799600012,980799600058', null, '0', '2018-10-19 16:47:58', '10');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:50:37', '2018-10-19 16:50:47', '980799600058', '00000006,980799600066,980799600058', null, '0', '2018-10-19 16:50:46', '11');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:50:47', '2018-10-19 16:50:57', '980799600058', '00000006,980799600066,980799600058', null, '0', '2018-10-19 16:50:57', '11');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:52:37', '2018-10-19 16:52:47', '980799600058', '00000006,980799600014,980799600058', null, '0', '2018-10-19 16:52:46', '5');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:52:47', '2018-10-19 16:52:57', '980799600058', '00000006,980799600014,980799600058', null, '0', '2018-10-19 16:52:57', '5');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:53:37', '2018-10-19 16:53:47', '980799600058', '00000006,980799600067,980799600058', null, '0', '2018-10-19 16:53:46', '6');
INSERT INTO `log_network` VALUES ('15', '2018-10-19 16:53:47', '2018-10-19 16:53:57', '980799600058', '00000006,980799600067,980799600058', null, '0', '2018-10-19 16:53:57', '6');
INSERT INTO `log_network` VALUES ('16', '2018-10-19 14:52:37', '2018-10-19 14:52:38', '980799600045', '00000006,980799600045', '88', '1', '2018-10-19 14:52:38', '0');
INSERT INTO `log_network` VALUES ('17', '2018-10-19 14:52:38', '2018-10-19 14:52:40', '980799600025', '00000006,980799600025', '104', '1', '2018-10-19 14:52:40', '0');
INSERT INTO `log_network` VALUES ('18', '2018-10-19 14:54:17', '2018-10-19 14:54:22', '980799600003', '00000006,980799600003', '80', '1', '2018-10-19 14:54:21', '0');
INSERT INTO `log_network` VALUES ('19', '2018-10-19 14:54:22', '2018-10-19 14:54:26', '980799600053', '00000006,980799600053', '82', '1', '2018-10-19 14:54:26', '0');
INSERT INTO `log_network` VALUES ('20', '2018-10-19 14:54:14', '2018-10-19 14:54:17', '980799600040', '00000006,980799600040', '91', '1', '2018-10-19 14:54:17', '0');
INSERT INTO `log_network` VALUES ('21', '2018-10-19 14:54:26', '2018-10-19 14:54:38', '980799600057', '00000006,980799600057', '86', '1', '2018-10-19 14:54:38', '0');
INSERT INTO `log_network` VALUES ('22', '2018-10-19 14:54:39', '2018-10-19 14:54:57', '980799600051', '00000006,980799600051', '54', '1', '2018-10-19 14:54:56', '0');
INSERT INTO `log_network` VALUES ('23', '2018-10-19 14:54:57', '2018-10-19 14:55:11', '980799600050', '00000006,980799600050', '83', '1', '2018-10-19 14:55:11', '0');
INSERT INTO `log_network` VALUES ('24', '2018-10-19 14:55:11', '2018-10-19 14:55:16', '980799600055', '00000006,980799600055', '79', '1', '2018-10-19 14:55:15', '0');
INSERT INTO `log_network` VALUES ('25', '2018-10-19 16:53:57', '2018-10-19 16:54:22', '980799600046', '00000006,980799600046', null, '0', '2018-10-19 16:54:21', '0');
INSERT INTO `log_network` VALUES ('25', '2018-10-19 16:54:22', '2018-10-19 16:54:46', '980799600046', '00000006,980799600046', null, '0', '2018-10-19 16:54:45', '0');
INSERT INTO `log_network` VALUES ('26', '2018-10-19 14:57:21', '2018-10-19 14:57:27', '980799600026', '00000006,980799600026', '66', '1', '2018-10-19 14:57:26', '0');
INSERT INTO `log_network` VALUES ('27', '2018-10-19 14:58:48', '2018-10-19 14:59:11', '980799600016', '00000006,980799600016', '64', '1', '2018-10-19 14:59:10', '0');
INSERT INTO `log_network` VALUES ('28', '2018-10-19 14:58:31', '2018-10-19 14:58:44', '980799600038', '00000006,980799600038', '65', '1', '2018-10-19 14:58:43', '0');
INSERT INTO `log_network` VALUES ('29', '2018-10-19 14:58:44', '2018-10-19 14:58:48', '980799600027', '00000006,980799600027', '52', '1', '2018-10-19 14:58:48', '0');
INSERT INTO `log_network` VALUES ('30', '2018-10-19 14:52:41', '2018-10-19 14:52:43', '000000000014', '00000006,000000000014', '107', '1', '2018-10-19 14:52:42', '0');
INSERT INTO `log_network` VALUES ('31', '2018-10-19 14:56:20', '2018-10-19 14:56:24', '980799600033', '00000006,980799600060,980799600033', '67', '1', '2018-10-19 14:56:23', '7');
INSERT INTO `log_network` VALUES ('32', '2018-10-19 14:55:56', '2018-10-19 14:56:00', '980799600063', '00000006,980799600060,980799600063', '45', '0', '2018-10-19 14:55:59', '7');
INSERT INTO `log_network` VALUES ('32', '2018-10-19 14:56:00', '2018-10-19 14:56:07', '980799600063', '00000006,980799600060,980799600063', '45', '0', '2018-10-19 14:56:06', '7');
INSERT INTO `log_network` VALUES ('32', '2018-10-19 15:00:39', '2018-10-19 15:00:59', '980799600063', '00000006,980799600060,980799600063', null, '0', '2018-10-19 15:00:59', '7');
INSERT INTO `log_network` VALUES ('32', '2018-10-19 15:00:59', '2018-10-19 15:01:08', '980799600063', '00000006,980799600060,980799600063', '46', '0', '2018-10-19 15:01:08', '7');
INSERT INTO `log_network` VALUES ('32', '2018-10-19 15:05:08', '2018-10-19 15:05:39', '980799600063', '00000006,980799600060,980799600059,980799600063', '68', '1', '2018-10-19 15:05:38', '2');
INSERT INTO `log_network` VALUES ('33', '2018-10-19 14:52:43', '2018-10-19 14:52:45', '980799600011', '00000006,980799600011', '101', '1', '2018-10-19 14:52:44', '0');
INSERT INTO `log_network` VALUES ('34', '2018-10-19 14:52:45', '2018-10-19 14:52:47', '980799600007', '00000006,980799600007', '97', '1', '2018-10-19 14:52:46', '0');
INSERT INTO `log_network` VALUES ('35', '2018-10-19 14:52:47', '2018-10-19 14:52:49', '980799600061', '00000006,980799600061', '100', '1', '2018-10-19 14:52:49', '0');
INSERT INTO `log_network` VALUES ('36', '2018-10-19 14:55:16', '2018-10-19 14:55:20', '980799600052', '00000006,980799600052', '74', '1', '2018-10-19 14:55:19', '0');
INSERT INTO `log_network` VALUES ('37', '2018-10-19 16:54:46', '2018-10-19 16:55:10', '980799600075', '00000006,980799600075', null, '0', '2018-10-19 16:55:10', '0');
INSERT INTO `log_network` VALUES ('39', '2018-10-19 14:56:24', '2018-10-19 14:56:28', '980799600004', '00000006,980799600060,980799600004', '70', '1', '2018-10-19 14:56:27', '7');

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
  `DeviceClass` int(1) DEFAULT '1',
  `Status` int(2) NOT NULL DEFAULT '-1',
  `Protocol` int(2) DEFAULT NULL,
  `ModuleProtocol` int(2) DEFAULT '0',
  `Baudrate` int(8) DEFAULT NULL,
  `Port` int(2) DEFAULT NULL,
  `TariffCount` int(2) DEFAULT NULL,
  `IntegerCount` int(2) DEFAULT NULL,
  `DecimalCount` int(2) DEFAULT NULL,
  `CustomerClass` int(3) DEFAULT NULL,
  `CustomerSubClass` int(3) DEFAULT NULL,
  `Longitude` double(16,10) NOT NULL,
  `Latitude` double(16,10) NOT NULL,
  `Distance` double(10,2) DEFAULT NULL,
  `Angle` float(6,3) DEFAULT NULL,
  `Sector` int(3) DEFAULT NULL,
  `DistrictX` int(1) DEFAULT NULL,
  `DistrictY` int(4) DEFAULT NULL,
  `Grade` int(4) DEFAULT '1',
  `Parent` int(8) DEFAULT '0',
  `Path` varchar(255) DEFAULT '0/',
  `Route` varchar(255) DEFAULT NULL,
  `Relay` int(1) DEFAULT '0',
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REGOn` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of prm_meter
-- ----------------------------

-- ----------------------------
-- Table structure for prm_node
-- ----------------------------
DROP TABLE IF EXISTS `prm_node`;
CREATE TABLE `prm_node` (
  `ID` int(8) unsigned NOT NULL,
  `PointNo` int(8) unsigned NOT NULL,
  `CommAddr` varchar(20) NOT NULL,
  `DeviceClass` int(1) NOT NULL DEFAULT '0' COMMENT '0:repeater;1:meter',
  `Status` int(2) NOT NULL DEFAULT '-1',
  `Protocol` int(2) DEFAULT NULL,
  `ModuleProtocol` int(2) DEFAULT '0',
  `Baudrate` int(8) DEFAULT NULL,
  `Port` int(2) DEFAULT NULL,
  `TariffCount` int(2) DEFAULT NULL,
  `DecimalCount` int(2) DEFAULT NULL,
  `IntegerCount` int(2) DEFAULT NULL,
  `CustomerClass` int(3) DEFAULT NULL,
  `CustomerSubClass` int(3) DEFAULT NULL,
  `Latitude` double(16,10) NOT NULL,
  `Longitude` double(16,10) NOT NULL COMMENT 'NO',
  `Distance` double(10,2) DEFAULT NULL,
  `Angle` float(6,3) DEFAULT NULL,
  `Sector` int(3) DEFAULT NULL,
  `DistrictX` int(1) DEFAULT NULL,
  `DistrictY` int(4) DEFAULT NULL,
  `Grade` int(4) DEFAULT '1',
  `Parent` int(8) DEFAULT '0',
  `Path` varchar(255) DEFAULT NULL,
  `Route` varchar(255) DEFAULT NULL,
  `Relay` int(1) DEFAULT '0' COMMENT '0:not;1:secondary;2:primary',
  `CRTOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDFOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REGOn` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of prm_node
-- ----------------------------
INSERT INTO `prm_node` VALUES ('2', '2', '980799600059', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3365160000', '120.0295960000', '4361.00', '152.997', '6', '43', '0', '2', '7', '0/7/2', '00000006,980799600060,980799600059', '0', '2018-10-18 13:52:23', '2018-10-19 14:56:11', '2018-10-19 14:56:11');
INSERT INTO `prm_node` VALUES ('3', '3', '980799600013', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3365160000', '120.0295960000', '4361.00', '152.997', '6', '43', '0', '2', '7', '0/7/3', '00000006,980799600060,980799600013', '0', '2018-10-18 13:52:36', '2018-10-19 14:56:15', '2018-10-19 14:56:15');
INSERT INTO `prm_node` VALUES ('4', '4', '980799600010', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3365160000', '120.0295960000', '4361.00', '152.997', '6', '43', '0', '2', '7', '0/7/4', '00000006,980799600060,980799600010', '0', '2018-10-18 13:52:36', '2018-10-19 14:56:19', '2018-10-19 14:56:19');
INSERT INTO `prm_node` VALUES ('5', '5', '980799600014', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3264940000', '120.0272060000', '4018.00', '164.779', '6', '40', '1', '1', '0', '0/5', '00000006,980799600014', '0', '2018-10-18 14:08:07', '2018-10-19 14:55:34', '2018-10-19 14:55:34');
INSERT INTO `prm_node` VALUES ('6', '6', '980799600067', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3264940000', '120.0272060000', '4018.00', '164.779', '6', '40', '1', '1', '0', '0/6', '00000006,980799600067', '0', '2018-10-18 14:08:07', '2018-10-19 14:55:38', '2018-10-19 14:55:38');
INSERT INTO `prm_node` VALUES ('7', '7', '980799600060', '0', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3264940000', '120.0272060000', '4018.00', '164.779', '6', '40', '1', '1', '0', '0/7', '00000006,980799600060', '2', '2018-10-18 14:08:07', '2018-10-19 14:55:43', '2018-10-19 14:55:43');
INSERT INTO `prm_node` VALUES ('8', '8', '980799600048', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3264940000', '120.0272060000', '4018.00', '164.779', '6', '40', '1', '1', '0', '0/8', '00000006,980799600048', '0', '2018-10-18 14:08:07', '2018-10-19 14:55:55', '2018-10-19 14:55:55');
INSERT INTO `prm_node` VALUES ('9', '9', '980799600043', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3260260000', '120.0583780000', '1604.00', '127.700', '5', '16', '0', '1', '0', '0/9', '00000006,980799600043', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:51', '2018-10-19 14:52:51');
INSERT INTO `prm_node` VALUES ('10', '10', '980799600012', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3260260000', '120.0583780000', '1604.00', '127.700', '5', '16', '0', '1', '0', '0/10', '00000006,980799600012', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:53', '2018-10-19 14:52:53');
INSERT INTO `prm_node` VALUES ('11', '11', '980799600066', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3260260000', '120.0583780000', '1604.00', '127.700', '5', '16', '0', '1', '0', '0/11', '00000006,980799600066', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:55', '2018-10-19 14:52:55');
INSERT INTO `prm_node` VALUES ('12', '12', '980799600064', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3198710000', '120.0409480000', '2527.00', '168.253', '6', '25', '1', '1', '0', '0/12', '00000006,980799600064', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:57', '2018-10-19 14:52:57');
INSERT INTO `prm_node` VALUES ('13', '13', '980799600006', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3198710000', '120.0409480000', '2527.00', '168.253', '6', '25', '1', '1', '0', '0/13', '00000006,980799600006', '0', '2018-10-18 14:08:08', '2018-10-19 14:53:10', '2018-10-19 14:53:10');
INSERT INTO `prm_node` VALUES ('14', '14', '980799600009', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3198710000', '120.0409480000', '2527.00', '168.253', '6', '25', '1', '1', '0', '0/14', '00000006,980799600009', '0', '2018-10-18 14:08:08', '2018-10-19 14:53:22', '2018-10-19 14:53:22');
INSERT INTO `prm_node` VALUES ('15', '15', '980799600058', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3198710000', '120.0409480000', '2527.00', '168.253', '6', '25', '1', '1', '0', null, null, '0', '2018-10-18 14:08:08', '2018-10-19 14:54:13', null);
INSERT INTO `prm_node` VALUES ('16', '16', '980799600045', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3182220000', '120.0706680000', '705.00', '40.039', '2', '7', '1', '1', '0', '0/16', '00000006,980799600045', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:38', '2018-10-19 14:52:38');
INSERT INTO `prm_node` VALUES ('17', '17', '980799600025', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3182220000', '120.0706680000', '705.00', '40.039', '2', '7', '1', '1', '0', '0/17', '00000006,980799600025', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:40', '2018-10-19 14:52:40');
INSERT INTO `prm_node` VALUES ('18', '18', '980799600003', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3143200000', '120.0924590000', '2534.00', '1.763', '1', '25', '0', '1', '0', '0/18', '00000006,980799600003', '0', '2018-10-18 14:08:08', '2018-10-19 14:54:21', '2018-10-19 14:54:21');
INSERT INTO `prm_node` VALUES ('19', '19', '980799600053', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3143200000', '120.0924590000', '2534.00', '1.763', '1', '25', '0', '1', '0', '0/19', '00000006,980799600053', '0', '2018-10-18 14:08:08', '2018-10-19 14:54:26', '2018-10-19 14:54:26');
INSERT INTO `prm_node` VALUES ('20', '20', '980799600040', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3143200000', '120.0924590000', '2534.00', '1.763', '1', '25', '0', '1', '0', '0/20', '00000006,980799600040', '0', '2018-10-18 14:08:08', '2018-10-19 14:54:17', '2018-10-19 14:54:17');
INSERT INTO `prm_node` VALUES ('21', '21', '980799600057', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3143200000', '120.0924590000', '2534.00', '1.763', '1', '25', '0', '1', '0', '0/21', '00000006,980799600057', '0', '2018-10-18 14:08:08', '2018-10-19 14:54:38', '2018-10-19 14:54:38');
INSERT INTO `prm_node` VALUES ('22', '22', '980799600051', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3213110000', '120.1001850000', '3395.00', '10.086', '1', '33', '1', '1', '0', '0/22', '00000006,980799600051', '0', '2018-10-18 14:08:08', '2018-10-19 14:54:56', '2018-10-19 14:54:56');
INSERT INTO `prm_node` VALUES ('23', '23', '980799600050', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3213110000', '120.1001850000', '3395.00', '10.086', '1', '33', '1', '1', '0', '0/23', '00000006,980799600050', '0', '2018-10-18 14:08:08', '2018-10-19 14:55:11', '2018-10-19 14:55:11');
INSERT INTO `prm_node` VALUES ('24', '24', '980799600055', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3213110000', '120.1001850000', '3395.00', '10.086', '1', '33', '1', '1', '0', '0/24', '00000006,980799600055', '0', '2018-10-18 14:08:08', '2018-10-19 14:55:15', '2018-10-19 14:55:15');
INSERT INTO `prm_node` VALUES ('25', '25', '980799600046', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3251770000', '120.1155280000', '4930.00', '10.149', '1', '49', '1', '1', '0', null, null, '0', '2018-10-18 14:08:08', '2018-10-19 14:57:20', null);
INSERT INTO `prm_node` VALUES ('26', '26', '980799600026', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3251770000', '120.1155280000', '4930.00', '10.149', '1', '49', '1', '1', '0', '0/26', '00000006,980799600026', '0', '2018-10-18 14:08:08', '2018-10-19 14:57:26', '2018-10-19 14:57:26');
INSERT INTO `prm_node` VALUES ('27', '27', '980799600016', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3368660000', '120.1161570000', '5480.00', '19.242', '1', '54', '1', '1', '0', '0/27', '00000006,980799600016', '0', '2018-10-18 14:08:08', '2018-10-19 14:59:10', '2018-10-19 14:59:10');
INSERT INTO `prm_node` VALUES ('28', '28', '980799600038', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3368660000', '120.1161570000', '5480.00', '19.242', '1', '54', '1', '1', '0', '0/28', '00000006,980799600038', '0', '2018-10-18 14:08:08', '2018-10-19 14:58:43', '2018-10-19 14:58:43');
INSERT INTO `prm_node` VALUES ('29', '29', '980799600027', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3368660000', '120.1161570000', '5480.00', '19.242', '1', '54', '1', '1', '0', '0/29', '00000006,980799600027', '0', '2018-10-18 14:08:08', '2018-10-19 14:58:48', '2018-10-19 14:58:48');
INSERT INTO `prm_node` VALUES ('30', '30', '000000000014', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3182220000', '120.0706680000', '705.00', '40.039', '2', '7', '1', '1', '0', '0/30', '00000006,000000000014', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:42', '2018-10-19 14:52:42');
INSERT INTO `prm_node` VALUES ('31', '31', '980799600033', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3365160000', '120.0295960000', '4361.00', '152.997', '6', '43', '0', '2', '7', '0/7/31', '00000006,980799600060,980799600033', '0', '2018-10-18 14:08:08', '2018-10-19 14:56:23', '2018-10-19 14:56:23');
INSERT INTO `prm_node` VALUES ('32', '32', '980799600063', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3365160000', '120.0295960000', '4361.00', '152.997', '6', '43', '0', '3', '2', '0/7/2/32', '00000006,980799600060,980799600059,980799600063', '0', '2018-10-18 14:08:08', '2018-10-19 15:05:38', '2018-10-19 15:05:38');
INSERT INTO `prm_node` VALUES ('33', '33', '980799600011', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3182220000', '120.0706680000', '705.00', '40.039', '2', '7', '1', '1', '0', '0/33', '00000006,980799600011', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:44', '2018-10-19 14:52:44');
INSERT INTO `prm_node` VALUES ('34', '34', '980799600007', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3182220000', '120.0706680000', '705.00', '40.039', '2', '7', '1', '1', '0', '0/34', '00000006,980799600007', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:46', '2018-10-19 14:52:46');
INSERT INTO `prm_node` VALUES ('35', '35', '980799600061', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3182220000', '120.0706680000', '705.00', '40.039', '2', '7', '1', '1', '0', '0/35', '00000006,980799600061', '0', '2018-10-18 14:08:08', '2018-10-19 14:52:49', '2018-10-19 14:52:49');
INSERT INTO `prm_node` VALUES ('36', '36', '980799600052', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3213110000', '120.1001850000', '3395.00', '10.086', '1', '33', '1', '1', '0', '0/36', '00000006,980799600052', '0', '2018-10-18 14:08:08', '2018-10-19 14:55:19', '2018-10-19 14:55:19');
INSERT INTO `prm_node` VALUES ('37', '37', '980799600075', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3251770000', '120.1155280000', '4930.00', '10.149', '1', '49', '1', '1', '0', null, null, '0', '2018-10-18 14:08:08', '2018-10-19 14:58:30', null);
INSERT INTO `prm_node` VALUES ('39', '39', '980799600004', '1', '1', '81', '0', '1', '20', '4', '2', '6', '5', '1', '30.3365160000', '120.0295960000', '4361.00', '152.997', '6', '43', '0', '2', '7', '0/7/39', '00000006,980799600060,980799600004', '0', '2018-10-18 13:51:42', '2018-10-19 14:56:27', '2018-10-19 14:56:27');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of run_param
-- ----------------------------
INSERT INTO `run_param` VALUES ('2', 'LastReportEventIndex', '0', 'Last Report Event Index', '2018-10-10 09:48:55');
INSERT INTO `run_param` VALUES ('3', 'LoraSignalThreshold', '50', 'Lora Signal Threshold', '2018-07-19 11:38:37');
INSERT INTO `run_param` VALUES ('4', 'LoraCOM', 'COM3', 'Lora COM', '2018-07-23 10:49:00');
