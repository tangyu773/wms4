Ext.define('Admin.view.main.Viewport', {
    extend: 'Ext.container.Viewport',
	alias: 'widget.main_Viewport',
    xtype: 'mainviewport',
    id:'main_Viewport',
    controller: 'mainviewport',
    viewModel: {
        type: 'mainviewport'
    },

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    listeners: {
        render: 'onMainViewRender'
    },

    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar toolbar-btn-shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo">&nbsp;&nbsp;&nbsp;  <i class=" icon-2x icon-print"></i> &nbsp; 4#仓wms</div>',
                    width: 200
                },
                {
                    margin: '0 0 0 8',
                    cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-navicon',
                    handler: 'onToggleNavigationSize'
                },
                {
                    xtype: 'tbspacer',
                    flex: 1
                },
                {
                    cls: 'delete-focus-bg',
                    iconCls: 'x-fa fa-key',
                    tooltip: '看板',
                    text:'看板',
                    handler:'onkanbclick'
                },
                {
                	cls: 'delete-focus-bg',
                	iconCls: 'x-fa fa-key',
                	tooltip: '修改密码',
                    text:'修改密码',
                    handler:'onrepwdclick'
                },{
                    cls: 'delete-focus-bg',
                    iconCls: 'x-fa fa-power-off',
                    text:'登出',
                    tooltip: '退出',
                    handler: 'onloginClick'
                }/*,
                {
                	cls: 'delete-focus-bg',
                	iconCls: 'x-fa fa-user',
                	tooltip: '修改用户信息'
                }*/,
                {
                    xtype: 'tbtext',
                    text: '角色：用户名',
                    name:'main_Viewport_tbtextjs',
                    cls: 'top-user-name'
                },
                {
                    xtype: 'image',
                    cls: 'header-right-profile-image',
                    height: 35,
                    width: 35,
                    alt:'当前用户图片',
                    src: 'resources/images/user-profile/face-1.jpg'
                }
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'navigation',
                    bind : {
                        store : '{ModuleTreeGrid}'
                    },
                    //store: 'NavigationTree',
                    width: 200,
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
});
