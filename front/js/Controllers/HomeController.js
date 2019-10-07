angular.module('Sagc').controller('HomeController', function ($scope, $location, $window)
{
    $scope.iniciar = function()
    {
        if($window.sessionStorage.getItem('usuario') == "logado")
        {
            $location.path('/painel');
        }
        else
        {
            alert("Faça o login para acessar o sistema.");
        }
    } 
});