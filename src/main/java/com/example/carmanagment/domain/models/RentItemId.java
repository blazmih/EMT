package com.example.carmanagment.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;

public class RentItemId extends DomainObjectId {
    private RentItemId() {
        super(RentItemId.randomId(RentItemId.class).getId());
    }

    public RentItemId(String uuid) {
        super(uuid);
    }
}
