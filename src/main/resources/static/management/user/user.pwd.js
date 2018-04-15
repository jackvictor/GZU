$(function() {
	var form = layui.form, layer = layui.layer, laydate = layui.laydate, $ = layui.jquery;
	//表单取消事件
    $('body').on('click', '#btnCancel', function(data) { parent.layer.closeAll(); })
    
    form.verify({
    	pwd: function(value){
    		if(value.length > 30 || value.length < 6){  
        		return '密码6~30字符';  
        	} 
        },
        npwd: function(value){
    		if(value.length > 30 || value.length < 6){  
        		return '密码6~30字符';  
        	} 
        }
	});
    
    //表单提交事件
	layui.form.on('submit(btnSubmit)', function(data) {
    	layer.load(1);
    	$.ajax({
		    type: "POST",
		    url: "/user/changpwd",
		    dataType: "JSON",
		    data: data.field, 
		    success: function (data) {
		    	layer.closeAll('loading');
		    	if(data != undefined && data != ""){
		    		if(data.code==200){    
		    			layer.msg(data.msg,{icon: 1});
		    			parent.layer.closeAll();
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