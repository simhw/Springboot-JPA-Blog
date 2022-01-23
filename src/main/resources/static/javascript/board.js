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
		$('#btn-reply-save').on("click", () => {
			this.replySave();
		});
	},
	// 게시글 작성 
	save: function() {

		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		}

		$.ajax({
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
	// 게시글 삭제 
	deleteById: function() {
		
		let id = $('#id').val();

		$.ajax({
			// 삭제 수행 요청 
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json",
			contentType: "application/json; charset=utf-8",

		}).done(function(resp) {
			// 응답 실행 
			alert("삭제가 완료되었습니다.");
			location.href = "/";

		}).fail(function(error) {
			// 응답 실패 
			alert(JSON.stringify(error));

		});
	},
	
	// 게시글 수정 
	update: function() {
		
		let id = $('#id').val();
		
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		};
		
		$.ajax({
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
	},
	
	// 댓글 작성  
	replySave: function() {
		let data = {
			boardId: $('#board-id').val(),
			content: $('#reply-content').val(),
		};
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			// 응답 실행
			location.href = `/board/${data.boardId}`;

		}).fail(function(error) {
			// 응답 실패
			alert(JSON.stringify(error));
		});
	}
}

index.init();