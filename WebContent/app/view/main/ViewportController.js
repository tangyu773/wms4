Ext.define('Admin.view.main.ViewportController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.mainviewport',
    _treestore:undefined,
	init : function() {
		this.control({
		    'main_Viewport':{
                beforerender:this.initUserInfo
            }
		});
	},
    listen : {
        controller : {
            '#' : {
                unmatchedroute : 'onRouteChange'
            }
        }
    },

    routes: {
        ':node': 'onRouteChange'
    },
    
    /**
     * 设置隐藏、展示左边菜单
     */
    onToggleNavigationSize: function () {
    	//查找到左边控件是扩展还是隐藏，并设置宽度
        var me = this,
            refs = me.getReferences(),
            navigationList = refs.navigationTreeList,
            wrapContainer = refs.mainContainerWrap,
            collapsing = !navigationList.getMicro(),
            new_width = collapsing ? 64 : 200;
        if (Ext.isIE9m || !Ext.os.is.Desktop) {
            Ext.suspendLayouts();
            //设置上面标题的宽度
            refs.senchaLogo.setWidth(new_width);
            //设置左边菜单项的宽度
            navigationList.setWidth(new_width);
            //设置隐藏还是展示
            navigationList.setMicro(collapsing);
            //回复布局
            Ext.resumeLayouts(); 
            wrapContainer.layout.animatePolicy = wrapContainer.layout.animate = null;
            wrapContainer.updateLayout();
        }
        else {
            if (!collapsing) {
                navigationList.setMicro(false);
            }
            refs.senchaLogo.animate({dynamic: true, to: {width: new_width}});
            navigationList.width = new_width;
            wrapContainer.updateLayout({isRoot: true});
            if (collapsing) {
                navigationList.on({
                    afterlayoutanimation: function () {
                        navigationList.setMicro(true);
                    },
                    single: true
                });
            }
        }
    },
    /**
     * 初始化用户信息
     * @param cmp
     * @param eOpts
     */
    initUserInfo: function(cmp, eOpts){
        var me = this;
        
        Ext.Ajax.request({
            url:just.getUrl('/sys/user/loadLoginInfo.action'),
            waitMsg :'...加载用户信息',
            success:function(response,opts){
                if(response.status == 200){
                    var jsonObj = Ext.JSON.decode(response.responseText);
                    var loginInfo = jsonObj.rows;
                    var ulists =Ext.ComponentQuery.query("tbtext[name='main_Viewport_tbtextjs']");
                    var ulist = ulists[ulists.length-1];
                    ulist.setText(loginInfo.ROLEDES +' : '+loginInfo.STAFFNAME)  ;
                    just.data.user.loginInfo  = loginInfo;
                }else{
                    me.initUserInfo(cmp,eOpts);
                }
            }
        });
    },
    /**
     * 初始化数据
     */
    onMainViewRender:function(cmp) {
        /*var me = this;
    	var listStore = Ext.create('Admin.store.NavigationTree');
        var me = this;
        var refs = me.getReferences(),
        navigationList = refs.navigationTreeList;
        navigationList.setStore(listStore);*/

        /*listStore.load({
            callback:function(records){
                 var node = listStore.findNode('id', 2);
                if (!window.location.hash) {
                    me.setCurrentView(2,node);
                }
            }
            });*/


    	



       // var str = this.getView().getViewModel().getData().ModuleTreeGrid;
        //var str = cmp.down('treelist').getViewModel();//.getData().ModuleTreeGrid;
       // node = str.findNode('id', 2);

       // var listViewModel =  this._listGrid.getViewModel(),
        //data = listViewModel.getData(),
        //listStore = data.UserGrid;
        /*if (!window.location.hash) {
            this.setCurrentView(2);
        }*/
    },
    
    /**
     * 跳转界面
     */
    setCurrentView: function(menuid,nodes) {


        var me = this,
        refs = me.getReferences();
        navigationList = refs.navigationTreeList,
        store = navigationList.getStore();
        if(this._treestore == undefined){
            this._treestore = Ext.create('Admin.store.NavigationTree');
            this._treestore.load({ 
            callback: function(records, options, success){
               
                 me.setCurrentView_back(menuid,nodes,me._treestore);                    
                
               
            } 
        });
        }
        else{
            me.setCurrentView_back(menuid,nodes,me._treestore);   
        }
    	/*//设置变量
        var listStore = Ext.create('Admin.store.NavigationTree');
        listStore.load({ 
            callback: function(records, options, success){
               
                 me.setCurrentView_back(menuid,nodes,listStore);                    
                
               
            } 
        });*/
        

        
        
        
        //node = store.findNode('id', menuid),
        /*view = node ? node.get('compoment') : null,
        mainCard = refs.mainCardPanel,
        mainLayout = mainCard.getLayout(),
        viewModel = me.getViewModel(),
        vmData = viewModel.getData(),
        existingItem = mainCard.child('component[id=' + menuid + ']'),
        lastView = vmData.currentView,
        newView;
        
        if(nodes){
           node = nodes; 
           view = node ? node.get('compoment') : null;

        }
        

        if (lastView && lastView.isWindow) {
            lastView.destroy();
        }
        //获取当前界面
        lastView = mainLayout.getActiveItem();
        if (!existingItem) {
            newView = Ext.create('Admin.view.' + (view ), {
                hideMode: 'offsets',
                menuid: menuid
            });
        }
        
        if (!newView || !newView.isWindow) {
            if (existingItem) {
                if (existingItem !== lastView) {
                    mainLayout.setActiveItem(existingItem);
                }
                newView = existingItem;
            }
            else {
                Ext.suspendLayouts();
                mainLayout.setActiveItem(mainCard.add(newView));
                Ext.resumeLayouts(true);
            }
        }
        
        navigationList.setSelection(node);
        if (newView.isFocusable(true)) {
            newView.focus();
        }
        vmData.currentView = newView;*/
    },
    
    setCurrentView_back:function(menuid,nodes,listStore){
            var me = this,
            refs = me.getReferences();

            var node = listStore.findNode('param', menuid),
                view = node ? node.get('compoment') : null,
                mainCard = refs.mainCardPanel,
                mainLayout = mainCard.getLayout(),
                viewModel = me.getViewModel(),
                vmData = viewModel.getData(),
                existingItem = mainCard.child('compoment[menuid=' + menuid + ']'),
                lastView = vmData.currentView,
                newView;
                
                if (lastView && lastView.isWindow) {
                    lastView.destroy();
                }
                lastView = mainLayout.getActiveItem();
                if (!existingItem) {
                    newView = Ext.create('Admin.view.' + (view /*|| 'pages.Error404Window'*/), {
                        hideMode: 'offsets',
                        menuid: menuid,
                        rawParams:node.raw,
                    });
                }
                //判断当前界面是不是刚执行的页面
                if (!newView || !newView.isWindow) {
                    if (existingItem) {
                        if (existingItem !== lastView) {
                            mainLayout.setActiveItem(existingItem);
                        }
                        newView = existingItem;
                    }
                    else {
                        Ext.suspendLayouts();
                        mainLayout.setActiveItem(mainCard.add(newView));
                        Ext.resumeLayouts(true);
                    }
                }
                //设置当前选择的界面
                navigationList.setSelection(node);
                if (newView.isFocusable(true)) {
                    newView.focus();
                }
                vmData.currentView = newView;     
        },
    onNavigationTreeSelectionChange: function (tree, node) {
      //  console.log(node);
        if (node && node.get('compoment')) {
            this.redirectTo(node.get("param"));
        }
    },
    onMainViewRender:function() {
        if (!window.location.hash) {

            this.redirectTo("widgets");
        }
    },
    onRouteChange:function(id){
        this.setCurrentView(id);
    },
    onloginClick:function(){
         if(Ext.MessageBox.confirm("系统提示","是否要退出系统",function(e){
            if(e == 'yes'){
                Ext.util.Cookies.clear("account");
                window.location.href='login.html';   
            }
        }));
    },
    onrepwdclick:function(){
         Ext.MessageBox.prompt('请输入', '请输入旧密码', this.showResultText, this);
     },
     onkanbclick:function(){
        window.location.href='echarts_web/kanb.html';   
     },
     showResultText: function(btn, text) {
        if(btn == 'ok'){

        var me =this;

        var params={};
            params.account = just.data.user.loginInfo.staff_id;
            params.password =  text;
            
            Ext.Ajax.request({

                submitEmptyText:true, 
                url : 'validatepwd.action',
                params : {
                        account: just.data.user.loginInfo.staff_id,
                        password: text
                     }, 
                success:function(response, opts){
                    var jsonObj = Ext.JSON.decode(response.responseText);
            
                    if( jsonObj.success)
                    {
                        Ext.MessageBox.prompt('请输入', '请输入新密码', me.showResultText1, this);
                    }
                    else
                    {
                        Ext.example.msg('系统提示', jsonObj.info );
                    }
                   
                    
                }
            })
        }
        //this.showToast(Ext.String.format('You clicked the {0} button and entered the text "{1}".', btn, text));
    },
    showResultText1: function(btn, text) {
        if(btn == 'ok'){
        var me =this;

        var params={};
            params.account = just.data.user.loginInfo.staff_id;
            params.password =  text;
            
            Ext.Ajax.request({

                submitEmptyText:true, 
                url : 'updatepwd.action',
                params : {
                        account: just.data.user.loginInfo.staff_id,
                        password: text
                     }, 
                success:function(response, opts){
                    var jsonObj = Ext.JSON.decode(response.responseText);
            
                    if( jsonObj.success)
                    {
                        Ext.example.msg('系统提示', jsonObj.info);
                    }
                    else
                    {
                        Ext.example.msg('系统提示', '修改失败');
                    }
                   
                    
                }
            })
        }
        //this.showToast(Ext.String.format('You clicked the {0} button and entered the text "{1}".', btn, text));
    },

});
