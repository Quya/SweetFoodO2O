	 /*
      * Jquery—shopping   0.1
      * Copyright (c) 2013  Nicky Yan   个人网：站http://www.chinacoder.cn  QQ：525690001
      * Date: 2013-04-02
      * 使用Jquery—shopping可以很方便的实现加入购物车效果
	 */

;(function($){
	$.extend($.fn,{
		shoping:function(options){
			var self=this,
				scoller=false,
				$shop=$('.J-shoping'),
				$title=$('.J-shoping-title'),
				$body=$('.J-shoping-body'),
				$num=$('.J-shoping-num'),
				$close=$('.J-shoping-close');
			var S={
				init:function(){
					$title.bind('click',this.clickOnTitle);
					$close.live('click',this.removeList);
					$(self).data('click',true).live('click',this.addShoping);
					$(document).bind('click',S.slideBoxMini);
					$body.bind('click',this.clickOnBody);
				},
				clickOnBody:function(e){
					if(!$(e.target).hasClass('J-shoping-close')){
						e.stopPropagation(); //阻止冒泡	
					};
				},
				addShoping:function(e){
					e.stopPropagation();
					
					var $target=$(e.target),
						id=$target.attr('id'),
						name=$target.attr('name'),
						photoUrl=$target.attr('photoUrl'),
						price=$target.attr('price'),
						dis=$target.data('click'),
					    x = $target.offset().left + 30,
						y = $target.offset().top + 10,
						X = $shop.offset().left+$shop.width()/2-$target.width()/2+10,
						Y = $shop.offset().top;
					if(dis){
						if ($('#floatOrder').length <= 0) {
							$('body').append('<div id="floatOrder"><img src="'+photoUrl+'" width="30" height="30" /></div>');
						};
						var $obj=$('#floatOrder');
						if(!$obj.is(':animated')){
							$obj.css({'left': x,'top': y}).animate({'left': X,'top': Y-80},500,function() {
								$obj.stop(false, false).animate({'top': Y-20,'opacity':0},500,function(){
										$obj.fadeOut(300,function(){
										$obj.remove();	
									//	$target.data('click',false).addClass('dis-click');
										var l=$('.J-shoping-list').length,
											num=Number($num.text());
										if(l>=5&&scoller==false){
											
											scoller=true;
											$body.attr({"style":"height:386px;overflow: auto!important;"});
											
										};
										$body.prepend('<div style="z-index:5"class="J-shoping-list" data-id="'+id+'"><a href="#"title=""><img src="'+photoUrl+'" width="30"height="30"/></a><p>'+name+'</p><p><span class="cleft">价钱：</span><span class="cright"><em>'+price+'</em>元</span></p><div class="baseBg J-shoping-close"></div></div>');
										$num.text(num+1);
									});
								});
							});	
						};
					};
				},
				clickOnTitle:function(e){
					e.stopPropagation();
					var length=$('.J-shoping-list').length;	
					if(length>0){
						if(!$shop.hasClass('J-shoping-small')){
							$body.slideToggle();	
						}else{
							$('.J-shoping-mx').hide();			
							$('.J-shoping-px').show();
							$shop.animate({'width':289},100,function(){
								$shop.removeClass('J-shoping-small');
								$body.slideDown();
								$shop.remove("style");
								$shop.attr({"style":"width:289px"});
							});
						};
					};
				},
				slideBoxMini:function(){
					$('.J-shoping-px,.J-shoping-body').hide();
					$('.J-shoping-mx').show();
					$shop.animate({'width':119},100,function(){
						$shop.addClass('J-shoping-small');
					});	
				},
				removeList:function(e){
					e.stopPropagation();
					var $target=$(e.target),
						$parent=$target.parents('.J-shoping-list'),
						id=$parent.attr('data-id');
					$parent.addClass('J-shoping-list-remove').fadeOut(300,function(){
					//	$('#'+id).data('click',true).removeClass('dis-click');
						$parent.remove();
						AT_food.deleteFoodFromShopCar({"foodId":id});
						S.hideBody();
						if(options&&options.callback){
							options.callback($(self));	
						};	
					});	
				},
				hideBody:function(){
					var length=$('.J-shoping-list').length;	
					$num.text(length);
					if(scoller==true&&length<5){
						scoller=false;
						$body.attr({"style":"height:auto"});

					}
					if(length==0){
						$('.J-shoping-px,.J-shoping-body').hide();
						$('.J-shoping-mx').show();
						$shop.animate({'width':119},100,function(){
							$shop.addClass('J-shoping-small');
						});	
					};
				}
			};
			S.init(); 
		}
	});	
})(jQuery);

