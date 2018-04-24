
layui.use(['form', 'layedit', 'laydate','layer','jquery'], function() {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , $ = layui.jquery
        , laydate = layui.laydate;

    //表单取消事件
    $('body').on('click', '#btnCancel', function(data) {
        parent.layer.closeAll();
    })

    //日期
    laydate.render({
        elem: '#goodsBuyTime'
        ,type:'datetime'
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });

    //表单内容验证
    form.verify({
        goodsName: function(value){
            if(value.length > 30 || value.length < 2){
                return '物品名称2~15字符';
            }
        },
        goodsNumber:[/^(0|[1-9][0-9]*){1,7}$/,'1-7数量'],
        goodsPrice: [/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9]){1,7}$)/, '1-7金额字符']
    });

    //
    form.on('submit(doSubmit)', function (data) {
         layer.msg(JSON.stringify(data.field));
        var obj = JSON.stringify(data.field)
        layer.load(1);
        $.ajax({
            type: "POST",
            url: "/goods/insert",
            data: data.field,
            timeout: 10000,
            success: function (data) {
                layer.closeAll('loading');
                if(data != undefined && data != ""){
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
});