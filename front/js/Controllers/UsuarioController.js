angular.module('Sagc').controller('UsuarioController', function ($scope, $route, $templateCache, $routeParams, $location, $http, $window)
{
    $scope.user = { name: '', password: '', email: '', city: '', question: '', answer: '', operator: false};
    $scope.esenha = { email: '', pergunta: '', resposta: ''};
    var email = '';
    var user = $scope.user;
    $scope.logado = false;
    $scope.mudarSenha = false;
    $scope.operador = false;
    if($window.sessionStorage.getItem('usuario') == "logado") {  $scope.logado = true; 
        $scope.user.name = $window.sessionStorage.getItem('nome');
        $scope.operador = $window.sessionStorage.getItem('operador');
    }
    $scope.salvar = function() 
    { 
        $http.post('http://localhost:8080/salvarusuario', user).then(function (response){
            console.log("Registro de Usuário criado com sucesso!");
            $scope.logado = true;
            $window.sessionStorage.setItem('usuario', "logado");
            $window.sessionStorage.setItem('nome', $scope.user.name);
            $window.sessionStorage.setItem('email', $scope.user.email);
            $scope.operador = response.data.operator;
            $window.sessionStorage.setItem('operador', $scope.operador);
            $location.path('/');
            $route.reload();
            
        },
        function(errResponse){
            if(errResponse.status == 409)
            {
                alert("Email já cadastrado no sistema!");
            }
            console.log("Erro ao cadastrar usuário");
        });       
    }; 
    $scope.logar = function()
    {
        $http.post('http://localhost:8080/validarlogin', user).then(function (response){
            $scope.user = response.data;
            console.log("Usuário Logado com sucesso");
            $scope.logado = true;
            $window.sessionStorage.setItem('usuario', "logado");
            $window.sessionStorage.setItem('nome', $scope.user.name);
            $window.sessionStorage.setItem('email', $scope.user.email);  
            $scope.operador = response.data.operator;
            $window.sessionStorage.setItem('operador', $scope.operador);                      
            $location.path('/');
            $route.reload();
            
        },
        function(errResponse){
            if(errResponse.status == 404)
            {
                alert("E-mail ou senha incorretos!");
            }
            console.log("Erro ao logar");
        });  
    };
    $scope.deslogar = function()
    {
        $window.sessionStorage.setItem('usuario', null);
        $window.sessionStorage.setItem('nome', null); 
        $window.sessionStorage.setItem('email', null);     
        $window.sessionStorage.setItem('operador', false);       
        $window.location.href = "/SAGC/";
        
    }
    $scope.esqueciSenha = function()
    {
        $http({
            url: 'http://localhost:8080/esenha',
            method: 'POST',
            params: {
                userEmail: $scope.esenha.email,
                pergunta: $scope.esenha.pergunta,
                resposta: $scope.esenha.resposta,
            }
            }).then(function (response){
            console.log("Pergunta e resposta correta ", response.data);
            email = response.data.email;
            $scope.mudarSenha = true;
                  
        },
        function(errResponse){
            if(errResponse.status == 409)
            {
                alert("Pergunta ou resposta incorreta!");
            }
            if(errResponse.status == 404)
            {
                alert("Email não cadastrado no Sistema!");
            }            
            console.log("Erro esqueci a senha: ", errResponse.data);
        });         
    }
    $scope.salvarSenha = function()
    {
        $http({
            url: 'http://localhost:8080/ssenha',
            method: 'POST',
            params: {
                userEmail: email,
                senha: $scope.esenha.nsenha,
            }
            }).then(function (response){
            console.log("Senha alterada com sucesso", response.data);
            $window.location.href = "/SAGC/";
                  
        },
        function(errResponse){         
            console.log("Erro esqueci a senha: ", errResponse.data);
        });         
    }    
});