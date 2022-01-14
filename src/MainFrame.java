import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

	MainFrame() {
		this.setSize(1250,650);
		this.setLocation(50,50);
		this.setLayout(new BorderLayout());
		this.setTitle("Cholesky algorithm simulation");
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cpu-intel-icon.png")));
		this.add(new MainPanel(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
