package com.globalsolution.carroeletrico.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_ambiente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ambiente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ambiente")
	private Long id;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private int temperatura;
	
	private String qualidadeDoAr;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ambiente")
    private List<Veiculo> veiculos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public String getQualidadeDoAr() {
		return qualidadeDoAr;
	}

	public void setQualidadeDoAr(String qualidadeDoAr) {
		this.qualidadeDoAr = qualidadeDoAr;
	}
}
