package com.denisfeier.pcbeEvents.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public abstract class Person {
    private String identifier;
    private String name;

    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    abstract void notify(double cost);

}