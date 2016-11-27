import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyClient extends JFrame{
	
	private JTextField Jinput=new JTextField(35);//输入框
	private JButton jbtsort=new JButton("查询");//查询按钮
	private JTextArea youdao=new JTextArea(6,47);//有道查询结果
	private JTextArea baidu=new JTextArea(6,47);//百度查询结果
	private JTextArea biying=new JTextArea(6,47);//必应查询结果
	
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	public static void main(String[] args) {
		new MyClient();
	}
	
	public MyClient() {
		JFrame frame=new JFrame();
		
		//查询栏
		frame.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
		frame.add(new JLabel("请输入单词:"));
		frame.add(Jinput);
		
		jbtsort.setForeground(new Color(0,0,149));
		frame.add(jbtsort);
		
		//显示栏  有道
		frame.add(new JLabel("有道:"));	
		youdao.setLineWrap(true);
		frame.add(youdao);
		
		//显示栏  百度
		frame.add(new JLabel("百度:"));
		baidu.setLineWrap(true);
		frame.add(baidu);
		
		//显示栏  必应
		frame.add(new JLabel("必应:"));
		biying.setLineWrap(true);
		frame.add(biying);
		
		//
		Jinput.addActionListener(new TextAreaListener());
		jbtsort.addActionListener(new ButtonListener());
		
		frame.setTitle("Net Dictionary");
		frame.setSize(600,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		try {
			Socket socket=new Socket("localhost",8000);
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
		}
		catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	private class TextAreaListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				String input=Jinput.getText().trim();
				toServer.writeChars(input);
				toServer.flush();
				
				String result=fromServer.readUTF();
				youdao.setText(result);
				baidu.setText(result);
				biying.setText(result);
			}
			catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				String input=Jinput.getText().trim();
				toServer.writeChars(input);
				toServer.flush();
				
				String result=fromServer.readUTF();
				youdao.setText(result);
				baidu.setText(result);
				biying.setText(result);
			}
			catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}