package domain;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private int totalRecord;
	private int totalPage;
	
	private int pagenum;
	private int startindex;
	
	private int pagesize=10;
	private int simplePagesize;//指定一页内有多少条记录
	private List<Book>list=new ArrayList<Book>();
	
	private int startpage;
	private int endpage;
	
	private String servlet;//指定servlet进行分页
	
	
	public Page(int totalRecord,int pagenum,int simplePagesize){
		this.totalRecord=totalRecord;
		this.pagenum=pagenum;
		
		totalPage=(int) Math.ceil(totalRecord*1.0/simplePagesize);
		startindex=(pagenum-1)*simplePagesize;
		
		startpage=pagenum-5;
		endpage=pagenum+4;
		
		if(startpage<1){
			startpage=1;
			endpage=totalPage;
		}
		
		if(endpage>totalPage){
			endpage=totalPage;
			startpage=totalPage-pagesize;
		}
	}

	
	public int getSimplePagesize() {
		return simplePagesize;
	}


	public void setSimplePagesize(int simplePagesize) {
		this.simplePagesize = simplePagesize;
	}


	public String getServlet() {
		return servlet;
	}

	public void setServlet(String servlet) {
		this.servlet = servlet;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	
	
}
