package com.yfl.modle;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import com.yfl.service.BookTypeService;

public class BooTypeModel extends AbstractTableModel {

	/**
	 * BookType类的表数据模型
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnName = null;
	private Vector<Object> rowDate = null;
	private BookTypeService bookTypeService = new BookTypeService();

	public BooTypeModel(BookType bookType) {

		// 得到共用多少种类型的书
		rowDate = bookTypeService.getBookTypes(bookType);
		// 得到列名
		columnName = bookTypeService.getColumnName();
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

		return ((Vector<String>) rowDate.get(rowIndex)).get(columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnName[column];
	}

}
