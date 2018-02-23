var actionURL = '/crm/ashx/followHandler.ashx';
var formurl = '/crm/html/follow.html';
 
 
$(function () {
 
    var size = {
 width: $(window).width(), height: $(window).height() 
};
    mylayout.init(size);
    $(window).resize(function () {
 
        size = {
 width: $(window).width(), height: $(window).height() 
};
        mylayout.resize(size);
     
});
 
    DicCategory.bindTree();
    autoResize({
 dataGrid: '#dicGrid', gridType: 'datagrid', callback: grid.bind, height: 4, width: 204 
});
 
     
    $('#a_add').click(CRUD.add);
    $('#a_edit').click(CRUD.edit);
    $('#a_delete').click(CRUD.del);
 
    $('#a_search').click(function () {
 
        search.go('list');
     
});
 
});
 
var mylayout = {
 
    init: function (size) {
 
        $('#layout').width(size.width - 4).height(size.height - 4).layout();
        var center = $('#layout').layout('panel', 'center');
        center.panel({
 
            onResize: function (w, h) {
 
                $('#dicGrid').datagrid('resize', {
 width: w, height: h 
});
             
}
         
});
     
},
    resize: function (size) {
 
        mylayout.init(size);
        $('#layout').layout('resize');
     
}
 
};
var DicCategory = {
 
    bindTree: function () {
 
        $('#dataDicType').tree({
 
            url: 'ashx/followHandler.ashx?action=category',
            onLoadSuccess: function (node, data) {
 
                if (data.length == 0) {
 
                    $('#noCategoryInfo').fadeIn();
                 
}
 
                $('body').data('categoryData', data);
             
},
            onClick: function (node) {
 
                var cc = node.id;
                $('#dicGrid').treegrid({
 
                    url: actionURL,
                    toolbar: '#toolbar',
                    title: "数据列表",
                    iconCls: 'icon icon-list',
                    nowrap: false, //折行
                    rownumbers: true, //行号
                    striped: true, //隔行变色
                    idField: 'KeyId', //主键
                    singleSelect: true, //单选
                    frozenColumns: [[]],
                    columns: [[
{
 title: '客户名称', field: 'Customer_name', width: 120 
},
    {
 title: '跟进内容', field: 'Follow', width: 200 
},
    {
 title: '跟进时间', field: 'Follow_date', width: 200 
},
            {
 title: '下次跟进时间', field: 'follow_nextdate', width: 200 
},
            {
 title: '跟进方式', field: 'Follow_Type', width: 120 
},
             {
 title: '跟进人', field: 'employee_name', width: 120 
}
            ]],
                    queryParams: {
 categoryId: cc 
}
                 
});
             
}
         
});
     
},
    reload: function () {
 
        $('#dataDicType').tree('reload');
     
},
    getSelected: function () {
 
        return $('#dataDicType').tree('getSelected');
     
}
 
};
 
 
var dicDialog;
var grid = {
 
    bind: function (winSize) {
 
        $('#dicGrid').datagrid({
 
            url: actionURL,
            toolbar: '#toolbar',
            title: "数据列表",
            iconCls: 'icon icon-list',
            width: winSize.width,
            height: winSize.height,
            nowrap: false, //折行
            rownumbers: true, //行号
            striped: true, //隔行变色
            idField: 'KeyId', //主键
            singleSelect: true, //单选
            frozenColumns: [[]],
            columns: [[
{
 title: '客户名称', field: 'Customer_name', width: 120 
},
    {
 title: '跟进内容', field: 'Follow', width:200 
},
    {
 title: '跟进时间', field: 'Follow_date', width: 200 
},
            {
 title: '下次跟进时间', field: 'follow_nextdate', width: 200 
},
            {
 title: '跟进方式', field: 'Follow_Type', width: 120 
},
             {
 title: '跟进人', field: 'employee_name', width: 120 
}
            ]]
         
});
     
},
    getSelectedRow: function () {
 
        return $("#dicGrid").datagrid('getSelected');
     
},
    reload: function (cc) {
 
        $('#dicGrid').treegrid({
 
            url: actionURL,
            queryParams: {
 categoryId: cc 
}
         
});
     
}
 
};
 
function createParam(action, keyid) {
 
    var o = {
 
};
    var cnode = DicCategory.getSelected();
    top.$("#txtCustomer_id").val(cnode.id);
    top.$("#txtCustomer_name").val(cnode.text);
    var query = top.$('#uiform :not(textarea)').serializeArray();
    query = convertArray(query);
    o.jsonEntity = JSON.stringify(query);
    o.action = action;
    o.keyid = keyid;
    //return "json=" + JSON.stringify(o) + '&Customer_id=' + encodeURIComponent(top.$('#txtCustomer_id').val()) + '&Customer_name=' + encodeURIComponent(top.$('#txtCustomer_name').val());
    return "json=" + JSON.stringify(o) + '&content=' + encodeURIComponent(top.$('#txtFollow').val());
 
}
 
var CRUD = {
 
    initCtrl: function () {
 
        top.$('#txtFollow').xheditor({
 tools: 'full'
});
        top.$('#txt_dtnext').datebox({
 width: 100 
});
     
},
    add: function () {
 
        if (!DicCategory.getSelected()) {
 
            msg.warning('请选择客户！');
            return false;
         
}
        dicDialog = top.$.hDialog({
 
            title: '添加', width: 650, height: 500, href: formurl, iconCls: 'icon-add',
            onLoad: function () {
 
                // top.$('#uiform').validate();
                CRUD.initCtrl();
             
},
            submit: function () {
 
                if (top.$('#uiform').form('validate')) {
 
                    var query = createParam('add', '0');
                    jQuery.ajaxjson(actionURL, query, function (d) {
 
                        if (parseInt(d) > 0) {
 
                            msg.ok('添加成功！');
                            var cnode = DicCategory.getSelected();
                            grid.reload(cnode.id);
                            dicDialog.dialog('close');
                         
} else {
 
                            MessageOrRedirect(d);
                         
}
                     
});
                 
}
                return false;
             
}
         
});
     
},
    edit: function () {
 
        var row = grid.getSelectedRow();
        if (row) {
 
            dicDialog = top.jQuery.hDialog({
 
                title: '编辑', width: 800, height: 500, href: formurl, iconCls: 'icon-save',
                onLoad: function () {
 
                    CRUD.initCtrl();
                    top.$('#txt_dtnext').datebox('setValue', row.follow_nextdate);
                    top.$('#txtCustomer_id').val(row.Customer_id);
                    top.$('#txtCustomer_name').val('setValue', row.Customer_name);
                    top.$('#txtFollow_Type').val('setValue', row.Follow_Type);
                    top.$('#txtFollow').val(row.Follow);
                    top.$('#txt_dtnext').val(row.follow_nextdate);
                 
},
                submit: function () {
 
                    if (top.$('#uiform').form('validate')) {
 
                        var query = createParam('edit', row.KeyId); ;
                        jQuery.ajaxjson(actionURL, query, function (d) {
 
                            if (parseInt(d) > 0) {
 
                                msg.ok('修改成功！');
                                var cnode = DicCategory.getSelected();
                                grid.reload(cnode.id);
                                dicDialog.dialog('close');
                             
} else {
 
                                MessageOrRedirect(d);
                             
}
                         
});
                     
}
                    return false;
                 
}
             
});
 
         
} else {
 
            msg.warning('请选择要修改的行。');
         
}
     
},
    del: function () {
 
        var row = grid.getSelectedRow();
        if (row) {
 
            if (confirm('确认要执行删除操作吗？')) {
 
                var rid = row.KeyId;
                jQuery.ajaxjson(actionURL, createParam('delete', rid), function (d) {
 
                    if (parseInt(d) > 0) {
 
                        msg.ok('删除成功！');
                        var cnode = DicCategory.getSelected();
                        grid.reload(cnode.id);
                        
                     
} else {
 
                        MessageOrRedirect(d);
                     
}
                 
});
             
}
         
} else {
 
            msg.warning('请选择要删除的行。');
         
}
     
}
 
};