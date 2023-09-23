package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    private int status;
    private String message;
    private Object data;

    public Msg(int status) {
        this.status = status;
        this.message = "";
        this.data = null;
    }

    public Msg(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public Msg(int status, Object data) {
        this.status = status;
        this.message = "";
        this.data = data;
    }

}
