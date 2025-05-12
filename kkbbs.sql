/*
Navicat MySQL Data Transfer

Source Server         : asd
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : kkbbs

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2025-05-11 00:47:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comandqu
-- ----------------------------
DROP TABLE IF EXISTS `comandqu`;
CREATE TABLE `comandqu` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `userid` varchar(36) CHARACTER SET utf8 NOT NULL,
  `queid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comandqu
-- ----------------------------
INSERT INTO `comandqu` VALUES ('3', '2', '3');
INSERT INTO `comandqu` VALUES ('4', '2', '4');
INSERT INTO `comandqu` VALUES ('5', '3', '5');
INSERT INTO `comandqu` VALUES ('6', '3', '6');
INSERT INTO `comandqu` VALUES ('10', '2', '10');
INSERT INTO `comandqu` VALUES ('11', '2', '11');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `comment_creator` bigint(20) NOT NULL,
  `receiver_id` bigint(20) NOT NULL,
  `receiver_name` varchar(50) DEFAULT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  `like_count` bigint(20) DEFAULT '0',
  `comment_count` int(11) DEFAULT '0',
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '1', '1', 'admin', '1702046795101', '1702046795101', '0', '0', '牛');
INSERT INTO `comment` VALUES ('2', '3', '1', '2', '2', 'asd', '1746188373284', '1746188373284', '0', '0', 'asd');
INSERT INTO `comment` VALUES ('3', '4', '1', '1', '1', 'qwe', '0', '0', '0', '0', 'lihai');
INSERT INTO `comment` VALUES ('4', '6', '1', '1', '2', 'asd', '0', '0', '0', '0', '无解');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notifier` bigint(20) NOT NULL,
  `receiver` bigint(20) NOT NULL,
  `outerid` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `out_title` varchar(256) DEFAULT NULL,
  `out_username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notification
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('3', '怎么搞大作业', 'ai', null, null, '2', '1', '5', '0', 'ai');
INSERT INTO `question` VALUES ('4', 'asd', 'asdasdasd', null, null, '1', '0', '0', '0', 'asd');
INSERT INTO `question` VALUES ('5', '测试测试', 'cscscscscscscs', null, null, '1', '0', '0', '0', '测试');
INSERT INTO `question` VALUES ('6', '大作业怎么办啊！！！', 'aiaiaiaiaiAI！', null, null, '1', '1', '2', '0', '操');
INSERT INTO `question` VALUES ('10', 'asdasdasdasd', 'asdsadsadsadsa', null, null, '1', '0', '0', '0', 'asdasdsad');
INSERT INTO `question` VALUES ('11', 'zxc', 'zxc', null, null, '1', '0', '0', '0', 'zxc');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `avatarUrl` text,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(200) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  `register_way` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, 'admin', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', '8d26c0880f9122e5904044b06e556fa9', 'd8xtc', '1702046538019', '1702046538019', null, 'register');
INSERT INTO `user` VALUES ('2', null, 'asd', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', '50ab591392aff1d8b6a98c1e881375ad', '0JOmY', '1746188309397', '1746188309397', null, 'register');
INSERT INTO `user` VALUES ('3', null, 'qwe', 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', '123456', null, '1746188309397', '1746188309397', null, 'register');
