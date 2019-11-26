package controllers;

import enumtypes.CurrencyCodeTableA;
import enumtypes.CurrencyCodeTableB;
import enumtypes.CurrencyCodeTableC;
import http.Gold;
import http.TableA;
import http.TableB;
import http.TableC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import models.gold.ArrayOfGoldPrice;
import models.gold.GoldPrice;
import models.rates.ExchangeRatesSeries;
import models.rates.Rate;
import models.ratesc.ExchangeRatesSeriesC;
import models.ratesc.RateC;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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
        tableA(cbCurrencyA.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    void cbTablesOnAction() {
        addItemsToCbCurrency();

        if (cbTables.getValue().equals("Table A")) {
            tableA(cbCurrencyA.getSelectionModel().getSelectedItem().toString());
        } else if (cbTables.getValue().equals("Table B")) {
            tableB(cbCurrencyB.getSelectionModel().getSelectedItem().toString());
        } else if (cbTables.getValue().equals("Table C")) {
            tableC(cbCurrencyC.getSelectionModel().getSelectedItem().toString());
        } else {
            goldPrice();
        }
    }

    private void addItemsToCbTables() {
        cbTables.getItems().addAll("Table A", "Table B", "Table C", "Gold Price");
        cbTables.getSelectionModel().selectFirst();
        addItemsToCbCurrency();
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
                tableA(cbCurrencyA.getSelectionModel().getSelectedItem().toString());
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
                tableB(cbCurrencyB.getSelectionModel().getSelectedItem().toString());
            });

        } else if (cbTables.getValue().equals("Table C")) {
            cbCurrencyC = new ComboBox();
            cbCurrencyC.setPrefWidth(100.0);
            cbCurrencyC.getItems().addAll(CurrencyCodeTableC.values());
            cbCurrencyC.getSelectionModel().selectFirst();
            hBox.getChildren().add(cbCurrencyC);
            hBox.getChildren().remove(cbCurrencyA);
            hBox.getChildren().remove(cbCurrencyB);
            cbCurrencyC.setOnAction(e -> {
                lineChart.getData().clear();
                tableC(cbCurrencyC.getSelectionModel().getSelectedItem().toString());
            });
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
            numberAxis.setLabel("PLN (złoty)");

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

            lineChart.getData().clear();
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
            numberAxis.setLabel("PLN (złoty)");

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

            lineChart.getData().clear();
            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tableC(String code) {
        try {
            CurrencyCodeTableC currencyCodeTableC = CurrencyCodeTableC.valueOf(code);

            ExchangeRatesSeriesC exchangeRatesSeriesC = new TableC().currencyExchangeRate(currencyCodeTableC);

            List<RateC> listBid = exchangeRatesSeriesC.getRates();
            List<Double> bid = new ArrayList<>();
            for (RateC bids : listBid) {
                bid.add(bids.getBid());
            }
            double lowerBid = Collections.min(bid);
            double upperBid = Collections.max(bid);

            List<RateC> listAsk = exchangeRatesSeriesC.getRates();
            List<Double> ask = new ArrayList<>();
            for (RateC asks : listAsk) {
                ask.add(asks.getBid());
            }
            double lowerAsk = Collections.min(ask);
            double upperAsk = Collections.max(ask);

            List<Double> average = new ArrayList<>();
            average.add(lowerBid);
            average.add(lowerAsk);
            average.add(upperBid);
            average.add(upperAsk);

            double lower = Collections.min(average);
            double upper = Collections.max(average);

            numberAxis.setLowerBound(lower);
            numberAxis.setUpperBound(upper);
            numberAxis.setLabel("PLN (złoty)");

            XYChart.Series dataSeriesBid = new XYChart.Series();
            dataSeriesBid.setName(code + " - buy prices  ");
            for (RateC element : exchangeRatesSeriesC.getRates()) {
                dataSeriesBid.getData().add(new XYChart.Data<>(element.getEffectiveDate(), element.getBid()));
            }

            XYChart.Series dataSeriesAsk = new XYChart.Series();
            dataSeriesAsk.setName(code + " - sell prices");
            for (RateC element : exchangeRatesSeriesC.getRates()) {
                dataSeriesAsk.getData().add(new XYChart.Data<>(element.getEffectiveDate(), element.getAsk()));
            }

            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().clear();
            lineChart.getData().addAll(dataSeriesBid, dataSeriesAsk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goldPrice() {
        try {
            ArrayOfGoldPrice arrayOfGoldPrice = new Gold().allGoldPrice();

            List<GoldPrice> list = arrayOfGoldPrice.getGoldQuotations();
            List<Double> price = new ArrayList<>();
            for (GoldPrice prices : list) {
                price.add(prices.getPrice());
            }
            double lower = Collections.min(price);
            double upper = Collections.max(price);

            numberAxis.setLowerBound(lower);
            numberAxis.setUpperBound(upper);
            numberAxis.setLabel("PLN (złoty)");

            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName("Gold prices");
            for (GoldPrice element : arrayOfGoldPrice.getGoldQuotations()) {
                dataSeries.getData().add(new XYChart.Data<>(String.valueOf(element.getDate()), element.getPrice()));
            }
            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().clear();
            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}