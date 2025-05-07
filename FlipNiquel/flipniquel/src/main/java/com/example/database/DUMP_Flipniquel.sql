CREATE DATABASE Industria_db;
USE Industria_db;


CREATE TABLE usuarioRH(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

INSERT INTO usuarioRH(usuario, senha) VALUES ('julia', '123');

CREATE TABLE `dadospessoais` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_completo` VARCHAR(45) NOT NULL,
  `data_nascimento` VARCHAR(15) NOT NULL,
  `sexo` CHAR(20) NOT NULL,
  `estado_civil` VARCHAR(15) NULL,
  `conjuge` VARCHAR(45) NULL,
  `dependentes` VARCHAR(150) NULL,
  `nacionalidade` VARCHAR(45) NULL,
  `naturalidade` VARCHAR(45) NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `rg` VARCHAR(15) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NULL,
  `filiacao` VARCHAR(150) NULL,
  `tipo_sanguineo` VARCHAR(3) NOT NULL,
  `contato_emergencia` VARCHAR(20) NULL,
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
  `admissao` VARCHAR(15) NOT NULL,
  `salario` VARCHAR(45) NOT NULL,
  `dadosbancarios` VARCHAR(45) NOT NULL,
  `beneficios` VARCHAR(45) NULL,
  `escolaridade` VARCHAR(45) NULL,
  `ctps` VARCHAR(45) NOT NULL,
  `pisPasep` VARCHAR(45) NOT NULL,
  `contrato` VARCHAR(45) NOT NULL,
  `horario` VARCHAR(45) NOT NULL,
  `acidentes` VARCHAR(200) NOT NULL,
  `advertencias` VARCHAR(200) NOT NULL,
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

-- Insert 1
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade,
cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Mariana Silva Souza', '1995-07-22', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Londrina-PR',
'98765432101', 'PR12345678', 'Rua das Flores, 150 - Londrina - PR', '(43) 99111-2222', 'mariana.silva@email.com', 'José Silva e Ana Souza', 'A+', '(43) 99333-4444');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Técnico em Automação', 'Automação', 'Manutenção e programação de sistemas automatizados', 'CLP, Sensores, Atuadores', '2023-03-10', '4500.00', 'Banco Itaú - Ag. 0123 - CC 98765-4', 'Vale Transporte, Plano de Saúde', 'Técnico Completo', '1234567', '10987654321', 'CLT', '08:00 - 18:00', '', '', '98765432101');

-- Insert 2
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade,
cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Carlos Alberto Oliveira', '1988-11-05', 'Masculino', 'Casado(a)', 'Fernanda Pereira Oliveira', 'Lucas Oliveira', 'Brasil', 'Maringá-PR',
'12345678902', 'PR87654321', 'Avenida Paraná, 300 - Maringá - PR', '(44) 99888-7777', 'carlos.alberto@email.com', 'Antônio Oliveira e Maria Oliveira', 'B-', '(44) 99666-5555');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Auxiliar de Estoque', 'Estoque', 'Organização e controle de materiais', 'Empilhadeira manual, Computador', '2024-01-15', '2100.00', 'Banco do Brasil - Ag. 4567 - CC 12345-6', 'Vale Transporte, Vale Alimentação', 'Médio Completo', '8765432', '21098765432', 'CLT', '07:00 - 17:00', '', '', '12345678902');

-- Insert 3
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade,
cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Ana Paula Santos', '1992-04-18', 'Feminino', 'Divorciado(a)', NULL, 'Pedro Santos', 'Brasil', 'Curitiba-PR',
'34567890103', 'PR23456789', 'Rua XV de Novembro, 800 - Curitiba - PR', '(41) 99777-6666', 'ana.paula@email.com', 'Ricardo Santos e Cláudia Santos', 'O+', '(41) 99555-4444');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista Financeiro Júnior', 'Financeiro', 'Contas a pagar, contas a receber, fluxo de caixa', 'Computador, Planilhas eletrônicas', '2024-08-01', '3800.00', 'Caixa Econômica - Ag. 7890 - CC 65432-1', 'Vale Transporte, Plano de Saúde', 'Graduação Completa (Bacharelado)', '3456789', '32109876543', 'CLT', '08:00 - 18:00', '', '', '34567890103');

-- Insert 4
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade,
cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('João Pedro Almeida', '1999-12-10', 'Masculino', 'Solteiro(a)', NULL, NULL, 'Argentina', 'Buenos Aires',
'45678901204', 'AR98765432', 'Avenida San Martín, 123 - Buenos Aires', '+54 11 1234-5678', 'joao.almeida@email.com', 'Pablo Almeida e Sofia Gomez', 'AB-', '+54 11 9876-5432');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Auxiliar de Manutenção', 'Manutenção', 'Suporte na manutenção preventiva e corretiva', 'Ferramentas manuais, Computador', '2025-05-01', '1900.00', 'Banco Santander - Ag. 1122 - CC 34567-8', 'Vale Transporte', 'Médio Incompleto', '4567890', '43210987654', 'Temporário', '08:00 - 17:00', '', '', '45678901204');

-- Insert 5
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade,
cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Fernanda Oliveira Costa', '1985-06-01', 'Feminino', 'Casado(a)', 'Ricardo Costa', 'Mariana Costa', 'Brasil', 'Londrina-PR',
'56789012305', 'PR34567890', 'Rua Espírito Santo, 400 - Londrina - PR', '(43) 99666-5555', 'fernanda.costa@email.com', 'Manuel Oliveira e Carmen Costa', 'O-', '(43) 99444-3333');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Operador de Produção', 'Produção', 'Operação de máquinas na linha de produção', 'Esteira, Máquinas CNC', '2022-09-20', '2800.00', 'Banco Bradesco - Ag. 7890 - CC 98765-0', 'Vale Transporte, Vale Alimentação', 'Médio Completo', '5678901', '54321098765', 'CLT', '14:00 - 22:00', '', '', '56789012305');




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
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE producao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    funcionario_id INT,
    maquina_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    data_producao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

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



-- Tabela de equipamentos
CREATE TABLE equipamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    modelo VARCHAR(100),
    numero_serie VARCHAR(50),
    setor VARCHAR(50),
    data_aquisicao DATE,
    valor_aquisicao DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'disponível',
    manutencao_periodica BOOLEAN DEFAULT FALSE
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

-- Tabela de empréstimos
CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipamento_id INT,
    setor_solicitante VARCHAR(50),
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
