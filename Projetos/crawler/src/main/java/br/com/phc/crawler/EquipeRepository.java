package br.com.phc.brasileirao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.brasileirao.model.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

	public Optional<Equipe> findBySiglaEquipe(String siglaEquipe);

	public Optional<Equipe> findByNome(String nome);

	public boolean existsByNome(String nome);

}
