package br.com.phc.crawler.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.phc.crawler.dto.PartidaDTO;
import br.com.phc.crawler.exception.CustomException;
import br.com.phc.crawler.model.Equipe;
import br.com.phc.crawler.model.Liga;
import br.com.phc.crawler.model.Partida;
import br.com.phc.crawler.repository.PartidaRepository;

@Service
public class PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LigaService ligaService;
	
	@Autowired
	private EquipeService equipeService;

	public PartidaDTO findById(Long idPartida) throws CustomException {
		Partida partida = partidaRepository.findById(idPartida).orElseThrow(() -> new CustomException("Partida nao encontrada", HttpStatus.NOT_FOUND));
		return modelMapper.map(partida, PartidaDTO.class);
	}

	public List<PartidaDTO> findAll() {
		List<Partida> partidas = partidaRepository.findAll();
		return modelMapper.map(partidas, new TypeToken<List<PartidaDTO>>() {}.getType());
	}

	public List<PartidaDTO> findAllByLiga(Long idLiga) {
		List<Partida> partidas =  partidaRepository.findAllByLigaIdLiga(idLiga);
		return modelMapper.map(partidas, new TypeToken<List<PartidaDTO>>() {}.getType());
	}

	public Boolean createPartida(PartidaDTO partidaDTO) throws CustomException {
		Liga liga = ligaService.findByNomeLiga(partidaDTO.getLiga().getNomeLiga());
		Equipe mandante = equipeService.findBySiglaEquipe(partidaDTO.getLiga().getNomeLiga());
		Equipe visitante = equipeService.findBySiglaEquipe(partidaDTO.getLiga().getNomeLiga());
		
		Partida partida = modelMapper.map(partidaDTO, Partida.class);
		partida.setLiga(liga);
		partida.setMandante(mandante);
		partida.setVisitante(visitante);

		partidaRepository.save(partida);
		return Boolean.TRUE;
	}
}
