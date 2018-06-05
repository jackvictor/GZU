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
                width: '12%'
            },
            {
                field: 'goodsPrice',
                sort: false,
                title: '物品价格',
                width: '10%'
            },
            {
                field: 'goodsMargin',
                sort: false,
                title: '物品余量',
                width: '10%'
            },
            {
                field: 'goodsLender',
                sort: false,
                title: '借物人',
                width: '12%'
            },
            {
                field: 'goodsReturner',
                sort: false,
                title: '还物人',
                width: '12%'
            }, {
                field: 'lendTime',
                sort: false,
                title: '借出时间',
                width: '12%'
            },
            {
                field: 'returnTime',
                sort: false,
                title: '归还时间',
                width: '12%'
            },
            {
                align: 'center',
                toolbar: '#barTpl',
                title: '操作',
                // width: '20%'
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
})

//搜索实现
function doSearch() {
    var value = $("#searchValue").val();
    layui.table.reload('table', {
        where: {searchName: value},
        page: {curr: 1}
    });
}

function do_lend(pid,sign){
    var open_height = Math.round($(parent.document.getElementsByClassName("layui-tab-content")[0]).height() * 0.83);
    var url = '/goods/lend_return?pid='+pid+'&sign='+sign;
    var title = '';

    if(sign == "lend"){
        title = '借记物品';
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

