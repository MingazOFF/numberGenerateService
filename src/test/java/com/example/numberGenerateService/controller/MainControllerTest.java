package com.example.numberGenerateService.controller;

import com.example.numberGenerateService.entity.OrderNumber;
import com.example.numberGenerateService.service.OrderNumberService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private OrderNumberService orderNumberService;


    @Test
    @DisplayName("Test create orderNumber and return String functionality")
    public void getOrderNumberValueAndDateTest() throws Exception {
        //given
        String value = "12345";
        String date = "20240229";
        OrderNumber orderNumberToSave = new OrderNumber(UUID.randomUUID(), value, date);
        BDDMockito.given(orderNumberService.createAndGetOrderNumberWithUniqueValueToString())
                .willReturn(value + date);
        //when
        ResultActions resultActions = mockMvc.perform(get("/numbers"));

        //then
        resultActions
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(value + date));
    }

    @Test
    @DisplayName("Test create orderNumber and return JSON functionality")
    public void getOrderNumberTest() throws Exception {
        //given
        String value = "12345";
        String date = "20240229";
        OrderNumber orderNumberToSave = new OrderNumber(UUID.randomUUID(), value, date);
        BDDMockito.given(orderNumberService.createAndGetOrderNumberWithUniqueValue())
                .willReturn(orderNumberToSave);
        //when
        ResultActions resultActions = mockMvc.perform(get("/numbers2"));
        //then
        resultActions
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", CoreMatchers.is(value)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(date)));
    }


    @Test
    @DisplayName("Test returned all orderNumbers in JSON functionality")
    public void getAllOrderNumberTest() throws Exception {
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

        BDDMockito.given(orderNumberService.getAllOrderNumbersFromDB())
                .willReturn(orderNumbersToSave);
        //when
        ResultActions resultActions = mockMvc.perform(get("/numbers/all"));
        //then
        resultActions
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value", CoreMatchers.is(value1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date", CoreMatchers.is(date1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].value", CoreMatchers.is(value2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date", CoreMatchers.is(date2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].value", CoreMatchers.is(value3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].date", CoreMatchers.is(date3)));
    }
}
