package com.api.delivery.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.api.delivery.api.exceptionhandler.Problem;
import com.api.delivery.api.model.EstadoModel;
import com.api.delivery.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Lista os estados")
	CollectionModel<EstadoModel> listar();
	
	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoModel buscar(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
					Long estadoId);

	@ApiOperation("Cadastra um estado")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Estado cadastrado"),
	})
	EstadoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true)
					EstadoInput estadoInput);

	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Estado atualizado"),
			@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoModel atualizar(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
					Long estadoId,

			@ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true)
					EstadoInput estadoInput);

	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Estado excluído"),
			@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
					Long estadoId);

}
