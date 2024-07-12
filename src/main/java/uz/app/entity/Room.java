package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private final String id = UUID.randomUUID().toString();
    private Integer floor;
    private Integer number;
}
