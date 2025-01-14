package com.webforj.webswing.event;

import java.util.Map;

import com.webforj.component.element.annotation.EventName;
import com.webforj.component.event.ComponentEvent;
import com.webforj.webswing.WebswingConnector;

/**
 * An event that is fired when the Webswing instance is initialized.
 *
 * @author Hyyan Abo Fakher
 */
@EventName("webswing-initialized")
public class WebswingConnectorInitializedEvent extends ComponentEvent<WebswingConnector> {

  /**
   * Creates a new webswing initialized event.
   *
   * @param component the component
   * @param eventMap the event map
   */
  public WebswingConnectorInitializedEvent(WebswingConnector component,
      Map<String, Object> eventMap) {
    super(component, eventMap);
  }
}
