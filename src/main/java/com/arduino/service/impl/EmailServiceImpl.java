package com.arduino.service.impl;

import com.arduino.repository.ArduinoRepository;
import com.arduino.service.EmailService;
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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

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
        List<Object[]> results = mediaUltimiSettegiorni();
        Map<String, Object> re = results.stream()
                .collect(Collectors.toMap(
                        r -> {
                                Date sqlDate = (Date) r[0];
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                return sdf.format(sqlDate);
                        },
                        r -> r[1]
                ));
        context.setVariable("giorniETemperature", re);
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

    private List<Object[]> mediaUltimiSettegiorni() {
        Instant setteGiorniFa = LocalDateTime.now().minusDays(7).toInstant(ZoneOffset.UTC);
        return arduinoRepository.findMediaPerGiorno(setteGiorniFa);
    }
}
