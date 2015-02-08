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
