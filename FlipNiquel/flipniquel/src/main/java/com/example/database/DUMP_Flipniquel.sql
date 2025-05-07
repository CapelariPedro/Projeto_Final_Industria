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
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Mariana Silva Souza', '22-07-1995', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Londrina-PR',
'98765432101', 'PR12345678', 'Rua das Flores, 150 - Londrina - PR', '(43) 99111-2222', 'mariana.silva@email.com', 'José Silva e Ana Souza', 'A+', '(43) 99333-4444');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Técnico em Automação', 'Automação', 'Manutenção e programação de sistemas automatizados', 'Sensores, Atuadores', '10-03-2023', '4500.00', 'Banco Itaú - Ag. 0123 - CC 98765-4', 'Vale Transporte, Plano de Saúde', 'Técnico Completo', '1234567', '10987654321', 'CLT', '08:00 - 18:00', '', '', '98765432101');

-- Insert 2
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Carlos Alberto Oliveira', '05-11-1988', 'Masculino', 'Casado(a)', 'Fernanda Pereira Oliveira', 'Lucas Oliveira', 'Brasil', 'Maringá-PR',
'12345678902', 'PR87654321', 'Avenida Paraná, 300 - Maringá - PR', '(44) 99888-7777', 'carlos.alberto@email.com', 'Antônio Oliveira e Maria Oliveira', 'B-', '(44) 99666-5555');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Auxiliar de Estoque', 'Estoque', 'Organização e controle de materiais', 'Empilhadeira manual', '15-01-2024', '2100.00', 'Banco do Brasil - Ag. 4567 - CC 12345-6', 'Vale Transporte, Vale Alimentação', 'Médio Completo', '8765432', '21098765432', 'CLT', '07:00 - 17:00', '', '', '12345678902');

-- Insert 3
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Ana Paula Santos', '18-04-1992', 'Feminino', 'Divorciado(a)', NULL, 'Pedro Santos', 'Brasil', 'Curitiba-PR',
'34567890103', 'PR23456789', 'Rua XV de Novembro, 800 - Curitiba - PR', '(41) 99777-6666', 'ana.paula@email.com', 'Ricardo Santos e Cláudia Santos', 'O+', '(41) 99555-4444');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista Financeiro Júnior', 'Financeiro', 'Contas a pagar, contas a receber, fluxo de caixa', 'Planilhas eletrônicas', '01-08-2024', '3800.00', 'Caixa Econômica - Ag. 7890 - CC 65432-1', 'Vale Transporte, Plano de Saúde', 'Graduação Completa (Bacharelado)', '3456789', '32109876543', 'CLT', '08:00 - 18:00', '', '', '34567890103');

-- Insert 4
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('João Pedro Almeida', '10-12-1999', 'Masculino', 'Solteiro(a)', NULL, NULL, 'Argentina', 'Buenos Aires',
'45678901204', 'AR98765432', 'Avenida San Martín, 123 - Buenos Aires', '+54 11 1234-5678', 'joao.almeida@email.com', 'Pablo Almeida e Sofia Gomez', 'AB-', '+54 11 9876-5432');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Auxiliar de Manutenção', 'Manutenção', 'Suporte na manutenção preventiva e corretiva', 'Ferramentas manuais', '01-05-2025', '1900.00', 'Banco Santander - Ag. 1122 - CC 34567-8', 'Vale Transporte', 'Médio Incompleto', '4567890', '43210987654', 'Temporário', '08:00 - 17:00', '', '', '45678901204');

-- Insert 5
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Fernanda Oliveira Costa', '01-06-1985', 'Feminino', 'Casado(a)', 'Ricardo Costa', 'Mariana Costa', 'Brasil', 'Londrina-PR',
'56789012305', 'PR34567890', 'Rua Espírito Santo, 400 - Londrina - PR', '(43) 99666-5555', 'fernanda.costa@email.com', 'Manuel Oliveira e Carmen Costa', 'O-', '(43) 99444-3333');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Operador de Produção', 'Produção', 'Operação de máquinas na linha de produção', 'Esteira, Máquinas CNC', '20-09-2022', '2800.00', 'Banco Bradesco - Ag. 7890 - CC 98765-0', 'Vale Transporte, Vale Alimentação', 'Médio Completo', '5678901', '54321098765', 'CLT', '14:00 - 22:00', '', '', '56789012305');

-- Insert 6
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Eduardo Lima Pereira', '15-09-1990', 'Masculino', 'Casado(a)', 'Juliana Lima Pereira', 'Lucas Pereira', 'Brasil', 'São Paulo-SP',
'67890123406', 'SP12345678', 'Rua das Palmeiras, 230 - São Paulo - SP', '(11) 98765-4321', 'eduardo.pereira@email.com', 'Carlos Pereira e Regina Lima', 'B+', '(11) 99876-5432');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Engenheiro de Produção', 'Engenharia', 'Desenvolvimento de processos e melhoria contínua', 'Softwares CAD, Planilhas', '10-02-2022', '7200.00', 'Banco Inter - Ag. 0001 - CC 12345-9', 'Plano de Saúde, PLR', 'Pós-graduação', '6789012', '65432109876', 'CLT', '09:00 - 18:00', '', '', '67890123406');

-- Insert 7
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Luciana Rocha Mendes', '30-03-1993', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Belo Horizonte-MG', '78901234507', 'MG87654321', 'Rua Ouro Preto, 456 - Belo Horizonte - MG', '(31) 91234-5678', 'luciana.mendes@email.com', 'Paulo Mendes e Teresa Rocha', 'AB+', '(31) 93456-7890');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Assistente Administrativo', 'Administrativo', 'Atendimento, organização de documentos', 'Computador, Impressora', '15-04-2023', '2900.00', 'Banco Original - Ag. 0023 - CC 99887-1', 'Vale Transporte, Auxílio Refeição', 'Superior Incompleto', '7890123', '76543210987', 'CLT', '08:00 - 17:00', '', '', '78901234507');

-- Insert 8
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Rafael Teixeira Gomes', '12-07-1987', 'Masculino', 'Casado(a)', 'Marta Gomes', 'João Gomes', 'Brasil', 'Fortaleza-CE', '89012345608', 'CE76543210', 'Rua das Acácias, 102 - Fortaleza - CE', '(85) 99988-7766', 'rafael.gomes@email.com', 'João Gomes e Luciana Teixeira', 'A-', '(85) 98877-6655');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Supervisor de Logística', 'Logística', 'Supervisão de equipe e controle de estoque', 'Computador, Paleteira', '05-06-2021', '5200.00', 'Banco do Nordeste - Ag. 3321 - CC 54321-0', 'Vale Transporte, Plano Odontológico', 'Graduação Completa (Tecnólogo)', '8901234', '87654321098', 'CLT', '09:00 - 18:00', '', '', '89012345608');

-- Insert 9
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Juliana Borges Silva', '23-08-1996', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Florianópolis-SC', '90123456709', 'SC45678901', 'Rua das Laranjeiras, 78 - Florianópolis - SC', '(48) 98899-1234', 'juliana.silva@email.com', 'João Silva e Maria Borges', 'B+', '(48) 98765-4321');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Técnico de Qualidade', 'Qualidade', 'Controle de qualidade e auditorias', 'Software ERP', '02-03-2024', '3700.00', 'Banco Safra - Ag. 4455 - CC 12398-7', 'Vale Transporte, Seguro de Vida', 'Técnico Completo', '9012345', '98765432109', 'CLT', '07:30 - 16:30', '', '', '90123456709');

-- Insert 10
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Thiago Martins Costa', '07-05-1991', 'Masculino', 'Casado(a)', 'Patrícia Costa', 'Sofia Costa', 'Brasil', 'Salvador-BA', '01234567810', 'BA34567890', 'Avenida Atlântica, 300 - Salvador - BA', '(71) 99888-4455', 'thiago.costa@email.com', 'Carlos Costa e Marina Martins', 'O+', '(71) 99666-7788');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista de RH', 'Recursos Humanos', 'Recrutamento, seleção e treinamento', 'Computador', '01-12-2023', '4800.00', 'Banco Itaú - Ag. 5678 - CC 12345-6', 'Vale Transporte, Vale Refeição', 'Graduação Completa (Bacharelado)', '0123456', '12345678901', 'CLT', '08:30 - 17:30', '', '', '01234567810');

-- Insert 11
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Beatriz Ramos Ferreira', '20-11-1994', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Natal-RN', '12345678911', 'RN12345678', 'Rua das Flores, 321 - Natal - RN', '(84) 98877-6655', 'beatriz.ferreira@email.com', 'Roberto Ferreira e Carla Ramos', 'A+', '(84) 98765-4433');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Auxiliar Administrativo', 'Administrativo', 'Organização de arquivos e apoio em tarefas de escritório', 'Computador, Scanner', '10-10-2022', '2600.00', 'Banco do Brasil - Ag. 1234 - CC 56789-0', 'Vale Transporte', 'Médio Completo', '1234567', '23456789012', 'CLT', '08:00 - 17:00', '', '', '12345678911');

-- Insert 12
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Carlos Eduardo Nunes', '14-03-1989', 'Masculino', 'Casado(a)', 'Daniela Nunes', 'Gabriel Nunes', 'Brasil', 'Joinville-SC', '23456789012', 'SC65432109', 'Rua Blumenau, 789 - Joinville - SC', '(47) 99988-7766', 'carlos.nunes@email.com', 'Eduardo Nunes e Sandra Nunes', 'B-', '(47) 99887-6655');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Mecânico Industrial', 'Manutenção', 'Reparo e manutenção de máquinas industriais', 'Torno, Furadeira', '05-11-2021', '4300.00', 'Banco Itaú - Ag. 5678 - CC 45678-9', 'Vale Transporte, Vale Alimentação', 'Técnico Completo', '2345678', '34567890123', 'CLT', '07:00 - 16:00', '', '', '23456789012');

-- Insert 13
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Vanessa Lima Souza', '08-01-1997', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Recife-PE', '34567890113', 'PE98765432', 'Rua Maracatu, 111 - Recife - PE', '(81) 99888-1122', 'vanessa.souza@email.com', 'Marcelo Souza e Luciana Lima', 'AB-', '(81) 99777-1133');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista de Logística', 'Logística', 'Controle de transporte e estoque', 'Computador, ERP', '20-01-2023', '4100.00', 'Banco Bradesco - Ag. 2345 - CC 23456-7', 'Plano de Saúde, Vale Transporte', 'Graduação Completa (Bacharelado)', '3456789', '45678901234', 'CLT', '08:00 - 17:00', '', '', '34567890113');

-- Insert 14 - dadospessoais
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Pedro Henrique Silva', '25-06-1983', 'Masculino', 'Casado(a)', 'Juliana Silva', 'Laura Silva', 'Brasil', 'Campinas-SP', '45678901214', 'SP34567891', 'Rua das Camélias, 202 - Campinas - SP', '(19) 99977-2233', 'pedro.silva@email.com', 'Henrique Silva e Marta Silva', 'O-', '(19) 99888-3344');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Engenheiro de Segurança', 'Engenharia', 'Avaliação e prevenção de riscos', 'Software de segurança', '01-07-2020', '8500.00', 'Banco Santander - Ag. 6789 - CC 12340-9', 'Plano de Saúde, Vale Transporte', 'Superior Completo', '98765432101', '10987654321', 'CLT', '08:00 - 18:00', '', '', '45678901214');

-- Insert 15
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Aline Pereira dos Santos', '15-09-1992', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Curitiba-PR', '56789012315', 'PR56789012', 'Avenida do Contorno, 456 - Curitiba - PR', '(41) 99876-5544', 'aline.santos@email.com', 'João Santos e Paula Pereira', 'O+', '(41) 98765-4433');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Coordenadora de Marketing', 'Marketing', 'Planejamento de campanhas e análise de mercado', 'Software de design', '01-08-2022', '6500.00', 'Banco Bradesco - Ag. 4321 - CC 87654-3', 'Vale Transporte, Auxílio Refeição', 'Graduação Completa', '5678901', '67890123456', 'CLT', '09:00 - 18:00', '', '', '56789012315');

-- Insert 16
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Maurício Andrade Silva', '11-04-1984', 'Masculino', 'Casado(a)', 'Renata Silva', 'Lucas Silva', 'Brasil', 'Vitória-ES', '67890123416', 'ES67890123', 'Rua dos Coqueiros, 789 - Vitória - ES', '(27) 99887-6655', 'mauricio.silva@email.com', 'Carlos Silva e Maria Andrade', 'AB+', '(27) 98765-4321');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Gerente de TI', 'Tecnologia', 'Gestão de equipe de TI e implementação de sistemas', 'Servidores', '10-05-2020', '10500.00', 'Banco Santander - Ag. 1342 - CC 98765-4', 'Plano de Saúde, Auxílio Educação', 'Graduação Completa (TI)', '6789012', '78901234567', 'CLT', '08:30 - 17:30', '', '', '67890123416');

-- Insert 17
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Sofia Almeida Martins', '28-06-1995', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Porto Alegre-RS', '78901234517', 'RS78901234', 'Rua do Sol, 123 - Porto Alegre - RS', '(51) 98765-4321', 'sofia.martins@email.com', 'Miguel Martins e Lucia Almeida', 'A-', '(51) 98877-6655');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Analista de Sistemas', 'TI', 'Desenvolvimento e suporte de sistemas internos', 'Servidores', '03-07-2021', '4300.00', 'Banco do Brasil - Ag. 2153 - CC 45678-9', 'Vale Transporte, Vale Alimentação', 'Graduação Completa (Sistemas de Informação)', '7890123', '89012345678', 'CLT', '09:00 - 18:00', '', '', '78901234517');

-- Insert 18
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Gustavo Costa Oliveira', '22-02-1987', 'Masculino', 'Casado(a)', 'Patrícia Oliveira', 'Livia Oliveira', 'Brasil', 'Rio de Janeiro-RJ', '89012345618', 'RJ89012345', 'Rua do Catete, 234 - Rio de Janeiro - RJ', '(21) 99988-7766', 'gustavo.oliveira@email.com', 'José Oliveira e Marta Costa', 'B+', '(21) 99777-6655');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Consultor de Vendas', 'Vendas', 'Prospectar clientes e fechar contratos', 'Computador, CRM', '01-02-2021', '3800.00', 'Banco Caixa Econômica - Ag. 1020 - CC 12345-0', 'Vale Transporte, Comissões', 'Graduação Completa (Administração)', '8901234', '90123456789', 'CLT', '09:00 - 18:00', '', '', '89012345618');

-- Insert 19
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Tatiane Lopes de Souza', '13-12-1990', 'Feminino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Aracaju-SE', '90123456720', 'SE90123456', 'Rua dos Lírios, 678 - Aracaju - SE', '(79) 99888-1122', 'tatiane.souza@email.com', 'José Souza e Ana Lopes', 'O-', '(79) 98765-4433');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Assistente de Recursos Humanos', 'Recursos Humanos', 'Apoio em recrutamento e organização de documentos', 'Impressora', '22-03-2023', '3100.00', 'Banco Santander - Ag. 1342 - CC 98765-5', 'Vale Transporte, Seguro de Vida', 'Ensino Superior Incompleto', '9012345', '01234567890', 'CLT', '08:00 - 17:00', '', '', '90123456720');

-- Insert 20
INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia)
VALUES ('Felipe Silva Gomes', '07-10-1993', 'Masculino', 'Solteiro(a)', NULL, NULL, 'Brasil', 'Salvador-BA', '12345678921', 'BA12345678', 'Rua do Campo, 345 - Salvador - BA', '(71) 99876-3344', 'felipe.gomes@email.com', 'Roberto Gomes e Silvia Silva', 'A+', '(71) 98877-2244');

INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais)
VALUES ('Engenheiro de Produção', 'Produção', 'Análise e controle de processos industriais', 'Computador', '15-05-2021', '8500.00', 'Banco do Brasil - Ag. 2345 - CC 65432-1', 'Plano de Saúde, Vale Alimentação', 'Graduação Completa (Engenharia)', '1234567', '23456789012', 'CLT', '08:00 - 17:00', '', '', '12345678921');


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
('vitor', '010123')
    
CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    setor VARCHAR(50) NOT NULL
);
INSERT INTO funcionario(nome, setor) 
VALUES
('Ana Souza', 'Produção'),
('Carlos Lima', 'Manutenção'),
('João Silva', 'Logística'),
('Maria Oliveira', 'Administrativo'),
('Fernanda Costa', 'Recursos Humanos'),
('Pedro Santos', 'Produção'),
('Lucas Pereira', 'Manutenção'),
('Juliana Almeida', 'Logística'),
('Roberta Martins', 'Administrativo'),
('Gabriel Rocha', 'Recursos Humanos'),
('Patrícia Ferreira', 'Produção'),
('Daniel Souza', 'Manutenção'),
('Fernanda Lima', 'Logística'),
('Rafael Oliveira', 'Administrativo'),
('Tatiane Costa', 'Recursos Humanos'),
('Renato Martins', 'Produção'),
('Carla Pereira', 'Manutenção'),
('Vinícius Santos', 'Logística'),
('Letícia Almeida', 'Administrativo'),
('Marcelo Rocha', 'Recursos Humanos'),

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
INSERT INTO produto(nome, lote, sku)
VALUES
('Fresadora CNC 5000', 'Lote001', 'SKU001'),
('Torno Mecânico Precision 3000', 'Lote002', 'SKU002'),
('Prensa Hidráulica Model 100', 'Lote003', 'SKU003'),
('Serra de Fita Industrial X200', 'Lote004', 'SKU004'),
('Moinho de Martelo Turbo', 'Lote005', 'SKU005'),
('Compressor de Ar Industrial 150', 'Lote006', 'SKU006'),
('Soldadora MIG 250', 'Lote007', 'SKU007'),
('Bomba Centrífuga HeavyDuty', 'Lote008', 'SKU008'),
('Escavadeira Hidráulica ProMax 400', 'Lote009', 'SKU009'),
('Elevador de Carga ElectroLift', 'Lote010', 'SKU010'),
('Injetora de Plástico Mod. 500', 'Lote011', 'SKU011'),
('Cortadora Laser ProCut 200', 'Lote012', 'SKU012'),
('Máquina de Corte a Plasma Xtreme', 'Lote013', 'SKU013'),
('Furadeira Radial HeavyPro', 'Lote014', 'SKU014'),
('Prensa Pneumática SpeedPress', 'Lote015', 'SKU015'),
('Máquina de Solda TIG Master 200', 'Lote016', 'SKU016'),
('Misturador de Ração AgroMix', 'Lote017', 'SKU017'),
('Linha de Produção Modular MegaLine', 'Lote018', 'SKU018'),
('Despolpadora Industrial FruitPro', 'Lote019', 'SKU019'),
('Extrusora de Alumínio UltraPress', 'Lote020', 'SKU020'),

CREATE TABLE producao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    funcionario VARCHAR(100),
    maquina VARCHAR(100),
    produto VARCHAR(100),
    quantidade INT NOT NULL,
    data_producao DATETIME DEFAULT CURRENT_TIMESTAMP,
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

