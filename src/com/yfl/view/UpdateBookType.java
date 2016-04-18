package com.yfl.view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.yfl.modle.BooTypeModel;
import com.yfl.modle.BookType;
import com.yfl.service.BookTypeService;
import com.yfl.utils.Utils;

public class UpdateBookType extends JDialog implements ActionListener,ListSelectionListener{
	
	/**
	 *BookType查询 修改 删除对话框 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable bookTypeTable;
	private JLabel searchName,bookTypeId,bookTypeName,bookTypeIntroduce;
	private JTextField searchText,bookTypeIdText,bookTypeNameText;
	private JTextArea bookTypeIntroduceText;
	private JButton searchButton,reviseButton,deleteButton;
	private JScrollPane tableJsp;
	private BookType bookType=new BookType();
	private BooTypeModel model=null;
	private BookTypeService bookTypeService=new BookTypeService();
	
	public UpdateBookType(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		Container container=this.getContentPane();
		this.setLayout(null);
		
		searchName=new JLabel("请输入图书类型名称:");
		searchName.setFont(Utils.f2);
		searchName.setBounds(70, 30, 140, 20);
		container.add(searchName);
		
		searchText=new JTextField();
		searchText.setFont(Utils.f2);
		searchText.setBounds(260, 28, 300, 20);
		container.add(searchText);
		
		searchButton=new JButton("查询");
		searchButton.setFont(Utils.f2);
		searchButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/search.png")));
		searchButton.setBounds(610, 22, 90, 30);
		searchButton.addActionListener(this);
		container.add(searchButton);
		
		bookTypeTable=new JTable(new BooTypeModel(bookType));
		//注册监听
		bookTypeTable.getSelectionModel().addListSelectionListener(this);
		//没有滚动条无法显示列名
		tableJsp=new JScrollPane(bookTypeTable);
		tableJsp.setFont(Utils.f2);
		tableJsp.setBounds(30, 80, 710, 250);
		tableJsp.setBorder(BorderFactory.createTitledBorder(""));
		container.add(tableJsp);
		
		bookTypeId=new JLabel("图书类型编号:");
		bookTypeId.setFont(Utils.f2);
		bookTypeId.setBounds(30, 360, 100, 20);
		container.add(bookTypeId);
		
		bookTypeIdText=new JTextField();
		bookTypeId.setFont(Utils.f2);
		bookTypeIdText.setBounds(150, 360, 190, 20);
		bookTypeIdText.setEditable(false);
		container.add(bookTypeIdText);
		
		bookTypeName=new JLabel("图书类型名称:");
		bookTypeName.setFont(Utils.f2);
		bookTypeName.setBounds(420, 360, 100, 20);
		container.add(bookTypeName);
		
		bookTypeNameText=new JTextField();
		bookTypeNameText.setFont(Utils.f2);
		bookTypeNameText.setBounds(550, 360, 190, 20);
		bookTypeNameText.setText("");
		container.add(bookTypeNameText);
		
		bookTypeIntroduce=new JLabel("图书类型介绍:");
		bookTypeIntroduce.setFont(Utils.f2);
		bookTypeIntroduce.setBounds(30, 400, 100, 20);
		container.add(bookTypeIntroduce);
		
		bookTypeIntroduceText=new JTextArea();
		bookTypeIntroduceText.setFont(Utils.f2);
		bookTypeIntroduceText.setBounds(150, 403, 400, 100);
		bookTypeIntroduceText.setBorder(BorderFactory.createTitledBorder(""));
		container.add(bookTypeIntroduceText);
		
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
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("com/yfl/images/manageBookType.png")).getImage());
		this.setSize(780,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==searchButton){
			
			String search=searchText.getText().trim();
			//如果输入不为空就按指定输入查询否则就查询全部
			if(Utils.isEmpty(search)){
				bookType.setBookTypeName(search);
				model=new BooTypeModel(bookType);
				//更新数据模型
				this.bookTypeTable.setModel(model);
			}else{
				this.updateModel();
			}
			
			//修改
		}else if(e.getSource()==reviseButton){
			
			String typeId=bookTypeIdText.getText().trim();
			String typeName=bookTypeNameText.getText().trim();
			String typeIntroduce=bookTypeIntroduceText.getText().trim();
			
			BookType bookType=new BookType();
			
			if(Utils.isEmpty(typeId)&&Utils.isEmpty(typeName)&&Utils.isEmpty(typeIntroduce)){
				bookType.setBookTypeId(typeId);
				bookType.setBookTypeName(typeName);
				bookType.setBookTypeIntroduce(typeIntroduce);
				
				if(bookTypeService.updateBookType(bookType)){
					
					JOptionPane.showMessageDialog(this, "修改成功!");
					this.updateModel();
				}else{
					JOptionPane.showMessageDialog(this, "修改失败!");
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "请选择您要修改的行!");
			}
			
			//删除
		}else if(e.getSource()==deleteButton){
			
			String typeId=bookTypeIdText.getText().trim();
			String typeName=bookTypeNameText.getText().trim();
			String typeIntroduce=bookTypeIntroduceText.getText().trim();
			BookType bookType=new BookType();
			
			if(Utils.isEmpty(typeId)&&Utils.isEmpty(typeName)&&Utils.isEmpty(typeIntroduce)){
				bookType.setBookTypeId(typeId);
				bookType.setBookTypeName(typeName);
				bookType.setBookTypeIntroduce(typeIntroduce);
				
				int i=bookTypeService.deleteBookType(bookType);
				
				if(i==0){
					
					JOptionPane.showMessageDialog(this, "该图书类下有图书不能删除!");
					
				}else if(i==1){
					
					JOptionPane.showMessageDialog(this, "删除成功!");
					this.updateModel();
				
				}else if(i==-1){
					
					JOptionPane.showMessageDialog(this, "删除失败!");
					this.updateModel();
				
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "请选择您要删除的行!");
			}
		}
		
	}
	
	//得到选中行
	public void getSelectRow(int rowNumber){
		
		if(rowNumber!=-1){
			
			bookTypeIdText.setText(bookTypeTable.getValueAt(rowNumber, 0).toString());
			bookTypeNameText.setText(bookTypeTable.getValueAt(rowNumber, 1).toString());
			bookTypeIntroduceText.setText(bookTypeTable.getValueAt(rowNumber, 2).toString());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int rowNumber=bookTypeTable.getSelectedRow();
		this.getSelectRow(rowNumber);
	}
	
	//更新数据模型
	public void updateModel(){
		
		bookType.setBookTypeName(null);
		model=new BooTypeModel(bookType);
		//更新数据模型
		this.bookTypeTable.setModel(model);
		//把输入框的内容置空
		bookTypeIdText.setText("");
		bookTypeNameText.setText("");
		bookTypeIntroduceText.setText("");
	}
	

}
