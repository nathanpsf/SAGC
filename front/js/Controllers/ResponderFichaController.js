angular.module('Sagc').controller('ResponderFichaController', function ($scope, $http, $window, $location, $routeParams)
{
    var urli = 'http://localhost:8080/fichas/ficha/' + $routeParams.id;
    $scope.dados = [];
    $scope.categorias = [];
    $scope.perguntas = [];
    $scope.respostas = [];
    $scope.teste = [];
    $scope.teste2 =[[]];
    var consultarDados = function()
    {
        $http({
            url: urli,
            method: 'GET'
            }).then(function (response){
            console.log("Sucesso no GET ", response.data);
            response.data.map(function(perguntas) {
                $scope.categorias.push(perguntas.categoria);
              });           
            $scope.categorias = uniq($scope.categorias);
            
            $scope.dados = response.data;
                                    
        },
        function(errResponse){
            console.log("Erro no GET: ", errResponse.data);
        }); 
    };      
    consultarDados();    
    $scope.enviarFicha = function()
    {       
        var sucess = true;
        $scope.dados.map(function(p, index) {
            if(p.tipo == 'text')
            {
                $http({
                    url: 'http://localhost:8080/fichas/salvarresposta',
                    method: 'POST',
                    params: {
                        user: $window.sessionStorage.getItem('email'),
                        nomeresp: '',
                        content: $scope.teste[p.nome],
                        pergunta: p.nome,
                        categoria: p.categoria,
                        fichaid: $routeParams.id,
                    }
                }).then(function (response){
                    console.log("Resposta Salva Com sucesso!");
                },
                function(errResponse){
                    sucess = false;
                    console.log("Erro ao salvar ficha", errResponse.data);
                });                  
            }
            else if(p.tipo == 'mult')
            {
                p.responses.map(function(r) {
                    if($scope.teste2[r.name] == true)
                    {
                        $http({
                            url: 'http://localhost:8080/fichas/salvarresposta',
                            method: 'POST',
                            params: {
                                user: $window.sessionStorage.getItem('email'),
                                nomeresp: r.name,
                                content: r.name,
                                pergunta: p.nome,
                                categoria: p.categoria,
                                fichaid: $routeParams.id,
                            }
                        }).then(function (response){
                            console.log("Resposta Salva Com sucesso!");
                        },
                        function(errResponse){
                            sucess = false;
                            console.log("Erro ao salvar ficha", errResponse.data);
                        });                              
                    }
                });
               
            }
            else
            {
                $http({
                    url: 'http://localhost:8080/fichas/salvarresposta',
                    method: 'POST',
                    params: {
                        user: $window.sessionStorage.getItem('email'),
                        nomeresp: $scope.teste[p.nome],
                        content: $scope.teste[p.nome],
                        pergunta: p.nome,
                        categoria: p.categoria,
                        fichaid: $routeParams.id,
                    }
                }).then(function (response){
                    console.log("Resposta Salva Com sucesso!");
                },
                function(errResponse){
                    sucess = false;
                    console.log("Erro ao salvar ficha", errResponse.data);
                });                    
            }

          });    
        setTimeout(function(){ $location.path("/painel"); }, 3000);
         
    }
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

