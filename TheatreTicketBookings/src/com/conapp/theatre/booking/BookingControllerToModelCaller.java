package com.conapp.theatre.booking;

import java.time.LocalDate;
import java.util.List;

import com.conapp.theatre.dto.Bookings;
import com.conapp.theatre.dto.User;

public interface BookingControllerToModelCaller {
    boolean getScreens(LocalDate date);

    boolean getSeats(LocalDate date, int movieNo);

    boolean getSeatAvailability(int day, int row, int column);

    int getCost(int row);

    void bookSeat(User user, LocalDate date, int row, int column);

    List<Bookings> getBookings(User user);

    void cancelBooking(User user, int booking);
}
