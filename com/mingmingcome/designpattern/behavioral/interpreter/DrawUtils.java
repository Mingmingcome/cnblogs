package com.mingmingcome.designpattern.behavioral.interpreter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DrawUtils extends Application {

    //defining a series
    private static List<XYChart.Series<Double, Double>> seriesList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        yAxis.setTickLabelFont(Font.font("serif", FontWeight.BOLD, 14));
        xAxis.setTickLabelFont(Font.font("serif", FontWeight.BOLD, 14));

        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);

        Scene scene = new Scene(lineChart, 1000, 800);
        for (XYChart.Series series : seriesList) {
            lineChart.getData().add(series);
        }


        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif';");
        stage.show();
    }


    public static void addXYChartData(XYChart.Series<Double, Double> series, double x, double y) {
        //populating the series with data
        XYChart.Data<Double, Double> data = new XYChart.Data<>(x, y);
        series.getData().add(data);
    }

    public static XYChart.Series<Double, Double> newSeries() {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        seriesList.add(series);
        return series;
    }

    public static void draw() {
        launch();
    }

    public static void main(String[] args) {
        XYChart.Series<Double, Double> series = newSeries();
        addXYChartData(series, -3, 12);
        addXYChartData(series, -2, 11);
        draw();
    }
}
