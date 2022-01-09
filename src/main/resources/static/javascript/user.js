let index = {
	
	init:function(){


		$('#btn-save').on("click", ()=>{
			this.save();
		});
		$('#btn-login').on("click", ()=>{
			this.login();
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
			url: "/auth/api/user/join",
			data: JSON.stringify(data),		// http body data
			contentType: "application/json; charset=utf-8",	// http body data type
			dataType: "json"	// 서버로부터 JSON 타입으로 응답이 왔을 경우 javascript 형식으로 반환

			// 응답 성공
		}).done(function(res){
			alert("회원가입이 완료되었습니다.");
			location.href= "/";
			return res;
			// 응답 실패
		}).fail(function(err){
			console.log(err);
			alert("회원가입이 실패하였습니다.");
			return err;
		});
	},

	// 로그인
	login: function(){

		let data = {
			id:$('#id').val(),
			password:$('#password').val(),
		}

		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(res){
			alert("로그인이 완료되었습니다.");
			location.href= "/";
			return res;

		}).fail(function(err){
			console.log(err);
			alert("회원가입이 실패하였습니다.");
			return err;
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