package uz.app.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Cleanup;
import uz.app.entity.Booking;
import uz.app.entity.History;
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
    public static ArrayList<History> historyList = new ArrayList<>();

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String JSON_FILE = "src/main/resources/users.json";

    private static final File usersFile = new File("src/main/resources/users.json");
    private static final File bookingsFile = new File("src/main/resources/bookings.json");
    private static final File hotelsFile = new File("src/main/resources/hotels.json");
    private static final File historiesFile = new File("src/main/resources/histories.json");

    private static Database database;

    // get instance, design pattern:
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

    // adding default admin:
    static {
        database = new Database();
        database.addUser(new User("admin", "admin", "admin", "123", 0D, Role.ADMIN, false));
    }

    // save to json:
    public void saveUsersToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add user:
    public static void addUser(User user) {
        userList.add(user);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile));
            writer.write(gson.toJson(userList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // add booking:
    public static void addBooking(Booking booking) {
        bookingList.add(booking);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(bookingsFile));
            writer.write(gson.toJson(bookingList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // add hotel:
    public static void addHotel(Hotel hotel) {
        hotelList.add(hotel);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(hotelsFile));
            writer.write(gson.toJson(hotelList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // add history:
    public static void addHistory(History history) {
        historyList.add(history);
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(hotelsFile));
            writer.write(gson.toJson(historyList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // all users:
    public static List<User> getUsers() {
        return userList;
    }

    // all hotels:
    public static List<Hotel> getHotels() {
        Type type = new TypeToken<ArrayList<Hotel>>() {
        }.getType();
        try {
            hotelList = gson.fromJson(new FileReader(hotelsFile), type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    // all bookings:
    public List<Booking> getBookings() {
        Type type = new TypeToken<ArrayList<Booking>>() {
        }.getType();
        try {
            bookingList = gson.fromJson(new FileReader(bookingsFile), type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }

    // all histories:
    public List<History> getHistories() {
        Type type = new TypeToken<ArrayList<History>>() {
        }.getType();
        try {
            historyList = gson.fromJson(new FileReader(hotelsFile), type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return historyList;
    }
}
