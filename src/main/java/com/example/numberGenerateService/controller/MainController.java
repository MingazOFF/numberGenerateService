package com.example.numberGenerateService.controller;

import com.example.numberGenerateService.entity.OrderNumber;
import com.example.numberGenerateService.service.OrderNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Main controller", description = "This is the main and only controller")
public class MainController {

    private final OrderNumberService orderNumberService;

    @GetMapping("/numbers")
    @Operation(
            summary = "Get the new order number in String format",
            description = "Create new unique order number and get in String format"
    )
    public String getOrderNumberValueAndDate() {
        return orderNumberService.createAndGetOrderNumberWithUniqueValueToString();
    }

    @GetMapping("/numbers2")
    @Operation(
            summary = "Get the new order number in JSON format",
            description = "Create new unique order number and get in JSON format"
    )
    public OrderNumber getOrderNumber() {
        return orderNumberService.createAndGetOrderNumberWithUniqueValue();
    }

    @GetMapping("/numbers/all")
    @Operation(
            summary = "Get all order numbers in JSON format",
            description = "Get all order numbers from DB in String format"
    )
    public List<OrderNumber> getAllOrderNumber() {
        return orderNumberService.getAllOrderNumbersFromDB();
    }
}
