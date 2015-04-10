var ET_businessman_seat={
		init:function(){
			ET_regist_login.logout();	
			ET_businessman_seat.regist();
			
		},
		regist:function(){
			var ret=AT_businessman_info.loadInfo({});
			$("#userid").val(ret.value.id);
			$("#userphoto").attr("src",ret.value.photoUrl);
			$('.plus').click(function (e) {
			    e.preventDefault();
			    BS_businessman_seat.resetModalToAdd();
			    $('#myModal').modal('show'); 
			});
			$("#operateseat").bind("click",BS_businessman_seat.addSeat);
			$("#closeseat").bind("click",BS_businessman_seat.closeSeatDlg);
			BS_businessman_seat.loadSeat();
			$('[data-toggle="tooltip"]').tooltip();
		}
};