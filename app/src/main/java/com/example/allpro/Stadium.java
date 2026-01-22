package com.example.allpro;

public class Stadium {

    private String teamName;
    private String stadiumName;
    private String stadiumLocation;
    private int stadiumCapacity;
    private String photo;

    // الحقول الجديدة
    private String openingDate;
    private String surfaceType;
    private String biggestMatch;
    private String famousPlayer;
    private String averageAttendance;
    private String maxAttendance;

    // Firebase requires empty constructor
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

    // Getters and setters لكل الحقول
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

    public String getOpeningDate() { return openingDate; }
    public void setOpeningDate(String openingDate) { this.openingDate = openingDate; }

    public String getSurfaceType() { return surfaceType; }
    public void setSurfaceType(String surfaceType) { this.surfaceType = surfaceType; }

    public String getBiggestMatch() { return biggestMatch; }
    public void setBiggestMatch(String biggestMatch) { this.biggestMatch = biggestMatch; }

    public String getFamousPlayer() { return famousPlayer; }
    public void setFamousPlayer(String famousPlayer) { this.famousPlayer = famousPlayer; }

    public String getAverageAttendance() { return averageAttendance; }
    public void setAverageAttendance(String averageAttendance) { this.averageAttendance = averageAttendance; }

    public String getMaxAttendance() { return maxAttendance; }
    public void setMaxAttendance(String maxAttendance) { this.maxAttendance = maxAttendance; }
}








