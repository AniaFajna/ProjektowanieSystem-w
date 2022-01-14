import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class AuthorDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 3L;
	private static final int DEFAULT_WIDTH = 620;
	private static final int DEFAULT_HEIGHT = 450;
	private JPanel infoPanel, titlePanel, personalInfoPanel, quotePanel, exitPanel;
	private JLabel nameLabel, nameLabel2, mailLabel, githubLabel,
			copyrightLabel, quoteLabel, titleLabel;
	private JButton exitInfoWindowButton;
	private Dimension frameSize, screenSize;

	public AuthorDialog() {
		initComponents();
		setUpDialogFrame();
		setUpInfoPanelLayout();
		this.add(infoPanel, BorderLayout.CENTER);
		this.add(copyrightLabel, BorderLayout.SOUTH);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	private void initComponents() {
		try {
			frameSize = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
			Toolkit defaultToolKit = Toolkit.getDefaultToolkit();
			screenSize = defaultToolKit.getScreenSize();

			infoPanel = new JPanel();
			titlePanel = new JPanel();
			personalInfoPanel = new JPanel();
			quotePanel = new JPanel();

			infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
			titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
			personalInfoPanel.setLayout(new BoxLayout(personalInfoPanel, BoxLayout.Y_AXIS));
			quotePanel.setLayout(new BoxLayout(quotePanel, BoxLayout.Y_AXIS));

			exitPanel = new JPanel();
			exitPanel.setLayout(new BoxLayout(exitPanel, BoxLayout.X_AXIS));
			exitInfoWindowButton = new JButton("Zamknij");
			exitInfoWindowButton.addActionListener(this);
			exitPanel.add(Box.createRigidArea(new Dimension(10,10)));
			exitPanel.add(Box.createHorizontalGlue());
			exitPanel.add(exitInfoWindowButton);
			exitPanel.add(Box.createRigidArea(new Dimension(10,10)));


			titleLabel = new JLabel("Cholesky algorithm simulation");
			nameLabel = new JLabel("    Przemysław Kondaszewski");
			nameLabel2 = new JLabel("     Marcin Cieciura");
			mailLabel = new JLabel("    random_mail@gmail.com");
			githubLabel = new JLabel("    gitlab.com/xyz");
			copyrightLabel = new JLabel("Copyright © 2022. All rights reserved");
			quoteLabel = new JLabel("\"Vision is the art of seeing things invisible.\"");

			Border blackline = BorderFactory.createLineBorder(Color.BLACK);
			Border empty = BorderFactory.createEmptyBorder();
			TitledBorder versionBorder = BorderFactory.createTitledBorder(empty, "version 1.0");
			TitledBorder titleBorder = BorderFactory.createTitledBorder(empty, "Jonathan Swift");
			TitledBorder nameBorder = BorderFactory.createTitledBorder(empty, "Name");
			TitledBorder mailBorder = BorderFactory.createTitledBorder(empty, "Mail");
			TitledBorder gitlabBorder = BorderFactory.createTitledBorder(empty, "Gitlab");

			versionBorder.setTitlePosition(TitledBorder.BOTTOM);
			titleBorder.setTitlePosition(TitledBorder.BOTTOM);

			titleLabel.setBorder(versionBorder);
			quoteLabel.setBorder(titleBorder);
			copyrightLabel.setBorder(blackline);
			nameLabel.setBorder(nameBorder);
			mailLabel.setBorder(mailBorder);
			githubLabel.setBorder(gitlabBorder);

			Font font = new Font(Font.DIALOG, Font.BOLD, 20);
			Font font2 = new Font(Font.DIALOG, Font.BOLD, 15);
			Font font3 = new Font(Font.SERIF, Font.ITALIC, 15);

			titleLabel.setFont(font);
			nameLabel.setFont(font2);
			nameLabel2.setFont(font2);
			mailLabel.setFont(font2);
			githubLabel.setFont(font2);
			quoteLabel.setFont(font3);
		} catch (Exception e) {
			//infoAboutAuthorDialogLogger.error("Initialization error.");
		}
	}

	private void setUpDialogFrame() {
		try {
			if(frameSize.height > screenSize.height) frameSize.height = screenSize.height;
			if(frameSize.width > screenSize.width) frameSize.width = screenSize.width;
			this.setTitle("Info about authors");
			this.setSize(frameSize);
			this.setResizable(false);
			this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
			this.setLayout(new BorderLayout());
		} catch (Exception e) {
			//infoAboutAuthorDialogLogger.error("Setting up dialog frame error.");
		}
	}

	private void setUpInfoPanelLayout() {
		titlePanel.add(Box.createRigidArea(new Dimension(0,10)));
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createRigidArea(new Dimension(0,5)));

		personalInfoPanel.add(Box.createRigidArea(new Dimension(0,40)));
		personalInfoPanel.add(nameLabel);
		personalInfoPanel.add(nameLabel2);
		personalInfoPanel.add(Box.createRigidArea(new Dimension(0,15)));
		personalInfoPanel.add(mailLabel);
		personalInfoPanel.add(Box.createRigidArea(new Dimension(0,15)));
		personalInfoPanel.add(githubLabel);

		quotePanel.add(Box.createRigidArea(new Dimension(0,30)));
		quotePanel.add(quoteLabel);
		quotePanel.add(Box.createRigidArea(new Dimension(0,10)));
		quotePanel.add(exitPanel);

		infoPanel.add(titlePanel, BorderLayout.NORTH);
		infoPanel.add(personalInfoPanel, BorderLayout.EAST);
		infoPanel.add(quotePanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(exitInfoWindowButton)) {
			//infoAboutAuthorDialogLogger.info("Closing infoAboutAuthor dialog.");
			dispose();
		}
	}
}