package com.yfl.modle;

public class BookType {
	
	
	private String bookTypeId,bookTypeName,bookTypeIntroduce;

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

	public String getBookTypeIntroduce() {
		return bookTypeIntroduce;
	}

	public void setBookTypeIntroduce(String bookTypeIntroduce) {
		this.bookTypeIntroduce = bookTypeIntroduce;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getBookTypeName();
	}

}
