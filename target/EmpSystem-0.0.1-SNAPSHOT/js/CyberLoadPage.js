var url;//返回的跳转路径
// 网络模块函数
var app=angular.module("myApp",[]);
app.factory('CyberNetworkServices',function() {
					var factory = {};
					factory.post = function($http, url, data, successFn,
							falidFn) {
						//把Json格式数据转换成我们需要的 name:value 格式
						var transFn = function(data) {
							return $.param(data);
						};
						var postCfg = {
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8',
								'X-Requested-With' : 'XMLHttpRequest'
							},
 							transformRequest : transFn
						};
						$http.post(url,data,postCfg).then(
								function successCallback(result) {
									if (result.data.code == 0) {
										successFn(result);
									} else if (result.data.code== 9) {
										var m11 = new MyModal.modal(function() {});
										m11.show();
										$("#inputInfo").html(result.data.msg)
										falidFn(result);
									} else {
										 falidFn(result);
									}
							    }, function errorCallback(result) {
							    	falidFn(result);
							}		
						)
					}
					return factory;
				});
// 服务这里依赖注入了function函数 CyberNetworkServices
app.service("NetworkUtil", function(CyberNetworkServices) {
	this.cPost = function(http, url, data, successFn, falidFn, page, isJson) {
		return CyberNetworkServices.post(http, url, data, successFn, falidFn);
	}
});
app.controller("myController",function($scope, $http, NetworkUtil){
	image($scope);
	initialize($scope, $http, NetworkUtil);
})
//验证码函数
	var image=function($scope){
	$scope.img = "../getImageCode"; /* 初始化图片 */
	$scope.updata = function() { /* 点击更换图片 */
		$scope.img = "../getImageCode?a=" + Math.random();
	}
}
//点击登录
	/*调用接口函数  */
	var initialize = function($scope, $http, NetworkUtil) {
	$scope.onCyberPageLogin = function() {
			var info = {}//定义一个Json对象
			info.account = $("#acc").val();//获取用户名
			info.rand_code = $("#verfcode").val();//获取验证码
			var password = $("#pwd").val();//获取密码
			password = getHashFromPass(password);
			var pass_hash = ""
			for (a in password) {
				pass_hash += password[a]
			}
			info.pass_hash = pass_hash;
			//登陆成功函数
			var success = function(result) {
				window.location.href=result.data.target; 	
			};
			//登陆失败函数
			var faild = function(result) {
				var m11 = new MyModal.modal(function() {});
				m11.show();
				$("#inputInfo").html(result.data.msg)
				$scope.confirm=function(){
					window.location.href=result.data.target; 	
				}
			};
			NetworkUtil.cPost($http, '../service/user/cyberLogin', info,
					success, faild);
		};
	}