package com.epam.code;

import org.apache.log4j.Logger;

import javax.xml.bind.ValidationException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Profit Calculator -
 * Calculates
 * - Total Net Profit (Tax deducted) by Region in USD.
 * - Gross Profit Total by Country in USD.
 * - Determines the most profitable region (before and after tax) in USD
 *
 * @author cn133244
 */
public class ProfitCalculator {

    private static final Logger logger = Logger.getLogger(ProfitCalculator.class);

    public static void main(String... args) {

        CsvFileReader csvFileReader = new CsvFileReader();
        List<Account> accounts;

        //Read file and return the list of Accounts
        String fileName="TestData.csv";

        try {
            accounts = csvFileReader.readAccountsFromCSV(fileName);

            // let's print all the data read from CSV file
            accounts.forEach(account -> System.out.println(account));

            //Distinct Regions
            Set<String> regions = accounts.stream().map(account -> account.getRegion()).collect(Collectors.toSet());

            //Distinct Countries
            Set<String> countries = accounts.stream().map(account -> account.getCountry()).collect(Collectors.toSet());

            //Total Net Profit (Tax deducted) by Region in USD.
            logger.info("------------------------------------");
            logger.info("Total Network profit by region in USD");
            logger.info("------------------------------------");
            Map<String,BigDecimal> netProfitByRegion=new HashMap<>();
            Map<String,BigDecimal> grossProfitByRegion=new HashMap<>();

            regions.stream().sorted().forEach(region -> {
                BigDecimal totalNetProfit=BigDecimal.valueOf(totalNetProfit(accounts, region));
                logger.info(region + "--Net Profit -->" + totalNetProfit);
                netProfitByRegion.put(region,totalNetProfit);

                BigDecimal totalGrossProfit=BigDecimal.valueOf(totalGrossProfitByRegion(accounts, region));
                logger.info(region + "--Gross Profit -->" + totalGrossProfit);
                grossProfitByRegion.put(region,totalGrossProfit);
            });
            logger.info("------------------------------------");
            logger.info("Total Gross profit by Country in USD");
            countries.stream().sorted().forEach(country -> {
                logger.info(country + "---->" + BigDecimal.valueOf(totalGrossProfitByCountry(accounts, country)));
            });
            logger.info("------------------------------------");
            logger.info("Most Profitable Region in USD before Tax=");
            logger.info(grossProfitByRegion.entrySet().stream().max((entry1, entry2) -> entry1.getValue().doubleValue() > entry2.getValue().doubleValue() ? 1 : -1).get().getKey());
            logger.info("------------------------------------");

            logger.info("------------------------------------");
            logger.info("Most Profitable Region in USD After Tax=");
            logger.info(netProfitByRegion.entrySet().stream().max((entry1, entry2) -> entry1.getValue().doubleValue() > entry2.getValue().doubleValue() ? 1 : -1).get().getKey());
            logger.info("------------------------------------");

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ValidationException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * totalNetProfit - Calculate Total Net Profit by given Region in USD
     * @param accounts
     * @param region
     * @return
     */
    public static Double totalNetProfit(List<Account> accounts, String region) {
        //Filter the elements by Region
        List<Account> accountsByRegion = accounts.stream().filter(account -> region.equalsIgnoreCase(account.getRegion())).collect(Collectors.toList());
        return accountsByRegion.stream().mapToDouble(account ->
                account.getNetProfitInUSD()
        ).sum();
    }


    /**
     * totalGrossProfitByCountry - Calculate Total Gross Profit by given Country in USD
     * @param accounts
     * @param country
     * @return
     */
    public static Double totalGrossProfitByCountry(List<Account> accounts, String country) {
        List<Account> accountsByCountry = accounts.stream().filter(account -> country.equalsIgnoreCase(account.getCountry())).collect(Collectors.toList());
        return accountsByCountry.stream().mapToDouble(account -> account.getGrossProfitInUSD()).sum();
    }


    /**
     *totalGrossProfitByRegion - Calculates Total Gross Profit By Region
     * @param accounts
     * @param region
     * @return
     */
    public static Double totalGrossProfitByRegion(List<Account> accounts,String region){
        List<Account> accountsByRegion = accounts.stream().filter(account -> region.equalsIgnoreCase(account.getRegion())).collect(Collectors.toList());
        return accountsByRegion.stream().mapToDouble(account ->
                account.getGrossProfitInUSD()
        ).sum();
    }


}
