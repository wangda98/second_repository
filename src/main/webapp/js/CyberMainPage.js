var app = angular.module("myApp", []);

// 点击二级菜单函数
function onClickCyberMenuItem(self) {
	var n = self.id;
	var lable = $("#" + n)
	var url = lable.attr("menuUrl")
	var tabName = lable.attr("name")
	var type = lable.attr("menuType")
	//第一次点击
	if(type == 0) {
		if(url !=null){
			lable.attr("menuType", 1)
		$("iframe").hide(); //获取iframe标签集合并且隐藏
		//添加的标签以及标签页面默认是显示的
		$("#labels").append('<button onclick="opentab(this)" pid=' + n + ' class="right_top" menuUrl=' + url + '>' + tabName + '<span onclick="closeCyberTab(this)" class="label">x</span> </button>')
		$("#tables").append('<iframe remove="0" class="iframe" pid=' + n + ' src=" ' + url + ' "></iframe>')
		$(".right_top").css("color", "#000") //改变所有标签的颜色000
		var div = $(".right_top[pid=" + n + "]")
		div.css("color", "#04b8dc") //设置当前标签颜色
		$("#fancybox-frame").show(); //显示天气
		}
	}else{ //第二次点击
		$("iframe").hide(); //获取iframe标签集合并且隐藏
		$("#fancybox-frame").show(); //显示天气
		var pp = $("iframe[pid=" + n + "]");
		pp.show(); //激活当前tab页
		//添加的标签以及标签页面默认是显示的
		$(".right_top").css("color", "#000") //改变所有标签的颜色000
		var div = $(".right_top[pid=" + n + "]")//获取当前标签
		div.css("color", "#04b8dc") //设置当前标签颜色
		
	}
}
// 点击关闭标签
function closeCyberTab(self) {
	var p = $(self).parent() //得到父元素
	var num = p.attr('pid'); //获取父元素的pid
	var lable = $("#" + num).attr("menuType", 0) //把二级菜单的属性改回来
	$(self).parent().remove();
	var nds = $("iframe[pid=" + num + "]")
	nds.attr("remove", 1)
	var shuxing = nds.attr("remove")
	nds.remove() //移除当前标签对应的页面
	//同时要激活一个标签
	var ls = $(".right_top")[0]
	var p = $(ls)
	switchCyberTab(p)
}
//刷新图片点击函数
function refreshCyberTab() {
	$("iframe").each(function() {
		var show = $(this).css("display");
		if(show == "inline" && $(this).attr("pid") != null) {
			var s = this;
			this.contentWindow.location.reload(true);
		}
	})
}
//激活标签点击函数
function opentab(self) {
	var p = $(self) //得到jquery对象
	switchCyberTab(p);
}
//激活的方法
function switchCyberTab(p) {
	var num = p.attr('pid'); //获取元素的pid
	var remove = $("iframe[pid=" + num + "]").attr("remove"); //判断是否被移除
	var length = $(".right_top").length
	if(length == 0) {
		$("iframe[pid=0]").show();
	}
	if(remove == 0) {
		$(".right_top").css("color", "#000") //改变所有标签的颜色
		p.css("color", "#04b8dc") //改变当前标签颜色EEEEEE
		$("iframe").hide(); //获取所有的iframe标签集合并且隐藏
		var pp = $("iframe[pid=" + num + "]");
		pp.show(); //激活当前tab页
		$("#fancybox-frame").show(); //显示天气
	}
}
// 点击一级菜单函数
var click1 = function(self) {
	var id = $(self).attr("id").substring(2); //获取一级标签的数据库id
	if($("#tb" + id + "2").attr("hidden") == "hidden") {
		$("#tb" + id + "2").attr("hidden", false)
	} else {
		$("#tb" + id + "2").attr("hidden", true)
	}
}
// 网络模块函数
app.factory('CyberNetworkServices', function() {
	var factory = {};
	factory.post = function($http, url, data, successFn,
		falidFn) {
		// 把Json格式数据转换成我们需要的 name:value 格式
		var transFn = function(data) {
			return $.param(data);
		};
		var postCfg = {
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
				'X-Requested-With': 'XMLHttpRequest'
			},
			transformRequest: transFn
		};
		$http.get(url, data, postCfg).then(
			function successCallback(result) {
				if(result.data.code == 0) {
					successFn(result);
				} else {
					falidFn(result);
				}
			},
			function errorCallback(result) {})
	}
	return factory;
});
// 服务这里依赖注入了function函数 CyberNetworkServices
app.service("NetworkUtil", function(CyberNetworkServices) {
	this.cPost = function(http, url, data, successFn, falidFn) {
		return CyberNetworkServices.post(http, url, data, successFn, falidFn);
	}
});
app.controller("myController", function($scope, $http, $interval, NetworkUtil) {
	$interval(function() { cyberHeart($scope, $http, NetworkUtil) }, 2 * 60 * 1000)
	loadCyberMainMenu($scope, $http, NetworkUtil); //执行登陆页面
	cyberLogout($scope, $http, NetworkUtil); //执行登出
})
/* 调用心跳接口函数 */
var cyberHeart = function($scope, $http, NetworkUtil) {
	// 登陆失败函数
	var success = function(result) {};
	// 登陆失败函数
	var faild = function(result) {};
	var info = {}
	NetworkUtil.cPost($http, '../service/main/cyberHeart', info, success, faild);
}
// 页面注销时调用登出接口
/* 调用登出接口函数 */
var cyberLogout = function($scope, $http, NetworkUtil) {
	$scope.logout = function() {
		var m11 = new MyModal.modal(function() {});
		m11.show();
		$("#inputInfo").html("确认是否退出登录?")
		$scope.confirm=function(){
			// 请求失败函数
			var success = function(result) {
				window.location.href = result.data.target; //跳转到指定页面
			}; // 请求失败函数
			var faild = function(result) {
				alert("网络异常")
			};
			var info = {}
			NetworkUtil.cPost($http, '../service/user/cyberLogout', info, success, faild); 	
		}
	}
}
// 页面加载时调用主菜单接口
/* 调用登陆接口函数 */
var loadCyberMainMenu = function($scope, $http, NetworkUtil) {
	$scope.show1 = false;
	$scope.show2 = true;
	$scope.show3 = true;
	var info = {};
	// 登陆成功函数
	var success = function(result) {
		// 左上方的时间区域
		function update() {
			var date = new Date(); // 获取当前的时间
			var weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
			var hour = date.getHours() / 10 < 1 ? ("0" + date.getHours()) : (date.getHours());
			var minute = date.getMinutes() / 10 < 1 ? ("0" + date.getMinutes()) : (date.getMinutes())
			$("#hour").html(hour + ":" + minute);
			$("#date").html(
				date.getFullYear() + "-" + (date.getMonth() + 1) + "-" +
				date.getDate() + "&nbsp;&nbsp;" +
				weeks[date.getDay()]);
			window.setTimeout(update, 1000)
		}
		update(); // 执行时间更新函数
		var results = result.data.result; // 得到一个Object数组
		var account = results[0]; // 获取到用户名
		$("#account").html(account);
		var list = results[1]; // 得到所有的菜单对象
		//console.log(list)
		var list1 = [] // 定义一个一级目录的集合对象
		var list2 = [] // 定义一个二级目录的集合对象
		for(a in list) { // 分别删选出一级菜单一级二级菜单数组
			if(list[a].level == 1) {
				list1.push(list[a])
			} else if(list[a].level == 2) {
				list2.push(list[a])
			}
		}
		// 将一级菜单放入html中
		console.log(list1)
		for(n in list1) {
			var id = list1[n].id;
			var level = list1[n].level;
			var name = list1[n].name;
			var parentId = list1[n].parentId;
			var removeFlag = list1[n].removeFlag;
			var type = list1[n].type;
			if(removeFlag == 0) { //生成一级菜单一级二级菜单的li标签
				if(type==2){
					$("#daoHang").append(
					' <div class="biaoti_1" id="tb' + id + '" onclick="click1(this)">' + name + '</div><ul id="tb' + id + '2" ></ul> '
				);
				}else{
					$("#daoHang").append(
					' <div class="biaoti_1"  id="tb' + id + '" onclick="click1(this)">' + name + '</div><ul id="tb' + id + '2" hidden="hidden"></ul> '
				);
				}
				
			}
			for(b in list2) {
				if(list2[b].parentId == list1[n].id) {
					var url = list2[b].url;
					var image = list2[b].icon;
					var type = list2[b].type
					var id = list2[b].id;
					var name = list2[b].name;
					var tab = $("#tb" + list1[n].id + "2");
					var removeFlag = list2[b].removeFlag;
					if(removeFlag == 0) {
						tab.append('<li name=' + name + ' id=' + id + '  onclick="onClickCyberMenuItem (this)" menuType=' + type + ' menuUrl="' + url + '" class="zgzdbcx_images"><img src=' + image + ' width="26" height="26" />' + name + '</li>')
					}
				}
			}
		}
	};
	// 登陆失败函数
	var faild = function(result) {
		alert("登陆失败,请重新登陆")
	};
	NetworkUtil
		.cPost($http, '../service/main/cyberMainMenu', info, success, faild);
}