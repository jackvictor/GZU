$(function(){
	//渲染表格
    layui.table.render({
        elem : '#table',
        url : '/role/rpage',
        where: {
        },
        page: true,
        cols: [[
            {field:'roleName', sort: false, title: '角色', width:'15%'},
            {field:'roleDesc', sort: false, title: '权限'},
            {field:'createDate', sort: false, title: '创建时间'},
            {field:'updateDate', sort: false, title: '修改时间'},
            {field:'roleStatus', sort: false, title: '状态',templet:function(d){
            	if(d.roleStatus == '0'){
           		 return "正常";
           	}else{
           		return "停用";
           	}
           }},
            {align:'center', toolbar: '#barTpl', title: '操作', width:'10%'}
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
    
    $("#role_list").on("click", function() {
		layer.open({
			type: 2,
			//skin: 'layui-layer-rim',
			title:'新增角色',
			//maxmin:true,
			fixed: false, //不固定
			area: ['600px', '600px'],
			offset: ['5px'],
			content: '/role/info?sign=add'			
		});
	});
    
    //工具条点击事件
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        if(layEvent === 'edit'){
        	var index = layui.layer.open({
                title : '修改角色',
                type : 2,
                fixed: false, //不固定
        		area: ['600px', '600px'],
        		offset: ['5px'],
                content : "/role/info?roleId="+obj.data.id+'&sign='+layEvent            
            })
        }else  if(layEvent === 'delete'){ //删除
            doDelete(obj.data.id);
        }
    });

});

//删除
function doDelete(roleId){
	layer.confirm('确定要删除吗？', function(index){
	    layer.close(index);
	    layer.load(1);
	    $.ajax({
            url: "/role/delete?rId=" + roleId,
            type: "GET",
            dataType: "JSON",
            success: function(data){
                layer.closeAll('loading');
                if(data != undefined && data != ""){
                	if(data.code==200){
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

//搜索
function doSearch(table){
    var value = $("#searchValue").val();
    layui.table.reload('table', {where: {searchName: value,s: new Date().getTime()} , page: { curr: 1} });
}

