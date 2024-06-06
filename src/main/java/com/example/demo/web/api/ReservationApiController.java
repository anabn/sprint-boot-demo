package com.example.demo.web.api;

import com.example.demo.data.entity.Reservation;
import com.example.demo.data.repository.ReservationRepository;
import com.example.demo.web.exception.BadRequestException;
import com.example.demo.web.exception.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllReservations(@RequestParam(value = "date", required = false) String dateString) {
        if (StringUtils.isNotBlank(dateString)) {
            Date date = new Date(new java.util.Date().getTime());
            return this.reservationRepository.findAllByReservationDate(date);
        }
        return this.reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return this.reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable("id") long id) {
        Optional<Reservation> reservation = this.reservationRepository.findById(id);
        if (reservation.isEmpty()) {
            throw new NotFoundException("Reservation is not found");
        }
        return reservation.get();
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        if (id != reservation.getId()) {
            throw new BadRequestException("id or path do not match");
        }
        return this.reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteReservation(@PathVariable("id") long id) {
        this.reservationRepository.deleteById(id);
    }
}
