package com.conapp.theatre.booking;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.conapp.AbstractBookingView;
import com.conapp.theatre.dto.Bookings;
import com.conapp.theatre.dto.Screen;
import com.conapp.theatre.dto.User;

public class BookingView extends AbstractBookingView implements BookingControllerToViewCaller{

    private BookingViewToControllerCaller bookingController;
    private User user;
    private BufferedReader reader;
    
    public BookingView(User user, BufferedReader reader)
    {
        this.user = user;
        this.reader = reader;
        bookingController = new BookingController(this);
        startBooking();
    }

    protected void startBooking()
    {
        char choice = 0;
        do{
            System.out.println("Enter ");
            System.out.println("1 - Book ticket");
            System.out.println("2 - View bookings");
            System.out.println("3 - Cancel booking");
            System.out.println("0 - Log out");
            try{
                choice = reader.readLine().charAt(0);
            }catch(IOException ioe)
            {}
            switch(choice)
            {
                case '1': openBooking();
                        break;
                case '2': viewBookings();
                        break;
                case '3': cancelBooking();
                        break;
                case '0': break;
                default: System.out.println("Invalid choice, try again.");
            }
        }while(choice!='0');
    }

    protected void openBooking()
    {
        char choice = 'Y';
        try{
            do{
                LocalDate date = getDate();
                if(date == null)
                    break;
                if(!bookingController.getScreens(date))
                {
                    System.out.print("Enter number: ");
                    int movieNo = Integer.parseInt(reader.readLine());
                    if(bookingController.getSeats(date, movieNo-1))
                    {
                        System.out.println("Enter row and column: ");
                        int row = reader.read();
                        int column = Integer.parseInt(reader.readLine());
                        row = row - 'A';
                        column--;
                        if(bookingController.checkAvailability(date, row, column))
                        {
                            System.out.println("Ticket cost: Rs."+bookingController.getCost(row));
                            System.out.print("Confirm booking? (Y/N): ");
                            char confirmation = reader.readLine().charAt(0);
                            if(confirmation == 'Y' || confirmation=='y')
                            {
                                bookingController.bookSeat(date, row, column);
                                System.out.print("Want to book again? (Y/N): ");
                            }
                            else
                                System.out.print("Want to continue? (Y/N): ");
                        }
                        else
                        {
                            System.out.println("Seat unavailable");
                            System.out.print("Want to continue? (Y/N): ");
                        }
                    }
                    else{
                        System.out.println("Screen unavailable");
                        System.out.print("Want to continue? (Y/N): ");
                    }
                }
                else
                    System.out.print("Want to continue? (Y/N): ");
                choice = reader.readLine().charAt(0);
            }while(choice=='Y' || choice=='y');
        }catch(IOException ioe)
        {}
    }

    LocalDate getDate()
    {
        System.out.print("Enter date (yyyy-mm-dd format), 0 to go back: ");
        LocalDate date = null;
        try{
            String dateString = reader.readLine();
            if(!dateString.contentEquals("0") && 
                    (date=bookingController.toDate(dateString))==null)
                date = getDate();
        }catch(IOException ioe)
        {}
        return date;
    }

    public void printScreens(List<Screen> list)
    {
        System.out.println("--------------------------");
        if(list==null)
            System.out.println("Sorry, no movies available :(");
        else
        {
            int i=0;
            System.out.println(String.format("%-5s%-26s%s", "No.", "Name", "Screen"));
            for(Screen screen : list)
            {
                System.out.println(String.format("%-5s", ++i)+screen);
            }
        }
    }

    public void printSeats(boolean[][] seats, int elite, int premium, int partition)
    {
        
        System.out.print("  ");
        for(int i=0; i<seats[0].length; i++)
        {
            if(i==partition || i==seats[0].length-partition)
                System.out.print("   ");
            System.out.print(String.format(" %-2s ", (i+1)));
        }
        System.out.println();
        System.out.println("ELITE - Rs.200");
        for(int i=0; i<seats.length; i++)
        {
            if(i==elite)
                System.out.println("PREMIUM - Rs.150");
            else if(i==premium)
                    System.out.println("BUDGET - Rs.120");
            System.out.print((char)(i+'A')+" ");
            for(int j=0; j<seats[0].length; j++)
            {
                if(j==partition || j==seats[0].length-partition)
                    System.out.print("   ");
                if(seats[i][j])
                    System.out.print(String.format(" %-2s ", "O"));
                else
                    System.out.print(String.format(" %-2s ", "-"));
            }
            System.out.println();
        }
        System.out.println("\nO : available\n- : booked");
    }

    protected void viewBookings()
    {
        List<Bookings> list = bookingController.getBookings(user);
        if(list.isEmpty())
        	System.out.println("No bookings available");
        else {
	        System.out.println(String.format("%-5s%-26s%-25s%-15s%s","No.","Movie name","Screen","Date","Seat no."));
	        int i=1;
	        for(Bookings booking : list)
	        {
	            System.out.println(String.format("%-5s", i)+booking);
	            i++;
	        }  
        }
    }

    public void cancelBooking()
    {
        viewBookings();
        System.out.print("Enter booking number to cancel: ");
        try{
            int booking = Integer.parseInt(reader.readLine());
            System.out.print("Are you sure want to cancel? (Y/N): ");
            char c = reader.readLine().charAt(0);
            if(c=='Y')
            bookingController.cancelBooking(user, booking-1);
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
 
    public User getUser()
    {
        return user;
    }

    public void printMessage(String message)
    {
       System.out.println(message);
    }
}
