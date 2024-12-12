package com.arduino.service;

import com.arduino.dto.ArduinoDTO;
import org.springframework.ui.Model;


public interface ArduService {

     void manageData(ArduinoDTO dto);
     String viewData(Model model);
}
