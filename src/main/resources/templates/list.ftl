<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Producto </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.producto.id_producto" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nombre</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.producto.nombre" id="uname" class="username form-control input-sm" placeholder="Ingrese su nombre" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="cant">Cantidad</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.producto.cantidad" id="cant" class="form-control input-sm" placeholder="Ingrese la cantidad" required ng-pattern="ctrl.onlyIntegers"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="precio_unitario">Precio unitario</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.producto.precio_unitario" id="precio_unitario" class="form-control input-sm" placeholder="Ingrese el precio unitario" required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="precio_iva">Precio iva</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.producto.precio_iva" id="precio_iva" class="form-control input-sm" placeholder="Ingrese el precio unitario" required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.producto.id_producto ? 'Agregar' : 'Actualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Productos </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOMBRE</th>
		                <th>CANTIDAD</th>
		                <th>PRECIO UNITARIO</th>
		                 <th>PRECIO IVA</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllProductos()">
		                <td>{{u.id_producto}}</td>
		                <td>{{u.nombre}}</td>
		                <td>{{u.cantidad}}</td>
		                <td>{{u.precio_unitario}}</td>
		                <td>{{u.precio_iva}}</td>
		                <td><button type="button" ng-click="ctrl.editProducto(u.id_producto)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.removeProducto(u.id_producto)" class="btn btn-danger custom-width">Eliminar</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>