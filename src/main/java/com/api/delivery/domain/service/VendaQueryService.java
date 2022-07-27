package com.api.delivery.domain.service;

import java.util.List;

import com.api.delivery.domain.filter.VendaDiariaFilter;
import com.api.delivery.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
