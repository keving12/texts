package com.kg.dao;

import com.kg.entities.Message;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kevingracie on 31/01/2016.
 */
public interface MessageDAO {

    public List<Message> getAllMessages();
    public List<Message> getMessagesBySender(String sender);
    public List<Message> getMessagesAfterDate(LocalDate date);


}
