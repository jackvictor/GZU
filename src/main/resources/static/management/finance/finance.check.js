layui.use(['table', 'form', 'layer'], function () {

    //加载表
    var table = layui.table,
        form = layui.form,
        layer = layui.layer
    table.render({
        elem: '#table',
        url: '/finance/check_page',
        page: true,
        cols: [[
            {
                field: 'financeSource',
                sort: false,
                title: '资金来源',
                width: '11%'
            },
            {
                field: 'financePropose',
                sort: false,
                title: '资金用途',
                width: '11%'
            },
            {
                field: 'financeMoney',
                sort: false,
                title: '金额',
                width: '10%'
            },
            {
                field: 'financeDepartment',
                sort: false,
                title: '申请部门',
                width: '12%'
            },
            {
                field: 'financeRemain',
                sort: false,
                title: '资金余额',
                width: '11%'
            },
            {
                field: 'status',
                sort: false,
                title: '资金状态',
                width: '11%',
                templet: function (d) {
                    if (d.status == "1") {
                        return "通过";
                    }
                    else if (d.status == "2") {
                        return "未通过";
                    }
                    else if (d.status == "0") {
                        return "待审核";
                    }

                }
            },
            {
                field: 'createTime',
                sort: false,
                title: '更改时间',
                width: '11%'
            },
            {
                field: 'updateTime',
                sort: false,
                title: '更改时间',
                width: '11%'
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

    $("#finance_check").on("click", function () {
        table.render({
            elem: '#table',
            url: '/finance/check_page',
            page: true,
            cols: [[
                {
                    field: 'financeSource',
                    sort: false,
                    title: '资金来源',
                    width: '11%'
                },
                {
                    field: 'financePropose',
                    sort: false,
                    title: '资金用途',
                    width: '11%'
                },
                {
                    field: 'financeMoney',
                    sort: false,
                    title: '金额',
                    width: '10%'
                },
                {
                    field: 'financeDepartment',
                    sort: false,
                    title: '申请部门',
                    width: '12%'
                },
                {
                    field: 'financeRemain',
                    sort: false,
                    title: '资金余额',
                    width: '11%'
                },
                {
                    field: 'status',
                    sort: false,
                    title: '资金状态',
                    width: '11%',
                    templet: function (d) {
                        if (d.status == "1") {
                            return "通过";
                        }
                        else if (d.status == "2") {
                            return "未通过";
                        }
                        else if (d.status == "0") {
                            return "待审核";
                        }

                    }
                },
                {
                    field: 'createTime',
                    sort: false,
                    title: '更改时间',
                    width: '11%'
                },
                {
                    field: 'updateTime',
                    sort: false,
                    title: '更改时间',
                    width: '11%'
                },
                {
                    align: 'center',
                    toolbar: '#barTpl',
                    title: '操作',
                    // width: '10%'
                }
            ]],
        });
    });


    $("#finance_checked").on("click", function () {
        table.render({
            elem: '#table',
            url: '/finance/page',
            page: true,
            cols: [[
                {
                    field: 'financeSource',
                    sort: false,
                    title: '资金来源',
                    width: '11%'
                },
                {
                    field: 'financePropose',
                    sort: false,
                    title: '资金用途',
                    width: '11%'
                },
                {
                    field: 'financeMoney',
                    sort: false,
                    title: '金额',
                    width: '10%'
                },
                {
                    field: 'financeDepartment',
                    sort: false,
                    title: '申请部门',
                    width: '12%'
                },
                {
                    field: 'financeRemain',
                    sort: false,
                    title: '资金余额',
                    width: '11%'
                },
                {
                    field: 'status',
                    sort: false,
                    title: '资金状态',
                    width: '11%',
                    templet: function (d) {
                        if (d.status == "1") {
                            return "通过";
                        }
                        else if (d.status == "2") {
                            return "未通过";
                        }
                        else if (d.status == "0") {
                            return "待审核";
                        }

                    }
                },
                {
                    field: 'createTime',
                    sort: false,
                    title: '更改时间',
                    width: '11%'
                },
                {
                    field: 'updateTime',
                    sort: false,
                    title: '更改时间',
                    width: '11%'
                },
                {
                    align: 'center',
                    toolbar: '#barTpl1',
                    title: '操作',
                    // width: '10%'
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
    var url = '/finance/info?pid='+pid+'&sign='+sign;
    var title = '';

    if(sign == "check"){
        title = '审核财务';
    }
    if(sign == "show"){
        title='查看财务';
    }
    if(sign=="edit")
    {
        title='编辑财务';
    }
    if(sign=="add")
    {
        title='新增财务';
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

