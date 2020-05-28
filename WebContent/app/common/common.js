/**
 * Created by xiaozou on 16-03-21.
 */
//声明
Ext.ns("just");
Ext.ns("just.util");
Ext.ns("just.data");
Ext.ns("just.data.user");
just.data.listStore = null;

//用户登录信息
just.data.user.loginInfo = {};
/**
 * 获取当前项目目录
 */
just.rootPath = function () {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    return(prePath + postPath);
};
/**
 * 获取地址
 */
just.getUrl = function (path) {
    return just.rootPath() + path;
};

/**
 * 用户类型
 */
just.data.SYSUSER_TYPE = [
    {"value": undefined, "text": '--请选择--'},
    {"value": 0, "text": '运营用户'},
    {"value": 1, "text": '学校用户'}
];

/**
 * 用户类型
 */
just.ST_SYSUSER_TYPE = Ext.create('Ext.data.Store',{
	fields: ['value', 'text'],
	data: just.data.SYSUSER_TYPE
});
 just.data.SEAT_TYPE_p = [
    {
        'value': '1', text: '硬座'
    },
    {
        'value': '3', text: '硬卧'
    },
    {
        'value': '4', text: '软卧'
    },
    {
        'value': '5', text: '高级软卧'
    }
];
 just.data.SEAT_TYPE_g = [
    {
        'value': 'M', text: '一等座'
    },
    {
        'value': 'O', text: '二等座'
    },
    {
        'value': 'P', text: '特等座'
    }
];


/**
 * 当前状态
 */
 just.data.SEAT_TYPE = [
    {
        'value': '1', text: '硬座'
    },
    {
        'value': '2', text: '软座'
    },
    {
        'value': '3', text: '硬卧'
    },
    {
        'value': '4', text: '软卧'
    },
    {
        'value': 'M', text: '一等座'
    },
    {
        'value': 'O', text: '二等座'
    },
    {
        'value': '6', text: '高等软卧'
    },
    {
        'value': 'P', text: '特等座'
    },
    {
        'value': 'Q', text: '观光座'
    },
    {
        'value': 'S', text: '一等包厢'
    },
    {
        'value': 'W', text: '无座'
    },
    {
        'value': '5', text: '高级软卧'
    },
    {
        'value': '7', text: '一等软座'
    },
    {
        'value': '8', text: '二等软座'
    },
    {
        'value': '9', text: '商务座'
    }
];
 just.data.ID_TYPE = [
    {
        'value': '1', text: '二代身份证'
    },
    {
        'value': '2', text: '港澳通行证'
    },
    {
        'value': '3', text: '台湾通行证'
    },
    {
        'value': '4', text: '护照'
    }
];
just.ST_ID_TYPE = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.ID_TYPE
});
just.ST_SEAT_TYPE = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.SEAT_TYPE
});

just.ST_SEAT_TYPE_p = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.SEAT_TYPE_p
});

just.ST_SEAT_TYPE_g = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.SEAT_TYPE_g
});

just.data.CUR_STATUS = [
    {"value": undefined, "text": '--请选择--'},
    {"value": 1, "text": '启用'},
    {"value": 0, "text": '禁用'}
];
just.data.CUR_STATUS1 = [
    {"value": 1, "text": '启用'},
    {"value": 0, "text": '禁用'}
];
just.data.CUR_STATUS2 = [
    {"value": 0, "text": '全部'},
    {"value": 1, "text": '订票成功'},
    {"value": 2, "text": '订票不成功'}
];
/**
 * 当前状态
 */
just.ST_CUR_STATUS = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.CUR_STATUS
});
just.ST_CUR_STATUS1 = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.CUR_STATUS1
});
just.ST_CUR_STATUS2 = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.CUR_STATUS2
});

/**
 * 模块类型
 */
just.data.MENU_TYPE = [
    {"value": 0, "text": '系统模块'},
    {"value": 1, "text": '学校模块'}
];

/**
 * 模块类型
 */
just.ST_MENU_TYPE = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.MENU_TYPE
});
/**
 * 模块名
 * @type {{value: string, text: string}[]}
 */
just.data.MODULE = [
    {"value": "sys", "text": "SYS"},
    {"value": "schmgr", "text": "SCHMGR"},
    {"value": "report", "text": "REPORT"},
    {"value": "base", "text": "BASE"},
    {"value": "sp", "text": "SP"},
    {"value": "pt", "text": "PT"},
    {"value": "st", "text": "ST"},
    {"value": "zx", "text": "ZX"},
    {"value": "re", "text": "RE"}
];

/**
 * 模块名
 * @type {Ext.data.Store}
 */
just.ST_MODULE = Ext.create('Ext.data.Store', {
    fields: ['value', 'text'],
    data: just.data.MODULE
});
/**
 * 显示等待对话框
 * @param msg
 */
just.showWaitingDlg = function (msg) {
    msg = msg == null || msg == '' ? '正在保存数据，请稍候...' : msg;
    Ext.MessageBox.show({
        msg: msg,
        wait: true,
        waitConfig: {
            interval: 200
        }
    });
};

just.util.futySearch = function(e) {  
    var combo = e.combo;   
    combo.store.clearFilter();   
    if(!e.forceAll){     
        var value = Ext.util.Format.lowercase(e.query);     

        combo.store.filterBy(function(record,id){     
            var text = record.get('station_name')+record.get('staiton_pycode'); 
            return (text.indexOf(value)!=-1);     
        });
        combo.expand();    

        return false;     
    }  
};
just.util.idcard = function(idcard){
                var Errors=new Array(  
                        "验证通过!",  
                        "身份证号码位数不对!",  
                        "身份证号码出生日期超出范围或含有非法字符!",  
                        "身份证号码校验错误!",  
                        "身份证地区非法!"  
                        );  
                        var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}   
                          
                        var idcard,Y,JYM;  
                        var S,M;  
                        var idcard_array = new Array();  
                        idcard_array = idcard.split("");  
                        //地区检验  
                        if(area[parseInt(idcard.substr(0,2))]==null)   
                            return false;//return Errors[4];  
                        //身份号码位数及格式检验  
                        switch(idcard.length)  
                        {  
                            case 15:  
                                if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 ))  
                                {  
                                    //测试出生日期的合法性  
                                    ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;  
                                }  
                                else   
                                {  
                                    //测试出生日期的合法性  
                                    ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;  
                                }  
                                if(ereg.test(idcard))   
                                    return true;//return Errors[0];  
                                else  
                                    return false;//return Errors[2];  
                            break;  
                            case 18:  
                                //18位身份号码检测  
                                //出生日期的合法性检查   
                                //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))  
                                //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))  
                                if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 ))  
                                {  
                                    //闰年出生日期的合法性正则表达式  
                                    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;  
                                }   
                                else   
                                {  
                                    //平年出生日期的合法性正则表达式  
                                    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;  
                                }  
                                if(ereg.test(idcard))  
                                {  
                                    //测试出生日期的合法性  
                                    //计算校验位  
                                    S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7  
                                    + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9  
                                    + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10  
                                    + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5  
                                    + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8  
                                    + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4  
                                    + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2  
                                    + parseInt(idcard_array[7]) * 1   
                                    + parseInt(idcard_array[8]) * 6  
                                    + parseInt(idcard_array[9]) * 3 ;  
                                    Y = S % 11;  
                                    M = "F";  
                                    JYM = "10X98765432";  
                                    M = JYM.substr(Y,1);//判断校验位  
                                    if(M == idcard_array[17])   
                                        return true;//return Errors[0]; //检测ID的校验位  
                                    else   
                                        return false;//return Errors[3];  
                                }  
                                else   
                                    return false;//return Errors[2];  
                            break;  
                            default:  
                                return false;//return Errors[1];  
                            break;  
                        }
            };

/**
 * 格式化文本样式
 * @param {} v
 * @param {} dd
 * @param {} css
 * @return {}
 */
just.util.valueTransText = function(v,dd,css){
    for(var i = 0 ; i < dd.length; i++){
        var item = dd[i];
        if(v == item.value){
            if(css){
                var cssName = css + '_' + item.value;
                return '<span class="'+cssName+'">' + item.text + '<span>';
            }
            return item.text;
        }
    }
    return v;
};


just.createUtil = function (utilName, parentPackage) {
    if (!(parentPackage == undefined || parentPackage == "")) {
        return just.appConfigs.name + just.UTIL + parentPackage + "." + utilName;
    }
    return 'admin' + '.util.' + utilName;
};

/**
 * 隐藏等待对话框并提示信息
 *
 * @param    msg            消息内容(默认为:保存成功)
 * @param    isShowAlert    是否显示alert(默认为false)
 */
just.hideWaitingDlg = function (msg, isShowAlert) {
    Ext.MessageBox.hide();
    isShowAlert = isShowAlert == null || isShowAlert == '' ? false : isShowAlert;
    if (isShowAlert == true) {
        msg = msg == null || msg == '' ? '保存成功' : msg;
        Ext.MessageBox.alert('系统提示', msg);
    }
};

Ext.onReady(function () {
    /**
     * 初始化提示组件,为对象添加toptip功能,这样提示信息才可用
     * */
    Ext.form.Field.prototype.msgTarget = 'side';//Ext表单提示方式：msgTarget:有4中方式：qtip,title,under,side
    /**
     * ajax异常处理
     * */
    Ext.Ajax.on('requestexception', function (conn, response, options, eOpts) {
        var status = response.status;
        var url = options.url;
        url = url.replace(just.rootPath(),'');
        var json = undefined;
		try{
			json = Ext.decode(response.responseText);
		}catch(e){
			return;
		}
		if(response.timedout == true){
			Ext.MessageBox.alert('系统提示', "操作超时");
			return;
		}
		//403表示操作未授权
        if (status == 403) {
			Ext.MessageBox.alert('系统提示', "系统未登录，请重新登录...");
            window.location.href= just.getUrl('/login.html');
            return;
        }else if (status == 303 || status == 309 || status == 400 || status == 404 || status == 407 || status == 500 ) {
            Ext.MessageBox.alert('系统提示', json.info);
        }else{
            Ext.MessageBox.alert('系统提示', json.info);
        }
    }, this);
});