package com.api.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.delivery.api.assembler.GrupoInputDisassembler;
import com.api.delivery.api.assembler.GrupoModelAssembler;
import com.api.delivery.api.model.GrupoModel;
import com.api.delivery.api.model.input.GrupoInput;
import com.api.delivery.api.openapi.controller.GrupoControllerOpenApi;
import com.api.delivery.domain.model.Grupo;
import com.api.delivery.domain.repository.GrupoRepository;
import com.api.delivery.domain.service.CadastroGrupoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@Override
	@GetMapping
	public List<GrupoModel> listar() {
		List<Grupo> todosGrupos = grupoRepository.findAll();
		
		return grupoModelAssembler.toCollectionModel(todosGrupos);
	}
	
	@Override
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		return grupoModelAssembler.toModel(grupo);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		
		grupo = cadastroGrupo.salvar(grupo);
		
		return grupoModelAssembler.toModel(grupo);
	}
	
	@Override
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId,
			@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
		
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		grupoAtual = cadastroGrupo.salvar(grupoAtual);
		
		return grupoModelAssembler.toModel(grupoAtual);
	}
	
	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.excluir(grupoId);	
	}
	
}