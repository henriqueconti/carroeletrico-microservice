package com.globalsolution.carroeletrico.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalsolution.carroeletrico.model.Veiculo;
import com.globalsolution.carroeletrico.model.dto.VeiculoDto;
import com.globalsolution.carroeletrico.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public List<Veiculo> getVeiculos() {
		return veiculoRepository.findAll();
	}

	public boolean validaVeiculoExistente(Long id) {
		try {
			veiculoRepository.findById(id).get();
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}

	public void deletaVeiculo(Long id) {
		veiculoRepository.deleteById(id);
	}

	public boolean validaVeiculo(VeiculoDto veiculoDto) {
		if(validaMarca(veiculoDto.getMarca()) && validaModelo(veiculoDto.getModelo()) && validaPlaca(veiculoDto.getPlaca())) {
			return true;
		}
		return false;
	}
	
	public boolean validaMarca(String marca) {
		if(marca.length() > 30 || marca.length() < 5 ) {
			return false;
		}
		return true;
	}
	
	public boolean validaModelo(String modelo) {
		if(modelo.length() > 20 || modelo.length() < 2 ) {
			return false;
		}
		return true;
	}
	
	public boolean validaPlaca(String placa) {
		if(placa.length() > 8 || placa.length() < 6 ) {
			return false;
		}
		return true;
	}

	public void cadastraVeiculo(Veiculo veiculo) {
		veiculoRepository.save(veiculo);
	}

	public void atualizaVeiculo(Veiculo veiculo, Long id) {
		veiculoRepository.deleteById(id);
		veiculoRepository.save(veiculo);		
	}
}
