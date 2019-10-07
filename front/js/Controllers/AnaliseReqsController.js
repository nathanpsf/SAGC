angular.module('Sagc').controller('AnaliseReqsController', function ($scope, $http, $window, $routeParams)
{
    $scope.users = [];
    $scope.fichas = [];
    $scope.categorias = [];
    $scope.respostas = [];
    $scope.dados = [];
    $scope.uselect = '';   
    $scope.idselect = 0; 
    var urli;
    $scope.userSelecionado = false;
    $scope.buscaCompleta = false;
    var consultarUsers = function()
    {
        $http.post('http://localhost:8080/allusers',).then(function (response){
            console.log("Sucesso busca de todos os usuarios: ", response.data);
            response.data.map(function(name) {
               $scope.users.push(name);
             });
                  
        },
        function(errResponse){
            console.log("Erro ao buscar os usuarios: ", errResponse.data);
        }); 
    };      
    consultarUsers();
    $scope.consultarResp = function()
    {
        $scope.userSelecionado = true;
        $http({
            url: 'http://localhost:8080/fichasresp',
            method: 'POST',
            params: {
                userEmail: $scope.uselect,
            }
            }).then(function (response){
            console.log("Sucesso na busca de requisição de fichas respondidas ", response.data);

            for (var prop in response.data) {
                $scope.fichas.push(prop);
            }
                  
        },
        function(errResponse){
            console.log("Erro ao consultar requisição de fichas respondidas: ", errResponse.data);
        }); 
    };  
  
    $scope.consultaResultado = function ()
    {
        urli = 'http://localhost:8080/fichas/ficha/' + $scope.idselect;          
        $http({
            url: urli,
            method: 'GET'
            }).then(function (response){
            console.log("Sucesso no GET visualizar ficha", response.data);
            response.data.map(function(perguntas) {
                $scope.categorias.push(perguntas.categoria);
              });           
            $scope.categorias = uniq($scope.categorias);
            
            $scope.dados = response.data;
                                    
        },
        function(errResponse){
            console.log("Erro no GET visualizar ficha: ", errResponse.data);
        }); 
        $http({
            url: 'http://localhost:8080/fichas/consultaresp',
            method: 'POST',
            params: {
                userEmail: $scope.uselect,
                fichaid: $scope.idselect,
            }
            }).then(function (response){
            console.log("Sucesso na busca de requisição das respostas da ficha ", response.data);
            $scope.respostas = response.data;
            $scope.buscaCompleta = true;
        },
        function(errResponse){
            console.log("Erro ao consultar requisição de fichas: ", errResponse.data);
        });         

    };
    function uniq(a) {
        var prims = {"boolean":{}, "number":{}, "string":{}}, objs = [];
    
        return a.filter(function(item) {
            var type = typeof item;
            if(type in prims)
                return prims[type].hasOwnProperty(item) ? false : (prims[type][item] = true);
            else
                return objs.indexOf(item) >= 0 ? false : objs.push(item);
        });
    }        
});