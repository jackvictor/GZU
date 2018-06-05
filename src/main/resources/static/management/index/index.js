layui.use('element', function() {
    var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    var $ = layui.jquery;
    index_element = element;

    element.on('tab(demo)', function(data){
        var id = $(this).attr("lay-id");
        $($("div .layui-side-scroll")[0]).find("a.skip").each(function(index,obj){
            $(obj).parent().removeClass("layui-this");
            if($(obj).attr("data-id") == id){
                $(obj).parent().addClass("layui-this");
                if($(obj).parent().parent().parent().attr("class") != undefined && $(obj).parent().parent().parent().attr("class").indexOf('layui-nav-itemed') == -1){
                    $(obj).parent().parent().parent().addClass("layui-nav-itemed");
                }
            }
        });
    });
    //触发事件
    var active = {
        //在这里给active绑定几项事件，后面可通过active调用这些事件
        tabAdd: function(url, id, name) {
            //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
            //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
            url = url+"?s="+new Date().getTime();
            element.tabAdd('demo', {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:85%;"></iframe>',
                id: id //规定好的id
            })
        },
        tabChange: function(id) {
            //切换到指定Tab项
            element.tabChange('demo', id); //根据传入的id传入到指定的tab项
        },
        tabDelete: function(id) {
            element.tabDelete("demo", id); //删除
        },
        tabDeleteAll: function(ids) { //删除所有
            $.each(ids, function(i, item) {
                element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
            })
        }
    };

    //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
    $('.skip').on('click', function() {
        var dataid = $(this);

        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if($(".layui-tab-title li[lay-id]").length <= 0) {
            //如果比零小，则直接打开新的tab项
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
        } else {
            //否则判断该tab项是否以及存在

            var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
            $.each($(".layui-tab-title li[lay-id]"), function() {
                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if($(this).attr("lay-id") == dataid.attr("data-id")) {
                    isData = true;
                }
            })
            if(isData == false) {
                //标志为false 新增一个tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            }
        }
        //最后不管是否新增tab，最后都转到要打开的选项页面上
        active.tabChange(dataid.attr("data-id"));
    });

    $('#user_message').on('click', function() {
        var uId = $("#user_id").val();
        var url = '/user/account?uId='+uId;
        var title = '基本资料';
        layui.layer.open({
            type: 2,
            //skin: 'layui-layer-rim',
            title:title,
            //maxmin:true,
            fixed: false, //不固定
            area: ['600px', '600px'],
            offset: ['60px'],
            content: url
        });
    });

    $('#reset_pwd').on('click', function() {
        var uId = $("#user_id").val();
        var url = '/user/pwd?uId='+uId;
        var title = '修改密码';
        layui.layer.open({
            type: 2,
            //skin: 'layui-layer-rim',
            title:title,
            //maxmin:true,
            fixed: false, //不固定
            area: ['600px', '300px'],
            offset: ['60px'],
            content: url
        });
    });
    var menu_i = $("div .layui-side-scroll").find("i");
    for(var i = 0; i < menu_i.length; i++){
        var icon = $(menu_i[i]).attr("html")+"&nbsp;";
        $(menu_i[i]).html(icon);
    }
});