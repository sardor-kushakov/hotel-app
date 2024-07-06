package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.app.enums.RoomType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Integer roomNumber;
    private RoomType type;
    private boolean isAvailable;
}
