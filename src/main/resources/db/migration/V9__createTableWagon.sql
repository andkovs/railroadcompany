CREATE TABLE `wagon` (
  `wagon_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_id` int(11) NOT NULL,
  `wagon_type_id` int(11) NOT NULL,
  `wagon_title` varchar(45) NOT NULL,
  PRIMARY KEY (`wagon_id`),
  CONSTRAINT `fk_wagon_train_id` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_wagon_wagon_type_id` FOREIGN KEY (`wagon_type_id`) REFERENCES `wagon_type` (`wagon_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;