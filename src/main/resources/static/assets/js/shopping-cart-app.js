const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
    $scope.cart = {
        items: [],
        // thêm sản phẩm vào giỏ hàng
        add(id) {
            // kiểm tra xem mặt hàng đã có hay không
            var item = this.items.find(item => item.id == id);
            
            // nếu có tăng số lương lên
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            }
            else {
                // tải sản phẩm ở trên service về thông qua một API
                $http.get(`/rest/products/${id}`).then(response => {
                    response.data.qty = 1; // thiết lập số lượng bằng 1
                    this.items.push(response.data);// add vào mảng items
                    this.saveToLocalStorage();
                })
            }
        },

        //xóa sản phẩm khỏi giỏ hàng
        remove(id) { 
            // tìm vị trí sản phẩm nằm trong giỏ hàng
            var index = this.items.findIndex(item => item.id == id);

            this.items.splice(index,1);
            this.saveToLocalStorage();
        },

        // xóa sạch các mặt hàng trong giỏ
        clear() { 
            this.items = [];
            this.saveToLocalStorage();
        },

        // tính thành tiền của 1 sản phẩm
        amt_of(item) { },

        // tính số lượng các mặt hàng trong giỏ
        get count(){
            return this.items
                  .map(item => item.qty)//lấy ra số lượng
                  .reduce((total,qty) => total += qty, 0);
        },

        // tổng thành tiền các mặt hàng trong giỏ
        get amount(){
            return this.items
                  .map(item => item.qty * item.price)
                  .reduce((total,qty) => total +=qty,0);
        },

        // lưu giỏ hàng vào local storage
        saveToLocalStorage(){
            // đổi cái mảng items(sản phẩm) sang json
            var json = JSON.stringify(angular.copy(this.items));

            // dùng chuỗi json lưu vào localStorage với tên là cart
            localStorage.setItem("cart",json);
        },

        // đọc giỏ hang từ local storage
        loadFromLocalStorage(){
            // đọc lại cart trong localstore
            var json = localStorage.getItem("cart");

            //nếu có chuyển sang json
            //nếu không có chuyển sang [] rỗng
            // gán vào items
            this.items = json ? JSON.parse(json) :[];
        },

    }

    // tải lại toàn bộ giỏ hang lưu trong Storage
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate:new Date(),
        address:"",
        account: {
            username: $("#username").text()
        },
        // toàn bộ các mặt hàng trong giỏ được chuyển thành orderDetails
        get orderDetails(){
            // duyêt qua các mặt hang trong giỏ hàng
            // mỗi mặt hàng tạo ra orderDetails
            return $scope.cart.items.map(item =>{
                return {
                    product:
                    {
                        id: item.id
                    },
                    price: item.price,
                    quantity: item.qty,
                }
            });
        },
        purchase(){
            // gửi thông tin lên server
            // lấy ra order hiện tại
            var order = angular.copy(this);

            // thực hiện đặt hàng
            $http.post("/rest/orders",order).then(response =>{
                alert("Đặt hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + response.data.id;
            }).catch(error =>{
                alert("Đặt hàng lỗi");
                console.log(error);
            })
        }
    }
    
})