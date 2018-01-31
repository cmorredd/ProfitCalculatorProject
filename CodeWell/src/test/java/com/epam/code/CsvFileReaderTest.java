package com.epam.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.fail;

public class CsvFileReaderTest {

	CsvFileReader csvFileReader;

	@Before
	public void setup() {
		csvFileReader = new CsvFileReader();
	}

	@Test
	public void readAccountsFromCSV_fileName_is_null() throws ValidationException{
		String fileName = null;
		try {
			csvFileReader.readAccountsFromCSV(fileName);
			fail("Expecting exception");
		} catch (FileNotFoundException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void readAccountsFromCSV_file_not_found(){
		String fileName = "dummypath/dummyfileName.csv";
		try {
			csvFileReader.readAccountsFromCSV(fileName);
			fail("Expecting exception");
		} catch (FileNotFoundException e) {
			Assert.assertTrue(true);
		} catch (ValidationException e) {
			fail("Expecting FileNotFoundException exception Not ValidationException!");
		}
	}

	@Test
	public void readAccountsFromCSV_invalid_headers(){
		String fileName = "TestData-InvalidHeaders.csv";
		try {
			csvFileReader.readAccountsFromCSV(fileName);
			fail("Expecting ValidationException exception!");
		} catch (FileNotFoundException e) {
			fail("Expecting ValidationException exception Not FileNotFoundException!");
		} catch (ValidationException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void readAccountsFromCSV() {
		// Please change according to the location of actual file or else we
		// have to keep it in class path and read it from there
		//String fileName = "C:/poc-ws/CodeWell/src/main/java/com/epam/code/TestData.csv";
		String fileName="TestData.csv";
		try {
			List<Account> accounts = csvFileReader.readAccountsFromCSV(fileName);
			Assert.assertNotNull(accounts);
		} catch (FileNotFoundException e) {
			fail("File Not found!");
		} catch (ValidationException e) {
			fail(e.getMessage());
		}
	}
}
