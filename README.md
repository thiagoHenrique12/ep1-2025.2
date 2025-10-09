# 🏥 Trabalho Prático – Sistema de Gerenciamento Hospitalar  

### 🎯 Objetivo  
Implementar um *Sistema de Gerenciamento Hospitalar* em *Java, aplicando conceitos avançados de **Programação Orientada a Objetos (POO), com foco em **herança, polimorfismo, encapsulamento, persistência de dados* e *regras de negócio mais complexas*.  

---
## Descrição do Projeto

Desenvolvimento de um sistema de gerenciamento hospitalar utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

## Dados do Aluno

- **Nome completo:** Thiago Henrique Machado de Souza
- **Matrícula:** 242015960
- **Curso:** Engenharias
- **Turma:** 02

---

## Instruções para Compilação e Execução

1. **Compilação:**  
   projeto compilado usando o compilador interno do IntelliJ

2. **Execução:**  
   baixar o arquivo main no repositório do gitHub, abrir no IntelliJ, dentro de src buscar o arquivo Main e rodar pelo botão run da IDE

3. **Estrutura de Pastas:**  
   ep1-2025.2
   │
   ├
   ├── .idea
   ├── out
   ├
   │
   ├── src/
   │   ├── entidades/
   │   │   ├── Consulta.java
   │   │   ├── Especialidade.java
   │   │   ├── Internacao.java
   │   │   ├── Medico.java
   │   │   ├── Paciente.java
   │   │   ├── PacienteEspecial.java
   │   │   ├── Pessoa.java
   │   │   ├── PlanoSaude.java
   │   │   ├── Quarto.java
   │   │   ├── StatusConsulta.java
   │   │   └── TipoQuarto.java
   │   │
   │   ├── repository/
   │   │   ├── ConsultaRepository.java
   │   │   ├── InternacaoRepository.java
   │   │   ├── MedicoRepository.java
   │   │   ├── PacienteRepository.java
   │   │   ├── PlanoSaudeRepository.java
   │   │   └── QuartoRepository.java
   │   │
   │   ├── services/
   │   │   ├── ConsultaService.java
   │   │   ├── InternacaoService.java
   │   │   ├── MedicoService.java
   │   │   ├── PacienteService.java
   │   │   ├── PlanoSaudeService.java
   │   │   └── QuartoService.java
   │   │
   │   ├── ui/
   │   │   ├── Menu.java
   │   │   ├── MenuConsultas.java
   │   │   ├── MenuInternacoes.java
   │   │   ├── MenuMedicos.java
   │   │   ├── MenuPacientes.java
   │   │   └── MenuPlanos.java
   │   │
   │   ├── utils/
   │   │   └── InputUtils.java
   │   │
   │   └── Main.java
  │    

   

  

3. **Versão do JAVA utilizada:**  
   zulu-21

---

## Vídeo de Demonstração

- https://youtu.be/USCBpPRQ6Gg

---

## Prints da Execução

1. Menu Principal:  
   !![img.png](img.png)

2. Cadastro de Médico:  
   !![img_1.png](img_1.png)

3. Cadastro de Paciente:  
   !![img_2.png](img_2.png)

---

---

## Observações (Extras ou Dificuldades)

Durante o desenvolvimento, tive dificuldades principalmente na serialização e no salvamento de dados em arquivos CSV. Também tive problemas na implementação dos blocos try-catch e na compreensão dos conceitos relacionados à persistência de dados. Esses pontos exigiram maior atenção e estudo.

---

## Contato

- estudos.trabalhos12@gmail.com

---

### 🖥️ Descrição do Sistema  

O sistema deve simular o funcionamento de um hospital com cadastro de *pacientes, médicos, especialidades, consultas e internações*.  

1. *Cadastro de Pacientes*  
   - Pacientes comuns e pacientes especiais (ex: com plano de saúde).  
   - Cada paciente deve ter: nome, CPF, idade, histórico de consultas e internações.  

2. *Cadastro de Médicos*  
   - Médicos podem ter especialidades (ex: cardiologia, pediatria, ortopedia).  
   - Cada médico deve ter: nome, CRM, especialidade, custo da consulta e agenda de horários.  

3. *Agendamento de Consultas*  
   - Um paciente pode agendar uma consulta com um médico disponível.  
   - Consultas devem registrar: paciente, médico, data/hora, local, status (agendada, concluída, cancelada).  
   - Pacientes especiais (plano de saúde) podem ter *vantagens*, como desconto.  
   - Duas consultas não podem estar agendadas com o mesmo médico na mesma hora, ou no mesmo local e hora

4. *Consultas e Diagnósticos*  
   - Ao concluir uma consulta, o médico pode registrar *diagnóstico* e/ou *prescrição de medicamentos*.  
   - Cada consulta deve ser registrada no *histórico do paciente*.  

5. *Internações*  
   - Pacientes podem ser internados.  
   - Registrar: paciente, médico responsável, data de entrada, data de saída (se já liberado), quarto e custo da internação.  
   - Deve existir controle de *ocupação dos quartos* (não permitir duas internações no mesmo quarto simultaneamente).  
   - Internações devem poder ser canceladas, quando isso ocorrer, o sistema deve ser atualizado automaticamente.

6. *Planos de saúde*    
   -  Planos de saude podem ser cadastrados.
   -  Cada plano pode oferecer *descontos* para *especializações* diferentes, com possibilidade de descontos variados.
   -  Um paciente que tenha o plano de saúde deve ter o desconto aplicado.
   -  Deve existir a possibilidade de um plano *especial* que torna internação de menos de uma semana de duração gratuita.
   -  Pacientes com 60+ anos de idade devem ter descontos diferentes.

7. *Relatórios*  
   - Pacientes cadastrados (com histórico de consultas e internações).  
   - Médicos cadastrados (com agenda e número de consultas realizadas).  
   - Consultas futuras e passadas (com filtros por paciente, médico ou especialidade).  
   - Pacientes internados no momento (com tempo de internação).  
   - Estatísticas gerais (ex: médico que mais atendeu, especialidade mais procurada).  
   - Quantidade de pessoas em um determinado plano de saúde e quanto aquele plano *economizou* das pessoas que o usam.  


---

### ⚙️ Requisitos Técnicos  
- O sistema deve ser implementado em *Java*.  
- Interface via *terminal (linha de comando)*.  
- Os dados devem ser persistidos em *arquivos* (.txt ou .csv).  
- Deve existir *menu interativo*, permitindo navegar entre as opções principais.  

---

### 📊 Critérios de Avaliação  

1. *Modos da Aplicação (1,5)* → Cadastro de pacientes, médicos, planos de saúde, consultas e internações.  
2. *Armazenamento em arquivo (1,0)* → Dados persistidos corretamente, leitura e escrita funcional.  
3. *Herança (1,0)* → Ex.: Paciente e PacienteEspecial, Consulta e ConsultaEspecial, Médico e subclasses por especialidade.  
4. *Polimorfismo (1,0)* → Ex.: regras diferentes para agendamento, preços de consultas.
5. *Encapsulamento (1,0)* → Atributos privados, getters e setters adequados.  
6. *Modelagem (1,0)* → Estrutura de classes clara, bem planejada e com relacionamentos consistentes.  
7. *Execução (0,5)* → Sistema compila, roda sem erros e possui menus funcionais.  
8. *Qualidade do Código (1,0)* → Código limpo, organizado, nomes adequados e boas práticas.  
9. *Repositório (1,0)* → Uso adequado de versionamento, commits frequentes com mensagens claras.  
10. *README (1,0)* → Vídeo curto (máx. 5 min) demonstrando as funcionalidades + prints de execução + explicação da modelagem.  

🔹 *Total = 10 pontos*  
🔹 *Pontuação extra (até 1,5)* → Melhorias relevantes, como:  
- Sistema de triagem automática com fila de prioridade.  
- Estatísticas avançadas (tempo médio de internação, taxa de ocupação por especialidade).  
- Exportação de relatórios em formato .csv ou .pdf.  
- Implementação de testes unitários para classes principais.  
- Menu visual.
