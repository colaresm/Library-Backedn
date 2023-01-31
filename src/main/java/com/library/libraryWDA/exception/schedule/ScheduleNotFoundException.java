package com.library.libraryWDA.exception.schedule;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

public class ScheduleNotFoundException extends EntityNotFoundException{
    public ScheduleNotFoundException(UUID id) {
    super(String.format("O agendamento com id %s não existe", id));
}

    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
