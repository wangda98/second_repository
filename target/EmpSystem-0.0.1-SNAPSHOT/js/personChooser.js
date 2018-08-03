var tableData;

$(function(){
	onCyberWidgetLoad();
})

//控件加载时
function onCyberWidgetLoad(){
	$.ajax({
		type : "POST",
		url : "../service/workanalysis/getUserPostInfo",
		data : {},
		success : function(r) {
			if(r.code == 1){
				tableData = r.result;
				createEmpTable(tableData);
			}else{
				alert(r.msg);
			}
		}
	});
}

//点击筛选按钮
function onCyberRecordFilter(){
	var name = $("#filterName").val();
	$.ajax({
		type : "POST",
		url : "../service/workanalysis/FilterUserPostInfo",
		data : {'filterName':name},
		success : function(r) {
			if(r.code == 1){
				tableData = r.result;
				createEmpTable(tableData);
			}else{
				alert(r.msg);
			}
		}
	});
}

//点击确定按钮
function onCyberConfirmChoice(){
	var names = "";
	$("#empChooseTable tr:gt(0)").each(function(){
		//alert($(this).children('td').eq(0).text());
		names += $(this).children('td').eq(0).text() + ",";
	});
	names = names.substring(0,names.length-1);
	//alert(names);
	return names;
}

//点击已选员工选项
function onCyberRemoveChoice(tr){
	tr.appendTo($("#empTable"));
	if($("#empChooseTable tr").length==1){
		$("#empChooseDiv").hide();
		$("#empDiv").css("height","340");
	}
}

//点击未选员工选项
function onCyberAddChoice(tr){
	tr.appendTo($("#empChooseTable"));
	$("#empChooseDiv").show();
	$("#empDiv").css("height","180");
}

//创建员工信息表
function createEmpTable(tableData){
	var table = $("#empTable");
	$("#empChooseDiv").hide();
	$("#empDiv").css("height","340");
	//清空表单
	$("#empTable tr").empty();
	$("#empTable tr").each(function(){
		$(this).remove();
	})
	$("#empChooseTable").empty();
	$("#empChooseTable tr").each(function(){
		$(this).remove();
	})
	createHead(table);
	createHead($("#empChooseTable"));
	for (var i in tableData) {
		var tr=$("<tr></tr>");
        tr.appendTo(table);
        var td=$("<td>"+"<input type='checkbox' onchange='isCheck(this)'/>"
        		+tableData[i].realname+"</td>");
        td.appendTo(tr);
        var td=$("<td>"+tableData[i].postName+"</td>");
        td.appendTo(tr);
        var td=$("<td>"+tableData[i].postType+"</td>");
        td.appendTo(tr);
	}
}

function createHead(table){
	var tr=$("<tr style='background-color:#e5f6f9;'></tr>");
    tr.appendTo(table);
    var td=$("<th>"+"姓名"+"</th>");
    td.appendTo(tr);
    var td=$("<th>"+"岗位级别"+"</th>");
    td.appendTo(tr);
    var td=$("<th>"+"类型"+"</th>");
    td.appendTo(tr);
}

//判断是否选中
function isCheck(ck){
	if($(ck).prop('checked')){
		onCyberAddChoice($(ck).parent().parent());
	}else{
		onCyberRemoveChoice($(ck).parent().parent());
	}
}