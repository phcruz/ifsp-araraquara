package br.com.phc.crawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.crawler.dto.EquipeDTO;
import br.com.phc.crawler.service.EquipeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

	@Autowired
	private EquipeService equipeService;

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EquipeDTO>> findEquipes() {
		return ResponseEntity.ok().body(equipeService.findAll());
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(path = "/{idEquipe}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EquipeDTO> findEquipeById(@PathVariable("idEquipe") Long idEquipe) throws Exception {
		return ResponseEntity.ok().body(equipeService.findById(idEquipe));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> postEquipe(@RequestBody EquipeDTO equipeDTO) throws Exception {
		return ResponseEntity.ok().body(equipeService.createEquipe(equipeDTO));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> putEquipe(@RequestBody EquipeDTO equipeDTO) throws Exception {
		return ResponseEntity.ok().body(equipeService.updateEquipe(equipeDTO));
	}
}
