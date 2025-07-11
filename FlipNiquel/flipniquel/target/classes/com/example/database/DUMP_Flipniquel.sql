CREATE DATABASE Industria_db;
USE Industria_db;

CREATE TABLE usuarioRH(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

INSERT INTO usuarioRH(usuario, senha) VALUES ('julia.rh', '2603');
INSERT INTO usuarioRH(usuario, senha) VALUES ('fabiane.rh', '0728');

CREATE TABLE `dadospessoais` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_completo` VARCHAR(45) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `sexo` CHAR(20) NOT NULL,
  `estado_civil` VARCHAR(15) NOT NULL,
  `conjugue` VARCHAR(45) NULL,
  `data_conjugue` DATE NULL,
  `dependentes` VARCHAR(150) NULL,
  `nacionalidade` VARCHAR(45) NOT NULL,
  `naturalidade` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `rg` VARCHAR(15) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `filiacao` VARCHAR(150) NOT NULL,
  `tipo_sanguineo` VARCHAR(3) NOT NULL,
  `contato_emergencia` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE INDEX `nome_completo_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `telefone_UNIQUE` (`telefone` ASC),
  UNIQUE INDEX `rg_UNIQUE` (`rg` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC));

CREATE TABLE `dadosprofissionais` (
  `idprof` INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `cargo` VARCHAR(30) NOT NULL,
  `departamento` VARCHAR(45) NOT NULL,
  `funcao` VARCHAR(150) NOT NULL,
  `maquinas` VARCHAR(45) NULL,
  `admissao` DATE NOT NULL,
  `salario` VARCHAR(45) NOT NULL,
  `dadosbancarios` VARCHAR(45) NOT NULL,
  `beneficios` VARCHAR(45) NULL,
  `escolaridade` VARCHAR(45) NOT NULL,
  `ctps` VARCHAR(45) NOT NULL,
  `pisPasep` VARCHAR(45) NOT NULL,
  `contrato` VARCHAR(45) NOT NULL,
  `horario` VARCHAR(45) NOT NULL,
  `acidentes` VARCHAR(200) NULL,
  `advertencias` VARCHAR(200) NULL,
  `dados_pessoais` VARCHAR(15),
  
  FOREIGN KEY (dados_pessoais) REFERENCES dadospessoais(cpf),
  UNIQUE INDEX `ctps_UNIQUE` (`ctps` ASC),
  UNIQUE INDEX `pisPasep_UNIQUE` (`pisPasep` ASC),
  UNIQUE INDEX `dadosbancarios_UNIQUE` (`dadosbancarios` ASC));

ALTER TABLE `dadospessoais` 
DROP INDEX `email_UNIQUE` ,
DROP INDEX `cpf_UNIQUE` ,
DROP INDEX `rg_UNIQUE` ,
DROP INDEX `telefone_UNIQUE` ;

ALTER TABLE `dadosprofissionais` 
DROP INDEX `dadosbancarios_UNIQUE` ,
DROP INDEX `pisPasep_UNIQUE` ,
DROP INDEX `ctps_UNIQUE` ;

-- Inserção de Dados Pessoais e Profissionais 1
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('João Silva', '12-04-1990', 'Masculino', 'Casado', 'Maria Silva', '10-12-2015', '2', 'Brasileiro', 'São Paulo', '12345678901', '123456789', 'Rua A, 123', '(11) 91234-5678', 'joao.silva@email.com', 'Carlos Silva, Ana Silva', 'O+', '(11) 98765-4321');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista de TI', 'Tecnologia', 'Desenvolvedor', 'Máquina A, Máquina B', '01-05-2015', 3500.00, 'Banco XYZ, Agência 123', 'Vale alimentação, Seguro saúde', 'Superior Completo', '1234567890', '12345678901', 'CLT', '08:00-17:00', 'Nenhum', 'Nenhuma', '12345678901');

-- Inserção de Dados Pessoais e Profissionais 2
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Ana Souza', '22-06-1985', 'Feminino', 'Solteira', 'N/A', NULL, '1', 'Brasileira', 'Rio de Janeiro', '23456789012', '234567890', 'Avenida B, 456', '(21) 92345-6789', 'ana.souza@email.com', 'Luiz Souza, Maria Souza', 'A-', '(21) 99876-5432');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Gerente de Marketing', 'Marketing', 'Estratégia', 'Máquina C', '15-08-2010', 6000.00, 'Banco ABC, Agência 456', 'Vale refeição, Plano odontológico', 'Mestrado', '2345678901', '23456789012', 'PJ', '09:00-18:00', 'Nenhum', 'Nenhuma', '23456789012');

-- Inserção de Dados Pessoais e Profissionais 3
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Carlos Pereira', '05-11-1982', 'Masculino', 'Divorciado', 'Ana Pereira', '18-06-2010', '3', 'Brasileiro', 'Belo Horizonte', '34567890123', '345678901', 'Rua C, 789', '(31) 93456-7890', 'carlos.pereira@email.com', 'João Pereira, Sandra Pereira', 'B+', '(31) 97654-3210');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Engenheiro de Produção', 'Produção', 'Coordenação', 'Máquina D, Máquina E', '10-12-2013', 8000.00, 'Banco DEF, Agência 789', 'Vale transporte, Plano de saúde', 'Doutorado', '3456789012', '34567890123', 'CLT', '07:30-16:30', 'Nenhum', 'Nenhuma', '34567890123');

-- Inserção de Dados Pessoais e Profissionais 4
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Patrícia Lima', '01-02-1995', 'Feminino', 'Casada', 'Ricardo Lima', '22-04-2020', '0', 'Brasileira', 'Salvador', '45678901234', '456789012', 'Rua D, 101', '(71) 91234-5678', 'patricia.lima@email.com', 'Carlos Lima, Ana Lima', 'O-', '(71) 98765-4321');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Assistente Administrativo', 'Administração', 'Suporte', 'Máquina F', '01-01-2021', 2000.00, 'Banco GHI, Agência 101', 'Vale transporte, Seguro de vida', 'Técnico', '4567890123', '45678901234', 'CLT', '08:00-17:00', 'Nenhum', 'Nenhuma', '45678901234');

-- Inserção de Dados Pessoais e Profissionais 5
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Ricardo Santos', '16-07-1990', 'Masculino', 'Solteiro', 'N/A', NULL, '0', 'Brasileiro', 'Curitiba', '56789012345', '567890123', 'Avenida E, 202', '(41) 92345-6789', 'ricardo.santos@email.com', 'Marcos Santos, Fernanda Santos', 'AB-', '(41) 97654-3210');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Consultor de Vendas', 'Vendas', 'Estratégia Comercial', 'Máquina G', '15-10-2018', 4500.00, 'Banco JKL, Agência 102', 'Vale alimentação, Bônus de performance', 'Superior Completo', '5678901234', '56789012345', 'PJ', '09:00-18:00', 'Nenhum', 'Nenhuma', '56789012345');

-- Inserção de Dados Pessoais e Profissionais 6
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Fernanda Oliveira', '08-12-1993', 'Feminino', 'Casada', 'Carlos Oliveira', '30-08-2018', '1', 'Brasileira', 'Porto Alegre', '67890123456', '678901234', 'Rua F, 303', '(51) 93456-7890', 'fernanda.oliveira@email.com', 'Jorge Oliveira, Claudia Oliveira', 'O+', '(51) 99876-5432');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Coordenadora de RH', 'Recursos Humanos', 'Gestão de Pessoas', 'Máquina H', '01-06-2016', 5000.00, 'Banco MNO, Agência 104', 'Vale refeição, Seguro saúde', 'Pós-Graduação', '6789012345', '67890123456', 'CLT', '08:30-17:30', 'Nenhum', 'Nenhuma', '67890123456');

-- Inserção de Dados Pessoais e Profissionais 7
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Márcio Costa', '18-09-1988', 'Masculino', 'Casado', 'Juliana Costa', '25-12-2013', '2', 'Brasileiro', 'Fortaleza', '78901234567', '789012345', 'Rua G, 404', '(85) 94567-8901', 'marcio.costa@email.com', 'José Costa, Maria Costa', 'B+', '(85) 91234-5678');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Supervisor de Produção', 'Produção', 'Coordenação', 'Máquina I', '10-11-2017', 7000.00, 'Banco PQR, Agência 105', 'Vale alimentação, Transporte corporativo', 'Superior Completo', '7890123456', '78901234567', 'CLT', '07:00-16:00', 'Nenhum', 'Nenhuma', '78901234567');

-- Inserção de Dados Pessoais e Profissionais 8
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Luciana Mendes', '30-10-1991', 'Feminino', 'Solteira', 'N/A', NULL, '0', 'Brasileira', 'Vitória', '89012345678', '890123456', 'Rua H, 505', '(27) 93456-7891', 'luciana.mendes@email.com', 'Paulo Mendes, Carla Mendes', 'A+', '(27) 98765-4322');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Assistente de Marketing', 'Marketing', 'Criação de Conteúdo', 'Máquina J', '20-09-2019', 3000.00, 'Banco STU, Agência 106', 'Vale refeição, Bônus de performance', 'Superior Completo', '8901234567', '89012345678', 'PJ', '09:00-18:00', 'Nenhum', 'Nenhuma', '89012345678');

-- Inserção de Dados Pessoais e Profissionais 9
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Eduardo Pereira', '03-05-1983', 'Masculino', 'Casado', 'Renata Pereira', '17-02-2011', '1', 'Brasileiro', 'Recife', '90123456789', '901234567', 'Rua I, 606', '(81) 91234-6789', 'eduardo.pereira@email.com', 'Luiz Pereira, Joana Pereira', 'O+', '(81) 98765-4321');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Supervisor de Logística', 'Logística', 'Gestão de Estoque', 'Máquina K', '15-04-2016', 6500.00, 'Banco VWX, Agência 107', 'Vale alimentação, Vale transporte', 'Superior Completo', '9012345678', '90123456789', 'CLT', '08:00-17:00', 'Nenhum', 'Nenhuma', '90123456789');

-- Inserção de Dados Pessoais e Profissionais 10
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Camila Martins', '21-03-1992', 'Feminino', 'Solteira', 'N/A', NULL, '0', 'Brasileira', 'Curitiba', '01234567890', '012345678', 'Rua J, 707', '(41) 95432-6789', 'camila.martins@email.com', 'Roberto Martins, Silvia Martins', 'B-', '(41) 97654-3210');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Assistente de TI', 'Tecnologia', 'Suporte Técnico', 'Máquina L', '10-07-2018', 2800.00, 'Banco XYZ, Agência 108', 'Vale refeição, Plano de saúde', 'Técnico', '0123456789', '01234567890', 'CLT', '09:00-18:00', 'Nenhum', 'Nenhuma', '01234567890');

-- Inserção de Dados Pessoais e Profissionais 11
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Felipe Costa', '10-01-1986', 'Masculino', 'Casado', 'Mariana Costa', '12-11-2012', '2', 'Brasileiro', 'Natal', '87934566610', '123456789', 'Avenida K, 808', '(84) 98765-4321', 'felipe.costa@email.com', 'Carlos Costa, Vera Costa', 'AB+', '(84) 91234-5678');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Coordenador de Vendas', 'Vendas', 'Gestão Comercial', 'Máquina M', '05-12-2014', 8500.00, 'Banco RST, Agência 109', 'Vale refeição, Vale transporte', 'Superior Completo', '12487890', '12345678901', 'CLT', '08:30-17:30', 'Nenhum', 'Nenhuma', '87934566610');

-- Inserção de Dados Pessoais e Profissionais 12
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Juliana Rocha', '17-09-1989', 'Feminino', 'Solteira', 'N/A', NULL, '1', 'Brasileira', 'Belém', '87661366659', '234567890', 'Rua L, 909', '(91) 92345-6789', 'juliana.rocha@email.com', 'Adriana Rocha, Jorge Rocha', 'O+', '(91) 97654-3210');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Recepcionista', 'Administração', 'Atendimento ao Cliente', 'Máquina N', '01-08-2020', 1800.00, 'Banco UVW, Agência 110', 'Vale transporte, Seguro saúde', 'Ensino Médio Completo', '2345678901', '23456789012', 'CLT', '08:00-17:00', 'Nenhum', 'Nenhuma', '87661366659');

-- Inserção de Dados Pessoais e Profissionais 13
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjugue, data_conjugue, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Ricardo Almeida', '25-06-1987', 'Masculino', 'Casado', 'Larissa Almeida', '08-03-2014', '1', 'Brasileiro', 'Brasília', '54467389210', '345678901', 'Rua O, 1010', '(61) 91234-5678', 'ricardo.almeida@email.com', 'Eduardo Almeida, Paula Almeida', 'AB-', '(61) 98765-4321');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista de Recursos Humanos', 'Recursos Humanos', 'Recrutamento e Seleção', 'Máquina O', '01-02-2016', 4000.00, 'Banco XYZ, Agência 111', 'Vale alimentação, Plano odontológico', 'Superior Completo', '3456789012', '34567890123', 'CLT', '09:00-18:00', 'Nenhum', 'Nenhuma', '54467389210');


CREATE TABLE usuarioFinanceiro(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

INSERT INTO usuarioFinanceiro(usuario, senha) VALUES ('karen', '123');

CREATE TABLE usuarioProducao(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);
INSERT INTO usuarioProducao(usuario, senha) 
VALUES
('matheus', '010123'),
('vitor', '010123');
    
CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    setor VARCHAR(50) NOT NULL
);


CREATE TABLE setores (
    id_setores int auto_increment primary key,
    nome_setor varchar(50)
);

CREATE TABLE funcionarios (
    id_funcionarios int auto_increment primary key,
    nome varchar(50)
);

CREATE TABLE funcionario_setor (
    id_funcionario_setor int auto_increment primary key,
    fk_funcionario int,
    fk_setor int,
    FOREIGN KEY (fk_funcionario) REFERENCES funcionarios(id_funcionarios),
    FOREIGN KEY (fk_setor) REFERENCES setores(id_setores)
);

create table fluxo(
id_fluxo int auto_increment primary key,
data_transacao varchar(50),
fk_setor int,
descricao varchar(50),
valor DECIMAL(10,2),
categoria varchar(50),
forma_pagto varchar(50),
vencimento varchar(50),
status boolean,
foreign key (fk_setor) references setores(id_setores));


create table solicitacoes(
id_solicitacoes int auto_increment primary key,
data_solicitacao varchar(50), 
fk_setor int,
descricao varchar(50),
quantidade varchar(50),
valor DECIMAL(10,2),
prazo varchar(50),
status varchar(50),
foreign key (fk_setor) references setores(id_setores));


create table pagfuncionarios(
id_pagfuncionarios int auto_increment primary key,
fk_funcionarios int,
fk_setor int,
data_pagto varchar(50),
salario_base DECIMAL(10,2),
descontos DECIMAL(10,2) ,
valor_liquido DECIMAL(10,2),
status boolean,
foreign key (fk_setor) references setores(id_setores));



CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    lote VARCHAR(100) NOT NULL,
    sku VARCHAR(100) NOT NULL
);
INSERT INTO produto (nome, lote, sku) VALUES
('Painel de Controle', 'L202505-A', 'PNL-CTRL-001'),
('Módulo de Reels', 'L202505-B', 'MOD-REELS-002'),
('Display LCD 24"', 'L202505-C', 'DISP-24LCD-003'),
('Placa de Circuito Principal', 'L202505-D', 'PLCCIRCUITO-004'),
('Fonte de Alimentação 12V', 'L202505-E', 'FONTE-12V-005'),
('Joystick para Máquinas', 'L202505-F', 'JOYSTICK-006'),
('Sensor de Moeda', 'L202505-G', 'SENSOR-MOEDA-007'),
('Motor de Reels', 'L202505-H', 'MOTOR-REELS-008'),
('Teclado de Controle', 'L202505-I', 'TECLADO-CTRL-009'),
('Botão de Início', 'L202505-J', 'BOTAO-START-010');

CREATE TABLE producao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    funcionario VARCHAR(100),
    maquina VARCHAR(100),
    produto VARCHAR(100),
    quantidade INT NOT NULL,
    data_producao DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO producao (funcionario, maquina, produto, quantidade, data_producao) VALUES
('Matheus Nunes de Almeida', 'Serra de Corte Rápido', 'Painel de Controle', 10, '2024-03-15'),
('Vitor Hugo Trindade', 'Furadeira de Bancada 13mm', 'Joystick para Máquinas', 9, '2025-02-12'),
('Matheus Nunes de Almeida', 'Dobradeira de Chapas Manual', 'Módulo de Reels', 8, '2024-06-10'),
('Vitor Hugo Trindade', 'Parafusadeira Bateria 18V', 'Sensor de Moeda', 11, '2024-08-05'),
('Matheus Nunes de Almeida', 'Soldadora MIG 220V', 'Display LCD 24"', 6, '2024-11-01'),
('Vitor Hugo Trindade', 'Retífica Elétrica', 'Motor de Reels', 7, '2024-10-18'),
('Matheus Nunes de Almeida', 'Cabine de Pintura', 'Placa de Circuito Principal', 12, '2023-09-18'),
('Vitor Hugo Trindade', 'Chave de Fenda Conjunto 6 peças', 'Teclado de Controle', 10, '2024-06-22'),
('Matheus Nunes de Almeida', 'Compressor de Ar 200L', 'Fonte de Alimentação 12V', 14, '2024-10-10'),
('Vitor Hugo Trindade', 'Chave Philips Conjunto 6 peças', 'Botão de Início', 13, '2024-06-22');

CREATE TABLE usuarioEstoque(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);


CREATE TABLE estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    marca VARCHAR(100),
    fornecedor VARCHAR(255),
    localizacao VARCHAR(255),
    codigo VARCHAR(50),
    preco_de_custo DECIMAL(10, 2),
    categoria VARCHAR(100)
);

CREATE TABLE usuarioQA(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);



CREATE TABLE tableDash (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    total INT NOT NULL,                 
    aprovados INT NOT NULL,              
    reprovados INT NOT NULL,             
    concertos INT NOT NULL               
);

CREATE TABLE barChart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mes VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL
);

CREATE TABLE barChart1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mes VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL
);

CREATE TABLE lineChart (
    dia VARCHAR(10),
    quantidade INT
);

CREATE TABLE tableRevisao (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    selo VARCHAR (10)NOT NULL,                  
    descricao VARCHAR (100) NOT NULL,              
    status VARCHAR (10) NOT NULL,             
    produtos VARCHAR (20) NOT NULL,
    lote INT NOT NULL,
    chegada DATE NOT NULL,
    saida DATE NOT NULL 
);

CREATE TABLE tableConferidos (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    selo VARCHAR (10)NOT NULL,                  
    descricao VARCHAR (100) NOT NULL,              
    status VARCHAR (10) NOT NULL,             
    produtos VARCHAR (20) NOT NULL,
    lote INT NOT NULL,
    chegada DATE NOT NULL,
    saida DATE NOT NULL 
);

CREATE TABLE tableRecusados (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    selo VARCHAR (12) NOT NULL,                   
    descricao VARCHAR (100) NOT NULL,              
    status VARCHAR (10) NOT NULL,             
    produtos VARCHAR (20) NOT NULL,
    lote INT NOT NULL,
    chegada DATE NOT NULL,
    saida DATE NOT NULL 
);

CREATE TABLE tableProducao (
    id INT AUTO_INCREMENT PRIMARY KEY,                    
    descricao VARCHAR (100) NOT NULL,              
    status VARCHAR (10) NOT NULL,             
    produtos VARCHAR (20) NOT NULL,
    lote INT NOT NULL,
    chegada DATE NOT NULL
);


INSERT INTO tableDash (id, total, aprovados, reprovados, concertos) VALUES (0, 0, 0, 0, 0);

INSERT INTO barChart (mes, quantidade) VALUES('Jan', 2),('Feb', 3),('Mar', 1),('Apr', 0),('Mai', 3),('Jun', 4);

INSERT INTO barChart1 (mes, quantidade) VALUES('Jan', 1),('Feb', 2),('Mar', 1),('Apr', 0),('Mai', 3),('Jun', 1);




CREATE TABLE usuarioAutomacao(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE automacaorh (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_automacao VARCHAR(100) NOT NULL,
    responsavel varchar(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    descricao varchar(500) not null,
    operacao varchar(100) not null,
    setor varchar(100) not null,
    localizacao varchar(100) not null,
    situacao varchar(100) not null,
    prioridade varchar(100) not null
    );

CREATE TABLE automacaoEst (
    id int not null auto_increment PRIMARY KEY,  
    material VARCHAR(100) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade >= 0),
    estado VARCHAR(50) NOT NULL
);

CREATE TABLE automacaoQA (
    id int not null auto_increment PRIMARY KEY,
    produto INT NOT NULL CHECK (produto >= 0),
    selo VARCHAR(100) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    caso VARCHAR(100) NOT NULL,
    chegada VARCHAR(50) NOT NULL,
    saida VARCHAR(50) NOT NULL,
    porcentagem VARCHAR(100) NOT NULL
);

CREATE TABLE automacaoProducao (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    nome_produto VARCHAR(100) NOT NULL,
    preco VARCHAR(100) NOT NULL,
    lote INT NOT NULL CHECK (lote >= 0),
    codigo INT NOT NULL CHECK (codigo >= 0)
);

CREATE TABLE automacaoFinanceiro(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_automacaoFin varchar(100) not null,
    descricaoFin VARCHAR(100) not null, 
    setorFin varchar(100) not null, 
    categoriaFin VARCHAR(100) not null,
    estadoFin varchar(100) not null
);


CREATE TABLE usuarioMaquinario(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

INSERT INTO usuarioMaquinario(usuario, senha) VALUES ('fabiano ', '123');
INSERT INTO usuarioMaquinario(usuario, senha) VALUES ('mauricio', '123');



-- Tabela de equipamentos com setor como ENUM para restrição
CREATE TABLE equipamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    modelo VARCHAR(100),
    numero_serie VARCHAR(50),
    setor ENUM('Acabamento','Almoxarifado', 'Automação', 'Controle de Qualidade', 'Financeiro', 'Manutenção', 'Produção', 'RH', 'Pintura', 'Montagem', 'Teste', 'Qualidade'),
    data_aquisicao DATE,
    valor_aquisicao DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'disponível',
    manutencao_periodica BOOLEAN DEFAULT FALSE,
    observacoes TEXT
);

-- Tabela de manutenções
CREATE TABLE manutencoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipamento_id INT,
    tipo_manutencao VARCHAR(50),
    data_inicio DATE,
    data_conclusao DATE,
    status VARCHAR(20),
    descricao_servico TEXT,
    FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id)
);

-- Tabela de empréstimos modificada para incluir referência à pessoa
CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipamento_id INT,
    setor_solicitante VARCHAR(50),
    funcionario_cpf VARCHAR(15),
    funcionario_nome VARCHAR(100),
    data_inicio DATE,
    data_devolucao DATE,
    status VARCHAR(20) DEFAULT 'no prazo',
    observacoes TEXT,
    FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id)
);

INSERT INTO setores (nome_setor) VALUES
('RH'),
('AUTOMAÇÃO'),
('PRODUÇÃO'),
('ESTOQUE'),
('CONTROLE DE QUALIDADE'),
('FINANCEIRO'),
('MAQUINARIO');


INSERT INTO fluxo (data_transacao, fk_setor, descricao, valor, categoria, forma_pagto, vencimento, status) VALUES
('2025-01-15', 1, 'Compra de componentes', 5000.00, 'Compra', 'Cartão de Crédito', '2025-02-15', true),
('2025-01-20', 2, 'Automação de processos', 1500.00, 'Serviço', 'Transferência', '2025-03-01', true),
('2025-02-01', 3, 'Produção de jogos', 10000.00, 'Venda', 'Boleto', '2025-03-05', true),
('2025-02-10', 4, 'Estoque de materiais', 3000.00, 'Compra', 'Cartão de Débito', '2025-02-20', true),
('2025-02-15', 5, 'Teste de qualidade', 2000.00, 'Serviço', 'Transferência', '2025-03-10', false),
('2025-02-18', 1, 'Salário do mês', 7000.00, 'Salário', 'Transferência', '2025-02-28', true),
('2025-02-20', 2, 'Manutenção de equipamentos', 2500.00, 'Serviço', 'Boleto', '2025-03-15', false),
('2025-03-01', 3, 'Lançamento de novo jogo', 12000.00, 'Venda', 'Cartão de Crédito', '2025-04-01', true),
('2025-03-05', 4, 'Ajustes no estoque', 800.00, 'Serviço', 'Cartão de Débito', '2025-03-20', true),
('2025-03-10', 5, 'Relatório de qualidade', 1500.00, 'Serviço', 'Transferência', '2025-03-25', true),
('2025-03-15', 6, 'Compra de licenças', 1800.00, 'Compra', 'Cartão de Crédito', '2025-04-15', false),
('2025-03-18', 7, 'Consultoria externa', 3200.00, 'Serviço', 'Transferência', '2025-04-10', true),
('2025-03-20', 1, 'Atualização de software', 2100.00, 'Serviço', 'Boleto', '2025-04-05', true),
('2025-03-22', 2, 'Capacitação da equipe', 1500.00, 'Serviço', 'Transferência', '2025-04-20', false),
('2025-03-25', 3, 'Venda de pacote premium', 8500.00, 'Venda', 'Cartão de Crédito', '2025-04-30', true),
('2025-03-28', 4, 'Aquisição de insumos', 2750.00, 'Compra', 'Cartão de Débito', '2025-04-18', true),
('2025-04-01', 5, 'Auditoria interna', 3000.00, 'Serviço', 'Transferência', '2025-04-25', true),
('2025-04-03', 6, 'Manutenção preventiva', 2000.00, 'Serviço', 'Boleto', '2025-04-30', false),
('2025-04-05', 7, 'Desenvolvimento de app', 9500.00, 'Serviço', 'Cartão de Crédito', '2025-05-05', true),
('2025-04-07', 1, 'Salário de abril', 7200.00, 'Salário', 'Transferência', '2025-04-30', false),
('2025-04-10', 2, 'Serviços de limpeza', 1300.00, 'Serviço', 'Cartão de Débito', '2025-04-20', true),
('2025-04-12', 3, 'Venda por assinatura', 6000.00, 'Venda', 'Boleto', '2025-05-01', true),
('2025-04-14', 4, 'Reparo em equipamentos', 2800.00, 'Serviço', 'Transferência', '2025-04-29', false),
('2025-04-16', 5, 'Relatório técnico', 1700.00, 'Serviço', 'Cartão de Crédito', '2025-04-30', true),
('2025-04-18', 6, 'Atualização de licenças', 1900.00, 'Compra', 'Boleto', '2025-05-15', true),
('2025-04-20', 7, 'Implementação de sistema', 8800.00, 'Serviço', 'Transferência', '2025-05-20', true),
('2025-04-22', 1, 'Compra de uniformes', 950.00, 'Compra', 'Cartão de Débito', '2025-05-10', false),
('2025-04-24', 2, 'Treinamento de segurança', 1650.00, 'Serviço', 'Transferência', '2025-05-05', true),
('2025-04-26', 3, 'Venda de DLCs', 7200.00, 'Venda', 'Cartão de Crédito', '2025-05-15', true),
('2025-04-28', 4, 'Reposição de estoque', 2400.00, 'Compra', 'Boleto', '2025-05-12', false);

INSERT INTO funcionarios (nome) VALUES
('Antonio Carlos da Silva Santos'),
('Fabiane Moura de Freitas'),
('Fabiano Teruo Omura'),
('Frederico Maceno Bazzoli'),
('Julia Marion Mendes'),
('Karen Alexandra Marques'),
('Matheus Nunes de Almeida'),
('Mauricio de Souza Gonçalves'),
('Pedro Lucas Gonçaves de Souza'),
('Vinicius Feitoza da Silva'),
('Vitor da Silva Bernardinelli'),
('Vitor Hugo Trindade');

INSERT INTO solicitacoes (data_solicitacao, fk_setor, descricao, quantidade, valor, prazo, status) VALUES
('2025-01-10', 1, 'Solicitação de material de escritório', '10', 150.00, '2025-01-20', 'Pendente'),
('2025-01-12', 2, 'Solicitação de software de automação', '5', 3000.00, '2025-01-25', 'Pendente'),
('2025-01-15', 3, 'Solicitação para produção de novos jogos', '100', 50000.00, '2025-02-15', 'Pendente'),
('2025-01-20', 4, 'Solicitação de manutenção de equipamentos', '2', 1200.00, '2025-02-05', 'Pendente'),
('2025-01-25', 5, 'Solicitação de auditoria de qualidade', '1', 4500.00, '2025-02-10', 'Pendente'),
('2025-02-01', 1, 'Solicitação de treinamento para equipe', '1 ', 2500.00, '2025-02-20', 'Pendente'),
('2025-02-05', 2, 'Solicitação de novos PCs', '10', 8000.00, '2025-02-15', 'Pendente'),
('2025-02-10', 3, 'Solicitação para compra de jogos antigos', '20', 3000.00, '2025-02-25', 'Pendente'),
('2025-02-15', 4, 'Solicitação de novos móveis para escritório', '5', 2000.00, '2025-03-01', 'Pendente'),
('2025-02-20', 5, 'Solicitação de campanha publicitária', '1', 7000.00, '2025-03-10', 'Pendente'),
('2025-02-22', 6, 'Solicitação de licenças de software', '3', 1800.00, '2025-03-05', 'Pendente'),
('2025-02-25', 7, 'Solicitação de consultoria especializada', '1', 4000.00, '2025-03-15', 'Pendente'),
('2025-03-01', 1, 'Solicitação de uniformes para equipe', '20', 1000.00, '2025-03-20', 'Pendente'),
('2025-03-03', 2, 'Solicitação de atualização de sistema', '2', 5000.00, '2025-03-18', 'Pendente'),
('2025-03-05', 3, 'Solicitação de novos controles de jogo', '50', 2500.00, '2025-03-25', 'Pendente'),
('2025-03-07', 4, 'Solicitação de peças de reposição', '15', 1200.00, '2025-03-22', 'Pendente'),
('2025-03-10', 5, 'Solicitação de nova avaliação de qualidade', '1', 3500.00, '2025-03-30', 'Pendente'),
('2025-03-12', 6, 'Solicitação de manutenção de servidor', '1', 2200.00, '2025-04-01', 'Pendente'),
('2025-03-15', 7, 'Solicitação de suporte técnico externo', '2', 3000.00, '2025-04-05', 'Pendente'),
('2025-03-17', 1, 'Solicitação de papelaria', '30', 900.00, '2025-03-27', 'Pendente'),
('2025-03-20', 2, 'Solicitação de firewall corporativo', '1', 5500.00, '2025-04-10', 'Pendente'),
('2025-03-22', 3, 'Solicitação de designer freelancer', '1', 2700.00, '2025-04-12', 'Pendente'),
('2025-03-25', 4, 'Solicitação de cabeamento novo', '100', 1500.00, '2025-04-15', 'Pendente'),
('2025-03-28', 5, 'Solicitação de teste de usabilidade', '1', 2800.00, '2025-04-18', 'Pendente'),
('2025-03-30', 6, 'Solicitação de backup em nuvem', '1', 1900.00, '2025-04-20', 'Pendente'),
('2025-04-01', 7, 'Solicitação de implantação de sistema ERP', '1', 12000.00, '2025-04-25', 'Pendente'),
('2025-04-03', 1, 'Solicitação de cadeira ergonômica', '5', 1250.00, '2025-04-15', 'Pendente'),
('2025-04-05', 2, 'Solicitação de roteadores novos', '4', 1600.00, '2025-04-22', 'Pendente'),
('2025-04-07', 3, 'Solicitação de componentes gráficos', '10', 4500.00, '2025-04-30', 'Pendente'),
('2025-04-10', 4, 'Solicitação de reorganização do layout', '1', 2000.00, '2025-05-01', 'Pendente');

INSERT INTO pagfuncionarios (fk_funcionarios, fk_setor, data_pagto, salario_base, descontos, valor_liquido, status) VALUES
(1, 5, '2025-01-31', 5000.00, 500.00, 4500.00, true),
(2, 1, '2025-01-31', 6000.00, 600.00, 5400.00, true),
(3, 7, '2025-01-31', 7000.00, 700.00, 6300.00, true),
(4, 4, '2025-01-31', 5500.00, 550.00, 4950.00, true),
(5, 1, '2025-01-31', 5800.00, 580.00, 5220.00, true),
(6, 6, '2025-01-31', 5200.00, 520.00, 4680.00, true),
(7, 3, '2025-01-31', 6100.00, 610.00, 5490.00, true),
(8, 7, '2025-01-31', 6700.00, 670.00, 6030.00, true),
(9, 5, '2025-01-31', 5300.00, 530.00, 4770.00, true),
(10, 2, '2025-01-31', 5600.00, 560.00, 5040.00, true),
(11, 2, '2025-01-31', 5800.00, 580.00, 5220.00, true),
(12, 3, '2025-01-31', 5900.00, 590.00, 5310.00, true),
(1, 5, '2025-02-28', 5000.00, 500.00, 4500.00, true),
(2, 1, '2025-02-28', 6000.00, 600.00, 5400.00, true),
(3, 7, '2025-02-28', 7000.00, 700.00, 6300.00, true),
(4, 4, '2025-02-28', 5500.00, 550.00, 4950.00, true),
(5, 1, '2025-02-28', 5800.00, 580.00, 5220.00, true),
(6, 6, '2025-02-28', 5200.00, 520.00, 4680.00, false),
(7, 3, '2025-02-28', 6100.00, 610.00, 5490.00, false),
(8, 7, '2025-02-28', 6700.00, 670.00, 6030.00, false),
(9, 5, '2025-02-28', 5300.00, 530.00, 4770.00, true),
(10, 2, '2025-02-28', 5600.00, 560.00, 5040.00, true),
(11, 2, '2025-02-28', 5800.00, 580.00, 5220.00, true),
(12, 3, '2025-02-28', 5900.00, 590.00, 5310.00, true),
(1, 5, '2025-03-31', 5100.00, 510.00, 4590.00, true),
(2, 1, '2025-03-31', 6100.00, 610.00, 5490.00, true),
(3, 7, '2025-03-31', 7100.00, 710.00, 6390.00, true),
(4, 4, '2025-03-31', 5600.00, 560.00, 5040.00, true),
(5, 1, '2025-03-31', 5900.00, 590.00, 5310.00, true),
(6, 6, '2025-03-31', 5300.00, 530.00, 4770.00, true),
(7, 3, '2025-03-31', 6200.00, 620.00, 5580.00, true),
(8, 7, '2025-03-31', 6800.00, 680.00, 6120.00, true),
(9, 5, '2025-03-31', 5400.00, 540.00, 4860.00, true),
(10, 2, '2025-03-31', 5700.00, 570.00, 5130.00, true),
(11, 2, '2025-03-31', 5900.00, 590.00, 5310.00, true),
(12, 3, '2025-03-31', 6000.00, 600.00, 5400.00, true),
(1, 5, '2025-04-30', 5200.00, 520.00, 4680.00, true),
(2, 1, '2025-04-30', 6200.00, 620.00, 5580.00, true),
(3, 7, '2025-04-30', 7200.00, 720.00, 6480.00, true),
(4, 4, '2025-04-30', 5700.00, 570.00, 5130.00, true),
(5, 1, '2025-04-30', 6000.00, 600.00, 5400.00, true),
(6, 6, '2025-04-30', 5400.00, 540.00, 4860.00, true),
(7, 3, '2025-04-30', 6300.00, 630.00, 5670.00, true),
(8, 7, '2025-04-30', 6900.00, 690.00, 6210.00, true),
(9, 5, '2025-04-30', 5500.00, 550.00, 4950.00, true),
(10, 2, '2025-04-30', 5800.00, 580.00, 5220.00, true),
(11, 2, '2025-04-30', 6000.00, 600.00, 5400.00, true),
(12, 3, '2025-04-30', 6100.00, 610.00, 5490.00, true);

INSERT INTO funcionario_setor (fk_funcionario, fk_setor) VALUES
(1, 5), 
(2, 1), 
(3, 7), 
(4, 4),   
(5, 1),
(6, 6),
(7, 3),
(8, 7),
(9, 5),
(10, 2),
(11, 2),
(12, 3);


-- Caso o banco já exista, vamos usar o existente
USE Industria_db;

-- Tabela de equipamentos com setor como ENUM para restrição
CREATE TABLE equipamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    modelo VARCHAR(100),
    numero_serie VARCHAR(50),
    setor ENUM('Acabamento','Almoxarifado', 'Automação', 'Controle de Qualidade', 'Financeiro', 'Manutenção', 'Produção', 'RH', 'Pintura', 'Montagem', 'Teste', 'Qualidade'),
    data_aquisicao DATE,
    valor_aquisicao DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'disponível',
    manutencao_periodica BOOLEAN DEFAULT FALSE,
    observacoes TEXT
);

-- Tabela de manutenções
CREATE TABLE manutencoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipamento_id INT,
    tipo_manutencao VARCHAR(50),
    data_inicio DATE,
    data_conclusao DATE,
    status VARCHAR(20),
    descricao_servico TEXT,
    FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id)
);

-- Tabela de empréstimos modificada para incluir referência à pessoa
CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipamento_id INT,
    setor_solicitante VARCHAR(50),
    funcionario_cpf VARCHAR(15),
    funcionario_nome VARCHAR(100),
    data_inicio DATE,
    data_devolucao DATE,
    status VARCHAR(20) DEFAULT 'no prazo',
    observacoes TEXT,
    FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id)
);

INSERT INTO equipamentos (
    codigo, nome, categoria, modelo, numero_serie, setor, data_aquisicao, valor_aquisicao, status, manutencao_periodica
) VALUES
-- Corte e estrutura
('EQP-M001', 'Serra de Corte Rápido', 'Ferramenta de Corte', 'SR-4500', 'SC-001-IND', 'Produção', '2024-03-15', 3500.00, 'disponível', TRUE),
('EQP-M002', 'Dobradeira de Chapas Manual', 'Ferramenta de Conformação', 'DC-1000', 'DC-002-IND', 'Produção', '2024-06-10', 2800.00, 'disponível', TRUE),
('EQP-M003', 'Soldadora MIG 220V', 'Solda', 'MIG220', 'SD-003-IND', 'Produção', '2024-11-01', 4200.00, 'disponível', TRUE),

-- Pintura e acabamento
('EQP-M004', 'Cabine de Pintura', 'Pintura Industrial', 'CB-500', 'PT-004-IND', 'Pintura', '2023-09-18', 7800.00, 'disponível', TRUE),
('EQP-M005', 'Compressor de Ar 200L', 'Pneumático', 'CMP-200', 'AR-005-IND', 'Pintura', '2024-10-10', 3900.00, 'disponível', TRUE),

-- Elétrica e eletrônica
('EQP-M006', 'Estação de Solda Digital', 'Eletrônica', 'ESD-202', 'EL-006-IND', 'Montagem', '2025-01-22', 1100.00, 'disponível', TRUE),
('EQP-M007', 'Fonte de Alimentação Ajustável', 'Eletrônica', 'FA-30V5A', 'FA-007-IND', 'Montagem', '2025-02-05', 750.00, 'disponível', FALSE),

-- Montagem e testes
('EQP-M008', 'Bancada de Montagem com Ferramentas', 'Montagem', 'BM-IND', 'BM-008-IND', 'Montagem', '2024-08-14', 2500.00, 'disponível', FALSE),
('EQP-M009', 'Multímetro Digital True RMS', 'Instrumentação', 'MD-600', 'MT-009-IND', 'Qualidade', '2025-04-17', 450.00, 'disponível', FALSE),
('EQP-M010', 'Osciloscópio de 2 Canais', 'Instrumentação', 'OSC-DS1102', 'OS-010-IND', 'Teste', '2024-05-30', 3200.00, 'disponível', FALSE),

-- Ferramentas Elétricas Portáteis
('EQP-F001', 'Furadeira de Bancada 13mm', 'Furadeira', 'FB-13', 'FD-001-PTL', 'Montagem', '2025-02-12', 850.00, 'disponível', TRUE),
('EQP-F002', 'Parafusadeira Bateria 18V', 'Parafusadeira', 'PB-18', 'PS-002-PTL', 'Montagem', '2024-08-05', 680.00, 'disponível', FALSE),
('EQP-F003', 'Retífica Elétrica', 'Acabamento', 'RE-100', 'RT-003-PTL', 'Acabamento', '2024-10-18', 430.00, 'disponível', FALSE),

-- Ferramentas Manuais
('EQP-F004', 'Chave de Fenda Conjunto 6 peças', 'Ferramenta Manual', 'CF-06', 'CF-004-MAN', 'Montagem', '2024-06-22', 120.00, 'disponível', FALSE),
('EQP-F005', 'Chave Philips Conjunto 6 peças', 'Ferramenta Manual', 'CP-06', 'CP-005-MAN', 'Montagem', '2024-06-22', 130.00, 'disponível', FALSE),
('EQP-F006', 'Martelo de Borracha 500g', 'Ferramenta Manual', 'MB-500', 'MT-006-MAN', 'Montagem', '2024-01-10', 60.00, 'disponível', FALSE),
('EQP-F007', 'Alicate Universal 8"', 'Ferramenta Manual', 'AU-8', 'AL-007-MAN', 'Montagem', '2025-03-05', 55.00, 'disponível', FALSE),
('EQP-F008', 'Alicate de Corte Diagonal', 'Ferramenta Manual', 'ACD-6', 'AC-008-MAN', 'Montagem', '2025-03-05', 58.00, 'disponível', FALSE),
('EQP-F009', 'Trena 5 metros', 'Ferramenta de Medição', 'TR-5M', 'TR-009-MAN', 'Produção', '2024-12-01', 35.00, 'disponível', FALSE),
('EQP-F010', 'Esquadro de Aço 12"', 'Ferramenta de Medição', 'ESQ-12', 'ES-010-MAN', 'Produção', '2024-12-01', 48.00, 'disponível', FALSE);

