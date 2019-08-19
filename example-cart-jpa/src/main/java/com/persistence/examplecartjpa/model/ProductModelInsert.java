package com.persistence.examplecartjpa.model;

import com.persistence.examplecartjpa.constant.MessageConstan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelInsert {

    @NotBlank(message = MessageConstan.dataNull)
    private String name;

    @Min(value = 1000, message = MessageConstan.dataMin)
    @NotNull(message = MessageConstan.dataNull)
    private long price;

    @Min(value = 1, message = MessageConstan.dataMin)
    @NotNull(message = MessageConstan.dataNull)
    private int stock;

}
