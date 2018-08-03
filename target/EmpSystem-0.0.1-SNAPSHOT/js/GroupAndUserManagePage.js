/*---------------------------------原来的项目组管理的Js---------------------*/
var scene2 = ""; //项目组员场景变量(添加.删除.修改)
var scene1 = ""; //项目组场景变量(添加.删除.修改)
var groupCheckedId="";//被选中的项目的id
var groupCheckedName="";//被选中的项目的名字
var beforeUpdateGroup;//组员更改项目前所在项目组的值
//清空所有的复选框
var  clear=function(){
	$("input[type=radio]").attr("checked",false)
}
//把模态框的场景切换到项目组管理
var changSceneToGroup=function(){
	scene2="空";
	$("#mo1").attr("hidden", false);
	$("#mo2").attr("hidden", true);
}
//把模态框的场景切换到项目成员管理
var changSceneTomember=function(){
	scene1="空"
	$("#mo1").attr("hidden", true);
	$("#mo2").attr("hidden", false);
}
var add1 = function() {
	changSceneToGroup();
	//改变模态框大小
	$(".m-modal-dialog").css("height","290px");
	$(".m-bottom").css("margin-top","30px");
	$("#deleteInfo").attr("hidden", true)
	$("#inputInfo").attr("hidden", false)
	scene1 = "添加"
	var s = scene1
	$("#status").attr("hidden", true)
	$("#title1").html("添加项目组")
	var m21 = new MyModal.modal(function() {});
	m21.show();
}
var update1 = function() {
	changSceneToGroup();
	//改变模态框大小
	$(".m-modal-dialog").css("height","290px");
	$(".m-bottom").css("margin-top","30px");
	var ipts = $(":radio:checked").parents("tr");//获取选中的input
	var name = ipts.find("td[name='1']").html() //获取名字
	var type = $(":radio:checked").attr("leix") //类型
	var status = $(":radio:checked").attr("statu") //获取状态
	if(name==null){
		alert("", "请选择项目组", function () {}, {type: 'warning', confirmButtonText: 'OK'});

	return;
	}
	$("#groupName1").val(name); //绑定姓名的值
	$("#groupType").val(type); //绑定类型的值
	$("#statusValue").val(status); //绑定状态的值
	scene1 = "修改" //更换模态框的场景
	$("#deleteInfo").attr("hidden", true)
	$("#inputInfo").attr("hidden", false)
	var m22 = new MyModal.modal(function() {});
	$("#title1").html("修改项目信息")
	$("#status").attr("hidden", false)
	m22.show();
}
var delete1 = function() {
	changSceneToGroup();
	//改变模态框大小
	$(".m-modal-dialog").css("height","290px");
	$(".m-bottom").css("margin-top","30px");
	var ipts = $("input[name='groupName']:radio:checked").parents("tr");//获取选中的input
	var name = ipts.find("td[name='1']").html() //获取名字
	if(name==null){
alert("", "请选择项目组", function () {}, {type: 'warning', confirmButtonText: 'OK'});
	return;
	}
	scene1 = "删除"
	$("#deleteInfo").attr("hidden", false)
	$("#inputInfo").attr("hidden", true)
	var m23 = new MyModal.modal(function() {});
	$("#title1").html("删除项目组")
	$("#deleteGroupInfo").html(name)
	m23.show();
}

 
/* 调用数据加载接口 */
var loadGourpData = function($scope, $http, NetworkUtil) {
	 $("#dataTable").hide();
	 $("#loading_image").show();
	var info = {};
	// 数据请求成功函数
	var success = function(result) {
		//清空表格
		 $("#dataTable tr:not(.table_teb)")
		$("#dataTable tr:not(.table_teb)").remove(); 
		var list = result.data.result;
		for(a in list) {
			var l = list[a];
			var id = l.id;
			var name = l.groupName;
			var type ;
			if(l.groupType==0){
				type="开发组"
			}else if(l.groupType==1){
				type="UI组"
			}else if(l.groupType==2){
				type="测试组"
			}else {
				type="维运组"
			}
			var statu ;
			if(l.removedFlag==0){
				statu="正常"
			}else{
				statu="下线"
			}
			var creatTime = l.createdTime;
			var updateTime = l.lastMofidiedTime;
			//往表格中添加数据
			if(a%2==0){
			$("#tbody").append(' <tr style=" background: #fff;" ><td ><input  statu="' + l.removedFlag + '" leix="' + l.groupType + '" id="' + id + '" type="radio" name="groupName" onclick="c()" /></td> <td name="1">' + name + ' </td><td name="2">' + type + '</td><td name="3">' + statu + '</td> </tr>')
			}else{
			$("#tbody").append(' <tr><td   ><input  statu="' + l.removedFlag + '" leix="' + l.groupType + '" id="' + id + '" type="radio" name="groupName" onclick="c()"  /></td> <td name="1">' + name + ' </td><td name="2">' + type + '</td><td name="3">' + statu + '</td></tr>')
			}
		}
		$("#loading_image").hide();
			$("#dataTable").show();
	};
	// 登陆失败函数
	var faild = function(result) {
		alert("网络错误!")
	};
	NetworkUtil
		.get($http, '../service/group/queryAll', info, success, faild);
}


/*---------------------------------原来的项目组成员管理的Js---------------------*/

var groupList;//项目组集合
var groupNameAndTypeList=[];//把项目集合转换成项目名字加类型
var typeArray=["开发组","UI组","测试组","运维组"];
var userList;//用户集合
var init=function(){//初始化模态框里的项目名称
	$("#groupName").empty();//清空项目名称的下拉框
	groupNameAndTypeList.length=0;//清空集合
	for(a in groupList){
		var type=groupList[a].groupType;
		var s=groupList[a].groupName+" "+typeArray[type];
		groupNameAndTypeList.push(s);
	}
	for(i in groupNameAndTypeList){
		var info=groupList[i]
	 $("#groupName").append( '<option class="groupName" value='+info.id+' >'+groupNameAndTypeList[i]+' </option>')
	}
}
//成员姓名搜索的多选框点击事件
var changeName=function(){
	var input = $(".select22:radio:checked");
	var t= input.attr("realname")
	$("#memberName").val(t)
} 
//点击姓名选择确认键
$("#confirmmember").click(function(){
		$(".hidden1").show();
	$("#selectMemberName").attr("hidden",true);//显示
	$("#ModelConfrim").attr("hidden", false);//点击完名字确认后显示模态框的确认键
	$("#confirmmember").attr("hidden", true);//隐藏选择名字的确认键
	$("#time").show();//显示添加时间的显示控件
})
//点击搜索进行模糊查询
var likeFind=function($scope, $http, NetworkUtil){
	var query=function(){
				$("#nameBody").empty();
	var info = {}
			info.inputName = $("#inputName").val();
			// 添加成功函数
			var success = function(result) {
				userList=result.data.result;
				for(a in userList){
					var id=userList[a].id;
					var realName=userList[a].realname;
					$("#nameBody").append('<tr><td><input   class="select22" onclick="changeName()" type="radio" name="user" uid="'+ id+'" realName="'+realName+'" /> </td> <td> <span>'+realName+'</span></td></tr>');
				}
			};
			// 添加失败函数
			var faild = function(result) {
			};
		
	 NetworkUtil.get($http, '../service/groupAndUser/likeFind', info, success, faild);
			
	}
	 //点击名字查询所有的用户
	$scope.showName=function(){
	$("#ModelConfrim").attr("hidden", true);//隐藏模态框的确认键
	$("#confirmmember").attr("hidden", false);//显示选择的时候的确认键
	$("#time").hide();//隐藏添加时间的显示控件
	$(".hidden1").hide();
	$("#selectMemberName").attr("hidden",false);//显示
	query();
	}
	$scope.insert2=function(){
		query();
}
}
var addmember = function() {
	changSceneTomember();
	/*改变模态框大小*/
	$(".m-modal-dialog").css("height","336px");
	$(".m-bottom").css("margin","-2px");
	$("#isLeader").val(0);
	$("#memberName").attr("disabled",false);
	$("#memberName").val(""); //清空用户名的值
		init();
		$("#groupName").val(groupCheckedId); //清空用户名的值	
	$("#deletememberInfo").attr("hidden", true)
	$("#memberinputInfo").attr("hidden", false)
	$("#memberStatus").attr("hidden", true);//隐藏成员信息这一栏
	$("#time").show();//显示离开时间的控件
	$("#memberTimeName").html("加入时间");//修改时间控件的名字
	scene2 = "添加"
	$("#title1").html("添加项目组员信息")
	var m11 = new MyModal.modal(function() {});
	m11.show();
}
var updatemember = function() {
	changSceneTomember();
	$("#time").hide();//显示离开时间的控件
	$("#memberTime").val("");//清空选择时间的控件内容
	/*改变模态框大小*/
	$(".m-modal-dialog").css("height","336px");
	$(".m-bottom").css("margin","-2px");
	var ipts = $("input[name='xuanxiang']:radio:checked").parents("tr");//获取选中的input
	var groupName = ipts.find("td[name='2']").html() //获取名字
	var realName = ipts.find("td[name='1']").html() //获取名字
	var isLeader =  $("input[name='xuanxiang']:radio:checked").attr("isLeader") //类型
	var isRemove =  $("input[name='xuanxiang']:radio:checked").attr("isRemove") //类型
		init(); //然后把所有的项目组加入
 var gId=$("input[name='xuanxiang']:radio:checked").attr("gId"); //获取id
	if(groupName==null){
		alert("请选择组员!")
	return;
	}
	$("#memberStatus").attr("hidden", false);// 显示成员状态
	$("#memberName").attr("disabled",true);
	$("option.groupName[value="+gId+"]").attr("selected","selected")//绑定项目名称
	beforeUpdateGroup=gId//把修改前的值传递到全局变量
	$("#isLeader").val(isLeader);
	$("#isRemove").val(isRemove);
	$("#memberName").val(realName); //绑定用户名的值
	scene2 = "修改" //更换模态框的场景
	$("#deletememberInfo").attr("hidden", true)
	$("#memberinputInfo").attr("hidden", false)
	var m12 = new MyModal.modal(function() {});
	$("#title1").html("修改组员信息")
	m12.show();
	/*监听成员状态是否改变,如果改变就显示离开时间*/
	$("#isRemove").change(function(){
		if($(this).val()==1){
			/*改变模态框大小*/
			$(".m-modal-dialog").css("height","377px");
			$(".m-bottom").css("margin","22px");
			$("#memberTimeName").html("离开时间");
			$("#time").show();//显示离开时间的控件
		}
	})
}
var deletemember = function() {
	changSceneTomember();
	/*改变模态框大小*/
	$(".m-modal-dialog").css("height","336px");
	$(".m-bottom").css("margin","-2px");
	var ipts =$("input[name='xuanxiang']:radio:checked").parents("tr");//获取选中的input
	var name = ipts.find("td[name='1']").html() //获取名字
	if(name==null){
		alert("请选择组员!")
	return;
	}
	scene2 = "删除"
	$("#deletememberInfo").attr("hidden", false)
	$("#memberinputInfo").attr("hidden", true)
	var m13 = new MyModal.modal(function() {});
	$("#title1").html("删除组员信息")
	$("#deletememberName").html(name)
	m13.show();
}
//点击确认函数
var isConfirms = function($scope, $http, NetworkUtil) {
	$scope.quxiaomember=function(){
		//点击取消同事作用于名字选择小模态框
		$(".hidden1").show();
	$("#selectMemberName").attr("hidden",true);//显示
		clear();
		clear();
	}
	$scope.confirmmember = function() {
		/*------------------项目组管理的点击确认------------------*/
		$scope.quxiao1=function(){
			clear();
		}
		var n = scene1
		if(scene1 == "添加") {
			var info = {}
			info.groupName = $("#groupName1").val();
			info.groupType = $("#groupType").val();
			// 添加成功函数
			var success = function(result) {
				//刷新页面
				loadGourpData($scope, $http, NetworkUtil);
				queryGroupData();
			};
			// 添加失败函数
			var faild = function(result) {
				alert(result.data.msg)
			};
			NetworkUtil.get($http, '../service/group/add', info, success, faild);
		clear();
		}
		if(scene1 == "删除") {
			var info = {}
			info.id=$("input[name='groupName']:radio:checked").attr("id")//获取所选id
			// 添加成功函数
			var success = function(result) {
				//刷新页面
				loadGourpData($scope, $http, NetworkUtil);
				queryMemberById();//刷新成员表
				queryGroupData();//重新查询工作组
			};
			// 添加失败函数
			var faild = function(result) {
				alert(result.data.msg)
			};
			NetworkUtil.get($http, '../service/group/delete', info, success, faild);
		clear();
		}
		if(scene1 == "修改") {
			var info = {}
			info.status = $("#statusValue").val();
			info.groupName = $("#groupName1").val();
			info.groupType = $("#groupType").val();
			info.id=$("input[name='groupName']:radio:checked").attr("id");
			// 添加成功函数
			var success = function(result) {
				//刷新页面
				loadGourpData($scope, $http, NetworkUtil);
				groupCheckedId=0;//被选中的项目的id
				groupCheckedName="";//被选中的项目的名字
				var groupName = $("#groupName1").val();
			};
			// 添加失败函数
			var faild = function(result) {
				alert(result.data.msg)
			};
			NetworkUtil.get($http, '../service/group/update', info, success, faild);
		clear();
		
		}
		
		/*-----------------项目成员管理的点击确认-----------------------------*/
		var n = scene2
		if(scene2 == "添加") {
			var info = {}
			var jionTime=$("#memberTime").val();
			info.date=jionTime;
			info.id=$("input[name='user']:radio:checked").attr("uid")//获取所选id
			info.groupName = $("#groupName").val();
			info .isLeader=$("#isLeader").val();
			// 添加成功函数
			var success = function(result) {
				queryMemberById()
			};
			// 添加失败函数
			var faild = function(result) {
				alert(result.data.msg)
			};
			NetworkUtil.get($http, '../service/groupAndUser/add', info, success, faild);
		clear();
		}
		if(scene2 == "删除") {
			var info = {}
			info.id=$("input[name='xuanxiang']:radio:checked").attr("id")//获取所选id
			// 添加成功函数
			var success = function(result) {
				queryMemberById()
			};
			// 添加失败函数
			var faild = function(result) {
				alert(result.data.msg)
			};
			NetworkUtil.get($http, '../service/groupAndUser/delete', info, success, faild);
		clear();
		}
		if(scene2 == "修改") {
			var input = $("input[name='xuanxiang']:radio:checked");//获取选中的input
			var info = {}
			var leaveTime=$("#memberTime").val();//获取选中的值
			var id=input.attr("id");
			var boolean=beforeUpdateGroup==$("#groupName").val();
			info.isChange=boolean;
			info.date=leaveTime;
			info.id = id;
			info.groupName = $("#groupName").val();
			info.memberName= input.attr("uId");
			info .isLeader=$("#isLeader").val();
			var remove=$("#isRemove").val();
			info.isRemove = remove;
			// 添加成功函数
			var success = function(result) {
				queryMemberById()
			};
			// 添加失败函数
			var faild = function(result) {
				alert(result.data.msg)
			};
			NetworkUtil.get($http, '../service/groupAndUser/update', info, success, faild);
		clear();
		$("#memberStatus").attr("hidden", true);//隐藏成员信息这一栏
		}
	}

}
var app = angular.module("myApp", []);
// 网络模块函数
app.factory('CyberNetworkServices', function() {
	var factory = {};
	factory.get = function($http, url, data, successFn,
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
		$http.post(url, data, postCfg).then(
			function successCallback(result) {
				if(result.data.code == 0) {
					successFn(result);
				}else {
					falidFn(result);
				}
			},
			function errorCallback(result) {
			})
	}
	return factory;
});
// 服务这里依赖注入了function函数 CyberNetworkServices
app.service("NetworkUtil", function(CyberNetworkServices) {
	this.get = function(http, url, data, successFn, falidFn) {
		return CyberNetworkServices.get(http, url, data, successFn, falidFn);
	}
});
                        /* angularJs的控制器*/
app.controller("myController", function($scope, $http, NetworkUtil) {
	likeFind($scope, $http, NetworkUtil); //模糊查询
	queryGroupData( ); //加载项目组数据
	loadGourpData($scope, $http, NetworkUtil); //加载数据
	isConfirms($scope, $http, NetworkUtil); //
})
//创建成员表
 function createMemberTable(result1){
	var list = result1.result ;
	//清空表格
		$("#memberdataTable tr:not(.membertable_teb)").empty();
		$("#memberdataTable tr:not(.membertable_teb)").each(function() {
			$(this).remove();
		})
	for(a in list) {
		var l = list[a];
		var id=l.id;
		var groupId = l.groupId;
		var userId=l.userId;
		var groupName = l.groupName;
		var groupT = l.groupType;
		var realname = l.realname;
		var createTime=l.createTime;
		var leaveTime=l.leaveTime;
		var status ;
		var groupType1=typeArray[groupT];
		if(createTime==null||createTime==""){
			createTime="...";
		}
		if( l.removedFlag==0 ){
			status="正常";
			leaveTime="..."
		}else{
			status="已移除";
		}
		var isLeader ;
		if(l.isLeader==0){
			isLeader="组员"
		}else{
			isLeader="组长"
		}
		//往表格中添加数据
		if(a%2==0){
		$("#membertbody").append(' <tr style=" background: #fff;" ><td><input isRemove="'+l.removedFlag+'" isLeader="'+l.isLeader+'" gId="'+groupId+'" uId="'+userId+'" id="' + id + '" type="radio" name="xuanxiang"/></td> <td name="1">' + realname + ' </td><td name="2">' + groupName+' '+groupType1 + '</td><td name="3">' + isLeader + '</td><td >' + status + '</td><td >' + createTime + '</td><td >' + leaveTime + '</td></tr>')
		}else{
    $("#membertbody").append(' <tr ><td><input isRemove="'+l.removedFlag+'" isLeader="'+l.isLeader+'" gId="'+groupId+'" uId="'+userId+'" id="' + id + '" type="radio" name="xuanxiang" /></td> <td name="1">' + realname + ' </td><td name="2">' + groupName+' '+groupType1 + '</td><td name="3">' + isLeader + '</td><td >' + status + '</td><td >' + createTime + '</td><td >' + leaveTime+ '</td></tr>')

		}
	}
}
//点击项目组的单选框
 function c(){
	var ipts = $("input[name='groupName']:radio:checked").parents("tr");//获取选中的项目组的input
	groupCheckedName= ipts.find("td[name='1']").html() //获取名字
	  groupCheckedId=$("input[name='groupName']:radio:checked").attr("id")//获取所选id
	  queryMemberById()
}
 	function  queryMemberById(){
 		 $("#memberdataTable").hide();
 		 $("#loading_image1").show();
 		$.ajax({
 			type : "POST",
 			url : '../service/groupAndUser/queryByGroupId' ,
 			data : {"id":groupCheckedId},
 			success : function(result) {
 				if (result.code ==0) {
 					createMemberTable(result);
 					$("#loading_image1").hide();
 					$("#memberdataTable").show();
 				} else {
 					alert(result.msg);
 				}
 			}
 		});
 }
/* 调用数据加载项目成员的信息 */
var queryGroupData = function() {
	$.ajax({
			type : "POST",
			url : '../service/groupAndUser/queryAll' ,
			data : {},
			success : function(result) {
				if (result.code ==0) {
					groupList=result.result;
				} else {
					alert(result.msg);
				}
			}
		});
}
 
