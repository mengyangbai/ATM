
SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for cash
-- ----------------------------
DROP TABLE IF EXISTS `cash`;
CREATE TABLE `cash`  (
  `VALUE` int(5) NOT NULL COMMENT 'The type of money\r\n',
  `COUNT` int(5) NOT NULL COMMENT 'The count of the money'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cash
-- ----------------------------
INSERT INTO `cash` VALUES (50, 50);
INSERT INTO `cash` VALUES (20, 50);
INSERT INTO `cash` VALUES (100, 50);
INSERT INTO `cash` VALUES (10, 50);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `ID` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `METHOD` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `REQUEST` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `RESPONSE` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `STATUS` int(5) NULL DEFAULT NULL,
  `CREATE_DATE` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

