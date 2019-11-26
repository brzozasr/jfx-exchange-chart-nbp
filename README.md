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
Table A
```java
try {
        CurrencyCodeTableA currencyCodeTableA = CurrencyCodeTableA.valueOf(code);

        ExchangeRatesSeries exchangeRatesSeries = new TableA().currencyExchangeRate(currencyCodeTableA);

        numberAxis.setLowerBound(exchangeRatesSeries.getMin());
        numberAxis.setUpperBound(exchangeRatesSeries.getMax());
        numberAxis.setLabel("PLN (złoty)");

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName(code);
        for (Rate element : exchangeRatesSeries.getRates()) {
           dataSeries.getData().add(new XYChart.Data<>(String.valueOf(element.getEffectiveDate()), element.getMid()));
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
```
