layui.use(['layer', 'form'], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form;
	
	//清理左侧菜单缓存
	var index = layer.load(2, {
	    shade: [0.3, '#333']
	});
	
	$(window).on('load', function() {
	    layer.close(index);
	    form.on('submit(login)', function(data) {
	        var loadIndex = layer.load(2, {
	            shade: [0.3, '#333']
	        });
	    	layer.load(1);
	        $.ajax({
	            url: "/login/check",
	            type: "POST",
	            dataType: "JSON",
	            data:data.field,
	            success: function(data){
	            	if (data.code == 200) {
		            	layer.closeAll('loading');
		                layer.msg(data.msg,{icon: 1});
		                setTimeout(function() {
		                    location.replace("/");
		                }, 1000);
		            } else {
		                layer.closeAll('loading');
		                layer.msg(data.msg,{icon: 2});
		            }
	            },
			    error: function(xhr, status, info){
			    	layer.closeAll('loading');
			    	layer.msg('网络异常，请刷新页面');
			    }
	        });
	        return false;
	    });
	}());
});