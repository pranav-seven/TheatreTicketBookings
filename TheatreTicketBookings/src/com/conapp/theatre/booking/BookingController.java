package com.conapp.theatre.booking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.conapp.theatre.dto.Bookings;
import com.conapp.theatre.dto.User;
import com.conapp.theatre.dto.Screen;


public class BookingController implements BookingViewToControllerCaller, BookingModelToControllerCaller{

    private BookingControllerToViewCaller bookingView;
    private BookingControllerToModelCaller bookingModel;

    BookingController(BookingView view)
    {
        bookingView = view;
        bookingModel = new BookingModel(this);
    }

    public LocalDate toDate(String dateString)
    {
        LocalDate date;
        try{
            date = LocalDate.parse(dateString);
        }catch(DateTimeParseException ex)
        {
            date = null;
        }
        return date;
    }
    
    public boolean getScreens(LocalDate date)
    {
        return bookingModel.getScreens(date);
    }

    public boolean getSeats(LocalDate date, int movieNo)
    {
        return bookingModel.getSeats(date, movieNo);
    }

    public boolean checkAvailability(LocalDate date, int row, int column)
    {
        return bookingModel.getSeatAvailability(date.compareTo(LocalDate.now()), row, column);
    }

    public int getCost(int row)
    {
        return bookingModel.getCost(row);
    }

    public void bookSeat(LocalDate date, int row, int column)
    {
        bookingModel.bookSeat(bookingView.getUser(), date, row, column);
    }

    public List<Bookings> getBookings(User user)
    {
        return bookingModel.getBookings(user);
    }

    public void cancelBooking(User user, int booking)
    {
        bookingModel.cancelBooking(user, booking);
    }
    
    public void sendMessage(String message)
    {
        bookingView.printMessage(message);
    }

    @Override
    public void printScreens(List<Screen> list) {
        bookingView.printScreens(list);
    }

    @Override
    public void printSeats(boolean[][] seats, int elite, int premium, int partition) {
        bookingView.printSeats(seats, elite, premium, partition);
    }
}
