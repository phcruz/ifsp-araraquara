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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_liga")
public class Liga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_liga")
	private Long idLiga;

	@Column(name = "nome_liga")
	private String nomeLiga;

	@Column(name = "pais_liga")
	private String paisLiga;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "temporada")
	private String temporada;

	@Column(name = "ativa")
	private boolean ativa;

}
