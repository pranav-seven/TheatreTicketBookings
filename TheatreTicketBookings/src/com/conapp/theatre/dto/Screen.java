package com.conapp.theatre.dto;

import java.util.Arrays;
import java.time.LocalDate;

public class Screen {
    private String name;
    private String movie;
    private LocalDate date;
    private int partition;
    private boolean[][][] seats;
    private int[] seatCount;
    private int days;
    private int premium;
    private int elite;
    // private int budget;

    public Screen(String name, String movie, LocalDate date, int rows, int columns, int elite, int premium, int budget, int partition)
    {
        this.name = name;
        this.date = date;
        this.movie = movie;
        days = 7;
        seatCount = new int[days];
        Arrays.fill(seatCount, rows*columns);
        this.elite = elite;
        this.premium = premium;
        // this.budget = budget;
        this.partition = partition;
        seats = new boolean[days][rows][columns];
        for(int i=0; i<days; i++)
            for(int j=0; j<rows; j++)
                Arrays.fill(seats[i][j], true);
    }

    public void changeSeatState(int day, int row, int column)
    {
        seats[day][row][column] = !seats[day][row][column];
    }
    public boolean[][] getSeats(LocalDate date)
    {
        return seats[date.compareTo(this.date)];
    }
    public boolean getSeatState(int day, int row, int column)
    {
        return seats[day][row][column];
    }

    public int getSeatCount(int day)
    {
        return seatCount[day];
    }
    public void setSeatCount(int day, int i)
    {
        seatCount[day] += i;
    }

    public int getCost(int row)
    {
        return row<elite?200:(row<premium?150:100);
    }

    public String toString()
    {
        return String.format("%-26s%s", movie, name);
    }

    public int getElite() {
        return elite;
    }

    public int getPremium() {
        return premium;
    }

    public int getPartition() {
        return partition;
    }

    public String getMovie() {
        return movie;
    }

    public String getName() {
        return name;
    }

}
