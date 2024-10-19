package br.com.phc.crawler.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.phc.crawler.constant.CrawlerConstants;
import lombok.Data;

@Data
public class PartidaDTO {

	@JsonProperty("liga")
	private LigaDTO liga;

	@JsonProperty("mandante")
	private EquipeDTO mandante;

	@JsonProperty("visitante")
	private EquipeDTO visitante;

	@JsonProperty("placarEquipeCasa")
	private Integer placarEquipeCasa;

	@JsonProperty("placarEquipeVisitante")
	private Integer placarEquipeVisitante;

	@JsonProperty("golsEquipeCasa")
	private String golsEquipeCasa;

	@JsonProperty("golsEquipeVisitante")
	private String golsEquipeVisitante;

	@JsonProperty("localPartida")
	private String localPartida;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CrawlerConstants.PATTERN_DATE_PTBR, locale = "pt-BR", timezone = CrawlerConstants.TIME_ZONE)
	@JsonProperty("dataHoraPartida")
	private Date dataHoraPartida;

	@JsonProperty("placarExtendidoEquipeCasa")
	private String placarExtendidoEquipeCasa;

	@JsonProperty("placarExtendidoEquipeVisitante")
	private String placarExtendidoEquipeVisitante;

	@JsonProperty("tempoPartida")
	private String tempoPartida;
}
