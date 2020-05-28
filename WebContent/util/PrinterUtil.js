/**
 * Created by CWJ on 14-3-24.
 */
Ext.define(just.createUtil('PrinterUtil'),{
    showPreview:function(config){
        config = config||{width:600};
        var win = Ext.widget('view_printView');
        win.setWidth(config.width);
        win.down('uxiframe').load(config.url);
    }
});