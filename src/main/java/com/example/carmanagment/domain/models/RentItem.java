package com.example.carmanagment.domain.models;

import com.example.carmanagment.value_objects.CarId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Entity
@Getter
@AttributeOverride(name = "id", column = @Column(name = "car_id"))
public class RentItem extends AbstractEntity<RentItemId> {
    private Integer quantity;
    private CarId carId;

    public RentItem(CarId carId, @NonNull Integer quantity){
        super(RentItemId.randomId(RentItemId.class));
        this.carId = carId;
        this.quantity = quantity;
    }

    public RentItem() {

    }
}
