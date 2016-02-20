package com.kg.dao.mongo;

import com.kg.dao.MessageDAO;
import com.kg.entities.Message;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kevingracie on 31/01/2016.
 */
public class MongoMessageDAOTest {

    private MongoDatabase database;
    private MongoCollection collection;
    private MongoCursor<Document> cursor;
    private FindIterable<Document> iterable;

    @Before
    public void setup() {
        database = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        cursor = mock(MongoCursor.class);
        iterable = mock(FindIterable.class);
    }

    @Test
    public void getAllMessagesTest() {
        /* Set up mock behaviour */
        when(database.getCollection(anyString())).thenReturn(collection);
        when(collection.find()).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true,true,true,true,true,false);
        List<Document> data = dummyAllDocuments();
        when(cursor.next()).thenReturn(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));

        MessageDAO messageDAO = new MongoMessageDAO(database);
        List<Message> messages = messageDAO.getAllMessages();
        assertNotNull(messages);
        assertEquals(5, messages.size());
        assertEquals("Adam", messages.get(0).getName());
        assertEquals("Belinda", messages.get(1).getName());
        assertEquals("Charlie", messages.get(2).getName());
        assertEquals("Davina", messages.get(3).getName());
        assertEquals("Eric", messages.get(4).getName());
    }

    @Test
    public void getMessagesBySenderTest() {
        when(database.getCollection(anyString())).thenReturn(collection);
        when(collection.find()).thenReturn(iterable);
        when(iterable.filter(any(Bson.class))).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true,true,true,false);
        List<Document> data = dummyBySenderDocuments();
        when(cursor.next()).thenReturn(data.get(0), data.get(1), data.get(2));

        MessageDAO messageDAO = new MongoMessageDAO(database);
        List<Message> messages = messageDAO.getMessagesBySender("Matthew");
        assertNotNull(messageDAO);
        assertEquals(3, messages.size());
    }

    private List<Document> dummyAllDocuments() {
        Map<String, Object> docMap1 = new HashMap<String, Object>();
        docMap1.put("contact_name", "Adam");
        docMap1.put("address", "+447123456789");
        docMap1.put("body", "Dummy message 1");
        docMap1.put("readable_date", "25 Jan 2013 19:43:29");
        docMap1.put("date", 1359143009907L);

        Map<String, Object> docMap2 = new HashMap<String, Object>();
        docMap2.put("contact_name", "Belinda");
        docMap2.put("address", "+447123456789");
        docMap2.put("body", "Dummy message 2");
        docMap2.put("readable_date", "25 Jan 2013 19:43:29");
        docMap2.put("date", 1359143009907L);

        Map<String, Object> docMap3 = new HashMap<String, Object>();
        docMap3.put("contact_name", "Charlie");
        docMap3.put("address", "+447123456789");
        docMap3.put("body", "Dummy message 3");
        docMap3.put("readable_date", "25 Jan 2013 19:43:29");
        docMap3.put("date", 1359143009907L);

        Map<String, Object> docMap4 = new HashMap<String, Object>();
        docMap4.put("contact_name", "Davina");
        docMap4.put("address", "+447123456789");
        docMap4.put("body", "Dummy message 4");
        docMap4.put("readable_date", "25 Jan 2013 19:43:29");
        docMap4.put("date", 1359143009907L);

        Map<String, Object> docMap5 = new HashMap<String, Object>();
        docMap5.put("contact_name", "Eric");
        docMap5.put("address", "+447123456789");
        docMap5.put("body", "Dummy message 5");
        docMap5.put("readable_date", "25 Jan 2013 19:43:29");
        docMap5.put("date", 1359143009907L);

        return Arrays.asList(new Document(docMap1),
                             new Document(docMap2),
                             new Document(docMap3),
                             new Document(docMap4),
                             new Document(docMap5));
    }

    private List<Document> dummyBySenderDocuments() {
        Map<String, Object> mapDoc1 = new HashMap<String, Object>();
        mapDoc1.put("contact_name", "James");
        mapDoc1.put("body", "Dummy sender 1");
        mapDoc1.put("address", "+447123456789");
        mapDoc1.put("readable_date", "25 Jan 2013 19:43:29");
        mapDoc1.put("date", 1359143009907L);

        Map<String, Object> mapDoc2 = new HashMap<String, Object>();
        mapDoc2.put("contact_name", "James");
        mapDoc2.put("body", "Dummy sender 2");
        mapDoc2.put("address", "+447123456789");
        mapDoc2.put("readable_date", "25 Jan 2013 19:43:29");
        mapDoc2.put("date", 1359143009907L);

        Map<String, Object> mapDoc3 = new HashMap<String, Object>();
        mapDoc3.put("contact_name", "James");
        mapDoc3.put("body", "Dummy sender 3");
        mapDoc3.put("address", "+447123456789");
        mapDoc3.put("readable_date", "25 Jan 2013 19:43:29");
        mapDoc3.put("date", 1359143009907L);

        return Arrays.asList(new Document(mapDoc1), new Document(mapDoc2), new Document(mapDoc3));
    }

    private List<Message> dummyMessagesBySenderData() {
        return null;
    }
}
