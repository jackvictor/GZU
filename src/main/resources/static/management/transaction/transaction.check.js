layui.use(['table', 'form', 'layer'], function () {

    //加载表
    var table = layui.table,
        form = layui.form,
        layer = layui.layer
    table.render({
        elem: '#table',
        url: '/transaction/check_page',
        page: true,
        cols: [[
            {
                field: 'transactionName',
                sort: false,
                title: '事务名称',
                width: '12%'
            },
            {field:'departmentList', sort: false,  templet:function(d){
                    var departments = "";
                    if(d.departmentInfos.length > 0){
                        for(var i = 0; i < d.departmentInfos.length; i++){
                            departments = departments + d.departmentInfos[i].depName + ";";
                        }
                    }
                    return departments;
                }, title: '参与部门',width:'13%'},
            {
                field: 'transactionScheme',
                sort: false,
                title: '活动经验',
                width: '12%'
            },
            {
                field: 'transactionContent',
                sort: false,
                title: '活动内容',
                width: '12%'
            },
            {
                field: 'transactionBegin',
                sort: false,
                title: '活动开始时间',
                width: '12%'
            },
            {
                field: 'transactionFinish',
                sort: false,
                title: '活动结束时间',
                width: '12%'
            },
            {
                field: 'status',
                sort: false,
                title: '审核状态',
                width: '13%',
                templet:function(d){
                    if(d.status=="1"){
                        return "通过";
                    }
                    else if(d.status == "2"){
                        return "未通过";
                    }
                    else if(d.status == "0"){
                        return "待审核";
                    }
                }},

            {
                align: 'center',
                toolbar: '#barTpl',
                title: '操作',
                // width: '12 %'
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
    $("#transaction_add").on("click", function () {
        var open_height = Math.round($(parent.document.getElementsByClassName("layui-tab-content")[0]).height() * 0.83);
        layer.open({
            type: 2,
            title:'新增部门',
            fixed: false, //不固定
            area:['100%', open_height+'px'],
            offset: ['0px', '0px'],
            content: '/transaction/info?sign=add'

        });
    });

    $("#transaction_check").on("click",function () {
        table.render({
            elem: '#table',
            url: '/transaction/check_page',
            page: true,
            cols: [[
                {
                    field: 'transactionName',
                    sort: false,
                    title: '事务名称',
                    width: '12%'
                },
                {field:'departmentList', sort: false,  templet:function(d){
                        var departments = "";
                        if(d.departmentInfos.length > 0){
                            for(var i = 0; i < d.departmentInfos.length; i++){
                                departments = departments + d.departmentInfos[i].depName + ";";
                            }
                        }
                        return departments;
                    }, title: '参与部门',width:'13%'},
                {
                    field: 'transactionScheme',
                    sort: false,
                    title: '活动经验',
                    width: '12%'
                },
                {
                    field: 'transactionContent',
                    sort: false,
                    title: '活动内容',
                    width: '12%'
                },
                {
                    field: 'transactionBegin',
                    sort: false,
                    title: '活动开始时间',
                    width: '12%'
                },
                {
                    field: 'transactionFinish',
                    sort: false,
                    title: '活动结束时间',
                    width: '12%'
                },
                {
                    field: 'status',
                    sort: false,
                    title: '审核状态',
                    width: '13%',
                    templet:function(d){
                        if(d.status=="1"){
                            return "通过";
                        }
                        else if(d.status == "2"){
                            return "未通过";
                        }
                        else if(d.status == "0"){
                            return "待审核";
                        }
                    }},

                {
                    align: 'center',
                    toolbar: '#barTpl',
                    title: '操作',
                    // width: '12 %'
                }
            ]],
        });
    });


    $("#transaction_checked").on("click",function () {
        table.render({
            elem: '#table',
            url: '/transaction/page',
            page: true,
            cols: [[
                {
                    field: 'transactionName',
                    sort: false,
                    title: '事务名称',
                    width: '12%'
                },
                {field:'departmentList', sort: false,  templet:function(d){
                        var departments = "";
                        if(d.departmentInfos.length > 0){
                            for(var i = 0; i < d.departmentInfos.length; i++){
                                departments = departments + d.departmentInfos[i].depName + ";";
                            }
                        }
                        return departments;
                    }, title: '参与部门',width:'13%'},
                {
                    field: 'transactionScheme',
                    sort: false,
                    title: '活动经验',
                    width: '12%'
                },
                {
                    field: 'transactionContent',
                    sort: false,
                    title: '活动内容',
                    width: '12%'
                },
                {
                    field: 'transactionBegin',
                    sort: false,
                    title: '活动开始时间',
                    width: '12%'
                },
                {
                    field: 'transactionFinish',
                    sort: false,
                    title: '活动结束时间',
                    width: '12%'
                },
                {
                    field: 'status',
                    sort: false,
                    title: '审核状态',
                    width: '13%',
                    templet:function(d){
                        if(d.status=="1"){
                            return "通过";
                        }
                        else if(d.status == "2"){
                            return "未通过";
                        }
                        else if(d.status == "0"){
                            return "待审核";
                        }
                    }},

                {
                    align: 'center',
                    toolbar: '#barTpl1',
                    title: '操作',
                    // width: '12 %'
                }
            ]],
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
    var url = '/transaction/info?pid='+pid+'&sign='+sign;
    var title = '';

    if(sign == "check"){
        title = '审核事务';
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

