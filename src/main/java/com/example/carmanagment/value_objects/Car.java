package com.example.carmanagment.value_objects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.credentials.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import java.util.Locale;

@Getter
public class Car implements ValueObject {
    private final CarId carId;
    private final String brand;
    private final Integer rents;
    private final boolean availability;
    private final Category category;
    private final double km;


    private Car(){

        this.carId = CarId.randomId(CarId.class);
        this.brand = "Ford";
        this.availability = true;
        this.category = Category.OLDTIMER;
        this.rents = 2;
        this.km = 123.00;
    }

    @JsonCreator
    public Car(CarId carId, String brand, Integer rents, boolean availability, Category category,
               double km)
    {
        this.carId = carId;
        this.brand = brand;
        this.availability = availability;
        this.category = category;
        this.rents = rents;
        this.km = km;
    }


}
