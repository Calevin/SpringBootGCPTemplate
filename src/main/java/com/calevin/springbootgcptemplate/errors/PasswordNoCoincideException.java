package com.calevin.springbootgcptemplate.errors;

public class PasswordNoCoincideException extends RuntimeException {
    public PasswordNoCoincideException() {
        super("Passwords no coinciden");
    }
}
