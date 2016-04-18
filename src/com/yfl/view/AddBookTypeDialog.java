package com.yfl.view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.yfl.modle.BookType;
import com.yfl.service.BookTypeService;
import com.yfl.utils.Utils;

public class AddBookTypeDialog extends JDialog implements ActionListener{

	/**
	 * 添加图书类型界面
	 */
	private JLabel typeName,typeIntroduce;
	private JTextField typeNameText;
	private JTextArea typeIntroduceText;
	private JButton add,reset;
	
	private static final long serialVersionUID = 1L;

	public AddBookTypeDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		Container container=this.getContentPane();
		this.setLayout(null);
		
		typeName=new JLabel("图书类型名称:");
		typeName.setFont(Utils.f2);
		typeName.setBounds(70, 50, 100, 20);
		container.add(typeName);
		
		typeNameText=new JTextField();
		typeNameText.setBounds(210, 47, 200, 20);
		typeName.setFont(Utils.f2);
		container.add(typeNameText);
		
		typeIntroduce=new JLabel("图书类型介绍:");
		typeIntroduce.setFont(Utils.f2);
		typeIntroduce.setBounds(70, 100, 100, 20);
		container.add(typeIntroduce);
		
		typeIntroduceText=new JTextArea(20,15);
		typeIntroduceText.setFont(Utils.f2);
		typeIntroduceText.setBounds(210, 100, 200, 150);
		typeIntroduceText.setLineWrap(true);
		typeIntroduceText.setBorder(BorderFactory.createTitledBorder(""));
		container.add(typeIntroduceText);
		
		add=new JButton("添加");
		add.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/yfl/images/add.png")));
		add.setBounds(70, 300, 90, 30);
		add.setFont(Utils.f2);
		add.addActionListener(this);
		container.add(add);
		
		reset=new JButton("重置");
		reset.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/yfl/images/reset.png")));
		reset.setBounds(210, 300, 90, 30);
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
	
		String bookTypeName=this.typeNameText.getText().trim();
		String bookTypeIntroduce=this.typeIntroduceText.getText().trim();
		if(e.getSource()==add){
	
			if(Utils.isEmpty(bookTypeName)&&Utils.isEmpty(bookTypeIntroduce)){
				BookType bookType=new BookType();
				bookType.setBookTypeName(bookTypeName);
				bookType.setBookTypeIntroduce(bookTypeIntroduce);
				
				if(new BookTypeService().addBookType(bookType)){
					JOptionPane.showMessageDialog(this, "添加成功!");
					typeIntroduceText.setText("");
					typeNameText.setText("");
				}else{
					JOptionPane.showMessageDialog(this, "添加失败!");
				}
			}else{
				JOptionPane.showMessageDialog(this, "名称或介绍不能为空!");
			}
			
		}else if(e.getSource()==reset){
			typeIntroduceText.setText("");
			typeNameText.setText("");
		}
		
	}
	
	
	

}
