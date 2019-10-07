angular.module('Sagc').controller('FichaController', function ($scope, $window, $route, $routeParams, $location, $http)
{
    $scope.ficha = { creator: null, questions: []};
    $scope.fichaid = 0;
    $scope.catslect = '';
    $scope.categorias = [];
    $scope.cats =[];
    $scope.perguntas = [];
    var fichacriada = false;
    var fichaid = 0;
    var consultarId = function()
    {
        $http.post('http://localhost:8080/fichas/proximoid', "a").then(function (response){
            console.log("Sucesso ID de ficha: ", response.data);
            $scope.fichaid = response.data;
                  
        },
        function(errResponse){
            console.log("Erro ID de ficha: ", errResponse.data);
        }); 
    }; 
    consultarId();
    var consultarCats = function()
    {
        $http.post('http://localhost:8080/perguntas/buscarcategoria', "a").then(function (response){
            console.log("Sucesso buscar categoria");
            response.data.map(function(nomes) {
                $scope.categorias.push(nomes);
              });
                  
        },
        function(errResponse){
            console.log("Erro ao buscar categoria: ", errResponse.data);
        });        
    }
    consultarCats();
    $scope.consultaPergunta = function ()
    {
        $scope.perguntas = [];
        $http({
                url: 'http://localhost:8080/perguntas/buscarperguntas',
                method: 'POST',
                params: {
                    catName: $scope.catselect,
                }
        }).then(function (response){
            console.log("Sucesso ao consultar as perguntas");
            response.data.map(function(nomes) {
                $scope.perguntas.push(nomes);
                console.log("Controle: " + nomes);
              });
                  
        },
        function(errResponse){
            console.log("Erro ao consultar perguntas: ", errResponse.data);
        }); 
    }

    $scope.salvarFicha = function()
    {
        if(fichacriada == false)
        {
            $http({
                url: 'http://localhost:8080/fichas/criar',
                method: 'POST',
                params: {
                    creator: $window.sessionStorage.getItem('nome'),
                    perguntas: $scope.perguntas,
                }
            }).then(function (response){
                console.log("Ficha salva com sucesso");
                alert("Ficha criada com sucesso!");
                $scope.cats.push($scope.catselect);
                $scope.perguntas = [];
                $scope.catslect = '';
                fichacriada = true;
                fichaid = response.data;
            },
            function(errResponse){
                console.log("Erro ao salvar ficha", errResponse.data);
            }); 
        }
        else
        {
            $http({
                url: 'http://localhost:8080/fichas/salvar',
                method: 'POST',
                params: {
                    id: fichaid,
                    perguntas: $scope.perguntas,
                }
            }).then(function (response){
                console.log("Categoria incluída com sucesso");
                alert("categoria incluída com sucesso!");
                $scope.cats.push($scope.catselect);
                $scope.perguntas = [];
                $scope.catslect = '';
            },
            function(errResponse){
                console.log("Erro ao salvar ficha", errResponse.data);
            });             
        }
    };

    $scope.removerPergunta = function(arg1)
    {
        $scope.perguntas = arrayRemove($scope.perguntas, arg1);
    }
    function arrayRemove(arr, value) {

        return arr.filter(function(ele){
            return ele != value;
        });
     
     }
     $scope.novaFicha = function()
     {
         $route.reload();
     }
     $scope.finalizarFicha = function()
     {
         $location.path('/painel');
     }        
     

});