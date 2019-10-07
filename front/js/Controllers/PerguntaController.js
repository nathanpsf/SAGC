angular.module('Sagc').controller('PerguntaController', function ($scope, $route, $routeParams, $location, $http)
{
    $scope.pergunta = { questionText: '', questionType: '', questionCategory: ''};
    var pergunta = $scope.pergunta;
    
    $scope.perguntacriada = false;
    $scope.temResposta = false;
    $scope.respostas = [];    
    $scope.criarPergunta = function() 
    { 
        $http.post('http://localhost:8080/perguntas/criar', pergunta).then(function (response){
            console.log("sucesso", response.data);
            $scope.perguntacriada = true;
            if($scope.pergunta.questionType == "text") 
            { 
                alert("Pergunta Criada com Sucesso!");  
                $scope.temResposta = false;    
            }
            else 
            { 
                $scope.temResposta = true;
                $scope.resposta = { name: '', question: response.data};
                alert("Pergunta Criada com Sucesso!\nAgora Adicione Respostas!");                
            }

                  
        },
        function(errResponse){
            console.log("error", errResponse.data);
        });       
    }; 
    $scope.criarResposta = function()
    {
        $http.post('http://localhost:8080/respostas/criar', $scope.resposta).then(function (response){
            console.log("sucesso", response.data);
            alert("Resposta Criada com sucesso");
            let obj = {
                texto: $scope.resposta.name
              };
            $scope.respostas.push(obj);
            $scope.resposta.name = '';
        },
        function(errResponse){
            console.log("error", errResponse.data);
        }); 
    };
    $scope.novaPergunta = function()
    {
        $route.reload();
    }
    $scope.finalizarPergunta = function()
    {
        $location.path('/painel');
    }         
});