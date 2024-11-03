-- Insira os produtos
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Camiseta Básica', 'CAMISETA', 'Preto', 'M', 'https://abrir.link/CrhyN', 'Algodão', 'Renner', 49.90, 'LISO', 'VERAO', SYSDATE);
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Calça Jeans Skinny', 'CALCA', 'Azul', 'P', 'https://abrir.link/MAMZF', 'Denim', 'Levis', 89.90, 'LISO', 'VERAO', SYSDATE);
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Casaco de Lã', 'CASACO', 'Bege', 'G', 'https://abrir.link/tSwCQ', 'Lã', 'Shein', 199.90, 'LISO', 'INVERNO', SYSDATE);
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Calça de Moletom', 'CALCA', 'Cinza', 'M', 'https://abrir.link/rMAON', 'Algodão', 'Puma', 79.90, 'LISO', 'INVERNO', SYSDATE);

-- Insira os lookbooks
INSERT INTO lookbook (nome, estilo, tendencia, data_criacao) VALUES ('Look Casual Verão', 'CASUAL', 'STREETWEAR', SYSDATE);
INSERT INTO lookbook (nome, estilo, tendencia, data_criacao) VALUES ('Look Elegante Inverno', 'ELEGANTE', 'VINTAGE', SYSDATE);

-- Insira os relacionamentos
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (1, 1);
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (1, 2);
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (2, 3);
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (2, 4);