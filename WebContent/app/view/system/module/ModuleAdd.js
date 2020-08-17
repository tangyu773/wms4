

/**
 * 新增角色
 * Created by xiaozou on 15-10-20.
 */
Ext.define('Admin.view.system.module.ModuleAdd', {
    extend : 'Ext.window.Window',
    alias: 'widget.system_module_ModuleAdd',
    autoShow : true,//自动打开
    modal : true,//模态窗口
    width : Math.floor(Ext.Element.getViewportWidth() * 0.4),//窗体宽度
    height: Math.floor(Ext.Element.getViewportHeight() * 0.4),
    resizable: false,
    isedit:0,
    title: '添加模块',
    constrain : true,//是否限制窗口超出浏览器
    plain : false,  //是否设置窗口透明背景
    autoScroll : true,  //是否添加滚动条
    buttonAlign :'center',
    requires: [
         'Admin.view.system.module.ModuleViewModel'
    ],
    controller : 'system_module_Module',
    viewModel : {
        type : 'system_module_Module'
    },
    items:[{
        xtype : 'form',//Form表单
        layout : 'column',//列布局
        autoScroll : true,  //是否添加滚动条
        defaultType : 'textfield',//默认的Form表单组件
        baseCls:'x-plain',
        defaults: {
            labelWidth : 70,
            labelAlign : 'right',
            columnWidth : 0.5,//列宽百分百
            padding: '10 10 10 10',//行列间距
            selectOnFocus : true,//选中所有内容
            allowBlank : false,
            minLength : 0,
            maxLength : 30
        },
        items:[{
            name : 'text',
            fieldLabel : '<span class="form_require_symbol">*</span>模块名称',
            emptyText : '请输入菜单名称',
            blankText : '菜单名称不能为空'
        },{
            name : 'compoment',
            fieldLabel : '组件名称',
            emptyText : '请输入组件control路径',
            allowBlank: true
        },{
            xtype: 'numberfield',
            name : 'msort',
            fieldLabel : '<span class="form_require_symbol">*</span>模块排序',
            minValue : 1,
            value : 1
        },{
            name:'status',
            xtype:'combo',
            mode:'local',
            fieldLabel: '<span class="form_require_symbol">*</span>组件状态',
            editable:false,
            selectOnFocus:false,
            emptyText : '请选择状态',
            valueField:'value',
            displayField:'text',
            value:1,
            store:just.ST_CUR_STATUS1
        },{
            name : 'moduleurl',
            fieldLabel : '相关路径',
            emptyText : '请输入URL',
            allowBlank:true
        },
        {
            xtype:'combobox',
            name : 'param',
            fieldLabel : '模块参数',
            emptyText : '选择模块参数',
            selectOnFocus : false,
            editable:false,
            store:just.ST_MODULE,
            queryMode: 'local',
            displayField: 'text',
            valueField: 'value',
            allowBlank:true
        },{
            name : 'iconCls',
            fieldLabel : '图标',
            emptyText : '请输入图标',
            allowBlank:true
        },{
            xtype: 'treepicker',
            name : 'pid',
            fieldLabel: '<span class="form_require_symbol">*</span>父级菜单',
            emptyText : '请选择父级菜单',
            blankText : '父级菜单不能为空',
            selectOnFocus : false,
            editable:false,
            rootVisible:true,
            valueField: 'id',
            displayField: 'text',
            store: Ext.create('Admin.store.system.module.ModuleTree')
           /* bind : {
                store : '{ModuleTreeGrid}'
             }*/
        }]
    }],
    buttons : [{
        text : '保存',
        action : 'save',
        iconCls : 'saveIcon'
    },{
        text : '取消',
        iconCls : 'cancelIcon',
        handler : function(btn){
            var window = btn.ownerCt.ownerCt;
            window.close();
        }
    }],
    initComponent : function(){
        this.callParent(arguments);
    }
});