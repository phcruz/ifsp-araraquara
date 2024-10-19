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

import br.com.phc.crawler.dto.LigaDTO;
import br.com.phc.crawler.service.LigaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/liga")
public class LigaController {

	@Autowired
	private LigaService ligaService;

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LigaDTO>> findLigas() {
		return ResponseEntity.ok().body(ligaService.findAll());
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping(path = "/{idLiga}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LigaDTO> findLigaById(@PathVariable("idLiga") Long idLiga) throws Exception {
		return ResponseEntity.ok().body(ligaService.findById(idLiga));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> postLiga(@RequestBody LigaDTO ligaDTO) throws Exception {
		return ResponseEntity.ok().body(ligaService.createLiga(ligaDTO));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> putLiga(@RequestBody LigaDTO ligaDTO) throws Exception {
		return ResponseEntity.ok().body(ligaService.updateLiga(ligaDTO));
	}
}
