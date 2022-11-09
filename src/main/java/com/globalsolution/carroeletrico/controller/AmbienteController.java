package com.globalsolution.carroeletrico.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.globalsolution.carroeletrico.model.Ambiente;
import com.globalsolution.carroeletrico.model.Veiculo;
import com.globalsolution.carroeletrico.model.dto.AmbienteDto;
import com.globalsolution.carroeletrico.model.dto.VeiculoDto;
import com.globalsolution.carroeletrico.repository.AmbienteRepository;

@RestController
@RequestMapping("/ambientes")
public class AmbienteController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@GetMapping
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<List<Ambiente>> listaAmbiente() {
		List<Ambiente> ambientes = ambienteRepository.findAll();
		
		return new ResponseEntity<List<Ambiente>>(ambientes, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<?> cadastraAmbiente(@RequestBody AmbienteDto ambienteDto) {
		Ambiente ambiente = modelMapper.map(ambienteDto, Ambiente.class);

		ambienteRepository.save(ambiente);

		return new ResponseEntity<Ambiente>(ambiente, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<?> deletaAmbiente(@PathVariable(value = "id") Long id) {
		try {
			ambienteRepository.findById(id).get();

			ambienteRepository.deleteById(id);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Ambiente deletado com sucesso =)");
		}catch(NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Ambiente não existente :(");
		}
	}
}
