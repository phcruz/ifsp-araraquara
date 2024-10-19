package br.com.phc.crawler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LigaDTO {

	@JsonProperty("nomeLiga")
	private String nomeLiga;

	@JsonProperty("paisLiga")
	private String paisLiga;

	@JsonProperty("descricao")
	private String descricao;

	@JsonProperty("temporada")
	private String temporada;

	@JsonProperty("ativa")
	private boolean ativa;
}
