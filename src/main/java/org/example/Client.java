package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Client {
    private long id;

    private String name;

    private int age;

    private List<PhoneNumber> numbers;
}
