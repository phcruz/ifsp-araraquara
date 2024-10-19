package br.com.phc.crawler.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.crawler.model.Liga;
import br.com.phc.crawler.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

	public boolean existsByDataHoraPartidaBetween(LocalDateTime init, LocalDateTime end);

	public List<Partida> findAllByDataHoraPartidaBetween(LocalDateTime init, LocalDateTime end);

	public List<Partida> findAllByLiga(Liga liga);

	public List<Partida> findAllByLigaIdLiga(Long idLiga);

}
