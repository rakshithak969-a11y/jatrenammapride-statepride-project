package com.jatre.namma.model;

public class ScheduleEvent {
    private String name;
    private String time;
    private String status; // ongoing, upcoming, completed
    private String location;
    private String emoji;

    public ScheduleEvent() {}

    public ScheduleEvent(String name, String time, String status, String location, String emoji) {
        this.name = name;
        this.time = time;
        this.status = status;
        this.location = location;
        this.emoji = emoji;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
}
