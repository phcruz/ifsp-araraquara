package br.com.phc.crawler.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.phc.crawler.dto.EquipeDTO;
import br.com.phc.crawler.dto.LigaDTO;
import br.com.phc.crawler.dto.PartidaDTO;
import br.com.phc.crawler.model.Equipe;
import br.com.phc.crawler.model.Liga;
import br.com.phc.crawler.model.Partida;

@Configuration
public class BeansConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Liga, LigaDTO> typeMapLiga = modelMapper.createTypeMap(Liga.class, LigaDTO.class);
		typeMapLiga.addMappings(mapper -> {
			mapper.map(src -> src.isAtiva(), LigaDTO::setAtiva);
		});
		
		TypeMap<Equipe, EquipeDTO> typeMapEquipe = modelMapper.createTypeMap(Equipe.class, EquipeDTO.class);
		typeMapEquipe.addMappings(mapper -> {
			mapper.map(src -> src.getUrlImagemEquipe(), EquipeDTO::setUrlImagemEquipe);
		});
		
		TypeMap<Partida, PartidaDTO> typeMapPartida = modelMapper.createTypeMap(Partida.class, PartidaDTO.class);
		typeMapPartida.addMappings(mapper -> {
			mapper.map(src -> src.getLocalPartida(), PartidaDTO::setLocalPartida);
		});
		return modelMapper;
	}
}
