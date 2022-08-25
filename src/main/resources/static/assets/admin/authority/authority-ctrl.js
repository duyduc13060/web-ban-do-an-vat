    app.controller("authority-ctrl", function ($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.intialize = function () {
        // load all roles
        $http.get("/rest/roles").then(response => {
            $scope.roles = response.data;
        })

        // load authorities od staff and director
        $http.get("/rest/accounts?admin=true").then(response => {
            $scope.admins = response.data;
        })

        // load các quyền được cấp
        $http.get("/rest/authorities?admin=true").then(response => {
            $scope.authorities = response.data;
        }).catch(error => {
            $location.path("unauthorized");
        })

    }

    $scope.authority_of = function (acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.accountAuth.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function(acc,role){

        // tim acc and role trong authoriti
        var authority = $scope.authority_of(acc,role);

        if(authority){//đã cấp quyền => thu hồi quyền xóa
            $scope.revoke_authority(authority);
        }
        else{// chưa cấp quyền => cấp quyền(thêm mới)
            authority = {
                account: acc, role: role};
            $scope.grant_authority(authority);
        }
    }

    // thêm mới 
    $scope.grant_authority = function(authority){
        $scope.post("/rest/authorities", authority).then(response=>{

            // khi thêm thành công thì lấy response.data bổ sung vào mảng authorities
            $scope.authorities.push(response.data)
            alert("cap quỷn thành cong")
        }).catch(error=>{
            alert("lôi cap quyen")
            console.log("Error", error)
        })
    }

    // xóa authoriti
    $scope.revoke_authority = function(authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(response=>{
            
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index,1);
            alert("thu hôi thanh cong")
        }).catch(error =>{
            alert("thu hồi quyên that bại")
            console.log("Error", error);

        })

    }


});