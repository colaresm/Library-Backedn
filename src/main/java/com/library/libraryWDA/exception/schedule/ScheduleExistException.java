package com.library.libraryWDA.exception.schedule;

import javax.persistence.EntityExistsException;

public class ScheduleExistException extends EntityExistsException {

    public ScheduleExistException() {
        super(String.format("Já existe uma consulta agendada para o profissional selecionado nesta data e horário"));
    }
}