/**
 * 用户界面 Created by xiaozou on 15-10-20.
 */

Ext.define('Admin.view.system.user.UserList', {
	 extend: 'Ext.grid.Panel',
	 alias: 'widget.system_user_UserList',
	// cls: 'email-inbox-panel shadow-panel',
	 cls: 'user-grid',
	 //id:'system_user_UserList',
	 name:'system_user_UserList',
	 viewModel: {
	     type: 'system_user_User'
	 },
	 height:Math.floor(Ext.Element.getViewportHeight()-90),
	 bind : {
		store : '{UserGrid}'
	 },
	 reference: 'user_grid',
	 viewConfig : {
	
		 stripeRows: true
	 },


    forceFit: true,
	
    columnLines:true,
	columns : [ {
		dataIndex : 'staff_id',
		menuDisabled : true,
		text : '用户账号',
		align: "center",
		//locked   : true,
		//width : 110,
       
	}, {
		dataIndex : 'usrname',
		text : '用户姓名',
		align: "center",
		//width : 100,
		
	}, {
		dataIndex : 'idcard',
		text : '身份证',
		//locked   : true,
		align: "center",
		width : 160,
        
	}, {
		dataIndex : 'mobilenum',
		text : '电话号码',
		align: "center",
		//width : 130,
        
	}, {
		dataIndex : 'roledes',
		text : '角色名称',
		align: "center",
		//width : 90,
       
	}, {
		dataIndex : 'useflag',
		text : '状态',
		align: "center",
		//width : 120,
        renderer:function(v) {
    		return just.util.valueTransText(v,just.data.CUR_STATUS,'color_status');
    	}
	}, {
		dataIndex : "limitnum",
		align : "center",
		text : "每日限制订单数",
		align: "center",
		width : 120
	},
    {
        menuDisabled: true,
        sortable: false,
       // locked   : true,
        xtype: 'actioncolumn',
      //  width: 90,
		align : "center",
        text: '操作',
        items: [{
            iconCls: 'fa fa-pencil-square fa-lg opear-button',
            action: 'edit',
            tooltip: '修改用户',
            handler: 'onusereditClick'
        }, {
        	iconCls: 'fa fa-key fa-lg opear-button',
        	action: 'edit',
            tooltip: '重置密码',
            handler: '_onResetPassword'
        }, {
        	iconCls: 'fa fa-cog fa-lg opear-button',
        	action: 'edit',
            tooltip: '设置允许订票车站',
            handler: '_onsetstation'
        }]
    }],
	dockedItems : [{
        xtype : 'toolbar',
		items : [ {
                    xtype : 'toolbar',
                    items : [ {
								xtype : 'button',
								text : '刷新',
								iconCls : 'icon-refresh',
								ui: 'soft-blue',
								width:65,
								listeners: {
			                        click: '_onRefresh'
			                        }
							},'|',{
                        xtype : 'button',
                        ui: 'soft-blue',
                        text : '新增用户',
                        iconCls : 'fa fa-align-left fa-plus-square-o',
                        action:'add',
                        listeners: {
                        click: 'onuseraddClick'
                        }
                    },'|',{
                    	xtype : 'form',
        name :'user_UserSearch_from',
        layout : 'column',// 列布局
		margin : '0 0 0 0',
		defaultType : 'textfield',// 默认的Form表单组件
		defaults : {
		labelWidth : 60,
		labelAlign : 'right',
		columnWidth : 0.18,// 列宽百分百
		padding : '0 0 0 0',// 行列间距
		selectOnFocus : true,// 选中所有内容
		allowBlank : true,
        height: 25,
		minLength : 0,
		maxLength : 30
	},
	items : [
		{
			name : 'usrname',
			fieldLabel : '<span style="font-weight:bold">姓名</span>',
			emptyText : '请搜索姓名',
			blankText : '姓名不能为空'
		},{
            xtype: 'button',
            iconCls: 'icon-search',
            text : '搜索',
            action: 'search',
            margin: '0 0 0 15',
            columnWidth : 0.08,
            listeners: {
                        click: '_onRefresh'
                        }
        }]
		

                    } ]
                }]
	}, {
		xtype : 'pagingtoolbar',
		dock : 'bottom',
		displayInfo : true,
		bind : {
			store : '{UserGrid}'
		}
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});
