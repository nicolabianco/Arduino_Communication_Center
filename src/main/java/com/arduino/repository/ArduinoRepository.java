package com.arduino.repository;

import com.arduino.entity.ArduinoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArduinoRepository extends JpaRepository<ArduinoData, Long> {
}
