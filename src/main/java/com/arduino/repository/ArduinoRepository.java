package com.arduino.repository;

import com.arduino.entity.ArduinoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ArduinoRepository extends JpaRepository<ArduinoData, Long> {

    @Query("SELECT MIN(t.temperature) FROM ArduinoData t")
    String findTemperaturaMinima();

    @Query("SELECT MAX(t.temperature) FROM ArduinoData t")
    String findTemperaturaMassima();

    @Query("SELECT FUNCTION('DATE', o.dataOra) AS giorno, ROUND(AVG(o.temperature), 1) AS media " +
            "FROM ArduinoData o " +
            "WHERE o.dataOra >= :dataInizio " +
            "GROUP BY FUNCTION('DATE', o.dataOra) " +
            "ORDER BY giorno")
    List<Object[]> findMediaPerGiorno(@Param("dataInizio") Instant dataInizio);
}
