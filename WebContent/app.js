/*
 * 实习平台
 */
Ext.setGlyphFontFamily('FontAwesome');
/*Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'Ext.just' : 'resources/just',
		'Ext.ux' : 'resources/ux'
	}
});*/
Ext.application({
    name: 'Admin',

    extend: 'Admin.Application',

    requires: [
        'Admin.view.main.Viewport'
    ],
    
    mainView: 'Admin.view.main.Viewport'

});
