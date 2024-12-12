package com.arduino.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "data")
public class ArduinoData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "data_generator")
    private Long id;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "data_ora")
    private Instant dataOra;
}
