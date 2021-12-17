package com.bridgelabz.addressbookapp.dto;
/**
 * @purpose: response for http methods shows message and object data such as Http status code.
 * @author: VamsiKrishna
 * @Since: 15/12/2021
 */

import lombok.Data;

@Data
public class ResponseDto {
    private String message;
    private Object data;

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }


}
