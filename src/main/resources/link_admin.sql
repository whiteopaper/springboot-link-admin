/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : link_admin

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-11-18 16:00:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_video`
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES ('11', '2019-10-04_37aaadf19afbfbdc4e3053380cf071d5.mp4', '/opt/store/linkadmin/tiktok/2019-10-04_37aaadf19afbfbdc4e3053380cf071d5.mp4', '2019-10-04 17:33:06', null);
INSERT INTO `t_video` VALUES ('15', '2019-10-21_2.mp4', '/opt/store/linkadmin/tiktok/2019-10-21_2.mp4', '2019-10-21 20:14:05', null);

-- ----------------------------
-- Table structure for `t_web_datascope`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_datascope`;
CREATE TABLE `t_web_datascope` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `targetCategory` varchar(30) DEFAULT NULL,
  `targetId` varchar(32) DEFAULT NULL,
  `targetName` varchar(30) DEFAULT NULL,
  `targetUrl` varchar(150) NOT NULL,
  `permissionId` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_datascope
-- ----------------------------

-- ----------------------------
-- Table structure for `t_web_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_dept`;
CREATE TABLE `t_web_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `levels` int(11) DEFAULT NULL,
  `for_service` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_dept
-- ----------------------------
INSERT INTO `t_web_dept` VALUES ('2', '上海集团', '0', '0', null, '0', '1');
INSERT INTO `t_web_dept` VALUES ('4', '青浦分公司', '2', '1', null, '0', '12');
INSERT INTO `t_web_dept` VALUES ('6', '人事部', '4', '2', null, '0', '11');
INSERT INTO `t_web_dept` VALUES ('8', '信息中心', '4', '2', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('9', '运维部', '4', '2', null, '0', '14');
INSERT INTO `t_web_dept` VALUES ('10', '安全部', '4', '2', null, '0', '15');
INSERT INTO `t_web_dept` VALUES ('11', 'IT与流程', '5', '2', null, '0', '21');
INSERT INTO `t_web_dept` VALUES ('12', '快递系统研发部', '11', '2', null, '0', '12');
INSERT INTO `t_web_dept` VALUES ('13', '渠道系统研发部', '11', '2', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('14', '整车业务研发部', '11', '2', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('15', '物流系统研发部', '11', '2', null, '0', '14');
INSERT INTO `t_web_dept` VALUES ('16', '徐汇分公司', '2', '1', null, '0', '2');
INSERT INTO `t_web_dept` VALUES ('17', '研发部', '16', null, null, '0', '21');
INSERT INTO `t_web_dept` VALUES ('18', '快递系统研发部', '4', null, null, '0', '1');
INSERT INTO `t_web_dept` VALUES ('20', '流程中心', '4', null, null, '0', null);
INSERT INTO `t_web_dept` VALUES ('21', '渠道系统研发部', '22', null, null, '0', '1');
INSERT INTO `t_web_dept` VALUES ('22', '西安分公司', '2', null, null, '0', null);
INSERT INTO `t_web_dept` VALUES ('25', '程序员鼓励师', '16', null, null, '0', null);

-- ----------------------------
-- Table structure for `t_web_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_dict`;
CREATE TABLE `t_web_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_type` varchar(50) NOT NULL DEFAULT '0' COMMENT ' 父ID ',
  `data_key` varchar(50) NOT NULL COMMENT ' 数据编码 ',
  `data_value` varchar(200) NOT NULL COMMENT ' 数据名称/值 ',
  `sorts` int(11) unsigned DEFAULT '1' COMMENT ' 顺序 ',
  `description` varchar(400) DEFAULT NULL COMMENT '数据描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_web_dict
-- ----------------------------
INSERT INTO `t_web_dict` VALUES ('2', 'data_scope', '1', '自定义数据权限', '1', '12');
INSERT INTO `t_web_dict` VALUES ('3', 'data_scope', '2', '全部数据权限', '2', '');
INSERT INTO `t_web_dict` VALUES ('4', 'data_scope', '3', '本部门及以下数据权限', '3', '');
INSERT INTO `t_web_dict` VALUES ('5', 'data_scope', '4', '本部门数据权限', '4', '');
INSERT INTO `t_web_dict` VALUES ('6', 'data_scope', '5', '仅本人数据权限', '5', '');
INSERT INTO `t_web_dict` VALUES ('9', 'sex', '1', '男', '1', '');
INSERT INTO `t_web_dict` VALUES ('10', 'sex', '2', '女', '2', '');

-- ----------------------------
-- Table structure for `t_web_logs`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_logs`;
CREATE TABLE `t_web_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginuser` varchar(30) DEFAULT NULL,
  `vsername` varchar(30) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `request_method` varchar(30) DEFAULT NULL,
  `content_type` varchar(50) DEFAULT NULL,
  `request_params` varchar(2000) DEFAULT NULL,
  `response_result` varchar(2000) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1678 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `t_web_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_permission`;
CREATE TABLE `t_web_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `types` int(11) DEFAULT NULL,
  `i_frame` int(11) DEFAULT NULL COMMENT '是否外链',
  `url` varchar(150) DEFAULT NULL,
  `levels` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `component_name` varchar(50) DEFAULT NULL,
  `component_path` varchar(150) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `cache` int(11) DEFAULT NULL,
  `hidden` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_permission
-- ----------------------------
INSERT INTO `t_web_permission` VALUES ('2', '文档', '0', '1', '0', '/documentation', null, null, '200', null, 'Documentation', '/documentation/index', 'documentation', '0', '0');
INSERT INTO `t_web_permission` VALUES ('4', '外链', '0', '1', '1', 'https://github.com/252956/vue-link-admin', null, null, '202', null, null, null, 'link', '0', '0');
INSERT INTO `t_web_permission` VALUES ('5', '错误页面', '0', '0', '0', '/error', null, null, '199', null, 'ErrorPages', '/error', '404', '0', '0');
INSERT INTO `t_web_permission` VALUES ('6', '401', '5', '1', '0', '/error/401', null, null, '30', null, 'Page401', '/error-page/401', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('7', '404', '5', '1', '0', '/error/404', null, null, '31', null, 'Page404', '/error-page/404', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('8', '系统权限', '0', '0', '0', '/permission', null, null, '100', null, 'Permission', '/permission', 'lock', '0', '0');
INSERT INTO `t_web_permission` VALUES ('9', '用户管理', '8', '1', '0', '/permission/user', null, null, '1', null, 'User', '/permission/user', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('10', '角色管理', '8', '1', '0', '/permission/role', null, null, '2', null, 'Role', '/permission/role', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('11', '权限管理', '8', '1', '0', '/permission/permission', null, null, '3', null, 'Permission', '/permission/permission', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('13', '部门管理', '8', '1', '0', '/permission/dept', null, null, '4', null, 'Department', '/permission/department', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('14', '图标', '0', '1', '0', '/icon', null, null, '201', null, 'Icons', '/icons/index', 'icon', '0', '0');
INSERT INTO `t_web_permission` VALUES ('16', '查询', '9', '2', '0', '/rest/user/list', null, null, '1', null, '', '', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('17', '新增', '9', '2', null, '/rest/user/add', null, null, '201', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('18', '修改', '9', '2', null, '/rest/user/update', null, null, '202', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('19', '删除', '9', '2', null, '/rest/user/delete', null, null, '203', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('20', '查询', '10', '2', null, '/rest/role/list', null, null, '210', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('21', '新增', '10', '2', null, '/rest/role/add', null, null, '211', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('22', '修改', '10', '2', null, '/rest/role/update', null, null, '212', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('23', '删除', '10', '2', null, '/rest/role/delete', null, null, '213', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('24', '查询', '11', '2', null, '/rest/permission/all', null, null, '220', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('25', '新增', '11', '2', null, '/rest/permission/add', null, null, '221', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('26', '修改', '11', '2', null, '/rest/permission/update', null, null, '222', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('27', '删除', '11', '2', null, '/rest/permission/delete', null, null, '223', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('28', '查询', '13', '2', null, '/rest/department/all', null, null, '240', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('29', '新增', '13', '2', null, '/rest/department/add', null, null, '241', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('30', '修改', '13', '2', null, '/rest/department/update', null, null, '242', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('31', '删除', '13', '2', null, '/rest/department/delete', null, null, '243', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('32', '系统监控', '0', '0', '0', '/monitor', null, null, '101', null, 'Monitor', '/monitor', 'monitor', '0', '0');
INSERT INTO `t_web_permission` VALUES ('33', '错误日志', '32', '1', '0', '/monitor/error-log', null, null, '40', null, 'ErrorLog', '/monitor/error-log', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('34', '业务日志', '32', '1', '0', '/monitor/blog', null, null, '41', null, 'Blog', '/monitor/blog', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('36', '视频+', '0', '0', '0', '/video', null, null, '2', null, 'Video', '/video', 'video', '0', '0');
INSERT INTO `t_web_permission` VALUES ('37', 'TikTok', '36', '1', '0', '/video/tiktok', null, null, '10', null, 'TikTok', '/video/tiktok', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('40', '系统管理', '0', '0', '0', '/system', null, null, '102', null, 'System', '/system', 'star', '0', '0');
INSERT INTO `t_web_permission` VALUES ('41', '数据字典', '8', '1', '0', '/permission/dict', null, null, '5', null, 'Dict', '/permission/dict', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('42', '通知公告', '40', '1', '0', '/system/notice', null, null, '999', null, 'ErrorLog', '/error-page/404', null, '0', '0');
INSERT INTO `t_web_permission` VALUES ('43', '数据权限', '10', '2', null, '/rest/role/saveDataScope', null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('44', '新增', '41', '2', null, '/rest/dict/add', null, null, '1', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('45', '修改', '41', '2', null, '/rest/dict/update', null, null, '2', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('46', '删除', '41', '2', null, '/rest/dict/delete', null, null, '3', null, null, null, null, null, null);
INSERT INTO `t_web_permission` VALUES ('48', '查询', '34', '2', '0', '/rest/logs/blog/list', null, null, '1', null, '', '', '', '0', '0');

-- ----------------------------
-- Table structure for `t_web_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_role`;
CREATE TABLE `t_web_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `levels` int(11) DEFAULT NULL COMMENT '新增用户时只能赋予比自己级别低的角色',
  `data_scope` varchar(11) DEFAULT NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_role
-- ----------------------------
INSERT INTO `t_web_role` VALUES ('1', 'admin', null, '2', '超级管理员');
INSERT INTO `t_web_role` VALUES ('2', 'editor', null, '1', '系统演示角色');
INSERT INTO `t_web_role` VALUES ('11', 'test', null, '5', '测试角色');

-- ----------------------------
-- Table structure for `t_web_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_role_dept`;
CREATE TABLE `t_web_role_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_web_role_dept
-- ----------------------------
INSERT INTO `t_web_role_dept` VALUES ('8', '2', '17');
INSERT INTO `t_web_role_dept` VALUES ('9', '2', '6');

-- ----------------------------
-- Table structure for `t_web_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_role_permission`;
CREATE TABLE `t_web_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1426 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_role_permission
-- ----------------------------
INSERT INTO `t_web_role_permission` VALUES ('1307', '11', '5');
INSERT INTO `t_web_role_permission` VALUES ('1308', '11', '6');
INSERT INTO `t_web_role_permission` VALUES ('1309', '11', '7');
INSERT INTO `t_web_role_permission` VALUES ('1310', '11', '2');
INSERT INTO `t_web_role_permission` VALUES ('1311', '11', '14');
INSERT INTO `t_web_role_permission` VALUES ('1312', '11', '4');
INSERT INTO `t_web_role_permission` VALUES ('1351', '2', '36');
INSERT INTO `t_web_role_permission` VALUES ('1352', '2', '37');
INSERT INTO `t_web_role_permission` VALUES ('1353', '2', '8');
INSERT INTO `t_web_role_permission` VALUES ('1354', '2', '9');
INSERT INTO `t_web_role_permission` VALUES ('1355', '2', '16');
INSERT INTO `t_web_role_permission` VALUES ('1356', '2', '17');
INSERT INTO `t_web_role_permission` VALUES ('1357', '2', '18');
INSERT INTO `t_web_role_permission` VALUES ('1358', '2', '19');
INSERT INTO `t_web_role_permission` VALUES ('1359', '2', '10');
INSERT INTO `t_web_role_permission` VALUES ('1360', '2', '43');
INSERT INTO `t_web_role_permission` VALUES ('1361', '2', '20');
INSERT INTO `t_web_role_permission` VALUES ('1362', '2', '21');
INSERT INTO `t_web_role_permission` VALUES ('1363', '2', '22');
INSERT INTO `t_web_role_permission` VALUES ('1364', '2', '23');
INSERT INTO `t_web_role_permission` VALUES ('1365', '2', '11');
INSERT INTO `t_web_role_permission` VALUES ('1366', '2', '24');
INSERT INTO `t_web_role_permission` VALUES ('1367', '2', '25');
INSERT INTO `t_web_role_permission` VALUES ('1368', '2', '26');
INSERT INTO `t_web_role_permission` VALUES ('1369', '2', '27');
INSERT INTO `t_web_role_permission` VALUES ('1370', '2', '13');
INSERT INTO `t_web_role_permission` VALUES ('1371', '2', '28');
INSERT INTO `t_web_role_permission` VALUES ('1372', '2', '29');
INSERT INTO `t_web_role_permission` VALUES ('1373', '2', '30');
INSERT INTO `t_web_role_permission` VALUES ('1374', '2', '31');
INSERT INTO `t_web_role_permission` VALUES ('1375', '2', '41');
INSERT INTO `t_web_role_permission` VALUES ('1376', '2', '32');
INSERT INTO `t_web_role_permission` VALUES ('1377', '2', '33');
INSERT INTO `t_web_role_permission` VALUES ('1378', '2', '40');
INSERT INTO `t_web_role_permission` VALUES ('1379', '2', '42');
INSERT INTO `t_web_role_permission` VALUES ('1380', '2', '5');
INSERT INTO `t_web_role_permission` VALUES ('1381', '2', '6');
INSERT INTO `t_web_role_permission` VALUES ('1382', '2', '7');
INSERT INTO `t_web_role_permission` VALUES ('1383', '2', '2');
INSERT INTO `t_web_role_permission` VALUES ('1384', '2', '14');
INSERT INTO `t_web_role_permission` VALUES ('1385', '2', '4');
INSERT INTO `t_web_role_permission` VALUES ('1386', '1', '36');
INSERT INTO `t_web_role_permission` VALUES ('1387', '1', '37');
INSERT INTO `t_web_role_permission` VALUES ('1388', '1', '8');
INSERT INTO `t_web_role_permission` VALUES ('1389', '1', '9');
INSERT INTO `t_web_role_permission` VALUES ('1390', '1', '16');
INSERT INTO `t_web_role_permission` VALUES ('1391', '1', '17');
INSERT INTO `t_web_role_permission` VALUES ('1392', '1', '18');
INSERT INTO `t_web_role_permission` VALUES ('1393', '1', '19');
INSERT INTO `t_web_role_permission` VALUES ('1394', '1', '10');
INSERT INTO `t_web_role_permission` VALUES ('1395', '1', '43');
INSERT INTO `t_web_role_permission` VALUES ('1396', '1', '20');
INSERT INTO `t_web_role_permission` VALUES ('1397', '1', '21');
INSERT INTO `t_web_role_permission` VALUES ('1398', '1', '22');
INSERT INTO `t_web_role_permission` VALUES ('1399', '1', '23');
INSERT INTO `t_web_role_permission` VALUES ('1400', '1', '11');
INSERT INTO `t_web_role_permission` VALUES ('1401', '1', '24');
INSERT INTO `t_web_role_permission` VALUES ('1402', '1', '25');
INSERT INTO `t_web_role_permission` VALUES ('1403', '1', '26');
INSERT INTO `t_web_role_permission` VALUES ('1404', '1', '27');
INSERT INTO `t_web_role_permission` VALUES ('1405', '1', '13');
INSERT INTO `t_web_role_permission` VALUES ('1406', '1', '28');
INSERT INTO `t_web_role_permission` VALUES ('1407', '1', '29');
INSERT INTO `t_web_role_permission` VALUES ('1408', '1', '30');
INSERT INTO `t_web_role_permission` VALUES ('1409', '1', '31');
INSERT INTO `t_web_role_permission` VALUES ('1410', '1', '41');
INSERT INTO `t_web_role_permission` VALUES ('1411', '1', '44');
INSERT INTO `t_web_role_permission` VALUES ('1412', '1', '45');
INSERT INTO `t_web_role_permission` VALUES ('1413', '1', '46');
INSERT INTO `t_web_role_permission` VALUES ('1414', '1', '32');
INSERT INTO `t_web_role_permission` VALUES ('1415', '1', '33');
INSERT INTO `t_web_role_permission` VALUES ('1416', '1', '34');
INSERT INTO `t_web_role_permission` VALUES ('1417', '1', '48');
INSERT INTO `t_web_role_permission` VALUES ('1418', '1', '40');
INSERT INTO `t_web_role_permission` VALUES ('1419', '1', '42');
INSERT INTO `t_web_role_permission` VALUES ('1420', '1', '5');
INSERT INTO `t_web_role_permission` VALUES ('1421', '1', '6');
INSERT INTO `t_web_role_permission` VALUES ('1422', '1', '7');
INSERT INTO `t_web_role_permission` VALUES ('1423', '1', '2');
INSERT INTO `t_web_role_permission` VALUES ('1424', '1', '14');
INSERT INTO `t_web_role_permission` VALUES ('1425', '1', '4');

-- ----------------------------
-- Table structure for `t_web_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_user`;
CREATE TABLE `t_web_user` (
  `uid` varchar(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `vsername` varchar(30) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `deptid` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_user
-- ----------------------------
INSERT INTO `t_web_user` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '管理员', '17601269', '2019-09-07 10:08:25', '1', '4', null, null);
INSERT INTO `t_web_user` VALUES ('2', 'editor', 'E10ADC3949BA59ABBE56E057F20F883E', '测试员1', '121212121212', '2019-09-09 16:40:43', '1', '16', '', null);
INSERT INTO `t_web_user` VALUES ('ad904a794a10434b8dec1de8ce23a288', '辉桑', 'E10ADC3949BA59ABBE56E057F20F883E', '辉桑', '1111111', '2019-09-18 13:47:51', '0', '20', '2222', null);
INSERT INTO `t_web_user` VALUES ('b88bb916dc054870ae124d92710ac3d3', '云桑', 'E10ADC3949BA59ABBE56E057F20F883E', '云桑', '1760126', '2019-09-18 11:11:39', '0', '18', null, null);
INSERT INTO `t_web_user` VALUES ('c2bd6773d48643a9ac4540a551ba6ffb', '用嗓', 'E10ADC3949BA59ABBE56E057F20F883E', '用嗓', '22222', '2019-09-18 15:16:13', '0', '16', null, null);
INSERT INTO `t_web_user` VALUES ('e97dadb6ad8f46e0937db079bc8de1c6', 'test', 'E10ADC3949BA59ABBE56E057F20F883E', 'test', '', '2019-11-04 06:55:48', '0', '2', '', null);

-- ----------------------------
-- Table structure for `t_web_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_user_role`;
CREATE TABLE `t_web_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_user_role
-- ----------------------------
INSERT INTO `t_web_user_role` VALUES ('22', '93bad36e94ab48ab977f9718147c573f', '2');
INSERT INTO `t_web_user_role` VALUES ('39', 'ab334041dc2c4786a7bddb49bf8b7816', '2');
INSERT INTO `t_web_user_role` VALUES ('40', 'ab334041dc2c4786a7bddb49bf8b7816', '8');
INSERT INTO `t_web_user_role` VALUES ('41', 'ab334041dc2c4786a7bddb49bf8b7816', '10');
INSERT INTO `t_web_user_role` VALUES ('42', 'ab334041dc2c4786a7bddb49bf8b7816', '9');
INSERT INTO `t_web_user_role` VALUES ('54', '696413831f394ac7b502e828e9ccda3e', '1');
INSERT INTO `t_web_user_role` VALUES ('55', '6535f71edf3d4e5f9891426bda3de1a1', '1');
INSERT INTO `t_web_user_role` VALUES ('56', '54136c2416984fa196f970d715e807e1', '1');
INSERT INTO `t_web_user_role` VALUES ('57', '6b6f0cbdda8d464c90ac58e68a37694e', '2');
INSERT INTO `t_web_user_role` VALUES ('58', '4b452255b3be4fc6829e6db2922257e1', '2');
INSERT INTO `t_web_user_role` VALUES ('59', '8dc0a90caeed4310a9c499ef3f1d3c1d', '1');
INSERT INTO `t_web_user_role` VALUES ('60', '1', '1');
INSERT INTO `t_web_user_role` VALUES ('61', '1', '2');
INSERT INTO `t_web_user_role` VALUES ('71', 'fa350415ebcc43c2a86cc0cbad98a0a2', '2');
INSERT INTO `t_web_user_role` VALUES ('79', '2', '2');
INSERT INTO `t_web_user_role` VALUES ('80', 'dde9b279949e4e76ac3fff531d712227', '11');
INSERT INTO `t_web_user_role` VALUES ('82', '2a1d17d2f4184e238816fd8b96195a3e', '2');
INSERT INTO `t_web_user_role` VALUES ('87', 'b730512de92c4689a87ed0fdd1e2bffc', '2');
INSERT INTO `t_web_user_role` VALUES ('93', 'b88bb916dc054870ae124d92710ac3d3', '2');
INSERT INTO `t_web_user_role` VALUES ('94', 'e97dadb6ad8f46e0937db079bc8de1c6', '11');
INSERT INTO `t_web_user_role` VALUES ('95', 'ad904a794a10434b8dec1de8ce23a288', '2');
INSERT INTO `t_web_user_role` VALUES ('96', 'c2bd6773d48643a9ac4540a551ba6ffb', '2');
