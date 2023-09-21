package com.example.helloworld;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EchoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 5)
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
