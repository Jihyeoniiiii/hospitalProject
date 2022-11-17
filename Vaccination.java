package hospital;

import java.util.ArrayList;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class Vaccination implements Manageable, UIData {
	// 백신명 감염병(0을받을때까지입력) 횟수 시기(0을받을때까지입력)
	String vaccine;
	ArrayList<String> infections = new ArrayList<>();
	int number;
	ArrayList<String> times = new ArrayList<>();
	
	public void read(Scanner scan) {
		vaccine = scan.next();
		String token = null;
		while (true) {
			token = scan.next();
			if (token.contentEquals("0"))
				break;
			infections.add(token);
		}
		number = scan.nextInt();
		while (true) {
			token = scan.next();
			if (token.contentEquals("0"))
				break;
			times.add(token);
		}
	}
	
	public void print() {
		for (String i : infections)
			System.out.print(i + " ");
		System.out.format("감염병은 %s 백신을 ", vaccine);
		for (String t : times)
			System.out.print(t + " ");
		System.out.print("시기에 접종해야 한다.");
		System.out.println();
	}
	
	public boolean matches(String kwd) {
		if (infections.contains(kwd)) // ""일 때 true
			return true;
		if (vaccine.contains(kwd))
			return true;
		return false;
	}
	
	public boolean matches(String[] kwdArr) {
		for (String kwd : kwdArr) {
			if (!matches(kwd))
				return false;
		}
		return true;
	}

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getUiTexts() {
		String[] texts = new String[4];
        texts[0] = vaccine;
        texts[1] = infections;
        texts[2] = "" + number;
        texts[3] = times;
        return texts;
	}
}
