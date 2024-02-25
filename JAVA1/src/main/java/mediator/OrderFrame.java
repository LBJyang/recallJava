package mediator;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OrderFrame extends JFrame {
	public OrderFrame(String... names) {
		setTitle("Order");
		setSize(460, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
		c.add(new JLabel("Use Mediator Pattern"));
		List<JCheckBox> checkBoxs = addCheckBox(names);
		JButton selectAll = addButton("Select All");
		JButton selectNone = addButton("Select None");
		selectNone.setEnabled(false);
		JButton selectInverse = addButton("Inverse Select");
		new Mediator(checkBoxs, selectAll, selectNone, selectInverse);
		setVisible(true);
	}

	private List<JCheckBox> addCheckBox(String... names) {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Menu:"));
		List<JCheckBox> list = new ArrayList<JCheckBox>();
		for (String name : names) {
			JCheckBox checkBox = new JCheckBox(name);
			list.add(checkBox);
			panel.add(checkBox);
		}
		getContentPane().add(panel);
		return list;
	}

	private JButton addButton(String label) {
		JButton button = new JButton(label);
		getContentPane().add(button);
		return button;
	}
}
