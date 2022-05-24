<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp"/>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
			<jsp:include page="../common/nav.jsp"/>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 text-gray-800 mb-4">Board Read Page</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left">Board Read Page</h6>
                        </div>
                        <div class="card-body">
                        
                        <form method="post">
						  <div class="form-group">
						    <label for="title">title</label>
						    <input type="text" class="form-control" placeholder="title" id="title" name="title" value="${board.title}" readonly>
						  </div>
						  <div class="form-group">
						    <label for="content">content</label>
						    <textarea type="text" class="form-control" placeholder="content" id="content" name="content" readonly>${board.content}</textarea>
						  </div>
						  <div class="form-group">
						    <label for="writer">writer</label>
						    <input type="text" class="form-control" placeholder="writer" id="writer" name="writer" value="${board.writer}" readonly>
						  </div>						
							<a class="btn btn-warning btn-sm" href="modify${cri.params}&bno=${board.bno}">수정</a>
						</form>
                        </div>
                    </div>
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left">Reply</h6>
                        </div>
                        
                        <div class="input-group p-3 reply-register-area">
                        	<textarea class="form-control" style='resize: none'></textarea>
                        	<div class="input-group-append">
                        		<button type="button" class="btn btn-primary">작성</button>
                        	</div>
                        </div>
                        
                        <div class="card-body list-group p-3 chat">

							<div class="list-group-item p-0 bg-dark text-white p-3" data-rno="1234">
								<strong>작성자</strong>
								<small class="float-right mt-1">게시일</small>
								<div class="dropdown float-right mr-2">
									<a href="#" data-toggle="dropdown" class="small text-white">
										<i class="fas fa-ellipsis-v"></i>
									</a>
									<div class="dropdown-menu small">
										<a class="dropdown-item" href="#">수정</a>
										<a class="dropdown-item" href="#">삭제</a>
									</div>
								</div>
							</div>
							<div class="reply-content">
								<p class="p-3 list-group-item mb-0">글 내용</p>
								<div class="input-group d-none">
									<textarea class="form-control rounded-0" style='resize: none'>글 내용</textarea>
									<div class="input-group-append">
										<button type="button" class="btn btn-warning rounded-0">수정</button>
									</div>
								</div>
							</div>
							
                        </div>

						<div class="p-3">
							<button class="btn btn-info btn-block btn-more">더보기</button>
						</div>

                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
			<jsp:include page="../common/footer.jsp"/>
			<script src="/resources/js/reply.js"></script>
			<script>
				var bno = '${board.bno}'
				console.log(replyService);
				
				$(function() {
					// 단일 댓글 생성
					function getReplyStr(reply) {
						var str = "";
						str += '<div class="list-group-item p-0 bg-dark text-white p-3" data-rno="' + reply.rno + '">';
						str += '	<strong>' + reply.replyer +' </strong>';
						str += '	<small class="float-right mt-1">'+ replyService.displayTime(reply.replyDate) +'</small>';
						str += '	<div class="dropdown float-right mr-2">';
						str += '		<a href="#" data-toggle="dropdown" class="small text-white">';
						str += '			<i class="fas fa-ellipsis-v"></i>';
						str += '		</a>';
						str += '		<div class="dropdown-menu small">';
						str += '			<a class="dropdown-item btn-reply-modify" href="#">수정</a>';
						str += '			<a class="dropdown-item btn-reply-remove" href="#">삭제</a>';
						str += '		</div>';
						str += '	</div>';
						str += '</div>';
						str += '<div class="reply-content">';
						str += '	<p class="p-3 list-group-item mb-0" style="white-space:pre-line">'+ reply.reply +'</p>';
						str += '	<div class="input-group d-none">';
						str += '		<textarea class="form-control rounded-0" style="resize: none"></textarea>';
						str += '		<div class="input-group-append">';
						str += '			<button type="button" class="btn btn-warning rounded-0">수정</button>';
						str += '		</div>';
						str += '	</div>';
						str += '</div>';
						return str;
					}

					// 댓글 목록 조회
					var lastRno;
					var amount; // undefined

					function showList(lastRno, amount) {
						var param = {bno:bno, lastRno:lastRno, amount:amount}
						replyService.getList(param, function(result) {
							console.log(result)
							var str = '';
							for(var i in result) {
								str += getReplyStr(result[i]);
							}
							// console.log(str);
							$(".chat").html(str);
						}) 
					}
					showList(lastRno, amount);

					/*event*/
					// 댓글 등록
					$(".reply-register-area button").click(function() {
						var replyContent = $(".reply-register-area textarea").val();

						if(replyContent.trim().length == 0) {
							alert("댓글 내용을 작성하세요");
							$(".reply-register-area textarea").focus();
							return;
						}
						var reply = {bno:bno, reply:$(".reply-register-area textarea").val(), replyer:"작성자"};
						replyService.add(reply, function(result) {
							alert("글 등록 성공");
							console.log(result); // 글번호

							replyService.get(result, function(result) {
								// alert("글 조회 성공");
								console.log(result); // reply object
								$(".chat").prepend(getReplyStr(result)); // 댓글목록 자식 태그 중 가장 첫번째 순서에 추가
							})

						});
					});

					// 댓글 삭제

					$(".chat").on("click", ".btn-reply-remove", function(){
						event.preventDefault();
						var $dom = $(this).closest(".bg-dark");
						var rno = $dom.data("rno");
						replyService.remove(rno, function(result) {
							alert("글 삭제 성공");
							console.log(result);
							$dom.next().remove();
							$dom.remove();
						})
					});
					
					// 댓글 수정
					
					$(".chat").on("click", ".btn-reply-modify", function(){
						event.preventDefault();
						var $dom = $(this).closest(".bg-dark");
						var rno = $dom.data("rno");
						console.log(rno);

						$(".reply-content > p").show();
						$(".reply-content > div").addClass("d-none");

						$dom.next().find("p").hide();
						$dom.next().find("div").find("textarea").val($dom.next().find("p").text()).end().removeClass("d-none");

					});
					
					// 댓글 수정 반영
					
					$(".chat").on("click", ".reply-content button", function(){
						var $replyContent = $(this).closest(".reply-content");
						var rno = $replyContent.prev().data("rno");
						var content = $replyContent.find("textarea").val();
						var reply = {rno: rno, reply:content};

						replyService.update(reply, function(result) {
							if(result && result !== 'success') {
								return;
							}
							// 성공
							$replyContent.find("p").text(content).show();
							$replyContent.find("div").addClass("d-none");
						});

					});
					
					// 더보기 버튼 이벤트
					$(".btn-more").click(function() {
						var lastRno = $(".chat > .bg-dark").last().data("rno");
						console.log(lastRno);
						var param = {bno:bno, lastRno:lastRno};

						$btnMore = $(this);
						replyService.getList(param, function(result) {
							console.log(result)
							var str = '';
							for(var i in result) {
								str += getReplyStr(result[i]);
							}
							$(".chat").append(str);
						}, null, function(){
							$btnMore.prop("disabled", true);
						},function(result) {
							if(result.length == 0) $btnMore.remove();
							setTimeout(function(){
								$btnMore.prop("disabled", false);
							},1000);
						}) 
					});

					// 무한 스크롤
					// $(document).scroll(function(){
					// 	var dh = $(document).height();
					// 	var wh = $(window).height();
					// 	var wst = $(window).scrollTop();
					// 	if(dh == wh + wst) {
					// 		var lastRno = $(".chat > .bg-dark").last().data("rno");
					// 		var param = {bno:bno, lastRno:lastRno};
					// 		replyService.getList(param, function(result) {
					// 			var str = '';
					// 			for(var i in result) {
					// 				str += getReplyStr(result[i]);
					// 			}
					// 			$(".chat").append(str);
					// 		})
					// 	}
					// })

				}); // 스크립트 끝

				
				
			</script>

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->    

	
</body>

</html>