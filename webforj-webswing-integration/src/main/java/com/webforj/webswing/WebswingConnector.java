package com.webforj.webswing;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.webforj.annotation.Attribute;
import com.webforj.annotation.JavaScript;
import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.PropertyDescriptor;
import com.webforj.component.element.annotation.NodeName;
import com.webforj.concern.HasClassName;
import com.webforj.concern.HasSize;
import com.webforj.concern.HasStyle;
import com.webforj.dispatcher.EventListener;
import com.webforj.dispatcher.ListenerRegistration;
import com.webforj.webswing.event.WebswingConnectorInitializedEvent;
import com.webforj.webswing.event.WebswingConnectorStartedEvent;
import com.webforj.webswing.event.WebswingConnectorActionEvent;

@JavaScript(value = "ws://webswing-connector.js",
    attributes = {@Attribute(name = "type", value = "module")})
@NodeName("webswing-connector")
public class WebswingConnector extends ElementComposite implements HasSize<WebswingConnector>,
    HasStyle<WebswingConnector>, HasClassName<WebswingConnector> {

  private PropertyDescriptor<String> urlProp = PropertyDescriptor.property("url", "");
  private PropertyDescriptor<WebswingConnectorOptions> optionsProp =
      PropertyDescriptor.property("options", null);

  /**
   * Creates a new instance of the webswing connector.
   *
   * @param url the url of the webswing server
   * @param autoStart whether to start the webswing server automatically
   */
  public WebswingConnector(String url, boolean autoStart) {
    super();
    setUrl(url);
    setOptions(new WebswingConnectorOptions().setAutoStart(autoStart));
  }

  /**
   * Creates a new instance of the webswing connector.
   *
   * @param url the url of the webswing server
   */
  public WebswingConnector(String url) {
    this(url, true);
  }

  /**
   * Sets the url of the webswing server.
   *
   * @param url the url of the webswing server
   * @return the component itself
   */
  public WebswingConnector setUrl(String url) {
    set(urlProp, url);
    return this;
  }

  /**
   * Gets the url of the webswing server.
   *
   * @return the url of the webswing server
   */
  public String getUrl() {
    return get(urlProp);
  }

  /**
   * Sets the options of the webswing connector.
   *
   * @param options the options of the webswing connector
   * @return the component itself
   */
  public WebswingConnector setOptions(WebswingConnectorOptions options) {
    set(optionsProp, options);
    return this;
  }

  /**
   * Gets the options of the webswing connector.
   *
   * @return the options of the webswing connector
   */
  public WebswingConnectorOptions getOptions() {
    return get(optionsProp);
  }

  /**
   * This will initiate the connection to Webswing server and start the Swing application.
   *
   * <p>
   * If the autoStart is set to false or not defined in config object, the start method has to be
   * called manually, otherwise the Webswing will call start method automatically.
   * </p>
   *
   * @return the component itself
   */
  public WebswingConnector start() {
    getElement().callJsFunctionVoidAsync("start");
    return this;
  }

  /**
   * Perform an action that triggers server-side listener.
   *
   * @param actionName the name of the action
   * @param data the data to send to the server
   * @param binaryData the binary data to send to the server
   *
   * @return the component itself
   */
  public WebswingConnector performAction(String actionName, String data, String binaryData) {
    String encodedBinaryData = binaryData;
    if (binaryData != null) {
      encodedBinaryData =
          new String(Base64.getEncoder().encode(binaryData.getBytes(StandardCharsets.UTF_8)),
              StandardCharsets.UTF_8);
    }

    getElement().callJsFunctionVoidAsync("performAction", actionName, data, encodedBinaryData);
    return this;
  }

  /**
   * Perform an action that triggers server-side listener.
   *
   * @param actionName the name of the action
   * @param data the data to send to the server
   *
   * @return the component itself
   */
  public WebswingConnector performAction(String actionName, String data) {
    return performAction(actionName, data, "");
  }

  /**
   * Perform an action that triggers server-side listener.
   *
   * @param actionName the name of the action
   * @return the component itself
   */
  public WebswingConnector performAction(String actionName) {
    return performAction(actionName, "");
  }

  /**
   * Adds a listener for the {@link WebswingConnectorInitializedEvent} event.
   *
   * @param listener the listener
   * @return A registration object for removing the event listener
   */
  public ListenerRegistration<WebswingConnectorInitializedEvent> addInitializedListener(
      EventListener<WebswingConnectorInitializedEvent> listener) {
    return addEventListener(WebswingConnectorInitializedEvent.class, listener);
  }

  /**
   * Alias for {@link #addInitializedListener(EventListener)}.
   *
   * @param listener the listener
   * @return A registration object for removing the event listener
   */
  public ListenerRegistration<WebswingConnectorInitializedEvent> onInitialized(
      EventListener<WebswingConnectorInitializedEvent> listener) {
    return addEventListener(WebswingConnectorInitializedEvent.class, listener);
  }

  /**
   * Adds a listener for the {@link WebswingConnectorStartedEvent} event.
   *
   * @param listener the listener
   * @return A registration object for removing the event listener
   */
  public ListenerRegistration<WebswingConnectorStartedEvent> addStartedListener(
      EventListener<WebswingConnectorStartedEvent> listener) {
    return addEventListener(WebswingConnectorStartedEvent.class, listener);
  }

  /**
   * Alias for {@link #addStartedListener(EventListener)}.
   *
   * @param listener the listener
   * @return A registration object for removing the event listener
   */
  public ListenerRegistration<WebswingConnectorStartedEvent> onStarted(
      EventListener<WebswingConnectorStartedEvent> listener) {
    return addEventListener(WebswingConnectorStartedEvent.class, listener);
  }

  /**
   * Adds a listener for the {@link WebswingConnectorActionEvent} event.
   *
   * @param listener the listener
   * @return A registration object for removing the event listener
   */
  public ListenerRegistration<WebswingConnectorActionEvent> addActionListener(
      EventListener<WebswingConnectorActionEvent> listener) {
    return addEventListener(WebswingConnectorActionEvent.class, listener);
  }

  /**
   * Alias for {@link #addActionListener(EventListener)}.
   *
   * @param listener the listener
   * @return A registration object for removing the event listener
   */
  public ListenerRegistration<WebswingConnectorActionEvent> onAction(
      EventListener<WebswingConnectorActionEvent> listener) {
    return addEventListener(WebswingConnectorActionEvent.class, listener);
  }
}
