package com.example.carmanagment.service.impl;

import com.example.carmanagment.domain.models.Host;
import com.example.carmanagment.domain.models.HostId;
import com.example.carmanagment.domain.models.RentItemId;
import com.example.carmanagment.domain.exceptions.HostIdNotExistException;
import com.example.carmanagment.domain.exceptions.RentItemIdNotExistException;
import com.example.carmanagment.domain.repository.HostRepository;
import com.example.carmanagment.service.HostService;
import com.example.carmanagment.service.forms.HostForm;
import com.example.carmanagment.service.forms.RentItemForm;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.infra.DomainEventPublisher;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    @Autowired
    private LocalValidatorFactoryBean validator;

    private final DomainEventPublisher domainEventPublisher;


    @Override
    public HostId placeOrder(HostForm hostForm) {
        BindingResult bindingResult = new BeanPropertyBindingResult(hostForm, "shelterForm");
        validator.validate(hostForm, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation failed for HostForm");
        }
        var newHost = hostRepository.saveAndFlush(toDomainObject(hostForm));
        //newHost.getRentItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getCarId().getId(),item.getQuantity())));
        return newHost.getId();


    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(HostId id) {
        return hostRepository.findById(id);
    }

    @Override
    public void addItem(HostId hostId, RentItemForm rentItemForm) throws HostIdNotExistException {
        Host host = hostRepository.findById(hostId).orElseThrow(HostIdNotExistException::new);
        host.addItem(rentItemForm.getCar(),rentItemForm.getQuantity());
        hostRepository.saveAndFlush(host);
       // domainEventPublisher.publish(new OrderItemCreated(rentItemForm.getCar().getCarId().getId(),rentItemForm.getQuantity()));

    }

    @Override
    public void deleteItem(HostId hostId, RentItemId rentItemId) throws HostIdNotExistException, RentItemIdNotExistException {
        Host host = hostRepository.findById(hostId).orElseThrow(HostIdNotExistException::new);
        host.removeItem(rentItemId);
        hostRepository.saveAndFlush(host);

    }
    private Host toDomainObject(HostForm hostForm) {
        var host = new Host(Instant.now());
        hostForm.getItems().forEach(item->host.addItem(item.getCar(), item.getQuantity()));
        return host;
    }

}
