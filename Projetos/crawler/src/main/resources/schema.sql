DROP TABLE IF EXISTS tb_partida;
DROP TABLE IF EXISTS tb_equipe;
DROP TABLE IF EXISTS tb_liga;

/*tabela de liga*/
CREATE TABLE tb_liga(
id_liga			INT AUTO_INCREMENT PRIMARY KEY,
nome_liga		VARCHAR(30) NOT NULL,
pais_liga		VARCHAR(30) NOT NULL,
descricao		VARCHAR(50),
temporada		VARCHAR(10) NOT NULL,
ativa			BOOL
);

/*tabela de equipe*/
CREATE TABLE tb_equipe(
id_equipe			INT AUTO_INCREMENT  PRIMARY KEY,
nome				VARCHAR(30) NOT NULL,
sigla_equipe		VARCHAR(3) NOT NULL UNIQUE,
url_imagem_equipe	VARCHAR(255) NOT NULL
);

/*tabela de partida*/
CREATE TABLE tb_partida(
id_partida							INT AUTO_INCREMENT  PRIMARY KEY,
id_liga								INT NOT NULL,
id_equipe_casa						INT NOT NULL,
id_equipe_visitante					INT NOT NULL,
placar_equipe_casa					INT,
placar_equipe_visitante				INT,
gols_equipe_casa					VARCHAR(255),
gols_equipe_visitante				VARCHAR(255),
local_partida						VARCHAR(30),
data_hora_partida					TIMESTAMP,
placar_extendido_equipe_casa		INT,
placar_extendido_equipe_visitante	INT,
tempo_partida 						VARCHAR(50),
FOREIGN KEY (id_liga) REFERENCES tb_liga(id_liga),
FOREIGN KEY (id_equipe_casa) REFERENCES tb_equipe(id_equipe),
FOREIGN KEY (id_equipe_visitante) REFERENCES tb_equipe(id_equipe)
);