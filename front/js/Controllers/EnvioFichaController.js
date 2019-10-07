angular.module('Sagc').controller('EnvioFichaController', function ($scope, $route, $routeParams, $location, $http)
{
     $scope.idselect = 0;
     $scope.uselect = '';
     $scope.ids = [];
     $scope.users = [];
     var consultarIds = function()
     {
         $http.post('http://localhost:8080/fichas/todosids',).then(function (response){
             console.log("Sucesso busca de todos os ids de fichas: ", response.data);
             response.data.map(function(id) {
                $scope.ids.push(id);
              });
                   
         },
         function(errResponse){
             console.log("Erro ao buscar ID's de ficha: ", errResponse.data);
         }); 
     }; 
     consultarIds();
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
     $scope.enviarFicha = function()
     {
        $http({
            url: 'http://localhost:8080/fichas/enviar',
            method: 'POST',
            params: {
                id: $scope.idselect,
                userEmail: $scope.uselect,
            }
            }).then(function (response){
            console.log("Sucesso envio de ficha para o usuário");
            alert("Ficha [" + $scope.idselect + "] enviada para: " + $scope.uselect);
                  
        },
        function(errResponse){
            console.log("Erro ao enviar ficha para o usuário: ", errResponse.data);
        });         
     }
     $scope.finalizarEnvio = function()
     {
         $location.path('/painel');
     }       
});