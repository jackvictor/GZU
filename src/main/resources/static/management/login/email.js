layui.use(['form','jquery' , 'layer'], function() {
	var form = layui.form, layer = layui.layer, $ = layui.jquery;
	
	$("#user_id").keyup(function(){
		$("#return_login").attr("href","tologin?userId="+$(this).val());
	});
	
	form.verify({
		userId: function(value){  
			if(value.length < 2 || value.length > 30){  
				return '用户名2~30字符';
			}
        }
	});
	layui.form.on('submit(sendEmail)', function(data) {
		layer.load(1);
		var obj = data.field;
		
		$.ajax({
            url: "/login/email",
            type: "POST",
            dataType: "JSON",
            data:{userId:obj.userId},
            success: function(data){
                layer.closeAll('loading');
                if(data != undefined && data != ""){
                	if (data.code == 200) {
        	        	layer.closeAll('loading');
        	            layer.msg(data.msg,{icon: 1});
        	        } else {
        	            layer.closeAll('loading');
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
