CREATE TABLE `schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `direction_id` int(11) NOT NULL,
  `train_id` int(11) NOT NULL,
  `departure_time` varchar(10) NOT NULL,
  `arrive_time` varchar(10) NOT NULL,
  PRIMARY KEY (`schedule_id`),
  CONSTRAINT `fk_schedule_train_id` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_schedule_direction_id` FOREIGN KEY (`direction_id`) REFERENCES `direction` (`direction_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;