package com.library.libraryWDA.exception.query;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
public class QueryNotFoundException extends EntityNotFoundException {

    public QueryNotFoundException(UUID id){
        super(String.format("A consulta com o id %s não existe!", id));
    }
     
}