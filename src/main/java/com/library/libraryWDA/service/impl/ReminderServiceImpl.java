package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.ReminderMultipleRequest;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.service.PatientService;
import com.library.libraryWDA.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
public class ReminderServiceImpl implements ReminderService {
    @Autowired
    private JavaMailSender javaMailSender;

    private PatientService patientService;

    public ReminderServiceImpl(JavaMailSender javaMailSender, PatientService patientService) {
        this.javaMailSender = javaMailSender;
        this.patientService = patientService;
    }

    public void sendToMultiple(ReminderMultipleRequest reminderMultipleRequest) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        for (String email: reminderMultipleRequest.getEmail()) {
            Patient patientFound = patientService.getByEmail(email);
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("library - Lembretes");
            helper.setTo(InternetAddress.parse(patientFound.getEmail()));
            helper.setText(reminderMultipleRequest.getBody(), true);
            javaMailSender.send(message);
        }
    }
}