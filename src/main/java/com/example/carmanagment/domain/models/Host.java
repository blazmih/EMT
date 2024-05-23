package com.example.carmanagment.domain.models;

import com.example.carmanagment.value_objects.Car;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.credentials.Email;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
public class Host extends AbstractEntity<HostId> {
    private String name;
    private String surname;
    private Email email;
    private Instant rentedOn;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RentItem> rentItemList=new HashSet<>();

    private Host(){
        super(HostId.randomId(HostId.class));
    }
    public Host(Instant now){
        super(HostId.randomId(HostId.class));
        this.rentedOn = now;
    }

    public RentItem addItem(@NonNull Car car, Integer q){
        Objects.requireNonNull(car, "car must not be null");
        var item = new RentItem(car.getCarId(), q);
        rentItemList.add(item);
        return item;
    }
    public void removeItem(@NonNull RentItemId rentItemId){
        Objects.requireNonNull(rentItemId, "id must not be null");
        rentItemList.removeIf(v->v.getId().equals(rentItemId));
    }
}
