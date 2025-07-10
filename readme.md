# 🧪 Projeto DemoShop

Automação de testes end-to-end para o site [Demo Web Shop](https://demowebshop.tricentis.com), utilizando **Java 17**, **JUnit 5**, **Selenium WebDriver** e **Rest-Assured**.

Este projeto demonstra práticas modernas de automação para testes **funcionais de UI e APIs REST**, com foco em **legibilidade**, **manutenibilidade** e **geração de evidências visuais**, como **screenshots** e **validação de PDFs com dados dinâmicos**.

## ⚙️ Tecnologias e Ferramentas

- Java 17+
- Maven 3.8+
- Selenium WebDriver
- JUnit 5
- Rest-Assured
- Apache PDFBox
- Lombok

## 📁 Estrutura de Diretórios

```bash
professor_demoshop/
├── src/test/java/
│   ├── elementos/               # Mapeamento de elementos fixos da UI
│   ├── metodos/                 # Métodos reutilizáveis e interações comuns
│   ├── pages/                   # Page Objects das páginas testadas
│   ├── testes/                  # Classes de testes JUnit
│   │   ├── ComprarProdutoTest.java
│   │   ├── LoginTest.java
│   │   ├── LogoutTest.java
│   │   ├── RegistrarUsuarioTest.java
│   │   └── ValidarPdfInvoice.java
│   └── utils/                   # Utilitários: Screenshot, PDF, etc.
│
├── target/
│   └── evidencias/              # Evidências geradas pelos testes (ex: screenshots)
│       ├── comprarProduto/
│       └── login/
│
├── pom.xml                      # Arquivo de configuração do Maven
└── README.md                    # Documentação do projeto
```

## ✅ Pré-requisitos

- Java 17 ou superior
- Maven 3.8 ou superior
- Google Chrome instalado e atualizado
- ChromeDriver compatível com a versão do Chrome (configurado no PATH)
- Git

## 🚀 Como Executar

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd professor_demoshop
```

Execute os testes:

```bash
mvn clean test
```

As evidências serão geradas automaticamente em: `target/evidencias/`
