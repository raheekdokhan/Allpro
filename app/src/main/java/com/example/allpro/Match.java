package com.example.allpro;

public class Match {
    private String stadium;
    private String homeTeam;
    private String awayTeam;
    private String dateTime;
    private String liveScore;
    private String streamUrl;

    public Match(String stadium, String homeTeam, String awayTeam, String dateTime, String liveScore, String streamUrl) {
        this.stadium = stadium;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateTime = dateTime;
        this.liveScore = liveScore;
        this.streamUrl = streamUrl;
    }

    public String getStadium() { return stadium; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public String getDateTime() { return dateTime; }
    public String getLiveScore() { return liveScore; }
    public String getStreamUrl() { return streamUrl; }
}