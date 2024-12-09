package com.Arduino.demo.service;

import com.Arduino.demo.dto.ArduinoDTO;
import org.springframework.ui.Model;


public interface ArduService {

     void manageData(ArduinoDTO dto);
     String viewData(Model model);
}
