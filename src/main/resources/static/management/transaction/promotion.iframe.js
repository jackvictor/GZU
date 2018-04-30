layui.use(['form', 'laydate','jquery' , 'layer'], function() {
	var form = layui.form, layer = layui.layer, laydate = layui.laydate, $ = layui.jquery;
	
	var parentMerchant = parent.$("#merchant_list").find("button");
	var parentInput = "";
	if(parentMerchant.length > 0){
		for(var i= 0; i < parentMerchant.length;i++){
			var merId = $(parentMerchant[i]).val();
			var merTitle = $(parentMerchant[i]).html();
			parentInput = parentInput + "<span style='display: inherit;margin-left: 5px;'><input type='checkbox' lay-skin='primary' title='"+merTitle+"' value="+merId+"></span>";
		}
	}
	$("#right").append(parentInput);
    form.render();
    
	$("#left_add_all").on("click",function(){
		var left_check_div = $("#left").find("div[lay-skin='primary']");
		if(left_check_div.length > 0){
			var mer_input = "";
			for(var i= 0; i < left_check_div.length;i++){
				var merId = $(left_check_div[i]).parent().find("input").val();
				var merTitle = $(left_check_div[i]).parent().find("input").attr("title");
				mer_input = mer_input + "<span style='display: inherit;margin-left: 5px;'><input type='checkbox' lay-skin='primary' name='like' title='"+merTitle+"' value="+merId+"></span>";
			}
			$("#right").html("");
			$("#right").append(mer_input);
            form.render();
		}
	});
    
	$("#right_remove_all").on("click",function(){
		$("#right").html("");
	});
	
	//根据商家名称查询，并填充左侧div
	$("#searchMer").on("click",function(){
		//活动ID
		var promotionID = $("#promotionId").val();
		//搜索关键字
		var searchName = $("#search_name").val();
		
		//调用后台
		
		$.ajax({
            url: "/promotion/searchmer",
            type: "GET",
            dataType: "JSON",
            data:{pid:promotionID,searchname:searchName},
            success: function(data){
            	if(data != undefined && data != ""){
            		//清空左侧div
            		$("#left").html(" ");
            		var mer_input = "";
            		if(data.length > 0){
            			for(var i = 0; i < data.length; i++){
            				mer_input = mer_input + "<span style='display: inherit;margin-left: 5px;'><input type='checkbox' title='"+data[i].merchantName+"' value="+data[i].id+" lay-skin='primary'></span>";
            			}
            		}
            		//填充左侧div
            		$("#left").html(mer_input);
            		//重新渲染页面
            		form.render();
            	}
            },
		    error: function(xhr, status, info){
		    	layer.closeAll('loading');
		    	layer.msg('网络异常，请刷新页面',{icon: 2});
		    }
        });
	});
	
	$("#search_name").keydown(function(event){
        if(event.keyCode ==13){
            $("#searchMer").trigger("click");
        }
    });
	
	//从左侧添加到右侧
	$("#left_add").on("click",function(){
		//获得左侧勾选的div
		var left_check_div = $("#left").find("div.layui-unselect.layui-form-checkbox.layui-form-checked");
		
		//获得右侧已选项
		var right_div = $("#right").find("input");
		var right_div_list = new Array();
		//添加商家id到list中，ps 右侧
		if(right_div.length > 0){
			for(var i= 0; i < right_div.length;i++){
				var merId = $(right_div[i]).val();
			right_div_list[i] = merId;
			}
		}
		
		//左侧是否有勾选项
		if(left_check_div.length > 0){
			var mer_input = "";
			for(var i= 0; i < left_check_div.length;i++){
				var merId = $(left_check_div[i]).parent().find("input").val();
				var merTitle = $(left_check_div[i]).parent().find("input").attr("title");
				//只添加右侧没有的商家
				if($.inArray(merId, right_div_list) == -1){
					mer_input = mer_input + "<span style='display: inherit;margin-left: 5px;'><input type='checkbox' lay-skin='primary' name='like' title='"+merTitle+"' value="+merId+"></span>";
				}
			}
			$("#right").append(mer_input);
            form.render();
		}
	});
	
	//从右侧取消到左侧
	$("#right_remove").on("click",function(){
		var right_check_div = $("#right").find("div.layui-unselect.layui-form-checkbox.layui-form-checked"); 
		if(right_check_div.length > 0){
			for(var i= 0; i < right_check_div.length;i++){
				$(right_check_div[i]).parent().remove();
			}
		}
	});
	
});
//确定
function merchant_submit(){
	var right_check_div = $("#right").find("input");
	var mer_input = "";
	if(right_check_div.length > 0){
		for(var i= 0; i < right_check_div.length;i++){
			var merId = $(right_check_div[i]).parent().find("input").val();
			var merTitle = $(right_check_div[i]).parent().find("input").attr("title");
			mer_input = mer_input + "<span><button value="+merId+" checked disabled='disabled' class='layui-btn layui-btn-sm layui-btn-disabled' style='background-color: #5FB878;color:#FBFBFB;'>"+merTitle+"</button></span>";
		}
	}
	return mer_input
}