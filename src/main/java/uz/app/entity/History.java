package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {
    private final String id = UUID.randomUUID().toString();
    private User user;
    private Hotel hotel;
    private Booking booking;
}