package com.railroad.core.mq;

import javax.jms.*;

import com.railroad.model.dto.TrainJMSDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final
    JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Sends trainJMS dto.
     *
     * @param train trainJMS dto.
     */
    public void sendMessage(final TrainJMSDto train) {
        jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("New train");
            textMessage.setObjectProperty("trainId", train.getTrainId());
            textMessage.setObjectProperty("shift", train.getShift());
            textMessage.setObjectProperty("enabled", train.getEnabled());
            return textMessage;
        });
    }

}