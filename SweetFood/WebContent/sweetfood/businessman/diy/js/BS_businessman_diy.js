var diyModel;
var BS_businessman_diy={
		diyViewModel : function(json) {
			self = this;
			self.diys = ko.observableArray(json);
			self.openDiyDlg=function(diy){
				$("#topic").text(diy.topicName);
				$("#time").text(diy.pushDate);
				$("#diycontent").text(diy.diyContent);
				$("#readtime").text(diy.readTimes+"次");
				$("#myDiyModal").modal({
					'backdrop' : 'static'
				});
			};
			self.deleteDiy=function(diy){
				var ret=AT_businessman_diy.deleteDiy({"diyId":diy.diyId});
				if(ret.state==1){
					bootbox.confirm(ret.value,function(choice){
						BS_businessman_diy.reloadDiy();
					});
					
				}
			};
		},
		addDiy:function(){
			var ret=AT_businessman_diy.addDiy({"userid":$("#userid")[0].value,"topicname":$("#topicname")[0].value,"diycontent":$("#diycontent")[0].value});
			if(ret.state==1){
				bootbox.confirm(ret.value,function(choice){
					$("#topicname").val("");
					$("#diycontent").val("");
					location.assign(jQuery.GetUrl(window)+ "sweetfood/businessman/diy/businessman_diy.html");
				});
				
			}	
		},
		loadDiy:function(){
			var ret=AT_businessman_diy.loadDiy({"hostid":$("#userid")[0].value});
			diyModel = new BS_businessman_diy.diyViewModel(ret.value);
			ko.applyBindings(diyModel);	
		},
		reloadDiy:function(){
			var ret=AT_businessman_diy.loadDiy({"hostid":$("#userid")[0].value});
			diyModel.diys(ret.value);
		}
};