/**
 * 系统用户添加界面
 * Created by xiaozou on 16-01-04.
 */
Ext.define('Admin.view.email.UserAdd', {
    extend: 'Ext.form.Panel',
    alias: 'widget.userAdd',
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor'
    ],
    id:'userAdd',
    viewModel: {
        type: 'system_user_User'
    },
    reference: 'userAdd',
    controller: 'system_user_User',
    _flag:true,
    cls: 'email-compose',

    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,



   // //bodyPadding: 10,
   // scrollable:true,
    width: 355,

    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 100,
        msgTarget: 'side',
        msgTarget: 'under'
    },




    /*defaults: {
        labelWidth: 60,
        xtype: 'textfield',
        labelSeparator: ''
    },*/

    items: [

    {
        xtype: 'fieldset',
        title: '用户信息',

        defaultType: 'textfield',
        defaults: {
            anchor: '100%'
        },
        
        items: [
            { allowBlank:false, fieldLabel: '账号',name: 'staff_id', },
            { allowBlank:false, fieldLabel: '姓名', name: 'usrname', },
            { allowBlank:false, fieldLabel: '密码', name: 'passwd',   },
            { allowBlank:false, fieldLabel: '电话', name: 'mobilenum',   },
            { allowBlank:false, fieldLabel: '身份证', name: 'idcard',vtype:'idcard',msgTarget: 'under'},

        {
            name : 'roleid',
            xtype : 'combo',
            mode : 'local',
            fieldLabel : '角色名称',
            selectOnFocus : false,
            editable:true,
            emptyText : '请选择角色',
            valueField : 'roleid',
            displayField : 'roledes',
            bind : {
                store : '{RoleGrid}'
            }
        },
            { xtype:'numberfield', allowBlank:false,minValue: 0,maxValue: 125 , fieldLabel: '每日限制订单数', name: 'limitnum',value:0  },
            {
                xtype: 'radiogroup',
                name:'useflag',
                fieldLabel: '启用标识',
                cls: 'x-check-group-alt',
                items: [
                    {boxLabel: '启用', name: 'useflag', inputValue: 1,checked: true},
                    {boxLabel: '禁用', name: 'useflag', inputValue: 0},
              
                ]
            }
     
        ]
    }
        
    ],

    buttons: [{
        text: '  确  定  ',
        disabled: true,
        formBind: true,
        listeners: {
            click: 'onusersaveClick'
        }

    }]
});
