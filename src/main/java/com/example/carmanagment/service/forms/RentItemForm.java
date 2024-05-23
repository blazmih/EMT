package com.example.carmanagment.service.forms;

import com.example.carmanagment.value_objects.Car;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentItemForm {
    @NotNull
    private Car car;
    private Integer quantity=1;
}
