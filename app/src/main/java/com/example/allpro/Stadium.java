package com.example.allpro;

public class Stadium {

    private String teamName;
    private String stadiumName;
    private String stadiumLocation;
    private int stadiumCapacity;
    private String photo;

    // **Firebase requires empty constructor**
    public Stadium() {}

    public Stadium(String teamName, String stadiumName, String stadiumLocation, int stadiumCapacity, String photo) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.stadiumCapacity = stadiumCapacity;
        this.photo = photo;
    }

    // Getters and Setters
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getStadiumName() { return stadiumName; }
    public void setStadiumName(String stadiumName) { this.stadiumName = stadiumName; }

    public String getStadiumLocation() { return stadiumLocation; }
    public void setStadiumLocation(String stadiumLocation) { this.stadiumLocation = stadiumLocation; }

    public int getStadiumCapacity() { return stadiumCapacity; }
    public void setStadiumCapacity(int stadiumCapacity) { this.stadiumCapacity = stadiumCapacity; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}






