package uz.app.service.impl;

import uz.app.entity.Booking;
import uz.app.repository.BookingRepository;

import java.util.Date;
import java.util.List;

public class BookingServiceImpl implements BookingRepository {
    @Override
    public List<Booking> findBookings(Date startDate, Date endDate, Integer floor, Integer number) {
        return List.of();
    }
}
