package br.com.phc.crawler.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.phc.crawler.constant.CrawlerConstants;
import br.com.phc.crawler.model.Partida;
import br.com.phc.crawler.repository.PartidaRepository;
import br.com.phc.crawler.util.PartidaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class TaskScheduling {

	@Autowired
	private PartidaRepository partidaRepository;

	@Autowired
	private PartidaUtil partidaUtil;

	@Scheduled(cron = "0/30 * 16-23 * * WED", zone = CrawlerConstants.TIME_ZONE)
	public void taskAgendamentoPartidasQuarta() {
		this.realizaTarefasAgendadasRobo("Quarta");
	}

	@Scheduled(cron = "0/30 * 16-23 * * THU", zone = CrawlerConstants.TIME_ZONE)
	public void taskAgendamentoPartidasQuinta() {
		this.realizaTarefasAgendadasRobo("Quinta");
	}

	@Scheduled(cron = "0/30 * 10-23 * * SAT", zone = CrawlerConstants.TIME_ZONE)
	public void taskAgendamentoPartidasSabado() {
		this.realizaTarefasAgendadasRobo("Sabado");
	}

	@Scheduled(cron = "0/30 * 11-23 * * SUN", zone = CrawlerConstants.TIME_ZONE)
	public void taskAgendamentoPartidasDomingo() {
		this.realizaTarefasAgendadasRobo("Domingo");
	}

	private void realizaTarefasAgendadasRobo(String task) {
		log.info("Agendamento: {}", task);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime init = now.minusHours(2);
		LocalDateTime end = now.plusHours(2);

		boolean existsPartidas = partidaRepository.existsByDataHoraPartidaBetween(init, end);
		log.info("Existe partidas proximas? {}", existsPartidas);
		if (existsPartidas) {
			List<Partida> partidas = partidaRepository.findAllByDataHoraPartidaBetween(init, end);
			for (Partida par : partidas) {
				partidaUtil.buscaResultadoPartidaGoogle(par);
			}
		}
	}
}
