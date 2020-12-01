package com.denisfeier.pcbeEvents.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public abstract class Person {
    private String name;

    public String getName() {
        return this.name;
    }

}