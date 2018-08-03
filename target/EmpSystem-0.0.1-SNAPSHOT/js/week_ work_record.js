	
	$(function () {
		getWeek();
		addObjectSelect();
	})
	
	// 发起Ajax请求获取所以项目名称
	function addObjectSelect() {
		var url="../service/workanalysis/getWorkInfoForWeekObject";
		var param="";
		$.post(url,param,function(object){
			addOption(object.result);
		})
		
	}
	
	
	// 点击姓名输入项
	function onCyberOpenChoosePersonWidget() {
		var m1 = new MyModal.modal(function() {
			var names = onCyberConfirmChoice();
			$("#names").val(names);
		});
		m1.show();
	}
	// 点击确定按钮
	function onCyberConfirmChoice(){
		var names = "";
		$("#empChooseTable tr:gt(0)").each(function(){
			// alert($(this).children('td').eq(0).text());
			names += $(this).children('td').eq(0).text() + ",";
		});
		names = names.substring(0,names.length-1);
		return names;
	}
	
	// 將数据写入项目下拉选中
	function addOption(object) {
		var select=$("#objectSelect");
		select.empty();
		select.append("<option>选择项目</option>");
		for(var i in object){
			select.append("<option>"+object[i]+"</option>");
		}
	}
	//筛选
	function screening() {
		//获取项目下拉选中的value
		var objectName=$("#objectSelect").val();
		var names=$("#names").val();
		var checkBox=$("#checkBox").prop("checked");
		$.ajax({
			type : "POST",
			url : "../service/workanalysis/getWorkInfoForWeekscreening",
			data : {"objectName":objectName,"names":names,"checkBox":checkBox},
			success : function(s) {
				if(s.code==1){
					addTable(s.result);
				}else{
					alert(s.msg)
				}
			}
		})
	}
	
	
	// 将数据写入表格中
	function addTable(result) {
		addObjectSelect();
		// 获得tbody对象(根据id获得)
		var tBody=$("#tBody");
		tBody.empty();
		// 迭代json对象
		for(var i in result){
		// 构建一个tr对象
		var tr=$("<tr></tr>");
		if(i%2){
			tr.addClass("bg_color");
		}
		tr.append("<td>"+result[i].姓名+"</td>");
		tr.append("<td>"+result[i].项目+"</td>");
		tr.append("<td>"+result[i].任务编号+"</td>");
		tr.append("<td>"+result[i].任务名称+"</td>");
		if(!result[i].预计开始日期){result[i].预计开始日期="0000-00-00";}
		tr.append("<td>"+result[i].预计开始日期+"</td>");
		if(!result[i].实际开始日期){result[i].实际开始日期="0000-00-00";}
		tr.append("<td>"+result[i].实际开始日期+"</td>");
		if(!result[i].预计截止日期){result[i].预计截止日期="0000-00-00";}
		tr.append("<td>"+result[i].预计截止日期+"</td>");
		tr.append("<td>"+result[i].预计总工时.toFixed(1)+"</td>");
		tr.append("<td>"+result[i].周耗费工时.toFixed(1)+"</td>");
		tr.append("<td>"+result[i].总耗费工时.toFixed(1)+"</td>");
		tr.append("<td>"+result[i].任务状态+"</td>");
			if(!result[i].汇总周工时){
				tr.append("<td></td>");
			}else {
				tr.append("<td>"+result[i].汇总周工时.toFixed(2)+"</td>");
			}
			tBody.append(tr);
		}
	}// parseFloat(a).toFixed(3)
	var weekDate; // 周数组
	var dateIndex;
	var dateSend; // 发送的周数据
	var tableData;
	// 打开加载周信息
	function getWeek(){
		$.ajax({
			type : "POST",
			url : "../service/workanalysis/getWeeksOfAnalysis",
			data : {},
			success : function(r) {
				if(r.code == 1){
					weekDate = r.result;
					for(var i in weekDate){
						if(i == weekDate.length-1){
							$("#weekSelect").append("<option selected>"+weekDate[i]+"</option>");
							$("#dateShow").text(weekDate[i]);
							dateIndex = i;
							dateSend = weekDate[i];
							ajaxGetData();
						}else{
							$("#weekSelect").append("<option>"+weekDate[i]+"</option>");
						}
					}
				}else{
					alert(r.msg);
				}
			}
		});
		addObjectSelect()
	}

	// 左切换
	function leftTab(){
			dateSend = weekDate[--dateIndex];
			$("#dateShow").text(dateSend);
			$("#nextDate").attr("disabled", false); 
			if(dateIndex <= 0){
				$("#preDate").attr("disabled", true); 
			}
			clearName();
			synSelect(dateSend);
			ajaxGetData();
	}
	
	// 右切换
	function rightTab(){
			dateSend = weekDate[++dateIndex];
			$("#dateShow").text(dateSend);
			$("#preDate").attr("disabled", false); 
			if(dateIndex >= weekDate.length-1){
				$("#nextDate").attr("disabled", true); 
			}
			clearName();
			synSelect(dateSend);
			ajaxGetData();
	}
	
	// 发送创建表格请求
	function ajaxGetData(){
		$.ajax({
			type : "POST",
			url : "../service/workanalysis/getWorkInfoForWeek",
			data : {'date':dateSend},
			success : function(r) {
				if(r.code == 1){
					tableData = r.result;
					addTable(tableData);
				}else{
					alert(r.msg);
				}
			}
		});
	}
	


	
	function synSelect(selectDate){
		$("#weekSelect option").each(function() {
	        if ($(this).text() == selectDate) {
                $(this).attr("selected", "selected");
	        }
	    });
	}

	// 点击查询按钮
	function onGetWeekCapacity(){
		clearName();
		getOption();
		ajaxGetData();
		$("#dateShow").text(dateSend);
	}

	//获取下拉选中的时间
	function getOption() {
		dateSend=$("#weekSelect option:selected").val();
	}
	//清除姓名框中的值
	function clearName() {
		$("#names").val("");
	}
	
	
	
	
	
	
	
	
	
	