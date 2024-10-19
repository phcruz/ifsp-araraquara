package br.com.phc.crawler.util;

import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.phc.crawler.constant.CrawlerConstants;
import br.com.phc.crawler.indicator.StatusPartidaIndicator;
import br.com.phc.crawler.model.Equipe;
import br.com.phc.crawler.model.Partida;
import br.com.phc.crawler.repository.PartidaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PartidaUtil {

	@Autowired
	private PartidaRepository partidaRepository;

	public void buscaResultadoPartidaGoogle(Partida partida) {
		Document doc;
		String url = "";
		try {
			url = this.montaUrlPartida(partida.getMandante(), partida.getVisitante());
			doc = Jsoup.connect(url).get();

			String title = doc.title();
			log.info("Titulo da pagina: [{}] - url[{}]", title, url);

			String msgValidaDia = null;
			try {
				msgValidaDia = doc.selectFirst(CrawlerConstants.MENSAGEM_PADRAO).text();
				if (Objects.nonNull(msgValidaDia) && !(msgValidaDia.toLowerCase().contains("hoje"))) {
					log.info(String.format("O jogo encontrado no Google nao é de hoje: %s", msgValidaDia));
					return;
				}
			} catch (Exception e) {}

			String mensagemTempoJogo = "Partida não iniciada.";
			StatusPartidaIndicator statusPartida = StatusPartidaIndicator.NAO_INICIADA;
			try {
				Elements elementsProgress = doc.select(CrawlerConstants.ANDAMENTO_PARTIDA);
				Elements elementsFinish = doc.select(CrawlerConstants.ENCERRAMENTO_PARTIDA);
				if (!elementsProgress.isEmpty()) {// bola rolando ou intervalo
					mensagemTempoJogo = elementsProgress.first().text();
					statusPartida = StatusPartidaIndicator.EM_ANDAMENTO;
					if (mensagemTempoJogo.contains(CrawlerConstants.PENALTIS)) {
						statusPartida = StatusPartidaIndicator.PENALTIS;
					}
				} else if (!elementsFinish.isEmpty()) {// encerrado
					mensagemTempoJogo = elementsFinish.first().text();
					statusPartida = StatusPartidaIndicator.ENCERRADA;				
				}
			} catch (Exception e) {}

			mensagemTempoJogo = this.corrigeTempoPartida(mensagemTempoJogo);

			switch (statusPartida) {
			case NAO_INICIADA:
				log.info("O jogo ainda não se iniciou");
				break;
			case EM_ANDAMENTO:
				log.info(String.format("O jogo esta com: %s", mensagemTempoJogo));

				this.atualizaTempoPlacar(doc, partida, mensagemTempoJogo);
				this.atualizaGols(doc, partida);

				break;
			case PENALTIS:
				log.info(String.format("O jogo está nas penalidades: %s", mensagemTempoJogo));

				this.buscaPenalidades(doc, partida);

				break;
			case ENCERRADA:
				log.info(String.format("O jogo encontra-se: %s", mensagemTempoJogo));
				if (!CrawlerConstants.ENCERRADO.equalsIgnoreCase(partida.getTempoPartida())) {
					this.atualizaGols(doc, partida);
					this.buscaPenalidades(doc, partida);
					this.atualizaPartidaEncerrada(doc, partida, mensagemTempoJogo);
				}

				break;
			default:
				log.info(mensagemTempoJogo);
			}
			partidaRepository.save(partida);
		} catch (Exception e) {
			log.error(url);
			log.error(e.getMessage());
		}
	}

	private String montaUrlPartida(Equipe mandante, Equipe visitante) {
		try {
			return CrawlerConstants.BASE_URL_GOOGLE
					.replace("{mandante}", mandante.getNome().replace("-", "+"))
					.replace("{visitante}", visitante.getNome().replace("-", "+"));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return CrawlerConstants.BASE_URL_GOOGLE;
	}

	private String corrigeTempoPartida(String tempo) {
		String mensagemTempoJogo = tempo;
		if (tempo.contains("'")) {
			mensagemTempoJogo = tempo.replace(" ", "").replace("'", "").concat(" min");
		} else if (tempo.contains("+")) {
			mensagemTempoJogo = tempo.replace(" ", "").concat(" min");
		}
		return mensagemTempoJogo;
	}

	private Integer formataStringInteger(String numero) {
		try {
			return Integer.parseInt(numero);
		} catch (Exception e) {
			return 0;
		}
	}

	private void atualizaPartidaEncerrada(Document doc, Partida partida, String mensagemTempoJogo) {
		String placarMandante = doc.selectFirst(CrawlerConstants.PLACAR_MANDANTE).text();
		String placarVisitante = doc.selectFirst(CrawlerConstants.PLACAR_VISITANTE).text();

		Integer placarTimeMandante = this.formataStringInteger(placarMandante);
		Integer placarTimeVisitante = this.formataStringInteger(placarVisitante);
		partida.setPlacarEquipeCasa(placarTimeMandante);
		partida.setPlacarEquipeVisitante(placarTimeVisitante);
		partida.setTempoPartida(mensagemTempoJogo);
	}

	private void atualizaTempoPlacar(Document doc, Partida partida, String mensagemTempoJogo) {
		String placarMandante = doc.selectFirst(CrawlerConstants.PLACAR_MANDANTE).text();
		String placarVisitante = doc.selectFirst(CrawlerConstants.PLACAR_VISITANTE).text();
		log.info(String.format("%s %s x %s %s", partida.getMandante().getNome(), placarMandante, placarVisitante, partida.getVisitante().getNome()));

		// primeira inserção
		if (partida.getTempoPartida() == null) {
			partida.setPlacarEquipeCasa(0);
			partida.setPlacarEquipeVisitante(0);
			partida.setTempoPartida(mensagemTempoJogo);
		}
		// verifica se houve alteracao do tempo pra nao ficar salvando o mesmo valor
		if (partida.getTempoPartida() != null && !partida.getTempoPartida().equalsIgnoreCase(mensagemTempoJogo)) {
			partida.setTempoPartida(mensagemTempoJogo);
		}
		Integer placarTimeMandante = this.formataStringInteger(placarMandante);
		Integer placarTimeVisitante = this.formataStringInteger(placarVisitante);
		if (partida.getPlacarEquipeCasa() != placarTimeMandante || partida.getPlacarEquipeVisitante() != placarTimeVisitante) {
			partida.setPlacarEquipeCasa(placarTimeMandante);
			partida.setPlacarEquipeVisitante(placarTimeVisitante);
		}
	}

	private void atualizaGols(Document doc, Partida partida) {
		StringBuilder golsMandante = new StringBuilder();
		StringBuilder golsVisitante = new StringBuilder();
		try {
			// busca gols da equipe da casa
			Elements elementsMandante = doc.select(CrawlerConstants.MARCADORES_MANDANTE).select(CrawlerConstants.MARCADOR_INFO);
			Integer quantidadeTotal = elementsMandante.size();
			Integer contador = 0;
			for (Element e : elementsMandante) {
				contador++;
				String valor = e.select(CrawlerConstants.MARCADOR_INFO).text();
				if (quantidadeTotal == contador) {
					golsMandante.append(valor);
				} else {
					golsMandante.append(valor + "\n");
				}
				log.info(valor);
			}

			// busca gols da equipe visitante
			Elements elementsVisitante = doc.select(CrawlerConstants.MARCADORES_VISITANTE).select(CrawlerConstants.MARCADOR_INFO);
			quantidadeTotal = elementsVisitante.size();
			contador = 0;
			for (Element e : elementsVisitante) {
				contador++;
				String valor = e.select(CrawlerConstants.MARCADOR_INFO).text();
				if (quantidadeTotal == contador) {
					golsVisitante.append(valor);
				} else {
					golsVisitante.append(valor + "\n");
				}
				log.info(valor);
			}

			// verifica se existe gols na partida
			if (elementsMandante.isEmpty() && elementsVisitante.isEmpty()) {
				log.info("Não há gols na partida.");
				return;
			}

			if (!elementsMandante.isEmpty()) {
				partida.setGolsEquipeCasa(golsMandante.toString());
			}
			if (!elementsVisitante.isEmpty()) {
				partida.setGolsEquipeVisitante(golsVisitante.toString());
			}
		} catch (Exception e) {
			log.error("Erro ao buscar gols na partida");
			log.error(e.getMessage());
		}
	}

	private void buscaPenalidades(Document doc, Partida partida) {
		try {
			String penalidades = doc.select(CrawlerConstants.PENALTI_INFO).text();
			if (penalidades.length() == 0) {
				log.info("Partida sem penalidades.");
				return;
			}
			String completo = penalidades.substring(0, 5).replace(" ", "");
			String[] divisao = completo.split("-");

			String equipeCasa = divisao[0];
			String equipeVisitante = divisao[1];

			log.info(String.format("(%s)penaltis(%s)", equipeCasa, equipeVisitante));

			partida.setPlacarExtendidoEquipeCasa(equipeCasa);
			partida.setPlacarExtendidoEquipeVisitante(equipeVisitante);
		} catch (Exception e) {
			log.error("Erro ao buscar penalidades.");
		}
	}
}
