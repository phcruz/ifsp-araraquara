package br.com.phc.crawler.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_equipe")
public class Equipe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipe")
	private Long idEquipe;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla_equipe")
	private String siglaEquipe;

	@Column(name = "url_imagem_equipe")
	private String urlImagemEquipe;

}
