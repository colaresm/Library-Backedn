package com.library.libraryWDA.exception.schedule;
import javax.persistence.EntityExistsException;
public class ScheduleDateNotAvailable  extends EntityExistsException {
    public ScheduleDateNotAvailable() {
        super(String.format("Não há disponibilidade de agendamentos para esse dia"));
    }
}
