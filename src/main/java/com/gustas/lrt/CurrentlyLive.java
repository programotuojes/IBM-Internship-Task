package com.gustas.lrt;

public class CurrentlyLive {

    private String channel;
    private String title;
    private String time;

    public CurrentlyLive() {
        channel = "";
        title = "";
        time = "";
    }

    public CurrentlyLive(String channel, String title, String time) {
        this.channel = channel;
        this.title = title;
        this.time = time;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}