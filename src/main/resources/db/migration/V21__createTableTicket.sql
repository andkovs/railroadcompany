CREATE TABLE `ticket` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_id` int(11) NOT NULL,
  `wagon_id` int(11) NOT NULL,
  `seat_namber` int(11) NOT NULL,
  `user_id` bigint NOT NULL,
  `ticket_date` date NOT NULL,
  `departure_station_id` int(11) NOT NULL,
  `arrive_station_id` int(11) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  CONSTRAINT `fk_ticket_train_id` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_wagon_id` FOREIGN KEY (`wagon_id`) REFERENCES `wagon` (`wagon_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_departure_station_id` FOREIGN KEY (`departure_station_id`) REFERENCES `station` (`station_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_arrive_station_id` FOREIGN KEY (`arrive_station_id`) REFERENCES `station` (`station_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;