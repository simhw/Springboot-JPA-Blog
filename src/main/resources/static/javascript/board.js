let index = {

	init: function() {
		$('#btn-save').on("click", () => {
			this.save();
		});

		$('#btn-delete').on("click", () => {
			this.deleteById();
		});
		$('#btn-update').on("click", () => {
			this.update();
		});

	},
	// 생성 
	save: function() {
		// alert('user save 함수 호출');
		// key:value

		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		}

		// ajax 통신을 이용해 데이터를 jason으로 변경해 insert 해준다. 
		$.ajax({
			// 회원가입 수행 요청 
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			// 응답 실행 
			alert("저장되었습니다.");
			location.href = "/";

		}).fail(function(error) {
			// 응답 실패 
			alert(JSON.stringify(error));

		});
	},
	// 삭제 
	deleteById: function() {
		let id = $('#id').val();
		$.ajax({
			// 삭제 수행 요청 
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"

		}).done(function(resp) {
			// 응답 실행 
			alert("삭제가 완료되었습니다.");
			location.href = "/";

		}).fail(function(error) {
			// 응답 실패 
			alert(JSON.stringify(error));

		});
	},
	
	// 수정 
	update: function() {
		let id = $('#id').val();
		
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		}
		console.log(id);
		console.log(data);
		$.ajax({
			// 회원가입 수행 요청 
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			// 응답 실행 
			alert("수정이 완료되었습니다.");
			location.href = "/";

		}).fail(function(error) {
			// 응답 실패 
			alert(JSON.stringify(error));

		});
	}
}

index.init();