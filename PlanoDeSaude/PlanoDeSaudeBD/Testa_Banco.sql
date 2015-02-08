DROP DATABASE planoDeSaude;

CREATE DATABASE planoDeSaude;

USE planoDeSaude;

CREATE TABLE PLANO(
        id_plano        INT AUTO_INCREMENT,
        descricao       VARCHAR(20),
        PRIMARY KEY(id_plano)
);

CREATE TABLE MEDICO(
        crm             VARCHAR(14),
        nome            VARCHAR(150) NOT NULL,
        credenciado     BOOLEAN NOT NULL DEFAULT true,
        PRIMARY KEY(crm)
);

CREATE TABLE USUARIO(
        id_usuario      INT AUTO_INCREMENT,
        cpf             CHAR(14) NOT NULL UNIQUE,
        nome            VARCHAR(150) NOT NULL,
        telefone        VARCHAR(15),
        rua             VARCHAR(100) NOT NULL,
        numero          VARCHAR(15),
        bairro          VARCHAR(60) NOT NULL,
        cidade          VARCHAR(100) NOT NULL,
        referencia      VARCHAR(60),
        cep             CHAR(9) NOT NULL,
        estado          VARCHAR(60) NOT NULL,
        ativo BOOLEAN   NOT NULL DEFAULT true,
        data_entrada    DATE NOT NULL,
        id_plano        INT NOT NULL,

        PRIMARY KEY(id_usuario),

        CONSTRAINT fk_USUARIO_id_plano_PLANO_id_plano
        FOREIGN KEY USUARIO(id_plano)
        REFERENCES PLANO(id_plano)
);

CREATE TABLE ESTABELECIMENTO_MEDICO(
        cnpj_estabelecimento    VARCHAR(18),
        credenciado             BOOLEAN NOT NULL DEFAULT true,
        nome VARCHAR(100),
        rua             VARCHAR(100) NOT NULL,
        numero          VARCHAR(6),
        bairro          VARCHAR(60) NOT NULL,
        cidade          VARCHAR(100) NOT NULL,
        referencia      VARCHAR(60),
        cep             CHAR(9) NOT NULL,
        estado          VARCHAR(60) NOT NULL,
        tipo_estabelecimento    VARCHAR(20) NOT NULL,
        
        PRIMARY KEY(cnpj_estabelecimento)

);

CREATE TABLE MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO(
        crm_medico VARCHAR(14),
        cnpj_estabelecimento VARCHAR(18),
        
        PRIMARY KEY(crm_medico, cnpj_estabelecimento),
        
        CONSTRAINT fk_MEDICO_RESPONSAVEL_crm_medico_MEDICO_crm
        FOREIGN KEY MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO(crm_medico)
        REFERENCES MEDICO(crm),

        CONSTRAINT fk_MEDICO_RESPONSAVEL_cnpj_estabelecimento_ESTABELECIMENTO
        FOREIGN KEY MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO(cnpj_estabelecimento)
        REFERENCES ESTABELECIMENTO_MEDICO(cnpj_estabelecimento)
        
);

CREATE TABLE ESPECIALIDADE(
        id_especialidade        INT AUTO_INCREMENT,
        nome                    VARCHAR(50) NOT NULL,
        descricao               VARCHAR(150) NOT NULL,
        PRIMARY KEY(id_especialidade)
);

CREATE TABLE PROCEDIMENTO(
        id_procedimento         INT AUTO_INCREMENT,
        preco                   FLOAT NOT NULL,
        nome                    VARCHAR(45),
        tipo_procedimento       VARCHAR(10),
        id_especialidade        INT,
        
        PRIMARY KEY(id_procedimento),

        CONSTRAINT fk_ESPECIALIDADE_id_esp_PROCEDIMENTO_id_esp
        FOREIGN KEY (id_especialidade)
        REFERENCES ESPECIALIDADE(id_especialidade)
);

CREATE TABLE MEDICO_tem_ESPECIALIDADE(
        crm_medico              VARCHAR(14),
        id_especialidade        INT,
        
        PRIMARY KEY(crm_medico, id_especialidade),
        
        CONSTRAINT fk_MEDICO_tem_ESPECIALIDADE_crm_medico_MEDICO_crm
        FOREIGN KEY MEDICO_tem_ESPECIALIDADE(crm_medico)
        REFERENCES MEDICO(crm),

        CONSTRAINT fk_MEDICO_tem_ESPECIALIDADE_crm_medico_ESPECIALIDADE_id
        FOREIGN KEY MEDICO_tem_ESPECIALIDADE(id_especialidade)
        REFERENCES ESPECIALIDADE(id_especialidade)
);

CREATE TABLE ESTABELECIMENTO_tem_ESPECIALIDADE (
    cnpj_estabelecimento_medico VARCHAR(18),
    id_especialidade            INT,

    PRIMARY KEY (cnpj_estabelecimento_medico , id_especialidade),
    
        CONSTRAINT ESTABELECIMENTO_tem_ESPECIALIDADE_cnpj_ESTAB_MEDICO_cnpj 
        FOREIGN KEY (cnpj_estabelecimento_medico)
        REFERENCES ESTABELECIMENTO_MEDICO (cnpj_estabelecimento),
    
        CONSTRAINT ESTABELECIMENTO_tem_ESPECIALIDADE_id_ESPECIALIDADE_id 
        FOREIGN KEY (id_especialidade)
        REFERENCES ESPECIALIDADE(id_especialidade)
);

CREATE TABLE EVENTO_MEDICO(
        id_procedimento_realizado INT AUTO_INCREMENT,
        id_procedimento         INT,
        id_usuario              INT,
        data_realizacao         TIMESTAMP,
        cnpj_estabelecimento    VARCHAR(18),
        
        PRIMARY KEY (id_procedimento_realizado),
        
        CONSTRAINT fk_MEDICO_realiza_PROC_crm_medico_MEDICO_crm
        FOREIGN KEY (cnpj_estabelecimento)
        REFERENCES ESTABELECIMENTO_MEDICO(cnpj_estabelecimento),

        CONSTRAINT fk_MEDICO_realiza_PROC_id_proc_PROCEDIMENTO_id_proc
        FOREIGN KEY (id_procedimento)
        REFERENCES PROCEDIMENTO(id_procedimento),
        
        CONSTRAINT fk_MEDICO_realiza_PROC_id_usuario_USUARIO_id_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIO(id_usuario)
);

CREATE TABLE MEDICO_realiza_EVENTO_MEDICO(
        id_procedimento_realizado INT,
        crm_medico VARCHAR(14),

        PRIMARY KEY (id_procedimento_realizado, crm_medico),

        CONSTRAINT fk_MED_REAL_EVENTO_MED_id_proc_real_EVENTO_MED_id_proc_real
        FOREIGN KEY (id_procedimento_realizado)
        REFERENCES EVENTO_MEDICO(id_procedimento_realizado),

        CONSTRAINT fk_MED_REAL_EVENTO_MED_crm_medico_MEDICO_crm
        FOREIGN KEY (crm_medico)
        REFERENCES MEDICO(crm)
);

CREATE VIEW MEDICOS_E_HORARIOS AS
        SELECT med_realiza_em.crm_medico, even_med.data_realizacao, even_med.id_procedimento_realizado
        FROM 
                MEDICO_realiza_EVENTO_MEDICO med_realiza_em,
                EVENTO_MEDICO even_med
        WHERE
                even_med.id_procedimento_realizado = med_realiza_em.id_procedimento_realizado
                AND even_med.data_realizacao >= TIMESTAMP(NOW());

CREATE VIEW EVENTO_E_ESPECIALIDADE AS
    SELECT 
        evento.id_procedimento_realizado, proc.id_especialidade
    FROM
        EVENTO_MEDICO evento,
        PROCEDIMENTO proc
    WHERE
        proc.id_procedimento = evento.id_procedimento;  

INSERT INTO PLANO(id_plano, descricao) VALUES(1, 'basico');
INSERT INTO PLANO(id_plano, descricao) VALUES(2, 'especial');


INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (1, '113.314.274-52', 'Rerisson Daniel Costa Silva Matos', '83996797956', 'Joaquim Guilherme Vasconscelos', '238B', 'Bela Vista', 'São Sebastião de Lagoa de Roça', 'Ao lado da rádio', '58119-000', 'Paraíba',true, '2003-02-25', 1);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (2, '379.614.268-09', 'Joaquim de Albuquerque Medeiros', '83996229624', 'Edifício Rocha Cavalcanti', '44Z', 'Catolé', 'Campina Grande', 'o próprio edifício', '11900-174', 'Sergipe', true, '2007-08-4', 2);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (3, '816.888.695-06', 'Laís Oliveira', '83999595190', 'Das Borboletas Margaridas', '456', 'Lajedo', 'Cabaceiras', 'Perto da fazenda', '87671-917', 'Rio de Janeiro', true, '2002-08-16', 1);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (4, '486.852.150-05', 'Terezinha Costa Silva', '83996851632', 'José Cândido Coelho', '544', 'Santo Antônio', 'Esperança', 'Perto dos Almeida', '65180-952', 'Paraíba', true, '2002-05-16', 1);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (5, '413.523.414-33', 'Gustavo Bezerra Ribeiro', '83998157234', 'Josefa Trindade', '48', 'Centro', 'São Sebastião de Lagoa de Roça', 'Geraldo Cabeleireiro', '58119-000', 'Paraíba', true, '2000-04-20', 1);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (6, '765.777.618-84', 'Letícia Aires', '83987747571', 'Caramelos Azuis de Banana', '544N', 'Monte Castelo', 'Campina Grande', 'perto do fiteiro branco', '87621-327', 'Nowhere do sudoeste', false, '2000-04-23', 2);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (7, '388.467.114-67', 'Juan Barros Barros e Barros', '83995737126', 'Beco diagonal', '523', 'Monte Santo', 'Campina Grande', 'Na ladeira', '10237-917', 'São Paulo', true, '1999-06-24', 1);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (8, '619.758.664-95', 'Ordan Silva Santos', '83992211574', 'Top Coder 5th avenue', '244', 'Santa Clara', 'Palo Alto', 'Ao lado da casa do tio Zuck', '11387-190', 'California', true, '2002-08-22', 1);

INSERT INTO USUARIO(id_usuario, cpf, nome, telefone, rua, numero, bairro, cidade, referencia, cep, estado, ativo, data_entrada, id_plano)
VALUES (9, '166.287.055-87', 'João José Joaquim Macedo', '83972115322', 'Maurício Mário Marcelo Andrade', '423', 'Dinamérica', 'Areial', 'caixa dágua', '72167-192', '', true, '2000-11-25', 1);


INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(1, "Alergia e Imunologia", "diagnóstico e tratamento das doenças alérgicas e do sistema imunitário");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(2, "Anestesiologia", "estudo da dor e anestesia");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(3, "Angiologia", "é a área da medicina que estuda o tratamento das doenças do aparelho circulatório");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(4, "Cancerologia (oncologia)", "é a especialidade que estuda os tumores malignos ou câncer");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(5, "Cardiologia", "estudo das doenças relacionadas com o coração");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(6, "Cirurgia Cardiovascular", "tratamento cirúrgico de doenças do coração");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(7, "Cirurgia de cabeça e pescoço", "tratamento cirúrgico de doenças da cabeça e do pescoço");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(8, "Cirurgia Plástica", "tratamento para correção das deformidades, má formação ou lesões que comprometem funções dos órgãos através de cirurgia de caráter reparador.");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(9, "Cirurgia Torácica", "atua na cirurgia dos pulmões");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(10, "Dermatologia", "é o estudo da pele e suas doenças");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(11, "Endocrinologia e Metabologia", "é o tratamento das glândulas");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(12, "Endoscopia", "cirurgia abdominal, cirurgia videolaparoscópica e Cirurgia do trauma");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(13, "Gastroenterologia", "É o tratamento do aparelho digestivo");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(14, "Geriatria", "É o estudo das doenças do idoso");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(15, "Mastologia", "Tratamento de doenças da mama");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(16, "Infectologia", "Estudo das causas e tratamentos de infecções (causadas por vírus, bacterias e fungos)");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(17, "Ginecologia e obstetrícia", "é o estudo do sistema reprodutor feminino");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(18, "Oftalmologia", "É a parte da medicina que estuda e trata os distúrbios dos olhos");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(19, "Ortopedia e Traumatologia", "É a parte da medicina que estuda e trata as doenças do sistema locomotor e as fraturas");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(20, "Urologia", "É a parte da medicina que estuda e trata cirurgicamente e clinicamente os problemas do sistema urinário e do sistema reprodutor masculino");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(21, "Obstetrícia", "Trata da saúde na reprodução da mulher");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(22, "Odontologia", "Trata da saúde da boca");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(23, "Pediatria", "Trata da saúde na infância");
INSERT INTO ESPECIALIDADE (id_especialidade, nome, descricao) VALUES(24, "Clínica médica", "Trata de doenças não cirúrgicas, não obstetrícias e não ginecológicas");

INSERT INTO MEDICO (crm, nome, credenciado) VALUES("1", "Dráuzio Varela", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("2", "Daniel Alves", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("3", "Damião Feliciano", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("4", "João Leite", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("5", "Gregory House", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("6", "André Matos", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("7", "Allison Cameron", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("8", "Robert Chase", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("9", "João das Neves", true);
INSERT INTO MEDICO (crm, nome, credenciado) VALUES("0", "Eric Foreman", true);

INSERT INTO ESTABELECIMENTO_MEDICO(cnpj_estabelecimento, credenciado, nome, rua, numero, bairro, cidade, referencia, cep, estado, tipo_estabelecimento) VALUES("51.334.520/0001-71", true, "Hospital Sírio-Libanês", "Dona Adma Jafet", "91", "Bela Bista", "São Paulo", null, '01308-050', 'São Paulo', "hospital");

INSERT INTO ESTABELECIMENTO_MEDICO(cnpj_estabelecimento, credenciado, nome, rua, numero, bairro, cidade, referencia, cep, estado, tipo_estabelecimento) VALUES("36.896.512/0001-25", true, "Hospital da FAP", "Dr. Franciso Pinto", "s/n", "Bodocongó", "Campina Grande", null, '58429-350', 'Paraíba', "hospital");

INSERT INTO ESTABELECIMENTO_MEDICO(cnpj_estabelecimento, credenciado, nome, rua, numero, bairro, cidade, referencia, cep, estado, tipo_estabelecimento) VALUES("03.992.927/0001-43", true, "Clínica Santa Clara", "Duque de Caxias", "630", "Prata", "Campina Grande", null, '02473-039', 'Paraíba', "consultorio");

INSERT INTO ESTABELECIMENTO_MEDICO(cnpj_estabelecimento, credenciado, nome, rua, numero, bairro, cidade, referencia, cep, estado, tipo_estabelecimento) VALUES("75.436.232/0001-96", true, "Clínica Nova João Pessoa", "Presidente Epitácio Pessoa", "557", "Tambaú", "João Pessoa", null, '32981-472', 'Rio Grande do Norte', "consultorio");

INSERT INTO ESTABELECIMENTO_MEDICO(cnpj_estabelecimento, credenciado, nome, rua, numero, bairro, cidade, referencia, cep, estado, tipo_estabelecimento) VALUES("89.418.013/0001-99", true, "Consultório do Headbanger", "Josefa Farias Trindade", "110", "Centro", "São Sebastião de Lagoa de Roça", null, '93482-943', 'Goiás', "consultorio");

INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('1', '51.334.520/0001-71');
INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('2', '36.896.512/0001-25');
INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('3', '03.992.927/0001-43');
INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('4', '75.436.232/0001-96');
INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('5', '75.436.232/0001-96');
INSERT INTO MEDICO_responsavel_por_ESTABELECIMENTO_MEDICO (crm_medico, cnpj_estabelecimento) VALUES('5', '89.418.013/0001-99');


INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('1', 3);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('1', 18);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('1', 5);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('2', 24);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('3', 18);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('3', 23);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('3', 24);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('4', 9);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('5', 18);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('6', 19);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('6', 18);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('6', 3);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('7', 5);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('8', 5);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('9', 21);
INSERT INTO MEDICO_tem_ESPECIALIDADE (crm_medico, id_especialidade) VALUES('0', 8);


INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE (cnpj_estabelecimento_medico, id_especialidade) VALUES("51.334.520/0001-71", 1);
INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE (cnpj_estabelecimento_medico, id_especialidade) VALUES("36.896.512/0001-25", 2);
INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE (cnpj_estabelecimento_medico, id_especialidade) VALUES("03.992.927/0001-43", 3);
INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE (cnpj_estabelecimento_medico, id_especialidade) VALUES("75.436.232/0001-96", 4);
INSERT INTO ESTABELECIMENTO_tem_ESPECIALIDADE (cnpj_estabelecimento_medico, id_especialidade) VALUES("89.418.013/0001-99", 5);


INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(1, 5, 2500.0, 'Ponte de Safena', 'cirurgia');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(2, 21, 2000.0, 'Cesariana', 'cirurgia');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(3, 22, 35.0, 'Obturação', 'cirurgia');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(4, 18, 500.35, 'Catarata', 'cirurgia');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(5, 23, 160.50, 'Pediatra', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(6, 18, 996.70, 'Miopia', 'cirurgia');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(7, 13, 200.00, 'Estômago', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(8, 19, 150.00, 'Ortopedista', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(9, 3, 200.00, 'Angiografia', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(10, 24, 180.00, 'Check-up', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(11, 5, 70.00, 'Eletrocardiograma', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(12, 9, 150.00, 'Traqueostomia', 'cirurgia');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(13, 18, 996.70, 'Oftamólogo', 'consulta');
INSERT INTO PROCEDIMENTO(id_procedimento, id_especialidade, preco, nome, tipo_procedimento) VALUES(14, 21, 2000.0, 'Pré-Natal', 'consulta');

INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(1, 8, 8, '2011-09-08 18:35', '03.992.927/0001-43');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(2, 5, 1, '2012-12-10 11:06', '03.992.927/0001-43');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(3, 10, 5, '2014-10-28 06:04', '51.334.520/0001-71');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(4, 9, 7, '2010-07-17 11:36', '89.418.013/0001-99');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(5, 4, 7, '2006-08-24 02:16', '36.896.512/0001-25');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(6, 11, 5, '2014-05-04 17:24', '03.992.927/0001-43');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(7, 10, 3, '2011-12-20 15:38', '36.896.512/0001-25');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(8, 11, 9, '2009-07-01 07:29', '89.418.013/0001-99');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(9, 5, 9, '2010-12-19 09:20', '75.436.232/0001-96');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(10, 13, 6, '2014-10-24 19:30', '89.418.013/0001-99');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(11, 12, 4, '2015-06-13 01:17', '36.896.512/0001-25');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(12, 14, 8, '2010-08-17 22:17', '89.418.013/0001-99');
INSERT INTO EVENTO_MEDICO(id_procedimento_realizado, id_procedimento, id_usuario, data_realizacao, cnpj_estabelecimento) VALUES(13, 1, 2, '2012-05-28 23:19', '51.334.520/0001-71');


INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(1, '6');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(2, '3');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(3, '2');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(4, '6');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(5, '3');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(6, '0');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(7, '3');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(8, '1');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(9, '1');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(10, '5');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(10, '6');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(11, '4');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(12, '9');
INSERT INTO MEDICO_realiza_EVENTO_MEDICO(id_procedimento_realizado, crm_medico) VALUES(13, '0');