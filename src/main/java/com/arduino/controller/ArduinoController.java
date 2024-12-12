package com.arduino.controller;

import com.arduino.dto.ArduinoDTO;
import com.arduino.service.ArduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class ArduinoController {

    @Autowired
    private ArduService arduService;


    @PostMapping("/receiveData")
    public ResponseEntity<String> receiveData(@RequestBody ArduinoDTO dto) {
        arduService.manageData(dto);
        return ResponseEntity.ok("dati ricevuti e elaborati");
    }


    @GetMapping("/viewData")
    public String viewData(Model model) {
        arduService.viewData(model);
        return "pagina";
    }
}
