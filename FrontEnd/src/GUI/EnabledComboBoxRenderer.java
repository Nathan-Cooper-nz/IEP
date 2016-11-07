package GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class EnabledComboBoxRenderer extends BasicComboBoxRenderer{

	private ListSelectionModel enabledItems;
	private Color disabled = Color.LIGHT_GRAY;

	public EnabledComboBoxRenderer(ListSelectionModel enabled){
		super();
		this.enabledItems = enabled;
	}
	
	public void setEnabledItems (ListSelectionModel enabled){
		this.enabledItems = enabled;
	}

	public void setColor(Color col){
		this.disabled = col;
	}

	 public Component getListCellRendererComponent(JList list, Object value,
	            int index, boolean isSelected, boolean cellHasFocus) {

	        Component c = super.getListCellRendererComponent(list, value, index,
	                isSelected, cellHasFocus);

	        if (!enabledItems.isSelectedIndex(index)) {// not enabled
	            if (isSelected) {
	                c.setBackground(UIManager.getColor("ComboBox.background"));
	            } else {
	                c.setBackground(super.getBackground());
	            }

	            c.setForeground(disabled);

	        } else {
	            c.setBackground(super.getBackground());
	            c.setForeground(super.getForeground());
	        }
	        return c;
	    }
}

