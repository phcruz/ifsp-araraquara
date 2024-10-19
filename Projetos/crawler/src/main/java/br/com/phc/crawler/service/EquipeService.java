package br.com.phc.crawler.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.phc.crawler.dto.EquipeDTO;
import br.com.phc.crawler.exception.CustomException;
import br.com.phc.crawler.model.Equipe;
import br.com.phc.crawler.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository equipeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public EquipeDTO findById(Long idEquipe) throws CustomException {
		Equipe equipe = equipeRepository.findById(idEquipe).orElseThrow(() -> new CustomException("Equipe nao encontrada", HttpStatus.NOT_FOUND));
		return modelMapper.map(equipe, EquipeDTO.class);
	}

	public List<EquipeDTO> findAll() {
		List<Equipe> equipes = equipeRepository.findAll();
		return modelMapper.map(equipes, new TypeToken<List<EquipeDTO>>() {}.getType());
	}

	public Boolean createEquipe(EquipeDTO equipeDTO) throws CustomException {
		Optional<Equipe> opEquipe = equipeRepository.findBySiglaEquipe(equipeDTO.getSiglaEquipe());
		if (opEquipe.isPresent()) {
			throw new CustomException("Equipe ja cadastrada", HttpStatus.BAD_REQUEST);
		}
		Equipe equipe = modelMapper.map(equipeDTO, Equipe.class);
		equipeRepository.save(equipe);
		return Boolean.TRUE;
	}

	public Boolean updateEquipe(EquipeDTO equipeDTO) throws CustomException {
		Equipe equipe = equipeRepository.findBySiglaEquipe(equipeDTO.getSiglaEquipe()).orElseThrow(() -> new CustomException("Equipe nao encontrada", HttpStatus.NOT_FOUND));
		equipe.setNome(equipeDTO.getNome());
		equipe.setUrlImagemEquipe(equipeDTO.getUrlImagemEquipe());
		equipeRepository.save(equipe);
		return Boolean.TRUE;
	}

	public Equipe findBySiglaEquipe(String siglaEquipe) throws CustomException {
		return equipeRepository.findBySiglaEquipe(siglaEquipe).orElseThrow(() -> new CustomException("Equipe nao encontrada", HttpStatus.NOT_FOUND));
	}
}
