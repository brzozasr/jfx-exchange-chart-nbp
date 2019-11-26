# jfx-exchange-chart-nbp

#### An example of using the repository (exchange-rates-nbp API for Java) with JavaFx /LineChart/ (exchange rates and gold prices NBP /Narodowy Bank Polski, National Bank of Poland/ API).
***
### License
jfx-exchange-chart-nbp is licensed under **Apache Software License, Version 2.0**.
***
### News
* Version 1.0-SNAPSHOT released on 26-Nov-2019.
***
### Examples:
Table A:
```java
try {
        private void tableA(String code) {
        try {
            CurrencyCodeTableA currencyCodeTableA = CurrencyCodeTableA.valueOf(code);

            ExchangeRatesSeries exchangeRatesSeries = new TableA().currencyExchangeRate(currencyCodeTableA);

            numberAxis.setLowerBound(exchangeRatesSeries.getMin());
            numberAxis.setUpperBound(exchangeRatesSeries.getMax());
            numberAxis.setLabel("PLN (złoty)");

            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(code);
            for (Rate element : exchangeRatesSeries.getRates()) {
                dataSeries.getData().add(new XYChart.Data<>(
                String.valueOf(element.getEffectiveDate()), element.getMid()));
            }
            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);
            lineChart.setHorizontalGridLinesVisible(true);
            lineChart.setVerticalGridLinesVisible(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().clear();
            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
Table B:
```java
private void tableB(String code) {
        try {
            CurrencyCodeTableB currencyCodeTableB = CurrencyCodeTableB.valueOf(code);

            ExchangeRatesSeries exchangeRatesSeries = new TableB().currencyExchangeRate(currencyCodeTableB);

            numberAxis.setLowerBound(exchangeRatesSeries.getMin());
            numberAxis.setUpperBound(exchangeRatesSeries.getMax());
            numberAxis.setLabel("PLN (złoty)");

            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(code);
            for (Rate element : exchangeRatesSeries.getRates()) {
                dataSeries.getData().add(new XYChart.Data<>(
                String.valueOf(element.getEffectiveDate()), element.getMid()));
            }
            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);
            lineChart.setHorizontalGridLinesVisible(true);
            lineChart.setVerticalGridLinesVisible(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().clear();
            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
Table C:
```java
private void tableC(String code) {
        try {
            CurrencyCodeTableC currencyCodeTableC = CurrencyCodeTableC.valueOf(code);

            ExchangeRatesSeriesC exchangeRatesSeriesC = new TableC().currencyExchangeRate(currencyCodeTableC);

            numberAxis.setLowerBound(exchangeRatesSeriesC.getMin());
            numberAxis.setUpperBound(exchangeRatesSeriesC.getMax());
            numberAxis.setLabel("PLN (złoty)");

            XYChart.Series dataSeriesBid = new XYChart.Series();
            dataSeriesBid.setName(code + " - buy prices  ");
            for (RateC element : exchangeRatesSeriesC.getRates()) {
                dataSeriesBid.getData().add(new XYChart.Data<>(
                String.valueOf(element.getEffectiveDate()), element.getBid()));
            }

            XYChart.Series dataSeriesAsk = new XYChart.Series();
            dataSeriesAsk.setName(code + " - sell prices");
            for (RateC element : exchangeRatesSeriesC.getRates()) {
                dataSeriesAsk.getData().add(new XYChart.Data<>(
                String.valueOf(element.getEffectiveDate()), element.getAsk()));
            }

            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);
            lineChart.setHorizontalGridLinesVisible(true);
            lineChart.setVerticalGridLinesVisible(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().clear();
            lineChart.getData().addAll(dataSeriesBid, dataSeriesAsk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
Gold prices:
```java
private void goldPrice() {
        try {
            ArrayOfGoldPrice arrayOfGoldPrice = new Gold().allGoldPrice();

            numberAxis.setLowerBound(arrayOfGoldPrice.getMin());
            numberAxis.setUpperBound(arrayOfGoldPrice.getMax());
            numberAxis.setLabel("PLN (złoty)");

            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName("Gold prices");
            for (GoldPrice element : arrayOfGoldPrice.getGoldQuotations()) {
                dataSeries.getData().add(new XYChart.Data<>(
                String.valueOf(element.getDate()), element.getPrice()));
            }
            lineChart.setCreateSymbols(false);
            numberAxis.setForceZeroInRange(false);
            lineChart.setHorizontalGridLinesVisible(true);
            lineChart.setVerticalGridLinesVisible(false);

            categoryAxis.setAnimated(false);
            numberAxis.setAnimated(true);
            lineChart.setAnimated(true);

            lineChart.getData().clear();
            lineChart.getData().add(dataSeries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
