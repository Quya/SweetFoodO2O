var AT_businessman_diy={
		addDiy:function(json){
			return jQuery.sendSyn("diyController","addDiy",json,window);
		},
		loadDiy:function(json){
			return jQuery.sendSyn("diyController", "loadDiy", json, window);
		},
		deleteDiy:function(json){
			return jQuery.sendSyn("diyController", "deleteDiy", json, window);
		}
};