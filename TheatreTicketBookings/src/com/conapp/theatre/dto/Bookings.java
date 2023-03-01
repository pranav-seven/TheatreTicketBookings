package com.conapp.theatre.dto;

import java.time.LocalDate;

public class Bookings implements Comparable<Bookings>{
    // User user;
    Screen screen;
    LocalDate date;
    String seatNo;
    public Bookings(LocalDate date, Screen screen, String seat)
    {
        // this.user = user;
        this.screen = screen;
        this.date = date;
        seatNo = seat;
    }
     public String toString()
     {
        return String.format("%-26s%-25s%-15s%s", screen.getMovie(), screen.getName(), date, seatNo);
     }

     public Screen getScreen()
     {
        return screen;
     }

      public String getSeat()
      {
        return seatNo;
      }

      public LocalDate getDate()
      {
        return date;
      }
      @Override
      public int compareTo(Bookings booking) {
        return date.compareTo(booking.getDate());
      }
}
