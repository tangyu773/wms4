/**
 *  系统用户修改界面
 * Created by xiaozou on 15-10-20.
 */
Ext.define('Admin.view.order.OrderAdd',{
    extend: 'Ext.window.Window',
    alias: 'widget.orderadd',
    autoShow : false,//自动打开
    modal : true,//模态窗口
    width : 1200,//窗体宽度
    height : 600,//窗体高度
    resizable: false,
    iconCls: 'editIcon',
    title: '出库详情',
    constrain : true,//是否限制窗口超出浏览器
    plain : true,   //是否设置窗口透明背景
    bodyPadding : '1 1 1 1',//表单边框 上内边距、右内边距、下内边距、左内边距
    autoScroll : true,  //是否添加滚动条
    buttonAlign :'center',
    controller : 'order_order',
    viewModel: {
        type: 'order_order'
    },
    items:[{
        xtype : 'form',//Form表单
        columnWidth : 1.0,
        layout : 'column',//列布局
        autoScroll : true,  //是否添加滚动条
        defaultType : 'textfield',//默认的Form表单组件
        baseCls:'x-plain',
        defaults: {
            labelWidth : 70,
            labelAlign : 'right',
            columnWidth : 0.3,//列宽百分百
            padding: '10 10 10 10',//行列间距
            allowBlank : true,
            minLength : 0,
            maxLength : 30,
            readOnly : true,
        },
        items:[   {
                    name : 'ORDERNUMBER',
                    fieldLabel : '订单号'
                },{
                    name : 'SOURCEBILLNUMBER',
                    fieldLabel : '来源单号'
                },{
                    name : 'DESCRIPTION',
                    fieldLabel : '单据类型'
                },{
                    name : 'CREATETIME',
                    fieldLabel : '发货时间'
                },{
                    name : 'DES',
                    fieldLabel : '状态'
                }  
        ]
    },{
        columnWidth : 1.0,
        xtype: 'gridpanel',
        border:true,
        reference: 'ordergrid',
        name:'order_detail_list',
         //selModel: Ext.create('Ext.selection.CheckboxModel'),
         height:'fit',//Math.floor(Ext.Element.getViewportHeight()-80),
         bind : {
            store : '{detail}'
         },
         viewConfig : {
            emptyText:'<div style="text-align:center;padding:8px;font-size:16px;">查询无数据</div>',
            stripeRows: true,
            enableTextSelection:true  ,
         },
         defaults: {
            align: "center",
            width : 80,
         },
        columnLines:true,
        columns : [ {
            xtype: 'rownumberer',
            text : '序号',
            align: "left",
            width : 50,
        },{
            dataIndex: 'LINENUM',
            text : '行号',
            width : 50,
        },{
            dataIndex : 'CODE',
            text : '物料代码',
        },{
            dataIndex : 'NAME',  
            text : '物料名称',
            width : 350,
        },{
            dataIndex : 'TARGETBATCHNUM',  
            text : '批次',
        },{
            dataIndex : 'QTY',  
            text : '数量',
            width : 50,
        },{
            dataIndex : 'UNITID',  
            text : '单位',
            width : 50,
        },{
            dataIndex : 'FACTORY',  
            text : '出库工厂',
        },{
            dataIndex : 'TARGETFACTORY',  
            text : '目标工厂',
        },{
            dataIndex : 'LOCATION',  
            text : '出库库存地点',
        },{
            dataIndex : 'TARGETLOCATION',  
            text : '目标库存地点',
        }
    ],
             

}

],

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
    }]
});


/*

*/