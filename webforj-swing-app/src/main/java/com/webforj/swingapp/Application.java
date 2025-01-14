package com.webforj.swingapp;

import com.github.javafaker.Faker;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Application {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Customer Table with Actions");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setUndecorated(true);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    frame.setSize(800, 400);

    Faker faker = new Faker();

    List<Customer> customers = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      customers.add(
          new Customer(i, faker.name().fullName(), faker.company().name(), faker.internet().emailAddress()));
    }

    CustomerTable customerTable = new CustomerTable(frame, customers);

    frame.add(customerTable.getScrollPane(), BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
