import { LitElement, html } from 'lit'
import { customElement, property } from 'lit/decorators.js'
import { getWebswingApi } from 'webswing-api';
import { IWebswingInstance, WebswingOptions } from 'webswing-api/build/types';

/**
 * A custom element that wraps a Webswing instance.
 *
 * @attr {String} url - The URL of the Webswing server.
 * @attr {Object} options - The options for initializing the Webswing instance.
 *
 * @fires webswing-initialized - Fired when the Webswing instance is initialized.
 * @fires webswing-started - Fired when the Webswing instance is started.
 * @fires webswing-action - Fired when an action is performed on the Webswing instance.
 */
@customElement("webswing-connector")
export class WebswingElement extends LitElement {
  private instance: IWebswingInstance | null = null;
  private started: boolean = false;
  private initId: number = 0;

  /**
   * The URL of the Webswing server.
   */
  @property({ type: String })
  url: string | null = null;

  /**
   * The options for initializing the Webswing instance.
   */
  @property({ type: Object })
  options: WebswingOptions | null = null;

  /**
   * {@inheritDoc}
   */
  connectedCallback() {
    super.connectedCallback();
    this.style.display = 'block';
  }

  /**
   * {@inheritDoc}
   */
  disconnectedCallback() {
    super.disconnectedCallback();
    if (this.instance != null) {
      this.instance.disconnect();
    }
    this.instance = null;
    this.started = false;
  }

  /**
   * This will initiate the connection to Webswing server and start the Swing application.
   * If the autoStart is set to false or not defined in config object, the start function has
   * to be called manually, otherwise the Webswing will call start function automatically.
   */
  public start() {
    if (this.started) {
      throw new Error("Instance already started!");
    }

    if (this.instance == null) {
      throw new Error("No instance initialized!");
    }

    this.started = true;
    this.instance.start();
  }

  /**
   * Perform an action that triggers server-side listener.
   *
   * @param actionName The name of the action to perform.
   * @param data The data to send with the action.
   * @param binaryDataBase64 The binary data to send with the action, encoded in Base64.
   */
  public performAction(actionName: string, data: string | undefined, binaryDataBase64: string | null) {
    const binaryData = this.decode(binaryDataBase64) || undefined;
    this.instance?.performAction({ actionName, data, binaryData });
  }

  /**
   * {@inheritDoc}
   */
  protected firstUpdated() {
    this.initialize();
  }

  /**
   * {@inheritDoc}
   */
  protected createRenderRoot() {
    return this;
  }

  /**
   * {@inheritDoc}
   */
  protected render() {
    return html`<div class="webswing-root"></div>`;
  }

  private initialize() {
    const url = this.url!;
    const opts = this.options;

    const thisInitId = ++this.initId;
    getWebswingApi(url).then((api) => {
      if (this.initId != thisInitId) {
        return;
      }

      let startupOptions = {
        connectionUrl: url,
        autoStart: false,
        onStart: () => {
          this.dispatchEvent(new Event('webswing-started', { bubbles: true, composed: true }));
        }
      };

      if (opts != null) {
        startupOptions = { ...startupOptions, ...opts };
      }

      this.instance = api.bootstrap(this.querySelector(".webswing-root")!, startupOptions, (injector) => {
        injector.services.base!.handleActionEvent = (actionName: string, data: string | null, binaryData: Uint8Array | null) => {
          const binaryDataBase64 = this.encode(binaryData);
          this.dispatchEvent(new CustomEvent('webswing-action', {
            detail: {
              actionName,
              data,
              binaryDataBase64
            },
            bubbles: true,
            composed: true
          }));
        }

        requestAnimationFrame(() => {
          this.dispatchEvent(new Event('webswing-initialized', { bubbles: true, composed: true }));
        });
      });
    })
      .catch((e) => {
        throw new Error(`Failed to initialize Webswing: ${e.message}`);
      });
  }

  private encode(binaryData: Uint8Array | null) {
    if (binaryData == null) {
      return null;
    }

    const decoder = new TextDecoder('utf8');
    return btoa(decoder.decode(binaryData));
  }

  private decode(binaryDataBase64: string | null) {
    if (binaryDataBase64 == null) {
      return null;
    }

    return new Uint8Array(atob(binaryDataBase64).split("").map(function (c) {
      return c.charCodeAt(0);
    }));
  }
}
