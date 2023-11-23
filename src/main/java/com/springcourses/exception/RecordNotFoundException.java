package com.springcourses.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Integer id) {
        super("Registro não encontrado com o id: " + id);
    }

}
