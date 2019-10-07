angular.module('Sagc').controller('OcorrenciaController', function ($scope, $http, $window, $location)
{
    $scope.ocorrencia = {cidade:'', endereco:'', numero: '', tipo: '', data:'', obs:'', emailCriador:$window.sessionStorage.getItem('email')};  
    $scope.enviarOcorrencia = function()
    {
        $http.post('http://localhost:8080/ocorrencias/salvar', $scope.ocorrencia)
        .then(function (response){
            console.log("Ocorrência Salva Com sucesso!");
            alert("Ocorrência enviada com sucesso!");
            $location.path("/painel");
        },
        function(errResponse){
            console.log("Erro ao salvar ocorrência", errResponse.data);
        }); 
        
    }
});