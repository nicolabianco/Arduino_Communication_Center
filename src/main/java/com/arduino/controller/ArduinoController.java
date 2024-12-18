package com.arduino.controller;

import com.arduino.dto.ArduinoDTO;
import com.arduino.service.ArduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/data")
public class ArduinoController {

    @Autowired
    private ArduService arduService;


    @PostMapping("/receiveData")
    @ResponseBody
    public ResponseEntity<String> receiveData(@RequestBody ArduinoDTO dto) {
        arduService.manageData(dto);
        return ResponseEntity.ok("dati ricevuti e elaborati");
    }


    @GetMapping("/showData")
    public String showData(Model model) {
        arduService.showData(model);
        return "pagina";
    }
}
