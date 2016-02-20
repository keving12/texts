package com.kg.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Created by kevingracie on 20/02/2016.
 */
public class MessageTest {

    private Message message;

    @Before
    public void setUp() throws Exception {
        message = new Message();
    }

    @Test
    public void getAndSetNameTest() {
        message.setName("Beverley");
        assertEquals("Beverley", message.getName());
    }

    @Test
    public void getAndSetPhoneNumberTest() {
        message.setPhoneNumber("+447123456789");
        assertEquals("+447123456789", message.getPhoneNumber());
    }

    @Test
    public void getAndSetBodyTest() {
        message.setBody("Here is a message body");
        assertEquals("Here is a message body", message.getBody());
    }

    @Test
    public void getAndSetReadableDateTest() {
        message.setReadableDate("25 Jan 2013 19:43:29");
        assertEquals("25 Jan 2013 19:43:29", message.getReadableDate());
    }

    @Test
    public void getAndSetDateLongTest() {
        message.setDateLong(1359143009907L);
        assertEquals(1359143009907L, message.getDateLong());
    }

    @Test
    public void constructorTest() {
        Message message2 = new Message("Bob", "+447987654321", "Message Body", "01 Feb 2015 21:55:12", 13483443009907L);
        assertEquals("Bob", message2.getName());
        assertEquals("+447987654321", message2.getPhoneNumber());
        assertEquals("Message Body", message2.getBody());
        assertEquals("01 Feb 2015 21:55:12", message2.getReadableDate());
        assertEquals(13483443009907L, message2.getDateLong());
    }
}
