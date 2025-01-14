package com.webforj.webswing.demo;

import com.webforj.App;
import com.webforj.annotation.AppTitle;
import com.webforj.annotation.Routify;
import com.webforj.annotation.StyleSheet;

@Routify(packages = "com.webforj.webswing.demo.views")
@AppTitle("Webswing")
@StyleSheet("ws://app.css")
public class Application extends App {
}
