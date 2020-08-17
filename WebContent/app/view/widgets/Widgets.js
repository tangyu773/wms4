Ext.define('Admin.view.widgets.Widgets', {
    extend: 'Ext.container.Container',
    xtype: 'widgets',

    defaults: {
        xtype: 'container'
    },

    items: [      
                {      
                    xtype: 'box', //或者xtype: 'component',      
                   // width: 100, //图片宽度      
                  //  height: 200, //图片高度      
                    autoEl: {      
                        tag: 'img',    //指定为img标签      
                        src: 'resources/images/img5.jpg'    //指定url路径      
                    }      
                }  
    ]
});
