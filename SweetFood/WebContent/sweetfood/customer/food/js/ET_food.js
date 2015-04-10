var ET_food={
		init:function(){
			ET_food.regist();
		},
		regist:function(){
			BS_food.loadShopInfoAndFoods();
		//	BS_food.loadShopFoods();
			BS_food.loadShopCarFoods();
		//	BS_businessman_seat.loadSeat();
			$(function(){
				   $('.Q-buy-btn').shoping(); //调用shoping函数
				});
		},
		
};
