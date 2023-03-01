package com.conapp.theatre.booking;

import java.util.List;

import com.conapp.theatre.dto.Screen;

public interface BookingModelToControllerCaller {
    void sendMessage(String message);

    void printScreens(List<Screen> list);

    void printSeats(boolean[][] seats, int elite, int premium, int partition);
}
