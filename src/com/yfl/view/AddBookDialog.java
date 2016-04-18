package com.yfl.view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

import com.yfl.modle.Book;
import com.yfl.modle.BookType;
import com.yfl.service.BookService;
import com.yfl.utils.Utils;

public class AddBookDialog extends JDialog implements ActionListener{

	/**
	 * 添加图书界面
	 */
	private JLabel bookName,bookOther,bookPrice,bookPress,bookType,bookIntroduce;
	private JTextField bookNameText,bookOtherText,bookPriceText,bookPressText;
	private JTextArea bookIntroduceText;
	private JButton add,reset;
	private JComboBox bookTypeBox;
	
	private static final long serialVersionUID = 1L;

	public AddBookDialog(Frame owner, String title, boolean modal) {
		super(owner,title, modal);
		
		Container container=this.getContentPane();
		this.setLayout(null);
		
		bookName=new JLabel("图书名称:");
		bookName.setFont(Utils.f2);
		bookName.setBounds(30, 30, 70, 20);
		container.add(bookName);
		
		bookNameText=new JTextField();
		bookNameText.setFont(Utils.f2);
		bookNameText.setBounds(120, 30, 100, 20);
		container.add(bookNameText);
		
		bookOther=new JLabel("图书作者:");
		bookOther.setFont(Utils.f2);
		bookOther.setBounds(280, 30, 70, 20);
		container.add(bookOther);
		
		bookOtherText=new JTextField();
		bookOtherText.setFont(Utils.f2);
		bookOtherText.setBounds(370, 30, 100, 20);
		container.add(bookOtherText);
		
		bookPrice=new JLabel("图书价格:");
		bookPrice.setFont(Utils.f2);
		bookPrice.setBounds(30, 80, 70, 20);
		container.add(bookPrice);
		
		bookPriceText=new JTextField();
		bookPriceText.setFont(Utils.f2);
		bookPriceText.setBounds(120, 80, 100, 20);
		container.add(bookPriceText);
		
		bookPress=new JLabel("出 版 社:");
		bookPress.setFont(Utils.f2);
		bookPress.setBounds(280, 80, 70, 20);
		container.add(bookPress);
		
		bookPressText=new JTextField();
		bookPressText.setFont(Utils.f2);
		bookPressText.setBounds(370, 80, 100, 20);
		container.add(bookPressText);
		
		bookType=new JLabel("图书类型");
		bookType.setFont(Utils.f2);
		bookType.setBounds(30, 130, 70, 20);
		container.add(bookType);
		
		bookTypeBox=new JComboBox();
		BookType type=new BookType();
		type.setBookTypeName("请选择...");
		bookTypeBox.insertItemAt(type, 0);
		Vector<BookType> types=new BookService().getBookTypes();
		//把值循环的加入bookTypeBox
		for(int i=0;i<types.size();i++){
			BookType bookType=types.get(i);
			//这里直接加入对象会调用该对象的toString方法 所有需要在该类中重写toString方法
			bookTypeBox.insertItemAt(bookType, i+1);
			
		}
		bookTypeBox.setFont(Utils.f2);
		bookTypeBox.setBounds(120, 130, 100, 20);
		bookTypeBox.setSelectedIndex(0);
		container.add(bookTypeBox);
	
		bookIntroduce=new JLabel("图书介绍:");
		bookIntroduce.setFont(Utils.f2);
		bookIntroduce.setBounds(30, 180, 70, 20);
		container.add(bookIntroduce);
		
		bookIntroduceText=new JTextArea();
		bookIntroduceText.setFont(Utils.f2);
		bookIntroduceText.setBounds(120, 180, 350, 100);
		bookIntroduceText.setBorder(BorderFactory.createTitledBorder(""));
		container.add(bookIntroduceText);
		
		
		add=new JButton("添加");
		add.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/yfl/images/add.png")));
		add.setBounds(140, 320, 90, 30);
		add.setFont(Utils.f2);
		add.addActionListener(this);
		container.add(add);
		
		reset=new JButton("重置");
		reset.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/yfl/images/reset.png")));
		reset.setBounds(270, 320, 90, 30);
		reset.setFont(Utils.f2);
		reset.addActionListener(this);
		container.add(reset);
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/yfl/images/add.png")).getImage());
		this.setSize(500, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==add){
			
			String bookName=bookNameText.getText().trim();
			String bookOther=bookOtherText.getText().trim();
			String bookPrice=bookPriceText.getText().trim();
			String bookPress=bookPressText.getText().trim();
			String bookIntroduce=bookIntroduceText.getText().trim();
			BookType bookType=(BookType)bookTypeBox.getSelectedItem();
			
			if(!(Utils.isEmpty(bookName)&&Utils.isEmpty(bookOther)&&Utils.isEmpty(bookPrice)&&Utils.isEmpty(bookPress)&&Utils.isEmpty(bookIntroduce))){
				JOptionPane.showMessageDialog(this, "其中有一项为空!");
				return;
			}
			if(bookTypeBox.getSelectedIndex()==0||bookTypeBox.getSelectedIndex()==-1){
				JOptionPane.showMessageDialog(this, "请选择图书类型!");
				return;
			}
			
			//封装book对象
			Book book=new Book(null,bookName, bookOther, bookPrice, bookPress, bookType.getBookTypeId(), bookIntroduce);
			if(new BookService().addBook(book)){
				JOptionPane.showMessageDialog(this, "添加成功!");
				this.inputClear();
			}else{
				JOptionPane.showMessageDialog(this, "添加失败!");
			}
			
		}else if(e.getSource()==reset){
			this.inputClear();
		}
		
	}
	
	//清空输入框的内容
	public void inputClear(){
		bookNameText.setText("");
		bookOtherText.setText("");
		bookPriceText.setText("");
		bookPressText.setText("");
		bookIntroduceText.setText("");
		bookTypeBox.setSelectedIndex(0);
	}
}