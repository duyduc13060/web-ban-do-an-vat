app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};


    $scope.initialize = function () {
        // load product
        $http.get("/rest/products").then(response => {
            // lấy ra sản phẩm vào vào bên trong items
            $scope.items = response.data;
            $scope.items.forEach(item => {
                // chuyển đổi ngày tháng dữ liệu được tải sang javascrip
                item.createDate = new Date(item.createDate);
            });
        })


        // load category
        $http.get("/rest/categories").then(response => {
            // lấy ra sản phẩm vào vào bên trong items
            $scope.cates = response.data;
        })

    }
    // khởi đầu
    $scope.initialize();

    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: '',
            available: true,
        }
    }

    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show')
    }

    $scope.create = function () {
        // lấy thông tin trên form
        var item = angular.copy($scope.form);
        $http.post("/rest/products", item).then(response => {
            // chuyển ngày sang javascrip
            response.data.createDate = new Date(response.data.createDate)
            // bỏ vào trong list(items)
            $scope.items.push(response.data);
            $scope.reset();
            alert("Thêm mới sản phẩm thành công");
        }).catch(error => {
            alert("create lỗi")
            console.log("Error", error);
        })
    }

    $scope.update = function () {
        // lấy dũ liệu ở trong form
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item)
            .then(response => {
                // tìm id trong items mà từ server trả về
                var index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                alert("Cập nhật thành công");
            }).catch(error => {
                alert("Lỗi")
                console.log("error", error);
            })
    }

    $scope.delete = function (item) {
       $http.delete(`/rest/products/${item.id}`)
           .then(response => {
               // sản phẩm hiện tại trong cái list phía client 
               var index = $scope.items.findIndex(p => p.id == item.id);
              
               $scope.items.splice(index,1);
               $scope.reset();
               alert("Delete thành công");
           }).catch(error => {
               alert("Lỗi")
               console.log("error", error);
           })
    }

    // up load hình
    $scope.imageChanged = function (files) {
        // tạo đối tượng formdata
        var data = new FormData();
        // lấy file người dùng chọn bỏ vào form data
        data.append('file', files[0]);

        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(response => {
            // lấy name trong data bỏ vào imgege ở trong form
            $scope.form.image = response.data.name;
        }).catch(error => {
            alert("upload lỗi")
            console.log("Error", error);
        })
    }

    // tạo đối tượng bên trong scope
    $scope.pager={
        page:0,
        size:9,
        // lấy các sản phẩm
        get items(){
            var start = this.page * this.size;
            // lấy các sản phẩm bên trong scope
            //slice(lấy 1 trang) các mặt hàng bên trong
            return $scope.items.slice(start,start + this.size);
        },
        get count(){
            // tổng số sản phẩm chia cho kích thươc mỗi trang
            return Math.ceil(1.0 * $scope.items.length / this.size)
        },
        
        first(){
            this.page = 0;
        },

        prev(){
            this.page--;
            if(this.page < 0){
                this.last();
            }
        },

        next(){
            this.page++;
            if(this.page >= this.count){
                this.first();
            }
        },

        last(){
            this.page = this.count - 1;
        }

    }
});