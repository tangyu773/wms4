/**
 * 代理Proxy 来加载数据
 * Created by xiaozou on 15-10-20.
 */
Ext.define('Admin.store.system.contact.Contact', {
    extend: 'Ext.data.Store',
    alias: 'store.system_contact',


    pageSize: 1000,
    
    proxy: {
        api: {
            ADD: just.getUrl('/sys/user/contact_add_c.action'),
            DEL: just.getUrl('/sys/user/contact_del_d.action'),
        },
        type: 'ajax',
        url: just.getUrl('/sys/user/generalcontact_query_r.action'),
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