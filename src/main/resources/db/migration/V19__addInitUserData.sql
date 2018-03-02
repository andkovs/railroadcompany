ALTER TABLE `user` MODIFY `password` varchar(90) NOT NULL;

INSERT INTO `user` VALUES 
(1,'admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','kovshov','andrey','mikhailovich', 
'1986-07-27', '8-953-355-04-93', 'andkovs@ya.ru', ''),
(2,'olkovr','ab65c6340912b0e1091893ca3e3eeb983745a6b68c67bb20917ae73039a57c12','kovrizin','oleg','igorevich', 
'1987-09-14', '8-952-555-55-44', 'olkovr@ya.ru', ''),
(3,'andkor','f2dbbe9d8e5f1a0f6553fc9cd4a93c1df104fe69ea32b333d6622f67662ed98c','koreshkov','andrey','leonidovich', 
'1986-10-31', '8-950-444-44-77', 'andkor@ya.ru', ''),
(4,'ivaiva','31db79a3eaefc0330633a463ebf44ab66a845337b9a2a3275b814b81680d27f0','ivanov','ivan','ivanovich', 
'1987-01-04', '8-951-222-11-88', 'ivaiva@ya.ru', '');