
var AT_regist_login={
		regist: function(json){
			return jQuery.sendSyn("userController","regist",json,window);
		},
		login: function(json){
			return jQuery.sendSyn("userController","login",json,window);
		},
		logout : function(json) {
			return jQuery.sendSyn("userController","logout",json,window);
		}
};