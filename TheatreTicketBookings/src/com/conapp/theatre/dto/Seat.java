package com.conapp.theatre.dto;

public class Seat {
    private String seatNo;
    private int cost;
    private String type;
    private boolean booked;
    
    Seat(String seatNo, int cost, String type)
    {
        this.seatNo = seatNo;
        this.cost = cost;
        this.type = type;
    }
}
