package com.epam.code;

/**
 * Created by cn133244 on 1/30/2018.
 */
public enum SpotRate {
    USD("USD"), CAN("CAN"), HKD("HKD"), SAR("SAR"), GBP("GBP"), EUR("EUR"), INR("INR"), AUD("AUD"), NZD("NZD"), MXN("MXN");

    private String name;
    SpotRate(String name){
        this.name=name;
    }

    /**
     * returns the corresponding exchange rate for given currency
     * @param currency
     * @return
     */
    public static Double getExchangeRate(String currency) {
        Double exchangeRate;
        currency=currency.trim();
        switch (currency) {
            case "USD":
                exchangeRate = 1.0;
                break;
            case "CAN":
                exchangeRate = 0.81080;
                break;
            case "HKD":
                exchangeRate = 0.128983;
                break;
            case "SAR":
                exchangeRate = 0.266645;
                break;
            case "GBP":
                exchangeRate = 1.57217;
                break;
            case "EUR":
                exchangeRate = 1.11598;
                break;
            case "INR":
                exchangeRate = 0.0157311;
                break;
            case "AUD":
                exchangeRate = 0.772744;
                break;
            case "NZD":
                exchangeRate = 0.684621;
                break;
            case "MXN":
                exchangeRate = 0.0648290;
                break;
            default:
                exchangeRate = 1.0;
        }
        return exchangeRate;
    }

    public String getName() {
        return name;
    }
}
