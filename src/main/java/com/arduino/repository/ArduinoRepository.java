package com.arduino.repository;

import com.arduino.entity.ArduinoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArduinoRepository extends JpaRepository<ArduinoData, Long> {

    @Query("SELECT MIN(t.temperature) FROM ArduinoData t")
    String findTemperaturaMinima();

    @Query("SELECT MAX(t.temperature) FROM ArduinoData t")
    String findTemperaturaMassima();
}
