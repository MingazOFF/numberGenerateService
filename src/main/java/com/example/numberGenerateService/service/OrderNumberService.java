package com.example.numberGenerateService.service;

import com.example.numberGenerateService.entity.OrderNumber;
import com.example.numberGenerateService.repository.OrderNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderNumberService {
    private final OrderNumberRepository orderNumberRepository;

    public boolean orderNumberNotExist (String value) {
        return orderNumberRepository.findByValue(value).isEmpty();
    }

    public OrderNumber createAndGetOrderNumberWithUniqueValue() {
        int numberOfDigitInValue = 5;
        String value = null;
        do {
            value = Integer.toString(new Random().nextInt((int) Math.pow(10,(numberOfDigitInValue-1)),(int) Math.pow(10, numberOfDigitInValue)));
        } while (!orderNumberNotExist(value));
        String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        OrderNumber orderNumber = new OrderNumber(UUID.randomUUID(), value, date);
        return orderNumberRepository.save(orderNumber);
    }

    public String createAndGetOrderNumberWithUniqueValueToString() {
        OrderNumber orderNumber = createAndGetOrderNumberWithUniqueValue();
        return orderNumber.getValue()+orderNumber.getDate();
    }

    public List<OrderNumber> getAllOrderNumbersFromDB() {
        return orderNumberRepository.findAll();
    }
}
