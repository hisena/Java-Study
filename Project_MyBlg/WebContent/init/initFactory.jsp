<%--
  -- 팩토리 초기 설정
  -- @author 류세은
  --%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%!
public void jspInit() {
DaoFactory factory = new JdbcDaoFactory();
getServletContext().setAttribute("factory", factory);
}
%>