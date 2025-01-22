package com.arduino.util;

import com.arduino.repository.ArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;


@Service
public class ArduUtil {

    @Autowired
    private ArduinoRepository arduinoRepository;

    public Map<String, Object> mappingMediaTemperatura(Long giorni) {
        Instant giorniDaEsaminare = LocalDateTime.now().minusDays(giorni).toInstant(ZoneOffset.UTC);
        List<Object[]> dati = arduinoRepository.findMediaPerGiorno(giorniDaEsaminare);
        return dati.stream()
                .sorted(Comparator.comparing(r -> (Date) r[0]))
                .collect(Collectors.toMap(
                        r -> {
                            Date sqlDate = (Date) r[0];
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            return sdf.format(sqlDate);
                        },
                        r -> r[1],
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}
