var AT_businessman_food={
		loadFood:function(json){
			return jQuery.sendSyn("foodController","loadFood",json,window);
		},
		addFood:function(json){
			return jQuery.sendImgSyn("foodController","addFood",json,BS_businessman_food.foodCallBack,window,"photo");
		},
		updateFood:function(json){
			return jQuery.sendImgSyn("foodController","updateFood",json,BS_businessman_food.foodCallBack,window,"photo");
		},
		saleStop:function(json){
			return jQuery.sendSyn("foodController","saleStop",json,window);
		},
		saleStart:function(json){
			return jQuery.sendSyn("foodController","saleStart",json,window);
		},
		deleteFood:function(json){
			return jQuery.sendSyn("foodController","deleteFood",json,window);
		}
};