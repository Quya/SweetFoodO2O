
var BS_regist_login={
		regist1:function(){
		var gender=document.getElementsByName("gender1");
		var sex;
		if(gender.item(0).checked){
			sex="male";
		}else{
			sex="female";
		}
		var ret = AT_regist_login.regist({"username":$("#shopname")[0].value,"password":$("#password")[0].value,"email":$("#email1")[0].value,"phone":$("#phone1")[0].value,"power":$("#power1")[0].value,"sex":sex});
		if(ret.state==0){
			bootbox.alert(ret.value);
			$("#businessregist").modal({
				'backdrop' : 'static'
			});
			$("#email1")[0].focus();
			$("#email1")[0].select();
		}
		if(ret.state==1){
			location.assign(jQuery.GetUrl(window)+ "sweetfood/businessman/info/businessman_modify_info.html");
		}else if(ret.state==2){
			location.assign(jQuery.GetUrl(window)+ "sweetfood/customer/restaurant.html");
		}
		},
		regist2:function(){
			var gender=document.getElementsByName("gender2");
			var sex;
		
			if(gender.item(0).checked){
				sex="male";
			}else{
				sex="female";
			}
	
			var ret = AT_regist_login.regist({"username":$("#username")[0].value,"password":$("#pwd")[0].value,"email":$("#email2")[0].value,"phone":$("#phone2")[0].value,"power":$("#power2")[0].value,"sex":sex});
			if(ret.state==0){
				bootbox.alert(ret.value);
				$("#customerregist").modal({
					'backdrop' : 'static'
				});
				$("#email2")[0].focus();
				$("#email2")[0].select();
			}
//			if(ret.state==1){
//				location.assign(jQuery.GetUrl(window)+ "sweetfood/businessman/info/businessman_modify_info.html");
//			}else if(ret.state==2){
//				location.assign(jQuery.GetUrl(window)+ "sweetfood/customer/customer.html");
//			}
			},
		login: function(){
			var login=$("#loginname")[0].value;
			var password=$("#loginpassword")[0].value;
	
			
				var ret=AT_regist_login.login({"loginname":login,"password":password});
				if (ret.type=="0") {
					bootbox.alert(ret.value);
					$("#loginname")[0].focus;
					$("#loginpassword")[0].select();
					    
					return;
				}
				if (ret.type=="1") {
					bootbox.alert(ret.value);
					$("#loginpassword")[0].focus();
					$("#loginpassword")[0].select();
					return;
				}
				
				if(ret.state==1){//普通用户
					if(ret.value.power==1){
						location.assign(jQuery.GetUrl(window)+ "sweetfood/businessman/food/businessman_food.html");
					}else{
						location.assign(jQuery.GetUrl(window)+ "sweetfood/customer/shop/shop.html");
					}
					
				}
			
				
			
			//完成跳转 window.open();
	
		},
		logout:function(){
			var ret = AT_regist_login.logout("");
			if(ret.state==1){
				location.assign(jQuery.GetUrl(window)+"/sweetfood/registAndLogin/index.html");
			}
		},
		showLoginDlg:	function () {
			$("#loginForm").modal({
				'backdrop' : 'static'
			});
		},
		showCustomerRegist: function () {
			$("#customerregist").modal({
				'backdrop' : 'static'
			});
		},
		closeCustomerRegst :function () {
			$("#customerregist").modal('hide');
		},

		showBusinessRegst :function () {
			$("#businessregist").modal({
				'backdrop' : 'static'
			});
		},
		closeBusinessRegst :function() {
			$("#businessregist").modal('hide');
		}
};