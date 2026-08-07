package br.com.guto.spring_boot_jpa.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
