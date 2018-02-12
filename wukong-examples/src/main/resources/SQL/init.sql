/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : wukong_write

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-02-08 17:37:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `groups`
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `groups_id` int(11) NOT NULL DEFAULT '0',
  `group_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`groups_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '研发组');
INSERT INTO `groups` VALUES ('2', '测试组');
INSERT INTO `groups` VALUES ('3', '产品组');



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `age` int(2) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `groups_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三w', '24', '2018-02-07 17:48:38', '1');
INSERT INTO `user` VALUES ('2', '李四', '28', '2018-01-03 17:55:51', '1');
INSERT INTO `user` VALUES ('3', '王五', '48', '2018-02-05 17:55:56', '2');
INSERT INTO `user` VALUES ('4', '小明', null, '2018-02-06 11:51:17', null);
