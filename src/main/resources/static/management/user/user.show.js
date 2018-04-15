$(function() {
	var form = layui.form, layer = layui.layer, laydate = layui.laydate, $ = layui.jquery;
	//表单取消事件
    $('body').on('click', '#btnCancel', function(data) {        
        parent.location.reload();
    })
    
    if($("#h_roleId").val() != ""){
    	$("#role-slect").val($("#h_roleId").val());
    	form.render('select');
    }
    
});