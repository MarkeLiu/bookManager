package com.yfl.modle;

public class Book {

	private String bookId,bookName,bookOther,bookPrice,bookPress,bookTypeId,bookIntroduce,bookTypeName;

	public String getBookId() {
		return bookId;
	}

	public Book(String bookId,String bookName, String bookOther, String bookPrice,
			String bookPress, String bookTypeId, String bookIntroduce) {
		super();
		this.bookName = bookName;
		this.bookOther = bookOther;
		this.bookPrice = bookPrice;
		this.bookPress = bookPress;
		this.bookTypeId = bookTypeId;
		this.bookIntroduce = bookIntroduce;
	}
	
	public Book(){
		
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookOther() {
		return bookOther;
	}

	public void setBookOther(String bookOther) {
		this.bookOther = bookOther;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public String getBookIntroduce() {
		return bookIntroduce;
	}

	public void setBookIntroduce(String bookIntroduce) {
		this.bookIntroduce = bookIntroduce;
	}

	public String getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
}
