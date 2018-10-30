<%--
 -- 게시판 화면
 -- @author 류세은
 --%>

<%@page import="kr.or.kosta.blog.page.PageBuilder"%>
<%@page import="kr.or.kosta.blog.page.Params"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String certify = request.getParameter("certify");

%>

<%
// 페이지당 보여지는 목록수 설정
int listSize = 10;
//페이지당 보여지는 페이지수 설정
int pageSize = 5;

// 선택페이지 수신
String requestPage = request.getParameter("page");
if(requestPage == null || requestPage.equals("")){
  requestPage = "1";
}

// 검색 요청일 경우 파라메터 수신
String searchType = request.getParameter("searchType");
String searchValue = request.getParameter("searchValue");
if(searchType == null || searchType.equals("")){
  searchType = null;
  searchValue = null;
}

// 요청파라메터 포장
Params params = new Params(Integer.parseInt(requestPage), listSize, pageSize, searchType, searchValue);
DaoFactory factory= (DaoFactory)application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
List<Article> list = dao.listByPage(params);
List<Article> listAll = dao.listAll();
// 페이징 처리에 필요한 검색 개수 DB조회
int rowCount = dao.countBySearch(params);

// PageBuilder를 이용하여 페이징 계산
PageBuilder pageBuilder = new PageBuilder(params, rowCount);

pageBuilder.build();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/board.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/snackbar.css">
<title>자유게시판</title>
</head>
<body>

 <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

<div class="wrap">
  <div class="wrap">
   <ul>
            <!-- 게시판 제목 -->
            <li id="title"> 자유게시판 </li>
   </ul>
  </div>
   <div class="wrap">
    <form>
  <ul class="board_search">
    <li><input type="radio" name="searchType" id="search00" checked="checked" value=""/><label for="search00">전체</label></li>
    <li><input type="radio" name="searchType" id="search01" value="writer"/><label for="search01">작성자</label></li>
    <li><input type="radio" name="searchType" id="search02" value="subject"/><label for="search02">글제목</label></li>
    <li><input type="radio" name="searchType" id="search03" value="content"/><label for="search03">글내용</label></li>
    <li><input type="text" name="searchValue" placeholder="Search.."/><input type="submit" value="검색" onclick="'<%=application.getContextPath() %>/board/board_form.jsp'"/></li>
  </ul>
   </form>
   </div>


  <div class="wrap">
      <h3>게시물 <%=rowCount %>개 (전체 게시물 총 <%=listAll.size()%>개)</h3>
	<table class="board_list">
		<colgroup>
			<col style="width:8%" />
			<col style="width:68%" />
			<col style="width:12%" />
			<col style="width:12%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
				<th scope="col">아이피</th>
				<th scope="col">조회</th>
			</tr>
		</thead>
		<tbody>
			<%
            for(int i=0; i<list.size(); i++) {
              Article article = list.get(i);
              if(!(article.getSubject().equals("해당 게시글은 삭제되었습니다."))) {
            %>
               <tr onclick="location.href='articleRead_form.jsp?article_id=<%=article.getArticleId()%>&page=<%=params.getPage()%>'">
                <td><%=(rowCount - listSize * (params.getPage()-1) ) - i %></td>
                <td><%=article.getSubject() %></td>
                <td><%=article.getWriter() %></td>
                <td><%=article.getRegdate() %></td>
                <td><%=article.getIp() %></td>
                <td><%=article.getHitcount() %></td>
              </tr>              
            <%
              } else {
            %>
             <tr>
               <td><%=(rowCount - listSize * (params.getPage()-1) ) - i %></td>
               <td colspan="5">해당 게시글은 삭제되었습니다.</td>
               </tr>
            <%
             }
           }
            %>
		</tbody>
	</table>
    </div>
	<!-- page_navi start -->
	<div class="page_navi">
   <%
      if(pageBuilder.isShowFirst()){
      %>
        <a href="<%=pageBuilder.getQueryString(1)%>" class="first">처음</a>      
      <%        
      }
      %>
		
     <%
      if(pageBuilder.isShowPrevious()){
      %>
        <a href="<%=pageBuilder.getQueryString(pageBuilder.getPreviousStartPage())%>" class="prev">이전</a>      
      <%        
      }
      %>
		<span>
     <%
      for(int i=pageBuilder.getStartPage(); i<=pageBuilder.getEndPage(); i++){
        if(i == params.getPage()){
      %>
          <strong><a class="active"><%=i %></a></strong>
      <%          
        }else{
      %>
           <a href="<%=pageBuilder.getQueryString(i)%>"><%=i %></a>
      <%          
        }
      }
      %>
		</span>
      <%
      if(pageBuilder.isShowNext()){
      %>
        <a href="<%=pageBuilder.getQueryString(pageBuilder.getNextStartPage())%>" class="next">다음</a>      
      <%        
      }
      %>
      <%
      if(pageBuilder.isShowLast()){
      %>
        <a href="<%=pageBuilder.getQueryString(pageBuilder.getPageCount())%>" class="last">마지막</a>      
      <%
      }
      %>                  
	</div>
      
    <div class="page_navi">
   <a href="/index.jsp" class="home">홈으로</a>
   <a href="/board/article_form.jsp" class="write">글쓰기</a>
   </div>

    </div>
    
<%--footer 시작--%>
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>
  <!-- The actual snackbar -->
<div id="snackbar">Some text some message..</div>
</body>
<script type="text/javascript">
function myFunction() {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
</script>
<%if(certify != null && certify.equals("fail")){ %>
<script>
document.getElementById("snackbar").innerText = "비밀번호를 정확히 입력해주세요";
myFunction();
</script>
<%} %>
</html>