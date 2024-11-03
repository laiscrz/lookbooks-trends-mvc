/*
Empresa LeadTech

Banco de dados relacionado ao aplicativo Java - LookBooks Trends MVC

Integrantes do Grupo:
- Bianca Leticia Roman Caldeira - RM552267 - Turma : 2TDSPH
- Charlene Aparecida Estevam Mendes Fialho - RM552252 - Turma : 2TDSPH
- Lais Alves Da Silva Cruz - RM552258 - Turma : 2TDSPH
- Fabrico Torres Antonio - RM97916 - Turma : 2TDSPH
- Lucca Raphael Pereira dos Santos - RM99675 - Turma : 2TDSPZ 

------------------ 
Este script contem as instrucoes DDL e os comandos necessarios para o deploy do banco de dados com pipelines no Azure. 
------------------ 
*/

/* 
   Remocao das tabelas existentes para evitar conflitos com a criacao das novas tabelas. 
   O uso de 'CASCADE CONSTRAINTS' garante que todas as restricoes e dependencias associadas sejam removidas.
*/
DROP TABLE lookbook_product CASCADE CONSTRAINTS;
DROP TABLE lookbook CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;

/* 
   DDL para tabela 'product' - Armazena informacoes detalhadas sobre os produtos de vestuario.
   A tabela inclui:
   - ID do produto (gerado automaticamente)
   - Data de cadastro do produto
   - Preco do produto
   - Cor do produto
   - URL da imagem do produto
   - Marca do produto
   - Material do produto
   - Nome do produto
   - Categoria, design, sazonalidade e tamanho do produto, com restricoes CHECK para validacao dos valores permitidos.
*/
CREATE TABLE product (
    id NUMBER(19, 0) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    data_cadastro DATE DEFAULT SYSDATE,
    preco FLOAT(53) NOT NULL,
    cor VARCHAR2(255 CHAR) NOT NULL,
    imagem_url VARCHAR2(255 CHAR),
    marca VARCHAR2(255 CHAR) NOT NULL,
    material VARCHAR2(255 CHAR) NOT NULL,
    nome VARCHAR2(255 CHAR) NOT NULL,
    categoria VARCHAR2(20 CHAR)NOT NULL CHECK (categoria IN ('CALCA', 'CAMISA', 'CAMISETA', 'CASACO', 'CONJUNTO', 'JAQUETA', 'JAQUETA_CASACO', 'SAIA', 'SHORTS', 'SUETER', 'VESTIDO')),
    design VARCHAR2(20 CHAR) NOT NULL CHECK (design IN ('ABSTRATO', 'ANIMAL_PRINT', 'ESTAMPADO', 'FLORAL', 'GEOMETRICO', 'LISO', 'LISTRADO', 'POA', 'XADREZ')),
    sazonalidade VARCHAR2(20 CHAR) NOT NULL CHECK (sazonalidade IN ('INVERNO', 'OUTONO', 'PRIMAVERA', 'VERAO')),
    tamanho VARCHAR2(255 CHAR) NOT NULL CHECK (tamanho IN ('PP', 'P', 'M', 'G', 'GG', 'XG'))
);

/* 
   DDL para tabela 'lookbook' - Armazena informacoes sobre colecoes de looks, permitindo organizar produtos em estilos especificos.
   A tabela inclui:
   - ID do lookbook (gerado automaticamente)
   - Data de criacao do lookbook
   - Nome do lookbook
   - Estilo e tendencia do lookbook, validados com restricoes CHECK para garantir que apenas valores permitidos sejam inseridos.
*/
CREATE TABLE lookbook (
    id NUMBER(19, 0) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    data_criacao DATE DEFAULT SYSDATE,
    nome VARCHAR2(255 CHAR) NOT NULL,
    estilo VARCHAR2(20 CHAR) CHECK (estilo IN ('ARTE', 'BOHO', 'CASUAL', 'CHIQUE', 'CLASSICO', 'ELEGANTE', 'ESPORTIVO', 'GOTICO', 'MODERNO')),
    tendencia VARCHAR2(20 CHAR) CHECK (tendencia IN ('FEMININO', 'MASCULINO', 'MAXIMALISTA', 'MINIMALISTA', 'NEON', 'RETRO', 'STREETWEAR', 'SUSTENTABILIDADE', 'UNISSEX', 'VINTAGE'))
);

/* 
   DDL para tabela associativa 'lookbook_product' - Facilita a relacao de muitos para muitos entre lookbooks e produtos.
   Cada entrada nesta tabela associa um lookbook a um produto, garantindo que as referencias de integridade sejam mantidas.
   A tabela inclui:
   - ID do lookbook associado
   - ID do produto associado
   As referencias sao validadas como chaves estrangeiras.
*/
CREATE TABLE lookbook_product (
    lookbook_id NUMBER(19, 0) NOT NULL,
    product_id NUMBER(19, 0) NOT NULL,
    FOREIGN KEY (lookbook_id) REFERENCES lookbook(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- INSERCOES NAS TABELAS
-- Insere os produtos na tabela 'product'
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade) VALUES ('Camiseta Basica', 'CAMISETA', 'Preto', 'M', 'https://abrir.link/CrhyN', 'Algodao', 'Renner', 49.90, 'LISO', 'VERAO');
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade) VALUES ('Calca Jeans Skinny', 'CALCA', 'Azul', 'P', 'https://abrir.link/MAMZF', 'Denim', 'Levis', 89.90, 'LISO', 'VERAO');
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade) VALUES ('Casaco de La', 'CASACO', 'Bege', 'G', 'https://abrir.link/tSwCQ', 'La', 'Shein', 199.90, 'LISO', 'INVERNO');
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade) VALUES ('Calca de Moletom', 'CALCA', 'Cinza', 'M', 'https://abrir.link/rMAON', 'Algodao', 'Puma', 79.90, 'LISO', 'INVERNO');

-- Insere os lookbooks na tabela 'lookbook'
INSERT INTO lookbook (nome, estilo, tendencia) VALUES ('Look Casual Verao', 'CASUAL', 'STREETWEAR');
INSERT INTO lookbook (nome, estilo, tendencia) VALUES ('Look Elegante Inverno', 'ELEGANTE', 'VINTAGE');

-- Insere os relacionamentos na tabela 'lookbook_product' para associar produtos aos lookbooks
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (1, 1); -- Lookbook Casual Verao com Camiseta Basica
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (1, 2); -- Lookbook Casual Verao com Calca Jeans Skinny
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (2, 3); -- Lookbook Elegante Inverno com Casaco de La
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (2, 4); -- Lookbook Elegante Inverno com Calca de Moletom

-- Confirmando todas as alteracoes realizadas nas insercoes
COMMIT;

-- Consultando todos os dados nas tabelas
SELECT * FROM product;
SELECT * FROM lookbook;
SELECT * FROM lookbook_product;

