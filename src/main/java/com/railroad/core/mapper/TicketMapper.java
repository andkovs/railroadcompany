package com.railroad.core.mapper;

import com.railroad.model.dto.TicketDto;
import com.railroad.model.dto.TicketFormDto;
import com.railroad.model.entity.Ticket;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Component
public class TicketMapper {

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

    public TicketDto ticketToTicketDto(Ticket ticket){
        return new TicketDto(
                ticket.getTicketId(),
                ticket.getUserId(),
                ticket.getTrainId(),
                ticket.getWagonId(),
                ticket.getSeatNamber(),
                ticket.getTicketDate(),
                ticket.getDepartureStationId(),
                ticket.getArriveStationId(),
                ticket.getTrainByTrainId(),
                ticket.getWagonByWagonId(),
                ticket.getUserByUserId(),
                ticket.getStationByDepartureStationId(),
                ticket.getStationByArriveStationId()
        );

    }
}
