$(function() {
	var form = layui.form, layer = layui.layer, laydate = layui.laydate, $ = layui.jquery;
	//表单取消事件
    $('body').on('click', '#btnCancel', function(data) {        
    	parent.layer.closeAll();
    })
    
    if($("#h_roleId").val() != ""){
    	$("#role-slect").val($("#h_roleId").val());
    	form.render('select');
    }
    
    //[/^(?![`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]+$)[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\u4e00-\u9fa5_a-zA-Z0-9]{2,20}$/, '用户名2~20位，不为纯字符'],
    
    form.verify({
    	userId: [/^(?![`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]+$)[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\u4e00-\u9fa5_a-zA-Z0-9]{2,20}$/, '用户名2~20位，不为纯字符'],
        pwd: function(value){  
        	if(value.length > 30 || value.length < 6){  
        		return '密码6~30字符';  
        	}
        },
        userName: [/^(?![`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]+$)[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\u4e00-\u9fa5_a-zA-Z0-9]{2,30}$/, '姓名2~30位，不为纯字符'],
        eMail: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不正确'],
        roleId: function(value){
        	if(value == ""){  
        		return '请选择角色';  
        	}
        }
	});
    
    //表单提交事件
    layui.form.on('submit(btnSubmit)', function(data) {
    	var obj_user = data.field;
    	obj_user['userStatus'] = "1";
    	var chenck_status = $("div .layui-unselect.layui-form-switch.layui-form-onswitch");
    	if(chenck_status.length > 0){
    		obj_user['userStatus'] = "0";
    	}
    	
    	layer.load(1);
        $.ajax({
            url: "/user/save",
            type: "POST",
            dataType: "JSON",
            data:obj_user,
            success: function(data){
            	layer.closeAll('loading');
            	if(data != undefined && data != ""){
            		if(data.code==0){    
            			layer.msg(data.msg,{icon: 1});
            			parent.layui.table.reload('table', { where: {s: new Date().getTime()} });
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