package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String consumerId;
    private Date startDate;
    private Date endDate;
    private Room room;
    private boolean active;
    private boolean temporary;
    private final Timestamp createdTime = new Timestamp(System.currentTimeMillis());

    public Booking(Date startDate, Date endDate, Boolean temporary) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.temporary = temporary;
    }
}
