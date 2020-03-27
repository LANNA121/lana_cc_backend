/*
 LANA_CC_BACKEND_DATABASE

 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Schema         : lana_cc_backend

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 27/03/2020 18:48:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lana_account
-- ----------------------------
DROP TABLE IF EXISTS `lana_account`;
CREATE TABLE `lana_account`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT COMMENT '账户userId 唯一标识用户',
  `role` enum('USER','OSS') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'USER' COMMENT '用户身份',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名/用户登录',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
  `birthday` bigint(0) NULL DEFAULT NULL COMMENT '用户生日 UNIX时间戳',
  `nike_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `gender` enum('M','F','NO') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'NO' COMMENT '用户性别',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个性签名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户头像',
  `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '背景底色图片',
  `status` int(0) NOT NULL DEFAULT 0 COMMENT '用户状态,用于标识用户的基本状态',
  `create_time` bigint(0) NOT NULL COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100017 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_account_addr
-- ----------------------------
DROP TABLE IF EXISTS `lana_account_addr`;
CREATE TABLE `lana_account_addr`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户绑定地址相关信息',
  `uid` int(0) NOT NULL COMMENT '用户UID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收件人名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收件人手机号码',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '城市',
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '行政街道',
  `street` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收件人门牌号',
  `status` int(0) NOT NULL DEFAULT 0 COMMENT '默认状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_account_book
-- ----------------------------
DROP TABLE IF EXISTS `lana_account_book`;
CREATE TABLE `lana_account_book`  (
  `lana_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账单ID',
  `uid` int(0) NOT NULL COMMENT '账单所有人UID',
  `type` enum('achieve','consume') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'consume' COMMENT '账单类型',
  `count` int(0) NOT NULL COMMENT '数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标记',
  `create_time` bigint(0) NOT NULL COMMENT '创建时间',
  `status` int(0) NOT NULL COMMENT '默认状态',
  PRIMARY KEY (`lana_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_garbage_categories
-- ----------------------------
DROP TABLE IF EXISTS `lana_garbage_categories`;
CREATE TABLE `lana_garbage_categories`  (
  `id` int(0) NULL DEFAULT NULL,
  `color` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bg_color` varchar(54) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `action` json NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_garbage_search
-- ----------------------------
DROP TABLE IF EXISTS `lana_garbage_search`;
CREATE TABLE `lana_garbage_search`  (
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sortId` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_mall_bill
-- ----------------------------
DROP TABLE IF EXISTS `lana_mall_bill`;
CREATE TABLE `lana_mall_bill`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '兑换的账单ID',
  `uid` int(0) NOT NULL COMMENT '账单所有人的ID',
  `goods_id` int(0) NOT NULL COMMENT '账单兑换的商品ID',
  `create_time` bigint(0) NOT NULL COMMENT '账单的创建时间',
  `track_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账单的物流编号',
  `bill_status` int(0) NOT NULL DEFAULT 0 COMMENT '账单的处理状态',
  `operator` int(0) NULL DEFAULT NULL COMMENT '处理人UID',
  `address_id` int(0) NOT NULL COMMENT '关联的地址ID',
  `status` int(0) NOT NULL DEFAULT 0 COMMENT '默认状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `lana_mall_goods`;
CREATE TABLE `lana_mall_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `total` int(0) NOT NULL COMMENT '商品总是',
  `price` int(0) NOT NULL COMMENT '商品价值',
  `goods_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品图片的链接地址',
  `goods_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品描述',
  `create_by` int(0) NOT NULL COMMENT '创建人',
  `create_time` bigint(0) NOT NULL COMMENT '创建时间',
  `status` int(0) NOT NULL COMMENT '商品状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lana_news
-- ----------------------------
DROP TABLE IF EXISTS `lana_news`;
CREATE TABLE `lana_news`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT ' 自增加主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '新闻标题',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '封面图片',
  `type` int(0) NOT NULL DEFAULT 2 COMMENT '新闻消息类型',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '新闻连接地址',
  `top` int(0) NOT NULL DEFAULT 0 COMMENT '是否置顶标识',
  `create_time` bigint(0) NOT NULL COMMENT '创建时间',
  `create_by` int(0) NOT NULL COMMENT '创建人',
  `status` int(0) NOT NULL DEFAULT 0 COMMENT '新闻状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
