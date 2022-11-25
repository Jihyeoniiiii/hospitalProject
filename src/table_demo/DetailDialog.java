package table_demo;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class DetailDialog extends javax.swing.JDialog {
	String[] itemDetails;
	DefaultTableModel model;
	JTable table;
	JScrollPane scroll;

	String title[] = { "백신종류", "1차", "2차", "3차", "추4차", "추5차", "추6차" };
	String data[][] = { { "BCG" }, { "HepB" }, { "DTaP" }, { "Tdap" }, { "IPV" }, { "Hib" }, { "PCV" }, { "MMR" },
			{ "VAR" }, { "HepA" }, { "IJEV" }, { "LJEV" }, { "RV1" }, { "RV5" } };
	
	JLabel details[] = new JLabel[5];
	DetailDialog(String[] texts) {
		super(GUIMain.mainFrame);
		itemDetails = texts;
	}
	
	void setup() {
			setTitle("예방접종현황");
			
			JPanel pane = new JPanel(new BorderLayout());
			model = new DefaultTableModel(title, 14);
			table = new JTable(model);
			
			scroll = new JScrollPane(table);
			add(scroll);

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					table.setValueAt(data[i][j], i, j); // data값을 i번 행, j번 열에 넣어라
				}
			}
			
			pack();
			setVisible(true);
	}
}
