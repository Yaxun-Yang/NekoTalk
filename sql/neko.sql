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

 Date: 15/07/2020 20:50:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `momentId` int(11) NOT NULL,
  `replyCommentId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `commentTimeStamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `momentId`(`momentId`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, '15273299601', 5, '0', 'this is a comment2', '2020-07-14 14:30:19.692000');
INSERT INTO `comment` VALUES (3, '15273299601', 5, '1', 'this is a moment 1', '2020-07-15 19:26:34.416668');
INSERT INTO `comment` VALUES (4, '15273299601', 5, '0', 'this is a moment 3', '2020-07-15 19:27:24.632283');

-- ----------------------------
-- Table structure for favour
-- ----------------------------
DROP TABLE IF EXISTS `favour`;
CREATE TABLE `favour`  (
  `momentId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`momentId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `favour_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favour_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favour
-- ----------------------------
INSERT INTO `favour` VALUES (1, '15273299601');
INSERT INTO `favour` VALUES (2, '15273299601');
INSERT INTO `favour` VALUES (3, '15273299601');
INSERT INTO `favour` VALUES (18, '15273299601');

-- ----------------------------
-- Table structure for following
-- ----------------------------
DROP TABLE IF EXISTS `following`;
CREATE TABLE `following`  (
  `userPhoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `followingPhoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`userPhoneNumber`, `followingPhoneNumber`) USING BTREE,
  INDEX `followingPhoneNumber`(`followingPhoneNumber`) USING BTREE,
  CONSTRAINT `following_ibfk_1` FOREIGN KEY (`userPhoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `following_ibfk_2` FOREIGN KEY (`followingPhoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  CONSTRAINT `forkmoment_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forkmoment_ibfk_2` FOREIGN KEY (`forkFrom`) REFERENCES `originalitymoment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`labelId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, '可爱猫猫');
INSERT INTO `label` VALUES (2, '橘猫');
INSERT INTO `label` VALUES (3, '白毛猫');

-- ----------------------------
-- Table structure for labelinclude
-- ----------------------------
DROP TABLE IF EXISTS `labelinclude`;
CREATE TABLE `labelinclude`  (
  `momentId` int(11) NOT NULL,
  `labelId` int(11) NOT NULL,
  PRIMARY KEY (`momentId`, `labelId`) USING BTREE,
  INDEX `labelId`(`labelId`) USING BTREE,
  CONSTRAINT `labelinclude_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `originalitymoment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `labelinclude_ibfk_2` FOREIGN KEY (`labelId`) REFERENCES `label` (`labelId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of labelinclude
-- ----------------------------
INSERT INTO `labelinclude` VALUES (2, 1);
INSERT INTO `labelinclude` VALUES (5, 1);
INSERT INTO `labelinclude` VALUES (8, 1);
INSERT INTO `labelinclude` VALUES (10, 1);
INSERT INTO `labelinclude` VALUES (6, 2);
INSERT INTO `labelinclude` VALUES (11, 2);
INSERT INTO `labelinclude` VALUES (16, 2);
INSERT INTO `labelinclude` VALUES (1, 3);
INSERT INTO `labelinclude` VALUES (9, 3);

-- ----------------------------
-- Table structure for moment
-- ----------------------------
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment`  (
  `momentId` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `text` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `originality` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `powerTpye` int(11) NOT NULL,
  `momentTimeStamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`momentId`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `moment_ibfk_1` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of moment
-- ----------------------------
INSERT INTO `moment` VALUES (1, '222', 'a moment', 'Y', 1, '2020-07-15 01:49:49.226814');
INSERT INTO `moment` VALUES (2, '222', 'a moment', 'Y', 1, '2020-07-15 01:50:14.106298');
INSERT INTO `moment` VALUES (3, '18307331878', 'this is a moment', 'Y', 1, '2020-07-15 03:45:29.106801');
INSERT INTO `moment` VALUES (4, '18307331878', 'moment', 'Y', 1, '2020-07-15 03:46:01.274799');
INSERT INTO `moment` VALUES (5, '15273299601', 'after update moment', 'Y', 1, '2020-07-11 07:10:17.160000');
INSERT INTO `moment` VALUES (6, '15273299601', 'this is a moment', 'Y', 1, '2020-07-13 18:43:40.442000');
INSERT INTO `moment` VALUES (8, '222', 'moment', 'Y', 1, '2020-07-15 06:11:43.670298');
INSERT INTO `moment` VALUES (9, '222', 'this is a', 'Y', 1, '2020-07-15 06:12:03.386392');
INSERT INTO `moment` VALUES (10, '222', 'hello', 'Y', 1, '2020-07-15 06:12:12.664369');
INSERT INTO `moment` VALUES (11, '15273299601', 'hihihi', 'Y', 1, '2020-07-15 20:44:03.459294');
INSERT INTO `moment` VALUES (16, '15273299601', 'this is a moment', 'Y', 1, '2020-07-13 20:51:16.031000');
INSERT INTO `moment` VALUES (18, '15273299601', 'this is a moment', 'N', 1, '2020-07-13 20:51:57.737000');
INSERT INTO `moment` VALUES (31, '222', 'good afternoon', 'Y', 3, '2020-07-15 12:49:26.113000');

-- ----------------------------
-- Table structure for momentpicture
-- ----------------------------
DROP TABLE IF EXISTS `momentpicture`;
CREATE TABLE `momentpicture`  (
  `pictureId` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `momentId` int(11) NOT NULL,
  PRIMARY KEY (`pictureId`) USING BTREE,
  INDEX `momentId`(`momentId`) USING BTREE,
  CONSTRAINT `momentpicture_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `originalitymoment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of momentpicture
-- ----------------------------
INSERT INTO `momentpicture` VALUES (1, 'http://qdeujgrtl.bkt.clouddn.com/01.JPG', 8);
INSERT INTO `momentpicture` VALUES (2, 'http://qdeujgrtl.bkt.clouddn.com/06.JPG', 3);
INSERT INTO `momentpicture` VALUES (3, 'http://qdeujgrtl.bkt.clouddn.com/01.JPG', 6);
INSERT INTO `momentpicture` VALUES (4, 'http://qdeujgrtl.bkt.clouddn.com/02.JPG', 16);
INSERT INTO `momentpicture` VALUES (5, 'http://qdeujgrtl.bkt.clouddn.com/04.JPG', 2);
INSERT INTO `momentpicture` VALUES (6, 'momentPicture6.jpg', 1);
INSERT INTO `momentpicture` VALUES (7, 'http://qdeujgrtl.bkt.clouddn.com/10.JPG', 9);
INSERT INTO `momentpicture` VALUES (8, 'http://qdeujgrtl.bkt.clouddn.com/momentPicture8.jpg', 10);
INSERT INTO `momentpicture` VALUES (9, 'http://qdeujgrtl.bkt.clouddn.com/02.JPG', 11);
INSERT INTO `momentpicture` VALUES (51, 'http://qdeujgrtl.bkt.clouddn.com/05.JPG', 5);
INSERT INTO `momentpicture` VALUES (52, 'http://qdeujgrtl.bkt.clouddn.com/04.JPG', 5);
INSERT INTO `momentpicture` VALUES (53, 'http://qdeujgrtl.bkt.clouddn.com/06.JPG', 5);

-- ----------------------------
-- Table structure for originalitymoment
-- ----------------------------
DROP TABLE IF EXISTS `originalitymoment`;
CREATE TABLE `originalitymoment`  (
  `momentId` int(11) NOT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`momentId`) USING BTREE,
  CONSTRAINT `originalitymoment_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of originalitymoment
-- ----------------------------
INSERT INTO `originalitymoment` VALUES (1, '1');
INSERT INTO `originalitymoment` VALUES (2, '2');
INSERT INTO `originalitymoment` VALUES (3, '四川省成都市');
INSERT INTO `originalitymoment` VALUES (4, '湖南省株洲市芦淞区');
INSERT INTO `originalitymoment` VALUES (5, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (6, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (8, '四川省成都市');
INSERT INTO `originalitymoment` VALUES (9, '四川省成都市');
INSERT INTO `originalitymoment` VALUES (10, '四川省成都市');
INSERT INTO `originalitymoment` VALUES (11, '四川省成都市');
INSERT INTO `originalitymoment` VALUES (16, '北京市中南海');
INSERT INTO `originalitymoment` VALUES (31, '湖南省湘潭市');

-- ----------------------------
-- Table structure for powerusers
-- ----------------------------
DROP TABLE IF EXISTS `powerusers`;
CREATE TABLE `powerusers`  (
  `momentId` int(11) NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`momentId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `powerusers_ibfk_1` FOREIGN KEY (`momentId`) REFERENCES `moment` (`momentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `powerusers_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of powerusers
-- ----------------------------

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `sessionId` int(11) NOT NULL AUTO_INCREMENT,
  `more` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTimeStamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sessionId`, `phoneNumber`) USING BTREE,
  INDEX `phoneNumber`(`phoneNumber`) USING BTREE,
  CONSTRAINT `sessionjoin_ibfk_1` FOREIGN KEY (`sessionId`) REFERENCES `session` (`sessionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sessionjoin_ibfk_2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `text` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sessionTimeStamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`messageId`) USING BTREE,
  INDEX `sessionmessage1`(`sessionId`) USING BTREE,
  INDEX `sessionmessage2`(`phoneNumber`) USING BTREE,
  CONSTRAINT `sessionmessage1` FOREIGN KEY (`sessionId`) REFERENCES `session` (`sessionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sessionmessage2` FOREIGN KEY (`phoneNumber`) REFERENCES `users` (`phoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `phoneNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sign` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `lastAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`phoneNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('15273299601', 'han', '111', 'http://qdeujgrtl.bkt.clouddn.com/05.JPG', 'male', '你好我是个性签名', '湖南省湘潭市');
INSERT INTO `users` VALUES ('18307331878', 'zhenzhen', '111', 'http://qdeujgrtl.bkt.clouddn.com/02.JPG', ' female', 'sign ', '湖南省湘潭市');
INSERT INTO `users` VALUES ('222', 'chen', '222', 'http://qdeujgrtl.bkt.clouddn.com/01.JPG', ' female', '你好我是个性签名', '湖南省湘潭市');

SET FOREIGN_KEY_CHECKS = 1;
