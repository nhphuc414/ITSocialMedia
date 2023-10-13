package com.nhp.itsocialserver.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse implements Serializable {
    private int status;
    private String message;
    private Object data;
}
