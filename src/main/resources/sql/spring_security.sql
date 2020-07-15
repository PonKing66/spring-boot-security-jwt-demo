/*
Navicat MySQL Data Transfer

Source Server         : DESKTOP-RD3SK04_MYSQL
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-07-15 20:18:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父资源ID，一级资源ID为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `permission` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NOT NULL COMMENT '类型  -1：根目录  0：目录   1：菜单   2：按钮',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '-1', '根节点', null, '-1', '2020-02-19 11:16:18', '2020-07-13 21:53:26');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'sys:user:menu', '1', '2019-10-30 00:16:24', '2019-11-06 03:52:30');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys:role:menu', '1', '2019-10-30 00:16:24', '2019-11-06 03:52:37');
INSERT INTO `sys_menu` VALUES ('4', '1', '资源管理', 'sys:menu:menu', '1', '2019-10-30 00:16:24', '2019-11-06 03:52:42');
INSERT INTO `sys_menu` VALUES ('5', '2', '查询用户', 'sys:user:list', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:20');
INSERT INTO `sys_menu` VALUES ('6', '2', '新增用户', 'sys:user:add', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:26');
INSERT INTO `sys_menu` VALUES ('7', '2', '修改用户', 'sys:user:update', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:29');
INSERT INTO `sys_menu` VALUES ('8', '2', '删除用户', 'sys:user:delete', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:31');
INSERT INTO `sys_menu` VALUES ('9', '3', '查询角色', 'sys:role:list', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:33');
INSERT INTO `sys_menu` VALUES ('10', '3', '新增角色', 'sys:role:add', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:36');
INSERT INTO `sys_menu` VALUES ('11', '3', '修改角色', 'sys:role:update', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:38');
INSERT INTO `sys_menu` VALUES ('12', '4', '查询资源', 'sys:menu:list', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:39');
INSERT INTO `sys_menu` VALUES ('13', '4', '新增资源', 'sys:menu:add', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:40');
INSERT INTO `sys_menu` VALUES ('14', '4', '修改资源', 'sys:menu:update', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:42');
INSERT INTO `sys_menu` VALUES ('15', '4', '删除资源', 'sys:menu:delete', '2', '2019-10-30 00:16:24', '2020-07-13 21:44:44');
INSERT INTO `sys_menu` VALUES ('16', '3', '删除角色', 'sys:role:delete', '2', '2019-11-04 01:09:36', '2020-07-13 21:44:45');
INSERT INTO `sys_menu` VALUES ('17', '2', '分配角色', 'sys:user:assign:role', '2', '2019-11-06 00:50:26', '2020-07-13 21:44:47');
INSERT INTO `sys_menu` VALUES ('18', '3', '分配资源', 'sys:role:assign:menu', '2', '2019-11-06 00:58:01', '2020-07-13 21:44:55');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '超级管理', '2019-10-30 01:03:14', '2020-07-14 16:22:12');
INSERT INTO `sys_role` VALUES ('2', 'ROLE', '角色管理', '2019-10-30 01:03:52', '2020-07-14 16:21:41');
INSERT INTO `sys_role` VALUES ('3', 'USER', '用户管理', '2020-07-14 16:21:29', '2020-07-14 16:21:29');
INSERT INTO `sys_role` VALUES ('4', 'MENU', '菜单管理', '2020-07-14 16:22:00', '2020-07-14 16:22:00');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=863 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '7');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态  0：正常   1：禁用   2：锁定',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$wU/6wvTzRc/3Fbnkqsv.pupXnNtMNhvt.FxUehGlPlEPi1m6qqH.S', '0', '2020-02-04 12:09:24', '2020-07-14 20:55:20');
INSERT INTO `sys_user` VALUES ('2', 'jerry', '$2a$10$wU/6wvTzRc/3Fbnkqsv.pupXnNtMNhvt.FxUehGlPlEPi1m6qqH.S', '0', '2020-02-18 12:00:31', '2020-07-14 22:26:48');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL COMMENT '用户ID',
  `role_id` int(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '3');
INSERT INTO `sys_user_role` VALUES ('4', '1', '4');
