package edu.capella.bsit.openweather;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * JavaFX App
 */

// Sample URL: https://api.openweathermap.org/data/2.5/weather?lat=44.9778&lon=-93.2650&appid=efa9914c7b90e2908ea267c546035a3f

public class App extends Application {
    Label weatherInfo = new Label();
    private final String serverAddr = "api.openweathermap.org";
    private final String appPath = "/data/2.5/weather";
    private final String queryStringFormat = "lat=%f&lon=%f&appid=%s";
    // Add OpenWeatherMap API key
    private final String apiKey = "2439a13dd32d47e2f40fd3a319a52642";

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 15, 15, 15));
        Label latPrompt = new Label("Latititude:  ");
        latPrompt.setFont(Font.font(15));
        latPrompt.setAlignment(Pos.BOTTOM_RIGHT);
        TextField latInputField = new TextField();
        latInputField.setFont(Font.font(15));
        latInputField.setAlignment(Pos.BASELINE_LEFT);
        HBox latHBox = new HBox(latPrompt, latInputField);
        latHBox.setPadding(new Insets(10, 10, 10, 10));
        Label lonPrompt = new Label("Longitude:  ");
        lonPrompt.setFont(Font.font(15));
        lonPrompt.setAlignment(Pos.BOTTOM_RIGHT);
        TextField lonInputField = new TextField();
        lonInputField.setFont(Font.font(15));
        lonInputField.setAlignment(Pos.BASELINE_LEFT);
        HBox lonHBox = new HBox(lonPrompt, lonInputField);
        lonHBox.setPadding(new Insets(10, 10, 10, 10));
        Button loadDataButton = new Button("Get Data");
        grid.add(latHBox, 0, 0);
        grid.add(lonHBox, 1, 0);
        grid.add(loadDataButton, 2, 0);

        weatherInfo.setPadding(new Insets(20, 20, 20, 20));
        weatherInfo.setFont(Font.font("Monospaced", FontWeight.BOLD, FontPosture.REGULAR, 18));
        weatherInfo.setLineSpacing(1.25);
        grid.add(weatherInfo, 0, 1, 2, 1);
        var scene = new Scene(grid, 650, 400);
        stage.setScene(scene);
        stage.show();
        
        loadDataButton.setOnAction( e -> {
            // Complete this lambda expression as specified in the 
            // assignment instructions. 

        });
    }
    
    public Scanner connectToOpenWeatherServer(double lat, double lon, 
            String apiKey) {
        Scanner scanner = null; 
        try {
            // Connect using HTTP port 80
            Socket socket = new Socket(serverAddr, 80);
            // Stream to send data out through socket
            PrintStream outToSocket = new PrintStream(socket.getOutputStream());
            // Stream to read data in through socket
            BufferedReader inFromSocket = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            // Assemble request string to send to server
            String request = "GET /api/lat/lon" + " HTTP/1.0";
            // Send request out through socket
            outToSocket.println(request);
            // Blank line to mark end of request
            outToSocket.println();
            
            String line;
            int httpResponseCode = 0;
            // Read response data in from socket
            while((line = inFromSocket.readLine()) != null) {
                if(line.startsWith("HTTP/1.")) {
                    String[] httpResponseParts = line.split(" ");
                    httpResponseCode = Integer.parseInt(httpResponseParts[1]);
                }
                // Watch for blank line in response
                if(httpResponseCode == 200) {
                    if(line.isEmpty()) {
                        // Line following the blank line contains JSON data
                        scanner = new Scanner(inFromSocket.readLine());
                    }
                }
                else {
                    System.out.println("HTTP error: " + httpResponseCode);
                }
            }
            // Close streams and socket
            inFromSocket.close();
            outToSocket.close();
            socket.close();
        }
        catch(UnknownHostException ex) {
            System.out.println("Can't connect to host: " + ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println("Connection error: " + ex.getMessage());
        }
        return scanner;
    }

    public static void main(String[] args) {
        launch();
    }
}