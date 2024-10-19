package br.com.phc.brasileirao.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.brasileirao.model.Liga;
import br.com.phc.brasileirao.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

	public boolean existsByDataHoraPartidaBetween(LocalDateTime init, LocalDateTime end);

	public List<Partida> findAllByDataHoraPartidaBetween(LocalDateTime init, LocalDateTime end);

	public List<Partida> findAllByLiga(Liga liga);

	public List<Partida> findAllByLigaIdLiga(Long idLiga);

}
