package com.api.delivery.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.api.delivery.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiOperation("Confirmação de pedido")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
			@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> confirmar(
			@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
					required = true)
					String codigoPedido);

	@ApiOperation("Cancelamento de pedido")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
			@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> cancelar(
			@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
					required = true)
					String codigoPedido);

	@ApiOperation("Registrar entrega de pedido")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso"),
			@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> entregar(
			@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
					required = true)
					String codigoPedido);

}
