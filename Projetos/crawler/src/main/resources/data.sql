INSERT INTO tb_liga (nome_liga, pais_liga, descricao, temporada, ativa)
VALUES ('Copa do Mundo', 'Qatar', 'Copa do Mundo FIFA', '2022', true);
INSERT INTO tb_liga (nome_liga, pais_liga, descricao, temporada, ativa)
VALUES ('Brasileirão', 'Brasil', 'Campeonato Brasileiro', '2024', true);
INSERT INTO tb_liga (nome_liga, pais_liga, descricao, temporada, ativa)
VALUES ('Premier League', 'Inglaterra', 'Campeonato Inglês', '2024/2025', true);
INSERT INTO tb_liga (nome_liga, pais_liga, descricao, temporada, ativa)
VALUES ('Serie A', 'Itália', 'Campeonato Italino', '2024/2025', true);
INSERT INTO tb_liga (nome_liga, pais_liga, descricao, temporada, ativa)
VALUES ('Bundesliga', 'Alemanha', 'Campeonato Alemão', '2024/2025', true);


INSERT INTO tb_equipe (nome, sigla_equipe, url_imagem_equipe) VALUES
('Argentina', 'ARG', 'https://ssl.gstatic.com/onebox/media/sports/logos/VHdNOT6wWOw_vJ38GMjMzg_96x96.png'),
('França', 'FRA', 'https://ssl.gstatic.com/onebox/media/sports/logos/VHdNOT6wWOw_vJ38GMjMzg_96x96.png'),
('Botafogo', 'BOT', 'https://ssl.gstatic.com/onebox/media/sports/logos/KLDWYp-H8CAOT9H_JgizRg_48x48.png'),
('Criciuma', 'CRI', 'https://ssl.gstatic.com/onebox/media/sports/logos/u_L7Mkp33uNmFTv3uUlXeQ_48x48.png'),
('Internacional', 'INT', 'https://ssl.gstatic.com/onebox/media/sports/logos/OWVFKuHrQuf4q2Wk0hEmSA_48x48.png'),
('Grêmio', 'GRE', 'https://ssl.gstatic.com/onebox/media/sports/logos/Ku-73v_TW9kpex-IEGb0ZA_48x48.png'),
('Tottenham', 'TOT', 'https://ssl.gstatic.com/onebox/media/sports/logos/7spurne-xDt2p6C0imYYNA_96x96.png'),
('West Ham', 'WES', 'https://ssl.gstatic.com/onebox/media/sports/logos/VHdNOT6wWOw_vJ38GMjMzg_96x96.png'),
('Manchester United', 'MAN', 'https://ssl.gstatic.com/onebox/media/sports/logos/7spurne-xDt2p6C0imYYNA_96x96.png'),
('Brentford', 'BRE', 'https://ssl.gstatic.com/onebox/media/sports/logos/VHdNOT6wWOw_vJ38GMjMzg_96x96.png'),
('Genoa', 'GEN', 'https://ssl.gstatic.com/onebox/media/sports/logos/Ku-73v_TW9kpex-IEGb0ZA_48x48.png'),
('Bologna', 'BOL', 'https://ssl.gstatic.com/onebox/media/sports/logos/7spurne-xDt2p6C0imYYNA_96x96.png'),
('Hoffenheim', 'HOF', 'https://ssl.gstatic.com/onebox/media/sports/logos/Ku-73v_TW9kpex-IEGb0ZA_48x48.png'),
('Bochum', 'BOC', 'https://ssl.gstatic.com/onebox/media/sports/logos/7spurne-xDt2p6C0imYYNA_96x96.png');

/*partida com penalidades*/
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (1, 1, 2, 0, 0, '2024-10-13 17:00', 'Lusail');
/*partida encerrada*/
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (2, 3, 4, 0, 0, '2024-10-18 20:00', 'Maracanã');
/*partida nao iniciada*/
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (2, 5, 6, 0, 0, '2024-10-19 16:00', 'Beira Rio');
/*partida em andamento*/
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (3, 7, 8, 0, 0, '2024-10-19 08:30', 'Hotspur Stadium');
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (3, 9, 10, 0, 0, '2024-10-19 11:00', 'Old Trafford');
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (4, 11, 12, 0, 0, '2024-10-19 10:00', 'Luigi Ferraris');
INSERT INTO tb_partida (id_liga, id_equipe_casa, id_equipe_visitante, placar_equipe_casa, placar_equipe_visitante, data_hora_partida, local_partida)
VALUES (5, 13, 14, 0, 0, '2024-10-19 10:30', 'BayArena');

