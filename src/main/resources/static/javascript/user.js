let index = {
	
	init:function(){
		
		$('#btn-save').on("click", ()=>{
			this.save();
		});
		$('#btn-update').on("click", ()=>{	
			this.update();
		});
	},

	// 회원가입
	save: function(){

		let data = {
			id:$('#id').val(),
			password:$('#password').val(),
			email:$('#email').val()
		}

		console.log(data);

		// ajax 통신 + json 형 변환
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/join",
			data: JSON.stringify(data),		// http body data
			contentType: "application/json; charset=utf-8",
			dataType: "json"	// 서버로부터 JSON 타입으로 응답이 왔을 때 javascript 형식으로 반환

		}).done(function(resp){
			// 응답 실행
			alert("회원가입이 완료되었습니다.");
			location.href= "/";

		}).fail(function(error){
			// 응답 실패
			alert(JSON.stringify(error));

		});
	},
	
	
	// 회원수정 
	update: function(){
		let data = {
			id:$('#id').val(),
			username:$('#username').val(),
			password:$('#password').val(),
			email:$('#email').val()	
			
		}
		console.log(id + " " + password  + " " + email);
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
			 		
		})
		.done(function(resp){
			// 응답 실행 
			alert("회원수정이 완료되었습니다.");
			location.href= "/";
		
		})
		.fail(function(error){
			// 응답 실패 
			alert(JSON.stringify(error));
		});
	}
}

index.init();