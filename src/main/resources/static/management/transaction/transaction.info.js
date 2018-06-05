layui.use(['form', 'laydate','jquery' , 'layer','layedit'], function() {
	var form = layui.form, layer = layui.layer, laydate = layui.laydate, layedit = layui.layedit, $ = layui.jquery;
	
	var E = window.wangEditor
	var editor = new E(document.getElementById('editor'));
	editor.create();
	
	var pageSign = $("#page_sign").val();
	var pId = $("#pid").val();
	var promotion_show_date = "";
	var nowTime = new Date().valueOf();
	if(pageSign == "edit" || pageSign == "show"){
		var h_start_date = $("#h_start_date").val();
		var h_end_date = $("#h_end_date").val();
		promotion_show_date = h_start_date + ' ~ ' + h_end_date;
		$("#show_promotion_date").attr("placeholder",promotion_show_date);
	}
	
	if(pageSign == "edit"){
		editor.txt.html($("#hide_content").text());
		laydate.render({
		  elem: '#promotion_date',
		  type: 'datetime',
		  range: '~',
		  min:nowTime,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  value : promotion_show_date
		});
		$('body').on('click', '#btnCancel', function(data) {        
	    	parent.layer.closeAll();
	    })
	}else if(pageSign == "add"){
		var d = new Date();
		var now_date = formatDate(d,"yyyy-MM-dd hh:mm:ss");
		d.setMonth(d.getMonth() + 1);
		var next_month_date = formatDate(d,"yyyy-MM-dd hh:mm:ss");
		laydate.render({
		  elem: '#promotion_date',
		  type: 'datetime',
		  range: '~',
		  min:nowTime,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  value : now_date + ' ~ ' + next_month_date
		});
		$('body').on('click', '#btnCancel', function(data) {        
			parent.layer.closeAll();
		})
	}else if(pageSign == "show"){
		editor.txt.html($("#hide_content").text());
		editor.$textElem.attr('contenteditable', false);
		//$("#LAY_layedit_1").contents().find('body').attr("contenteditable","false");
	}

	$('body').on( 'click','.img-del',function(){
        $(this).parent().html("");
    })
	
	$("#promotion_iframe").on("click", function() {
		layer.open({
			type: 2,
			//skin: 'layui-layer-rim',
			title:'添加部门',
			area: ['750px', '540px'],
			offset: ['5px'],
			fixed: false, 
			content: '/transaction/department?pid='+pId+'&sign='+pageSign,
			yes: function(index, layero){
				var iframeWin = layero.find('iframe')[0];
				var merchant_list = iframeWin.contentWindow.merchant_submit();
				$("#merchant_list").html(" ");
				$("#merchant_list").append(merchant_list);
				layer.close(index);
				form.render();
			},
			btn: ['确定'],
			btnAlign: 'c'
		});
	});
	
	
	form.verify({
		promotionName: [/^(?![`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\s]+$)[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\u4e00-\u9fa5_a-zA-Z0-9(Ä|Ö|Ü|ä|ö|ß|ü)\s]{2,30}$/, '活动名称2~30位，不全为特殊字符'],
        /*promotionUrl: [/^((ht|f)tps?):\/\/[\w\-]+(\.[\w\-]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#])?$/, '活动链接格式不正确'],*/
        promotion_date: function(value){
        	if(value.length < 5){  
        		return '请输入活动时间';  
        	}  
        }/*,
        contact: function(value){
        	if(layedit.getText(index).length > 2000 || layedit.getText(index).length < 5){  
        		return '活动内容5~2000字符';  
        	}
        }*/
	});
	
	form.on('submit(save)', function(data){
		var obj = data.field;
		var check_tag = $("#promotion_tag").find("div.layui-unselect.layui-form-checkbox.layui-form-checked");
		var check_mer = $("#promotion_merchant").find("button.layui-btn.layui-btn-sm.layui-btn-disabled");
		
		if(check_mer.length < 1){
			layer.msg('请选择至少一个部门', {icon: 5});
			return false;
		}
		if(editor.txt.text().length > 2000 || editor.txt.text().length < 5){
			layer.msg('活动内容5~2000字符', {icon: 5});
			return false;
		}

		var merIdList = new Array()
		if(check_mer.length > 0){
			for(var i= 0; i < check_mer.length;i++){
				var merId = $(check_mer[i]).val();
				merIdList[i] = merId;
			}
		}
		obj['depIds'] = merIdList;
		obj['transactionContent'] = editor.txt.html();
		layer.load(1);
		$.ajax({
		    type: "POST",
		    url: "/transaction/insert",
		    contentType: "application/json",
		    data: JSON.stringify(obj), 
		    dataType: "html",
		    success: function (data) {
		    	layer.closeAll('loading');
		    	if(data != undefined && data != ""){
		    		data = JSON.parse(data)
		            if(data.code==200){
		                layer.msg(data.msg,{icon: 1,shade: [0.8, '#393D49']},function(){
		                	var index = parent.layer.getFrameIndex(window.name);
		                	parent.layer.closeAll();
		                	parent.layui.table.reload('table', { where: {s: new Date().getTime()}, page: { curr: 1} });
		                });
		            }else{
		                layer.msg(data.msg,{icon: 2});
		            }
		    	}
		    },
		    error: function(xhr, status, info){
		    	layer.closeAll('loading');
		    	layer.msg('网络异常，请刷新页面',{icon: 2});
		    }
		});
		return false;  
	});
	
});

function formatDate(date,fmt) { //author: meizz 
	var o = {
			"M+": date.getMonth() + 1, //月份 
			"d+": date.getDate(), //日 
			"h+": date.getHours(), //小时 
			"m+": date.getMinutes(), //分 
			"s+": date.getSeconds(), //秒 
			"q+": Math.floor((date.getMonth() + 3) / 3), //季度 
			"S": date.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
