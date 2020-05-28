/**
 * Created by CWJ on 14-1-4.
 */
Ext.define(just.createUtil('VTypes'),
    (function(){
        var ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
            mobile=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}1[3-8][0-9]{9}$)/,
            phone=/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,
            positiveNumber=/^[0-9]*[1-9][0-9]*$/;

        Ext.apply(Ext.form.field.VTypes, {
            'ip': function (val, field) {
                try {
                    return ip.test(val);
                }
                catch (e) {
                    return false;
                }
            },
            'ipText': '请输入正确的IP地址',

            'port':function(val,field){
                if(!Ext.isEmpty(val)){
                    if(parseInt(val) > 0 && parseInt(val) < 65536){
                        return true;
                    }else{
                        return false;
                    }
                }
            },
            'portText': '请输入正确的端口(1-65535)',

            'mobile':function(val, field){
                return mobile.test(val);
            },
            'mobileText':'请输入正确的手机号码，如（13912345678）',

            'phone':function(val){
                return phone.test(val);
            },
            'phoneText':'请输入正确的电话号码,如:010-81234567',

            'phoneMobile':function(val){
                return phone.test(val) || mobile.test(val);
            },
            'phoneMobileText':'请输入正确的电话号码或手机号码,如(010-81234567)或(13912345678)',

            'positiveNumber': function(val){
                return positiveNumber.test(val);
            },
            'positiveNumberText':'请输入正确的正整数,如(100)',
            
            'test': function(val){
                return positiveNumber.test(val);
            },
            'testText':'请输入正确的正整数,如(100)',
            'idcard' : function(idcard){
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
            },
            'idcardText':'身份证不正确',
            'minCompare':function(val, field){
            	var form = field.up('form');
				var max = form.down(Ext.String.format('{0}[name={1}]',field.xtype,field.compareTo));
//				if(max.allowBlank){
//					return true;
//				}
				var maxVal = "";
				if(max.xtype == 'datetimefield'|| max.xtype == 'timefield' || max.xtype == 'datefield'){
					maxVal = max.getRawValue();
				}else{
					maxVal = max.getValue();
				}
				if(Ext.isEmpty(maxVal) && max.allowBlank){
					max.clearInvalid();
					return true;
				}
				if(val > maxVal){
//					max.isValid();
					return false;
				}
				max.clearInvalid();
//				max.isValid();
				return true;
            },
            'minCompareText':'',
            'maxCompare':function(val, field){
            	var form = field.up('form');
				var min = form.down(Ext.String.format('{0}[name={1}]',field.xtype,field.compareTo));
//				if(min.allowBlank){
//					return true;
//				}
				var minVal = "";
				if(min.xtype == 'datetimefield' || min.xtype == 'timefield' || min.xtype == 'datefield'){
					minVal = min.getRawValue();
				}else{
					minVal = min.getValue();
				}
				
				if(Ext.isEmpty(minVal) && min.allowBlank){
					min.clearInvalid();
					return true;
				}
				if(val < minVal){
					return false;
				}
				min.clearInvalid();
				return true;
            },
            'maxCompareText':''
        });
        return {singleton: true};
    })()
);