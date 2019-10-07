var app = angular.module('Sagc', ['ngRoute']);

app.config(function($routeProvider, $locationProvider)
{
   // remove o # da url
   $locationProvider.html5Mode(true);

   $routeProvider

   .when('/', {
      templateUrl : 'views/home.html',
      controller: "HomeController",
      
   })
   .when('/novapergunta', {
    templateUrl : 'views/criarpergunta.html',
    controller  : "PerguntaController",
    
 })
   .when('/painel', {
      templateUrl : 'views/Painel.html',
      controller: "PainelController",
   })
   .when('/novaficha', {
      templateUrl : 'views/criarficha.html',
      controller: "FichaController",
   })
   .when('/enviarficha', {
      templateUrl : 'views/envioficha.html',
      controller: "EnvioFichaController",
   })   
   .when('/responderficha/:id', {
      templateUrl : 'views/responderficha.html',
      controller: "ResponderFichaController",
   })      
   .when('/analisarreqs', {
      templateUrl : 'views/analisereqs.html',
      controller: "AnaliseReqsController",
   })   
   .when('/esqueciasenha', {
      templateUrl : 'views/esquecisenha.html',
      controller: "UsuarioController",
   })  
   .when('/cadastrarocorrencia', {
      templateUrl : 'views/cadastrocorrencia.html',
      controller: "OcorrenciaController",
   })    
   .when('/criaroperador', {
      templateUrl : 'views/criaroperador.html',
      controller: "CriarOperadorController",
   })       
   // caso não seja nenhum desses, redirecione para a rota '/'
   .otherwise ({ redirectTo: '/' });
});
