package com.example.carmanagment.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;

public class HostId extends DomainObjectId {
    private HostId() {
        super(HostId.randomId(HostId.class).getId());
    }

    public HostId(String uuid) {
        super(uuid);
    }

}
