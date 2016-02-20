package com.kg.dao.mongo;

import com.kg.connections.MongoConnectionFactory;
import com.kg.dao.MessageDAO;
import com.kg.entities.Message;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by kevingracie on 31/01/2016.
 */
public class MongoMessageDAO implements MessageDAO {

    private MongoDatabase database;

    public MongoMessageDAO() {
        Properties props = new Properties();
        try {
            props.load(MongoMessageDAO.class.getResourceAsStream("app.properties"));
        } catch(IOException e) {

        }

        database = MongoConnectionFactory.getDatabase(props);
    }

    MongoMessageDAO(MongoDatabase database) {
        this.database = database;
    }

    public List<Message> getAllMessages() {
        MongoCollection collection = database.getCollection("texts");
        MongoCursor<Document> cursor = collection.find().iterator();
        List<Message> messages = new ArrayList<Message>();
        while(cursor.hasNext()) {
            messages.add(constructMessageFromDocument(cursor.next()));
        }
        return messages;
    }

    public List<Message> getMessagesBySender(String sender) {
        MongoCollection collection = database.getCollection("texts");
        MongoCursor<Document> cursor = collection.find().filter(eq("contact_name", sender)).iterator();
        List<Message> messages = new ArrayList<Message>();
        while(cursor.hasNext()) {
            messages.add(constructMessageFromDocument(cursor.next()));
        }
        return messages;
    }

    public List<Message> getMessagesAfterDate(LocalDate date) {
        return null;
    }

    private Message constructMessageFromDocument(Document doc) {
        return new Message(doc.getString("contact_name"),
                            doc.getString("address"),
                            doc.getString("body"),
                            doc.getString("readable_date"),
                            Long.valueOf(doc.getLong("date")));
    }
}
