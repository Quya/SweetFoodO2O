var ET_businessman_info={
		init : function() {
			ET_businessman_info.register();
			ET_regist_login.logout();	
			$(function(){
				$("#city").citySelect({
				
					nodata:"none",
					required:false
				}); 
			});
		},
		register : function() {

			BS_businessman_info.loadInfo();
			$("#saveChange").bind("click",BS_businessman_info.saveInfo);
			
			} 
};