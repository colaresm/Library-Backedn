package com.library.libraryWDA.controller.docs;

import com.library.libraryWDA.dto.ReminderMultipleRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;

@Api("Reminders Management")
public interface RemindersControllerDocs {

    @ApiOperation(value = "Envia um lembrete para múltiplos e-mails")
    void sendToMultiple(@RequestBody ReminderMultipleRequest reminderMultipleRequest) throws MessagingException;
}
