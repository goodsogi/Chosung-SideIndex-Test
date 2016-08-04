package com.example.fastscrolltest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView myListView;
	ArrayList<String> elements;
	HashMap<String, String> values = new HashMap<String, String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// char �񱳰� �ȵǾ� ���ڿ��� ����
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "��");
		values.put("��", "ī");
		values.put("��", "Ÿ");
		values.put("��", "��");
		values.put("��", "��");
		// elements
		String s = "QWERTZUIOPASDFGHJKLYXCVBNM�����ٶ󸶹ٻ����ī������";
		Random r = new Random();
		elements = new ArrayList<String>();
		for (int i = 0; i < 300; i++) {

			elements.add(s.substring(r.nextInt(s.length())));

		}
		Collections.sort(elements); // Must be sorted!

		// listview
		myListView = (ListView) findViewById(R.id.myListView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.list_item, elements);
		myListView.setAdapter(adapter);

	}

	/**
	 * side index Ŭ�� ������
	 * 
	 * @param v
	 */
	public void goToRow(View v) {
		String text = ((TextView) v).getText().toString();
		String tempText = values.get(text);
		int searchPosition = 0;
		for (String s : elements) {

			if (getChosung(tempText) == getChosung(s)) {
				// if (currenttext.charAt(0) == getChosung(s)) {
				searchPosition = elements.indexOf(s);
				break;
			}
		}

		List<String> newElements = elements.subList(searchPosition,
				elements.size() - 1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.list_item, newElements);
		myListView.setAdapter(adapter);

	}

	/**
	 * ���ڿ����� �ʼ� �˻�
	 * 
	 * @param s
	 * @return
	 */
	private char getChosung(String s) {

		// typo��Ʈ���� ���ڼ� ��ŭ list�� ��ƵӴϴ�.
		for (int i = 0; i < s.length(); i++) {
			char comVal = (char) (s.charAt(i) - 0xAC00);

			if (comVal >= 0 && comVal <= 11172) {
				// �ѱ��ϰ��

				// �ʼ��� �Է� ���� �ÿ� �ʼ��� �����ؼ� List�� �߰��մϴ�.
				char uniVal = (char) comVal;

				// �����ڵ� ǥ�� ���߾� �ʼ� �߼� ������ �и��մϴ�..
				char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);

				if (cho != 4519) {
					return cho;
				}

			} else {
				return '��';
			}
		}
		return '��';
	}

}
