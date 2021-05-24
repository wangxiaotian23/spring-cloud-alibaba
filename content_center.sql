/*
 Navicat Premium Data Transfer

 Source Server         : 本机8
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : content_center

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 27/01/2021 11:44:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mid_user_share
-- ----------------------------
DROP TABLE IF EXISTS `mid_user_share`;
CREATE TABLE `mid_user_share` (
  `id` int NOT NULL AUTO_INCREMENT,
  `share_id` int NOT NULL COMMENT 'share.id',
  `user_id` int NOT NULL COMMENT 'user.id',
  PRIMARY KEY (`id`),
  KEY `fk_mid_user_share_share1_idx` (`share_id`),
  KEY `fk_mid_user_share_user1_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户-分享中间表【描述用户购买的分享】';

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
BEGIN;
INSERT INTO `mid_user_share` VALUES (2, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `show_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示 0:否 1:是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for rocketmq_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `rocketmq_transaction_log`;
CREATE TABLE `rocketmq_transaction_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transaction_id` varchar(45) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事务ID',
  `log` varchar(45) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of rocketmq_transaction_log
-- ----------------------------
BEGIN;
INSERT INTO `rocketmq_transaction_log` VALUES (3, '8651426d-85be-4dba-8a3b-bdd355b9fa5f', '审核分享');
INSERT INTO `rocketmq_transaction_log` VALUES (4, '1fb611cf-2523-44e2-b33e-5c1bf7e8714e', '审核分享');
INSERT INTO `rocketmq_transaction_log` VALUES (5, '37b7f22c-8c8a-4991-ba16-721358c24f3f', '审核分享');
INSERT INTO `rocketmq_transaction_log` VALUES (6, 'dfb447ab-137e-4718-a1fa-6139b145a193', '审核分享');
INSERT INTO `rocketmq_transaction_log` VALUES (7, 'a634f413-4d0c-4184-ae36-7cd5ccb42de8', '审核分享');
COMMIT;

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '发布人id',
  `title` varchar(80) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_original` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否原创 0:否 1:是',
  `author` varchar(45) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者',
  `cover` varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `summary` varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '概要信息',
  `price` int NOT NULL DEFAULT '0' COMMENT '价格（需要的积分）',
  `download_url` varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '下载地址',
  `buy_count` int NOT NULL DEFAULT '0' COMMENT '下载数 ',
  `show_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示 0:否 1:是',
  `audit_status` varchar(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过',
  `reason` varchar(200) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审核不通过原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分享表';

-- ----------------------------
-- Records of share
-- ----------------------------
BEGIN;
INSERT INTO `share` VALUES (1, 1, 'docker学习笔记', '2020-12-28 18:07:35', '2020-12-28 18:07:38', 1, '李四', 'a.png', 'docker', 0, 'http://aaa.png', 0, 1, 'PASS', '还行');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
