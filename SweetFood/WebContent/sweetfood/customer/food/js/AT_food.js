var AT_food={
		loadShopFoods:function(json){
			return jQuery.sendSyn("foodController","loadShopFoods",json,window);
		},
		addFoodToShopCar:function(json){
			return jQuery.sendSyn("foodController","addFoodToShopCar",json,window);
		},
		deleteFoodFromShopCar:function(json){
			return jQuery.sendSyn("foodController","deleteFoodFromShopCar",json,window);
		},
		loadShopCarFoods:function(json){
			return jQuery.sendSyn("foodController","loadShopCarFoods",json,window);
		},
		loadShopInfo:function(json){
			return jQuery.sendSyn("foodController","loadShopInfo",json,window);
		}
};