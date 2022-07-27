package com.api.delivery.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.api.delivery.api.ApiLinks;
import com.api.delivery.api.controller.CidadeController;
import com.api.delivery.api.controller.FormaPagamentoController;
import com.api.delivery.api.controller.PedidoController;
import com.api.delivery.api.controller.RestauranteController;
import com.api.delivery.api.controller.RestauranteProdutoController;
import com.api.delivery.api.controller.UsuarioController;
import com.api.delivery.api.model.PedidoModel;
import com.api.delivery.domain.model.Pedido;

@Component
public class PedidoModelAssembler 
		extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinks apiLinks;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}
	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		pedidoModel.add(apiLinks.linkToPedidos());
		
		pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
				.buscar(pedido.getRestaurante().getId())).withSelfRel());
		
		pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
				.buscar(pedido.getCliente().getId())).withSelfRel());
		
		pedidoModel.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
				.buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());
		
		pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
				.buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());
		
		pedidoModel.getItens().forEach(item -> {
			item.add(linkTo(methodOn(RestauranteProdutoController.class)
					.buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId()))
					.withRel("produto"));
		});
		
		return pedidoModel;
	}

}