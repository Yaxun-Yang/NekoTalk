/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : neko

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 15/07/2020 05:23:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `momentId` int(11) NOT NULL,
  `replyCommentId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `commentTimeStamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `momentId`(`momentId`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, '15273299601', 6, '0', 'this is a comment', '2020-07-14 21:19:19.467000');
INSERT INTO `comment` VALUES (3, '15273299601', 6, '2', 'this is a comment', '2020-07-14 21:19:49.231000');
INSERT INTO `comment` VALUES (4, '15273299601', 6, '2', 'this is a comment', '2020-07-14 21:19:51.098000');
INSERT INTO `comment` VALUES (5, '15273299601', 6, '2', 'this is a comment', '2020-07-14 21:19:52.294000');
INSERT INTO `comment` VALUES (6, '15273299601', 6, '4', 'this is a comment', '2020-07-14 21:20:03.857000');
INSERT INTO `comment` VALUES (7, '15273299601', 6, '3', 'this is a comment', '2020-07-14 21:20:10.737000');

-- ----------------------------
-- Table structure for favour
-- ----------------------------
DROP TABLE IF EXISTS `favour`;
CREATE TABLE `favour`  (
  `momentId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`momentId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `favour_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `favour_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favour
-- ----------------------------
INSERT INTO `favour` VALUES (5, '15273299601');
INSERT INTO `favour` VALUES (6, '183-359-9357');
INSERT INTO `favour` VALUES (16, '183-359-9357');
INSERT INTO `favour` VALUES (18, '183-359-9357');

-- ----------------------------
-- Table structure for following
-- ----------------------------
DROP TABLE IF EXISTS `following`;
CREATE TABLE `following`  (
  `userPhoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `followingPhoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userPhoneNumber`, `followingPhoneNumber`) USING BTREE,
  INDEX `followingPhoneNumber`(`followingPhoneNumber`) USING BTREE,
  CONSTRAINT `following_ibfk_1` FOREIGN KEY (`userPhoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `following_ibfk_2` FOREIGN KEY (`followingPhoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of following
-- ----------------------------
INSERT INTO `following` VALUES ('15273299601', '15273299601');
INSERT INTO `following` VALUES ('15273299601', '222');

-- ----------------------------
-- Table structure for forkmoment
-- ----------------------------
DROP TABLE IF EXISTS `forkmoment`;
CREATE TABLE `forkmoment`  (
  `momentId` int(50) NOT NULL,
  `forkFrom` int(50) NOT NULL,
  PRIMARY KEY (`momentId`) USING BTREE,
  INDEX `forkForm`(`forkFrom`) USING BTREE,
  CONSTRAINT `forkmoment_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `forkmoment_ibfk_2` FOREIGN KEY (`forkFrom`) REFERENCES `originalitymoment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forkmoment
-- ----------------------------
INSERT INTO `forkmoment` VALUES (18, 6);
INSERT INTO `forkmoment` VALUES (24, 6);
INSERT INTO `forkmoment` VALUES (25, 6);
INSERT INTO `forkmoment` VALUES (26, 6);
INSERT INTO `forkmoment` VALUES (27, 6);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `labelId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`labelId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, '可爱猫猫');

-- ----------------------------
-- Table structure for labelinclude
-- ----------------------------
DROP TABLE IF EXISTS `labelinclude`;
CREATE TABLE `labelinclude`  (
  `momentId` int(11) NOT NULL,
  `labelId` int(11) NOT NULL,
  PRIMARY KEY (`momentId`, `labelId`) USING BTREE,
  INDEX `labelId`(`labelId`) USING BTREE,
  CONSTRAINT `labelinclude_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `originalitymoment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `labelinclude_ibfk_2` FOREIGN KEY (`labelId`) REFERENCES `label` (`labelId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of labelinclude
-- ----------------------------
INSERT INTO `labelinclude` VALUES (5, 1);

-- ----------------------------
-- Table structure for moment
-- ----------------------------
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment`  (
  `momentId` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `text` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `originality` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `powerTpye` int(11) NOT NULL,
  `momentTimeStamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`momentId`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `moment_ibfk_1` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moment
-- ----------------------------
INSERT INTO `moment` VALUES (5, '15273299601', 'after update moment', 'Y', 1, '2020-07-11 07:10:17.160000');
INSERT INTO `moment` VALUES (6, '15273299601', 'this is a moment', 'Y', 1, '2020-07-13 18:43:40.442000');
INSERT INTO `moment` VALUES (16, '15273299601', 'this is a moment', 'Y', 1, '2020-07-13 20:51:16.031000');
INSERT INTO `moment` VALUES (18, '15273299601', 'this is a moment', 'N', 1, '2020-07-13 20:51:57.737000');
INSERT INTO `moment` VALUES (19, '017-777-0420', 'this is a moment', 'Y', 3, '2020-07-14 21:12:19.880000');
INSERT INTO `moment` VALUES (20, '183-359-9357', 'this is a moment', 'Y', 4, '2020-07-14 21:12:40.801000');
INSERT INTO `moment` VALUES (21, '183-359-9357', 'this is a moment', 'Y', 1, '2020-07-14 21:12:54.936000');
INSERT INTO `moment` VALUES (22, '183-359-9357', 'this is a moment', 'Y', 2, '2020-07-14 21:13:01.985000');
INSERT INTO `moment` VALUES (23, '183-359-9357', 'this is a moment', 'Y', 3, '2020-07-14 21:13:06.500000');
INSERT INTO `moment` VALUES (24, '183-359-9357', 'this is a moment', 'N', 1, '2020-07-14 21:13:44.687000');
INSERT INTO `moment` VALUES (25, '183-359-9357', 'this is a moment', 'N', 1, '2020-07-14 21:13:51.512000');
INSERT INTO `moment` VALUES (26, '183-359-9357', 'this is a moment', 'N', 1, '2020-07-14 21:13:52.991000');
INSERT INTO `moment` VALUES (27, '183-359-9357', 'this is a moment', 'N', 1, '2020-07-14 21:13:54.248000');

-- ----------------------------
-- Table structure for momentpicture
-- ----------------------------
DROP TABLE IF EXISTS `momentpicture`;
CREATE TABLE `momentpicture`  (
  `pictureId` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `momentId` int(11) NOT NULL,
  PRIMARY KEY (`pictureId`) USING BTREE,
  INDEX `momentId`(`momentId`) USING BTREE,
  CONSTRAINT `momentpicture_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `originalitymoment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of momentpicture
-- ----------------------------
INSERT INTO `momentpicture` VALUES (4, 'http://qdeujgrtl.bkt.clouddn.com/04.jpg', 5);
INSERT INTO `momentpicture` VALUES (5, 'http://qdeujgrtl.bkt.clouddn.com/05.jpg', 5);
INSERT INTO `momentpicture` VALUES (6, 'http://qdeujgrtl.bkt.clouddn.com/06.jpg', 5);
INSERT INTO `momentpicture` VALUES (8, 'http://qdeujgrtl.bkt.clouddn.com/08.jpg', 5);
INSERT INTO `momentpicture` VALUES (9, 'http://qdeujgrtl.bkt.clouddn.com/09.jpg', 5);
INSERT INTO `momentpicture` VALUES (10, 'http://qdeujgrtl.bkt.clouddn.com/10.jpg', 6);
INSERT INTO `momentpicture` VALUES (11, 'http://qdeujgrtl.bkt.clouddn.com/11.jpg', 6);

-- ----------------------------
-- Table structure for originalitymoment
-- ----------------------------
DROP TABLE IF EXISTS `originalitymoment`;
CREATE TABLE `originalitymoment`  (
  `momentId` int(11) NOT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`momentId`) USING BTREE,
  CONSTRAINT `originalitymoment_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of originalitymoment
-- ----------------------------
INSERT INTO `originalitymoment` VALUES (5, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (6, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (16, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (19, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (20, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (21, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (22, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (23, '北京市中南海');

-- ----------------------------
-- Table structure for powerusers
-- ----------------------------
DROP TABLE IF EXISTS `powerusers`;
CREATE TABLE `powerusers`  (
  `momentId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`momentId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `powerusers_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `powerusers_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of powerusers
-- ----------------------------
INSERT INTO `powerusers` VALUES (19, '183-359-9357');
INSERT INTO `powerusers` VALUES (20, '183-359-9357');
INSERT INTO `powerusers` VALUES (23, '183-359-9357');
INSERT INTO `powerusers` VALUES (23, '448-320-1397');

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `sessionId` int(11) NOT NULL AUTO_INCREMENT,
  `more` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTimeStamp` timestamp(0) NOT NULL,
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES (1, 'Y', '2020-07-14 16:49:44');
INSERT INTO `session` VALUES (2, 'Y', '2020-07-14 16:52:17');

-- ----------------------------
-- Table structure for sessionjoin
-- ----------------------------
DROP TABLE IF EXISTS `sessionjoin`;
CREATE TABLE `sessionjoin`  (
  `sessionId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`sessionId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `sessionjoin_ibfk_1` FOREIGN KEY (`sessionId`) REFERENCES `session` (`sessionId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sessionjoin_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sessionjoin
-- ----------------------------
INSERT INTO `sessionjoin` VALUES (2, '15273299601');
INSERT INTO `sessionjoin` VALUES (2, '18307331878');

-- ----------------------------
-- Table structure for sessionmessage
-- ----------------------------
DROP TABLE IF EXISTS `sessionmessage`;
CREATE TABLE `sessionmessage`  (
  `messageId` int(50) NOT NULL AUTO_INCREMENT,
  `sessionId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `text` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sessionTimeStamp` timestamp(0) NOT NULL,
  PRIMARY KEY (`messageId`) USING BTREE,
  INDEX `sessionmessage1`(`sessionId`) USING BTREE,
  INDEX `sessionmessage2`(`phoneNumber`) USING BTREE,
  CONSTRAINT `sessionmessage1` FOREIGN KEY (`sessionId`) REFERENCES `session` (`sessionId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sessionmessage2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sessionmessage
-- ----------------------------
INSERT INTO `sessionmessage` VALUES (1, 2, '15273299601', 'hello', NULL, '2020-07-14 17:01:23');
INSERT INTO `sessionmessage` VALUES (2, 2, '15273299601', 'hello', NULL, '2020-07-14 17:02:24');
INSERT INTO `sessionmessage` VALUES (3, 2, '15273299601', 'hello', NULL, '2020-07-14 17:03:07');
INSERT INTO `sessionmessage` VALUES (4, 2, '15273299601', 'hello', NULL, '2020-07-14 17:03:30');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sign` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lastAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`phoneNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('017-777-0420', 'zhenzhen', '698d51a19d8a12', 'http://qdeujgrtl.bkt.clouddn.com/avatar017-777-0420.jpg', ' female', 'sign ', '湖南省湘潭市');
INSERT INTO `users` VALUES ('15273299601', 'han', '111', NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('183-359-9357', 'zhenzhen', '698d51a19d8a12', 'http://qdeujgrtl.bkt.clouddn.com/avatar183-359-9357.jpg', ' female', 'sign ', '湖南省湘潭市');
INSERT INTO `users` VALUES ('18307331878', 'zhenzhen', '111', 'http://qdeujgrtl.bkt.clouddn.com/avatar18307331878.jpg', ' female', 'sign ', '湖南省湘潭市');
INSERT INTO `users` VALUES ('222', 'chen', '222', NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('448-320-1397', 'zhenzhen', '698d51a19d8a12', 'http://qdeujgrtl.bkt.clouddn.com/avatar448-320-1397.jpg', ' female', 'sign ', '湖南省湘潭市');
INSERT INTO `users` VALUES ('930-498-4346', 'zhenzhen', '698d51a19d8a12', 'http://qdeujgrtl.bkt.clouddn.com/avatar930-498-4346.jpg', ' female', 'sign ', '湖南省湘潭市');

SET FOREIGN_KEY_CHECKS = 1;
