# 🎨 Lookbooks Trends : Gerencie seus Looks

**Lookbooks Trends** é uma aplicação inovadora que permite aos usuários explorar e gerenciar looks de moda, tudo enquanto segue as últimas tendências do mercado! 🌟

## ⚙️ Funcionalidades

- **Exploração de Looks**: Navegue por uma coleção diversificada de looks inspiradores. 👗👖
- **Gerenciamento de Produtos e Looks**: Adicione, edite e remova produtos e looks com facilidade. Organize suas criações em lookbooks personalizados e aproveite recomendações com base em suas preferências e tendências atuais.  🎉
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

## 🧪 Testes Unitários

A aplicação **VisitMap** inclui uma suíte de testes unitários que garante a qualidade e o funcionamento adequado dos serviços. Foram implementados testes para as classes, `Product`, `Lookbook`, `ProductService` e `LookbookService` utilizando **JUnit** e **Mockito**. 

No total, 22 testes foram executados com sucesso, confirmando que as funcionalidades principais estão funcionando conforme o esperado.

![Terminal com comando `mvn clean test`](https://github.com/user-attachments/assets/4bd301e7-fdbc-474c-b54a-96f4eae10889)

---

## CRUD - Exemplos 📋

### 1. Criar 🎤

#### *Produto* 👕 

Para criar um novo Produto, você deve enviar um formulário com as seguintes informações:

```plaintext
Nome: Camiseta Jordan
Categoria: CAMISETA
Cor: Vermelha
Tamanho: M
Material: Algodão
Marca: Nike
Preço: 99.90
Design: ESTAMPADA
Sazonalidade: INVERNO
Imagem URL: https://abrir.link/yLVET
```

#### *Lookbook* 📚 

Para criar um novo Lookbook, você pode usar o seguinte formulário:

```plaintext
Nome: Look Estiloso - Jordan
(Selecione os Produtos: Camiseta Jordan)
Estilo: ESPORTIVO
Tendência: UNISSEX
```

### 2. Ler 📖

#### *Produto* 🔍

Para ler os detalhes de um produto específico, aperte em 'Detalhes'.

#### *Lookbook* 📅

Para ler os detalhes de um Lookbook específico, aperte em 'Detalhes'.

### 3. Atualizar ✏️

#### *Produto* 👕

Para atualizar as informações de um produto existente, você pode enviar o seguinte formulário:

```plaintext
Nome: Camiseta Air Jordan
Categoria: CAMISETA
Cor: Vermelha
Tamanho: P
Material: Algodão
Marca: Nike
Preço: 99.90
Design: ESTAMPADA
Sazonalidade: INVERNO
Imagem URL: https://abrir.link/yLVET
```

#### *Lookbook* 🌟

Para atualizar um Lookbook existente, você pode usar o seguinte formulário:

```plaintext
Nome: Look Esportivo - Air Jordan
(Selecione os Produtos: Camiseta Jordan)
Estilo: ESPORTIVO
Tendência: UNISSEX
```

### 4. Excluir 🗑️

#### *Produto* ❌

Para excluir um produto, aperte em 'Excluir'.

#### *Lookbook* ❌

Para excluir um Lookbook, aperte em 'Excluir'.

---

## 🚀 Deploy e Integração Contínua - Azure Pipelines

O **VisitMap** utiliza uma abordagem automatizada para implantação e entrega contínua, aproveitando os recursos do **Azure Web App**. O pipeline de CI/CD é configurado para garantir que o processo de build, teste e deploy seja contínuo e integrado ao fluxo de desenvolvimento. 🌐🔧

### Passo a Passo para Configuração do CI/CD

1. **Criação do Repos do Azure DevOps** ☁️
   - Primeiramente, configure uma conta no **Azure DevOps** e crie um novo projeto para o **LookbooksTrends**.
   - Importe o repositório do GitHub e crie as tasks necessárias para o pipeline.

2. **Criação da Infraestrutura no Azure** ☁️
   - Inicie configurando uma conta no **Azure Cloud**.
   - Utilize o **Azure CLI** para automatizar a criação da infraestrutura necessária para o seu projeto, garantindo que todos os recursos sejam provisionados de forma eficiente e padronizada.
   - Adicione uma conexão de serviço com o **Azure Web App**, que será utilizado para hospedar a aplicação.

3. **Configuração do Pipeline de Build (CI)** 🛠️
   - Crie um pipeline de build no **Azure Pipelines** para o repositório do projeto.
   - Use o arquivo YAML gerado a seguir para configurar o pipeline de build:

```yaml
# azure-pipelines.yml
trigger:
- main

pool:
  vmImage: ubuntu-latest
  name: Azure Pipelines
  demands: maven

steps:
- task: Maven@4
  inputs:
    azureSubscription: '$(AzureSubscriptionID)'
    mavenPomFile: 'pom.xml'
    publishJUnitResults: true
    testResultsFiles: '**/surefire-reports/TEST-*.xml'
    testRunTitle: 'LookbooksTrends Tests Code'
    javaHomeOption: 'JDKVersion'
    jdkVersionOption: '1.17'
    mavenVersionOption: 'Default'
    mavenAuthenticateFeed: false
    effectivePomSkip: false
    sonarQubeRunAnalysis: false
- task: CopyFiles@2
  displayName: 'Copy Files : $(build.artifactstagingdirectory)'
  inputs:
    SourceFolder: '$(system.defaultworkingdirectory)'
    Contents: '**/target/*.jar'
    TargetFolder: '$(build.artifactstagingdirectory)'

- task: PublishBuildArtifacts@1
  displayName: 'Publish Artifact: app'
  inputs:
    PathtoPublish: '$(build.artifactstagingdirectory)'
    ArtifactName: app
```

   - Esse pipeline executa os testes unitários com **JUnit**, constrói o pacote da aplicação. 🔧

> [!IMPORTANT]  
> **Nota: A variável `$(AzureSubscriptionID)` representa o identificador da assinatura do Azure, que é utilizado para autenticação e acesso seguro aos recursos da conta no Azure.** 

4. **Configuração do Pipeline de Release (CD)** 🚢
   - **Criação do Pipeline de Release**: Configure um pipeline de release no Azure DevOps para automatizar a implantação da aplicação.
   - **Integração com o Pipeline de Build**: Utilize o artefato gerado pelo pipeline de build como fonte para garantir que a versão mais recente seja implantada.
   - **Estágio de Deploy com Web App**: Defina estágios de deploy utilizando o **Azure Web App** como o ambiente de produção, permitindo implantações escaláveis e seguras.
   - **Aprovações e Gates**: Caso necessário, implemente revisões e aprovações antes de implantações em produção para aumentar a segurança e confiabilidade.

5. **Teste do Deploy Manual** 🧪
   - Realize um teste inicial do pipeline de release criando uma nova release manualmente.
   - Verifique se a aplicação é implantada corretamente e se todos os serviços estão funcionando como esperado.

---

## Tecnologias Utilizadas 🛠️

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Thymeleaf**: Motor de templates para gerar páginas HTML dinâmicas.
- **Hibernate**: ORM para mapeamento objeto-relacional.
- **Oracle Database**: Banco de dados relacional utilizado para armazenar informações de lookbooks e produtos.
- **JUnit**: Framework para testes unitários em Java.
- **Maven**: Ferramenta de gerenciamento de dependências e build do projeto.
- **Azure CLI**: Utilizada para criar e gerenciar Web App Service.
- **Azure DevOps**: Para configuração de pipelines de CI/CD.

---


## 📄 Documentação Adicional

- **Código Fonte**: [GitHub Repository](https://github.com/laiscrz/lookbooks-trends-mvc) 📁
- **Vídeo de Demonstração**: [YouTube Video]() 📹

---

## 🫂 Integrantes

Aqui estão os membros do grupo que participaram durante desenvolvimento desta SPRINT.

* **RM 552258 - Laís Alves da Silva Cruz**
  - Turma: 2TDSPH
  - Responsável: Banco de Dados e DevOps

* **RM 552267 - Bianca Leticia Román Caldeira**
  - Turma: 2TDSPH
  - Responsável: Mobile

* **RM 552252 – Charlene Aparecida Estevam Mendes Fialho**
  - Turma: 2TDSPH
  - Responsável: Java e .NET

* **RM 97916 – Fabricio Torres Antonio**
  - Turma: 2TDSPH
  - Responsável: QA

* **RM 99675 – Lucca Raphael Pereira dos Santos**
  - Turma: 2TDSPZ
  - Responsável: IA
    
<table>
  <tr>
      <td align="center">
      <a href="https://github.com/laiscrz">
        <img src="https://avatars.githubusercontent.com/u/133046134?v=4" width="100px;" alt="Lais Alves's photo on GitHub"/><br>
        <sub>
          <b>Lais Alves</b>
        </sub>
      </a>
      </td>
      <td align="center">
      <a href="https://github.com/biancaroman">
        <img src="https://avatars.githubusercontent.com/u/128830935?v=4" width="100px;" border-radius='50%' alt="Bianca Román's photo on GitHub"/><br>
        <sub>
          <b>Bianca Román</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/charlenefialho">
        <img src="https://avatars.githubusercontent.com/u/94643076?v=4" width="100px;" border-radius='50%' alt="Charlene Aparecida's photo on GitHub"/><br>
        <sub>
          <b>Charlene Aparecida</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Fabs0602">
        <img src="https://avatars.githubusercontent.com/u/111320639?v=4" width="100px;" border-radius='50%' alt="Fabricio Torres's photo on GitHub"/><br>
        <sub>
          <b>Fabricio Torres</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/LuccaRaphael">
        <img src="https://avatars.githubusercontent.com/u/127765063?v=4" width="100px;" border-radius='50%' alt="Lucca Raphael's photo on GitHub"/><br>
        <sub>
          <b>Lucca Raphael</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

---

> Esse guia detalha o processo de configuração do CI/CD, desde a criação do projeto no Azure até a configuração do pipeline de build e release, facilitando a automação do deploy e a entrega contínua da aplicação **LookbooksTrends**. ✨🚀
