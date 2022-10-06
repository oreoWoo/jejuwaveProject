package service.ch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Mypage;
import dao.MypageDao;
import service.CommandProcess;

public class MypageCommentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // 세션값 받아오기
	     List<Mypage> list1 = new ArrayList<Mypage>();
	     List<Mypage> list2 = new ArrayList<Mypage>();
	     List<Mypage> list = new ArrayList<Mypage>();
		 HttpSession session = request.getSession();
		 String user_id = (String) session.getAttribute("user_id");
		 System.out.println("session 값 : "  + user_id);
		 MypageDao my = MypageDao.getInstance();
		 
		 try {
			 int totCnt = my.getTotalCommentCnt(user_id);
			 String pageNum = request.getParameter("pageNum"); 
			 if(pageNum==null || pageNum.equals("") || pageNum.equals("0")) {pageNum = "1";}
			 int currentPage = Integer.parseInt(pageNum);
			 int pageSize = 10 , blockSize = 10;
			 int startRow = (currentPage - 1) * pageSize + 1;
			 int endRow = startRow + pageSize -1;
			 int startNum = totCnt - startRow + 1;
			 System.out.println(startRow); // 작성자가 쓴 글 조회
			 list1 =my.commentList(user_id,startRow,endRow);
			 list2 =my.commentList2(user_id,startRow,endRow);
			 for(int i = 0 ; i < list1.size() ; i ++) {
				 list.add(list1.get(i));
			 }
			 for(int i = 0 ; i < list2.size() ; i ++) {
				 list.add(list2.get(i));
			 }
			 
		 int pageCnt = (int) Math.ceil((double)totCnt/pageSize);
		 
		 int startPage = (int)(currentPage-1)/blockSize*blockSize + 1; 
		 int endPage = startPage + blockSize - 1;
		 
		 if (endPage > pageCnt) endPage = pageCnt; 
		 
		 request.setAttribute("totCnt",totCnt);
		 System.out.println("MypageCommentAction totCnt" + totCnt);
		 request.setAttribute("pageNum", pageNum);
		 request.setAttribute("currentPage", currentPage);
		 request.setAttribute("startNum", startNum);
		 request.setAttribute("list",list);
		 System.out.println("MypageCommentAction list   : " + list);
		 request.setAttribute("blockSize", blockSize);
		 request.setAttribute("startPage", startPage);
		 request.setAttribute("endPage", endPage);
		 request.setAttribute("user_id", user_id);
		  System.out.println("MypageCommentAction list size : " + list.size());
		 } catch(Exception e) { 
			 System.out.println("MypageCommentAction 오류 -->" + e.getMessage());
		 	}
		

		return "ch/mypageComment.jsp";
	}

}
