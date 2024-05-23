package com.example.carmanagment.service;

import com.example.carmanagment.domain.models.Host;
import com.example.carmanagment.domain.models.HostId;
import com.example.carmanagment.domain.models.RentItemId;
import com.example.carmanagment.domain.exceptions.HostIdNotExistException;
import com.example.carmanagment.domain.exceptions.RentItemIdNotExistException;
import com.example.carmanagment.service.forms.HostForm;
import com.example.carmanagment.service.forms.RentItemForm;

import java.util.List;
import java.util.Optional;

public interface HostService {
    HostId placeOrder(HostForm hostForm);

    List<Host> findAll();

    Optional<Host> findById(HostId id);

    void addItem(HostId hostId, RentItemForm rentItemForm) throws HostIdNotExistException;

    void deleteItem(HostId hostId,  RentItemId rentItemId) throws HostIdNotExistException, RentItemIdNotExistException;


}
