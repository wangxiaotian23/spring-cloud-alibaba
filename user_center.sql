/*
 Navicat Premium Data Transfer

 Source Server         : 本机8
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 27/01/2021 11:44:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_event_log
-- ----------------------------
DROP TABLE IF EXISTS `bonus_event_log`;
CREATE TABLE `bonus_event_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` int DEFAULT NULL COMMENT 'user.id',
  `value` int DEFAULT NULL COMMENT '积分操作值',
  `event` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发生的事件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `fk_bonus_event_log_user1_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='积分变更记录表';

-- ----------------------------
-- Records of bonus_event_log
-- ----------------------------
BEGIN;
INSERT INTO `bonus_event_log` VALUES (1, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (2, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (3, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (4, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (5, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (6, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (7, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (8, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (9, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (10, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (11, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (12, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (13, 1, 50, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (14, 1, 0, 'CONTRIBUTE', NULL, '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (15, 1, 0, 'CONTRIBUTE', NULL, '投稿加积分');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `wx_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信id',
  `wx_nickname` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信昵称',
  `roles` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar_url` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `bonus` int NOT NULL DEFAULT '300' COMMENT '积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分享';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '1111', '张大仙', 'admin', 'a.png', '2020-12-28 18:09:19', '2020-12-28 18:09:20', 950);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
