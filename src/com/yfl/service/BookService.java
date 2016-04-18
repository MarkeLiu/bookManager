package com.yfl.service;

import java.util.Vector;
import com.yfl.modle.Book;
import com.yfl.modle.BookType;
import com.yfl.utils.SqlHelper;
import com.yfl.utils.Utils;

public class BookService {

	// 获取当前数据库中有哪些图书类型
	public Vector<BookType> getBookTypes() {

		String sql = "select * from bookType";
		Vector<Object[]> objects = new SqlHelper().executeQuery(sql, null);

		Vector<BookType> types = new Vector<BookType>();

		// 循环取出数据并封装
		for (int i = 0; i < objects.size(); i++) {

			Object[] object = objects.get(i);
			// 封装图书类型对象
			BookType bookType = new BookType();
			for (int j = 0; j < object.length; j++) {
				
				bookType.setBookTypeId(object[0].toString());
				bookType.setBookTypeName(object[1].toString());
				bookType.setBookTypeIntroduce(object[2].toString());  
			}

			types.add(bookType);

		}

		return types;
	}

	// 添加图书
	public boolean addBook(Book book) {

		boolean b = false;
		String sql = "insert into book values(?,?,?,?,?,?,?)";
		String parameters[] = {null, book.getBookName(), book.getBookOther(),
				book.getBookPrice(), book.getBookPress(), book.getBookTypeId(),
				book.getBookIntroduce() };
		if (new SqlHelper().executeUpdata(sql, parameters) == 1) {
			b = true;
		}

		return b;
	}

	// 查询所有图书类型并封装
	public Vector<Object> getBooks(Book book) {

		Vector<Object> rowDate = null;
		StringBuffer sql = new StringBuffer("select bookId,bookName,bookOther,bookPrice,bookPress,bookTypeName,bookIntroduce from book,bookType");
		
		sql.append(" and book.bookTypeId=bookType.bookTypeId");
		
		if (Utils.isEmpty(book.getBookName())) {
			
			sql.append(" and bookName like '%" + book.getBookName() + "%'");
		}
		
		if (Utils.isEmpty(book.getBookOther())) {
			
			sql.append(" and bookOther like '%" + book.getBookOther() + "%'");
		}
		
		if (Utils.isEmpty(book.getBookTypeId())) {
			
			sql.append(" and bookTypeId like '%" + book.getBookTypeId() + "%'");
		}

		// 这里不能加else 注意啊~!!!!!!!!!!!!!!(如果要替换的字符串不存在返回原字符串)
		rowDate = new SqlHelper().executeTable(sql.toString().replaceFirst("and", "where"), null);

		return rowDate;
	}

	// 得到列名并二次封装
	public String[] getColumnName() {

		String columnName[] = null;
		String sql = "select bookId,bookName,bookOther,bookPrice,bookPress,bookTypeName,bookIntroduce from book,bookType" +
				" where book.bookTypeId=bookType.bookTypeId";
	
		columnName = new SqlHelper().executeColumn(sql, null);

		for (int i = 0; i < columnName.length; i++) {

			if ("bookId".equals(columnName[i])) {

				columnName[i] = "图书编号";
			} else if("bookName".equals(columnName[i])){
				
				columnName[i] = "图书名称";
			} else if ("bookOther".equals(columnName[i])) {

				columnName[i] = "图书作者";
			} else if ("bookPrice".equals(columnName[i])) {

				columnName[i] = "图书价格";
			} else if ("bookPress".equals(columnName[i])) {

				columnName[i] = "图书出版社";
			} else if ("bookTypeName".equals(columnName[i])) {

				columnName[i] = "图书类型";
			} else if ("bookIntroduce".equals(columnName[i])) {

				columnName[i] = "图书介绍";
			} 
		}

		return columnName;
	}

	// 删除书
	public boolean deleteBook(Book book){
		
		boolean b=false;
		
		String sql="delete from book where bookId=?";
		
		String[] parameters={book.getBookId()};
		
		if ((new SqlHelper().executeUpdata(sql, parameters))==1) {
			
			b=true;
		}
		
		return b;
	}

	// 修改图书
	public boolean updateBook(Book book){
		
		boolean b=false;
		
		String sql="update book set bookName=?,bookOther=?,bookPrice=?,bookPress=?,bookTypeId=?,bookIntroduce=? where bookId=?";


		String[] parameters={book.getBookName(),book.getBookOther(),book.getBookPrice(),book.getBookPress(),book.getBookTypeId(),book.getBookIntroduce(),book.getBookId()};
		
		if ((new SqlHelper().executeUpdata(sql, parameters))==1) {
			
			b=true;
		}
		
		return b;
	}
}
