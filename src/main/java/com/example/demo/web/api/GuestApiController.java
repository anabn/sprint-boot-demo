package com.example.demo.web.api;

import com.example.demo.data.entity.Guest;
import com.example.demo.data.repository.GuestRepository;
import com.example.demo.web.exception.BadRequestException;
import com.example.demo.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
    private final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getGuests() {
        return this.guestRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest) {
        return this.guestRepository.save(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable("id") long id) {
        Optional<Guest> guest = this.guestRepository.findById(id);
        if (guest.isEmpty()) {
            throw new NotFoundException("doesn't find");
        }
        return guest.get();
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
        if (id != guest.getId()) {
            throw new BadRequestException("id or path don't match the body");
        }
        return this.guestRepository.save(guest);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable("id") long id) {
        this.guestRepository.deleteById(id);
    }
}
