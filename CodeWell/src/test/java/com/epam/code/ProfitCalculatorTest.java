package com.epam.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by CN133244 on 1/31/2018.
 */
public class ProfitCalculatorTest {

    private List<Account> accounts;

    @Before
    @Ignore
    public void setUp() throws Exception {
        accounts=new ArrayList<>();

        //Long id, String country, String region, String currency, BigDecimal grossProfit, BigDecimal grossProfitInUSD, Double taxRate, BigDecimal netProfitInUSD)
        //Long id, String country, String region, String currency, Double grossProfit, Double grossProfitInUSD, Double taxRate, Double netProfitInUSD
        accounts.add(new Account(1L,"USA","NA","USD",10000.0,10000.0,10.0,9000.0));
        accounts.add(new Account(2L,"Canada","NA","CAN",4789.0,3882.9211999999998,10.0,3494.6290799999997));
        accounts.add(new Account(3L,"UK","EMEA","GBP",50000.0,78608.5,12.0,69175.48));
        accounts.add(new Account(4L,"India","APAC","INR",277777.0,4369.7377647,12.99,3802.10882906547));
        accounts.add(new Account(5L,"Australia","APAC","AUD",90356.0,69822.056864,6.67,65164.9256711712));
        accounts.add(new Account(6L,"New Zealand","APC","NZD",12345.0,8451.646245,7.25,7838.9018922375));
        accounts.add(new Account(7L,"Mexico","NA","MXN",334433.0,21680.956957,3.0,21030.52824829));
        accounts.add(new Account(8L,"Republic of Ireland","EMEA","EUR",222222.0,247995.30756,12.5,216995.89411499997));
        accounts.add(new Account(9L,"Greece","EMEA","EUR",123225.0,137516.6355,1.25,135797.67755625));
    }

    @Test
    public void totalNetProfit() throws Exception {
        Double result=null;
        result=ProfitCalculator.totalNetProfit(accounts,"APAC");
        Assert.assertEquals(68967.034,result.doubleValue(),2);
        result=ProfitCalculator.totalNetProfit(accounts,"APC");
        Assert.assertEquals(7838.90,result.doubleValue(),2);
        result=ProfitCalculator.totalNetProfit(accounts,"EMEA");
        Assert.assertEquals(421969.05,result.doubleValue(),2);
        result=ProfitCalculator.totalNetProfit(accounts,"NA");
        Assert.assertEquals(33525.15,result.doubleValue(),2);
    }

    @Test
    public void totalGrossProfitByCountry() throws Exception {
        Double result=ProfitCalculator.totalGrossProfitByCountry(accounts,"Canada");
        Assert.assertEquals(3882.92,result.doubleValue(),2);

        result=ProfitCalculator.totalGrossProfitByCountry(accounts,"UK");
        Assert.assertEquals(78608.5,result.doubleValue(),2);

        result=ProfitCalculator.totalGrossProfitByCountry(accounts,"USA");
        Assert.assertEquals(10000.0,result.doubleValue(),2);

        result=ProfitCalculator.totalGrossProfitByCountry(accounts,"Australia");
        Assert.assertEquals(69822.05,result.doubleValue(),2);

        result=ProfitCalculator.totalGrossProfitByCountry(accounts,"Canada");
        Assert.assertEquals(3882.92,result.doubleValue(),2);

        result=ProfitCalculator.totalGrossProfitByCountry(accounts,"Greece");
        Assert.assertEquals(137516.63,result.doubleValue(),2);
    }

}