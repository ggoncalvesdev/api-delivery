package com.api.delivery.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoInput {

	@ApiModelProperty(example = "Cartão de crédito", required = true)
	@NotNull
	private Long id;
	
}
