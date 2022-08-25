app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
    $routeProvider
    .when("/product",{
        templateUrl:"/assets/admin/product/index.html",
        controller:"product-ctrl"
    })
    .when("/authorize",{// khi gọi đến url authorize thì chuyển đến html
        templateUrl:"/assets/admin/authority/index.html",
        controller:"authority-ctrl"
    })
    .when("unauthorized",{
        templateUrl:"/assets/admin/authority/unauthorized.html",
        controller:"authority-ctrl"
    })
    .otherwise({
        template: " <h1>Fpt Ploy Adminsss</h1>"
    })
})