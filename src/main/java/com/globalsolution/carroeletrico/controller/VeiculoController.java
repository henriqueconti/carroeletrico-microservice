package com.globalsolution.carroeletrico.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalsolution.carroeletrico.model.Veiculo;
import com.globalsolution.carroeletrico.model.dto.VeiculoDto;
import com.globalsolution.carroeletrico.repository.AmbienteRepository;
import com.globalsolution.carroeletrico.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@PostMapping
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<?> cadastraVeiculo(@RequestBody VeiculoDto veiculoDto) {
		if(veiculoService.validaVeiculo(veiculoDto)) {
			Veiculo veiculo = modelMapper.map(veiculoDto, Veiculo.class);
	
			veiculo.setAmbiente(ambienteRepository.findById(veiculoDto.getAmbiente()).get());

			veiculoService.cadastraVeiculo(veiculo);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body("Veículo cadastrado com sucesso =)");
		}else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Dado(s) inválido, verifique as regras de negócio :(");
		}
	}
	
	@GetMapping
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<List<Veiculo>> listaVeiculo() {
		List<Veiculo> veiculos = veiculoService.getVeiculos();
		
		return new ResponseEntity<List<Veiculo>>(veiculos, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<?> deletaVeiculo(@PathVariable(value = "id") Long id) {
		if(veiculoService.validaVeiculoExistente(id)){

			veiculoService.deletaVeiculo(id);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Veículo deletado com sucesso =)");
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Veículo não existente :(");	
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<?> atualizaVeiculo(@PathVariable(value = "id") Long id, @RequestBody VeiculoDto veiculoDto){
		if(veiculoService.validaVeiculoExistente(id)) {
			if(veiculoService.validaVeiculo(veiculoDto)) {
				Veiculo veiculo = modelMapper.map(veiculoDto, Veiculo.class);
				
				veiculoService.atualizaVeiculo(veiculo, id);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("Veículo atualizado com sucesso =)");	
			}
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Dado(s) inválido, verifique as regras de negócio :(");	
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Veículo não existente :(");	
	}
}
