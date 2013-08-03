package com.rtnotifier.websocket.model;

public class Status {
    private int status;
    private int value;
    private String date;

    public Status(){

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status=" + status +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
