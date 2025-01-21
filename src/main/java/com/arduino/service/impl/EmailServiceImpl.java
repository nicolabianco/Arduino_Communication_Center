package com.arduino.service.impl;

import com.arduino.repository.ArduinoRepository;
import com.arduino.service.EmailService;
import com.arduino.util.ArduUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ArduUtil arduUtil;

    @Autowired
    private ArduinoRepository arduinoRepository;

    @Value("${mail.sender}")
    private String senderEmail;

    @Value("${mail.receiver}")
    private String receiverEmail;

    @Override
    @Async
    public void sendEmail() {
        Context context = new Context();


        context.setVariable("giorniETemperature", arduUtil.mappingMediaTemperatura(7l));
        String htmlContent = templateEngine.process("emailTemplate", context);
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receiverEmail);
            helper.setSubject("riepilogo settimanale");
            helper.setText(htmlContent, true);
            helper.setFrom(senderEmail);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
