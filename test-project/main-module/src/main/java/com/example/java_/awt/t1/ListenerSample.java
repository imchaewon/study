package com.example.java_.awt.t1;

import javax.swing.*;
import java.awt.*;

class ListenerSample extends JFrame
{
	ListenerSample()
	{
		this.setTitle("Action 이벤트 리스너 작성");
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//버튼 컴포넌트를 생성하고 ActionListener를 단다.
		JButton btn = new JButton("Action");
		MyActionListener listener = new MyActionListener();
		btn.addActionListener(listener);

		add(btn);//버튼을 컨텐츠팬에 단다.

		this.setSize(300,150);
		this.setVisible(true);
	}
}
