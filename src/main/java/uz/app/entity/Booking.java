package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Date startDate;
    private Date endDate;
    private Integer floor;
    private Room room;
    private boolean active;
    private boolean temporary;

    public Booking(Date startDate, Date endDate, Boolean temporary) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.temporary = temporary;
    }
}
