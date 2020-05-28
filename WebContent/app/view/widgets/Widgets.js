Ext.define('Admin.view.widgets.Widgets', {
    extend: 'Ext.container.Container',
    xtype: 'widgets',

    requires: [
        'Admin.view.widgets.WidgetA',
        'Admin.view.widgets.WidgetB',
        'Admin.view.widgets.WidgetC',
        'Admin.view.widgets.WidgetD',
        'Admin.view.widgets.WidgetE',
        'Admin.view.widgets.WidgetF',
        'Admin.model.DataXY',
        'Admin.model.MultiDataXY',
        'Admin.model.Subscription',
       
        
        'Ext.slider.Single',
        'Ext.form.field.Display',
        'Admin.view.widgets.WidgetController',
      
    ],

    viewModel: {
        type: 'dashboard'
    },
 
    controller: 'Widgets',
    layout: 'responsivecolumn',

    defaults: {
        xtype: 'container'
    },

    items: [
       
                {
                    xtype: 'widget-d',
                    responsiveCls: 'big-100 small-100'
                },
        {
            xtype: 'dashboardservicespanel',
            responsiveCls: 'big-100 small-100',
         
        },{
                      xtype: 'dashboardnetworkpanel',
            responsiveCls: 'big-100 small-100',
            
        }
    ]
});
