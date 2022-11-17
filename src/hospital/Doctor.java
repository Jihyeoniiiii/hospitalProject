package hospital;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;
import mgr.Manager;

public class Doctor implements Manageable
{
	//의사코드 의사이름 나이 연락처 성별
	String code;
	String name;
	int age;
	String gender;
	String phone;
	ArrayList<Patient> patientList = new ArrayList<Patient>();
	
	@Override
	public void read(Scanner scan) {
		code = scan.next();
		name = scan.next();
		age = scan.nextInt();
		phone = scan.next();
		gender = scan.next();
	}
	/*
	 [의사코드] 의사이름 : 의사연락처
	 [진료 환자] 환자이름 : 접수내역 - 시간대
	 */
	@Override
	public void print() {
		System.out.format("[%s] %s : %s\n", code, name, phone);
		for(Patient p : patientList)
			p.print();
	}
	//진료 클래스에서 등록할 때 이름 입력 받아서 patient find하여 저장하고,
	//객체를 전송하여 addPatient (10주차 개인과제 Order 클래스 참고)
	void addPatient(Patient pati)
	{
		patientList.add(pati);
	}
	@Override
	public boolean matches(String kwd) {
		if(name.equals(kwd))
			return true;
		for(Patient p : patientList)
		{
			if(p.matches(kwd))
				return true;
		}
		return false;
	}
}
