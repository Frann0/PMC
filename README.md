# PMC
Private Movie Collection - 1 semester Eksamen 

## Install
To run this program, you must set the following VM options.

```java
--module-path
${PATH_TO_FX}
--add-modules
javafx.controls,javafx.fxml,javafx.media
--add-exports
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
--add-opens
"javafx.graphics/javafx.css=ALL-UNNAMED"
```

and use following maven libraries:

* com.jfoenix:jfoenix:9.0.10
* de.jensd:fontawesomefx-commons:11.0
* de.jensd:fontawesomefx-controls:11.0
* de.jensd:fontawesomefx-fontawesome:4.7.0-9.1.2
* de.jensd:fontawesomefx-materialdesignfont:2.0.26-9.1.2
