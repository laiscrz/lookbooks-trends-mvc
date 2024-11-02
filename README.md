# 🎨 Lookbook Trend : Gerencie seus Looks

**Lookbook Trend** é uma aplicação inovadora que permite aos usuários explorar e gerenciar looks de moda, tudo enquanto segue as últimas tendências do mercado! 🌟

## 🚀 Funcionalidades

- **Exploração de Looks**: Navegue por uma coleção diversificada de looks inspiradores. 👗👖
- **Recomendações Personalizadas**: Receba sugestões de looks com base nas suas preferências. 🎉
- **Experiência de Usuário Intuitiva**: Navegação fácil e design responsivo para todos os dispositivos. 📱💻

---

## 📚 Estrutura do Banco de Dados

A aplicação utiliza um modelo de banco de dados relacional para gerenciar as informações dos produtos, lookbooks e suas interações. Abaixo está o diagrama de Entidade e Relacionamento com as tabelas principais da estrutura do banco de dados:

```mermaid
erDiagram
    PRODUCT {
        Long id PK "Identificador único"
        Date data_cadastro "Data de cadastro"
        Float preco "Preço do produto"
        String cor "Cor do produto"
        String imagem_url "URL da imagem do produto"
        String marca "Marca do produto"
        String material "Material do produto"
        String nome "Nome do produto"
        Categoria categoria "Categoria do produto (enum)"
        Design design "Design do produto (enum)"
        Sazonalidade sazonalidade "Sazonalidade do produto (enum)"
        Tamanho tamanho "Tamanho do produto (enum)"
    }

    LOOKBOOK {
        Long id PK "Identificador único"
        String nome "Nome do lookbook"
        Date data_criacao "Data de criação do lookbook"
    }

    LOOKBOOK_PRODUCT {
        Long lookbook_id FK "Referência ao Lookbook"
        Long product_id FK "Referência ao Produto"
    }

    PRODUCT ||--o{ LOOKBOOK_PRODUCT : "contém"
    LOOKBOOK ||--o{ LOOKBOOK_PRODUCT : "inclui"

```

---

## 🛠 Tecnologias Utilizadas

- **Java**: A linguagem de programação principal da aplicação. ☕
- **Spring Boot**: Para construção de APIs e gerenciamento de dependências. 🚀
- **Thymeleaf**: Para renderização de templates no lado do servidor. 📜
- **Oracle SQL**: Banco de dados relacional para armazenamento e gerenciamento eficiente de dados. 🗄️
