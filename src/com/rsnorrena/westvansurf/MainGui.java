package com.rsnorrena.westvansurf;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class MainGui {
	
	//name of the configuration file for log4j
		private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
		
		//static method to read the log4j configuration file and set up in application
		static {
			configureLogging();
		}
		
		//declaration of the log4j logger to be used by the class Main.
		private static final Logger LOG = LogManager.getLogger();
		
		
	// initialize field variables
	private JFrame frame;
	// private final Action action = new SwingAction();
	ArrayList<SavedData> savedDataArrayList;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private boolean dataExists;
	boolean monitorService;
	static final int MAX_RECORDS = 6;
	static final int INDEX_OFFSET = 1;
	static final int INDEX_ZERO = 0;
	boolean goodOkHttpResponse;
	static int sleepInterval;
	boolean windWarning;

	JToggleButton tglbtnNewToggleButton;
	JButton btnClearData;
	JLabel tv1bbb, tv1a, tv1bb, tv1b, tv1c, tv1d, tv1e, tv1f, tv1g, tv1h, tv1i, tv1j, tv1k, tv1l;
	JLabel tv2bbb, tv2a, tv2bb, tv2b, tv2c, tv2d, tv2e, tv2f, tv2g, tv2h, tv2i, tv2j, tv2k, tv2l;
	JLabel tv3bbb, tv3a, tv3bb, tv3b, tv3c, tv3d, tv3e, tv3f, tv3g, tv3h, tv3i, tv3j, tv3k, tv3l;
	JLabel tv4bbb, tv4a, tv4bb, tv4b, tv4c, tv4d, tv4e, tv4f, tv4g, tv4h, tv4i, tv4j, tv4k, tv4l;
	JLabel tv5bbb, tv5a, tv5bb, tv5b, tv5c, tv5d, tv5e, tv5f, tv5g, tv5h, tv5i, tv5j, tv5k, tv5l;
	JLabel tv6bbb, tv6a, tv6bb, tv6b, tv6c, tv6d, tv6e, tv6f, tv6g, tv6h, tv6i, tv6j, tv6k, tv6l;

	JLabel lbll = new JLabel(
			"...............................................................................................................................................................................");
	JLabel lblNewLabel_1 = new JLabel("West Vancouver Surf Report");
	JLabel lblWind = new JLabel("Wind Warning");
	JLabel label = new JLabel(
			"...............................................................................................................................................................................");
	JLabel lblWindDirection = new JLabel("Wind Direction:");
	JLabel label_23 = new JLabel("Time:");
	JLabel lblFt_5 = new JLabel("1 ft  --");
	JLabel lblFt_4 = new JLabel("2 ft  --");
	JLabel lblFt_3 = new JLabel("3 ft  --");
	JLabel lblFt_2 = new JLabel("4 ft  --");
	JLabel lblFt = new JLabel("5 ft  --");
	JLabel lblM = new JLabel(" 1 m  --");
	JLabel label_67 = new JLabel("");
	JLabel label_75 = new JLabel("");
	JLabel label_83 = new JLabel("");
	JLabel label_91 = new JLabel("");
	JLabel label_74 = new JLabel("");
	private final JLabel background_image = new JLabel("New label");
	JLabel tvWindSpeed, tvWindDir, tvWindWarning, tvWaterTemp, tvAirTemp, lblCurrentReport, lblAirTemperature,
			lblWind_1, lblWaveheight, tvWaveInterval;

	// the main method is the entry point for the application
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		LOG.info("The main method is called");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// call to the constructor for the maingui
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void configureLogging() {
		// TODO Auto-generated method stub
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);

		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
		
	}

	// constructor for the maingui class
	public MainGui() throws ClassNotFoundException, IOException {
		LOG.info("The MainGui constructor is called");
		// call to initialize jtags, buttons and field variables
		initialize();
	}

	// method to initialize UI elements
	private void initialize() throws ClassNotFoundException, IOException {

		// method to check if the tray icon is supported in the host os then
		// initialize.
		addTrayIcon();

		// initialize of the arraylist for the saved Halibank bank data
		savedDataArrayList = new ArrayList<SavedData>();

		// initialize the application frame
		frame = new JFrame();
		// method to add the application icon
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/res/images/wvs32.jpg")));
		frame.getContentPane().setBackground(new Color(0, 153, 153));
		// first two params are x y position of the top left corner of the
		// window. The seconds two parameters are the size of the window
		frame.setBounds(100, 100, 550, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// sets the application frame to be a permanent size
		frame.setResizable(false);
		// method to centre the window on the screen
		frame.setLocationRelativeTo(null);

		// initialization of the JTags
		JLabel lblFt_1 = new JLabel("");
		lblFt_1.setBounds(69, 292, 46, 14);
		frame.getContentPane().add(lblFt_1);

		tv1bbb = new JLabel("1bbb");
		tv1bbb.setForeground(Color.WHITE);
		tv1bbb.setBounds(148, 452, 46, 14);
		frame.getContentPane().add(tv1bbb);

		tv2bbb = new JLabel("2bbb");
		tv2bbb.setForeground(Color.WHITE);
		tv2bbb.setBounds(204, 452, 46, 14);
		frame.getContentPane().add(tv2bbb);

		tv3bbb = new JLabel("3bbb");
		tv3bbb.setForeground(Color.WHITE);
		tv3bbb.setBounds(260, 452, 46, 14);
		frame.getContentPane().add(tv3bbb);

		tv4bbb = new JLabel("4bbb");
		tv4bbb.setForeground(Color.WHITE);
		tv4bbb.setBounds(316, 452, 46, 14);
		frame.getContentPane().add(tv4bbb);

		tv5bbb = new JLabel("5bbb");
		tv5bbb.setForeground(Color.WHITE);
		tv5bbb.setBounds(371, 452, 46, 14);
		frame.getContentPane().add(tv5bbb);

		tv6bbb = new JLabel("6bbb");
		tv6bbb.setForeground(Color.WHITE);
		tv6bbb.setBounds(427, 452, 46, 14);
		frame.getContentPane().add(tv6bbb);

		tv1a = new JLabel("1a");
		tv1a.setBounds(148, 490, 46, 14);
		tv1a.setForeground(Color.WHITE);
		tv1a.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(tv1a);

		tv1b = new JLabel("1b");
		tv1b.setBounds(148, 402, 46, 14);
		tv1b.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1b);

		tv1c = new JLabel("1c");
		tv1c.setBounds(148, 377, 46, 14);
		tv1c.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1c);

		tv1d = new JLabel("1d");
		tv1d.setBounds(148, 352, 46, 14);
		tv1d.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1d);

		tv1e = new JLabel("1e");
		tv1e.setBounds(148, 327, 46, 14);
		tv1e.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1e);

		tv1f = new JLabel("1f");
		tv1f.setBounds(148, 302, 46, 14);
		tv1f.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1f);

		tv1g = new JLabel("1g");
		tv1g.setBounds(148, 277, 46, 14);
		tv1g.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1g);

		tv1h = new JLabel("1h");
		tv1h.setBounds(148, 252, 46, 14);
		tv1h.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1h);

		tv1i = new JLabel("1i");
		tv1i.setBounds(148, 227, 46, 14);
		tv1i.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1i);

		tv1j = new JLabel("1j");
		tv1j.setBounds(148, 202, 46, 14);
		frame.getContentPane().add(tv1j);

		tv1k = new JLabel("1k");
		tv1k.setBounds(148, 163, 46, 14);
		tv1k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv1k.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1k);

		tv1l = new JLabel("1l");
		tv1l.setBounds(148, 138, 46, 14);
		tv1l.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tv1l.setForeground(Color.WHITE);
		frame.getContentPane().add(tv1l);

		tv1bb = new JLabel("1bb");
		tv1bb.setForeground(Color.WHITE);
		tv1bb.setBounds(148, 427, 46, 14);
		frame.getContentPane().add(tv1bb);

		tv2bb = new JLabel("2bb");
		tv2bb.setForeground(Color.WHITE);
		tv2bb.setBounds(204, 427, 46, 14);
		frame.getContentPane().add(tv2bb);

		tv3bb = new JLabel("3bb");
		tv3bb.setForeground(Color.WHITE);
		tv3bb.setBounds(260, 427, 46, 14);
		frame.getContentPane().add(tv3bb);

		tv4bb = new JLabel("4bb");
		tv4bb.setForeground(Color.WHITE);
		tv4bb.setBounds(316, 427, 46, 14);
		frame.getContentPane().add(tv4bb);

		tv5bb = new JLabel("5bb");
		tv5bb.setForeground(Color.WHITE);
		tv5bb.setBounds(371, 427, 46, 14);
		frame.getContentPane().add(tv5bb);

		tv6bb = new JLabel("6bb");
		tv6bb.setForeground(Color.WHITE);
		tv6bb.setBounds(427, 427, 46, 14);
		frame.getContentPane().add(tv6bb);

		tv2a = new JLabel("2a");
		tv2a.setBounds(204, 490, 46, 14);
		tv2a.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv2a.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2a);

		tv2b = new JLabel("2b");
		tv2b.setBounds(204, 402, 46, 14);
		tv2b.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2b);

		tv2c = new JLabel("2c");
		tv2c.setBounds(204, 377, 46, 14);
		tv2c.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2c);

		tv2d = new JLabel("2d");
		tv2d.setBounds(204, 352, 46, 14);
		tv2d.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2d);

		tv2e = new JLabel("2e");
		tv2e.setBounds(204, 327, 46, 14);
		tv2e.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2e);

		tv2f = new JLabel("2f");
		tv2f.setBounds(204, 302, 46, 14);
		tv2f.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2f);

		tv2g = new JLabel("2g");
		tv2g.setBounds(204, 277, 46, 14);
		tv2g.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2g);

		tv2h = new JLabel("2h");
		tv2h.setBounds(204, 252, 46, 14);
		frame.getContentPane().add(tv2h);
		tv2h.setForeground(Color.WHITE);

		tv2i = new JLabel("2i");
		tv2i.setBounds(204, 227, 46, 14);
		tv2i.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2i);

		tv2j = new JLabel("2j");
		tv2j.setBounds(204, 202, 46, 14);
		tv2j.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2j);

		tv2k = new JLabel("2k");
		tv2k.setBounds(204, 163, 46, 14);
		tv2k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv2k.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2k);

		tv2l = new JLabel("2l");
		tv2l.setBounds(204, 138, 46, 14);
		tv2l.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tv2l.setForeground(Color.WHITE);
		frame.getContentPane().add(tv2l);

		tv3a = new JLabel("3a");
		tv3a.setBounds(260, 490, 46, 14);
		tv3a.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv3a.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3a);

		tv3b = new JLabel("3b");
		tv3b.setBounds(260, 402, 46, 14);
		tv3b.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3b);

		tv3c = new JLabel("3c");
		tv3c.setBounds(260, 377, 46, 14);
		tv3c.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3c);

		tv3d = new JLabel("3d");
		tv3d.setBounds(260, 352, 46, 14);
		tv3d.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3d);

		tv3e = new JLabel("3e");
		tv3e.setBounds(260, 327, 46, 14);
		tv3e.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3e);

		tv3f = new JLabel("3f");
		tv3f.setBounds(260, 302, 46, 14);
		tv3f.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3f);

		tv3g = new JLabel("3g");
		tv3g.setBounds(260, 277, 46, 14);
		tv3g.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3g);

		tv3h = new JLabel("3h");
		tv3h.setBounds(260, 252, 46, 14);
		tv3h.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3h);

		tv3i = new JLabel("3i");
		tv3i.setBounds(260, 227, 46, 14);
		tv3i.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3i);

		tv3j = new JLabel("3j");
		tv3j.setBounds(260, 202, 46, 14);
		tv3j.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3j);

		tv3k = new JLabel("3k");
		tv3k.setBounds(260, 163, 46, 14);
		tv3k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv3k.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3k);

		tv3l = new JLabel("3l");
		tv3l.setBounds(260, 138, 46, 14);
		tv3l.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tv3l.setForeground(Color.WHITE);
		frame.getContentPane().add(tv3l);

		tv4a = new JLabel("4a");
		tv4a.setBounds(316, 490, 46, 14);
		tv4a.setForeground(Color.WHITE);
		tv4a.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(tv4a);

		tv4b = new JLabel("4b");
		tv4b.setBounds(316, 402, 46, 14);
		tv4b.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4b);

		tv4c = new JLabel("4c");
		tv4c.setBounds(316, 377, 46, 14);
		tv4c.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4c);

		tv4d = new JLabel("4d");
		tv4d.setBounds(316, 352, 46, 14);
		tv4d.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4d);

		tv4e = new JLabel("4e");
		tv4e.setBounds(316, 327, 46, 14);
		tv4e.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4e);

		tv4f = new JLabel("4f");
		tv4f.setBounds(316, 302, 46, 14);
		tv4f.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4f);

		tv4g = new JLabel("4g");
		tv4g.setBounds(316, 277, 46, 14);
		tv4g.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4g);

		tv4h = new JLabel("4h");
		tv4h.setBounds(316, 252, 46, 14);
		tv4h.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4h);

		tv4i = new JLabel("4i");
		tv4i.setBounds(316, 227, 46, 14);
		tv4i.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4i);

		tv4j = new JLabel("4j");
		tv4j.setBounds(316, 202, 46, 14);
		tv4j.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4j);

		tv4k = new JLabel("4k");
		tv4k.setBounds(316, 163, 46, 14);
		tv4k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv4k.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4k);

		tv4l = new JLabel("4l");
		tv4l.setBounds(316, 138, 46, 14);
		tv4l.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tv4l.setForeground(Color.WHITE);
		frame.getContentPane().add(tv4l);

		tv5a = new JLabel("5a");
		tv5a.setBounds(371, 490, 46, 14);
		tv5a.setForeground(Color.WHITE);
		tv5a.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(tv5a);

		tv5b = new JLabel("5b");
		tv5b.setBounds(371, 402, 46, 14);
		tv5b.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5b);

		tv5c = new JLabel("5c");
		tv5c.setBounds(371, 377, 46, 14);
		tv5c.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5c);

		tv5d = new JLabel("5d");
		tv5d.setBounds(371, 352, 46, 14);
		tv5d.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5d);

		tv5e = new JLabel("5e");
		tv5e.setBounds(371, 327, 46, 14);
		tv5e.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5e);

		tv5f = new JLabel("5f");
		tv5f.setBounds(371, 302, 46, 14);
		tv5f.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5f);

		tv5g = new JLabel("5g");
		tv5g.setBounds(371, 277, 46, 14);
		tv5g.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5g);

		tv5h = new JLabel("5h");
		tv5h.setBounds(371, 252, 46, 14);
		tv5h.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5h);

		tv5i = new JLabel("5i");
		tv5i.setBounds(371, 227, 46, 14);
		tv5i.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5i);

		tv5j = new JLabel("5j");
		tv5j.setBounds(371, 202, 46, 14);
		tv5j.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5j);

		tv5k = new JLabel("5k");
		tv5k.setBounds(371, 163, 46, 14);
		tv5k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv5k.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5k);

		tv5l = new JLabel("5l");
		tv5l.setBounds(371, 138, 46, 14);
		tv5l.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tv5l.setForeground(Color.WHITE);
		frame.getContentPane().add(tv5l);

		tv6a = new JLabel("6a");
		tv6a.setBounds(427, 490, 46, 14);
		tv6a.setForeground(Color.WHITE);
		tv6a.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(tv6a);

		tv6b = new JLabel("6b");
		tv6b.setBounds(427, 402, 46, 14);
		tv6b.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6b);

		tv6c = new JLabel("6c");
		tv6c.setBounds(427, 377, 46, 14);
		tv6c.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6c);

		tv6d = new JLabel("6d");
		tv6d.setBounds(427, 352, 46, 14);
		tv6d.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6d);

		tv6e = new JLabel("6e");
		tv6e.setBounds(427, 327, 46, 14);
		tv6e.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6e);

		tv6f = new JLabel("6f");
		tv6f.setBounds(427, 302, 46, 14);
		tv6f.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6f);

		tv6g = new JLabel("6g");
		tv6g.setBounds(427, 277, 46, 14);
		tv6g.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6g);

		tv6h = new JLabel("6h");
		tv6h.setBounds(427, 252, 46, 14);
		tv6h.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6h);

		tv6i = new JLabel("6i");
		tv6i.setBounds(427, 227, 46, 14);
		tv6i.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6i);

		tv6j = new JLabel("6j");
		tv6j.setBounds(427, 202, 46, 14);
		tv6j.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6j);

		tv6k = new JLabel("6k");
		tv6k.setBounds(427, 163, 46, 14);
		tv6k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tv6k.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6k);

		tv6l = new JLabel("6l");
		tv6l.setBounds(427, 138, 46, 14);
		tv6l.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tv6l.setForeground(Color.WHITE);
		frame.getContentPane().add(tv6l);

		JLabel label_75 = new JLabel("");
		label_75.setBounds(69, 242, 46, 14);
		frame.getContentPane().add(label_75);

		JLabel lbll = new JLabel(
				"...............................................................................................................................................................................");
		lbll.setForeground(Color.WHITE);
		lbll.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbll.setBounds(10, 512, 514, 14);
		frame.getContentPane().add(lbll);

		JLabel lblNewLabel_1 = new JLabel("West Vancouver Surf Report");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(148, 27, 258, 23);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel label = new JLabel(
				"...............................................................................................................................................................................");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 113, 514, 14);
		frame.getContentPane().add(label);

		JLabel lblWindDirection = new JLabel("Wind Direction:");
		lblWindDirection.setForeground(Color.WHITE);
		lblWindDirection.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWindDirection.setBounds(28, 138, 110, 14);
		frame.getContentPane().add(lblWindDirection);

		JLabel label_67 = new JLabel("");
		label_67.setBounds(69, 267, 46, 14);
		frame.getContentPane().add(label_67);

		JLabel label_83 = new JLabel("");
		label_83.setBounds(69, 217, 46, 14);
		frame.getContentPane().add(label_83);

		JLabel label_91 = new JLabel("");
		label_91.setBounds(69, 188, 46, 14);
		frame.getContentPane().add(label_91);
		tv1j.setForeground(Color.WHITE);

		JLabel label_74 = new JLabel("");
		label_74.setBounds(69, 163, 46, 14);
		frame.getContentPane().add(label_74);

		JLabel label_23 = new JLabel("Time:");
		label_23.setForeground(Color.WHITE);
		label_23.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_23.setBounds(28, 490, 87, 14);
		frame.getContentPane().add(label_23);

		JLabel lblFt_5 = new JLabel("1 ft  --");
		lblFt_5.setForeground(Color.WHITE);
		lblFt_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFt_5.setBounds(69, 402, 46, 14);
		frame.getContentPane().add(lblFt_5);

		JLabel lblFt_4 = new JLabel("2 ft  --");
		lblFt_4.setForeground(Color.WHITE);
		lblFt_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFt_4.setBounds(69, 352, 46, 14);
		frame.getContentPane().add(lblFt_4);

		JLabel lblFt_3 = new JLabel("3 ft  --");
		lblFt_3.setForeground(Color.WHITE);
		lblFt_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFt_3.setBounds(69, 302, 46, 14);
		frame.getContentPane().add(lblFt_3);

		JLabel lblFt_2 = new JLabel("4 ft  --");
		lblFt_2.setForeground(Color.WHITE);
		lblFt_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFt_2.setBounds(69, 252, 46, 14);
		frame.getContentPane().add(lblFt_2);

		JLabel lblFt = new JLabel("5 ft  --");
		lblFt.setForeground(Color.WHITE);
		lblFt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFt.setBounds(69, 202, 46, 14);
		frame.getContentPane().add(lblFt);

		JLabel lblM = new JLabel(" 1 m  --");
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblM.setBounds(28, 277, 55, 14);
		frame.getContentPane().add(lblM);

		tvWindSpeed = new JLabel("wind");
		tvWindSpeed.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tvWindSpeed.setForeground(Color.WHITE);

		tvWindDir = new JLabel("wd");
		tvWindDir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tvWindDir.setForeground(Color.WHITE);

		tvWindWarning = new JLabel(" No wind warning in effect");
		tvWindWarning.setBounds(205, 64, 157, 14);
		tvWindWarning.setForeground(Color.WHITE);
		frame.getContentPane().add(tvWindWarning);

		tvAirTemp = new JLabel("temp");
		tvAirTemp.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tvAirTemp.setForeground(Color.WHITE);

		lblAirTemperature = new JLabel("Water temp:");
		lblAirTemperature.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAirTemperature.setForeground(Color.WHITE);

		JLabel lblCurrentConditions = new JLabel("Current conditions:");
		lblCurrentConditions.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCurrentConditions.setForeground(Color.WHITE);

		tvWaterTemp = new JLabel("temp");
		tvWaterTemp.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tvWaterTemp.setForeground(Color.WHITE);

		lblWind_1 = new JLabel("Wind:");
		lblWind_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWind_1.setForeground(Color.WHITE);

		JLabel lblWaveInterval = new JLabel("Waves:");
		lblWaveInterval.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWaveInterval.setForeground(Color.WHITE);

		lblWaveheight = new JLabel("wave_height");
		lblWaveheight.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWaveheight.setForeground(Color.WHITE);

		tvWaveInterval = new JLabel("wave");
		tvWaveInterval.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tvWaveInterval.setForeground(Color.WHITE);

		lblCurrentReport = new JLabel("Air temp:");
		lblCurrentReport.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCurrentReport.setForeground(Color.WHITE);

		// button to start the monitoring service thread
		tglbtnNewToggleButton = new JToggleButton("Monitor Start");

		tglbtnNewToggleButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		tglbtnNewToggleButton.setBackground(Color.BLACK);
		tglbtnNewToggleButton.setForeground(Color.WHITE);

		tglbtnNewToggleButton.setToolTipText("Start or stop monitoring");

		tglbtnNewToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					tglbtnNewToggleButton.setForeground(Color.BLACK);
					tglbtnNewToggleButton.setText("Monitor Stop");
					// method call to start the monitoring service
					startMonitorService();
				} else if (arg0.getStateChange() == ItemEvent.DESELECTED) {
					tglbtnNewToggleButton.setBackground(Color.BLACK);
					tglbtnNewToggleButton.setForeground(Color.WHITE);
					tglbtnNewToggleButton.setText("Monitor Start");
					// boolean to control the monitoring service in its own
					// thread
					monitorService = false;
				}
			}

			// starts the monitoring service thread if the monitorService
			// boolean is false
			private void startMonitorService() {
				if (!monitorService) {
					System.out.println("Start monitor service called");
					// thread t is a instance of the period checker class
					Thread t = new PeriodicChecker(MainGui.this);
					// call of the start method of the thread class instance t.
					t.start();
				}

			}
		});

		// button to clear contents of all JTags in the main display
		btnClearData = new JButton("Clear Data");
		btnClearData.setBackground(Color.BLACK);
		btnClearData.setForeground(Color.WHITE);
		btnClearData.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnClearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// method to clear the arraylist of saved data
				savedDataArrayList.clear();
				// call to the method the clear the display screen
				clearScreen();
				// method to delete the saved data in the local file system
				try {

					File file = new File("savedData");

					if (file.delete()) {
						System.out.println(file.getName() + " is deleted!");
					} else {
						System.out.println("Delete operation is failed.");
					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(118, 537, 306, 23);
		horizontalBox.setBackground(new Color(0, 0, 0, 0));
		horizontalBox.add(tglbtnNewToggleButton);
		Component horizontalStrut = Box.createHorizontalStrut(100);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(btnClearData);
		frame.getContentPane().add(horizontalBox);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBounds(17, 89, 509, 23);
		horizontalBox_1.add(lblCurrentConditions);
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_1);
		horizontalBox_1.add(lblCurrentReport);
		Component horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalBox_1.add(horizontalStrut_7);
		horizontalBox_1.add(tvAirTemp);
		Component horizontalStrut_2 = Box.createHorizontalStrut(15);
		horizontalBox_1.add(horizontalStrut_2);
		horizontalBox_1.add(lblAirTemperature);
		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_3);
		horizontalBox_1.add(tvWaterTemp);
		Component horizontalStrut_4 = Box.createHorizontalStrut(15);
		horizontalBox_1.add(horizontalStrut_4);
		horizontalBox_1.add(lblWind_1);
		Component horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_6);
		horizontalBox_1.add(tvWindSpeed);
		Component horizontalStrut_11 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_11);
		horizontalBox_1.add(tvWindDir);
		Component horizontalStrut_10 = Box.createHorizontalStrut(15);
		horizontalBox_1.add(horizontalStrut_10);
		horizontalBox_1.add(lblWaveInterval);
		Component horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_5);
		horizontalBox_1.add(lblWaveheight);
		Component horizontalStrut_9 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_9);
		horizontalBox_1.add(tvWaveInterval);
		frame.getContentPane().add(horizontalBox_1);

		// set of the background image in the application frame.
		background_image.setIcon(new ImageIcon(MainGui.class.getResource("/res/images/wvs.jpg")));
		background_image.setBounds(0, -1, 544, 571);
		frame.getContentPane().add(background_image);
		
		// check for previously saved data
		checkForSavedData();
		
		//Method to seed data for testing purpose.
		 savedDataArrayList = new LoadData().getLoadData();// used to load
//		 test data
		 updateDisplay();

	}

	// method to add a tray icon if compatible with the host os
	private void addTrayIcon() {
		// return if the os does not support the tray icon
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}

		// initialize the pop up menu tray icon and system tray objects
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon(createImage("/res/images/wvs16.jpg", "tray icon"));
		final SystemTray tray = SystemTray.getSystemTray();

		// create the exist menu item and add it to the popup menu
		MenuItem exitItem = new MenuItem("Exit");
		popup.add(exitItem);

		// add of the popup menu to the trayicon
		trayIcon.setPopupMenu(popup);

		// add of an event listener and action to the exit item in the exit menu
		// item in the popup menu attached to the trayicon
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});

		try {
			// add of the tray icon to the system tray
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}

	}

	// method retrieves an image from the system for the tray icon
	protected static Image createImage(String path, String description) {
		URL imageURL = MainGui.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

	// method to check for saved data when the program is first launched
	private void checkForSavedData() throws IOException, ClassNotFoundException {
		LOG.info("Checking for saved data");
		try {
			fis = new FileInputStream("savedData");
			// boolean is set to true if the savedData file exists in the file
			// system
			dataExists = true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		if (dataExists) {
			try {
				ois = new ObjectInputStream(fis);
				savedDataArrayList = (ArrayList<SavedData>) ois.readObject();
				System.out.println("Data files exists. Go ahead and update display");
				updateDisplay();
				ois.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	// method to update the main graphical user interface
	void updateDisplay() {
		LOG.info("Update display has been called.");

		clearScreen();
		// retrieves the index of the most recent saved data file
		int lastReportTimeStamp = savedDataArrayList.size() - INDEX_OFFSET;
		// retrieves the most recent data file
		SavedData lastReport = savedDataArrayList.get(lastReportTimeStamp);
		// prints out data from the last report to the main display
		tvAirTemp.setText(String.valueOf(lastReport.getAirTemperatureInCelsius()) + "\u00b0");
		tvWaterTemp.setText(String.valueOf(lastReport.getWaterTemperatureInCelsius()) + "\u00b0");
		tvWaveInterval.setText(String.valueOf(lastReport.getWavePeriodInSeconds()) + " seconds");
		lblWaveheight.setText(String.valueOf(lastReport.getWaveHeightInFeet()) + " ft  @");
		tvWindSpeed.setText(String.valueOf(lastReport.getWindSpeedInKnots()) + " kt");
		tvWindDir.setText(lastReport.getWindDirectionLetters());

		// initialize report data variables
		int time = 0;
		String windDirection = "";
		String windDegress = "";
		double windSpeed = 0.0;
		double waveHeight = 0.0;
		// int wavePeriod = 0;

		// loop through the arraylist and output saved data to the main display
		// starting with column one.
		int column = 1;
		for (int i = 0; i < savedDataArrayList.size(); i++) {
			SavedData savedData = savedDataArrayList.get(i);
			time = savedData.getHour();

			windDirection = savedData.getWindDirectionLetters();
			windDegress = savedData.getWindDirectionDegrees();
			windSpeed = savedData.getWindSpeedInKnots();
			waveHeight = savedData.getWaveHeightInFeet();

			if (column == 1) {
				tv1a.setText(String.valueOf(time + ":00"));
				tv1k.setText(windDegress + "\u00b0");
				tv1l.setText(windDirection);
				if (waveHeight < 1.0) {
					tv1bb.setText(String.valueOf(windSpeed + " kt"));
					tv1bbb.setText("*");
				} else if (waveHeight >= 1.0 && waveHeight < 1.5) {
					tv1b.setText(String.valueOf(windSpeed + " kt"));
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 1.5 && waveHeight < 2.0) {
					tv1c.setText(String.valueOf(windSpeed + " kt"));
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 2.0 && waveHeight < 2.5) {
					tv1d.setText(String.valueOf(windSpeed + " kt"));
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 2.5 && waveHeight < 3.0) {
					tv1e.setText(String.valueOf(windSpeed + " kt"));
					tv1d.setText("*");
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 3.0 && waveHeight < 3.5) {
					tv1f.setText(String.valueOf(windSpeed + " kt"));
					tv1e.setText("*");
					tv1d.setText("*");
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 3.5 && waveHeight < 4.0) {
					tv1g.setText(String.valueOf(windSpeed + " kt"));
					tv1f.setText("*");
					tv1e.setText("*");
					tv1d.setText("*");
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 4.0 && waveHeight < 4.5) {
					tv1h.setText(String.valueOf(windSpeed + " kt"));
					tv1g.setText("*");
					tv1f.setText("*");
					tv1e.setText("*");
					tv1d.setText("*");
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else if (waveHeight >= 4.5 && waveHeight < 5.0) {
					tv1i.setText(String.valueOf(windSpeed + " kt"));
					tv1h.setText("*");
					tv1g.setText("*");
					tv1f.setText("*");
					tv1e.setText("*");
					tv1d.setText("*");
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				} else {
					tv1j.setText(String.valueOf(windSpeed + " kt"));
					tv1i.setText("*");
					tv1h.setText("*");
					tv1g.setText("*");
					tv1f.setText("*");
					tv1e.setText("*");
					tv1d.setText("*");
					tv1c.setText("*");
					tv1b.setText("*");
					tv1bb.setText("*");
					tv1bbb.setText("*");
				}
			}
			if (column == 2) {
				tv2a.setText(String.valueOf(time + ":00"));
				tv2k.setText(windDegress + "\u00b0");
				tv2l.setText(windDirection);
				if (waveHeight < 1.0) {
					tv2bb.setText(String.valueOf(windSpeed + " kt"));
					tv2bbb.setText("*");
				} else if (waveHeight >= 1.0 && waveHeight < 1.5) {
					tv2b.setText(String.valueOf(windSpeed + " kt"));
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 1.5 && waveHeight < 2.0) {
					tv2c.setText(String.valueOf(windSpeed + " kt"));
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 2.0 && waveHeight < 2.5) {
					tv2d.setText(String.valueOf(windSpeed + " kt"));
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 2.5 && waveHeight < 3.0) {
					tv2e.setText(String.valueOf(windSpeed + " kt"));
					tv2d.setText("*");
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 3.0 && waveHeight < 3.5) {
					tv2f.setText(String.valueOf(windSpeed + " kt"));
					tv2e.setText("*");
					tv2d.setText("*");
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 3.5 && waveHeight < 4.0) {
					tv2g.setText(String.valueOf(windSpeed + " kt"));
					tv2f.setText("*");
					tv2e.setText("*");
					tv2d.setText("*");
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 4.0 && waveHeight < 4.5) {
					tv2h.setText(String.valueOf(windSpeed + " kt"));
					tv2g.setText("*");
					tv2f.setText("*");
					tv2e.setText("*");
					tv2d.setText("*");
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else if (waveHeight >= 4.5 && waveHeight < 5.0) {
					tv2i.setText(String.valueOf(windSpeed + " kt"));
					tv2h.setText("*");
					tv2g.setText("*");
					tv2f.setText("*");
					tv2e.setText("*");
					tv2d.setText("*");
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				} else {
					tv2j.setText(String.valueOf(windSpeed + " kt"));
					tv2i.setText("*");
					tv2h.setText("*");
					tv2g.setText("*");
					tv2f.setText("*");
					tv2e.setText("*");
					tv2d.setText("*");
					tv2c.setText("*");
					tv2b.setText("*");
					tv2bb.setText("*");
					tv2bbb.setText("*");
				}
			}
			if (column == 3) {
				tv3a.setText(String.valueOf(time + ":00"));
				tv3k.setText(windDegress + "\u00b0");
				tv3l.setText(windDirection);
				if (waveHeight < 1.0) {
					tv3bb.setText(String.valueOf(windSpeed + " kt"));
					tv3bbb.setText("*");
				} else if (waveHeight >= 1.0 && waveHeight < 1.5) {
					tv3b.setText(String.valueOf(windSpeed + " kt"));
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 1.5 && waveHeight < 2.0) {
					tv3c.setText(String.valueOf(windSpeed + " kt"));
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 2.0 && waveHeight < 2.5) {
					tv3d.setText(String.valueOf(windSpeed + " kt"));
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 2.5 && waveHeight < 3.0) {
					tv3e.setText(String.valueOf(windSpeed + " kt"));
					tv3d.setText("*");
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 3.0 && waveHeight < 3.5) {
					tv3f.setText(String.valueOf(windSpeed + " kt"));
					tv3e.setText("*");
					tv3d.setText("*");
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 3.5 && waveHeight < 4.0) {
					tv3g.setText(String.valueOf(windSpeed + " kt"));
					tv3f.setText("*");
					tv3e.setText("*");
					tv3d.setText("*");
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 4.0 && waveHeight < 4.5) {
					tv3h.setText(String.valueOf(windSpeed + " kt"));
					tv3g.setText("*");
					tv3f.setText("*");
					tv3e.setText("*");
					tv3d.setText("*");
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else if (waveHeight >= 4.5 && waveHeight < 5.0) {
					tv3i.setText(String.valueOf(windSpeed + " kt"));
					tv3h.setText("*");
					tv3g.setText("*");
					tv3f.setText("*");
					tv3e.setText("*");
					tv3d.setText("*");
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				} else {
					tv3j.setText(String.valueOf(windSpeed + " kt"));
					tv3i.setText("*");
					tv3h.setText("*");
					tv3g.setText("*");
					tv3f.setText("*");
					tv3e.setText("*");
					tv3d.setText("*");
					tv3c.setText("*");
					tv3b.setText("*");
					tv3bb.setText("*");
					tv3bbb.setText("*");
				}
			}
			if (column == 4) {
				tv4a.setText(String.valueOf(time + ":00"));
				tv4k.setText(windDegress + "\u00b0");
				tv4l.setText(windDirection);
				if (waveHeight < 1.0) {
					tv4bb.setText(String.valueOf(windSpeed + " kt"));
					tv4bbb.setText("*");
				} else if (waveHeight >= 1.0 && waveHeight < 1.5) {
					tv4b.setText(String.valueOf(windSpeed + " kt"));
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 1.5 && waveHeight < 2.0) {
					tv4c.setText(String.valueOf(windSpeed + " kt"));
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 2.0 && waveHeight < 2.5) {
					tv4d.setText(String.valueOf(windSpeed + " kt"));
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 2.5 && waveHeight < 3.0) {
					tv4e.setText(String.valueOf(windSpeed + " kt"));
					tv4d.setText("*");
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 3.0 && waveHeight < 3.5) {
					tv4f.setText(String.valueOf(windSpeed + " kt"));
					tv4e.setText("*");
					tv4d.setText("*");
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 3.5 && waveHeight < 4.0) {
					tv4g.setText(String.valueOf(windSpeed + " kt"));
					tv4f.setText("*");
					tv4e.setText("*");
					tv4d.setText("*");
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 4.0 && waveHeight < 4.5) {
					tv4h.setText(String.valueOf(windSpeed + " kt"));
					tv4g.setText("*");
					tv4f.setText("*");
					tv4e.setText("*");
					tv4d.setText("*");
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else if (waveHeight >= 4.5 && waveHeight < 5.0) {
					tv4i.setText(String.valueOf(windSpeed + " kt"));
					tv4h.setText("*");
					tv4g.setText("*");
					tv4f.setText("*");
					tv4e.setText("*");
					tv4d.setText("*");
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				} else {
					tv4j.setText(String.valueOf(windSpeed + " kt"));
					tv4i.setText("*");
					tv4h.setText("*");
					tv4g.setText("*");
					tv4f.setText("*");
					tv4e.setText("*");
					tv4d.setText("*");
					tv4c.setText("*");
					tv4b.setText("*");
					tv4bb.setText("*");
					tv4bbb.setText("*");
				}
			}
			if (column == 5) {
				tv5a.setText(String.valueOf(time + ":00"));
				tv5k.setText(windDegress + "\u00b0");
				tv5l.setText(windDirection);
				if (waveHeight < 1.0) {
					tv5bb.setText(String.valueOf(windSpeed + " kt"));
					tv5bbb.setText("*");
				} else if (waveHeight >= 1.0 && waveHeight < 1.5) {
					tv5b.setText(String.valueOf(windSpeed + " kt"));
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 1.5 && waveHeight < 2.0) {
					tv5c.setText(String.valueOf(windSpeed + " kt"));
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 2.0 && waveHeight < 2.5) {
					tv5d.setText(String.valueOf(windSpeed + " kt"));
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 2.5 && waveHeight < 3.0) {
					tv5e.setText(String.valueOf(windSpeed + " kt"));
					tv5d.setText("*");
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 3.0 && waveHeight < 3.5) {
					tv5f.setText(String.valueOf(windSpeed + " kt"));
					tv5e.setText("*");
					tv5d.setText("*");
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 3.5 && waveHeight < 4.0) {
					tv5g.setText(String.valueOf(windSpeed + " kt"));
					tv5f.setText("*");
					tv5e.setText("*");
					tv5d.setText("*");
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 4.0 && waveHeight < 4.5) {
					tv5h.setText(String.valueOf(windSpeed + " kt"));
					tv5g.setText("*");
					tv5f.setText("*");
					tv5e.setText("*");
					tv5d.setText("*");
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else if (waveHeight >= 4.5 && waveHeight < 5.0) {
					tv5i.setText(String.valueOf(windSpeed + " kt"));
					tv5h.setText("*");
					tv5g.setText("*");
					tv5f.setText("*");
					tv5e.setText("*");
					tv5d.setText("*");
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				} else {
					tv5j.setText(String.valueOf(windSpeed + " kt"));
					tv5i.setText("*");
					tv5h.setText("*");
					tv5g.setText("*");
					tv5f.setText("*");
					tv5e.setText("*");
					tv5d.setText("*");
					tv5c.setText("*");
					tv5b.setText("*");
					tv5bb.setText("*");
					tv5bbb.setText("*");
				}
			}
			if (column == 6) {
				tv6a.setText(String.valueOf(time + ":00"));

				tv6k.setText(windDegress + "\u00b0");
				tv6l.setText(windDirection);
				if (waveHeight < 1.0) {
					tv6bb.setText(String.valueOf(windSpeed + " kt"));
					tv6bbb.setText("*");
				} else if (waveHeight >= 1.0 && waveHeight < 1.5) {
					tv6b.setText(String.valueOf(windSpeed + " kt"));
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 1.5 && waveHeight < 2.0) {
					tv6c.setText(String.valueOf(windSpeed + " kt"));
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 2.0 && waveHeight < 2.5) {
					tv6d.setText(String.valueOf(windSpeed + " kt"));
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 2.5 && waveHeight < 3.0) {
					tv6e.setText(String.valueOf(windSpeed + " kt"));
					tv6d.setText("*");
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 3.0 && waveHeight < 3.5) {
					tv6f.setText(String.valueOf(windSpeed + " kt"));
					tv6e.setText("*");
					tv6d.setText("*");
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 3.5 && waveHeight < 4.0) {
					tv6g.setText(String.valueOf(windSpeed + " kt"));
					tv6f.setText("*");
					tv6e.setText("*");
					tv6d.setText("*");
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 4.0 && waveHeight < 4.5) {
					tv6h.setText(String.valueOf(windSpeed + " kt"));
					tv6g.setText("*");
					tv6f.setText("*");
					tv6e.setText("*");
					tv6d.setText("*");
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else if (waveHeight >= 4.5 && waveHeight < 5.0) {
					tv6i.setText(String.valueOf(windSpeed + " kt"));
					tv6h.setText("*");
					tv6g.setText("*");
					tv6f.setText("*");
					tv6e.setText("*");
					tv6d.setText("*");
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				} else {
					tv6j.setText(String.valueOf(windSpeed + " kt"));
					tv6i.setText("*");
					tv6h.setText("*");
					tv6g.setText("*");
					tv6f.setText("*");
					tv6e.setText("*");
					tv6d.setText("*");
					tv6c.setText("*");
					tv6b.setText("*");
					tv6bb.setText("*");
					tv6bbb.setText("*");
				}
			}

			column++;
		}

	}

	// method to clear the main gui
	private void clearScreen() {
		tv1k.setText("");
		tv1l.setText("");
		tv1j.setText("");
		tv1i.setText("");
		tv1h.setText("");
		tv1g.setText("");
		tv1f.setText("");
		tv1e.setText("");
		tv1d.setText("");
		tv1c.setText("");
		tv1b.setText("");
		tv1bb.setText("");
		tv1bbb.setText("");
		tv1a.setText("");

		tv2k.setText("");
		tv2l.setText("");
		tv2j.setText("");
		tv2i.setText("");
		tv2h.setText("");
		tv2g.setText("");
		tv2f.setText("");
		tv2e.setText("");
		tv2d.setText("");
		tv2c.setText("");
		tv2b.setText("");
		tv2bb.setText("");
		tv2bbb.setText("");
		tv2a.setText("");

		tv3k.setText("");
		tv3l.setText("");
		tv3j.setText("");
		tv3i.setText("");
		tv3h.setText("");
		tv3g.setText("");
		tv3f.setText("");
		tv3e.setText("");
		tv3d.setText("");
		tv3c.setText("");
		tv3b.setText("");
		tv3bb.setText("");
		tv3bbb.setText("");
		tv3a.setText("");

		tv4k.setText("");
		tv4l.setText("");
		tv4j.setText("");
		tv4i.setText("");
		tv4h.setText("");
		tv4g.setText("");
		tv4f.setText("");
		tv4e.setText("");
		tv4d.setText("");
		tv4c.setText("");
		tv4b.setText("");
		tv4bb.setText("");
		tv4bbb.setText("");
		tv4a.setText("");

		tv5k.setText("");
		tv5l.setText("");
		tv5j.setText("");
		tv5i.setText("");
		tv5h.setText("");
		tv5g.setText("");
		tv5f.setText("");
		tv5e.setText("");
		tv5d.setText("");
		tv5c.setText("");
		tv5b.setText("");
		tv5bb.setText("");
		tv5bbb.setText("");
		tv5a.setText("");

		tv6k.setText("");
		tv6l.setText("");
		tv6j.setText("");
		tv6i.setText("");
		tv6h.setText("");
		tv6g.setText("");
		tv6f.setText("");
		tv6e.setText("");
		tv6d.setText("");
		tv6c.setText("");
		tv6b.setText("");
		tv6bb.setText("");
		tv6bbb.setText("");
		tv6a.setText("");

		tvAirTemp.setText("");
		tvWaterTemp.setText("");
		tvWindSpeed.setText("");
		tvWindDir.setText("");
		lblWaveheight.setText("");
		tvWaveInterval.setText("");

	}

	// method the write the arraylist of data to the local disk
	void writeNewReportToFile(ArrayList<SavedData> savedDataArrayList) throws IOException {
		
//		ArrayList<SavedData> jsonTestData = savedDataArrayList;
//		Gson gson = new Gson();
//		JsonElement element = gson.toJsonTree(jsonTestData, new TypeToken<List<SavedData>>() {}.getType());
//		JsonArray jsonArray = element.getAsJsonArray();
//		
//		FileOutputStream jsonFos = new FileOutputStream("jsonSavedData");
//		ObjectOutputStream jsonOos = new ObjectOutputStream(jsonFos);
//		jsonOos.writeObject(jsonArray);
//		jsonOos.close();
		
		FileOutputStream fos = new FileOutputStream("savedData");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(savedDataArrayList);
		oos.close();
	}
	// private class SwingAction extends AbstractAction {
	// public SwingAction() {
	// putValue(NAME, "SwingAction");
	// putValue(SHORT_DESCRIPTION, "Some short description");
	// }
	//
	// public void actionPerformed(ActionEvent e) {
	// }
	// }

	// a pop up messaged displayed when the surf grade is good.

	void popUpMessage(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
