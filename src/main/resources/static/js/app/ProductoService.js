'use strict';

angular.module('crudApp').factory('ProductoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllProductos: loadAllProductos,
                getAllProductos: getAllProductos,
                getProducto: getProducto,
                createProducto: createProducto,
                updateProducto: updateProducto,
                removeProducto: removeProducto
            };

            return factory;

            function loadAllProductos() {
                console.log('Recuperando todos los productos');
                var deferred = $q.defer();
                $http.get(urls.PRODUCTO_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Recuperado satisfatoriamente todos los productos');
                            $localStorage.productos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error mientras se cargaban los productos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllProductos(){
                return $localStorage.productos;
            }

            function getProducto(id) {
                console.log('Recuperando producto con id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PRODUCTO_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Recuperado satisfatoriamente Producto con id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error mientras se cargaba producto con id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createProducto(producto) {
                console.log('Creando producto');
                var deferred = $q.defer();
                $http.post(urls.PRODUCTO_SERVICE_API, producto)
                    .then(
                        function (response) {
                            loadAllProductos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error mientras se creaba usuario : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateProducto(producto, id) {
                console.log('Actualizando producto con id '+id);
                var deferred = $q.defer();
                $http.put(urls.PRODUCTO_SERVICE_API + id, producto)
                    .then(
                        function (response) {
                            loadAllProductos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error mientras se actualizaba producto con id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeProducto(id) {
                console.log('Eliminando producto con  id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PRODUCTO_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllProductos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error mientras se eliminaba producto con id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);