package com.yfl.view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import com.yfl.modle.Book;
import com.yfl.modle.BookModel;
import com.yfl.modle.BookType;
import com.yfl.service.BookService;
import com.yfl.utils.Utils;

public class UpdateBook extends JDialog implements ActionListener,ListSelectionListener{

	/**
	 * 图书维护对话框
	 */
	private static final long serialVersionUID = 1L;
	private JLabel bookNameTop,bookOtherTop,bookTypeTop;
	private JLabel bookId,bookName,bookOther,bookPrice,bookPress,bookType,bookIntroduce;
	private JTextField bookNameTextTop,bookOtherTextTop,bookIdText,bookNameText,bookOtherText,bookPriceText,bookPressText;
	private JTextArea bookIntroduceText;
	private JButton searchButton,reviseButton,deleteButton;
	private JScrollPane tableScroll;
	private JTable bookTable;
	private JComboBox bookTypeBoxTop,bookTypeBox;
	private Container container=null;
	private Book book=new Book();
	private BookModel model=null;

	
	
	public UpdateBook(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		container=this.getContentPane();
		this.setLayout(null);
		
		bookNameTop=new JLabel("图书名称:");
		bookNameTop.setFont(Utils.f2);
		bookNameTop.setBounds(20, 30, 70, 20);
		container.add(bookNameTop);
		
		bookNameTextTop=new JTextField();
		bookNameTextTop.setFont(Utils.f2);
		bookNameTextTop.setBounds(100, 30, 100, 20);
		container.add(bookNameTextTop);
		
		bookOtherTop=new JLabel("图书作者:");
		bookOtherTop.setFont(Utils.f2);
		bookOtherTop.setBounds(230, 30, 70, 20);
		container.add(bookOtherTop);
		
		bookOtherTextTop=new JTextField();
		bookOtherTextTop.setFont(Utils.f2);
		bookOtherTextTop.setBounds(310, 30, 100, 20);
		container.add(bookOtherTextTop);
		
		bookTypeTop=new JLabel("图书类型:");
		bookTypeTop.setFont(Utils.f2);
		bookTypeTop.setBounds(440, 30, 70, 20);
		container.add(bookTypeTop);
		
		
		bookTypeBoxTop=new JComboBox(); 
		BookType bookType=new BookType();
		bookType.setBookTypeName("请选择...");
		bookType.setBookTypeId("-1");
		bookTypeBoxTop.insertItemAt(bookType, 0);
		//获取当前有哪些类型并添加到bookTypeBoxTop中
		Vector<BookType> types=new BookService().getBookTypes();
		for (int i=0; i<types.size();i++){
			BookType type=types.get(i);
			bookTypeBoxTop.insertItemAt(type, i+1);
		}
		bookTypeBoxTop.setSelectedIndex(0);
		bookTypeBoxTop.setFont(Utils.f2);
		bookTypeBoxTop.setBounds(520, 30, 100, 20);
		container.add(bookTypeBoxTop);
		
		searchButton=new JButton("查询");
		searchButton.setFont(Utils.f2);
		searchButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/search.png")));
		searchButton.setBounds(660, 24, 90, 30);
		searchButton.addActionListener(this);
		container.add(searchButton);
		
		bookTable=new JTable(new BookModel(new Book()));
		bookTable.getSelectionModel().addListSelectionListener(this);
		bookTable.setFont(Utils.f2);
		//不加滚动条不会显示列名
		tableScroll=new JScrollPane(bookTable);
		tableScroll.setBorder(BorderFactory.createTitledBorder(""));
		tableScroll.setBounds(20, 80, 730, 200);
		container.add(tableScroll);
		
		//加载下面的组件
		this.initBottom();
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/manageBookType.png")).getImage());
		this.setSize(780,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initBottom(){
		
		bookId=new JLabel("图书编号:");
		bookId.setFont(Utils.f2);
		bookId.setBounds(80, 320, 70, 20);
		container.add(bookId);
		
		bookIdText=new JTextField();
		bookIdText.setFont(Utils.f2);
		bookIdText.setBounds(160, 320, 100, 20);
		bookIdText.setEditable(false);
		container.add(bookIdText);
		
		bookName=new JLabel("图书名称:");
		bookName.setFont(Utils.f2);
		bookName.setBounds(290, 320, 70, 20);
		container.add(bookName);
		
		bookNameText=new JTextField();
		bookNameText.setFont(Utils.f2);
		bookNameText.setBounds(370, 320, 100, 20);
		container.add(bookNameText);
		
		bookOther=new JLabel("图书作者:");
		bookOther.setFont(Utils.f2);
		bookOther.setBounds(500, 320, 70, 20);
		container.add(bookOther);
		
		bookOtherText=new JTextField();
		bookOtherText.setFont(Utils.f2);
		bookOtherText.setBounds(580, 320, 100, 20);
		container.add(bookOtherText);
		
		bookPrice=new JLabel("图书价格:");
		bookPrice.setFont(Utils.f2);
		bookPrice.setBounds(80, 350, 70, 20);
		container.add(bookPrice);
		
		bookPriceText=new JTextField();
		bookPriceText.setFont(Utils.f2);
		bookPriceText.setBounds(160, 350, 100, 20);
		container.add(bookPriceText);
		
		bookPress=new JLabel("出 版 社:");
		bookPress.setFont(Utils.f2);
		bookPress.setBounds(290, 350, 70, 20);
		container.add(bookPress);
		
		bookPressText=new JTextField();
		bookPressText.setFont(Utils.f2);
		bookPressText.setBounds(370, 350, 100, 20);
		container.add(bookPressText);
		
		bookType=new JLabel("图书类型:");
		bookType.setFont(Utils.f2);
		bookType.setBounds(500, 350, 70, 20);
		container.add(bookType);
		
		bookTypeBox = new JComboBox();
		BookType bookType=new BookType();
		bookType.setBookTypeName("请选择...");
		bookType.setBookTypeId("-1");
		bookTypeBox.insertItemAt(bookType, 0);
		//获取当前有哪些类型并添加到bookTypeBoxTop中
		Vector<BookType> types=new BookService().getBookTypes();
		for (int i=0; i<types.size();i++){
			BookType type=types.get(i);
			bookTypeBox.insertItemAt(type, i+1);
		}
		bookTypeBox.setSelectedIndex(0);
		bookTypeBox.setFont(Utils.f2);
		bookTypeBox.setBounds(580, 350, 100, 20);
		container.add(bookTypeBox);
		
		bookIntroduce=new JLabel("图书介绍:");
		bookIntroduce.setFont(Utils.f2);
		bookIntroduce.setBounds(80, 400, 70, 20);
		container.add(bookIntroduce);
		
		bookIntroduceText=new JTextArea();
		bookIntroduceText.setFont(Utils.f2);
		bookIntroduceText.setBounds(160, 403, 400, 100);
		bookIntroduceText.setBorder(BorderFactory.createTitledBorder(""));
		bookIntroduceText.setLineWrap(true);
		container.add(bookIntroduceText);
		
		reviseButton=new JButton("修改");
		reviseButton.setFont(Utils.f2);
		reviseButton.setBounds(255, 523, 90, 30);
		reviseButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/pen.png")));
		reviseButton.addActionListener(this);
		container.add(reviseButton);
		
		deleteButton=new JButton("删除");
		deleteButton.setFont(Utils.f2);
		deleteButton.setBounds(405, 523, 90, 30);
		deleteButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/delete.png")));
		deleteButton.addActionListener(this);
		container.add(deleteButton);
	}
	
	//封装搜索book对象
	public Book getBookBySearch(){
		
		Book book=new Book();
		
		book.setBookName(bookNameTextTop.getText().trim());
		book.setBookOther(bookOtherTextTop.getText().trim());
		BookType type=(BookType)bookTypeBoxTop.getSelectedItem();
		if (Integer.parseInt(type.getBookTypeId())!=-1){
			
			book.setBookTypeId((type.getBookTypeId()));
		}
		
		return book;
	}
	
	//得到选定行的数据并封装成book对象
	public void getSelectRowDate(int rowNum){
	
		//必须判断rowNum==-1否则会报下标越界异常
		if(rowNum!=-1){
			String bookId=bookTable.getValueAt(rowNum, 0).toString();
			String bookName=bookTable.getValueAt(rowNum, 1).toString();
			String bookOther=bookTable.getValueAt(rowNum, 2).toString();
			String bookPrice=bookTable.getValueAt(rowNum, 3).toString();
			String bookPress=bookTable.getValueAt(rowNum, 4).toString();
			String bookTypeName=bookTable.getValueAt(rowNum, 5).toString();
			String bookIntroduce=bookTable.getValueAt(rowNum, 6).toString();
			
			
			bookIdText.setText(bookId);
			bookNameText.setText(bookName);
			bookOtherText.setText(bookOther);
			bookPriceText.setText(bookPrice);
			bookPressText.setText(bookPress);
			bookIntroduceText.setText(bookIntroduce);
			
			//获取下拉列表的总选项
			int listCount=bookTypeBox.getItemCount();
			//遍历下拉列表的值
			for(int i=0; i<listCount; i++){
				
				BookType type=(BookType) bookTypeBox.getItemAt(i);
				String typeName=type.getBookTypeName();
				
				if(typeName.equals(bookTypeName)){
				
					bookTypeBox.setSelectedIndex(i);
				}
			}
		}
	
	}
	
	//清空输入框中的内容
	public void clearInput(){
		
		bookIdText.setText("");
		bookNameText.setText("");
		bookOtherText.setText("");
		bookPriceText.setText("");
		bookPressText.setText("");
		bookTypeBox.setSelectedIndex(0);
		bookIntroduceText.setText("");
	}
	
	//封装book对象
	public Book getBook(){
		
		Book book=new Book();
		
		//获取数据
		String bookId=bookIdText.getText().trim();
		String bookName=bookNameText.getText().trim();
		String bookOther=bookOtherText.getText().trim();
		String bookPrice=bookPriceText.getText().trim();
		String bookPress=bookPressText.getText().trim();
		String bookTypeId=((BookType)bookTypeBox.getSelectedItem()).getBookTypeId().trim();
		String bookIntroduce=bookIntroduceText.getText().trim();
		
		//如果获取的数据都不为空就封装成book对象
		if(Utils.isEmpty(bookId)&&Utils.isEmpty(bookName)&&Utils.isEmpty(bookOther)&&Utils.isEmpty(bookPrice)&&Utils.isEmpty(bookPress)&&Integer.parseInt(bookTypeId)!=-1&&Utils.isEmpty(bookIntroduce)){

			book=new Book(bookId, bookName, bookOther, bookPrice, bookPress, bookTypeId, bookIntroduce);
			
			book.setBookId(bookId);
			book.setBookName(bookName);
			book.setBookOther(bookOther);
			book.setBookPrice(bookPrice);
			book.setBookPress(bookPress);
			book.setBookTypeId(bookTypeId);
			book.setBookIntroduce(bookIntroduce);
			
		} else {
			
			JOptionPane.showMessageDialog(this, "每一项都不能为空!");
			
		}

		return book;
	}

	
	//更新数据模型
		public void updateModel(){
			
			book.setBookId(null);
			model=new BookModel(book);
			//更新数据模型
			this.bookTable.setModel(model);
			//把输入框的内容置空
			this.clearInput();
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==searchButton){
			
			Book book=this.getBookBySearch();
			
			//验证搜索book对象是否为空
			if (!(Utils.isEmpty(book.getBookName())||Utils.isEmpty(book.getBookOther()))) {
				
				bookTable.setModel(new BookModel(book));
				
				//清除输入框的内容
				bookNameTextTop.setText("");
				bookOtherTextTop.setText("");
				bookTypeBoxTop.setSelectedIndex(0);
			} else {
			
				BookModel model=new BookModel(book);
				bookTable.setModel(model);
			}
			
		} else if (e.getSource()==reviseButton){

			//修改
			
			Book bookResive=this.getBook();
			
			if(bookResive==null){
				
				return;
				
			} else {

				if(new BookService().updateBook(bookResive)){
					
					JOptionPane.showMessageDialog(this, "修改成功!");
					this.updateModel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "修改失败!");
					
				}
			}
			
			
		} else if (e.getSource()==deleteButton){
			
			//删除
			Book book=new Book();
			String bookId=bookIdText.getText().trim();
			
			if(Utils.isEmpty(bookId)){
				
				book.setBookId(bookId);
				
				if(new BookService().deleteBook(book)){
					
					JOptionPane.showMessageDialog(this, "删除成功!");
					this.updateModel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "删除失败!");
					
				}
				
			} else{
				
				JOptionPane.showMessageDialog(this, "请选择您要删除的行!");
				return;
			}
		
		}
		
	}
	
	//该函数在每次更新数据模型时自动调用 并且table.getSelectedRow()会返回-1  注意啊!!!!!!
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		this.getSelectRowDate(bookTable.getSelectedRow());
		
	}



}