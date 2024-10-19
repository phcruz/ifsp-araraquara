package br.com.phc.brasileirao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.brasileirao.model.Liga;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Long> {

	public boolean existsByIdLiga(Long idLiga);

	public Optional<Liga> findByIdLiga(Long idLiga);

	public List<Liga> findByAtivaTrue();

	public Optional<Liga> findByNomeLiga(String nomeLiga);

}
