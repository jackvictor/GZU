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
                title: '物品单价',
                width: '20%'
            },
            {
                field: 'goodsNumber',
                sort: false,
                title: '物品数量',
                width: '10%'
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
                align: 'center',
                toolbar: '#barTpl',
                title: '操作',
                // width: '10%'
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
        var open_height = Math.round($(parent.document.getElementsByClassName("layui-tab-content")[0]).height() * 0.83);
        layer.open({
            type: 2,
            title:'新增物品',
            fixed: false, //不固定
            area:['100%', open_height+'px'],
            offset: ['0px', '0px'],
            content: '/goods/add_info?sign=add'

        });
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

function detail(pid,sign){
    var open_height = Math.round($(parent.document.getElementsByClassName("layui-tab-content")[0]).height() * 0.83);
    var url = '/goods/add_info?pid='+pid+'&sign='+sign;
    var title = '';

    if(sign == "edit"){
        title = '修改物品';
    }

    layer.open({
        type: 2,
        title:title,
        fixed: false, //不固定
        area:['100%', open_height+'px'],
        offset: ['0px', '0px'],
        content: url

    });
}

function delete_goods(pid) {
    console.log(pid);
    layer.confirm('确定要删除吗？', function (index) {
        layer.load(1);
        $.ajax({
            url: "/goods/delete?pid=" + pid,
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                layer.closeAll('loading');
                if (data != undefined && data != "") {
                    if (data.code == 200) {
                        layer.msg(data.msg, {icon: 1});
                        layui.table.reload('table', {
                            done: function (res, curr, count) {
                                if (res.data.length < 1 && curr != 1) {
                                    layui.table.reload('table', {page: {curr: (curr - 1)}});
                                }
                            }
                        });
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            },
            error: function (xhr, status, info) {
                layer.closeAll('loading');
                layer.msg('网络异常，请刷新页面', {icon: 2});
            }
        });
    });
}
