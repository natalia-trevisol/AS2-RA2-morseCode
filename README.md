# Sistema de Árvore Binária com Código Morse em Java

## Identificação
- **Aluna:** Natália Moritani Trevisol  
- **Disciplina:** Resolução de Problemas Estruturados em Computação
- **Turma:** B - manhã  
- **Curso:** Ciência da Computação  
- **Universidade:** Pontifícia Universidade Católica do Paraná (PUCPR)  
- **Professor:** Andrey Cabral Meira  

---

## Objetivo
O objetivo deste projeto é implementar uma árvore binária em Java para representar caracteres alfanuméricos utilizando o **Código Morse**.  
A aplicação permite:  
- Inserção automática ou manual de caracteres com seu código Morse;  
- Busca de caracteres a partir do código Morse e busca do código Morse a partir de caracteres;  
- Tradução de mensagens entre texto e código Morse;  
- Remoção de caracteres da árvore sem perder a hierarquia;  
- Exibição da árvore de forma hierárquica.

O projeto reforça conceitos como:  
- Árvores binárias e hierarquia de nós;  
- Estruturas de dados dinâmicas sem uso de arrays fixos;  
- Manipulação de strings e recursão;  
- Controle de fluxo através de menus interativos.

---

## Introdução
O Código Morse é um método de codificação que representa letras e números através de pontos (`.`) e traços (`-`).  
Neste projeto, cada caminho da raiz até um nó na árvore define a sequência de pontos e traços correspondente ao caractere armazenado.  

A árvore binária segue a lógica:  
- **Filho esquerdo:** ponto (`.`)  
- **Filho direito:** traço (`-`)  

Isso permite uma representação clara da estrutura Morse em forma de árvore.

---

## Estruturas de Dados Utilizadas

### Nodo (`Nodo.java`)
Representa cada nó da árvore, contendo:  
- `caractere`: o caractere armazenado (ou vazio se nenhum);  
- `esquerda`: referência para o filho que corresponde a `.`;  
- `direita`: referência para o filho que corresponde a `-`.  

---

### Árvore Binária Morse (`ArvoreBinariaMorse.java`)
Contém a raiz da árvore e os principais métodos:

**Métodos principais:**  
- `inserir(String codigo, String caractere)` → insere o caractere conforme a sequência Morse;  
- `buscar(String codigo)` → retorna o caractere correspondente ao código Morse;  
- `buscarCodigo(String letra)` → retorna o código Morse correspondente a um caractere;  
- `remover(String codigo)` → remove o caractere da árvore (mantendo a estrutura);  
- `exibirArvore()` → exibe a árvore horizontalmente, mantendo o alinhamento;  
- `buscarMensagem(String mensagem)` → traduz mensagem de Morse para texto;  
- `traduzirMensagemParaMorse(String mensagem)` → traduz mensagem de texto para Morse.

---

### Main (`Main.java`)
Contém o **menu interativo** e permite que o usuário interaja com a árvore:

**Menu do sistema:**
```bash
===== MENU: ÁRVORE BINÁRIA CÓDIGO MORSE =====
(1) - Inserir caracteres automaticamente (A-Z, 0-9)
(2) - Inserir caractere manualmente
(3) - Buscar caractere pelo código Morse
(4) - Buscar código Morse de uma letra
(5) - Exibir árvore verticalmente
(6) - Traduzir mensagem (código Morse -> texto)
(7) - Traduzir mensagem (texto -> código Morse)
(8) - Remover caractere
(0) - Sair
```

**Detalhes das opções:**  
- **1:** Insere todos os caracteres alfabéticos e números automaticamente na árvore;  
- **2:** Permite inserir manualmente um novo caractere com código Morse;  
- **3:** Busca o caractere correspondente a um código Morse digitado;  
- **4:** Busca o código Morse correspondente a um caractere digitado;  
- **5:** Exibe a árvore na forma hierárquica vertical ou horizontal;  
- **6:** Traduz mensagens de Morse para texto (separadas por espaços);  
- **7:** Traduz mensagens de texto para código Morse;  
- **8:** Remove um caractere da árvore (digitando o caractere ou código Morse);  
- **0:** Encerra o programa.

---

## Estrutura do Código
```bash
src/
│
├── Main.java # Menu principal e interação com o usuário
├── ArvoreBinariaMorse.java # Implementação da árvore binária Morse
├── Nodo.java # Estrutura de nó da árvore
└── README.md # Documentação do projeto
```

---

## Instruções para Executar o Projeto

### Pré-requisitos
- JDK 17 ou superior instalado;  
- IDE de sua preferência (IntelliJ, Eclipse, NetBeans) ou terminal com `javac` e `java`.

### Execução via terminal
1. Compilar os arquivos:
```bash
javac *.java
```

2. Executar o programa:
```bash
java Main
```

3. Interagir com o menu digitando o número da opção desejada.

--- 

## Explicação Geral do Código

- O programa inicia criando a raiz da árvore binária Morse;
- Os caracteres podem ser inseridos manual ou automaticamente;
- A árvore permite buscas e traduções, usando recursão para percorrer os caminhos de pontos e traços;
- A remoção apenas apaga o caractere do nó, mantendo a hierarquia intacta;
- A exibição vertical ou horizontal mostra claramente a estrutura, usando apenas () para representar nós.

--- 

## Conclusão

Este projeto consolida conceitos de árvores binárias, recursão e manipulação de strings, demonstrando como representar e gerenciar códigos Morse de forma estruturada.
A implementação permite inserção, busca, tradução e remoção de caracteres, reforçando os fundamentos da programação orientada a objetos e do raciocínio lógico em Java.
