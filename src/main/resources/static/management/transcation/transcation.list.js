layui.use(['table', 'form', 'layer'], function () {

    //加载表
    var table = layui.table,
        form = layui.form,
        layer = layui.layer
    table.render({
        elem: '#table',
        url: '/transaction/page',
        page: true,
        cols: [[
            {
                field: 'transactionName',
                sort: false,
                title: '事务名称',
                width: '15%'
            },
            {field:'departmentList', sort: false,  templet:function(d){
                    var depments = "";
                    if(d.departmentList.length > 0){
                        for(var i = 0; i < d.departmentList.length; i++){
                            depments = depments + d.departmentList[i].depName + ";";
                        }
                    }
                    return ;
                }, title: '参与部门',width:'20%'},
            {
                field: 'statusDesc',
                sort: false,
                title: '审核意见',
                width: '20%'
            },
            {
                field: 'ministerPhoneNu',
                sort: false,
                title: '部长电话',
                width: '20%'
            },
            {
                field: 'createTime',
                sort: false,
                title: '创建时间',
                width: '10%'
            },
            {
                field: 'updateTime',
                sort: false,
                title: '更新时间',
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
        var open_height = Math.round($(parent.document.getElementsByClassName("layui-tab-content")[0]).height() * 0.83);
        layer.open({
            type: 2,
            title:'新增部门',
            fixed: false, //不固定
            area:['100%', open_height+'px'],
            offset: ['0px', '0px'],
            content: '/department/department_info?sign=add'

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
    var url = '/department/department_info?pid='+pid+'&sign='+sign;
    var title = '';

    if(sign == "edit"){
        title = '修改部门';
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
            url: "/department/delete?pid=" + pid,
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
