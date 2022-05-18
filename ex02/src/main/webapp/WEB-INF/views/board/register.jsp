<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp"/>
<!-- <style>
.thumbs figure {overflow:hidden;}
.thumbs figure img {transition:all .2s}
.thumbs figure img:hover {transform:scale(1.05)}
</style> -->
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
                    <h1 class="h3 text-gray-800 mb-4">Board Register Page</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left">DataTables Example</h6>
                        </div>
                        <div class="card-body">
                        
                        <form method="post">
						  <div class="form-group">
						    <label for="title">title</label>
						    <input type="text" class="form-control" placeholder="title" id="title" name="title">
						  </div>
						  <div class="form-group">
						    <label for="content">content</label>
						    <textarea type="text" class="form-control" placeholder="content" id="content" name="content"></textarea>
						  </div>
						  <div class="form-group">
						    <label for="writer">writer</label>
						    <input type="text" class="form-control" placeholder="writer" id="writer" name="writer" readonly value='<sec:authentication property="principal.username"/>'>
						  </div>
							
						  <hr>
						  <div class="form-group uploadDiv">
						    <label for="attach" class="btn btn-success btn-sm" >첨부</label>
						    <input type="file" class="form-control d-none" placeholder="attach" id="attach" name="attach" multiple>
						  </div>
						  
						  	<ul class="list-group small container px-1 upload-files">
							  <!-- <li class="list-group-item"><a href="#">파일1.html</a></li> -->
							</ul>
							
							<div class="container pt-3 px-1">							
								<div class="row thumbs">
								
									<!-- <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
										<figure class="d-inline-block" style="position: relative;">
											<figcaption><button type="button" class="close" style="position: absolute; top: 15px; right: 8px"><span>&times;</span></button></figcaption>
											<a href="#"><img alt="" src="/display?fileName=2022/05/03/s_f865ac7c-bff7-41dc-ba6a-67dc6247c86b.png" class="mx-1 my-2 img-thumbnail"></a>
										</figure>
									</div> -->

									<!-- <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
										<figure class="d-inline-block" style="position: relative;">
											<figcaption>
												<button type="button" class="close" style="position: absolute; top: 15px; right: 8px"><span>&times;</span></button>
											</figcaption>
											<a href="/display?origin=book-1091627_1920.jpg&amp;uuid=a759698c-4cb3-4548-bfac-e17ddeaee868.jpg&amp;path=2022%2F05%2F04&amp;image=true" data-lightbox="aa-1" data-title="파일의 원본이름.jpg">
												<img alt="" src="/display?origin=book-1091627_1920.jpg&amp;uuid=s_a759698c-4cb3-4548-bfac-e17ddeaee868.jpg&amp;path=2022%2F05%2F04&amp;image=true" class="mx-1 my-2 img-thumbnail">
											</a>
										</figure>
									</div> -->

									
								</div><!-- row 끝 -->
							</div> <!-- container 끝 -->
						  
						  <sec:csrfInput/>
						  <button type="submit" class="btn btn-primary" id="btnReg">Submit</button>
						</form>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
			<jsp:include page="../common/footer.jsp"/>
			
			<script>
/*                 $(function () {
                	$("#attach").change(function() {
                		var str = "";
                		$(this.files).each(function() {
							str += "<p>" + this.name + "</p>";
						})
						$(this).next().html(str);
					});
				}) */
			</script>
			<script>
			$(function() {
				lightbox.option({
					resizeDuration: 200,
					wrapAround: true,
					imageFadeDuration: 100
				})

				var $clone = $(".uploadDiv").clone();
				var regexp = /(.*?)\.(exe|sh|js|jsp)$/;
				var maxSize = 1024 * 1024 * 5;
				
				function validateFiles (fileName, fileSize) {
					if(fileSize >= maxSize) {
						alert("파일 사이즈 초과");
						return false;
					}
					if(regexp.test(fileName)) {
						alert("해당 파일의 종류는 업로드할 수 없습니다.");
						return false;
					}
					return true;
				}
				// 파일 첨부
				$(".uploadDiv").on("change", ":file", function() {
					var formData = new FormData();
					
					for(var i in this.files) {
						if(!validateFiles(this.files[i].name, this.files[i].size)) {
							return false;
						}
						formData.append("files", this.files[i]);
					}
					
					$.post({
						processData : false,
						contentType : false,
						data : formData,
						url : "/upload",
						dataType : "json"
					}).done(function(result) {
						console.log(result);
						$(".uploadDiv").html($clone.html());
						
						var str = "";
						var thumbStr = "";
						for(var i in result) {	
							//object >> queryString

							console.log(result[i]);
							console.log($.param(result[i]));
							str += '<li class="list-group-item" data-uuid="' + result[i].uuid + '" data-path="' + result[i].path + '" data-image="' + result[i].image + '" data-origin="' + result[i].origin + '"><a href="/download?' + $.param(result[i]) +'">'
								+result[i].origin+'</a><button type="button" class="close"><span>&times;</span></button></li>';
							
							if(result[i].image) {
								var o = {...result[i]}; // clone
								o.uuid = 's_' + o.uuid;
								thumbStr += '<div class="col-6 col-sm-4 col-lg-3 col-xl-2" data-uuid="' + result[i].uuid + '" data-path="' + result[i].path + '" data-image="' + result[i].image + '" data-origin="' + result[i].origin + '">';
								thumbStr += '	<figure class="d-inline-block" style="position: relative;">';
								thumbStr += '		<figcaption><button type="button" class="close" style="position: absolute; top: 15px; right: 8px"><span>&times;</span></button></figcaption>';
								thumbStr += '		<a href="/display?'+ $.param(result[i]) +'" data-lightbox="img" data-title="' + o.origin + '"><img alt="" src="/display?'+ $.param(o) +'" class="mx-1 my-2 img-thumbnail"></a>';
								thumbStr += '	</figure>';
								thumbStr += '</div>';
							}


						}
						$(".upload-files").append(str);
						$(".thumbs").append(thumbStr);
					})
				})
				// 파일 첨부 종료

				// 파일 삭제 이벤트
				$(".upload-files, .thumbs").on("click", ".close", function(){
					var $dom = $(this).closest("[data-uuid]");
					var uuid = $dom.data("uuid");
					var image = $dom.data("image");
					var path = $dom.data("path");
					// console.log(uuid);
					$.post({
						url : "/deleteFile",
						data : {uuid:uuid, path:path, image:image},
						success : function(result) {
							console.log(result);
							$("[data-uuid ='"+ uuid +"']").remove();
						}
					})
				});

				// 게시글 등록 이벤트
				$("#btnReg").click(function(){
					event.preventDefault();
					var str = "";
					var attrArr = ['uuid', 'origin', 'path', 'image'];
					$(".upload-files li").each(function(i){
						for(var j in attrArr){
							str += 
								$("<input>")
								.attr("type", "hidden")
								.attr("name", "attachs[" + i + "]." + attrArr[j])
								.attr("value", $(this).data(attrArr[j])).get(0).outerHTML + "\n";
						}
					});
					console.log(str);
					$(this).closest("form").append(str).submit();
				});
			})
		</script>

        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->    

	
</body>

</html>