CREATE TABLE IF NOT EXISTS book(
                                   `bid` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `title` varchar(255) DEFAULT NULL,
                                   `author` varchar(255) DEFAULT NULL,
                                   `type` varchar(255) DEFAULT NULL,
                                   `price` decimal(10,2) DEFAULT NULL,
                                   `inventory` int(11) DEFAULT NULL,
                                   `description` varchar(2000) DEFAULT NULL,
                                   `isbn` varchar(255) DEFAULT NULL,
                                   `image` varchar(255) DEFAULT NULL,
                                   PRIMARY KEY (`bid`)
);

-- INSERT INTO `book` VALUES ('1', '指环王', 'J.R.R'.托尔金', '奇幻', '99', '100', '一部由J.R.R.托尔金创作的经典奇幻小说。跟随这场毁灭魔戒的史诗征程。', '978-7-115-19577-7', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.kEVMWloCZ4F8nF4WrF3vjwHaKH?pid=ImgDet&rs=1');
-- INSERT INTO `book` VALUES ('2', '霍比特人', 'J.R.R.托尔金', '奇幻', '99', '100', 'J.R.R.托尔金创作的冒险奇幻小说。加入比尔博·巴金斯去夺回孤山的探险。', '978-7-115-19614-9', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.6uCByXgC5dgqBfOg-0WujAHaK-?rs=1&pid=ImgDetMain');
-- INSERT INTO `book` VALUES ('3', '精灵宝钻', 'J.R.R.托尔金', '奇幻', '99', '100', 'J.R.R.托尔金创作的神话故事集。探索中土丰富的历史。', '978-7-101-05502-0', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.bE1yNTv-JDS9Wm1cYNaRMQHaLt?pid=ImgDet&rs=1');
-- INSERT INTO `book` VALUES ('4', '胡林的孩子们', 'J.R.R.托尔金', '儿童奇幻', '99', '100', 'J.R.R.托尔金编写的引人入胜的儿童奇幻小说。跟随图林·图兰巴的冒险。', '978-7-115-36788-7', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.NxyhPzdI7AuLEZB9KvHpowHaLZ?pid=ImgDet&rs=1');
-- INSERT INTO `book` VALUES ('5', '贡多林的陨落', 'J.R.R.托尔金', '神话奇幻', '99', '100', 'J.R.R.托尔金创作的迷人神话奇幻小说。见证精灵之城贡多林的陨落。', '978-7-115-19577-7', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.7RCCzzhFxpSaS2A_tvHQ1AHaLJ?pid=ImgDet&rs=1');
-- INSERT INTO `book` VALUES ('6', '失落的故事集', 'J.R.R.托尔金', '经典奇幻', '99', '100', 'J.R.R.托尔金创作的经典奇幻小说。探索中土早期的故事和神话。', '978-7-115-19614-9', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.4l6eUc9AKtdtuwowv4GnnAHaMI?pid=ImgDet&rs=1');
-- INSERT INTO `book` VALUES ('7', '失落的故事集 2', 'J.R.R.托尔金', '经典奇幻', '99', '100', 'J.R.R.托尔金创作的经典奇幻小说。延续中土失落的故事。', '978-7-115-19614-8', 'https://i.thenile.io/r1000/9780780715479.jpg?r=5ec815c466162');
-- INSERT INTO `book` VALUES ('8', '贝勒里安德之歌', 'J.R.R.托尔金', '经典奇幻', '99', '100', 'J.R.R.托尔金创作的经典奇幻小说。沉浸于贝勒里安德的诗歌故事。', '978-7-101-05502-0', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.KHsmolneQDRI77cMQcBaZwHaL9?pid=ImgDet&rs=1');
-- INSERT INTO `book` VALUES ('9', '活着', '余华', '小说', '29.90', '100', '描述了一位普通农民的命运沉浮，深刻反映了人性的弱点和坚韧。', '978-7-115-19577-7', 'https://img13.360buyimg.com/n1/jfs/t1/117523/11/7420/83736/5ec3a966E3b2deceb/ce64a8c509cc4307.jpg');
-- INSERT INTO `book` VALUES ('10', '平凡的世界', '余华', '小说', '39.90', '80', '讲述了农村知识青年冯子材的成长和奋斗故事，是中国文学的经典之作。', '978-7-115-19614-9', 'https://ts1.cn.mm.bing.net/th/id/R-C.98f5b060b770fb6d8ef0a9cc483823db?rik=Fj9DBoCGpkleUg&riu=http%3a%2f%2fimage31.bookschina.com%2f2017%2fzuo%2f8%2f7515842.jpg&ehk=VHnVHhNtp2yVbXjssJ0%2fKzWKnpUus4Dto8Ok59RKewY%3d&risl=&pid=ImgRaw&r=0');
-- INSERT INTO `book` VALUES ('11', '白夜行', '东野圭吾', '推理小说', '32.50', '120', '一段黑暗而扣人心弦的故事，探讨了罪与罚、爱与复仇之间的复杂关系。', '978-7-101-05502-0', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.-oMw4HZKTRTFrnAXhML48AHaHa?rs=1&pid=ImgDetMain');
-- INSERT INTO `book` VALUES ('12', '围城', '钱钟书', '小说', '26.80', '90', '描写了一位年轻知识分子在婚姻、事业、理想等方面的纠结和抉择。', '978-7-02-012345-6', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.4BpStSuSEQI9HjTrcO54rQHaLo?rs=1&pid=ImgDetMain');
-- INSERT INTO `book` VALUES ('13', '解忧杂货铺', '东野圭吾', '小说', '29.80', '110', '主要讲述了一家以解决客人烦恼为目标的杂货店的故事，充满温情和智慧。', '978-7-115-36788-7', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.M8nPKz4TWfcnmyE_lKdlXQHaKh?rs=1&pid=ImgDetMain');
-- INSERT INTO `book` VALUES ('14', '时间简史', '史蒂芬·霍金', '科普', '45.00', '90', '史蒂芬·霍金的科普经典，解读宇宙起源、黑洞等物理现象。', '978-7-100-00000-0', 'https://so1.360tres.com/t0151ae7fb7ec6d8870.jpg');
-- INSERT INTO `book` VALUES ('15', '人类简史', '尤瓦尔·赫拉利', '历史', '59.00', '120', '探讨人类历史发展的巨著，从史前时代到现代社会。', '978-7-200-00000-0', 'https://pic1.zhimg.com/v2-eb163921065032e081e825082898ed7d_r.jpg');
-- INSERT INTO `book` VALUES ('16', '三体', '刘慈欣', '科幻小说', '32.00', '150', '刘慈欣的科幻巨作，描绘人类与外星文明的交锋。', '978-7-000-00000-0', 'https://img.zcool.cn/community/01fca15bffd6b0a80121ab5d6723c4.jpg@1280w_1l_2o_100sh.jpg');
-- INSERT INTO `book` VALUES ('17', '红楼梦', '曹雪芹', '古典小说', '28.00', '100', '中国古典小说经典之一，描绘贾宝玉等人的家族命运。', '978-7-300-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.46c31ba64bd77ed8a19dad9b77cc11a5?rik=7jgPCHk5BkBu0w&riu=http%3a%2f%2fimage31.bookschina.com%2f2017%2fzuo%2f7%2f7535210.jpg&ehk=iVZ18Tns76tRWoFYLZqV5Zs7oLiBRGcYdDmvdCWBbEA%3d&risl=&pid=ImgRaw&r=0');
-- INSERT INTO `book` VALUES ('18', '1984', '乔治·奥威尔', '文学', '26.80', '80', '反乌托邦文学经典之一，警示社会极权主义的危险。', '978-7-400-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.8a1ffa5adcc0af2835605042e315d4c6?rik=YfayV00r5VTBlQ&riu=http%3a%2f%2fpic.baike.soso.com%2fp%2f20131227%2f20131227112451-430284487.jpg&ehk=xzdjr3UPz4Mr93TT2PclvXt5B2yTagSZQP%2bVca6vwQc%3d&risl=&pid=ImgRaw&r=0');
-- INSERT INTO `book` VALUES ('19', '活着为了讲述', '加西亚·马尔克斯', '传记', '38.00', '110', '诺贝尔文学奖得主加西亚·马尔克斯的传记，生动讲述他的一生。', '978-7-500-00000-0', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.9Pb6IWeNM82VeWtIx3SrsgHaJm?rs=1&pid=ImgDetMain');
-- INSERT INTO `book` VALUES ('20', '哈利·波特', 'J.K.罗琳', '奇幻', '45.50', '130', 'J.K.罗琳创作的畅销奇幻系列，描绘年轻巫师哈利·波特的冒险。', '978-7-600-00000-0', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.Q50gSoIfu4H7amyHq9mjoQHaK-?rs=1&pid=ImgDetMain');
-- INSERT INTO `book` VALUES ('21', '心理学与生活', '理查德·格里格', '心理学', '48.90', '100', '心理学经典教材，介绍心理学的基本理论和研究方法。', '978-7-700-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.d78d43d28152b31bda61588a32efbe8f?rik=Mvz3g85y7D0b2Q&riu=http%3a%2f%2fimg36.ddimg.cn%2f38%2f2%2f23615696-1_u_2.jpg&ehk=rU8uT6P9CPwHs01JLT8sGbeT2hZEH24Nlm6e3MB5%2fhw%3d&risl=&pid=ImgRaw&r=0');
-- INSERT INTO `book` VALUES ('22', '莎士比亚全集', '威廉·莎士比亚', '戏剧', '55.00', '120', '包含莎士比亚所有戏剧作品的全集，文学经典之一。', '978-7-800-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.f41d73a6918fc22f84ca1c10769ac12f?rik=%2bmggex2Bx4AD%2bA&riu=http%3a%2f%2fimage12.bookschina.com%2f2019%2f20190909%2f1%2f7891464.jpg&ehk=EF3iP0UJIkTMiF7NzPyTFbgAC03sbuRXTieDm%2fiDqeE%3d&risl=&pid=ImgRaw&r=0');
-- INSERT INTO `book` VALUES ('23', '计算机科学导论', '艾伦·图灵', '计算机科学', '36.50', '90', '计算机科学经典教材，介绍计算机科学的基本概念和原理。', '978-7-900-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.a80ae92c98482b97222dfe302be503ae?rik=hjrNTFyCAfKdzA&riu=http%3a%2f%2fs.cmpedu.com%2fimages%2fupload%2f2019%2f4%2f7%2f1558375987033(LT800).jpg&ehk=rFUPBlmy154li6C%2fbXdIri7Hot8IZNaji9fvHtecxgA%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `book` VALUES ('1', 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', '99', '100', 'A classic fantasy novel written by J.R.R. Tolkien. Follow the epic journey to destroy the One Ring.', '978-7-115-19577-7', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.kEVMWloCZ4F8nF4WrF3vjwHaKH?pid=ImgDet&rs=1');
INSERT INTO `book` VALUES ('2', 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy', '99', '100', 'An adventure fantasy novel by J.R.R. Tolkien. Join Bilbo Baggins in the quest to reclaim Lonely Mountain.', '978-7-115-19614-9', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.6uCByXgC5dgqBfOg-0WujAHaK-?rs=1&pid=ImgDetMain');
INSERT INTO `book` VALUES ('3', 'The Silmarillion', 'J.R.R. Tolkien', 'Fantasy', '99', '100', 'A collection of mythic stories by J.R.R. Tolkien. Explore the rich history of Middle-earth.', '978-7-101-05502-0', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.bE1yNTv-JDS9Wm1cYNaRMQHaLt?pid=ImgDet&rs=1');
INSERT INTO `book` VALUES ('4', 'The Children of Húrin', 'J.R.R. Tolkien', 'Children''s Fantasy', '99', '100', 'A captivating children''s fantasy novel by J.R.R. Tolkien. Follow the adventures of Túrin Turambar.', '978-7-115-36788-7', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.NxyhPzdI7AuLEZB9KvHpowHaLZ?pid=ImgDet&rs=1');
INSERT INTO `book` VALUES ('5', 'The Fall of Gondolin', 'J.R.R. Tolkien', 'Mythical Fantasy', '99', '100', 'An enchanting mythical fantasy novel by J.R.R. Tolkien. Witness the fall of the Elven city Gondolin.', '978-7-115-19577-7', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.7RCCzzhFxpSaS2A_tvHQ1AHaLJ?pid=ImgDet&rs=1');
INSERT INTO `book` VALUES ('6', 'The Lost Road and Other Writings', 'J.R.R. Tolkien', 'Classic Fantasy', '99', '100', 'A classic fantasy novel by J.R.R. Tolkien. Explore early stories and myths of Middle-earth.', '978-7-115-19614-9', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.4l6eUc9AKtdtuwowv4GnnAHaMI?pid=ImgDet&rs=1');
INSERT INTO `book` VALUES ('7', 'The Lost Road and Other Writings, Part 2', 'J.R.R. Tolkien', 'Classic Fantasy', '99', '100', 'A continuation of the lost stories of Middle-earth by J.R.R. Tolkien.', '978-7-115-19614-8', 'https://i.thenile.io/r1000/9780780715479.jpg?r=5ec815c466162');
INSERT INTO `book` VALUES ('8', 'The Lays of Beleriand', 'J.R.R. Tolkien', 'Classic Fantasy', '99', '100', 'Immerse yourself in the poetic stories of Beleriand by J.R.R. Tolkien.', '978-7-101-05502-0', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.KHsmolneQDRI77cMQcBaZwHaL9?pid=ImgDet&rs=1');
INSERT INTO `book` VALUES ('9', 'To Live', 'Yu Hua', 'Fiction', '29.90', '100', 'Describes the fate of an ordinary farmer, reflecting the weaknesses and resilience of human nature.', '978-7-115-19577-7', 'https://img13.360buyimg.com/n1/jfs/t1/117523/11/7420/83736/5ec3a966E3b2deceb/ce64a8c509cc4307.jpg');
INSERT INTO `book` VALUES ('10', 'The Ordinary World', 'Yu Hua', 'Fiction', '39.90', '80', 'Tells the story of rural intellectual Fang Zecai''s growth and struggle, a classic in Chinese literature.', '978-7-115-19614-9', 'https://ts1.cn.mm.bing.net/th/id/R-C.98f5b060b770fb6d8ef0a9cc483823db?rik=Fj9DBoCGpkleUg&riu=http%3a%2f%2fimage31.bookschina.com%2f2017%2fzuo%2f8%2f7515842.jpg&ehk=VHnVHhNtp2yVbXjssJ0%2fKzWKnpUus4Dto8Ok59RKewY%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `book` VALUES ('11', 'Journey Under the Midnight Sun', 'Higashino Keigo', 'Mystery', '32.50', '120', 'A dark and gripping story by Higashino Keigo, exploring the complex relationships between crime, punishment, love, and revenge.', '978-7-101-05502-0', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.-oMw4HZKTRTFrnAXhML48AHaHa?rs=1&pid=ImgDetMain');
INSERT INTO `book` VALUES ('12', 'Fortress Besieged', 'Qian Zhongshu', 'Fiction', '26.80', '90', 'Depicts the entanglements and choices of a young intellectual in marriage, career, and ideals.', '978-7-02-012345-6', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.4BpStSuSEQI9HjTrcO54rQHaLo?rs=1&pid=ImgDetMain');
INSERT INTO `book` VALUES ('13', 'The Miracles of the Namiya General Store', 'Higashino Keigo', 'Fiction', '29.80', '110', 'Mainly tells the story of a grocery store dedicated to solving customers'' troubles, full of warmth and wisdom.', '978-7-115-36788-7', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.M8nPKz4TWfcnmyE_lKdlXQHaKh?rs=1&pid=ImgDetMain');
INSERT INTO `book` VALUES ('14', 'A Brief History of Time', 'Stephen Hawking', 'Science', '45.00', '90', 'Stephen Hawking''s classic popular science book, interpreting phenomena such as the origin of the universe and black holes.', '978-7-100-00000-0', 'https://so1.360tres.com/t0151ae7fb7ec6d8870.jpg');
INSERT INTO `book` VALUES ('15', 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'History', '59.00', '120', 'A monumental work exploring the development of human history from prehistoric times to modern society.', '978-7-200-00000-0', 'https://pic1.zhimg.com/v2-eb163921065032e081e825082898ed7d_r.jpg');
INSERT INTO `book` VALUES ('16', 'The Three-Body Problem', 'Liu Cixin', 'Science Fiction', '32.00', '150', 'Liu Cixin''s science fiction masterpiece, depicting the confrontation between humanity and extraterrestrial civilizations.', '978-7-000-00000-0', 'https://img.zcool.cn/community/01fca15bffd6b0a80121ab5d6723c4.jpg@1280w_1l_2o_100sh.jpg');
INSERT INTO `book` VALUES ('17', 'Dream of the Red Chamber', 'Cao Xueqin', 'Classical Fiction', '28.00', '100', 'One of the classics of Chinese classical fiction, depicting the fate of Jia Baoyu and others in their family.', '978-7-300-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.46c31ba64bd77ed8a19dad9b77cc11a5?rik=7jgPCHk5BkBu0w&riu=http%3a%2f%2fimage31.bookschina.com%2f2017%2fzuo%2f7%2f7535210.jpg&ehk=iVZ18Tns76tRWoFYLZqV5Zs7oLiBRGcYdDmvdCWBbEA%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `book` VALUES ('18', '1984', 'George Orwell', 'Literature', '26.80', '80', 'One of the classics of dystopian literature, warning against the dangers of societal totalitarianism.', '978-7-400-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.8a1ffa5adcc0af2835605042e315d4c6?rik=YfayV00r5VTBlQ&riu=http%3a%2f%2fpic.baike.soso.com%2fp%2f20131227%2f20131227112451-430284487.jpg&ehk=xzdjr3UPz4Mr93TT2PclvXt5B2yTagSZQP%2bVca6vwQc%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `book` VALUES ('19', 'Living to Tell the Tale', 'Gabriel García Márquez', 'Biography', '38.00', '110', 'The biography of Nobel Prize-winning author Gabriel García Márquez, vividly narrating his life.', '978-7-500-00000-0', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.9Pb6IWeNM82VeWtIx3SrsgHaJm?rs=1&pid=ImgDetMain');
INSERT INTO `book` VALUES ('20', 'Harry Potter Series', 'J.K. Rowling', 'Fantasy', '45.50', '130', 'Bestselling fantasy series by J.K. Rowling, depicting the adventures of young wizard Harry Potter.', '978-7-600-00000-0', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.Q50gSoIfu4H7amyHq9mjoQHaK-?rs=1&pid=ImgDetMain');
INSERT INTO `book` VALUES ('21', 'Psychology and Life', 'Richard J. Gerrig', 'Psychology', '48.90', '100', 'A classic textbook in psychology, introducing the fundamental theories and research methods of psychology.', '978-7-700-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.d78d43d28152b31bda61588a32efbe8f?rik=Mvz3g85y7D0b2Q&riu=http%3a%2f%2fimg36.ddimg.cn%2f38%2f2%2f23615696-1_u_2.jpg&ehk=rU8uT6P9CPwHs01JLT8sGbeT2hZEH24Nlm6e3MB5%2fhw%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `book` VALUES ('22', 'Complete Works of William Shakespeare', 'William Shakespeare', 'Drama', '55.00', '120', 'A complete collection of all the plays by William Shakespeare, one of the literary classics.', '978-7-800-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.f41d73a6918fc22f84ca1c10769ac12f?rik=%2bmggex2Bx4AD%2bA&riu=http%3a%2f%2fimage12.bookschina.com%2f2019%2f20190909%2f1%2f7891464.jpg&ehk=EF3iP0UJIkTMiF7NzPyTFbgAC03sbuRXTieDm%2fiDqeE%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `book` VALUES ('23', 'Introduction to Computer Science', 'Alan Turing', 'Computer Science', '36.50', '90', 'A classic textbook in computer science, introducing the basic concepts and principles of computer science.', '978-7-900-00000-0', 'https://ts1.cn.mm.bing.net/th/id/R-C.a80ae92c98482b97222dfe302be503ae?rik=hjrNTFyCAfKdzA&riu=http%3a%2f%2fs.cmpedu.com%2fimages%2fupload%2f2019%2f4%2f7%2f1558375987033(LT800).jpg&ehk=rFUPBlmy154li6C%2fbXdIri7Hot8IZNaji9fvHtecxgA%3d&risl=&pid=ImgRaw&r=0');


-- ----------------------------
-- Table structure for user
-- ----------------------------
-- DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user(
                                   `uid` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `username` varchar(255) DEFAULT NULL,
                                   `phone` varchar(255) DEFAULT NULL,
                                   `address` varchar(255) DEFAULT NULL,
                                   `email` varchar(255) DEFAULT NULL,
                                   `avatar` varchar(255) DEFAULT NULL,
                                   `mode` varchar(255) DEFAULT NULL,
                                   PRIMARY KEY (`uid`)
);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ( '1', 'user1', '11111111111', 'A', 'email1@qq.com', 'https://tupian.qqw21.com/article/UploadPic/2020-8/20208522181570993.jpg', 'admin');
INSERT INTO `user` VALUES ( '2', 'user2', '22222222222', 'B', 'email2@qq.com', 'https://www.qqkw.com/d/file/p/2018/07-19/0a3bdaa7cbe14b0da52d7b8f3b2b0855.jpg', 'user');
INSERT INTO `user` VALUES ( '3', 'user3', '33333333333', 'C', 'email3@qq.com', 'https://tupian.qqw21.com/article/UploadPic/2020-5/2020514225358304.jpg', 'user');

-- ----------------------------
-- Table structure for userpassword
-- ----------------------------
-- DROP TABLE IF EXISTS userpassword;
CREATE TABLE IF NOT EXISTS userpassword(
                                           `uid` bigint(20) NOT NULL,
                                           `password` varchar(255) DEFAULT NULL,
                                           PRIMARY KEY (`uid`),
                                           FOREIGN KEY (`uid`) REFERENCES user(`uid`)
);

-- ----------------------------
-- Records of userpassword
-- ----------------------------
INSERT INTO `userpassword` VALUES ('1', '111111');
INSERT INTO `userpassword` VALUES ('2', '222222');
INSERT INTO `userpassword` VALUES ('3', '333333');

-- ----------------------------
-- Table structure for order
-- ----------------------------
-- DROP TABLE IF EXISTS `myorder`;
CREATE TABLE IF NOT EXISTS `myorder`(
                                        `oid` bigint(20) NOT NULL AUTO_INCREMENT,
                                        `uid` bigint(20),
                                        `year` varchar(255) DEFAULT NULL,
                                        `month` varchar(255) DEFAULT NULL,
                                        `day` varchar(255) DEFAULT NULL,
                                        `hour` varchar(255) DEFAULT NULL,
                                        `minute` varchar(255) DEFAULT NULL,
                                        `price` varchar(255) DEFAULT NULL,
                                        `username` varchar(255) DEFAULT NULL,
                                        PRIMARY KEY (`oid`),
                                        FOREIGN KEY (`uid`) REFERENCES user(`uid`)
);

-- ----------------------------
-- Records of myorder
-- ----------------------------

-- # INSERT INTO `myorder` (`uid`) VALUES ('1', '2020', '11', '11', '11', '11', '99', 'user1');
-- # INSERT INTO `myorder` (`uid`) VALUES ('2', '2020', '11', '11', '11', '11', '99', 'user2');
-- # INSERT INTO `myorder` (`uid`) VALUES ('2', '2020', '11', '11', '11', '11', '99', 'user2');
-- # INSERT INTO `myorder` (`uid`) VALUES ('3', '2020', '11', '11', '11', '11', '99', 'user3');
INSERT INTO `myorder` VALUES ('1', '3', '2020', '11', '11', '11', '1', '100', 'user3');
INSERT INTO `myorder` VALUES ('2', '2', '2021', '11', '11', '11', '4', '100', 'user2');
INSERT INTO `myorder` VALUES ('3', '2', '2021', '12', '11', '15', '12', '100', 'user2');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
-- DROP TABLE IF EXISTS orderitem;
CREATE TABLE IF NOT EXISTS orderitem(
-- #                         `key` varchar(255) NOT NULL,
                                        `iid` bigint(20) NOT NULL AUTO_INCREMENT,
                                        `oid` bigint(20),
                                        `title` varchar(255) DEFAULT NULL,
                                        `author` varchar(255) DEFAULT NULL,
                                        `quantity` varchar(255) DEFAULT NULL,
                                        `price` varchar(255) DEFAULT NULL,
                                        PRIMARY KEY (`iid`),
                                        FOREIGN KEY (`oid`) REFERENCES `myorder`(`oid`)
);

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', '1', 'The Hobbit', 'J.R.R. Tolkien', '1', '40');
INSERT INTO `orderitem` VALUES ('2', '1', 'The Lord of the Rings', 'J.R.R. Tolkien', '1', '60');
INSERT INTO `orderitem` VALUES ('3', '2', 'The Silmarillion', 'J.R.R. Tolkien', '1', '54');
INSERT INTO `orderitem` VALUES ('4', '2', 'The Children of Hurin', 'J.R.R. Tolkien', '1', '46');
INSERT INTO `orderitem` VALUES ('5', '3', 'The Book of Lost Tales', 'J.R.R. Tolkien', '1', '33');
INSERT INTO `orderitem` VALUES ('6', '3', 'The Book of Lost Tales 2', 'J.R.R. Tolkien', '1', '34');
INSERT INTO `orderitem` VALUES ('7', '3', 'The Lays of Beleriand', 'J.R.R. Tolkien', '1', '33');