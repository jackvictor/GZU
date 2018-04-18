$.ajaxSetup({
	complete : function(XMLHttpRequest, textStatus) {
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		if (sessionstatus == "timeout") {
			layer.msg('回话超时，跳转登录页...', function(){
				window.location.href = "/";
			}); 
		}
	}
});
(function($) {
	
    $.expo = {
    };

    $.common = {
        initUpload:function(elementid, succ, err){
            layui.upload.render({
                elem: "#" +elementid,
                url: '/management/ftp/uploadFile?sign='+elementid,
                before: function(obj){ 
                	layer.load(1);
                },
                accept:'images',
                acceptMime: 'image/jpg, image/png',
                exts: 'jpg|png',
                size:4096,
                done: succ,
                error: function(index, upload){
                	layer.closeAll('loading');
                }
            })
        }
    };
    
})(jQuery);
