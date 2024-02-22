package com.example.java_.awt.t1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener를 상속받아 Action 리스너를 작성한다.
class MyActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();//선택된 버튼을 알아낸다.

		//버튼의 문자열이 "Action"이면 "액션"으로, 아니면 다시 "Action"으로 바꾼다.
		if(b.getText().equals("Action")) {
			System.out.println(111);
			b.setText("액션");
		}else {
			System.out.println(222);
			b.setText("Action");
		}
	}
}