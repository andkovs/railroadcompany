CREATE TABLE `train` (
  `train_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_number` varchar(45) NOT NULL,
  `enabled` bit(1) DEFAULT b'1',
  PRIMARY KEY (`train_id`),
  UNIQUE KEY `train_number_unq` (`train_number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;