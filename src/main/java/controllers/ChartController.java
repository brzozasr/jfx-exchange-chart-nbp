package controllers;

import enumtypes.CurrencyCodeTableA;
import enumtypes.CurrencyCodeTableB;
import enumtypes.CurrencyCodeTableC;
import http.TableA;
import http.TableB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import models.rates.ExchangeRatesSeries;
import models.rates.Rate;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ChartController implements Initializable {
    @FXML
    private ComboBox<String> cbTables;

    @FXML
    private HBox hBox;

    @FXML
    private ComboBox<CurrencyCodeTableA> cbCurrencyA;

    @FXML
    private ComboBox<CurrencyCodeTableB> cbCurrencyB;

    @FXML
    private ComboBox<CurrencyCodeTableC> cbCurrencyC;

    @FXML
    private LineChart lineChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addItemsToCbTables();
        addItemsToCbCurrency();
        tableA(cbCurrencyA.getSelectionModel().getSelectedItem().name());
    }

    @FXML
    void changeCbOnAction() {
        addItemsToCbCurrency();
    }

    private void addItemsToCbTables() {
        cbTables.getItems().addAll("Table A", "Table B", "Table C", "Gold Price");
        cbTables.getSelectionModel().selectFirst();
    }

    private void addItemsToCbCurrency() {

        if (cbTables.getValue().equals("Table A")) {
            cbCurrencyA = new ComboBox();
            cbCurrencyA.setPrefWidth(100.0);
            cbCurrencyA.getItems().addAll(CurrencyCodeTableA.values());
            cbCurrencyA.getSelectionModel().selectFirst();
            hBox.getChildren().add(cbCurrencyA);
            hBox.getChildren().remove(cbCurrencyB);
            hBox.getChildren().remove(cbCurrencyC);
            cbCurrencyA.setOnAction(e -> {
                lineChart.getData().clear();
                tableA(cbCurrencyA.getSelectionModel().getSelectedItem().name());
            });

        } else if (cbTables.getValue().equals("Table B")) {
            cbCurrencyB = new ComboBox();
            cbCurrencyB.setPrefWidth(100.0);
            cbCurrencyB.getItems().addAll(CurrencyCodeTableB.values());
            cbCurrencyB.getSelectionModel().selectFirst();
            hBox.getChildren().add(cbCurrencyB);
            hBox.getChildren().remove(cbCurrencyA);
            hBox.getChildren().remove(cbCurrencyC);
            cbCurrencyB.setOnAction(e -> {
                lineChart.getData().clear();
                tableB(cbCurrencyB.getSelectionModel().getSelectedItem().name());
            });

        } else if (cbTables.getValue().equals("Table C")) {
            cbCurrencyC = new ComboBox();
            cbCurrencyC.setPrefWidth(100.0);
            cbCurrencyC.getItems().addAll(CurrencyCodeTableC.values());
            cbCurrencyC.getSelectionModel().selectFirst();
            hBox.getChildren().add(cbCurrencyC);
            hBox.getChildren().remove(cbCurrencyA);
            hBox.getChildren().remove(cbCurrencyB);
        } else {
            hBox.getChildren().remove(cbCurrencyA);
            hBox.getChildren().remove(cbCurrencyB);
            hBox.getChildren().remove(cbCurrencyC);
        }
    }

    private void tableA(String code) {
        try {
            CurrencyCodeTableA currencyCodeTableA = CurrencyCodeTableA.valueOf(code);

            ExchangeRatesSeries exchangeRatesSeries = new TableA().currencyExchangeRate(currencyCodeTableA);

            List<Rate> list = exchangeRatesSeries.getRates();
            List<Double> mid = new ArrayList<>();
            for (Rate mids : list) {
                mid.add(mids.getMid());
            }
            double lower = Collections.min(mid);
            double upper = Collections.max(mid);

            numberAxis.setLowerBound(lower);
            numberAxis.setUpperBound(upper);

            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(code);
            for (Rate element : exchangeRatesSeries.getRates()) {
                dataSeries.getData().add(new XYChart.Data<>(element.getEffectiveDate(), element.getMid()));
            }
            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tableB(String code) {
        try {
            CurrencyCodeTableB currencyCodeTableB = CurrencyCodeTableB.valueOf(code);

            ExchangeRatesSeries exchangeRatesSeries = new TableB().currencyExchangeRate(currencyCodeTableB);

            List<Rate> list = exchangeRatesSeries.getRates();
            List<Double> mid = new ArrayList<>();
            for (Rate mids : list) {
                mid.add(mids.getMid());
            }
            double lower = Collections.min(mid);
            double upper = Collections.max(mid);

            numberAxis.setLowerBound(lower);
            numberAxis.setUpperBound(upper);

            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(code);
            for (Rate element : exchangeRatesSeries.getRates()) {
                dataSeries.getData().add(new XYChart.Data<>(element.getEffectiveDate(), element.getMid()));
            }
            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
