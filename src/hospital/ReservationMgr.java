package hospital;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import facade.DataEngineInterface;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class ReservationMgr implements DataEngineInterface {
		private static ReservationMgr mgr = null;
		private ReservationMgr() {}
		public static ReservationMgr getInstance() {
			if (mgr == null)
				mgr = new ReservationMgr();
			return mgr;
		}
		
		private String[] headers = {"날짜", "이름", "나이", "성별", "증상", "의사"};
		@Override
		public String[] getColumnNames() {
			return headers;
		}
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 6;
		}
		@Override
		public void readAll(String filename) {
			// TODO Auto-generated method stub
		}
		@Override
		public List<Manageable> search(String kwd) {
			// TODO Auto-generated method stub
			return Main.reservationMgr.findAll(kwd);
		}
		@Override
		public void addNewItem(String[] uiTexts) {
			// TODO Auto-generated method stub
		}
		@Override
		public void update(String[] uiTexts) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void remove(String kwd) {
			// TODO Auto-generated method stub

		}
}

