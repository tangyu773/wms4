/**
 * Created by CWJ on 13-12-14.
 */
Ext.define(just.createUtil('Permission'),{
    initPermission : function(cmp, container){
        var tbar = container.down('toolbar');
        var actioncolum = container.down('actioncolumn');
        if(cmp.rawParams.c == 0){
            var button = tbar.down('button[action=add]');
            this.destroyTbarItem(tbar, button);
        }

        if(cmp.rawParams.r == 0){
            var button = tbar.down('button[action=refresh]');
            this.destroyTbarItem(tbar, button);
        }

        if(cmp.rawParams.u == 0){
            if(actioncolum != null){
            var items =  actioncolum.items;
            for(var i = 0; i < items.length; i++){
                if(items[i].action == 'edit'){
                    actioncolum.disableAction(i);
                    }

                    }
                }
             
            
           
        }
        
        if(cmp.rawParams.d == 0){
            if(actioncolum != null){
            var items =  actioncolum.items;
            for(var i = 0; i < items.length; i++){
                if(items[i].action == 'del'){
                    actioncolum.disableAction(i);
            }
        }
        }
    }
        
        this._initAdd(tbar,cmp.rawParams);
        this._initEdit(tbar,cmp.rawParams);
        this._initDelete(tbar,cmp.rawParams);
        this._initAudit(tbar,cmp.rawParams);
    },
    
    _initAdd:function(tbar,rawParams){
    	this._initPermissionByAction(tbar,rawParams,'add','c');
    },
    
    _initEdit:function(tbar,rawParams){
    	this._initPermissionByAction(tbar,rawParams,'edit','u');
    },
    
    _initDelete:function(tbar,rawParams){
    	this._initPermissionByAction(tbar,rawParams,'delete','d');
    },
    
    _initAudit:function(tbar,rawParams){

    	this._initPermissionByAction(tbar,rawParams,'audit','a');
    },
    
    _initPermissionByAction:function(tbar,rawParams,prefixAction, permissionType){
    	var tbItems = tbar.items.items;
    	var len = tbItems.length;
    	for(var i=0; i < tbItems.length; i++){
    		var item = tbItems[i];
    		if(item.xtype == "button"){
    			if(rawParams[permissionType] == 0){
    				if(Ext.String.startsWith(item.action, prefixAction, true)){
    					if(i > 0){
    						var preItem = tbItems[i-1];
    						if(preItem.xtype == 'tbseparator'){
    							i--;
    						}
    					}
    					this.destroyTbarItem(tbar,item);
    				}
    			}
    		}
    	}
    },

    getCmpIndex : function(container,cmp){
    	if(Ext.isEmpty(cmp)){
    		return -1;
    	}
        if(cmp.text == '新增模块')
        {
            var items = container.items.items;
        }
        else
        {
            var items = container.items.items[0].items.items;
        }
        
        if(!items){
            return -1;
        }

        for(var i = 0; i < items.length; i++){
            if(items[i].id == cmp.id){
                return i
            }
//          i++;
        }
        return -1;
    },

    /**
     * 删除不可用控件
     * @param tbar
     * @param item
     */
    destroyTbarItem : function(tbar, item){
        var idx = this.getCmpIndex(tbar,item);
        if(idx == -1){
            return;
        }
        if(idx == 0 ){
            if(tbar.items.items.length > 1){
                tbar.items.items[1].destroy();
            }
        }else{
            if(item.text == '新增模块'){
                if(tbar.items.items[idx - 1].xtype == "tbseparator"){
                    tbar.items.items[idx - 1].destroy();
                };
            }
            else{
                if(tbar.items.items[0].items.items[idx - 1].xtype == "tbseparator"){
                    tbar.items.items[idx - 1].destroy();
                };
            }
        	
            
        }
        item.destroy();

    },
    
    canUpdate : function(config){
    	var rawParams = this.getRawParams(config);
    	return rawParams.u == 1;
    },
    
    canDelete : function(config){
    	var rawParams = this.getRawParams(config);
    	return rawParams.d == 1;
    },
    
    canCreate : function(config){
    	var rawParams = this.getRawParams(config);
    	return rawParams.c == 1;
    },
    
    canRead : function(config){
    	var rawParams = this.getRawParams(config);
    	return rawParams.r == 1;
    },
    
    /**
     * 获取权限参数
     */
    getRawParams:function(config){
    	if(Ext.isEmpty(Ext.ClassManager.getClass(config))){
    		return config;
    	}else{
    		return config.rawParams;
    	}
    }
});