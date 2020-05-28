Ext.define('Admin.view.order.Order', {
	extend : 'Ext.container.Container',
	xtype : 'order_order',
	title : '用户管理',
	name:'container_order_main',
	requires: [
	    'Admin.view.order.OrderViewModel',
	    'Admin.view.order.OrderController',
	    'Admin.view.order.OrderAdd'
    ],
     cls: 'shadow-panel',
	controller : 'order_order',
	viewModel : {
		type : 'order_order'
	},
	listeners: {
        afterrender: 'initPermission'
    },
	// 列表布局
	layout : 'column',
    items: [{
		columnWidth : 1.0,
        xtype: 'gridpanel',
        //cls: 'user-grid',
        border:false,
        reference: 'ordergrid',
		 //id:'system_user_UserList',
		 name:'order_order_list',

		 height:Math.floor(Ext.Element.getViewportHeight()-80),
		 bind : {
			store : '{order}'
		 },
		 viewConfig : {
		
			 stripeRows: true,
			 enableTextSelection:true  ,
		 },
	    //forceFit: true,	
	   // columnLines:true,

	    columnLines:true,
	   // frame: true,
		//selType : 'checkboxmodel',//多选框模式
	    
	    


	    columns : [ {
                xtype: 'rownumberer'
            },{
		dataIndex : 'staff_id',
		text : '账号',
		//locked   : true,
		align: "center",
		width : 60,
       
	},{
		dataIndex : 'seqno',
		text : '单号',
		//locked   : true,
		align: "center",
		width : 100,
		renderer:function(v,m, r) {  
			/*var id = Ext.id(); 
			m.tdAttr = 'data-qtip="'+v+'"';
			Ext.defer(function () {                         
                            Ext.widget('displayfield', {
                                renderTo: id,
                                value: '<div style="white-space:normal;">'+v+'</div>', 
                                layout : 'fit', 
                             
                                height : 100,
                               
                                readOnly:true
                                });                     
                            }, 50);  */
		  if(v!=null){		                                                                                                   
           if(v.length<9)
           		return '-';
           	else
           		return v;
           }
        }
       
	}, {
		dataIndex : 'reason',
		text : '订单结果',
		align: "center",
		//locked:true,
		width : 160,
		renderer : function(v) {
			if(v!=null){	
		
					if(v.replace(/(^\s*)|(\s*$)/g, "") == '预定成功')
					{
						return '<span class="color_status_1">' + v + '<span>';
					}
					else{
						return '<span class="color_status_3">' + v + '<span>';
					}
						
				}
					
				}	
		
       
	},{
		dataIndex : 'submit_date',	
		text : '提交时间',
		align: "center",
		width : 150,
		
       
	},{
		dataIndex : 'train_date',
		text : '乘车日期',
		align: "center",
		width : 90,
		 renderer:function(v,m,r) {                                                                                                    
                      return v.substring(0,10);
                    }
       
	}, {
		dataIndex : 'station_train_code',
		text : '车次',
		align: "center",
		width : 70,

		
	}, {
		dataIndex : 'from_station',
		text : '发站',
		align: "center",
		width : 70,
		renderer : function(v,m,r) {
					return r.get('form_station_name');
				}
	
		
	}, {
		dataIndex : 'to_station',
		text : '到站',
		align: "center",
		width : 70,
		renderer : function(v,m,r) {
					return r.get('to_station_name');
				}
		
		
	}, {
		dataIndex : 'seat_type_code',
		text : '席别',
		align: "center",
		width : 70,
		renderer : function(v) {
					return just.util.valueTransText(v, just.data.SEAT_TYPE);
				}
		
		
	},
	{
		dataIndex : 'total_num',
		text : '总张数',
		align: "center",
		width : 120,
		renderer : function(v,m,r) {
			if(r.get('child_num')	!= '0'){
				return v+' (含'+r.get('child_num')	+'张小孩票)';
			}
			else{
				return v;
			}
			
		}	
	}, {
		dataIndex : 'total_price',
		text : '总票价',
		align: "center",
		//locked:true,
		width : 90,
		renderer : function(v) {
			if(v == 0)
			{
				return '--';
			}
        	else {
            	//return '<span style=color:orange>￥'+v.toString().substring(1,v.length-3)+'.'+v.toString().substring(v.length-4,v.length)+'</span>';
            	return '<span style=color:orange>￥'+v.toString().substring(0,v.toString().length-2)+'.'+v.toString().substring(v.toString().length-2,v.toString().length)+'</span>';
        	}
					
		}	
		
       
	},{
		dataIndex : 'seat_info',
		text : '座位号',
		width : 120,
		align: "center",     
		renderer : function(v) {
			if( v == null || v== '')
			{
				return '--';
			}
        	else {
            	return  v;
        	}
					
		}	  
	}, {
		dataIndex : 'id_nos',
		text : '证件号码',
		align: "center",
		width : 160,
		renderer:function(v,m, r) {  
			/*var id = Ext.id(); 
			m.tdAttr = 'data-qtip="'+v+'"';
			Ext.defer(function () {                         
                            Ext.widget('displayfield', {
                                renderTo: id,
                                value: '<div style="white-space:normal;">'+v+'</div>', 
                                layout : 'fit', 
                             
                                height : 100,
                               
                                readOnly:true
                                });                     
                            }, 50);  
		  */
           		return v;
           
        }
		
		
	},{
		dataIndex : 'process_time_stamp',	
		text : '处理时间',
		align: "center",
		width : 150,
		
       
	},{
		dataIndex : 'ani',
		text : '电话',
		align: "center",
		width : 110,
       
	},{
		dataIndex : 'sid',
		text : '预订ID',
		align: "center",
		//locked   : true,
		width : 100,
       
	}
   ],
    dockedItems : [{
        xtype : 'toolbar',
		items : [ {
                    xtype : 'toolbar',
                    items : [ {
                        xtype : 'button',
                        action:'add',
                        ui: 'soft-blue',
                        text : '新增订单',
                        iconCls : 'fa fa-align-left fa-plus-square-o',
                        listeners: {
                        click: 'onuseraddClick'
                        }
                    },'| ',{
                    	xtype : 'form',
        name :'order_search_form',
        layout : 'column',// 列布局
		margin : '0 0 0 0',
		defaultType : 'textfield',// 默认的Form表单组件
		defaults : {
		labelWidth : 60,
		labelAlign : 'right',
		//columnWidth : 0.15,// 列宽百分百
		padding : '0 0 0 0',// 行列间距
		selectOnFocus : true,// 选中所有内容
		allowBlank : true,
        height: 25,
		minLength : 0,
		maxLength : 30
	},
	items : [
		{
			name : 'seqno',
			fieldLabel : '<span style="font-weight:bold">订单号</span>',
			emptyText : '请搜索订单号',
			width:240,
			blankText : '订单不能为空'
		},{
			xtype: 'datefield',
            fieldLabel: '提交日期',
            name: 'st_date',
            emptyText : '请输入开始日期',
            width:200,
            value: Ext.Date.add(new Date(), Ext.Date.DAY,-7),
            format:'Y-m-d'
		},{
			xtype: 'datefield',
            fieldLabel: '结束日期',
            name: 'et_date',
            emptyText : '请输入结束日期',
            width:200,
          
            value: new Date(),
            format:'Y-m-d'
		},{
            name:'status',
            xtype:'combo',
            mode:'local',
            fieldLabel: '状态',
            labelWidth : 35,
            editable:false,
            selectOnFocus:false,
            emptyText : '请选择状态',
            valueField:'value',
            displayField:'text',
            width:160,
            value:0,
            store:just.ST_CUR_STATUS2
        },{
            xtype: 'button',
            iconCls: 'icon-search',
            text : '搜索',
            action: 'search',
            width:80,
            margin: '0 0 0 20',
            columnWidth : 0.15,
            listeners: {
                        click: '_onLoadBaseData'
                        }
        }]
		

                    },'| ',{
                    	xtype : 'button',
                        ui: 'soft-blue',
                        text : '导   出',
                        iconCls : 'fa fa-align-left fa-sign-out ',
                        listeners: {
                        click: 'onorderexport'
                        }

                    }]
                }]
	}, {
		xtype : 'pagingtoolbar',
		dock : 'bottom',
		displayInfo : true,
		bind : {
			store : '{order}'
		}
	} ],

	}

	],
	margin : '10 10 5 10'
});
