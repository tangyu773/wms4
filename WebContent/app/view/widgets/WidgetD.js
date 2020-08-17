Ext.define('Admin.view.widgets.WidgetD', {
    extend: 'Ext.panel.Panel',
    xtype: 'widget-d',

    cls:'admin-widget shadow-panel',

    items: [
       /* {
            xtype: 'image',
            cls: 'widget-top-container-first-img',
            height: 66,
            width: 66,
            alt: 'profile-image',
            src: 'resources/images/user-profile/face-1.jpg'
        },*/
        {
            xtype: 'component',
            cls: 'widget-top-first-fourth-container postion-class',
            height: 200
        },
        {
            xtype: 'container',
            cls: 'widget-bottom-first-container postion-class',
            height: 100,
            padding: '0 0 0 0',
            layout: {
                type: 'vbox',
                align: 'center',
                pack: 'center'
            },
            items: [
                {
                    xtype: 'toolbar',
                    flex: 1,
                    cls: 'widget-follower-toolbar',
                    width: '100%',
                    margin: '0 0 0 0',
                    defaults: {
                        xtype: 'displayfield',
                        flex: 1,
                        labelAlign: 'top'
                    }
                }
            ]
        }

    ]
    ,
     listeners: {
            afterrender: 'ondAfterRender',
           
        }
});
