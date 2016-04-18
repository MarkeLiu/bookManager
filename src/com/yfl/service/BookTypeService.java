package com.yfl.service;

import java.util.Vector;
import com.yfl.modle.BookType;
import com.yfl.utils.SqlHelper;
import com.yfl.utils.Utils;

public class BookTypeService {

	// 添加图书类型函数
	public boolean addBookType(BookType bookType) {

		boolean b = false;

		String sql = "insert into bookType values(?,?,?)";
		String parameters[] = {null, bookType.getBookTypeName(),
				bookType.getBookTypeIntroduce() };

		if (new SqlHelper().executeUpdata(sql, parameters) == 1) {
			b = true;
		}

		return b;

	}

	// 查询所有图书类型并封装
	public Vector<Object> getBookTypes(BookType bookType) {

		Vector<Object> rowDate = null;
		StringBuffer sql = new StringBuffer("select * from bookType");

		if (Utils.isEmpty(bookType.getBookTypeName())) {

			sql.append(" and bookTypeName like '%" + bookType.getBookTypeName()
					+ "%'");
		}
		// 这里不能加else 注意啊~!!!!!!!!!!!!!!(如果要替换的字符串不存在返回原字符串)
		rowDate = new SqlHelper().executeTable(
				sql.toString().replaceFirst("and", "where"), null);

		return rowDate;
	}

	// 得到列名并二次封装
	public String[] getColumnName() {

		String columnName[] = null;

		String sql = "select * from bookType";

		columnName = new SqlHelper().executeColumn(sql, null);

		for (int i = 0; i < columnName.length; i++) {

			if ("bookTypeId".equals(columnName[i])) {

				columnName[i] = "图书类型编号";
			} else if ("bookTypeName".equals(columnName[i])) {

				columnName[i] = "图书类型名称";
			} else if ("bookTypeIntroduce".equals(columnName[i])) {

				columnName[i] = "图书类型介绍";
			}
		}

		return columnName;
	}

	// 修改图书类型的内容
	public boolean updateBookType(BookType type) {
		boolean b = false;

		String sql = "update bookType set bookTypeName=?,bookTypeIntroduce=? where bookTypeId=?";
		String[] parameters = { type.getBookTypeName(),
				type.getBookTypeIntroduce(), type.getBookTypeId() };

		if (new SqlHelper().executeUpdata(sql, parameters) == 1) {
			b = true;
		}

		return b;
	}

	// 删除某一图书类型
	public int deleteBookType(BookType type) {

		int i = -1;

		String sql = "select bookId,bookType.bookTypeId from book,bookType where book.bookTypeId=?";
		String[] parameters = { type.getBookTypeId() };

		// 该图书类下面有图书
		if (new SqlHelper().executeQuery(sql, parameters).size() > 0) {

			i = 0;
			return 0;
		}

		sql = "delete from bookType where bookTypeId=?";

		if (new SqlHelper().executeUpdata(sql, parameters) == 1) {

			i = 1;

		}

		return i;
	}

}
