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
		// char 비교가 안되어 문자열과 매핑
		values.put("ㄱ", "가");
		values.put("ㄴ", "나");
		values.put("ㄷ", "다");
		values.put("ㄹ", "라");
		values.put("ㅁ", "마");
		values.put("ㅂ", "바");
		values.put("ㅅ", "사");
		values.put("ㅇ", "아");
		values.put("ㅈ", "자");
		values.put("ㅋ", "카");
		values.put("ㅌ", "타");
		values.put("ㅍ", "파");
		values.put("ㅎ", "하");
		// elements
		String s = "QWERTZUIOPASDFGHJKLYXCVBNM가나다라마바사아차카차파하";
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
	 * side index 클릭 리스너
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
	 * 문자열에서 초성 검색
	 * 
	 * @param s
	 * @return
	 */
	private char getChosung(String s) {

		// typo스트링의 글자수 만큼 list에 담아둡니다.
		for (int i = 0; i < s.length(); i++) {
			char comVal = (char) (s.charAt(i) - 0xAC00);

			if (comVal >= 0 && comVal <= 11172) {
				// 한글일경우

				// 초성만 입력 했을 시엔 초성은 무시해서 List에 추가합니다.
				char uniVal = (char) comVal;

				// 유니코드 표에 맞추어 초성 중성 종성을 분리합니다..
				char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);

				if (cho != 4519) {
					return cho;
				}

			} else {
				return 'ㄱ';
			}
		}
		return 'ㄱ';
	}

}
