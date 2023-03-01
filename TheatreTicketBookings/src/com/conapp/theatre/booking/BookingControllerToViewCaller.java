package com.conapp.theatre.booking;

 import java.util.List;

import com.conapp.theatre.dto.Screen;
import com.conapp.theatre.dto.User;
 
public interface BookingControllerToViewCaller {
    public User getUser();
    public void printMessage(String message);
    public void printScreens(List<Screen> list);
    public void printSeats(boolean[][] seats, int elite, int premium, int partition);
}
