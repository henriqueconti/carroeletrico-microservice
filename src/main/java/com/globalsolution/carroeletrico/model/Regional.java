package com.globalsolution.carroeletrico.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_regional")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Regional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_regional")
	private Long id;
	
	private String idOperacao;
	
	private LocalDate dataOperacao;

	public Regional() {

	}

	public Regional(String idOperacao, LocalDate dataOperacao) {
		this.idOperacao = idOperacao;
		this.dataOperacao = dataOperacao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}

	public LocalDate getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(LocalDate dataOperacao) {
		this.dataOperacao = dataOperacao;
	}	
}
