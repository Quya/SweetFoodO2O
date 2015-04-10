var AT_businessman_seat={
		addSeat:function(json){
			return jQuery.sendSyn("seatController","addSeat",json,window);
		},
		updateSeat:function(json){
			return jQuery.sendSyn("seatController","updateSeat",json,window);
		},
		onSeat:function(json){
			return jQuery.sendSyn("seatController","onSeat",json,window);
		},
		offSeat:function(json){
			return jQuery.sendSyn("seatController","offSeat",json,window);
		},
		deleteSeat:function(json){
			return jQuery.sendSyn("seatController","deleteSeat",json,window);
		},
		loadSeat:function(json){
			return jQuery.sendSyn("seatController","loadSeat",json,window);
		}
};