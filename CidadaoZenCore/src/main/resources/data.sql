drop view if exists count_arvore;

CREATE VIEW COUNT_ARVORE AS (
select count(*) QTD, arv.id, p.nome PARAMETRO, e.bairro, s.nome SATISFACAO
  from avaliacao a, 
       arvore arv,
       parametro p,
       endereco e,
       satisfacao s
 where a.id_arvore = arv.id and arv.id_parametro = p.id and a.id_endereco = e.id and a.id_satisfacao = s.id
  group by arv.id, p.nome, e.bairro, s.nome );

ALTER SEQUENCE seq_arvore 			RESTART WITH 200;
ALTER SEQUENCE seq_avaliacao 		RESTART WITH 200;
ALTER SEQUENCE seq_endereco 		RESTART WITH 200;
ALTER SEQUENCE seq_log_aplicacao	RESTART WITH 200;
ALTER SEQUENCE seq_parametro		RESTART WITH 200;
ALTER SEQUENCE seq_satisfacao		RESTART WITH 200;
ALTER SEQUENCE seq_usuario			RESTART WITH 200;

insert into usuario (id, admin, ativo, data_atualizacao, data_inclusao, data_nascimento, email, nome, senha, telefone, informacoes, permanecer_logado) 
values 
(nextval('seq_usuario'), true, true, now(), now(), now(), 'marcos@marcoscarvalho.com', 'Marcos de Carvalho Oliveira', 'teste123', '(41) 9674-0964', false, false);

insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), '�gua Verde', -25.4575643,-49.2820291, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Ah�', -25.4020919,-49.2640733, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Alto Boqueir�o', -25.5419407,-49.2375229, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Alto da Gl�ria', -25.425446,-49.260153, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Alto da Rua XV', -25.4280192,-49.250645, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Atuba', -25.3850001,-49.2064468, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Augusta', -25.4676697,-49.3712299, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Bacacheri', -25.4019017,-49.2299652, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Bairro Alto', -25.4123735,-49.2093207, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Barreirinha', -25.3727515,-49.2577379, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Batel', -25.4422008,-49.287382, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Bigorrilho', -25.4317162,-49.2986936, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Boa Vista', -25.3891824,-49.236511, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Bom Retiro', -25.4095766,-49.2778547, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Boqueir�o', -25.5106831,-49.2508643, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Butiatuvinha', -25.3965747,-49.3567007, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cabral', -25.4066772,-49.2498565, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cachoeira', -25.3554491,-49.2587277, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cajuru', -25.4682885,-49.2204667, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Campina do Siqueira', -25.4387374,-49.308036, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Campo Comprido', -25.4495108,-49.3308007, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Campo de Santana', -25.6014466,-49.326739, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cap�o da Imbuia', -25.4367317,-49.217281, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cap�o Raso', -25.5058573,-49.2946528, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cascatinha', -25.412501,-49.3117765, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Caximba', -25.6208696,-49.3595687, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Centro', -25.4322442,-49.2708677, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Centro C�vico', -25.4157989,-49.2687061, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cidade Industrial', 25.457339,-49.3567912, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Cristo Rei', -25.4349546,-49.2445807, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Fanny', -25.4816282,-49.2681365, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Fazendinha', -25.4809876,-49.3243517, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Ganchinho', -25.5757001,-49.2585703, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Guabirotuba', -25.4651619,-49.242983, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Gua�ra', -25.4695488,-49.2739116, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Hauer', -25.4774412,-49.2522391, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Hugo Lange', -25.4169215,-49.2458258, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Jardim Botanico', -25.443311,-49.2469266, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Jardim Social', -25.4192087,-49.2336577, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Jardim das Americas', -25.4579708,-49.2289614, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Juveve', -25.4157877,-49.2581242, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Lamenha Pequena', -25.3629452,-49.3376124, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Lind�ia', -25.4789849,-49.2730556, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Merc�s', -25.4224597,-49.2939955, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Mossungu�', -25.4382634,-49.3273532, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Novo Mundo', -25.4878116,-49.295685, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Orleans', -25.4240949,-49.362233, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Parolin', -25.4614401,-49.2639038, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Pilarzinho', -25.3896003,-49.2881944, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Pinheirinho', -25.5238828,-49.2929361, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Port�o', -25.4720718,-49.30101, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Prado Velho', -25.4566126,-49.2528651, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Rebou�as', -25.446167,-49.2639454, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Riviera', -25.4364294,-49.381182, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Santa Candida', -25.366924,-49.229549, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Santa Felicidade', -25.4179118,-49.3368664, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Santa Quit�ria', -25.4624308,-49.3005087, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Santo Inacio', -25.426426,-49.326913, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'S�o Braz', -25.4150126,-49.3505658, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'S�o Francisco', -25.4231749,-49.2763145, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'S�o Jo�o', -25.3940206,-49.3120137, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'S�o Louren�o', -25.3901595,-49.267912, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'S�o Miguel', -25.5037337,-49.3636447, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Semin�rio', -25.448399,-49.3049924, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Sitio Cercado', -25.5440808,-49.2700445, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Tabo�o', -25.3706388,-49.2806523, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Tarum�', -25.4268885,-49.2225945, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Tatuquara', -25.5607354,-49.3188704, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Tingui', -25.3861504,-49.2207545, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Uberaba', -25.4900526,-49.215541, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Umbar�', -25.5823246,-49.2870551, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Vila Izabel', -25.456781,-49.295853, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Vista Alegre', -25.4067712,-49.2952291, 'Curitiba');
insert into endereco (id, bairro, latitude, longitude, cidade) values (nextval('seq_endereco'), 'Xaxim', -25.506844,-49.2684641, 'Curitiba');

insert into satisfacao (id, nome, cor) values (nextval('seq_satisfacao'), 'Satisfeito', '#00FF00');
insert into satisfacao (id, nome, cor) values (nextval('seq_satisfacao'), 'Neutro', '#FFCC00');
insert into satisfacao (id, nome, cor) values (nextval('seq_satisfacao'), 'Insatisfeito', '#FF0000');

insert into parametro (id, nome) values (nextval('seq_parametro'), 'Cal�adas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Cal�adas rebaixadas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Ciclismo');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Coleta de lixo');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Combate a viol�ncia');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Distribui��o de �gua');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Educa��o');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Estrutura');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Fiscaliza��o');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Fiscaliza��o ao tr�fico de drogas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Fluxo de tr�nsito');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Hor�rio de �nibus');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Incentivo ao ciclismo');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Incentivo educacional');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Insatisfeito');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Localiza��o das escolas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Mobilidade');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Op��o de �nibus');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Organiza��o');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Policiamento');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Privada');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'P�blica');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Qualidade das cal�adas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Qualidade das vias');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Qualidade e tempo de atendimento');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade de cal�adas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade de ciclovias');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade de escolas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade de hospitais e leitos');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade de �nibus');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade de passageiros');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Respeito aos ciclistas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Saneamento B�sico');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Sa�de');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Seguran�a');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Seguran�a no transporte coletivo');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Sinaliza��o');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Tempo para realiza��o de exames');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Tr�nsito');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Transporte coletivo');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Tratamento da �gua');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Tratamento de esgoto');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Vagas escolares');	
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Pessoas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Gentileza');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Respeito aos idosos');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'M�sica alta no transporte coletivo');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Vizinhan�a');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Guias rebaixadas para cadeirantes');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Quantidade e localiza��o de escolas');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Qualidade do Ensino Fundamental');
insert into parametro (id, nome) values (nextval('seq_parametro'), 'Qualidade do Ensino M�dio');

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), null, (select id from parametro where nome = 'Mobilidade'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Mobilidade'), 
(select id from parametro where nome = 'Tr�nsito'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Tr�nsito'), 
(select id from parametro where nome = 'Fluxo de tr�nsito'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Tr�nsito'), 
(select id from parametro where nome = 'Qualidade das vias'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Tr�nsito'), 
(select id from parametro where nome = 'Sinaliza��o'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Tr�nsito'), 
(select id from parametro where nome = 'Fiscaliza��o'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Mobilidade'), 
(select id from parametro where nome = 'Transporte coletivo'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Transporte coletivo'), 
(select id from parametro where nome = 'Quantidade de �nibus'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Transporte coletivo'), 
(select id from parametro where nome = 'Hor�rio de �nibus'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Transporte coletivo'), 
(select id from parametro where nome = 'Op��o de �nibus'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Transporte coletivo'), 
(select id from parametro where nome = 'Quantidade de passageiros'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Mobilidade'), 
(select id from parametro where nome = 'Ciclismo'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Ciclismo'), 
(select id from parametro where nome = 'Quantidade de ciclovias'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Ciclismo'), 
(select id from parametro where nome = 'Estrutura'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Ciclismo'), 
(select id from parametro where nome = 'Incentivo ao ciclismo'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Ciclismo'), 
(select id from parametro where nome = 'Respeito aos ciclistas'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Mobilidade'), 
(select id from parametro where nome = 'Cal�adas'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Cal�adas'), 
(select id from parametro where nome = 'Qualidade das cal�adas'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Cal�adas'), 
(select id from parametro where nome = 'Quantidade de cal�adas'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Cal�adas'), 
(select id from parametro where nome = 'Guias rebaixadas para cadeirantes'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), null, (select id from parametro where nome = 'Sa�de'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Sa�de'), 
(select id from parametro where nome = 'P�blica'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'P�blica'), 
(select id from parametro where nome = 'Quantidade de hospitais e leitos'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'P�blica'), 
(select id from parametro where nome = 'Tempo para realiza��o de exames'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'P�blica'), 
(select id from parametro where nome = 'Qualidade e tempo de atendimento'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'P�blica'), 
(select id from parametro where nome = 'Organiza��o'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Sa�de'), 
(select id from parametro where nome = 'Privada'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Privada'), 
(select id from parametro where nome = 'Quantidade de hospitais e leitos'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Privada'), 
(select id from parametro where nome = 'Tempo para realiza��o de exames'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Privada'), 
(select id from parametro where nome = 'Qualidade e tempo de atendimento'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Privada'), 
(select id from parametro where nome = 'Organiza��o'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Sa�de'), 
(select id from parametro where nome = 'Saneamento B�sico'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Saneamento B�sico'), 
(select id from parametro where nome = 'Distribui��o de �gua'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Saneamento B�sico'), 
(select id from parametro where nome = 'Tratamento da �gua'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Saneamento B�sico'), 
(select id from parametro where nome = 'Tratamento de esgoto'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Saneamento B�sico'), 
(select id from parametro where nome = 'Coleta de lixo'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), null, (select id from parametro where nome = 'Seguran�a'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Seguran�a'), 
(select id from parametro where nome = 'Fiscaliza��o ao tr�fico de drogas'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Seguran�a'), 
(select id from parametro where nome = 'Policiamento'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Seguran�a'), 
(select id from parametro where nome = 'Combate a viol�ncia'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Seguran�a'), 
(select id from parametro where nome = 'Seguran�a no transporte coletivo'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), null, (select id from parametro where nome = 'Educa��o'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Educa��o'), 
(select id from parametro where nome = 'Quantidade e localiza��o de escolas'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Educa��o'), 
(select id from parametro where nome = 'Qualidade do Ensino Fundamental'));


insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Educa��o'), 
(select id from parametro where nome = 'Qualidade do Ensino M�dio'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Educa��o'), 
(select id from parametro where nome = 'Vagas escolares'));

insert into arvore 
(id, ativo, data_atualizacao, data_inclusao, id_arvore, id_parametro) 
values 
(nextval('seq_arvore'), true, now(),now(), (select a.id from arvore a, parametro p where a.id_parametro = p.id and p.nome = 'Educa��o'), 
(select id from parametro where nome = 'Incentivo educacional'));