package com.library.libraryWDA.exception.schedule;

import javax.persistence.EntityExistsException;

public class ScheduleInProgress   extends EntityExistsException {

    public ScheduleInProgress() {
        super(String.format("Já existe uma consulta em andamento"));
    }
}
