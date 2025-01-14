package com.webforj.swingapp;

import java.awt.Color;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.webswing.toolkit.api.WebswingUtil;

import com.google.gson.Gson;

public class CustomerTable {

  private final JTable table;
  private final DefaultTableModel model;
  private final List<Customer> customers;

  public CustomerTable(JFrame frame, List<Customer> customers) {
    this.customers = customers;

    String[] columnNames = { "Name", "Company", "Email" };
    this.model = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    refresh();

    this.table = new JTable(model);
    table.setRowHeight(30);
    table.setFocusable(false);
    table.setRowSelectionAllowed(true);

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = table.rowAtPoint(e.getPoint());
          if (row >= 0 && row < customers.size()) {
            Customer customer = customers.get(row);
            Gson gson = new Gson();
            WebswingUtil.getWebswingApi().sendActionEvent("select-customer", gson.toJson(customer), null);
          }
        }
      }
    });

    WebswingUtil.getWebswingApi().addBrowserActionListener(event -> {
      switch (event.getActionName()) {
        case "update-customer":
          Customer customer = new Gson().fromJson(event.getData(), Customer.class);
          // update the customer in the list
          for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
              customers.set(i, customer);
              break;
            }
          }
          refresh();
          break;
        default:
          break;
      }
    });
  }

  public void refresh() {
    model.setRowCount(0);
    for (Customer customer : customers) {
      model.addRow(new Object[] { customer.getName(), customer.getCompany(), customer.getEmail() });
    }
  }

  public JScrollPane getScrollPane() {
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return scrollPane;
  }
}
