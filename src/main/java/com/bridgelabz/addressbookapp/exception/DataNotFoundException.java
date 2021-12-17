package com.bridgelabz.addressbookapp.exception;

/**
 * @purpose: class used to  show DataNotFound Exceptional handler Super message.
 * @author: VamsiKrishna
 * @since: 15-12-2021
 */
public class DataNotFoundException extends CustomException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
