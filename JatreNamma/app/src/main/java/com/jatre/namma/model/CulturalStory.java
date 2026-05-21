package com.jatre.namma.model;

public class CulturalStory {
    private String title;
    private String content;
    private String emoji;
    private String colorHex;

    public CulturalStory(String title, String content, String emoji, String colorHex) {
        this.title = title;
        this.content = content;
        this.emoji = emoji;
        this.colorHex = colorHex;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getEmoji() { return emoji; }
    public String getColorHex() { return colorHex; }
}
