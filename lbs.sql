/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lbs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-15 15:08:39
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
INSERT INTO `knl_fn` VALUES ('30', '0', '11', 'C42A', 'Tamper Event ', null, 'Tamper Event', '1', '2018-07-24 16:15:33');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', '9010', 'Current Active Energy', 'BCD_STRING,4,3:1,1', 'Current Active Energy', '1', '2018-07-24 19:24:18');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', '901F', 'Current Active Energy', 'STRUCT,5,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1', 'Current Active Energy', '1', '2018-07-24 19:26:09');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', '903F', 'Current Negative Energy', 'STRUCT,5,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1,BCD_STRING,4,3:1,1', 'Current Negative Energy', '1', '2018-07-24 19:26:05');
INSERT INTO `knl_fn` VALUES ('30', '1', '11', 'C42A', 'Tamper Event', 'ARRAY_645_EVENT,3,STRUCT,3,BCD_STRING,1,1:0,1,OCT_STRING,1,xx,1,DATE_TIME,6,yyMMddHHmmss,1', 'Tamper Event', '1', '2018-07-24 19:26:21');
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
  `Longitude` double(16,10) DEFAULT NULL,
  `Latitude` double(16,10) DEFAULT NULL,
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
INSERT INTO `knl_lbs` VALUES ('0', '00000006', '100', '00000006', '0000', '0', '120.0001000000', '30.0010000000', '0', '10.157.1.238', '9012', '10.157.1.238', '9012', 'CMNET\0\0\0\0\0\0\0\0\0\0\0', '2018-04-25 19:39:42', '2018-10-10 10:34:30');

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
INSERT INTO `log_network` VALUES ('1', '2018-10-15 14:52:51', '2018-10-15 14:53:25', '000000000014', '00000006,000000000014', null, '0', '2018-10-15 14:53:25', '0');
INSERT INTO `log_network` VALUES ('1', '2018-10-15 14:53:25', '2018-10-15 14:53:49', '000000000014', '00000006,000000000014', null, '0', '2018-10-15 14:53:49', '0');
INSERT INTO `log_network` VALUES ('1', '2018-10-15 15:03:52', '2018-10-15 15:04:26', '000000000014', '00000006,000000000014', null, '0', '2018-10-15 15:04:25', '0');
INSERT INTO `log_network` VALUES ('1', '2018-10-15 15:04:26', '2018-10-15 15:04:50', '000000000014', '00000006,000000000014', null, '0', '2018-10-15 15:04:50', '0');
INSERT INTO `log_network` VALUES ('2', '2018-10-15 14:53:50', '2018-10-15 14:54:14', '000090018348', '00000006,000090018348', null, '0', '2018-10-15 14:54:13', '0');
INSERT INTO `log_network` VALUES ('2', '2018-10-15 14:54:14', '2018-10-15 14:54:38', '000090018348', '00000006,000090018348', null, '0', '2018-10-15 14:54:38', '0');
INSERT INTO `log_network` VALUES ('2', '2018-10-15 15:04:50', '2018-10-15 15:05:15', '000090018348', '00000006,000090018348', null, '0', '2018-10-15 15:05:14', '0');
INSERT INTO `log_network` VALUES ('2', '2018-10-15 15:05:15', '2018-10-15 15:05:39', '000090018348', '00000006,000090018348', null, '0', '2018-10-15 15:05:38', '0');
INSERT INTO `log_network` VALUES ('3', '2018-10-15 14:54:39', '2018-10-15 14:55:03', '000090011673', '00000006,000090011673', null, '0', '2018-10-15 14:55:02', '0');
INSERT INTO `log_network` VALUES ('3', '2018-10-15 14:55:03', '2018-10-15 14:55:37', '000090011673', '00000006,000090011673', null, '0', '2018-10-15 14:55:36', '0');
INSERT INTO `log_network` VALUES ('3', '2018-10-15 15:05:39', '2018-10-15 15:06:03', '000090011673', '00000006,000090011673', null, '0', '2018-10-15 15:06:03', '0');
INSERT INTO `log_network` VALUES ('3', '2018-10-15 15:06:03', '2018-10-15 15:06:38', '000090011673', '00000006,000090011673', null, '0', '2018-10-15 15:06:37', '0');
INSERT INTO `log_network` VALUES ('4', '2018-10-15 14:55:37', '2018-10-15 14:56:02', '000090018352', '00000006,000090018352', null, '0', '2018-10-15 14:56:01', '0');
INSERT INTO `log_network` VALUES ('4', '2018-10-15 14:56:02', '2018-10-15 14:56:26', '000090018352', '00000006,000090018352', null, '0', '2018-10-15 14:56:25', '0');
INSERT INTO `log_network` VALUES ('4', '2018-10-15 15:06:38', '2018-10-15 15:07:02', '000090018352', '00000006,000090018352', null, '0', '2018-10-15 15:07:02', '0');
INSERT INTO `log_network` VALUES ('4', '2018-10-15 15:07:02', '2018-10-15 15:07:27', '000090018352', '00000006,000090018352', null, '0', '2018-10-15 15:07:26', '0');
INSERT INTO `log_network` VALUES ('5', '2018-10-15 14:56:26', '2018-10-15 14:56:50', '000090011668', '00000006,000090011668', null, '0', '2018-10-15 14:56:50', '0');
INSERT INTO `log_network` VALUES ('5', '2018-10-15 14:56:50', '2018-10-15 14:57:14', '000090011668', '00000006,000090011668', null, '0', '2018-10-15 14:57:14', '0');
INSERT INTO `log_network` VALUES ('5', '2018-10-15 15:07:27', '2018-10-15 15:07:51', '000090011668', '00000006,000090011668', null, '0', '2018-10-15 15:07:51', '0');
INSERT INTO `log_network` VALUES ('5', '2018-10-15 15:07:51', '2018-10-15 15:08:16', '000090011668', '00000006,000090011668', null, '0', '2018-10-15 15:08:15', '0');
INSERT INTO `log_network` VALUES ('6', '2018-10-15 14:57:15', '2018-10-15 14:57:48', '180503200100', '00000006,180503200100', null, '0', '2018-10-15 14:57:48', '0');
INSERT INTO `log_network` VALUES ('6', '2018-10-15 14:57:48', '2018-10-15 14:58:13', '180503200100', '00000006,180503200100', null, '0', '2018-10-15 14:58:12', '0');
INSERT INTO `log_network` VALUES ('7', '2018-10-15 14:50:15', '2018-10-15 14:50:40', '180503200074', '00000006,180503200074', null, '0', '2018-10-15 14:50:39', '0');
INSERT INTO `log_network` VALUES ('7', '2018-10-15 14:50:40', '2018-10-15 14:51:13', '180503200074', '00000006,180503200074', null, '0', '2018-10-15 14:51:13', '0');
INSERT INTO `log_network` VALUES ('7', '2018-10-15 15:01:15', '2018-10-15 15:01:40', '180503200074', '00000006,180503200074', null, '0', '2018-10-15 15:01:39', '0');
INSERT INTO `log_network` VALUES ('7', '2018-10-15 15:01:40', '2018-10-15 15:02:14', '180503200074', '00000006,180503200074', null, '0', '2018-10-15 15:02:13', '0');
INSERT INTO `log_network` VALUES ('8', '2018-10-15 14:51:14', '2018-10-15 14:51:38', '180503200046', '00000006,180503200046', null, '0', '2018-10-15 14:51:37', '0');
INSERT INTO `log_network` VALUES ('8', '2018-10-15 14:51:38', '2018-10-15 14:52:02', '180503200046', '00000006,180503200046', null, '0', '2018-10-15 14:52:02', '0');
INSERT INTO `log_network` VALUES ('8', '2018-10-15 15:02:14', '2018-10-15 15:02:39', '180503200046', '00000006,180503200046', null, '0', '2018-10-15 15:02:38', '0');
INSERT INTO `log_network` VALUES ('8', '2018-10-15 15:02:39', '2018-10-15 15:03:03', '180503200046', '00000006,180503200046', null, '0', '2018-10-15 15:03:03', '0');
INSERT INTO `log_network` VALUES ('9', '2018-10-15 14:52:02', '2018-10-15 14:52:27', '180503200075', '00000006,180503200075', null, '0', '2018-10-15 14:52:26', '0');
INSERT INTO `log_network` VALUES ('9', '2018-10-15 14:52:27', '2018-10-15 14:52:51', '180503200075', '00000006,180503200075', null, '0', '2018-10-15 14:52:50', '0');
INSERT INTO `log_network` VALUES ('9', '2018-10-15 15:03:03', '2018-10-15 15:03:28', '180503200075', '00000006,180503200075', null, '0', '2018-10-15 15:03:27', '0');
INSERT INTO `log_network` VALUES ('9', '2018-10-15 15:03:28', '2018-10-15 15:03:52', '180503200075', '00000006,180503200075', null, '0', '2018-10-15 15:03:51', '0');
INSERT INTO `log_network` VALUES ('1000001', '2018-10-15 14:47:50', '2018-10-15 14:48:15', '000001234567', '00000006,000001234567', null, '0', '2018-10-15 14:48:14', '0');
INSERT INTO `log_network` VALUES ('1000001', '2018-10-15 14:48:15', '2018-10-15 14:48:39', '000001234567', '00000006,000001234567', null, '0', '2018-10-15 14:48:39', '0');
INSERT INTO `log_network` VALUES ('1000002', '2018-10-15 14:58:13', '2018-10-15 14:58:38', '000007654321', '00000006,000007654321', null, '0', '2018-10-15 14:58:37', '0');
INSERT INTO `log_network` VALUES ('1000002', '2018-10-15 14:58:38', '2018-10-15 14:59:02', '000007654321', '00000006,000007654321', null, '0', '2018-10-15 14:59:02', '0');
INSERT INTO `log_network` VALUES ('1000003', '2018-10-15 15:00:01', '2018-10-15 15:00:25', '000001237788', '00000006,000001237788', null, '0', '2018-10-15 15:00:25', '0');
INSERT INTO `log_network` VALUES ('1000003', '2018-10-15 15:00:25', '2018-10-15 15:00:50', '000001237788', '00000006,000001237788', null, '0', '2018-10-15 15:00:49', '0');
INSERT INTO `log_network` VALUES ('1000004', '2018-10-15 14:59:02', '2018-10-15 14:59:27', '000001337788', '00000006,000001337788', null, '0', '2018-10-15 14:59:26', '0');
INSERT INTO `log_network` VALUES ('1000004', '2018-10-15 14:59:27', '2018-10-15 15:00:01', '000001337788', '00000006,000001337788', null, '0', '2018-10-15 15:00:00', '0');

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
  `Longitude` double(16,10) NOT NULL COMMENT 'NO',
  `Latitude` double(16,10) NOT NULL,
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
  `MDFOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REGOn` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of prm_node
-- ----------------------------
INSERT INTO `prm_node` VALUES ('1', '1', '000000000014', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0612150000', '30.3154450000', '35494.00', '70.906', '3', '355', '1', '1', '0', '0/', null, '0', '2018-10-10 16:20:25', '2018-10-10 16:21:14', null);
INSERT INTO `prm_node` VALUES ('2', '2', '000090018348', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0612150000', '30.3154450000', '35494.00', '70.906', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:33:01', '2018-10-11 14:33:26', null);
INSERT INTO `prm_node` VALUES ('3', '3', '000090011673', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0612150000', '30.3154450000', '35494.00', '70.906', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('4', '4', '000090018352', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0612150000', '30.3154450000', '35494.00', '70.906', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('5', '5', '000090011668', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0612150000', '30.3154450000', '35494.00', '70.906', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('6', '6', '180503200100', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0612132800', '30.3154605100', '35496.00', '70.907', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('7', '7', '180503200074', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0611849900', '30.3154318900', '35492.00', '70.912', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('8', '8', '180503200046', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0611750300', '30.3154289000', '35492.00', '70.914', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('9', '9', '180503200075', '1', '0', '81', '0', '1', '20', '4', '2', '6', '5', '1', '120.0611750300', '30.3154289000', '35492.00', '70.914', '3', '355', '1', '1', '0', null, null, '0', '2018-10-11 14:34:56', '2018-10-11 14:35:26', null);
INSERT INTO `prm_node` VALUES ('1000002', '1000002', '000007654321', '0', '0', '0', '0', '1', '1', null, null, null, null, null, '120.3123000000', '30.2121000000', '38159.00', '21.649', '1', '382', '2', '1', '0', '0/', null, '0', '2018-10-10 13:22:51', '2018-10-15 10:14:20', null);
INSERT INTO `prm_node` VALUES ('1000003', '1000003', '000001237788', '0', '0', '0', '0', '1', '1', null, null, null, null, null, '120.4143000000', '30.2311000000', '47401.00', '15.875', '1', '475', '1', '1', '0', '0/', null, '0', '2018-10-10 13:22:51', '2018-10-15 10:14:20', null);
INSERT INTO `prm_node` VALUES ('1000004', '1000004', '000001337788', '0', '0', '0', '0', '6', '20', null, null, null, null, null, '120.3112000000', '30.3121000000', '45782.00', '29.531', '1', '458', '2', '1', '0', null, null, '0', '2018-10-12 18:07:09', '2018-10-15 10:14:20', null);

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
