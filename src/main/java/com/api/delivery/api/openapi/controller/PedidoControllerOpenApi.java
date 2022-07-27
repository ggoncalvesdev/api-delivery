package com.api.delivery.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.api.delivery.api.exceptionhandler.Problem;
import com.api.delivery.api.model.PedidoModel;
import com.api.delivery.api.model.PedidoResumoModel;
import com.api.delivery.api.model.input.PedidoInput;
import com.api.delivery.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation("Pesquisa os pedidos")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
					name = "campos", paramType = "query", type = "string")
	})
	
	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	@ApiOperation("Registra um pedido")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Pedido registrado"),
	})
	
	PedidoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
					PedidoInput pedidoInput);

	@ApiOperation("Busca um pedido por código")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	
	@ApiImplicitParams({
			@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
					name = "campos", paramType = "query", type = "string")
	})
	
	PedidoModel buscar(
			@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
					required = true)
					String codigoPedido);

}