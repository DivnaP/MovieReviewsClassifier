package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import com.google.gson.Gson;

import broker.Broker;
import klase.PositiveNegativeSentence;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pocetna extends JFrame {
	UIManager u = new UIManager();
	final JFileChooser fc = new JFileChooser();
	public JTextArea textArea = new JTextArea();
	File fileChosen;
	String text = "";
	final JComboBox comboBox2 = new JComboBox();
	JScrollPane scroll;
	JFrame frame;
	final URL url;
	/**
	 * @wbp.nonvisual location=-19,469
	 */
	private final JLabel label = new JLabel("");
	// In response to a button click:

	public Pocetna() throws MalformedURLException {
		url = new URL("file:///C:/Users/Divna/workspace/Test/res/wait9.gif");
		frame = this;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Divna\\Desktop\\sliciceJava\\mo.jpg"));
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initUI();
	}

	public final void initUI() {

		JMenuBar menubar = new JMenuBar();
		JMenu train = new JMenu("Trening");

		menubar.add(train);
		
		JMenu mnIzborKlasifikatora = new JMenu("Izbor klasifikatora");
		train.add(mnIzborKlasifikatora);
		
		JMenuItem mntmLogisticRegre = new JMenuItem("Logistic regression");
		mnIzborKlasifikatora.add(mntmLogisticRegre);
		mntmLogisticRegre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						/** Execute some operation */

						return Broker.getInstance().train("logistic");
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {

					textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		JMenuItem mntmSmo = new JMenuItem("SMO");
		mnIzborKlasifikatora.add(mntmSmo);
		
		mntmSmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						/** Execute some operation */

						return Broker.getInstance().train("smo");
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {

					textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		
		
		
		
		
		JMenuItem mntmJ_1 = new JMenuItem("J48");
		mnIzborKlasifikatora.add(mntmJ_1);
		mntmJ_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						/** Execute some operation */

						return Broker.getInstance().train("j48");
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {

					textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		JMenuItem mntmSvm = new JMenuItem("SVM");
		mnIzborKlasifikatora.add(mntmSvm);

		mntmSvm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						

						return Broker.getInstance().train("svm");
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {

					textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
	
		JMenuItem mntmNaivebayes_1 = new JMenuItem("NaiveBayes");
		mntmNaivebayes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						/** Execute some operation */

						return Broker.getInstance().train("nb");
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {

					textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
				
			
		mnIzborKlasifikatora.add(mntmNaivebayes_1);
		setJMenuBar(menubar);

		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);

		fc.setFileFilter(new MyTXTFilter());
		final JButton btUcitaj = new JButton();

		btUcitaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Handle open button action.
				textArea.setEditable(false);
				if (arg0.getSource() == btUcitaj) {
					int returnVal = fc.showOpenDialog(Pocetna.this);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						fileChosen = fc.getSelectedFile();

						try {

							textArea.read(new FileReader(fileChosen.getAbsolutePath()), null);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {

					}
				}
			}
		});
		btUcitaj.setText("U\u010Ditaj kritiku");
		btUcitaj.setBorder(UIManager.getBorder("Button.border"));
		toolbar.add(btUcitaj);

		getContentPane().add(toolbar, BorderLayout.NORTH);

		JButton btUnesiKritiku = new JButton();
		btUnesiKritiku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChosen = null;
				textArea.setText("");
				textArea.setEditable(true);
				textArea.requestFocus();
			}
		});
		btUnesiKritiku.setText("Unesi kritiku");
		btUnesiKritiku.setBorder(UIManager.getBorder("Button.border"));
		toolbar.add(btUnesiKritiku);

	//	JButton btTrening = new JButton();
		/*btTrening.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						

						return Broker.getInstance().train();
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {

					textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		*/
	//	btTrening.setText("Trening");
	//	btTrening.setBorder(UIManager.getBorder("Button.border"));
	//	toolbar.add(btTrening);

		JToolBar vertical2 = new JToolBar(JToolBar.VERTICAL);
		vertical2.setFloatable(false);
		vertical2.setMargin(new Insets(10, 5, 5, 5));

		JLabel lblIzborKlasifikatora = new JLabel("Izbor klasifikatora");
		lblIzborKlasifikatora.setHorizontalTextPosition(SwingConstants.LEFT);
		lblIzborKlasifikatora.setHorizontalAlignment(SwingConstants.LEFT);
		vertical2.add(lblIzborKlasifikatora);

		// vertical2.add(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// arg0.getActionCommand().
				JComboBox comboBox = (JComboBox) arg0.getSource();

				Object selected = comboBox.getSelectedItem();

				if (selected.toString().startsWith("Klasifikacija na osnovu vrste reči"))
					comboBox2.setVisible(true);
				else
					comboBox2.setVisible(false);
			}
		});
		comboBox.addItem("Klasifikacija na osnovu treninga");
		comboBox.addItem("Klasifikacija na osnovu vrste reči");

		vertical2.add(comboBox);

		comboBox2.setVisible(false);

		Component verticalStrut = Box.createVerticalStrut(20);
		vertical2.add(verticalStrut);
		comboBox2.addItem("SWN");
		comboBox2.addItem("HP IDOLOnDemand");
		vertical2.add(comboBox2);

		getContentPane().add(vertical2, BorderLayout.EAST);

		JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
		vertical.setFloatable(false);
		vertical.setMargin(new Insets(10, 5, 5, 5));

		ImageIcon select = new ImageIcon("res/analizaS.png");
		ImageIcon freehand = new ImageIcon("res/computerS.png");
		ImageIcon shapeed = new ImageIcon("res/printerS.png");

		JButton btAnalyse = new JButton(select);
		btAnalyse.setToolTipText("Pokreni analizu kritike");
		btAnalyse.setBorder(new EmptyBorder(3, 0, 3, 0));

		btAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog loading = new JDialog(frame);
				JPanel p1 = new JPanel(new BorderLayout());
				p1.setBackground(new Color(255, 255, 255));
				JLabel jlabel = new JLabel("Molimo Vas sačekajte... ");
				jlabel.setBackground(new Color(255, 255, 255));
				// jlabel.setForeground(new Color(255, 255, 255));
				p1.add(jlabel, BorderLayout.CENTER);

				Icon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);

				p1.add(label, BorderLayout.EAST);
				loading.setUndecorated(true);
				loading.getContentPane().add(p1);
				loading.pack();
				loading.setLocationRelativeTo(frame);
				loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				loading.setModal(true);

				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() {
						/** Execute some operation */

						String klasifikator;
						if (comboBox.getSelectedItem().equals("Klasifikacija na osnovu treninga"))
							klasifikator = "nb";
						else {
							klasifikator = "ar";
							if (comboBox2.getSelectedItem().equals("SWN"))
								klasifikator += "+SWN";
							else
								klasifikator += "+HP";
						}
						if (fileChosen != null) {

							if (!klasifikator.contains("+HP"))
								return Broker.getInstance().analyze(fileChosen.getPath(), klasifikator);

							else {
								text = Broker.getInstance().load(fileChosen.getPath());

								Thread reg = new Thread(qoueryIDOL);
								reg.start();
							}
						}

						else if (!textArea.getText().equals("")) {
							FileWriter fw;
							try {

								fw = new FileWriter("data/kritika.txt", false);
								textArea.write(fw);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}

							if (klasifikator.contains("+HP")) {
								text = Broker.getInstance().load("data/kritika.txt");

								Thread reg = new Thread(qoueryIDOL);
								reg.start();
							} else
								return Broker.getInstance().analyze("data/kritika.txt", klasifikator);

						}

						else {
							JOptionPane.showMessageDialog(null, "Niste učitali ili uneli kritiku za analzu!",
									"Upozorenje", 2);

						}

						return "";
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {
					if (worker.get() != "")
						textArea.setText(worker.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton freehandb = new JButton(freehand);
		freehandb.setBorder(new EmptyBorder(3, 0, 3, 0));

		JButton btPrint = new JButton(shapeed);
		btPrint.setToolTipText("\u0160tampaj");
		btPrint.setBorder(new EmptyBorder(3, 0, 3, 0));

		btPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textArea.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Nema teksta za štampu!", "Upozorenje", 2);
						return;
					}

					Broker.getInstance().createPDF(textArea.getText().toString());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		vertical.add(btAnalyse);
		vertical.add(freehandb);
		vertical.add(btPrint);

		getContentPane().add(vertical, BorderLayout.WEST);
		textArea.setEditable(false);

		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setToolTipText("Unesi kritiku");

		getContentPane().add(scroll, BorderLayout.CENTER);

		JLabel statusbar = new JLabel("Didi 2015@ Copyright");
		statusbar.setPreferredSize(new Dimension(-1, 22));
		statusbar.setBorder(LineBorder.createGrayLineBorder());

		getContentPane().add(statusbar, BorderLayout.SOUTH);
		ImageIcon lab = new ImageIcon("res/mo.jpg");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setIcon(lab);
		// vertical2.add(label);
		// getContentPane().add(label, BorderLayout.SOUTH);
		setSize(729, 487);
		setTitle("Klasifikacija filmskih kritika");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	Runnable qoueryIDOL = new Runnable() {

		@Override
		public void run() {
			String result = "";
		
			HttpClient client = new DefaultHttpClient();

			String[] niz = text.split(" ");
			String zaSlanje = "";
			for (int i = 0; i < niz.length; i++) {
				zaSlanje += niz[i] + "+";
			}
			HttpGet request2 = new HttpGet("https://api.idolondemand.com/1/api/sync/analyzesentiment/v1?text="
					+ zaSlanje + "+&apikey=6996e750-60ec-44a6-8838-c84b716d2dac");
			HttpResponse response;
			try {
				response = client.execute(request2);
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				final StringBuilder builder = new StringBuilder(50);
				String line = "";

				while ((line = rd.readLine()) != null) {

					builder.append(line);

				}

				final PositiveNegativeSentence results = new Gson().fromJson(builder.toString(),
						PositiveNegativeSentence.class);
				result = results.toString();
				System.out.println(results);
				// p.textArea.setText(result);

				request2.completed();
				request2.abort();
				Pocetna.this.textArea.setText(result);
				// textArea.setText(result);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				Pocetna ex=null;
				try {
					ex = new Pocetna();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ex.setVisible(true);
			}
		});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}