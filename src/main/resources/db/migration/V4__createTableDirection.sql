CREATE TABLE `direction` (
  `direction_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_station_id` int(11) NOT NULL,
  `arr_station_id` int(11) NOT NULL,
  PRIMARY KEY (`direction_id`),
  CONSTRAINT `fk_dep_st_id` FOREIGN KEY (`dep_station_id`) REFERENCES `station` (`station_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_arr_st_id` FOREIGN KEY (`arr_station_id`) REFERENCES `station` (`station_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;