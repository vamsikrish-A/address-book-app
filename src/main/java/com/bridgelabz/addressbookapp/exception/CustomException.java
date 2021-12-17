package com.bridgelabz.addressbookapp.exception;

/**
 * @purpose: class used to  show Custom Exceptional handler Super message.
 * @author: VamsiKrishna
 * @since: 15-12-2021
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
