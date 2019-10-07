angular.module('Sagc').controller('CriarOperadorController', function ($scope, $http, $location)
{
    $scope.users = [];
    $scope.uselect = '';
    $scope.vselect = '';
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
    $scope.salvarOperador = function ()
    {
        let oper;
        if($scope.vselect == 'true') { oper = true; }
        else { oper = false; }
        $http({
            url: 'http://localhost:8080/setoperator',
            method: 'POST',
            params: {
                op: oper,
                userEmail: $scope.uselect,
            }
            }).then(function (response){
            console.log("Sucesso na modificação de operador: ", response.data);
            $location.path("/painel");
                  
        },
        function(errResponse){
            console.log("Erro na modificação de operador: ", errResponse.data);
        });        
    }   
});