package com.conapp.theatre.booking;

import java.time.LocalDate;
// import com.conapp.theatre.dto.Movie;
import java.util.List;

import com.conapp.theatre.dto.Bookings;
import com.conapp.theatre.dto.User;

public interface BookingViewToControllerCaller {
    boolean getScreens(LocalDate date);
    // boolean[][] getSeats();
    // boolean checkAvailability(int row, int column);
    // void bookSeat(int row, int column);
    // boolean checkFlights(String date, String source, String destination);
    // void cancelBooking(User user, int booking);
    // List<Bookings> getBookings(User user);
    // public int getCost();

    // boolean viewMovies();

    LocalDate toDate(String date);

    boolean getSeats(LocalDate date, int movieNo);

    boolean checkAvailability(LocalDate date, int row, int column);

    int getCost(int row);

    void bookSeat(LocalDate date,  int row, int i);

    List<Bookings> getBookings(User user);

    void cancelBooking(User user, int booking);
}
