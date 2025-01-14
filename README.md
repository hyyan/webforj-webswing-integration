# webforJ + Webswing 



https://github.com/user-attachments/assets/9f86eda1-54b2-4022-9a9e-142d9a68ee98



## 1. webforj-webswing-integration

`webforj-webswing-integration` is a webforj addon that introduces the `WebswingConnector` component. 

First, build and install the addon in your Maven local repository:

```sh
mvn clean install
```

### 2. webforj-swing-app

A simple Java Swing application that features a customer table. The app uses the Webswing API to send and receive browser actions. 
For more information, see the [Java API documentation](https://www.webswing.org/docs/24.2/integrate/api).

You need to deploy the app to the Webswing server.

1. Build the application:
```sh
mvn clean package
```
2. Copy the JAR file:
   Copy the JAR file (`webforj-swing-app-jar-with-dependencies.jar`) to the `webswing/apps/webforj-swing-app` folder.
3. Configure the app in the Webswing admin panel and save it with the following settings:
   - Name: `webforj-swing-app`
   - CORS Origins: `*`
   - Class Path: `*.jar`
   - Main class: `com.webforj.swingapp.Application`

### 3. webforj-webswing-integration-demo

`webforj-webswing-integration-demo` is a simple webforj app that uses the `WebswingConnector` component.

To run the demo:

```sh
mvn jetty:run
```

Then open your browser and go to [http://localhost:7070](http://localhost:7070).
