package br.com.phc.brasileirao.constant;

public interface CrawlerConstants {

	public static final String PATTERN_DATE_PTBR = "dd/MM/yyyy HH:mm:ss";
	public static final String TIME_ZONE = "America/Sao_Paulo";

	public static final String PENALTIS = "Pênaltis";
	public static final String ENCERRADO = "Encerrado";

	public static final String MENSAGEM_PADRAO = "div[class=imso_mh__pre-m-stts imso-hide-overflow]";
	public static final String PLACAR_MANDANTE = "div[class=imso_mh__l-tm-sc imso_mh__scr-it imso-light-font]";
	public static final String PLACAR_VISITANTE = "div[class=imso_mh__r-tm-sc imso_mh__scr-it imso-light-font]";
	public static final String ANDAMENTO_PARTIDA = "div[class=imso_mh__lv-m-stts-cont]";
	public static final String ENCERRAMENTO_PARTIDA = "span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]";

	public static final String MARCADORES_MANDANTE = "div[class=imso_gs__tgs imso_gs__left-team]";
	public static final String MARCADORES_VISITANTE = "div[class=imso_gs__tgs imso_gs__right-team]";
	public static final String MARCADOR_INFO = "div[class=imso_gs__gs-r]";
	public static final String PENALTI_INFO = "div[class=imso_mh_s__psn-sc]";

	// url base de pesquisa
	public static final String BASE_URL_GOOGLE = "https://www.google.com.br/search?q={mandante}+x+{visitante}&hl=pt-BR";
}
