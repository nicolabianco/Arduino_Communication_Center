package com.arduino.service.impl;

import com.arduino.dto.ArduinoDTO;
import com.arduino.dto.DataDTO;
import com.arduino.mapper.ArduinoMapper;
import com.arduino.repository.ArduinoRepository;
import com.arduino.service.ArduService;
import com.arduino.service.TelegramService;
import com.arduino.util.ArduUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;


@Service
public class ArduServiceImpl implements ArduService {

    private DataDTO data;

    public ArduServiceImpl() {
        this.data = new DataDTO();
    }

    @Autowired
    private ArduUtil arduUtil;

    @Autowired
    private ArduinoRepository arduinoRepository;

    @Autowired
    private TelegramService telegramService;

    public void manageData(ArduinoDTO dto){
        data.setTemperatura(dto.getTemperatura());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currentDate = now.format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(timeFormatter);
        data.setDataLettura(currentDate);
        data.setOraLettura(currentTime);
        arduinoRepository.save(ArduinoMapper.toEntity(data));
        telegramService.sendMessage(dto.getTemperatura());
    }

    public String showData(Model model){
        String temperaturaDefault = "N/A";
        String dataLetturaDefault = "N/A";
        String oraLetturaDefault = "N/A";

        String temperatura = Objects.requireNonNullElse(data.getTemperatura(), temperaturaDefault);
        String dataLettura = Objects.requireNonNullElse(data.getDataLettura(), dataLetturaDefault);
        String oraLettura = Objects.requireNonNullElse(data.getOraLettura(), oraLetturaDefault);

        model.addAttribute("temperatura", temperatura);
        model.addAttribute("data_lettura", dataLettura);
        model.addAttribute("ora_lettura", oraLettura);
        model.addAttribute("temperatura_minima", arduinoRepository.findTemperaturaMinima());
        model.addAttribute("temperatura_massima", arduinoRepository.findTemperaturaMassima());
        return "pagina";
    }

    @Override
    public ResponseEntity<byte[]> downloadReport() {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Report temperature").setBold().setFontSize(16));
            Map<String, Object> data = arduUtil.mappingMediaTemperatura(7l);
            float[] columnWidths = {1, 3};
            Table table = new Table(columnWidths);
            table.addHeaderCell("Data");
            table.addHeaderCell("Temperatura");

            for ( Map.Entry<String, Object> d : data.entrySet()) {
                table.addCell(d.getKey());
                table.addCell(String.valueOf(d.getValue()));
            }
            document.add(table);
            document.close();
            byte[] pdfBytes = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report_temperature.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
