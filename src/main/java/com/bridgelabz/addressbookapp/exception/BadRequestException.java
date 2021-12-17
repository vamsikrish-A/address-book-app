package com.bridgelabz.addressbookapp.exception;

/**
 * @purpose: class used to  show BadRequest Exceptional handler Super message.
 * @author: VamsiKrishna
 * @since: 15-12-2021
 */
public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message);
    }
}
