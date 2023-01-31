package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.ReminderMultipleRequest;

import javax.mail.MessagingException;

public interface ReminderService {
    void sendToMultiple(ReminderMultipleRequest reminderMultipleRequest) throws MessagingException;

}
