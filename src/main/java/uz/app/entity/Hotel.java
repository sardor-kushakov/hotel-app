package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();
}
