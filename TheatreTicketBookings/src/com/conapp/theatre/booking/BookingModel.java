package com.conapp.theatre.booking;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.conapp.theatre.dto.Bookings;
import com.conapp.theatre.dto.Screen;
import com.conapp.theatre.dto.User;
import com.conapp.theatre.repository.TheatreRepository;

public class BookingModel implements BookingControllerToModelCaller{

    private boolean[][] seats;
    private TheatreRepository repo;
    private Screen screen;

    private BookingModelToControllerCaller bookingController;

    BookingModel(BookingController controller)
    {
        bookingController = controller;
        repo = TheatreRepository.getInstance();
    }
    
    public boolean getScreens(LocalDate date)
    {
        List<Screen> list = repo.getScreens(date);
        bookingController.printScreens(list);
        return list==null;
    }

    public boolean getSeats(LocalDate date, int movieNo)
    {
        screen = repo.getScreen(movieNo);
        if(screen!=null){
            seats = screen.getSeats(date);
            bookingController.printSeats(seats, screen.getElite(), screen.getPremium(), screen.getPartition());
            return true;
        }
        return false;

    }
    public boolean getSeatAvailability(int day, int row, int column)
    {
        if(row>=seats.length || column>=seats[0].length || row<0 || column<0 || screen.getSeatCount(day)<1)
            return false;
        return screen.getSeatState(day, row, column);
    }

    public void bookSeat(User user, LocalDate date, int row, int column)
    {
        String seat = ""+(char)(row+'A')+(column+1);
        int day = date.compareTo(LocalDate.now());
        List<Bookings> bookingList = user.getBookings();
        bookingList.add(new Bookings(date, screen, seat));
        Collections.sort(bookingList);
        screen.changeSeatState(day, row, column);
        screen.setSeatCount(day, -1);
        String message = "Booking confirmed!\n";
        message = message + String.format("%-26s%-25s%-15s%s","Movie name","Screen","Date","Seat no.")+"\n";
        message = message + String.format("%-26s%-25s%-15s%s",screen.getMovie(),screen.getName(),date,seat);
        bookingController.sendMessage(message);
    }

    public void cancelBooking(User user, int booking)
    {
        List<Bookings> bookings = user.getBookings();
        boolean cancelled = false;
        if(!bookings.isEmpty())
        {
            if(booking>=0 && booking<bookings.size())
            {
                cancelled = true;
                Bookings current = bookings.get(booking);
                screen = current.getScreen();
                String seat = current.getSeat();
                int day = current.getDate().compareTo(LocalDate.now());
                int row = seat.charAt(0)-'A';
                int column = Integer.parseInt(seat.substring(1))-1;
                screen.changeSeatState(day, row, column);
                screen.setSeatCount(day, 1);
                bookings.remove(booking);
            }
        }
        bookingController.sendMessage(cancelled?"Booking cancelled!":"Unavailable booking");

    }

    public List<Bookings> getBookings(User user)
    {
        return user.getBookings();
    }
    
    public int getCost(int row)
    {
        return screen.getCost(row);
    }
}
