package com.epam.code;

import org.apache.log4j.Logger;

import javax.xml.bind.ValidationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to serve the purpose of reading CSV file and returning List of Account information back to caller.
 *
 * @author cn133244
 */
public class CsvFileReader {

    private static final Logger logger = Logger.getLogger(CsvFileReader.class);

    /**
     * Pass the file as argument
     *
     * @param args
     */
    public static void main(String... args) {

        CsvFileReader csvFileReader = new CsvFileReader();

        String fileName = "TestData.csv";
        List<Account> accounts;
        try {
            accounts = csvFileReader.readAccountsFromCSV(fileName);
            // let's print all the data read from CSV file
            for (Account b : accounts) {
                logger.info(b);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ValidationException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public List<Account> readAccountsFromCSV(String fileName) throws FileNotFoundException, ValidationException {
        List<Account> accounts = new ArrayList<>();

        if (fileName == null) {
            throw new FileNotFoundException("File not found!");
        }
        Path pathToFile = Paths.get(fileName);
        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // read the first line from the text file
            String line = br.readLine();
            if (!validHeaders(line)) {
                throw new ValidationException("Headers are invalid in CSV. Please validate it once.");
            }

            // Read the data from 2nd line onwards ...
            line = br.readLine();
            // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from
                // each line of the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                Account account = createAccount(attributes);
                // adding book into ArrayList
                accounts.add(account);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }
        } catch (IOException ioe) {
            throw new FileNotFoundException("File not found!");
        }
        return accounts;
    }

    /**
     * Validates the headers (REGION,COUNTRY,ACCOUNT_ID,CURRENCY,GROSS_PROFIT,TAX_RATE)
     * @param line
     * @return
     */
    private boolean validHeaders(String line) {
        if (line != null) {
            String[] attributes = line.split(",");
            if ("REGION".equalsIgnoreCase(attributes[0])
                    && "COUNTRY".equalsIgnoreCase(attributes[1])
                    && "ACCOUNT_ID".equalsIgnoreCase(attributes[2])
                    && "CURRENCY".equalsIgnoreCase(attributes[3])
                    && "GROSS_PROFIT".equalsIgnoreCase(attributes[4])
                    && "TAX_RATE".equalsIgnoreCase(attributes[5])) {
                return true;
            }
        }
        return false;
    }


    /**
     * Creates an Account
     *
     * @param attributes
     * @return
     */
    private static Account createAccount(String[] attributes) {
        //REGION,COUNTRY,ACCOUNT_ID,CURRENCY,GROSS_PROFIT,TAX_RATE
        String region = attributes[0];
        String country = attributes[1];
        Long id = Long.valueOf(attributes[2]);
        String currency = attributes[3];
        Double grossProfit = new Double(attributes[4]);
        Double taxRate = Double.valueOf(attributes[5]);
        Double grossProfitInUSD = grossProfit * SpotRate.getExchangeRate(currency);
        Double netProfitInUSD = grossProfitInUSD - (grossProfitInUSD * taxRate / 100);
        return new Account(id, country, region, currency, grossProfit, grossProfitInUSD, taxRate, netProfitInUSD);
    }

}
