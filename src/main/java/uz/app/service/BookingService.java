package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.Booking;
import uz.app.entity.Room;
import uz.app.repository.BookingRepository;
import uz.app.util.Utils;

import java.util.Date;
import java.util.List;

public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public boolean isRoomAvailable(Date startDate, Date endDate, Room room) {
        List<Booking> bookings = bookingRepository.findBookings(startDate, endDate, room.getFloor(), room.getNumber());

        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room) &&
                    booking.getStartDate().before(endDate) &&
                    booking.getEndDate().after(startDate)) {
                return false;
            }
        }
        return true;
    }

    public boolean createBooking(Date startDate, Date endDate, Room room) {
        if (isRoomAvailable(startDate, endDate, room)) {
            Booking booking = new Booking();

            booking.setConsumerId(Utils.getCurrentUser().getId());
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setRoom(room);
            booking.setActive(true);
            booking.setTemporary(false);

            Database.addBooking(booking);

            return true;
        } else {
            return false;
        }
    }
}
