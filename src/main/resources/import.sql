-- Insira os produtos
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Camiseta Básica', 'CAMISETA', 'Preto', 'M', 'https://abrir.link/LtUfW', 'Algodão', 'H&M', 49.90, 'LISO', 'VERAO', '2024-01-15');
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Calça Jeans Skinny', 'CALCA', 'Azul', 'M', 'https://abrir.link/IEjTC', 'Denim', 'Levis', 89.90, 'LISO', 'VERAO', '2024-02-20');
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Casaco de Lã', 'CASACO', 'Bege', 'M', 'https://abrir.link/tSwCQ', 'Lã', 'Shein', 199.90, 'LISO', 'INVERNO', '2024-03-10');
INSERT INTO product (nome, categoria, cor, tamanho, imagem_url, material, marca, preco, design, sazonalidade, data_cadastro) VALUES ('Calça de Moletom', 'CALCA', 'Cinza', 'M', 'https://abrir.link/rMAON', 'Algodão', 'Puma', 79.90, 'LISO', 'INVERNO', '2024-04-05');

-- Insira os lookbooks
INSERT INTO lookbook (nome, estilo, tendencia) VALUES ('Lookbook Casual Verão', 'CASUAL', 'STREETWEAR');
INSERT INTO lookbook (nome, estilo, tendencia) VALUES ('Lookbook Elegante Inverno', 'ELEGANTE', 'VINTAGE');

-- Insira os relacionamentos
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (1, 1); -- Lookbook Casual Verão com Camiseta Básica
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (1, 2); -- Lookbook Casual Verão com Calça Jeans Skinny
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (2, 3); -- Lookbook Elegante Inverno com Casaco de Lã
INSERT INTO lookbook_product (lookbook_id, product_id) VALUES (2, 4); -- Lookbook Elegante Inverno com Calça de Moletom
