package com.railroad.core.service;

import com.railroad.core.mapper.StationMapper;
import com.railroad.core.mapper.TicketMapper;
import com.railroad.model.dao.TicketDao;
import com.railroad.model.dao.UserDao;
import com.railroad.model.dto.*;
import com.railroad.model.entity.Ticket;
import com.railroad.model.entity.User;
import com.railroad.rest.MasterController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TicketService {

    private static final Logger logger = Logger.getLogger(MasterController.class);

    private final
    TrainService trainService;

    private final
    StationMapper stationMapper;

    private final
    TicketDao ticketDao;

    private final
    UserDao userDao;

    private final
    TicketMapper ticketMapper;

    @Autowired
    public TicketService(TrainService trainService, TicketDao ticketDao, StationMapper stationMapper, TicketMapper ticketMapper, UserDao userDao) {
        this.trainService = trainService;
        this.ticketDao = ticketDao;
        this.stationMapper = stationMapper;
        this.ticketMapper = ticketMapper;
        this.userDao = userDao;
    }

    public TicketFormDto getTicketForm(Long trainId, Long depStationId, Long arrStationId) {
        TicketFormDto ticketFormDto = new TicketFormDto();
        //get train by train id
        TrainDto train = trainService.getTrainById(trainId);
        ticketFormDto.setTrain(train);
        //get number of available/not available seats
        Integer numberOfSeats = getNumberOfSeats(train);
        Integer numberOfNotAvailableSeats = getNumberOfNotAvailableSeats(train);
        ticketFormDto.setNumberOfSeats(numberOfSeats);
        ticketFormDto.setNumberOfAvailableSeats(numberOfSeats - numberOfNotAvailableSeats);
        //get arrival and departure station/time
        for (ScheduleDto s :
                train.getSchedules()) {
            if (s.getDirectionByDirectionId().getDepStationId().equals(depStationId)) {
                ticketFormDto.setDepStation(stationMapper.stationToStationDto(s.getDirectionByDirectionId().getStationByDepStationId()));
                ticketFormDto.setDepTime(s.getDepartureTime());
            }
            if (s.getDirectionByDirectionId().getArrStationId().equals(arrStationId)) {
                ticketFormDto.setArrStation(stationMapper.stationToStationDto(s.getDirectionByDirectionId().getStationByArrStationId()));
                ticketFormDto.setArrTime(s.getArriveTime());
            }
        }
        return ticketFormDto;
    }

    public String saveTicket(TicketFormDto ticketFormDto, String login) {
        if (!isAvailableSeats(getNumberOfSeats(ticketFormDto.getTrain()), getNumberOfNotAvailableSeats(ticketFormDto.getTrain()))) {
            return "No seats available!";
        }
        if (!isTenMinutesCheck(ticketFormDto.getDepTime())) {
            return "Less than 10 minutes before departure!";
        }
        User user = userDao.getUserByLogin(login);
        if(isUserBoughtTicket(user.getUserId(), ticketFormDto.getTrain().getTrainId())){
            return "You already bought a ticket for this train!";
        }
        ticketDao.saveTicket(ticketMapper.ticketFormDtoToTicket(ticketFormDto, user.getUserId()));
        return null;
    }

    private boolean isTenMinutesCheck(String depTimeStr) {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";
        if (depTimeStr.matches(reg)) {
            try {
                //Departure time
                Date departureTrainTime = new SimpleDateFormat("HH:mm").parse(depTimeStr);
                //Current Time
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date currentTime = sdf.parse(sdf.format(new Date()));
                //Departure time minus current time
                Long depTimeLong = departureTrainTime.getTime();
                Long currTimeLong = currentTime.getTime();
                long diff = depTimeLong - currTimeLong;
                long diffMinutes = diff / (60 * 1000);
                if (diffMinutes > 10) {
                    return true;
                }
            } catch (ParseException e) {
                logger.error("invalid date format in isTenMinutesCheck()!", e);
                return false;
            }
        }
        return false;
    }

    private Integer getNumberOfSeats(TrainDto train) {
        Integer numberOfSeats = 0;
        for (WagonDto w :
                train.getWagons()) {
            numberOfSeats = numberOfSeats + w.getWagonTypeObj().getCapacity();
        }
        return numberOfSeats;
    }

    private boolean isUserBoughtTicket(Long userId, Long trainId){
        Ticket ticket = ticketDao.getTicketByUserIdAndTrainId(userId, trainId);
        if(ticket==null){
            return false;
        }
        return true;
    }

    private Integer getNumberOfNotAvailableSeats(TrainDto train) {
        return ticketDao.getCountOfTicketsByTrainId(train.getTrainId());
    }

    private boolean isAvailableSeats(Integer all, Integer notAvailable) {
        return all > notAvailable;
    }
}
