package com.mysite.vo;

import org.springframework.stereotype.Component;

@Component
public class PageVo {
	int currNo;			//현재 페이지 번호
	int pageCnt;		//화면에 비쳐질 페이지 갯수
	int pageNo;			//한 페이지에 보여질 게시글 갯수
	int totalCount;		//총 게시글 갯수
	int firstNo;		//첫번째 페이지 숫자
	int endNo;			//마지막 페이지 숫자
	int endPage;		//마지막 페이지
	
	public PageVo() {}
	
	public PageVo(int currNo, int pageCnt, int pageNo, int totalCount, int firstNo, int endNo, int endPage) {
		this.currNo = currNo;
		this.pageCnt = pageCnt;
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.firstNo = firstNo;
		this.endNo = endNo;
		this.endPage = endPage;
	}

	public void PageSetting(int currNo, int totalCount) {
		if(currNo < 1) {
			this.currNo = 1;
		} else if(currNo > (int) Math.ceil((double)totalCount/pageNo)) {
			this.currNo = (int) Math.ceil((double)totalCount/pageNo);
		} else {
			this.currNo = currNo;
		}
		pageCnt = 5;
		pageNo = 5;
		this.totalCount = totalCount;
		endPage = (int) Math.ceil((double)totalCount/pageNo);
		firstNo = (currNo/(pageCnt+1))*pageCnt+1;
		if(endPage < pageCnt) {
			endNo = endPage;
		} else {
			endNo = firstNo + pageCnt - 1;
		}
		if(endNo > endPage) {
			endNo = endPage;
		}
	}

	public void PageSetting(int currNo, int pageCnt, int pageNo, int totalCount) {
		if(currNo < 1) {
			this.currNo = 1;
		} else if(currNo > (int) Math.ceil((double)totalCount/pageNo)) {
			this.currNo = (int) Math.ceil((double)totalCount/pageNo);
		} else {
			this.currNo = currNo;
		}
		this.pageCnt = pageCnt;
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		endPage = (int) Math.ceil((double)totalCount/pageNo);
		firstNo = (currNo/(pageCnt+1))*pageCnt+1;
		if(endPage < pageCnt) {
			endNo = endPage;
		} else {
			endNo = firstNo + pageCnt - 1;
		}
		if(endNo > endPage) {
			endNo = endPage;
		}
		
	}

	public int getCurrNo() {
		return currNo;
	}

	public void setCurrNo(int currNo) {
		this.currNo = currNo;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getFirstNo() {
		return firstNo;
	}

	public void setFirstNo(int firstNo) {
		this.firstNo = firstNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}	
}
