/**
 *
 */
package com.epam.code;

/**
 * @author cn133244
 *         Bean to store Account information
 */
public class Account {

    /**
     * Account Id
     */
    private Long id;
    /**
     * country
     */
    private String country;
    /**
     * region
     */
    private String region;
    /**
     * currency
     */
    private String currency;
    /**
     * gross profit for given currency
     */
    private Double grossProfit;

    /**
     * Gross Profit in USD
     */
    private Double grossProfitInUSD;

    /**
     * tax rate
     */
    private Double taxRate;

    /**
     * Net Profit in USD
     */
    private Double netProfitInUSD;

    /**
     * Parameterized constructor
     *
     * @param id
     * @param country
     * @param region
     * @param currency
     * @param grossProfit
     * @param grossProfitInUSD
     * @param taxRate
     * @param netProfitInUSD
     */
    public Account(Long id, String country, String region, String currency, Double grossProfit, Double grossProfitInUSD, Double taxRate, Double netProfitInUSD) {
        super();
        this.id = id;
        this.country = country;
        this.region = region;
        this.currency = currency;
        this.grossProfit = grossProfit;
        this.grossProfitInUSD = grossProfitInUSD;
        this.taxRate = taxRate;
        this.netProfitInUSD = netProfitInUSD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getGrossProfitInUSD() {
        return grossProfitInUSD;
    }

    public void setGrossProfitInUSD(Double grossProfitInUSD) {
        this.grossProfitInUSD = grossProfitInUSD;
    }

    public Double getNetProfitInUSD() {
        return netProfitInUSD;
    }

    public void setNetProfitInUSD(Double netProfitInUSD) {
        this.netProfitInUSD = netProfitInUSD;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", currency='" + currency + '\'' +
                ", grossProfit=" + grossProfit +
                ", grossProfitInUSD=" + grossProfitInUSD +
                ", taxRate=" + taxRate +
                ", netProfitInUSD=" + netProfitInUSD +
                '}';
    }
}
