package login.frames;

import login.dao.BookDAO;
import login.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    protected JButton buttonValue;
    HomeFrame parent;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox, HomeFrame parent) {
        super(checkBox);
        this.parent = parent;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        buttonValue = (JButton) value;
        button.setText("Rent");
        button.setEnabled(buttonValue.isEnabled());
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            BookDAO bookDAO = new BookDAO();
            bookDAO.rentBook(new Book(Integer.parseInt(buttonValue.getText()), "", "", ""), HomeFrame.currentUser);
            JOptionPane.showMessageDialog(button, "Book rented successfully");
        }
        isPushed = false;
        parent.addTableView();
        return button;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}