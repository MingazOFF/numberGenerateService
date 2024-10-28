package com.example.numberGenerateService.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "OrderNumbers")
@Schema(description = "Order number entity")
public class OrderNumber {
    @Id
    @Schema(description = "identifier")
    private UUID id;

    @Indexed(unique = true)
    @Schema(description = "order number's value", example = "12345")
    private String value;

    @Schema(description = "date of generation order number in YYYYMMDD format", example = "20240229")
    private String date;
}
