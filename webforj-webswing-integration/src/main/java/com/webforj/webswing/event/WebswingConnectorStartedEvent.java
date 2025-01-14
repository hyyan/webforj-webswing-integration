package com.webforj.webswing.event;

import java.util.Map;

import com.webforj.component.element.annotation.EventName;
import com.webforj.component.event.ComponentEvent;
import com.webforj.webswing.WebswingConnector;

/**
 * An event that is fired when the Webswing instance is started.
 *
 * @author Hyyan Abo Fakher
 */
@EventName("webswing-started")
public class WebswingConnectorStartedEvent extends ComponentEvent<WebswingConnector> {

  /**
   * Creates a new webswing started event.
   *
   * @param component the component
   * @param eventMap the event map
   */
  public WebswingConnectorStartedEvent(WebswingConnector component, Map<String, Object> eventMap) {
    super(component, eventMap);
  }
}
