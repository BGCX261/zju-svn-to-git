package cn.itcast.bbs.entities;

import java.util.List;

import cn.itcast.bbs.cfg.SystemGlobals;

/**
 * @author 传智播客.汤阳光 Aug 16, 2008
 * @param <T>
 */
public class PageInfo<T> {

	/**
	 * <pre>
	 * 页面中显示的页码的列表:
	 * startPage是开始的页码; endPage是结束的页码.
	 * 显示时包含startPage,也包含endPage.
	 * 例如：startPage = 3, endPage = 9; 则显示:
	 *   3,4,5,6,7,8,9(包含3也包含9)
	 * </pre>
	 * 
	 * @author 传智播客.汤阳光 Apr 26, 2009
	 * 
	 */
	public static class PageIndex {
		private int startPage;
		private int endPage;

		PageIndex(int currentPage, int totalPage) {
			this.calculatePageIndex(currentPage, totalPage);
		}

		/**
		 * 计算startPage与endPage.
		 * 
		 * @param currentPage
		 * @param totalPage
		 */
		public void calculatePageIndex(int currentPage, int totalPage) {
			int viewPageCount = SystemGlobals.getSettings().getViewPageCount();
			if (totalPage <= viewPageCount) {
				this.startPage = 1;
				this.endPage = totalPage;
				return;
			}

			int n = (viewPageCount / 2);
			this.startPage = currentPage - n;
			if (this.startPage <= 1) {
				this.startPage = 1;
				// 因为两个边界的页码数也要显示
				this.endPage = this.startPage + (viewPageCount - 1);
				return;
			}

			this.endPage = currentPage + n;
			if (viewPageCount % 2 == 0) {
				this.startPage += 1;
			}
			if (this.endPage > totalPage) {
				this.endPage = totalPage;
				// 因为两个边界的页码数也要显示
				this.startPage = totalPage - (viewPageCount - 1);
			}
		}

		public int getStartPage() {
			return startPage;
		}

		public int getEndPage() {
			return endPage;
		}

		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}

		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}
	}

	private int pageSize; // 一页中显示的数据数量
	private int totalPage; // 总页码
	private int currentPage; // 当前页

	private int totalItems; // 总记录数
	private List<T> items; // 本页的记录
	private PageIndex pageIndex; // 显示的页码信息

	PageInfo() {}

	/**
	 * 计算指定页中的第一条记录在所有记录中的索引
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static int calculateFirstResult(int pageNum, int pageSize) {
		return (pageNum - 1) * pageSize;
	}

	/**
	 * 计算总页码数
	 * 
	 * @param totalItems
	 * @param pageSize
	 * @return
	 */
	public static int calculateTotalPage(int totalItems, int pageSize) {
		return (totalItems + pageSize - 1) / pageSize;
	}

	/**
	 * 使用必要的信息生成一个PageInfo
	 * 
	 * @param <E>
	 * @param qr
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static <E> PageInfo<E> populate(QueryResult<E> qr, int pageNum, int pageSize) {
		PageInfo<E> pageInfo = new PageInfo<E>();
		int totalPage = calculateTotalPage(qr.getTotal(), pageSize);
		PageIndex pageIndex = new PageIndex(pageNum, totalPage);

		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setItems(qr.getItems());
		pageInfo.setTotalItems(qr.getTotal());
		pageInfo.setTotalPage(totalPage);
		pageInfo.setPageIndex(pageIndex);
		return pageInfo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public List<T> getItems() {
		return items;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public PageIndex getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(PageIndex pageIndex) {
		this.pageIndex = pageIndex;
	}
}
