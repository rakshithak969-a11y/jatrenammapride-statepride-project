package com.jatre.namma.model;

public class LostFoundItem {
    private String id;
    private String type; // lost or found
    private String title;
    private String description;
    private String contactName;
    private String contactPhone;
    private long timestamp;
    private String status; // pending or resolved
    private String imageUrl;

    public LostFoundItem() {}

    public LostFoundItem(String id, String type, String title, String description,
                         String contactName, String contactPhone, long timestamp,
                         String status, String imageUrl) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.timestamp = timestamp;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
