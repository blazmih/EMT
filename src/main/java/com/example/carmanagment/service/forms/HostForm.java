package com.example.carmanagment.service.forms;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class HostForm {
    @Valid
    @NotEmpty
    private List<RentItemForm> items = new ArrayList<>();

}
