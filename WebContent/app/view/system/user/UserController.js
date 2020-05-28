Ext.define('Admin.view.system.user.UserController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.system_user_User',
    _container: undefined,
    _listGrid: undefined,
    _searchGrid: undefined,
    init: function() {
        this.control({
        	'system_user_User':{
    			beforerender: this._initViews
        	},
	        'system_user_UserSearch button[action=search]': {
	            click: this._onRefresh
	        },
        	'system_user_UserList':{
	            afterrender: this._onRefresh
        	},
		    'system_user_UserList actioncolumn': {  
		    	onShowEditWin: this._onShowEditWin,
		    	onResetPassword: this._onResetPassword,
		    	onDelete: this._onDelete
	        },
	        'system_user_UserEdit': {
	        	render: this._onLoadBaseData
	        },
            'system_user_User treepanel[name=usertree]>treeview ':{
                beforedrop:this.beforeiteminsert        , 
                drop:this.iteminsert ,
            },
        });
    },	
    /**
     * 界面加载后
     */
	_initViews : function(cmp, eOpts){
        if(just.data.user.loginInfo.roleid == '1')
        {
            cmp.down('panel[name=user_tree]').setHidden(false);
        }
		this._container = cmp;
		this._listGrid = cmp.down('system_user_UserList');
		this._searchGrid = cmp.down('system_user_UserSearch');
	},
    /**
	 * 刷新
	 */
	_onRefresh:function(){
       //var dd =  this.lookupReference('userAdd');
    //   var roleViewModel =  this.getView();
		this._loadData();
	},
	/**
     * 初始化搜索参数
     * @private
     */
    _initSearchParams:function(){
        var userses = Ext.ComponentQuery.query("form[name='user_UserSearch_from']");
        var userse = userses[userses.length-1];
                var formParams =userse.getValues();

       // var formParams =Ext.ComponentQuery.query("form[name='system_user_UserSearch']")[0].getValues();
        //var formParams = Ext.getCmp('system_user_UserSearch').getValues();
        var params = {};
        params.name = formParams.usrname;
      
        return params;
    },
	/**
	 * 加载数据
	 */
	_loadData : function(page){
       var ulists =Ext.ComponentQuery.query("gridpanel[name='system_user_UserList']");
       var ulist = ulists[ulists.length-1];
        var listViewModel =  ulist.getViewModel(),
		data = listViewModel.getData(),
		listStore = data.UserGrid;
		var params = this._initSearchParams();
		if(!page){
			page = 1;
		}
		listStore.currentPage = page;
		listStore.proxy.extraParams = {params: Ext.encode(params)};
		listStore.load();
	},
	/**
	 * 修改用户
	 */
    _onShowEditWin: function(column, grid, rowIndex, colIndex, node, e, record, rowEl){
        this.showEditView(record);
    },
    
	showEditView : function(record){
        /*this._isOnceEdit = true;
		this._isSchoolidEdit = true;*/
        var win = Ext.widget('system_user_UserEdit');
        win.down('form').loadRecord(record);
        win.down('textfield[name=account]').setValue(record.data.account);
	},
	/**
	 * 加载界面基础数据数据
	 */
	_onLoadBaseData : function(cmp, eOpts){
		//加载角色数据
		var viewModel =  cmp.getViewModel(),
		data = viewModel.getData(),
		roleStore = data.RoleGrid;
      	//角色
	   /* var params = {};
        params.usertype1 = '';
        roleStore.proxy.extraParams = {params: Ext.encode(params)};*/
        roleStore.proxy.url = roleStore.proxy.api.LIST;
        roleStore.load();
	},
    /**
     * 重置密码
     */
    _onResetPassword:function(grid, rowIndex, colIndex,b,h,v){
        var me = this;
        var params = {};
        params.id = v.data.staff_id;
        if(Ext.MessageBox.confirm("系统提示","是否重置密码？",function(e){
            if(e == 'yes'){
                just.showWaitingDlg("正在重置,请稍候...");
                Ext.Ajax.request({
                    url: me._listGrid.getStore().getProxy().api.REPWD,
                    params : {
                   	   params: Ext.encode(params)
                     },
                    success : function(response, options){
                        var json = Ext.JSON.decode(response.responseText);
                        just.hideWaitingDlg(json.info,false);
                        if (json.status == '200') {
                            me._loadData(me._listGrid.store.currentPage);
                            Ext.example.msg('系统提示', json.info);
                        	
                        }
                   }
                });
            }
        }));
    },
    onuseraddClick:function(){
        var width = Math.floor(Ext.Element.getViewportWidth() * 0.3),
            height = Math.floor(Ext.Element.getViewportHeight() * 0.55);
        var params= {
                targetCfg: {
                    //put any extra configs for your view here
                },
                windowCfg: {
                    // Any configs that you would like to apply for window popup goes here
                    title: '添加用户',
                    width:width,
                    height:height
                }
            };

            this.setCurrentView('userAdd',params);
            var win = Ext.getCmp('userAdd');
            var te = win.down('textfield[name=staff_id]');
            te.setReadOnly(false)  ;
          
    },
    onusereditClick:function(grid, rowIndex, colIndex,b,h,v){
    

        //console.log(v.data);
                var width = Math.floor(Ext.Element.getViewportWidth() * 0.3),
            height = Math.floor(Ext.Element.getViewportHeight() * 0.55);

        // We use percentage sizes so we'll never overflow the screen (potentially
        // clipping buttons and locking the user in to the dialog).

        //this.setSize(Math.floor(width * 0.3), Math.floor(height * 0.45));
 
        
        var params= {
                targetCfg: {
                    //put any extra configs for your view here
                },
                windowCfg: {
                    // Any configs that you would like to apply for window popup goes here
                    title: '编辑用户',
                    width:width,
                    height:height
                }
            };

            this.setCurrentView('userAdd',params);
            var win = Ext.getCmp('userAdd');
            
           win.loadRecord(v);
           var te = win.down('textfield[name=staff_id]');
           te.setValue(v.data.staff_id);
           te.setReadOnly(true)  ;
           var re = win.down('radiogroup');
           re.setValue({useflag:v.data.useflag});

   
    },
    setCurrentView: function(view, params) {
       var cfg = Ext.apply({
                xtype: 'window',
                items: [
                    Ext.apply({
                        xtype: view
                    }, params.targetCfg)
                ]
            }, params.windowCfg);

            Ext.create(cfg);
    },
   
    onusersaveClick: function(view) {
        var me = this;
        var listStore = this.getView().getViewModel().getData().UserGrid;
        var addUrl = listStore.getProxy().api.ADD;
        var me = this;
       
        var win = view.up('window');
        var form = view.up('window').down('form');
        var type   = form.down('textfield[name=staff_id]').readOnly?1:0;

        var formparams = form.getValues();
        if(form.isValid()){
           just.showWaitingDlg("正在保存,请稍候...");
            var params={};
            params.staff_id = formparams.staff_id;
            params.passwd =  formparams.passwd;
            params.usrname =  formparams.usrname;
            params.idcard =  formparams.idcard;
            params.limitnum =  formparams.limitnum;
            params.useflag =  formparams.useflag;
            params.roleid =  formparams.roleid;
            params.mobile =  formparams.mobilenum;
            params.acttype = type;
            Ext.Ajax.request({
                submitEmptyText:true, 
                url : addUrl,
                params:{params:Ext.encode(params)},
                success:function(response, opts){
                    var jsonObj = Ext.JSON.decode(response.responseText);
            
                    just.hideWaitingDlg(jsonObj.info,false);
                    Ext.example.msg('系统提示', jsonObj.info);
                   
                    if(jsonObj.status == '200'){   
                        
                       me._loadData();
                        win.close();//关闭窗体
                    }
                }
            })
        }
    },
    beforeiteminsert: function(node, data, overModel, dropPosition, dropHandlers) {//注：此处事件是gridviewdragdrop 的放置监听事件  
              dropHandlers.wait = true;

          
             
              Ext.MessageBox.confirm('提示', '真的要移动吗？', function(btn){ 
                  
                  if (btn === 'yes') { 
                      dropHandlers.processDrop();  
                  } else {  
                      dropHandlers.cancelDrop();  
                  }  
              });  
          }, 
    iteminsert: function( node, data, overModel, dropPosition, eOpts) {  
             
              

         
        
              Ext.Ajax.request({ 
                    submitEmptyText:true,  
                    url : just.getUrl('/sys/user/droptreepanel.action'),
                     params : {
                        id:data.records[0].raw.id,
                        pid: overModel.raw.pid
                     }, 
 
                    success:function(response, opts){
                       var jsonObj = Ext.JSON.decode(response.responseText);
                    
                    Ext.Msg.alert('系统提示',jsonObj.info);          
                     
                    }                           
                });  

          } ,
    _onsetstation:function(grid, rowIndex, colIndex,b,h,v){
        if(v.data.staff_id=='1001')
         {
            Ext.example.msg('系统提示！', '该账户有所有站的权限，不需要添加！');   
            return; 
         }   
        var win = this.lookupReference('Userstationlistwin');
        
        if (!win) {
            win = new Admin.view.system.user.Userstationlistwin();
            this.getView().add(win);
        }       
        win.show();
        win.win_staff_id = v.data.staff_id;       
        this._loadsubstaion();
          },
       _loadsubstaion:function(){ 
            var clists =Ext.ComponentQuery.query("window[name='Userstationlistwin']");
            var clist = clists[clists.length-1],


            listViewModel =  clist.getViewModel(),
            data = listViewModel.getData(),
            listStore = data.UserGrid;
      
            listStore.proxy.extraParams = {params: Ext.encode({saff_id:clist.win_staff_id})};
            listStore.proxy.url = listStore.proxy.api.LOADSTATION;
            listStore.load();
          },



        onsubstationaddClick:function(c){
            var sub_staffid = c.up('window').win_staff_id;
            var win = this.lookupReference('Userstationwin');
        
            if (!win) {
                win = new Admin.view.system.user.Userstationwin();
                this.getView().add(win);
            }
            
            win.show();
            win.win_staff_id = sub_staffid; 


           this._loadaddsubstation();
        },
      _loadaddsubstation:function(){
        var clists =Ext.ComponentQuery.query("window[name='Userstationwin']");
        var clist = clists[clists.length-1],

         listViewModel =  clist.getViewModel(),
        data = listViewModel.getData(),
        listStore = data.UserGrid;
        listStore.proxy.url = listStore.proxy.api.NOTINSTATION;
        listStore.proxy.extraParams = {params: Ext.encode({saff_id:clist.win_staff_id})};
        listStore.load({ 
            callback: function(records, options, success){
                if(records.length < 1){
                    clist.setHidden(true); 
                    Ext.example.msg('系统提示！', '当前没有满足条件车站，无法添加！');                            
                }                             
            } 
        });


        bureauStore = data.bureaumod;
        bureauStore.proxy.url = bureauStore.proxy.api.BUREAU;
        bureauStore.load({ 
            callback: function(records, options, success){
                if(records.length < 1){
                    Ext.example.msg('系统提示！', '当前没有开通订票的路局！');                            
                }                             
            } 
        });

      },
      onsubstationSub:function(c){
        var me = this;
        var win = c.up('window');
        var tab = win.down('tabpanel').getActiveTab();
        var tag = win.down('tagfield[name=user_add_tagf]').getValue();//.join('#')+'#';;
        var url = just.getUrl('/sys/user/substation_add_c.action');
        if (tab.title = '按路局添加') {
            tag = win.down('tagfield[name=bueau_add_tagf]').getValue();//.join('#')+'#';;
            url =  just.getUrl('/sys/user/stationbybureau_c.action');          
        }
        

     
        var params={};
        params.substaff_id = win.win_staff_id;
        params.passwd =  tag.join('","');
        params.passwd =  '"'+params.passwd+'"';
        Ext.Ajax.request({
                    url: url,
                    params : {
                       params: Ext.encode(params)
                     },
                    success : function(response, options){
                        var json = Ext.JSON.decode(response.responseText);
                        just.hideWaitingDlg(json.info,false);
                        if (json.status == '200') {
                            me._loadsubstaion();
                            Ext.example.msg('系统提示', json.info);
                            win.close();
                            
                        }
                   }
        });


      },
      _ondelsubstation:function(grid, rowIndex, colIndex,b,h,v){
        var me = this;
        var params = {};
        params.id = v.data.staff_id;
        params.station_telecode = v.data.station_telecode;
        if(Ext.MessageBox.confirm("系统提示","是否要删除？",function(e){
            if(e == 'yes'){
                just.showWaitingDlg("正在删除,请稍候...");
                Ext.Ajax.request({
                    url: just.getUrl('/sys/user/substation_del_d.action'),
                    params : {
                       params: Ext.encode(params)
                     },
                    success : function(response, options){
                        var json = Ext.JSON.decode(response.responseText);
                        just.hideWaitingDlg(json.info,false);
                        if (json.status == '200') {
                            me._loadsubstaion();
                            Ext.example.msg('系统提示', json.info);
                            
                        }
                   }
                });
            }
        }));

      },
      onTabChange: function(tabs, newTab, oldTab) {
        Ext.suspendLayouts();
        console.log(tabs.activeTab.getTabIndex( )  );
        console.log(tabs.activeTab);
        Ext.resumeLayouts(true);
    },
    initPermission : function(cmp){
        var me = this;
        var refs = me.getReferences(),
        user_grid = refs.user_grid;
        
        var util = Ext.create(just.createUtil('Permission'));
                util.initPermission(cmp,user_grid);
                
             },


});
