package com.api.delivery.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.api.delivery.api.controller.PedidoController;
import com.api.delivery.api.controller.RestauranteController;
import com.api.delivery.api.controller.UsuarioController;
import com.api.delivery.api.model.PedidoResumoModel;
import com.api.delivery.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler 
		extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}
	
	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		
		pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
				.buscar(pedido.getRestaurante().getId())).withSelfRel());
		
		pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
				.buscar(pedido.getCliente().getId())).withSelfRel());
		
		return pedidoModel;
	}

}