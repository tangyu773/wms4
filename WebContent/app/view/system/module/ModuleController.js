/**
 * 模块管理控制器
 * Created by xiaozou on 15-10-22.
 */
Ext.define('Admin.view.system.module.ModuleController',{
	extend: 'Ext.app.ViewController',	
    alias: 'controller.system_module_Module',	
    _listGrid: undefined,
    init: function() {
        this.control({
        	'system_module_Module':{
        		beforerender: this._initViews
        	},
                      
            'system_module_ModuleAdd button[action=save]':{
                click: this._onAddSave
            }
        });
    },
    /**
     * 界面加载后
     */
	_initViews : function(cmp, eOpts){
		this._container = cmp;
		this._listGrid = cmp.down('system_module_ModuleList');
        var userses = Ext.ComponentQuery.query("treepanel[name='system_module_ModuleList']"),
         userse = userses[userses.length-1];

         userse.expandAll();
 
	},
    /**
     * 刷新界面
     */
	_onResfresh : function(cmp, eOpts){
		listStore = this._listGrid.store;
        //listStore.proxy.url = just.getUrl('/sys/module/show.action');
		listStore.load();
        //this.expandAll();
    },
    /**
     * 显示添加界面
     */
    _onShowAddWin :function(cmp, eOpts){
        var addVin = Ext.widget('system_module_ModuleAdd');
    /*    var saveButton = addVin.down('button[name=saveButton]');
        saveButton.on('click',this._onAddSave);    */
    },
    /**
     * 保存添加模块
     */
    _onAddSave : function(cmp, eOpts){
    	Ext.cmp
    	console.log(this._listGrid);
    	console.log(this.getView());
    	var listStore = this.getView().getViewModel().getData().ModuleTreeGrid;
    	console.log(listStore);
    	var addUrl = listStore.getProxy().api.ADD;
        var me = this;
        var win = cmp.up('window');
        var form = cmp.up('window').down('form');
        var formparams = form.getValues();
        if(form.isValid()){
            just.showWaitingDlg("正在保存菜单信息,请稍候...");
            var params={};
            params.pid = formparams.pid;
            params.text =  formparams.text;
            params.moduleurl =  formparams.moduleurl;
            params.compoment =  formparams.compoment;
            params.msort =  formparams.msort;
            params.status =  formparams.status;
            params.param =  formparams.param;
            params.menu_type = formparams.menu_type;
            Ext.Ajax.request({
            	submitEmptyText:true, 
            	url : addUrl,
                params:{params:Ext.encode(params)},
                success:function(response, opts){
                    var jsonObj = Ext.JSON.decode(response.responseText);
                    just.hideWaitingDlg(jsonObj.info,false);
                    Ext.Msg.alert('系统提示',jsonObj.info);
                    if(jsonObj.status == '200'){    
                        console.log(listStore);
                        listStore.load({ 
                        	callback: function(records, options, success){
                        		console.log(records);
                        	} 
                        });
                        win.close();//关闭窗体
                    }
                }
            })
        }
    },
    onuseraddClick:function(){
        var width = Math.floor(Ext.Element.getViewportWidth() * 0.4),
            height = Math.floor(Ext.Element.getViewportHeight() * 0.4);
        var params= {
                targetCfg: {
                    //put any extra configs for your view here
                },
                windowCfg: {
                    // Any configs that you would like to apply for window popup goes here
                    title: '添加模块',
                    width:width,
                    height:height
                }
            };

            this.setCurrentView('system_module_ModuleAdd',params);
            /*var win = Ext.getCmp('userAdd');
            var te = win.down('textfield[name=userss]');
            te.enable();*/
          
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
    initPermission : function(cmp){
        var me = this;
        var refs = me.getReferences(),
        modulegrid = refs.modulegrid;
        
        var util = Ext.create(just.createUtil('Permission'));
                util.initPermission(cmp,modulegrid);
                
             },
});