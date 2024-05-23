package com.example.carmanagment.value_objects;

import com.example.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;

@Embeddable
public class CarId extends DomainObjectId {
    public CarId() {
        super(CarId.randomId(CarId.class).getId());
    }

    public CarId(String uuid) {
        super(uuid);
    }

}
