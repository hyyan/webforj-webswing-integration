package com.webforj.webswing.demo.views;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.router.annotation.Route;
import com.webforj.webswing.WebswingConnector;
import com.webforj.webswing.demo.components.CustomerForm;

@Route("/")
public class CustomerView extends Composite<FlexLayout> {
  private FlexLayout self = getBoundComponent();

  public CustomerView() {
    WebswingConnector connector = new WebswingConnector("http://localhost:8080/webforj-swing-app/");
    connector.setSize("100vw", "100vh");

    connector.onAction(event -> {
      switch (event.getActionName()) {
        case "select-customer":
          JsonObject customer = JsonParser.parseString(event.getActionData()).getAsJsonObject();
          CustomerForm dialog = new CustomerForm(customer);
          self.add(dialog);
          dialog.onSave(() -> {
            Gson gson = new Gson();
            connector.performAction("update-customer", gson.toJson(customer));
          });
          break;
        default:
          break;
      }
    });

    self.add(connector);
  }
}
