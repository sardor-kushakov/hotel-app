package uz.app.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Cleanup;
import uz.app.entity.Booking;
import uz.app.entity.Hotel;
import uz.app.entity.User;
import uz.app.enums.Role;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Booking> bookingList = new ArrayList<>();
    public static ArrayList<Hotel> hotelList = new ArrayList<>();

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final File usersFile = new File("src/main/resources/users.json");
    private static final File bookingsFile = new File("src/main/resources/bookings.json");
    private static final File hotelsFile = new File("src/main/resources/hotels.json");

    private static Database database;

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    {
        try {
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            userList = gson.fromJson(new FileReader(usersFile), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static {
        database = new Database();
        database.addUser(new User("admin", "admin", "admin", "123", 0D, Role.ADMIN));
    }

    public static void addUser(User user) {
        userList.add(user);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile));
            writer.write(gson.toJson(userList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBooking(Booking booking) {
        bookingList.add(booking);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(bookingsFile));
            writer.write(gson.toJson(bookingList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addHotel(Hotel hotel) {
        hotelList.add(hotel);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(hotelsFile));
            writer.write(gson.toJson(hotelList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() {
        return userList;
    }

    public List<Hotel> getHotels() {
        return hotelList;
    }

    public List<Booking> getBookings() {
        return bookingList;
    }
}
