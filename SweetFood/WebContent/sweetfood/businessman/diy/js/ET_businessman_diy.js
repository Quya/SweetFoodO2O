var ET_businessman_diy={
		loadUserInfo:function(){
			var ret=AT_businessman_info.loadInfo({});
			$("#userid").val(ret.value.id);
			$("#userphoto").attr("src",ret.value.photoUrl);
			ET_regist_login.logout();	
		},
		initDiy:function(){
			ET_businessman_diy.registDiy();
			
		},
		registDiy:function(){
			ET_businessman_diy.loadUserInfo();
			BS_businessman_diy.loadDiy();
					
		},
		initPush:function(){
			ET_businessman_diy.registPush();
		},
		registPush:function(){
			ET_businessman_diy.loadUserInfo();
			$("#adddiy").bind("click",BS_businessman_diy.addDiy);
		}
		
};