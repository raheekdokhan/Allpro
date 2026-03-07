package com.example.allpro;

public class Ticket {
    private String matchName;
    private String date;
    private String category;
    private double price;
    private String bookingUrl; // رابط الحجز الرسمي

    public Ticket(String matchName, String date, String category, double price, String bookingUrl) {
        this.matchName = matchName;
        this.date = date;
        this.category = category;
        this.price = price;
        this.bookingUrl = bookingUrl;
    }

    public String getMatchName() { return matchName; }
    public String getDate() { return date; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getBookingUrl() { return bookingUrl; }
}