package com.example.demo.data.repository;

import com.example.demo.data.entity.Guest;
import com.example.demo.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}
