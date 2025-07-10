#language: pt
#Author: anderson@e2etreinamentos.com.br


 @regressivos
Funcionalidade: Login WebSite WebShop
 Como usuário da aplicação
 Quero enviar as minhas credenciais
 Para ter acesso área logada
 
 Contexto: Acessar o formulário de login
 	Dado que o usuário acesso o formulário de login

  @login @teste
  Cenario: Login com sucesso
  	Quando enviar os dados "testetestes@gmail.com" e "B?0P248kEf-P" corretamente
  	Então então valida o "testetestes@gmail.com" logado
   

  @loginNegativo
   Cenario: Login não realizado devido inconsistência <mensagem>
  	Quando enviar os dados <email> e <password> incorretamente
  	Então então valida a <mensagem> com a inconsistência

    Exemplos: 
      | email                    | password     | mensagem  |
      | "testetestes@gmail.com"  |""            | "Login was unsuccessful. Please correct the errors and try again."|
      | ""                       |""            | "No customer account found."|
