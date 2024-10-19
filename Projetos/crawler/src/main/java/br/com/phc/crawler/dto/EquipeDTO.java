package br.com.phc.crawler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EquipeDTO {

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("siglaEquipe")
	private String siglaEquipe;

	@JsonProperty("urlImagemEquipe")
	private String urlImagemEquipe;
}
