# Quản lý đề tài nghiên cứu khoa học

## Require to run project
- [JavaFX javafx-sdk-21.0.2](https://gluonhq.com/products/javafx/)
- [FontAwesomeIcon 8.2](https://jar-download.com/artifacts/de.jensd/fontawesomefx/8.2/source-code)
- [MySQL Connector](https://dev.mysql.com/downloads/connector/j/)
- [Scene Builder](https://gluonhq.com/products/scene-builder/#download)
- If you use VsCode, add config below to your `launch.json`
```json
"vmArgs": "--module-path 'YOUR JAVAFX LIB PATH' --add-modules javafx.controls,javafx.fxml",
```
Example:
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "App",
            "request": "launch",
            "mainClass": "App",
            "projectName": "QuanLyDeTai_e37fdbc",
            "vmArgs": "--module-path 'C:/Program Files/Java/javafx-sdk-21.0.1/lib' --add-modules javafx.controls,javafx.fxml",
        }
    ]
}