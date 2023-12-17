app = angular.module("app", ["ngRoute"]);
app.config(function ($routeProvider) {
  $routeProvider
    .when("/product", {
      templateUrl: "/admin/product/index.html",
      controller: "product-ctrl"
    })
    .when("/authorize", {
      templateUrl: "/admin/authority/index.html",
      controller: "authority-ctrl"
    })
    .when("/unauthorized", {
      templateUrl: "/admin/authority/unauthorized.html",
      controller: "authority-ctrl"
    })
    .when("/order", {
	  templateUrl: "/admin/order/index.html",
	  controller: "order-ctrl"
	})
	.when("/voucher", {
	  templateUrl: "/admin/voucher/index.html",
	  controller: "voucher-ctrl"
	})
    .otherwise({
      templateUrl: "/admin/home/index.html",
      controller: "home-ctrl"
    });
});

