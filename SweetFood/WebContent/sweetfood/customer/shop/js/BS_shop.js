var shopModel;
var BS_shop={

		shopViewModel : function(json) {
			self = this;
			self.shops = ko.observableArray(json);
			self.enterShop=function(shop){
				alert(shop.shopName);
				var ret=AT_shop.getShopFoodList({"businessmanId":shop.businessmanId,"shopName":shop.shopName,"shopPhoto":shop.shopPhoto,"address":shop.address,"shopPhone":shop.shopPhone,"indroduce":shop.indroduce});
				if(ret.state==1){
					location.assign(jQuery.GetUrl(window)+ "sweetfood/customer/food/sweetfood.html");
				}
			};
			},
		searchShops:function(){
			var  selectProv=document.getElementById("prov");
			   var  selectCity=document.getElementById("city0");
			   var  selectDist=document.getElementById("dist");
			   var provIndex=selectProv.selectedIndex;
			   var cityIndex=selectCity.selectedIndex;
			   var distIndex=selectDist.selectedIndex;
			   var dist="";
			   var prov=selectProv.options[provIndex].text;
			   var city=selectCity.options[cityIndex].text;
			   if(distIndex>0){
				   dist=selectDist.options[distIndex].text;
			   }
			var ret=AT_shop.searchShops({"prov":prov,"city":city,"dist":dist});
			shopModel = new BS_shop.shopViewModel(ret.value);
			ko.applyBindings(shopModel);
			$('[data-toggle="tooltip"]').tooltip();

		}
};