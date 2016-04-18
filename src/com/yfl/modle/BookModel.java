package com.yfl.modle;

import java.util.Vector;
import javax.swing.table.*;
import com.yfl.service.BookService;

public class BookModel extends AbstractTableModel{

	/**
	 * 图书的table数据模型
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] columnName=null;
	private Vector<Object> rowDate=null;
	private BookService bookService=new BookService();
	
	public BookModel(Book book){

		//得到共用多少种类型的书
		rowDate=bookService.getBooks(book);
		//得到列名
		columnName=bookService.getColumnName();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnName[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowDate.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		return ((Vector<String>)rowDate.get(rowIndex)).get(columnIndex);
	}

}
