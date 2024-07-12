package uz.app.repository;

import uz.app.entity.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepository {
    List<Booking> findBookings(Date startDate, Date endDate, Integer floor, Integer number);
}
