Ext.define('Admin.store.Order', {
    extend: 'Ext.data.Store',
    alias: 'store.order_order',

/*    model: 'Admin.model.system.user.User',*/

    pageSize: 10000,
    
    proxy: {
        api: {
            REV: just.getUrl('/order/revlist.action'),
            DETAILIST: just.getUrl('/order/detailist.action'),
            CONTACT: just.getUrl('/sys/user/generalcontact_query_r.action'),
            ADDORDER: just.getUrl('/sys/user/order_add_c.action')
        },
        type: 'ajax',
        url: just.getUrl('/order/orderlist.action'),
        actionMethods: {
            create: "POST", read: "POST", update: "POST", destroy: "POST"
        },
        reader: {
            type: 'json',
            rootProperty: 'rows',
            totalProperty: 'total'
        },
        writer: {
            type: 'json'
        }
    }
});