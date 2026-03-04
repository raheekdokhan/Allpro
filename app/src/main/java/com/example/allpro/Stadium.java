package com.example.allpro;

public class Stadium {

    private String teamName;
    private String stadiumName;
    private String stadiumLocation;
    private int stadiumCapacity;
    private String photo;

    private String openingDate;
    private String surfaceType;
    private String biggestMatch;
    private String famousPlayer;
    private String averageAttendance;
    private String maxAttendance;

    public Stadium() {}

    public Stadium(String teamName, String stadiumName, String stadiumLocation, int stadiumCapacity, String photo,
                   String openingDate, String surfaceType, String biggestMatch, String famousPlayer,
                   String averageAttendance, String maxAttendance) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.stadiumCapacity = stadiumCapacity;
        this.photo = photo;
        this.openingDate = openingDate;
        this.surfaceType = surfaceType;
        this.biggestMatch = biggestMatch;
        this.famousPlayer = famousPlayer;
        this.averageAttendance = averageAttendance;
        this.maxAttendance = maxAttendance;
    }

    // Getters
    public String getTeamName() { return teamName; }
    public String getStadiumName() { return stadiumName; }
    public String getStadiumLocation() { return stadiumLocation; }
    public int getStadiumCapacity() { return stadiumCapacity; }
    public String getPhoto() { return photo; }
    public String getOpeningDate() { return openingDate; }
    public String getSurfaceType() { return surfaceType; }
    public String getBiggestMatch() { return biggestMatch; }
    public String getFamousPlayer() { return famousPlayer; }
    public String getAverageAttendance() { return averageAttendance; }
    public String getMaxAttendance() { return maxAttendance; }

    // Setters
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public void setStadiumName(String stadiumName) { this.stadiumName = stadiumName; }
    public void setStadiumLocation(String stadiumLocation) { this.stadiumLocation = stadiumLocation; }
    public void setStadiumCapacity(int stadiumCapacity) { this.stadiumCapacity = stadiumCapacity; }
    public void setPhoto(String photo) { this.photo = photo; }
    public void setOpeningDate(String openingDate) { this.openingDate = openingDate; }
    public void setSurfaceType(String surfaceType) { this.surfaceType = surfaceType; }
    public void setBiggestMatch(String biggestMatch) { this.biggestMatch = biggestMatch; }
    public void setFamousPlayer(String famousPlayer) { this.famousPlayer = famousPlayer; }
    public void setAverageAttendance(String averageAttendance) { this.averageAttendance = averageAttendance; }
    public void setMaxAttendance(String maxAttendance) { this.maxAttendance = maxAttendance; }
}