layui.use(['table', 'form', 'layer'], function () {

    //加载表
    var table = layui.table,
        form = layui.form,
        layer = layui.layer
    table.render({
        elem: '#table',
        url: '/goods/page',
        page: true,
        cols: [[
            {
                field: 'goodsName',
                sort: false,
                title: '物品名称',
                width: '20%'
            },
            {
                field: 'goodsPrice',
                sort: false,
                title: '物品价格',
                width: '20%'
            },
            {
                field: 'createTime',
                sort: false,
                title: '创建时间',
                width: '20%'
            },
            {
                field: 'updateTime',
                sort: false,
                title: '更改时间',
                width: '20%'
            },
            {
                field: 'status',
                sort: false,
                title: '物品状态',
                width: '10%'
            },
            {
                align: 'center',
                toolbar: '#barTpl',
                title: '操作',
                width: '10%'
            }
        ]],
    });
    //搜素
    $("#doSearch").on("click", function () {
        doSearch(table);
    });
    $("#searchValue").keydown(function (event) {
        if (event.keyCode == 13) {
            $("#doSearch").trigger("click");
        }
    });
    //弹出增加框
    $("#goods_add").on("click", function () {
        layer.open({
            type: 2,
            title: '添加标签',
            area: ['600px', '400px'],
            fixed: false,
            content: '/feedback/info'

        });
    });
    //监听工具条
    table.on('tool(table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                layer.load(1);
                $.ajax({
                    type: "POST",
                    url: "/feedback/delete",
                    data:data,
                    timeout: 10000,
                    success: function (data) {
                        layer.closeAll('loading');
                        if(data != undefined && data != "") {
                            if (data.code == 200) {
                                layer.msg(data.msg, {icon: 1});
                                layui.table.reload('table', {
                                    done: function(res, curr, count){
                                        if(res.data.length < 1 && curr != 1){
                                            layui.table.reload('table', {page: { curr: (curr - 1)}});
                                        }
                                    }
                                });
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }
                    },
                    error: function(xhr, status, info){
                        layer.closeAll('loading');
                        layer.msg('网络异常，请刷新页面',{icon: 2});
                    }
                });
                layer.close(index);
            });


        } else if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改标签',
                area: ['600px', '400px'],
                fixed: false,
                content: '/feedback/updateInfo?pageId='+data.pageId
            });
        }
    });
})

//搜索实现
function doSearch() {
    var value = $("#searchValue").val();
    layui.table.reload('table', {
        where: {searchName: value},
        page: {curr: 1}
    });
}

