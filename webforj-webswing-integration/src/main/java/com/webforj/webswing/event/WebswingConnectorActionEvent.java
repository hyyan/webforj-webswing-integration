package com.webforj.webswing.event;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import com.webforj.component.element.annotation.EventName;
import com.webforj.component.element.annotation.EventOptions;
import com.webforj.component.element.annotation.EventOptions.EventData;
import com.webforj.component.event.ComponentEvent;
import com.webforj.webswing.WebswingConnector;

/**
 * An event that is fired when an action is performed on the Webswing instance.
 *
 * @author Hyyan Abo Fakher
 */
@EventName("webswing-action")
@EventOptions(data = {@EventData(key = "actionName", exp = "event.detail.actionName"),
    @EventData(key = "data", exp = "event.detail.data"),
    @EventData(key = "binaryDataBase64", exp = "event.detail.binaryDataBase64"),})
public class WebswingConnectorActionEvent extends ComponentEvent<WebswingConnector> {
  private final String actionName;
  private final String data;
  private final String binaryData;

  /**
   * Creates a new webswing action event.
   *
   * @param component the component
   * @param eventMap the event map
   */
  public WebswingConnectorActionEvent(WebswingConnector component, Map<String, Object> eventMap) {
    super(component, eventMap);
    this.actionName = (String) eventMap.get("actionName");
    this.data = (String) eventMap.get("data");
    this.binaryData = (String) eventMap.get("binaryDataBase64");
  }

  /**
   * Gets the action name.
   *
   * @return the action name
   */
  public String getActionName() {
    return actionName;
  }

  /**
   * Gets the data.
   *
   * @return the data
   */
  public String getActionData() {
    return data;
  }

  /**
   * Gets the binary data.
   *
   * @return the binary data
   */
  public String getActionBinaryData() {
    if (binaryData != null) {
      return new String(Base64.getDecoder().decode(binaryData.getBytes(StandardCharsets.UTF_8)),
          StandardCharsets.UTF_8);
    }

    return null;
  }
}
