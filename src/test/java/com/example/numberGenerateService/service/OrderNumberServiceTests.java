package com.example.numberGenerateService.service;

import com.example.numberGenerateService.entity.OrderNumber;
import com.example.numberGenerateService.repository.OrderNumberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class OrderNumberServiceTests {
    @Mock
    private OrderNumberRepository orderNumberRepository;

    @InjectMocks
    private OrderNumberService orderNumberServiceForTest;

    @Test
    @DisplayName("Test orderNumber's value is not exist functionality")
    public void givenOrderNumberNotExist_whenFindByValue_thenRepositoryIsCalled() {
        //given
        String value = "12345";
        BDDMockito.given(orderNumberRepository.findByValue(anyString()))
                .willReturn(Optional.empty());
        //when
        Boolean orderNumberIsNotExist = orderNumberServiceForTest.orderNumberNotExist(value);
        //then
        assertThat(orderNumberIsNotExist).isTrue();
    }

    @Test
    @DisplayName("Test save and return orderNumber functionality")
    public void givenCreateAndGetOrderNumberWithUniqueValue_whenSaveOrderNumber_thenRepositoryIsCalled() {
        //given
        String value = "12345";
        String date = "20240229";
        OrderNumber orderNumberToSave = new OrderNumber(UUID.randomUUID(), value, date);
        BDDMockito.given(orderNumberRepository.findByValue(anyString()))
                .willReturn(Optional.empty());
        BDDMockito.given(orderNumberRepository.save(any(OrderNumber.class)))
                .willReturn(orderNumberToSave);
        //when
        OrderNumber savedOrderNumber = orderNumberServiceForTest.createAndGetOrderNumberWithUniqueValue();
        //then
        assertThat(savedOrderNumber).isNotNull();
        assertEquals(orderNumberToSave.getValue(), savedOrderNumber.getValue());
        assertEquals(orderNumberToSave.getDate(), savedOrderNumber.getDate());
    }

    @Test
    @DisplayName("Test save and return orderNumber to String functionality")
    public void givenCreateAndGetOrderNumberWithUniqueValueToString_whenSaveOrderNumber_thenRepositoryIsCalled() {
        //given
        String value = "12345";
        String date = "20240229";
        OrderNumber orderNumberToSave = new OrderNumber(UUID.randomUUID(), value, date);
        BDDMockito.given(orderNumberRepository.findByValue(anyString()))
                .willReturn(Optional.empty());
        BDDMockito.given(orderNumberRepository.save(any(OrderNumber.class)))
                .willReturn(orderNumberToSave);
        //when
        String orderNumber = orderNumberServiceForTest.createAndGetOrderNumberWithUniqueValueToString();
        //then
        assertEquals(orderNumber, orderNumberToSave.getValue() + orderNumberToSave.getDate());
    }

    @Test
    @DisplayName("Test return all orderNumbers functionality")
    public void givenThreeOrderNumbersToSave_whenGetAllOrderNumbersFromDB_thenAllOrderNumbersIsReturned() {
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

        BDDMockito.given(orderNumberRepository.findAll())
                .willReturn(orderNumbersToSave);
        //when
        List<OrderNumber> returnedOrderNumbers = orderNumberServiceForTest.getAllOrderNumbersFromDB();
        //then
        assertThat(returnedOrderNumbers).isNotNull();
        assertThat(CollectionUtils.isEmpty(returnedOrderNumbers)).isFalse();
        assertThat(returnedOrderNumbers.size()).isEqualTo(orderNumbersToSave.size());
    }
}
