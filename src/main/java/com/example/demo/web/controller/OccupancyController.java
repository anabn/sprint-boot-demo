package com.example.demo.web.controller;

import com.example.demo.service.RoomReservationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/occupancy")
public class OccupancyController {

    private final RoomReservationService roomReservationService;

    public OccupancyController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public String getOccupancy(Model model, @RequestParam(value = "date", required = false) String dateString) {
        Date date =  new Date();
        if (StringUtils.isNotBlank(dateString)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Sorry, invalid format");
            }
        }
        model.addAttribute("date", date);
        model.addAttribute("reservations", this.roomReservationService.getRoomReservationsForDate(dateString));
        return "occupancy";
    }
}
