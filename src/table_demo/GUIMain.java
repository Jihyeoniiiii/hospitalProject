package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


import hospital.Main;
import hospital.PatientMgr;
import hospital.ReceptionMgr;
import hospital.ReservationMgr;


public class GUIMain {
    // 싱글톤 패턴 적용 부분
    private static GUIMain main = null;
    private GUIMain() {}
    public static GUIMain getInstance() {
        if (main == null)
            main = new GUIMain();
        return main;
    }
    // 엔진의 인스턴스를 편리를 위해 변수에 저장한다
    static Main hospitalMain = Main.getInstance();
    Login login;
    public static void main(String args[]) {
        GUIMain main = new GUIMain();
        main.login = new Login(); // 로그인창 보이기
        main.login.setMain(main); // 로그인창에게 메인 클래스보내기

    }
    public void showFrameTest(){
        login.dispose(); // 로그인창닫기
        hospitalMain.run();
        startGUI();
    }
    public static void startGUI() {
        // 이벤트 처리 스레드를 만들고
        // 거기서 GUI를 생성하고 보여준다.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIMain.getInstance().createAndShowGUI();
            }
        });
    }
    /**
     * GUI를 생성하여 보여준다. 스레드 안전을 위하여
     * 이 메소드는 이벤트 처리 스레드에서 불려져야 한다.
     */
    static JFrame mainFrame = new JFrame("병원 환자 관리 프로그램");
    private void createAndShowGUI() {
        //mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 탭을 생성하고 두개 패널을 추가한다.
        JTabbedPane jtab = new JTabbedPane();

        setupReceptionPane();
        setupPatientPane();
        setupVaccinationPane();
        setupResevationPane();
        // 아이템 리스트 탭과 주문 탭 두 개의 패널을 가지는 탭 패널
        jtab.add("접수", receptionPane);
        jtab.add("환자", patientPane);
        jtab.add("예방접종현황", vaccinationPane);
        jtab.add("예방접종 예약", reservationPane);
        mainFrame.getContentPane().add(jtab);
        //Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    // 접수을 보여주는 패널 부분 - 탑과 JTable 포함
    private JPanel receptionPane;
    TableSelectionDemo receptionTable = new TableSelectionDemo();
    TopPanel receptionTop = new TopPanel();  // 검색과 상세보기 버튼을 가진 패널
    ReceptionDownPanel receptionDown = new ReceptionDownPanel();
    private void setupReceptionPane() {
        receptionPane = new JPanel(new BorderLayout());
        //Create and set up the content pane.
        receptionTable.tableTitle = "reception";
        receptionTable.addComponentsToPane(ReceptionMgr.getInstance());  // 싱글톤
        receptionTop.setupTopPane(receptionTable);
        receptionPane.add(receptionTop, BorderLayout.NORTH);
        receptionPane.add(receptionTable, BorderLayout.CENTER);

        receptionDown.setupDownPane(receptionTable);
        receptionPane.add(receptionDown, BorderLayout.SOUTH);
    }

    // 환자을 보여주는 패널 부분 - 위에는 검색과 JTable, 아래 패널은 장바구니와 버튼
    private JPanel patientPane;
    TableSelectionDemo patientTable = new TableSelectionDemo();
    ReceptionOfPatientTableDemo rListTable = new ReceptionOfPatientTableDemo();
    TopPanel patientTop = new TopPanel();
    PatientDownPanel patientDown = new PatientDownPanel();
    private void setupPatientPane() {
        patientPane = new JPanel(new BorderLayout());
        patientPane.setPreferredSize(new Dimension(720,600));

        patientTable.tableTitle = "patient";
        patientTable.addComponentsToPane(PatientMgr.getInstance());
        //맨 위에는 검색 창
        patientTop.setupTopPane(patientTable);
        patientPane.add(patientTop, BorderLayout.NORTH);

        //맨 아래는 환자 등록 창
        patientDown.setupDownPane(patientTable);
        patientPane.add(patientDown, BorderLayout.SOUTH);

        //가운데 환자 리스트와 진료 기록 테이블 추가
        JPanel center = new JPanel();
        center.add(patientTable, BorderLayout.CENTER);
        rListTable.tableTitle = "receptionList";
        rListTable.addComponentsToPane(ReceptionMgr.getInstance());
        center.add(rListTable, BorderLayout.SOUTH);
        patientPane.add(center, BorderLayout.CENTER);


        // 여기에 여러 가지 버튼을 넣을 수 있음 - 결재, 취소, 추가, 변경 등
        //bottom.add(new JLabel("환자 별 진료기록"), BorderLayout.LINE_END);
    }
    //예방접종 현황 탭
    private JPanel vaccinationPane;
    //TableSelectionDemo VaccinStatusTable = new TableSelectionDemo();
    TableSelectionDemo v_patientTable = new TableSelectionDemo();
    ReceptionOfPatientTableDemo v_rListTable = new ReceptionOfPatientTableDemo();
    TopPanel v_patientTop = new TopPanel();
    private void setupVaccinationPane() {
        //검색
        vaccinationPane = new JPanel(new BorderLayout());
        v_patientTable.tableTitle ="VaccinationStatus";
        v_patientTop.setupTopPane(v_patientTable);
        vaccinationPane.add(v_patientTop, BorderLayout.NORTH);

        v_patientTable.tableTitle = "patient";
        v_patientTable.addComponentsToPane(PatientMgr.getInstance());
        vaccinationPane.add(v_patientTable, BorderLayout.CENTER);

        //JPanel bottom = new JPanel();
        v_rListTable.tableTitle = "receptionList";
        v_rListTable.addComponentsToPane(ReceptionMgr.getInstance());
        //bottom.add(v_rListTable, BorderLayout.CENTER);
        vaccinationPane.add(v_rListTable, BorderLayout.SOUTH);
    }
    //예방접종 예약 탭
    private JPanel reservationPane;
    TableSelectionDemo reservationTable = new TableSelectionDemo();
    ReservationTopPanel reservationTop = new ReservationTopPanel();  // 검색과 상세보기 버튼을 가진 패널
    ReservationDownPanel reservationDown = new ReservationDownPanel();
    private void setupResevationPane() {
        reservationPane = new JPanel(new BorderLayout());
        //Create and set up the content pane.
        reservationTable.tableTitle = "reservation";
        reservationTable.addComponentsToPane(ReservationMgr.getInstance());  // 싱글톤
        reservationTop.setupTopPane(reservationTable);
        reservationPane.add(reservationTop, BorderLayout.NORTH);
        reservationPane.add(reservationTable, BorderLayout.CENTER);

        reservationDown.setupDownPane(reservationTable);
        reservationPane.add(reservationDown, BorderLayout.SOUTH);
    }
}
