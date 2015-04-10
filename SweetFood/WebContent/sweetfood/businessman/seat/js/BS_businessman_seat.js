var seatModel;
var BS_businessman_seat={
		
		seatViewModel : function(json) {
			self = this;
			self.seatlines = ko.observableArray(json);
			self.openUpdateSeat=function(seat){
				
					$("#seatname").val(seat.seatName);
					$("#seatnum").val(seat.seatNum);
					$("#seatid").val(seat.seatId);
					$("#topic").text("修改餐桌");
					$("#operateseat").val("保存修改");
					var radio=document.getElementsByName("status");
					if(seat.state=='free'){
						radio[0].checked=true;
					}else{
						radio[1].checked=true;
					}
					BS_businessman_seat.showUpdateSeatDlg();
				
				
			};
			self.deleteSeat=function(seat){
				if(seat.state=='busy'){
					bootbox.alert("无法删除，餐桌正在使用");
				}else{
					bootbox.confirm("你确定删除此餐桌",function(choice){
						if(choice){
							var ret=AT_businessman_seat.deleteSeat({"seatid":seat.seatId});
							if(ret.state==1){
								bootbox.confirm(ret.value,function(choice){
									BS_businessman_seat.reloadSeat();
								});								
							}
						}
					});
					
				}
			};
			self.onSeat=function(seat){
				if(seat.state=='busy'){
					bootbox.alert("餐桌处于忙碌状态，无法就座");
				}else{
					var ret=AT_businessman_seat.onSeat({"seatid":seat.seatId});
					if(ret.state==1){
						bootbox.confirm(ret.value,function(choice){
							BS_businessman_seat.reloadSeat();
						});
						
					}
				}
			};
			self.offSeat=function(seat){
				if(seat.state=='free'){
					bootbox.alert("餐桌处于空闲状态，无需让座");
				}else{
					var ret=AT_businessman_seat.offSeat({"seatid":seat.seatId});
					if(ret.state==1){
						bootbox.confirm(ret.value,function(choice){
							BS_businessman_seat.reloadSeat();
						});
						
					}
				}
			};
		},
		addSeat:function(){
			var status=document.getElementsByName("status");
			var state;
			var hostid=$("#userid")[0].value;
			if(status.item(0).checked){
				state="free";
			}else{
				state="busy";
			}
			var ret=AT_businessman_seat.addSeat({"hostid":hostid,"seatname":$("#seatname")[0].value,"seatnum":$("#seatnum")[0].value,"state":state});
			if(ret.state==1){
				BS_businessman_seat.closeSeatDlg();
				BS_businessman_seat.reloadSeat();
			}
		},
		reloadSeat:function(){
			var ret=AT_businessman_seat.loadSeat({"hostid":$("#userid")[0].value});
			seatModel.seatlines(ret.value);
			$('[data-toggle="tooltip"]').tooltip();
		},
		loadSeat:function(){
			var ret=AT_businessman_seat.loadSeat({"hostid":$("#userid")[0].value});
			seatModel = new BS_businessman_seat.seatViewModel(ret.value);
			ko.applyBindings(seatModel);
		},
		updateSeat:function(){
			var status=document.getElementsByName("status");
			   var state;
			   if(status.item(0).checked){
				   state="free";
				}else{
					state="busy";
				}
			var ret=AT_businessman_seat.updateSeat({"seatid":$("#seatid")[0].value,"seatname":$("#seatname")[0].value,"seatnum":$("#seatnum")[0].value,"state":state});
				if(ret.state==1){
					bootbox.confirm(ret.value,function(choice){
						BS_businessman_seat.closeSeatDlg();
						BS_businessman_seat.reloadSeat();
					});
			}
		},
		showUpdateSeatDlg:function(){
			$("#operateseat").unbind();
			$("#operateseat").bind("click",BS_businessman_seat.updateSeat);
			$("#myModal").modal({
				'backdrop' : 'static'
			});
		},
		resetModalToAdd:function(){
			var radio=document.getElementById("status0");
			radio.checked=true;
		    $("#seatname").val("");
			$("#seatnum").val("");
			$("#operateseat").val("保存添加");
			$("#operateseat").unbind();
			$("#operateseat").bind("click",BS_businessman_seat.addSeat);	
		    $('#topic').text('添加餐桌');
		},
		closeSeatDlg :function() {
				$("#myModal").modal('hide');
		},
	
};