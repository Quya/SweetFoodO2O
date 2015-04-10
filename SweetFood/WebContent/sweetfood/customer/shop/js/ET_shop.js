var ET_shop={
	init:function(){
		ET_regist_login.logout();
		ET_shop.regist();
	},
	regist:function(){
		$("#search").bind("click",BS_shop.searchShops);
		$('[data-toggle="tooltip"]').tooltip();
	}
};