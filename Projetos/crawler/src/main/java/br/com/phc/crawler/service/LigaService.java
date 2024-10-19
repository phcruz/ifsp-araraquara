package br.com.phc.crawler.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.phc.crawler.dto.LigaDTO;
import br.com.phc.crawler.exception.CustomException;
import br.com.phc.crawler.model.Liga;
import br.com.phc.crawler.repository.LigaRepository;

@Service
public class LigaService {

	@Autowired
	private LigaRepository ligaRepository;

	@Autowired
	private ModelMapper modelMapper;

	public LigaDTO findById(Long idLiga) throws CustomException {
		Liga liga = ligaRepository.findById(idLiga).orElseThrow(() -> new CustomException("Liga nao encontrada", HttpStatus.NOT_FOUND));
		return modelMapper.map(liga, LigaDTO.class);
	}

	public List<LigaDTO> findAll() {
		List<Liga> ligas = ligaRepository.findAll();
		return modelMapper.map(ligas, new TypeToken<List<LigaDTO>>() {}.getType());
	}

	public Boolean createLiga(LigaDTO ligaDTO) throws CustomException {
		Optional<Liga> opLiga = ligaRepository.findByNomeLiga(ligaDTO.getNomeLiga());
		if (opLiga.isPresent()) {
			throw new CustomException("Liga ja cadastrada", HttpStatus.BAD_REQUEST);
		}
		Liga liga = modelMapper.map(ligaDTO, Liga.class);
		ligaRepository.save(liga);
		return Boolean.TRUE;
	}

	public Boolean updateLiga(LigaDTO ligaDTO) throws CustomException {
		Liga liga = ligaRepository.findByNomeLiga(ligaDTO.getNomeLiga()).orElseThrow(() -> new CustomException("Liga nao encontrada", HttpStatus.NOT_FOUND));
		liga.setPaisLiga(ligaDTO.getPaisLiga());
		liga.setDescricao(ligaDTO.getDescricao());
		liga.setTemporada(ligaDTO.getTemporada());
		liga.setAtiva(ligaDTO.isAtiva());
		ligaRepository.save(liga);
		return Boolean.TRUE;
	}

	public Liga findByNomeLiga(String nomeLiga) throws CustomException {
		return ligaRepository.findByNomeLiga(nomeLiga).orElseThrow(() -> new CustomException("Liga nao encontrada", HttpStatus.NOT_FOUND));
	}
}
