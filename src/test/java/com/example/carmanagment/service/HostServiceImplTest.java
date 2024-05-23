package com.example.carmanagment.service;

import com.example.carmanagment.domain.exceptions.HostIdNotExistException;
import com.example.carmanagment.domain.models.Host;
import com.example.carmanagment.domain.models.HostId;
import com.example.carmanagment.service.forms.HostForm;
import com.example.carmanagment.service.forms.RentItemForm;
import com.example.carmanagment.value_objects.Car;
import com.example.carmanagment.value_objects.CarId;
import com.example.carmanagment.xportclient.CarCLient;
import com.example.sharedkernel.domain.credentials.Category;
import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HostServiceImplTest {
    @Autowired
    private HostService hostService;

    @Autowired
    private CarCLient carCLient;



    private static Car newCar(String brand, boolean availability, Category category,
                              double km) {
        Car p = new Car(CarId.randomId(CarId.class), brand, 0, availability, category, km);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        RentItemForm oi1 = new RentItemForm();
        oi1.setCar(newCar("Ford", true, Category.RACE, 12000.00));
        oi1.setQuantity(1);

        RentItemForm oi2 = new RentItemForm();
        oi2.setCar(newCar("Mazda", true, Category.SPORT, 72000.00));
        oi2.setQuantity(1);

        HostForm orderForm = new HostForm();
        orderForm.setItems(Arrays.asList(oi1,oi2));

        HostId newOrderId = hostService.placeOrder(orderForm);
        Host newOrder = hostService.findById(newOrderId).orElseThrow(HostIdNotExistException::new);
        Assertions.assertEquals(newOrder.getRentItemList().size(), 2);

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Car> productList = carCLient.findAll();

        Car p1 = productList.get(0);
        Car p2 = productList.get(1);

        RentItemForm oi1 = new RentItemForm();
        oi1.setCar(p1);
        oi1.setQuantity(1);

        RentItemForm oi2 = new RentItemForm();
        oi2.setCar(p2);
        oi2.setQuantity(3);

        HostForm orderForm = new HostForm();
        orderForm.setItems(Arrays.asList(oi1,oi2));

        HostId newOrderId = hostService.placeOrder(orderForm);
        Host newOrder = hostService.findById(newOrderId).orElseThrow(HostIdNotExistException::new);

        Assertions.assertEquals(newOrder.getRentItemList().size(), 2);
        }

}
