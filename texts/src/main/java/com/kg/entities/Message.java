package com.kg.entities;

/**
 * Created by kevingracie on 31/01/2016.
 */
public class Message {

    private String name;
    private String phoneNumber;
    private String body;
    private String readableDate;
    private long dateLong;

    public Message() {

    }


    public Message(String name, String phoneNumber, String body, String readableDate, long dateLong) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.body = body;
        this.readableDate = readableDate;
        this.dateLong = dateLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReadableDate() {
        return readableDate;
    }

    public void setReadableDate(String readableDate) {
        this.readableDate = readableDate;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setDateLong(long dateLong) {
        this.dateLong = dateLong;
    }
}
