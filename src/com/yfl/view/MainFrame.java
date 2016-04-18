package com.yfl.view;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.yfl.modle.User;
import com.yfl.utils.Utils;

public class MainFrame extends JFrame implements MouseListener, ActionListener {
	/**
	 * 主界面
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menubar;
	private JMenu manageBookType, manageBook, about;
	private JMenuItem bookType, addBookType, book, addBook, aboutUs, help;
	private JToolBar toolBar;
	private JLabel addBookTypeLable, bookTypeLabel, addBookLabel, bookLabel;

	// 屏幕宽度
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	// 屏幕的高度
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public static void main(String[] args) {

		new MainFrame(new User());
	}

	public MainFrame(User user) {

		menubar = new JMenuBar();

		manageBookType = new JMenu("图书类型管理");
		manageBookType.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/manageBookType.png")));
		manageBookType.setFont(Utils.f2);

		addBookType = new JMenuItem("添加图书类型");
		addBookType.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/add.png")));
		addBookType.setFont(Utils.f2);
		addBookType.addActionListener(this);
		manageBookType.add(addBookType);

		bookType = new JMenuItem("图书类别维护");
		bookType.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/set.png")));
		bookType.setFont(Utils.f2);
		bookType.addActionListener(this);
		manageBookType.add(bookType);

		manageBook = new JMenu("图书管理");
		manageBook.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/manageBook.png")));
		manageBook.setFont(Utils.f2);

		addBook = new JMenuItem("添加图书");
		addBook.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/add.png")));
		addBook.setFont(Utils.f2);
		addBook.addActionListener(this);
		manageBook.add(addBook);

		book = new JMenuItem("图书维护");
		book.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/set.png")));
		book.setFont(Utils.f2);
		book.addActionListener(this);
		manageBook.add(book);

		about = new JMenu("关于");
		about.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/about.png")));
		about.setFont(Utils.f2);

		help = new JMenuItem("帮助");
		help.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/help.png")));
		help.setFont(Utils.f2);
		help.addActionListener(this);
		about.add(help);

		aboutUs = new JMenuItem("关于我们");
		aboutUs.setIcon(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/aboutUs.png")));
		aboutUs.setFont(Utils.f2);
		aboutUs.addActionListener(this);
		about.add(aboutUs);

		menubar.add(manageBookType);
		menubar.add(manageBook);
		menubar.add(about);

		// 工具栏
		toolBar = new JToolBar();
		FlowLayout f = new FlowLayout(FlowLayout.LEFT);
		f.setHgap(30);
		toolBar.setLayout(f);
		toolBar.setFloatable(false);

		addBookTypeLable = new JLabel(new ImageIcon(this.getClass()
				.getClassLoader()
				.getResource("com/yfl/images/addBookTypeLabel.png")));
		addBookTypeLable.setToolTipText("添加图书类别");
		addBookTypeLable.addMouseListener(this);
		toolBar.add(addBookTypeLable);

		bookTypeLabel = new JLabel(new ImageIcon(this.getClass()
				.getClassLoader()
				.getResource("com/yfl/images/bookTypeLabel.png")));
		bookTypeLabel.setToolTipText("图书类别维护");
		bookTypeLabel.addMouseListener(this);
		toolBar.add(bookTypeLabel);

		addBookLabel = new JLabel(new ImageIcon(this.getClass()
				.getClassLoader()
				.getResource("com/yfl/images/addBookLabel.png")));
		addBookLabel.setToolTipText("添加图书");
		addBookLabel.addMouseListener(this);
		toolBar.add(addBookLabel);

		bookLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/bookLabel.png")));
		bookLabel.setToolTipText("添加图书");
		bookLabel.addMouseListener(this);
		toolBar.add(bookLabel);

		BgImage bg = new BgImage();
		bg.setBounds(0, 0, width, height - 30);
		this.add(toolBar, "North");
		this.add(bg);
		this.setJMenuBar(menubar);
		this.setTitle("图书管理系统   当前用户:" + user.getUserName());
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader()
				.getResource("com/yfl/images/tittle.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height - 30);
		this.setVisible(true);
		try {
			// 使用系统分格显示
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 背景图内部类
	class BgImage extends JPanel {

		private static final long serialVersionUID = 1L;

		Image im = null;

		public BgImage() {
			im = new ImageIcon(this.getClass().getClassLoader()
					.getResource("com/yfl/images/bg.jpg")).getImage();

		}

		// 画图片函数
		public void paintComponent(Graphics g) {

			g.drawImage(im, 0, 0, width, height, this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == addBookType || e.getSource() == addBookTypeLable) {
			new AddBookTypeDialog(this, "添加图书类型", true);

		} else if (e.getSource() == bookType || e.getSource() == bookTypeLabel) {
			new UpdateBookType(this, "图书类型维护", true);

		} else if (e.getSource() == addBook || e.getSource() == addBookLabel) {

			new AddBookDialog(this, "添加图书", true);

		} else if (e.getSource() == book || e.getSource() == bookLabel) {

			new UpdateBook(this, "图书维护", true);

		} else if (e.getSource() == aboutUs) {

			JOptionPane.showMessageDialog(this, "作者QQ:617823298,欢迎大家一起交流一起进步.");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 菜单点击事件
		if (e.getSource() == aboutUs) {
			
			JOptionPane.showMessageDialog(this, "作者QQ:617823298,欢迎大家一起交流一起进步.");
			
		} else if (e.getSource() == addBookType) {
			
			new AddBookTypeDialog(this, "添加图书类型", true);
			
		} else if (e.getSource() == bookType) {
			
			new UpdateBookType(this, "图书类型维护", true);
			
		} else if (e.getSource() == addBook) {
			
			new AddBookDialog(this, "添加图书", true);
			
		} else if (e.getSource() == book) {
			
			new UpdateBook(this, "图书维护", true);
		}
	}

}
