package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.RemindersControllerDocs;
import com.library.libraryWDA.dto.ReminderMultipleRequest;
import com.library.libraryWDA.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/reminders")
public class RemindersController implements RemindersControllerDocs {

    private final ReminderService reminderService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/send-multiple-mail")
    public void sendToMultiple(@RequestBody ReminderMultipleRequest reminderMultipleRequest) throws MessagingException {
        reminderService.sendToMultiple(reminderMultipleRequest);

    }
}
