var ET_businessman_food={
	init:function(){
		ET_regist_login.logout();	
		ET_businessman_food.regist();
	},
	regist:function(){
		BS_businessman_food.loadInfo();
		BS_businessman_food.loadFood();
				
	}
};