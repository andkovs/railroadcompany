package com.railroad.core.mapper;

import com.railroad.model.dto.TicketDto;
import com.railroad.model.dto.TicketFormDto;
import com.railroad.model.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TicketMapper {

    private final
    StationMapper stationMapper;

    private final
    UserMapper userMapper;

    private final
    WagonMapper wagonMapper;

    @Autowired
    public TicketMapper(UserMapper userMapper, WagonMapper wagonMapper, StationMapper stationMapper) {
        this.userMapper = userMapper;
        this.wagonMapper = wagonMapper;
        this.stationMapper = stationMapper;
    }

    /**
     * Converts ticket form DTO to ticket.
     *
     * @param ticketFormDto convertible ticket form DTO.
     * @param userId        user id.
     * @return ticket.
     */
    public Ticket ticketFormDtoToTicket(TicketFormDto ticketFormDto, Long userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateWithoutTime = null;
        try {
            dateWithoutTime = sdf.parse(sdf.format(new java.util.Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Ticket(
                userId,
                ticketFormDto.getTrain().getTrainId(),
                ticketFormDto.getTrain().getWagons().get(0).getWagonId(),
                1L,
                new java.sql.Date(dateWithoutTime.getTime()),
                ticketFormDto.getDepStation().getStationId(),
                ticketFormDto.getArrStation().getStationId()
        );
    }

    /**
     * Converts ticket to ticket DTO.
     *
     * @param ticket convertible ticket.
     * @return ticket DTO.
     */
    TicketDto ticketToTicketDto(Ticket ticket) {
        return new TicketDto(
                ticket.getTicketId(),
                ticket.getTrainId(),
                ticket.getTrainByTrainId().getTrainNumber(),
                ticket.getSeatNamber(),
                ticket.getTicketDate(),
                userMapper.userToUserDto(ticket.getUserByUserId()),
                wagonMapper.wagonToWagonDto(ticket.getWagonByWagonId()),
                stationMapper.stationToStationDto(ticket.getStationByDepartureStationId()),
                stationMapper.stationToStationDto(ticket.getStationByArriveStationId())
        );
    }
}
