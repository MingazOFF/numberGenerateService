package com.example.numberGenerateService.repository;

import com.example.numberGenerateService.entity.OrderNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class OrderNumberRepositoryTests {
    @Autowired
    OrderNumberRepository orderNumberRepository;

    @BeforeEach
    public void setUp() {
        orderNumberRepository.deleteAll();
    }

    @Test
    @DisplayName("Test orderNumber save functionality")
    public void saveTest() {
        //given
        String value = "12345";
        String date = "20240229";
        OrderNumber orderNumberToSave = new OrderNumber(UUID.randomUUID(), value, date);
        //when
        OrderNumber savedOrderNumber = orderNumberRepository.save(orderNumberToSave);
        //then
        assertThat(savedOrderNumber).isNotNull();
        assertEquals(orderNumberToSave.getValue(), savedOrderNumber.getValue());
        assertEquals(orderNumberToSave.getDate(), savedOrderNumber.getDate());

    }

    @Test
    @DisplayName("Test get orderNumber by value functionality")
    public void findByValueTest() {
        //given
        String value = "12345";
        String date = "20240229";
        OrderNumber orderNumberToSave = new OrderNumber(UUID.randomUUID(), value, date);
        orderNumberRepository.save(orderNumberToSave);
        //when
        OrderNumber returnedOrderNumber = orderNumberRepository.findByValue(value).orElse(null);
        //then
        assertThat(returnedOrderNumber).isNotNull();
        assertEquals(orderNumberToSave.getValue(), returnedOrderNumber.getValue());
        assertEquals(orderNumberToSave.getDate(), returnedOrderNumber.getDate());

    }

    @Test
    @DisplayName("Test orderNumber not found functionality")
    public void findByValueReturnNullTest() {
        //given
        String value = "12345";
        //when
        OrderNumber returnedOrderNumber = orderNumberRepository.findByValue(value).orElse(null);
        //then
        assertThat(returnedOrderNumber).isNull();

    }

    @Test
    @DisplayName("Test get all orderNumbers functionality")
    public void findAllTest() {
        //given
        String value1 = "12345";
        String date1 = "20240229";
        OrderNumber orderNumberToSave1 = new OrderNumber(UUID.randomUUID(), value1, date1);
        String value2 = "12344";
        String date2 = "20240228";
        OrderNumber orderNumberToSave2 = new OrderNumber(UUID.randomUUID(), value2, date2);
        String value3 = "12343";
        String date3 = "20240227";
        OrderNumber orderNumberToSave3 = new OrderNumber(UUID.randomUUID(), value3, date3);
        List<OrderNumber> orderNumbersToSave = List.of(orderNumberToSave1, orderNumberToSave2, orderNumberToSave3);
        orderNumberRepository.saveAll(orderNumbersToSave);
        //when
        List<OrderNumber> returnedOrderNumbers = orderNumberRepository.findAll();
        //then
        assertThat(CollectionUtils.isEmpty(returnedOrderNumbers)).isFalse();
        assertThat(returnedOrderNumbers.size()).isEqualTo(orderNumbersToSave.size());


    }
}
