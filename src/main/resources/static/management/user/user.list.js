$(function() {
	
	var userAccount = $("#user_account").val();
	var userToolbar = '#barTpl';
	if(userAccount == "admin"){
		userToolbar = '#adminBar';
	}
    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/user/page',
        where: {
        },
        page: true,
        cols: [[
            {field:'userId', sort: false, title: '用户名'},
            {field:'userName', sort: false, title: '姓名'},
            {field:'userRoleMid', sort: false,  templet:function(d){
            	return d.userRoleMid.roleName;
            }, title: '角色', width:'15%'},
            {field:'eMail', sort: false, title: '邮箱'},
            {field:'createTime', sort: false, title: '创建时间'},
            {field:'updateTime', sort: false, title: '修改时间'},
            {field:'userStatus', sort: false, title: '状态',width:'6%',templet:function(d){
            	if(d.userStatus == '0'){
            		 return "正常";
            	}else{
            		return "停用";
            	}
            }},
            {align:'center', toolbar: userToolbar, title: '操作'}
        ]]
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function(){
        doSearch(table);
    });
    $("#searchValue").keydown(function(event){
        if(event.keyCode ==13){
            $("#searchBtn").trigger("click");
        }
    });
    
    $("#user_list").on("click", function() {
		layer.open({
			type: 2,
//			skin: 'layui-layer-rim',
			title:'新增用户',
//			maxmin:true,
			fixed: false, //不固定
			area: ['600px', '500px'],
			offset: ['5px'],
			content: '/user/info?sign=add'
			
		});
	});
    
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;
        if(layEvent === 'delete'){
        	doDelete(data.uId);
        }else if(layEvent === 'resetpwd'){
        	doResetPwd(data.uId);
        }
    });
    
});



//显示表单弹窗
function showEditModel(data){
    layer.open({
        type: 1,
        title: data==null?"添加用户":"修改用户",
        area: '450px',
        offset: '120px',
        content: $("#addModel").html()
    });
    $("#editForm")[0].reset();
    $("#editForm").attr("method","POST");
    var selectItem = "";
    if(data!=null){
        $("#editForm input[name=id]").val(data.id);
        $("#editForm input[name=userName]").val(data.userName);
        $("#editForm input[name=realUserName]").val(data.realUserName);
        $("#editForm input[name=userPhone]").val(data.userPhone);
        $("#role-select").val(data.role);
        $("#sex-select").val(data.sex);
        $("#user-status-switch").val(data.status);
        if(data.status == '0'){
            $("#editForm input[name=status]").attr("checked", 'true');
        }
        layui.form.render('radio');
    }
    $("#btnCancel").click(function(){
        layer.closeAll('page');
    });

    // getRoles(selectItem);
}


//搜索
function doSearch(table){
    var value = $("#searchValue").val();
    layui.table.reload('table', {where: {searchName: value,s: new Date().getTime()} , page: { curr: 1} });
}

//查看
function show(uid){
	var url = '/user/show?uId='+uid;
	var title = '查询用户';
	layer.open({
		type: 2,
		title:title,
		fixed: false, //不固定
		area: ['600px', '500px'],
		offset: ['5px'],
		content: url
	});
}

//修改
function edit(pid,sign){
	var url = '/user/info?pid='+pid+'&sign='+sign;
	var title = '修改用户';
	
	
	layer.open({
		type: 2,
		//skin: 'layui-layer-rim',
		title:title,
		//maxmin:true,
		fixed: false, //不固定
		area: ['600px', '500px'],
		offset: ['5px'],
		content: url
		
	});
}

//删除
function doDelete(userId){
	layer.confirm('确定要删除吗？', {
		  offset: '50px'
		},function(index){
		    layer.close(index);
		    layer.load(1);
		    
		    $.ajax({
	            url: "/user/delete?uId=" + userId,
	            type: "GET",
	            dataType: "JSON",
	            success: function(data){
	                layer.closeAll('loading');
	                if(data != undefined && data != ""){
	                	if(data.code==0){
	                		layer.msg(data.msg,{icon: 1});
	                		layui.table.reload('table', {});
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
		});
}

//重置密码
function doResetPwd(userId){
	layer.confirm('确定要重置密码吗？', {
		  offset: '50px'
	}, function(index){
	    layer.close(index);
	    layer.load(1);
	    $.ajax({
            url: "/user/resetpwd",
            type: "POST",
            dataType: "JSON",
            data:{uId: userId},
            success: function(data){
            	layer.closeAll('loading');
            	if(data != undefined && data != ""){
            		if(data.code==200){
        	        	layer.msg(data.msg,{icon: 1});
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
	});
}