package mediator;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;

public class Mediator {
	private List<JCheckBox> checkBoxList;
	private JButton selectAllButton;
	private JButton selectNoneButton;
	private JButton selectInverseButton;

	public Mediator(List<JCheckBox> checkBoxList, JButton selectAllButton, JButton selectNoneButton,
			JButton selectInverseButton) {
		// TODO Auto-generated constructor stub
		this.checkBoxList = checkBoxList;
		this.selectAllButton = selectAllButton;
		this.selectNoneButton = selectNoneButton;
		this.selectInverseButton = selectInverseButton;

		this.checkBoxList.forEach(checkBox -> {
			checkBox.addChangeListener(this::onCheckBoxChanged);
		});
		this.selectAllButton.addActionListener(this::onSelectAllClicked);
		this.selectNoneButton.addActionListener(this::onSelectNoneClicked);
		this.selectInverseButton.addActionListener(this::onSelectInverseClicked);
	}

	private void onCheckBoxChanged(ChangeEvent event) {
		boolean allChecked = true;
		boolean allUnchecked = true;
		for (JCheckBox jCheckBox : checkBoxList) {
			if (jCheckBox.isSelected()) {
				allUnchecked = false;
			} else {
				allChecked = false;
			}
		}
		selectAllButton.setEnabled(!allChecked);
		selectNoneButton.setEnabled(!allUnchecked);
	}

	public void onSelectAllClicked(ActionEvent event) {
		checkBoxList.forEach(checkBox -> checkBox.setSelected(true));
		selectAllButton.setEnabled(false);
		selectNoneButton.setEnabled(true);
	}

	private void onSelectNoneClicked(ActionEvent changeevent1) {
		checkBoxList.forEach(checkBox -> checkBox.setSelected(false));
		selectAllButton.setEnabled(true);
		selectNoneButton.setEnabled(false);
	}

	private void onSelectInverseClicked(ActionEvent event) {
		checkBoxList.forEach(checkBox -> checkBox.setSelected(!checkBox.isSelected()));
		onCheckBoxChanged(null);
	}
}
