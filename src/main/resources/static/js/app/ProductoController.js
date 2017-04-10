'use strict';

angular.module('crudApp').controller('ProductoController',
    ['ProductoService', '$scope',  function( ProductoService, $scope) {

        var self = this;
        self.producto = {};
        self.productos=[];

        self.submit = submit;
        self.getAllProductos = getAllProductos;
        self.createProducto = createProducto;
        self.updateProducto = updateProducto;
        self.removeProducto = removeProducto;
        self.editProducto = editProducto;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.producto.id_producto === undefined || self.producto.id_producto === null) {
                console.log('Guardando nuevo producto', self.producto);
                createProducto(self.producto);
            } else {
                updateProducto(self.producto, self.producto.id_producto);
                console.log('Producto actualizado con id ', self.producto.id);
            }
        }

        function createProducto(producto) {
            console.log('Crear producto');
            ProductoService.createProducto(producto)
                .then(
                    function (response) {
                        console.log('Producto creado satisfactoriamente');
                        self.successMessage = 'Producto creado satisfactoriamente';
                        self.errorMessage='';
                        self.done = true;
                        self.producto={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error mientras se creaba producto');
                        self.errorMessage = 'Error mientras se creaba producto: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateProducto(producto, id){
            console.log('Actualizar producto');
            ProductoService.updateProducto(producto, id)
                .then(
                    function (response){
                        console.log('Producto actualizado satisfactoriamente');
                        self.successMessage='Producto actualizado satisfactoriamente';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error mientras se actualizaba producto');
                        self.errorMessage='Error mientras se actualizaba producto '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeProducto(id){
            console.log('Eliminar producto con id '+id);
            ProductoService.removeProducto(id)
                .then(
                    function(){
                        console.log('Producto '+id + ' eliminado satisfactoriamente');
                    },
                    function(errResponse){
                        console.error('Error mientras se eliminaba producto '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllProductos(){
            return ProductoService.getAllProductos();
        }

        function editProducto(id) {
            self.successMessage='';
            self.errorMessage='';
            ProductoService.getProducto(id).then(
                function (producto) {
                    self.producto = producto;
                },
                function (errResponse) {
                    console.error('Error mientras se eliminaba producto ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.producto={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);