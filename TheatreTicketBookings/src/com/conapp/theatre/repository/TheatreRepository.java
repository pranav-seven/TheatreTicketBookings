package com.conapp.theatre.repository;

import java.util.List;

import com.conapp.theatre.dto.*;

import java.util.ArrayList;
import java.time.LocalDate;

public class TheatreRepository {
    private static TheatreRepository repoInstance;
    private List<Admin> adminList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Screen> screenList = new ArrayList<>();

    private TheatreRepository()
    {
        adminList.add(new Admin("Nedumaaran", "maaran@deccanairlines.com", "maaran", "flightkuowner"));
        userList.add(new User("Vikram", "vikramakakarnan@gmail.com", "vikram", "aarambikalangala!"));
        userList.add(new User("Amar", "amarchief@gmail.com", "amar", "blacksquad"));
        userList.add(new User("Sandhanam", "drsandhanam@gmail.com", "drsandhanam", "methdoctor"));
        userList.add(new User("Thor, son of Odin", "thorodinson@gmail.com", "thor", "pointbreak"));
        screenList.add(new Screen("Maris", "Avengers: Endgame", LocalDate.now(), 23, 30, 10, 20, 23, 7));
        screenList.add(new Screen("Sona", "Baatsha", LocalDate.now(), 23, 30, 10, 20, 23, 7));
        screenList.add(new Screen("Meena", "Vikram", LocalDate.now(), 23, 30, 10, 20, 23, 7));
        screenList.add(new Screen("Shanthi", "Ant-Man 3", LocalDate.now(), 20, 26, 9, 17,20, 6));
        screenList.add(new Screen("Rockstar", "Vasool Raja", LocalDate.now(), 20, 26, 9, 17, 20, 6));
    }
 
    public static TheatreRepository getInstance()
    {
        if(repoInstance==null)
            repoInstance = new TheatreRepository();
        return repoInstance;
    }

    public List<Screen> getScreens(LocalDate date)
    {
        int difference = date.compareTo(LocalDate.now());
        if(difference<0 || difference>7)
            return null;
        return screenList;
    }

    public Screen getScreen(int movieNo)
    {
        if(movieNo>=0 && movieNo<screenList.size())
            return screenList.get(movieNo);
        return null;
    }
    public List<User> getUsers()
    {
        return userList;
    }

    public List<Admin> getAdmins()
    {
        return adminList;
    }

}
