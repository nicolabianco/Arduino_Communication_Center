package com.arduino.scheduler;

import com.arduino.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 9 * * MON")
    private void sendSchedulatedEmail(){
        emailService.sendEmail();
    }
}
