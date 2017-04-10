var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/CRUDApp',
    PRODUCTO_SERVICE_API : 'http://localhost:8080/CRUDApp/api/producto/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ProductoController',
                controllerAs:'ctrl',
                resolve: {
                    productos: function ($q, ProductoService) {
                        console.log('Cargado todos los productos');
                        var deferred = $q.defer();
                        ProductoService.loadAllProductos().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

