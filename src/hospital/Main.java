package hospital;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class Main {
	private static Main hospitalMain = null;
	private Main() {}
	public static Main getInstance() {
		if (hospitalMain == null)
			hospitalMain = new Main();
		return hospitalMain;
	}
	Scanner scan = new Scanner(System.in);
	static Manager doctorMgr = new Manager();
	static Manager patientMgr = new Manager();
	static Manager receptionMgr = new Manager();
	static Manager reservationMgr = new Manager();
	public void run()
	{
		doctorMgr.readAll("doctor.txt",new Factory(){
			public Manageable create(){
				return new Doctor();
			}
		});
		System.out.println("\n================= 전체 의사 리스트 =================");
		doctorMgr.printAll();
		patientMgr.readAll("patient.txt",new Factory(){
			public Manageable create(){
				return new Patient();
			}
		});

		receptionMgr.readAll("reception.txt",new Factory(){
			public Manageable create(){
				return new Reception();
			}
		});
		System.out.println("\n================= 전체 환자 리스트 =================");
		patientMgr.printAll();
		System.out.println("\n=============== 전체 접수 리스트 =================");
		receptionMgr.printAll();
	}

	public static void main(String args[]) {
		Main m = new Main();
		m.run();
	}
}