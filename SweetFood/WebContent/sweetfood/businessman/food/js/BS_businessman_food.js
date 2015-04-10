var foodModel;
var BS_businessman_food={
		foodViewModel : function(json) {
			self = this;
			self.foods = ko.observableArray(json);
			self.openEditDlg = function(food) {
				$("#foodname").val(food.foodName);
				$("#price").val(food.price);
				$("#description").val(food.description);
				$("#topic").text("修改美食");
				$("#imgShow").attr("src",food.photoUrl);
				$("#foodid").val(food.id);
				var radio=document.getElementsByName("status");
				if(food.state=='on'){
					radio[0].checked=true;
				}else{
					radio[1].checked=true;
				}
				BS_businessman_food.showUpdateDlg();
			};
			self.saleStop = function(food) {
				try {
					var ret = AT_businessman_food.saleStop({"foodid" : food.id});
					if(ret.state==1){
						bootbox.confirm(ret.value,function(choice){
							var ret=AT_businessman_food.loadFood({"hostid":$("#userid")[0].value});
							foodModel.foods(ret.value);
						});
					}
				} catch (e) {
					bootbox.alert(e.message);
				}
			};
			
			self.saleStart = function(food) {
				try {
					var ret = AT_businessman_food.saleStart({"foodid" : food.id});
					if(ret.state==1){
						bootbox.confirm(ret.value,function(choice){
							var ret=AT_businessman_food.loadFood({"hostid":$("#userid")[0].value});
							foodModel.foods(ret.value);
						});
					}
				} catch (e) {
					bootbox.alert(e.message);
				}
			};
			
			self.deleteFood = function(food) {
				bootbox.confirm("确实删除这份美食吗？", function(choice) {
					if (choice) {
						try {	
							var ret = AT_businessman_food.deleteFood({"foodid" : food.id});
							if(ret.state==1){
								bootbox.confirm(ret.value,function(choice){
									var ret=AT_businessman_food.loadFood({"hostid":$("#userid")[0].value});
									foodModel.foods(ret.value);
								});
							}
						} catch (e) {
							bootbox.alert(e.message);
						}
					}
				});
			};
		},
		
	
		loadInfo: function(){
			$('.plus').click(function (e) {
			    e.preventDefault();
			    var radio=document.getElementById("status0");
				radio.checked=true;
			    $("#foodname").val("");
				$("#price").val("");
				$("#description").val("");
				$("#imgShow").attr("src","#");
				$("#operatefood").val("保存添加");
				$("#operatefood").unbind();
				$("#operatefood").bind("click",BS_businessman_food.addFood);	
			    $('#topic').text('添加美食');
			    $('#myModal').modal('show');
			 
			});
			var ret=AT_businessman_info.loadInfo({});
			$("#userid").val(ret.value.id);
			$("#userphoto").attr("src",ret.value.photoUrl);
		},
		loadFood:function(){
			var ret=AT_businessman_food.loadFood({"hostid":$("#userid")[0].value});
			foodModel = new BS_businessman_food.foodViewModel(ret.value);
			ko.applyBindings(foodModel);
		},
		addFood: function(){
			var status=document.getElementsByName("status");
			var state;
			var imgName=$("#photo")[0].value;
			if(status.item(0).checked){
				state="on";
			}else{
				state="off";
			}
			if(imgName==""||(imgName!=""&&(imgName.indexOf(".png")>-1||imgName.indexOf(".jpg")>-1||imgName.indexOf(".gif")>-1||imgName.indexOf(".jpeg")>-1))){
				AT_businessman_food.addFood("?foodname="+$("#foodname")[0].value+"&price="+$("#price")[0].value+"&state="+state+"&description="+$("#description")[0].value+"&hostid="+$("#userid")[0].value);
			}			
		},
		updateFood: function(){
			  var status=document.getElementsByName("status");
			   var state;
			   if(status.item(0).checked){
				   state="on";
				}else{
					state="off";
				}

			AT_businessman_food.updateFood("?foodid="+$("#foodid")[0].value+"&foodname="+$("#foodname")[0].value+"&price="+$("#price")[0].value+"&state="+state+"&description="+$("#description")[0].value+"&hostid="+$("#userid")[0].value);
			
		},
		foodCallBack: function(ret){
			if(ret.state==1){
				bootbox.confirm(ret.value,function(choice) {
					BS_businessman_food.closeFoodDlg();
					var ret=AT_businessman_food.loadFood({"hostid":$("#userid")[0].value});
					foodModel.foods(ret.value);
					}
				);
			}
		},
		showUpdateDlg :function () {
			$("#operatefood").val("保存修改");
			$("#operatefood").unbind();
			new uploadPreview({ UpBtn: "photo", DivShow: "photoDiv", ImgShow: "imgShow" });
			$("#operatefood").bind("click",BS_businessman_food.updateFood);
			$("#myModal").modal({
				'backdrop' : 'static'
			});
		},	
		closeFoodDlg :function() {
			new uploadPreview({ UpBtn: "photo", DivShow: "photoDiv", ImgShow: "imgShow" });
			  var radio=document.getElementById("status0");
			radio.checked=true;
		    $("#foodname").val("");
			$("#price").val("");
			$("#description").val("");
			$("#imgShow").attr("src","#");
			$("#operatefood").val("保存添加");
			$("#operatefood").unbind();
			$("#operatefood").bind("click",BS_businessman_food.addFood);	
		    $('#topic').text('添加美食');
			$("#myModal").modal('hide');
		}
};