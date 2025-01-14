package com.webforj.webswing;

/**
 * Define the options can be modified in the webswing bootstrap.
 *
 * @author Hyyan Abo Fakher
 */
public class WebswingConnectorOptions {
  private boolean autoStart = false;
  private Integer autoReconnect = null;
  private boolean disableLogout = false;
  private boolean disableLogin = false;
  private boolean syncClipboard = false;
  private String securityToken = null;
  private String realm = null;
  private String args = null;
  private boolean recording = false;
  private Integer debugPort = null;
  private int javaCallTimeout = 3000;
  private PingParams pingParams = new PingParams();

  /**
   * Sets whether Webswing should execute the <code>start()</code> method right after the instance
   * is initialized.
   *
   * @param autoStart whether autoStart is enabled
   * @return the options object
   */
  public WebswingConnectorOptions setAutoStart(boolean autoStart) {
    this.autoStart = autoStart;
    return this;
  }

  /**
   * Gets whether Webswing should execute the <code>start()</code> method right after the instance
   * is initialized.
   *
   * @return whether autoStart is enabled
   */
  public boolean isAutoStart() {
    return autoStart;
  }

  /**
   * Sets the number of milliseconds to wait until re-connection attempt in case of connection to
   * server is terminated.
   *
   * @param autoReconnect the number of milliseconds to wait until re-connection attempt
   * @return the options object
   */
  public WebswingConnectorOptions setAutoReconnect(Integer autoReconnect) {
    this.autoReconnect = autoReconnect;
    return this;
  }

  /**
   * Gets the number of milliseconds to wait until re-connection attempt in case of connection to
   * server is terminated.
   *
   * @return the number of milliseconds to wait until re-connection attempt
   */
  public Integer getAutoReconnect() {
    return autoReconnect;
  }

  /**
   * Sets whether the Logout button is removed from all dialogs.
   *
   * @param disableLogout whether the Logout button is removed
   * @return the options object
   */
  public WebswingConnectorOptions setDisableLogout(boolean disableLogout) {
    this.disableLogout = disableLogout;
    return this;
  }

  /**
   * Gets whether the Logout button is removed from all dialogs.
   *
   * @return whether the Logout button is removed
   */
  public boolean isDisableLogout() {
    return disableLogout;
  }

  /**
   * Sets whether the login process is completely disabled.
   *
   * @param disableLogin whether the login process is disabled
   * @return the options object
   */
  public WebswingConnectorOptions setDisableLogin(boolean disableLogin) {
    this.disableLogin = disableLogin;
    return this;
  }

  /**
   * Gets whether the login process is completely disabled.
   *
   * @return whether the login process is disabled
   */
  public boolean isDisableLogin() {
    return disableLogin;
  }

  /**
   * Sets whether synchronization of user's local clipboard with Webswing is enabled (not yet
   * supported in Firefox).
   *
   * @param syncClipboard whether clipboard synchronization is enabled
   * @return the options object
   */
  public WebswingConnectorOptions setSyncClipboard(boolean syncClipboard) {
    this.syncClipboard = syncClipboard;
    return this;
  }

  /**
   * Gets whether synchronization of user's local clipboard with Webswing is enabled (not yet
   * supported in Firefox).
   *
   * @return whether clipboard synchronization is enabled
   */
  public boolean isSyncClipboard() {
    return syncClipboard;
  }

  /**
   * Sets the parameter passed to the security module during authentication.
   *
   * @param securityToken the security token
   * @return the options object
   */
  public WebswingConnectorOptions setSecurityToken(String securityToken) {
    this.securityToken = securityToken;
    return this;
  }

  /**
   * Gets the parameter passed to the security module during authentication.
   *
   * @return the security token
   */
  public String getSecurityToken() {
    return securityToken;
  }

  /**
   * Sets the parameter passed to the security module during authentication.
   *
   * @param realm the realm
   * @return the options object
   */
  public WebswingConnectorOptions setRealm(String realm) {
    this.realm = realm;
    return this;
  }

  /**
   * Gets the parameter passed to the security module during authentication.
   *
   * @return the realm
   */
  public String getRealm() {
    return realm;
  }

  /**
   * Sets additional Java application arguments. Value of this option is available in Webswing
   * config using the ${customArgs} variable.
   *
   * @param args the additional Java application arguments
   * @return the options object
   */
  public WebswingConnectorOptions setArgs(String args) {
    this.args = args;
    return this;
  }

  /**
   * Gets additional Java application arguments. Value of this option is available in Webswing
   * config using the ${customArgs} variable.
   *
   * @return the additional Java application arguments
   */
  public String getArgs() {
    return args;
  }

  /**
   * Sets whether this application session is recorded (default: false).
   *
   * @param recording whether the session is recorded
   * @return the options object
   */
  public WebswingConnectorOptions setRecording(boolean recording) {
    this.recording = recording;
    return this;
  }

  /**
   * Gets whether this application session is recorded (default: false).
   *
   * @return whether the session is recorded
   */
  public boolean isRecording() {
    return recording;
  }

  /**
   * Sets the integer that specifies on which port should the debugger listen to. See development
   * docs for more information.
   *
   * @param debugPort the debug port
   * @return the options object
   */
  public WebswingConnectorOptions setDebugPort(Integer debugPort) {
    this.debugPort = debugPort;
    return this;
  }

  /**
   * Gets the integer that specifies on which port should the debugger listen to. See development
   * docs for more information.
   *
   * @return the debug port
   */
  public Integer getDebugPort() {
    return debugPort;
  }

  /**
   * Sets the JsLink Java method invocation timeout. If Java method is not returned with a result,
   * an error is logged to the browser console.
   *
   * @param javaCallTimeout the Java method invocation timeout
   * @return the options object
   */
  public WebswingConnectorOptions setJavaCallTimeout(int javaCallTimeout) {
    this.javaCallTimeout = javaCallTimeout;
    return this;
  }

  /**
   * Gets the JsLink Java method invocation timeout. If Java method is not returned with a result,
   * an error is logged to the browser console.
   *
   * @return the Java method invocation timeout
   */
  public int getJavaCallTimeout() {
    return javaCallTimeout;
  }

  /**
   * Sets the setup parameters for checking connection stability.
   *
   * @param pingParams the ping parameters
   * @return the options object
   */
  public WebswingConnectorOptions setPingParams(PingParams pingParams) {
    this.pingParams = pingParams;
    return this;
  }

  /**
   * Gets the setup parameters for checking connection stability.
   *
   * @return the ping parameters
   */
  public PingParams getPingParams() {
    return pingParams;
  }

  public static class PingParams {
    private int count = 6;
    private int interval = 5;
    private int maxLatency = 500;
    private int notifyIf = 3;

    /**
     * Sets the number of ping attempts.
     *
     * @param count the number of ping attempts
     * @return the options object
     */
    public PingParams setCount(int count) {
      this.count = count;
      return this;
    }

    /**
     * Gets the number of ping attempts.
     *
     * @return the number of ping attempts
     */
    public int getCount() {
      return count;
    }

    /**
     * Sets the interval between ping attempts in milliseconds.
     *
     * @param interval the interval between ping attempts
     * @return the options object
     */
    public PingParams setInterval(int interval) {
      this.interval = interval;
      return this;
    }

    /**
     * Gets the interval between ping attempts in milliseconds.
     *
     * @return the interval between ping attempts
     */
    public int getInterval() {
      return interval;
    }

    /**
     * Sets the maximum acceptable latency in milliseconds.
     *
     * @param maxLatency the maximum acceptable latency
     * @return the options object
     */
    public PingParams setMaxLatency(int maxLatency) {
      this.maxLatency = maxLatency;
      return this;
    }

    /**
     * Gets the maximum acceptable latency in milliseconds.
     *
     * @return the maximum acceptable latency
     */
    public int getMaxLatency() {
      return maxLatency;
    }

    /**
     * Sets the value in milliseconds to notify if latency exceeds.
     *
     * @param notifyIf the value to notify if latency exceeds
     * @return the options object
     */
    public PingParams setNotifyIf(int notifyIf) {
      this.notifyIf = notifyIf;
      return this;
    }

    /**
     * Gets the value in milliseconds to notify if latency exceeds.
     *
     * @return the value to notify if latency exceeds
     */
    public int getNotifyIf() {
      return notifyIf;
    }
  }
}
