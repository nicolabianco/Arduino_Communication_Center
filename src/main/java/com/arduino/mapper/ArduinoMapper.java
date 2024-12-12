package com.arduino.mapper;

import com.arduino.dto.DataDTO;
import com.arduino.entity.ArduinoData;
import lombok.Data;

import java.time.Instant;

@Data
public class ArduinoMapper {

    public static ArduinoData toEntity(DataDTO dto){
        ArduinoData arduinoData = new ArduinoData();
        arduinoData.setTemperature(dto.getTemperatura());
        arduinoData.setDataOra(Instant.now());
        return arduinoData;
    }
}
