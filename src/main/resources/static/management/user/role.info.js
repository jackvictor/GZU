layui.use(['form', 'laydate','jquery' , 'layer'], function() {
	var form = layui.form, layer = layui.layer, laydate = layui.laydate, $ = layui.jquery;
	loadTree();
	
	//表单取消事件
    $('body').on('click', '#btnCancel', function(data) {        
        parent.location.reload();
    })
    //验证
    form.verify({
    	roleName: [/^(?![`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]+$)[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\u4e00-\u9fa5_a-zA-Z0-9]{2,30}$/, '角色名2~30位，不全为特殊字符'],
        roleDesc: [/^(?![`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]+$)[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、\u4e00-\u9fa5_a-zA-Z0-9]{2,30}$/, '角色描述2~200位，不全为特殊字符'],
	});
    
    //表单提交事件
    layui.form.on('submit(btnSubmit)', function(data) {
    	var obj = data.field;
    	obj['roleStatus'] = "1";
    	var chenck_status = $("div .layui-unselect.layui-form-switch.layui-form-onswitch");
    	if(chenck_status.length > 0){
    		obj['roleStatus'] = "0";
    	}
    	obj = getData(obj);
    	layer.load(1);
        
        
        $.ajax({
		    type: "POST",
		    url: "/role/save",
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
		    				parent.layui.table.reload('table', { where: {s: new Date().getTime()} });
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
})

function loadTree(){
	var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	var roleId = $("#id").val();
	$.get("/role/getAllFun?roleId="+roleId, function(data){		
		if(data.length > 0){
			 var zNodes = [];
			 for(var i=0;i<data.length;i++){
				 var isChkDisabled = false;
				 if(data[i].id == "1001" || data[i].id == "1002"){
					 data[i].check = true;
					 isChkDisabled = true;
				 }
				 var node ={
						 id:data[i].id,
						 pId:data[i].parentId,
						 name:data[i].funName,
						 open:true,
						 checked:data[i].check,
						 chkDisabled:isChkDisabled
				 }; 
				 zNodes[i] = node;
			 }
			 $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		 }		 		 
	}, "JSON");
}

function getData(data){
	//获取选中节点id
	var checkedFuns = new Array();
	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
    var nodes=treeObj.getCheckedNodes(true);
    if(nodes.length > 0){
    	for(var i=0;i<nodes.length;i++){
    		checkedFuns[i] = nodes[i].id;
    	}
    }
    data["checkedFuns"] = checkedFuns;
    
    return data;
}

