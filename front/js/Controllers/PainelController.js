angular.module('Sagc').controller('PainelController', function ($scope, $http, $window, $location)
{
    $scope.fichas = [];
    $scope.ocorrencias = [];
    $scope.cidade = '';
    var consultarReqs = function()
    {
        $http({
            url: 'http://localhost:8080/fichasreqs',
            method: 'POST',
            params: {
                userEmail: $window.sessionStorage.getItem('email'),
            }
            }).then(function (response){
            console.log("Sucesso na busca de requisição de fichas ", response.data);

            for (var prop in response.data) {
                $scope.fichas.push(prop);
            }
                  
        },
        function(errResponse){
            console.log("Erro ao consultar requisição de fichas: ", errResponse.data);
        }); 
    };      
    consultarReqs();  
    var consultarOcorrencias = function()
    {         
        $http.get('http://localhost:8080/ocorrencias/all')
        .then(function (response){
            console.log("Sucesso na busca de requisição de ocorrências ", response.data);
            response.data.map(function(o){
                $scope.ocorrencias.push(o);
            } 
            );
                  
        },
        function(errResponse){
            console.log("Erro ao consultar ocorrências: ", errResponse.data);
        }); 

    };
    consultarOcorrencias();
    $scope.filtrarCidade = function()
    {
        $scope.cidade = $scope.q;
        alert($scope.cidade);
    }
    $scope.abrirFicha = function(id)
    {
        let pat = '/responderficha/' +id; 
        $location.path(pat);
    }
    $scope.irOcorrencia = function()
    {
        $location.path("/cadastrarocorrencia");
    }
});

