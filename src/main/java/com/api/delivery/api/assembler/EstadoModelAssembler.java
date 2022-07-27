package com.api.delivery.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.api.delivery.api.controller.EstadoController;
import com.api.delivery.api.model.EstadoModel;
import com.api.delivery.domain.model.Estado;

@Component
public class EstadoModelAssembler 
		extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
	}
	
	@Override
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoModel);
		
		estadoModel.add(linkTo(EstadoController.class).withRel("estados"));
		
		return estadoModel;
	}
	
	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities)
			.add(linkTo(EstadoController.class).withSelfRel());
	}
	
}
