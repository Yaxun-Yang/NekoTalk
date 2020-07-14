/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : neko

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 14/07/2020 13:39:08
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for momentpicture
-- ----------------------------
DROP TABLE IF EXISTS `momentpicture`;
CREATE TABLE `momentpicture`  (
  `pictureId` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `momentId` int(11) NOT NULL,
  PRIMARY KEY (`pictureId`) USING BTREE,
  INDEX `momentId`(`momentId`) USING BTREE,
  CONSTRAINT `momentpicture_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `originalitymoment` (`momentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of momentpicture
-- ----------------------------
INSERT INTO `momentpicture` VALUES (2, 'temp', 5);

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
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `sessionId` int(11) NOT NULL AUTO_INCREMENT,
  `more` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTimestamp` timestamp(0) NOT NULL,
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
-- Table structure for sessionmessage
-- ----------------------------
DROP TABLE IF EXISTS `sessionmessage`;
CREATE TABLE `sessionmessage`  (
  `sessionId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `text` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` timestamp(0) NOT NULL,
  PRIMARY KEY (`sessionId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `sessionmessage_ibfk_1` FOREIGN KEY (`sessionId`) REFERENCES `session` (`sessionId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sessionmessage_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sessionpicture
-- ----------------------------
DROP TABLE IF EXISTS `sessionpicture`;
CREATE TABLE `sessionpicture`  (
  `sessionId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` timestamp(0) NOT NULL,
  PRIMARY KEY (`sessionId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `sessionpicture_ibfk_1` FOREIGN KEY (`sessionId`) REFERENCES `session` (`sessionId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sessionpicture_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sign` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lastAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`phoneNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('15273299601', 'han', '111', NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('18307331878', 'zhenzhen', '111', 'momentPicture18307331878.jpg', ' female', 'sign ', '湖南省湘潭市');
INSERT INTO `users` VALUES ('222', 'chen', '222', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
