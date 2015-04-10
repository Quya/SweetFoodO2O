var VM;
var BS_food={
		viewModel:function(foodlist,seatlist){
			self=this;
			self.foods=ko.observableArray(foodlist);
			
			self.addFoodToShopCar=function(food){
				var ret =AT_food.addFoodToShopCar({"id":food.id,"name":food.foodName,"price":food.price,"photo":food.photoUrl,"hostId":food.hostId});
			};
			self.seats=ko.observableArray(seatlist);
		},
//		loadShopFoods:function(){
//			var ret=AT_food.loadShopFoods("");
//			foodModel=new BS_food.foodViewModel(ret.value);
//			ko.applyBindings(foodModel);
//			$('[data-toggle="tooltip"]').tooltip();
////			$(function() {
////				$('.raty').raty({
////					readOnly:	true,
////					start: 0,
////					number: 5,
////					hints:	['糟糕透了', '不理想', "一般般", '很好', '棒极了'],
////				   	starOn:		'star-on.png',//设置图片，有默认的
////				   	starOff:	'star-off.png',
////				   	starHalf:	'star-half.png',
////				   	showCancel: true,
////				   	cancelPlace: 'left',//取消按钮的位置
////				   	cancelHint: '取消评级分数！',
////				   	onClick: function(score) {
////						$("#score").val(score);
////					}
////			   });
////			});
//			$('.raty').raty({
//				readOnly:	true,
//				  score: function() {
//				    return $(this).attr('data-score');
//				  }
//				});
//		},
		loadShopCarFoods:function(){
			var ret=AT_food.loadShopCarFoods("");
			if(ret.state==1){
				var foods=ret.value;
				$body=$('.J-shoping-body');
				$num=$('.J-shoping-num');
				$num.text(foods.length);
				if(foods.length>5){
					$body.attr({"style":"height:386px;overflow: auto!important;"});
				}
				for(var food in foods){
					
					$body.prepend('<div style="z-index:5"class="J-shoping-list" data-id="'+foods[food].id+'"><a href="#"title=""><img src="'+foods[food].photoUrl+'" width="30"height="30"/></a><p>'+foods[food].foodName+'</p><p><span class="cleft">价钱：</span><span class="cright"><em>'+foods[food].price+'</em>元</span></p><div class="baseBg J-shoping-close"></div></div>');
				}		
			}
		},
		loadShopInfoAndFoods:function(){
			var ret=AT_food.loadShopInfo("");
			var shop=ret.value;
			$("#shopName").text(shop.shopName);
			$("#userid").val(shop.businessmanId);
			var seatRet=AT_businessman_seat.loadSeat({"hostid":$("#userid").val()});
			var foodRet=AT_food.loadShopFoods("");
			VM=new BS_food.viewModel(foodRet.value,seatRet.value);
			ko.applyBindings(VM);
			$('[data-toggle="tooltip"]').tooltip();
			$('.raty').raty({
				readOnly:	true,
				  score: function() {
				    return $(this).attr('data-score');
				  }
				});
			
			
		}
		
};