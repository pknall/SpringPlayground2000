package com.github.pknall.springplayground2000;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class Job implements Serializable {


    private long id;

    @NotBlank
    @Size(min=5, message="Name must be at least 5 characters long.")
    private String name;

    private String args;
}
