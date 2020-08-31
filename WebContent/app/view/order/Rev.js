Ext.define('Admin.view.order.Rev', {
	extend : 'Ext.panel.Panel',
	xtype : 'order_Rev',
	title : '入库单管理',
	name:'container_order_Rev',
	requires: [
	'Admin.view.order.OrderViewModel',
	'Admin.view.order.RevController',
	'Admin.view.order.OrderAdd'
	],
	cls: 'shadow-panel',
	controller : 'order_rev',
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
		 selModel: Ext.create('Ext.selection.CheckboxModel'),
		 height:Math.floor(Ext.Element.getViewportHeight()-120),
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
			xtype: 'rownumberer',
			text : '序号',
			align: "left",
			width : 50,
		},{
			dataIndex : 'RECEIVINGNUMBER',
			text : '订单号',
		//locked   : true,
		align: "center",
		width : 150,

	},{
		dataIndex : 'SOURCEBILLNUMBER',
		text : '来源单号',
		//locked   : true,
		align: "center",
		width : 120,
		/*renderer:function(v,m, r) {  
			var id = Ext.id(); 
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
		  if(v!=null){		                                                                                                   
           if(v.length<9)
           		return '-';
           	else
           		return v;
           }
       }*/
       
   }, {
   	dataIndex : 'CREATETIME',
   	text : '接收日期',
   	align: "center",
		//locked:true,
		width : 160,
		/*renderer : function(v) {
			if(v!=null){	
		
					if(v.replace(/(^\s*)|(\s*$)/g, "") == '预定成功')
					{
						return '<span class="color_status_1">' + v + '<span>';
					}
					else{
						return '<span class="color_status_3">' + v + '<span>';
					}
						
				}
					
			}	*/


		},{
			dataIndex : 'DES',	
			text : '状态',
			align: "center",
			width : 190,


		},{
			dataIndex : 'DESCRIPTION',
			text : '获取接口',
			align: "center",
			width : 150,
		// renderer:function(v,m,r) {                                                                                                    
                      //return v.substring(0,10);
                  //  }

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
              		},'|', {
              			xtype : 'button',
              			action:'query',
              			ui: 'soft-blue',
              			text : ' 查询 ',
              			iconCls : 'icon-search',
              			listeners: {
              				click: 'onqueryClick'
              			}
              		},{
                       	xtype : 'form',
                       	name :'order_search_form',
        layout : 'column',// 列布局
        margin : '0 0 0 0',
		defaultType : 'textfield',// 默认的Form表单组件
}]
}]
} ],

}

],
margin : '10 10 5 10'
});


