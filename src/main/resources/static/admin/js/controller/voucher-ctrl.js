app.controller("voucher-ctrl", function($scope, $http){
	
	$scope.initialize = function(){
		$http.get("/rest/models").then(resp => {
			$scope.productModels = resp.data; // lấy list productmodels
		})

		$http.get("/rest/shop/vouchers").then(resp => {
			$scope.vouchers = resp.data; // lấy list vouchers
			$scope.vouchers.forEach(voucher => {
				voucher.endDate = new Date(voucher.endDate)
			})
		});
		$scope.reset();
	}
	
	$scope.reset = function(){
		$scope.active = 0;
		$scope.form = {
			productModel:{modelname: 'Laptop Acer'},
			endDate: new Date(),
		};
	}
	
	$scope.edit = function(voucher){
		$scope.active = 1; // để ẩn hiện id + model name
		$scope.form = angular.copy(voucher);
		$(".nav-tabs a:eq(0)").tab("show");
	}
	
	$scope.create = function(){
		var voucher = angular.copy($scope.form);
		$http.post(`/rest/shop/voucher`, voucher).then(resp => {
			resp.data.endDate = new Date(resp.data.endDate)
			$scope.vouchers.push(resp.data);
			$scope.reset();
			alert("Thêm mới voucher thành công!");
			$(".nav-tabs a:eq(1)").tab("show");
		}).catch(error => {
			alert("Lỗi thêm mới voucher!");
			console.log("Error", error);
		});
	}

	$scope.update = function(){
		var voucher = angular.copy($scope.form);
		$http.put(`/rest/shop/voucher/${voucher.id}`, voucher).then(resp => {
			var index = $scope.vouchers.findIndex(vc => vc.id == voucher.id);
			$scope.vouchers[index] = voucher;
			alert("Cập nhật voucher thành công!");
			//location.reload();
			$(".nav-tabs a:eq(1)").tab("show");
		})
		.catch(error => {
			alert("Lỗi cập nhật voucher!");
			console.log("Error", error);
		});
	}

	$scope.delete = function(voucher){
		if(confirm("Bạn muốn voucher này?")){
			$http.delete(`/rest/shop/voucher/${voucher.id}`).then(resp => {
				var index = $scope.vouchers.findIndex(vc => vc.id == voucher.id);
				$scope.vouchers.splice(index, 1);
				$scope.reset();
				alert("Xóa voucher thành công !");
			}).catch(error => {
				alert("Lỗi xóa voucher !");
				console.log("Error", error);
			})
		}
	}
	
	$scope.initialize();
	
	$scope.pager = {
		page: 0,
		size: 10,
		get vouchers(){
			if(this.page < 0){
				this.last();
			}
			if(this.page >= this.count){
				this.first();
			}
			var start = this.page*this.size;
			return $scope.vouchers.slice(start, start + this.size)
		},
		get count(){
			return Math.ceil(1.0 * $scope.vouchers.length / this.size);
		},
		get array(){
			return new Array(Math.ceil(1.0 * $scope.vouchers.length / this.size));
		}
		,
		gotoPage(pageIndex){
			this.page = pageIndex;
		},
		first(){
			this.page = 0;
		},
		last(){
			this.page = this.count - 1;
		},
		next(){
			this.page++;
		},
		prev(){
			this.page--;
		}
	}
});