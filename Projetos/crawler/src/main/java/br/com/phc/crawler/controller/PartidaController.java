package br.com.phc.crawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.crawler.dto.PartidaDTO;
import br.com.phc.crawler.service.PartidaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/partida")
public class PartidaController {

	@Autowired
	private PartidaService partidaService;

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PartidaDTO>> findPartidas() {
		return ResponseEntity.ok().body(partidaService.findAll());
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(path = "/{idPartida}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PartidaDTO> findPartidaById(@PathVariable("idPartida") Long idPartida) throws Exception {
		return ResponseEntity.ok().body(partidaService.findById(idPartida));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(path = "/{idLiga}/liga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PartidaDTO>> findPartidasByIdLiga(@PathVariable("idLiga") Long idLiga) {
		return ResponseEntity.ok().body(partidaService.findAllByLiga(idLiga));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> postPartida(@RequestBody PartidaDTO partidaDTO) throws Exception {
		return ResponseEntity.ok().body(partidaService.createPartida(partidaDTO));
	}
}
