# App Country API
## Aplicação que coleta dados de uma Api de países disponível no link: ""
* Lista as bandeiras dos países
* Lista informações de países
Arquitetura utilizada : MVVM
Componentes utilizados:   
* RecyclerView
* ViewModel para separar  a gerencia de estado e buscar informaçôes  da nossa UI
  A camada  ViewModel utilizará  o Architecture Component ViewModel, que vai gerenciar o tempo de vida
  através da mudança do ciclo de vida da Activity através da injeção usando o koin.
  LiveData para atualizar de forma reativa as informações da tela.
  Libs utilizadas:
    * Retrofit para fazer as  requisições HTTP  para uma AP pública.
* Injeção de dependências com Koin
* Coroutines para gerenciar a chamada assíncrona dessa  requisição HTTP e definir
  em quias threads devem ser executadas
  
<img src="img.png" widt="200" height="300"> <img src="img_2.png" widt="200" height="300"> <img src="image_3.png" widt="200" height="300">
