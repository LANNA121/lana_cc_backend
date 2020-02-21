/*
 LANA DATABASE ACCOUNT

 Source Server         : LANA:Localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3066
 Source Schema         : lana_cc_backend

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 21/02/2020 11:43:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lana_account
-- ----------------------------
DROP TABLE IF EXISTS `lana_account`;
CREATE TABLE `lana_account`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户userId 唯一标识用户',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名/用户登录',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
  `birthday` bigint(20) NOT NULL COMMENT '用户生日 UNIX时间戳',
  `nikeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `gender` enum('M','F','NO') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'NO' COMMENT '用户性别',
  `
signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个性签名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户头像',
  `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '背景底色图片',
  `status` int(2) NOT NULL COMMENT '用户状态,用于标识用户的基本状态',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
