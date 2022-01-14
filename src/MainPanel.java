import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MainPanel extends JPanel implements ActionListener {

	private final JTextArea jta0, jta1, jta2, jta3, jta4, jta5;
	private final JTextField jft0, jft;
	private MainClass mc;
	private MainAlgorythm mc0;
	private final JButton choleskyButton, drawWezlyButton, drawLukiButton, ep1Button, ep2Button, ep3Button, runAppButton,
					authorButton, showButton;
	private final JPanel choleskyPanel;
	private final JPanel wezlyPanel;
	private final JPanel lukiPanel;
	private final JPanel ep1Panel;
	private final JPanel ep2Panel;
	private final JPanel ep3Panel;

	MainPanel() {
		this.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		choleskyPanel = new JPanel();
		wezlyPanel = new JPanel();
		lukiPanel = new JPanel();
		ep1Panel = new JPanel();
		ep2Panel = new JPanel();
		ep3Panel = new JPanel();

		choleskyButton = new JButton("cholesky");
		drawWezlyButton = new JButton("węzły");
		drawLukiButton = new JButton("łuki");
		ep1Button = new JButton("EP1");
		ep2Button = new JButton("EP2");
		ep3Button = new JButton("EP3");
		runAppButton = new JButton("Uruchom");
		showButton = new JButton("Pokaż");
		showButton.setEnabled(false);
		authorButton = new JButton("Autorzy");
		jft = new JTextField(3);
		jft0 = new JTextField(3);
		jft.setText("4");
		jft0.setText("4");

		jta0 = new JTextArea(30,20);
		jta0.setBackground(Color.BLACK);
		jta0.setFont(new Font("Dialog", Font.BOLD, 14));
		JScrollPane scrollPane0 = new JScrollPane(jta0);
		scrollPane0.setPreferredSize(new Dimension(600, 500));
		jta0.setEnabled(false);

		jta1 = new JTextArea(30,20);
		jta1.setBackground(Color.BLACK);
		jta1.setFont(new Font("Dialog", Font.BOLD, 14));
		JScrollPane scrollPane1 = new JScrollPane(jta1);
		scrollPane1.setPreferredSize(new Dimension(750, 500));
		jta1.setEnabled(false);

		jta2 = new JTextArea(30,20);
		jta2.setBackground(Color.BLACK);
		jta2.setFont(new Font("Dialog", Font.BOLD, 14));
		JScrollPane scrollPane2 = new JScrollPane(jta2);
		scrollPane2.setPreferredSize(new Dimension(750, 500));
		jta2.setEnabled(false);

		jta3 = new JTextArea(30,20);
		jta3.setBackground(Color.BLACK);
		jta3.setFont(new Font("Dialog", Font.BOLD, 14));
		JScrollPane scrollPane3 = new JScrollPane(jta3);
		scrollPane3.setPreferredSize(new Dimension(600, 500));
		jta3.setEnabled(false);

		jta4 = new JTextArea(30,20);
		jta4.setBackground(Color.BLACK);
		jta4.setFont(new Font("Dialog", Font.BOLD, 14));
		JScrollPane scrollPane4 = new JScrollPane(jta4);
		scrollPane4.setPreferredSize(new Dimension(600, 500));
		jta4.setEnabled(false);

		jta5 = new JTextArea(30,20);
		jta5.setBackground(Color.BLACK);
		jta5.setFont(new Font("Dialog", Font.BOLD, 14));
		JScrollPane scrollPane5 = new JScrollPane(jta5);
		scrollPane5.setPreferredSize(new Dimension(500, 500));
		jta5.setEnabled(false);

		choleskyButton.addActionListener(this);
		drawWezlyButton.addActionListener(this);
		drawLukiButton.addActionListener(this);
		ep1Button.addActionListener(this);
		ep2Button.addActionListener(this);
		ep3Button.addActionListener(this);
		runAppButton.addActionListener(this);
		showButton.addActionListener(this);
		authorButton.addActionListener(this);

		northPanel.add(choleskyButton);
		northPanel.add(jft0);
		northPanel.add(drawWezlyButton);
		northPanel.add(drawLukiButton);
		northPanel.add(ep1Button);
		northPanel.add(ep2Button);
		northPanel.add(ep3Button);
		northPanel.add(jft);
		northPanel.add(runAppButton);
		northPanel.add(showButton);
		northPanel.add(authorButton);
		try {
			BufferedImage myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("cholesky.JPG")));
			JLabel picLabel1 = new JLabel(new ImageIcon(myPicture));
			choleskyPanel.add(picLabel1);

			myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("trojkaty.png")));
			JLabel picLabel2 = new JLabel(new ImageIcon(myPicture));
			choleskyPanel.add(picLabel2);

			myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("graf.png")));
			JLabel picLabel3 = new JLabel(new ImageIcon(myPicture));
			wezlyPanel.add(picLabel3);

			myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("graf.png")));
			JLabel picLabel4 = new JLabel(new ImageIcon(myPicture));
			lukiPanel.add(picLabel4);

			myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("fs1.jpg")));
			JLabel picLabel5 = new JLabel(new ImageIcon(myPicture));
			ep1Panel.add(picLabel5);

			myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("fs2.jpg")));
			JLabel picLabel6 = new JLabel(new ImageIcon(myPicture));
			ep2Panel.add(picLabel6);

			myPicture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("fs3.jpg")));
			JLabel picLabel7 = new JLabel(new ImageIcon(myPicture));
			ep3Panel.add(picLabel7);
		} catch (IOException e) {
			e.printStackTrace();
		}

		choleskyPanel.add(scrollPane0);
		wezlyPanel.add(scrollPane1);
		lukiPanel.add(scrollPane2);
		ep1Panel.add(scrollPane3);
		ep2Panel.add(scrollPane4);
		ep3Panel.add(scrollPane5);

		this.add(northPanel, BorderLayout.NORTH);
	}

	private void showCholeskyPanel() {
		this.add(choleskyPanel, BorderLayout.CENTER);
		choleskyPanel.setVisible(true);
		wezlyPanel.setVisible(false);
		lukiPanel.setVisible(false);
		ep1Panel.setVisible(false);
		ep2Panel.setVisible(false);
		ep3Panel.setVisible(false);
		this.revalidate();
	}

	private void showWezlyPanel() {
		this.add(wezlyPanel, BorderLayout.CENTER);
		choleskyPanel.setVisible(false);
		wezlyPanel.setVisible(true);
		lukiPanel.setVisible(false);
		ep1Panel.setVisible(false);
		ep2Panel.setVisible(false);
		ep3Panel.setVisible(false);
		this.revalidate();
	}

	private void showLukiPanel() {
		this.add(lukiPanel, BorderLayout.CENTER);
		choleskyPanel.setVisible(false);
		wezlyPanel.setVisible(false);
		lukiPanel.setVisible(true);
		ep1Panel.setVisible(false);
		ep2Panel.setVisible(false);
		ep3Panel.setVisible(false);
		this.revalidate();
	}

	private void showEP1Panel() {
		this.add(ep1Panel, BorderLayout.CENTER);
		choleskyPanel.setVisible(false);
		wezlyPanel.setVisible(false);
		lukiPanel.setVisible(false);
		ep1Panel.setVisible(true);
		ep2Panel.setVisible(false);
		ep3Panel.setVisible(false);
		this.revalidate();
	}

	private void showEP2Panel() {
		this.add(ep2Panel, BorderLayout.CENTER);
		choleskyPanel.setVisible(false);
		wezlyPanel.setVisible(false);
		lukiPanel.setVisible(false);
		ep1Panel.setVisible(false);
		ep2Panel.setVisible(true);
		ep3Panel.setVisible(false);
		this.revalidate();
	}

	private void showEP3Panel() {
		this.add(ep3Panel, BorderLayout.CENTER);
		choleskyPanel.setVisible(false);
		wezlyPanel.setVisible(false);
		lukiPanel.setVisible(false);
		ep1Panel.setVisible(false);
		ep2Panel.setVisible(false);
		ep3Panel.setVisible(true);
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(choleskyButton)) {
			showCholeskyPanel();
		} else if (e.getSource().equals(drawWezlyButton)) {
			showWezlyPanel();
		} else if (e.getSource().equals(drawLukiButton)) {
			showLukiPanel();
		} else if (e.getSource().equals(ep1Button)) {
			showEP1Panel();
		} else if (e.getSource().equals(ep2Button)) {
			showEP2Panel();
		} else if (e.getSource().equals(ep3Button)) {
			showEP3Panel();
		} else if (e.getSource().equals(runAppButton)) {
			try {
				jta0.setText("");
				jta1.setText("");
				jta2.setText("");
				jta3.setText("");
				jta4.setText("");
				jta5.setText("");
				mc0 = new MainAlgorythm(Integer.parseInt(jft0.getText()));
				jta0.append("\n" + " czas wykonania algorytmu = " + mc0.getTimeElapsed() + "ns = " +
						mc0.getTimeElapsed()/1000000.f + "ms");
				mc = new MainClass(Integer.parseInt(jft.getText()));
				jta3.append(String.format("\n średnie obciążenie elementu przetwarzającego = %.4f\n", mc.getSrEP1()));
				jta4.append(String.format("\n średnie obciążenie elementu przetwarzającego = %.4f\n", mc.getSrEP2()));
				jta5.append(String.format("\n średnie obciążenie elementu przetwarzającego = %.4f\n", mc.getSrEP3()));
				showButton.setEnabled(true);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this,
						"Prosze podać liczbę",
						"Number input error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(authorButton)) {
			AuthorDialog x = new AuthorDialog();
			x.setVisible(true);
		} else if (e.getSource().equals(showButton)) {
			mc0.showMatrix(Integer.parseInt(jft0.getText()), mc0.getA(), jta0);
			mc0.showMatrix(Integer.parseInt(jft0.getText()), mc0.getL(), jta0);
			mc0.showMatrix(Integer.parseInt(jft0.getText()), mc0.getLt(), jta0);
			mc0.showMatrix(Integer.parseInt(jft0.getText()), mc0.getM(), jta0);
			mc.showMix(jta1);
			mc.showWezlyI1(jta2);
			mc.showWezlyI2(jta2);
			mc.showWezlyI3(jta2);
			mc.showWezlyI4(jta2);
			mc.showWezlyI34(jta2);
			mc.showWezlyI24(jta2);
			mc.showWezlyI23(jta2);
			mc.showWezlyI13(jta2);
			mc.showEP1(jta3);
			mc.showEP2(jta4);
			mc.showEP3(jta5);
		}
	}
}
