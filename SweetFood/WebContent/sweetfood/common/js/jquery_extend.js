/**
 * 李伟编写 2014-8-17
 * 扩展了jquery部分功能
 * 同步请求
 */
jQuery.sendSyn= function(action,method,json,win){
		data : null,
		jQuery.ajax({
            type: "post",
            async:false,
            url: jQuery.GetUrl(win == null?window:win)+ action + "/" + method + ".do",
            data: {"params" : JSON.stringify(json)}, 
            contentType: "application/x-www-form-urlencoded",
            dataType: "text",
            cache: false,
            success: function (data) {
                //返回的数据用data.d获取内容
                jQuery.sendSyn.data = parseJSON(data);
            },
            error: function (err) {
                alert("您发送的请求没有相应，请与系统管理员联系");
                return err;
            }
        });
		return jQuery.sendSyn.data;
}

jQuery.sendSynForJosnString= function(action,method,json,win){
	data : null,
	jQuery.ajax({
        type: "post",
        async:false,
        url: jQuery.GetUrl(win == null?window:win)+ action + "/" + method + ".do",
        data: {"params" : json}, 
        contentType: "application/x-www-form-urlencoded",
        dataType: "text",
        cache: false,
        success: function (data) {
            //返回的数据用data.d获取内容
            jQuery.sendSyn.data = parseJSON(data);
        },
        error: function (err) {
            alert("您发送的请求没有相应，请与系统管理员联系");
            return err;
        }
    });
	return jQuery.sendSyn.data;
}

jQuery.sendAsyn= function(action,method,json,callback,win){
	data : null,
	jQuery.ajax({
        type: "post",
        async:true,
        url: jQuery.GetUrl(win == null?window:win)+ action + "/" + method + ".do",
        data: {"params" : JSON.stringify(json)}, 
        contentType: "application/x-www-form-urlencoded",
        dataType: "text",
        cache: false,
        success: function (data) {
            //返回的数据用data.d获取内容
            jQuery.sendAsyn.data = parseJSON(data);
            callback.call(this,jQuery.sendAsyn.data);
        },
        error: function (err) {
            alert("您发送的请求没有相应，请与系统管理员联系");
            return err;
        }
    });
}


jQuery.sendImgSyn= function(action,method,params,callback,win,fileElID){
	$.ajaxFileUpload({
		type: "post",
        async:false,
		//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
		url: jQuery.GetUrl(win == null?window:win)+ action + "/" + method + ".do"+params,
		secureuri:false,                       //是否启用安全提交,默认为false 
		fileElementId:fileElID,           //文件选择框的id属性
		dataType:'text',                       //服务器返回的格式,可以是json或xml等
		
		success: function (data, status) {
            //返回的数据用data.d获取内容
			jQuery.sendAsyn.data = parseJSON(data);
            callback.call(this,jQuery.sendAsyn.data);
        },
        error: function (data, status, e) {
            alert("您发送的请求没有相应，请与系统管理员联系");
            return e;
        }
		
	});
}



jQuery.GetUrl = function(win, layer) {
	var target = "";
	try {
		if(win) {
			target = win.top.Agency.Target.value;
		} else {
			target = top.Agency.Target.value;
		}
	} catch (e) {
		target = "web";
	}
	if(target && target == "local") {
		if(layer) {
			var strPath = "";
			for(var i=0; i<layer; i++) {
				strPath += "../";
			}
			return strPath;
		} else {
			return "../../";
		}
	} else {
		var hrefStr= top.location.href;
		var index = hrefStr.indexOf("\/", 8);
		index = hrefStr.indexOf("\/", index+1);
		return hrefStr.substring(0, index+1);//return "http://localhost:8080/spring/";
	}
}
function parseJSON(string) {
	try {
		return eval('(' + string + ')');
	} catch (e) {
		throw new SyntaxError("解析JSON字符串;出错");
	}
}