package br.com.phc.brasileirao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_partida")
public class Partida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partida")
	private Long idPartida;

	@ManyToOne
	@JoinColumn(name = "id_liga")
	private Liga liga;

	@ManyToOne
	@JoinColumn(name = "id_equipe_casa")
	private Equipe mandante;

	@ManyToOne
	@JoinColumn(name = "id_equipe_visitante")
	private Equipe visitante;

	@Column(name = "placar_equipe_casa")
	private Integer placarEquipeCasa;

	@Column(name = "placar_equipe_visitante")
	private Integer placarEquipeVisitante;

	@Column(name = "gols_equipe_casa")
	private String golsEquipeCasa;

	@Column(name = "gols_equipe_visitante")
	private String golsEquipeVisitante;

	@Column(name = "local_partida")
	private String localPartida;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_partida")
	private LocalDateTime dataHoraPartida;

	@Column(name = "placar_extendido_equipe_casa")
	private String placarExtendidoEquipeCasa;

	@Column(name = "placar_extendido_equipe_visitante")
	private String placarExtendidoEquipeVisitante;

	@Column(name = "tempo_partida")
	private String tempoPartida;

}
