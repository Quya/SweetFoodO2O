var AT_shop={
		searchShops:function(json){
			return jQuery.sendSyn("customerController","searchShops",json,window);
		},
		getShopFoodList:function(json){
			return jQuery.sendSyn("foodController","getShopFoods",json,window);
		}
};