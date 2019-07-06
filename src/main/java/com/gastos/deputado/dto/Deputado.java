package com.gastos.deputado.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Entity
public class Deputado{
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String partido;
	private String uf;
	private String cotaParlamentar;
	private String auxilioMoradia;
	private String verbaGabinete;
	private boolean	renunciouAuxilioMoradia;
	
	public void setNome(String nome) {
		this.nome = nome.split("\\(")[0].trim();
		partido = nome.split("\\(")[1].split("-")[0];
		uf = nome.split("\\(")[1].split("-")[1].replace(")", "");
	}
}
