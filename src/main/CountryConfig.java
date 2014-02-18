package main;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;

import persistence.PersistenceUtil;
import entity.CellTable;
import entity.Country;
import entity.EventCause;
import entity.Failure;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class CountryConfig {

	public CountryConfig() {
		try {

			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);

			// Gets the sheets from workbook

			Sheet currentSheet = w.getSheet(4);
			Cell[] row = null;

			// Gets the cells from sheet
			for (int i = 1; i < currentSheet.getRows(); i++) {
				row = currentSheet.getRow(i);

				if (row.length > 0) {
					int mcc = Integer.parseInt(row[0].getContents());
					if (PersistenceUtil.findCountry(mcc) == null) {			
					createCountry(mcc, row[2].getContents());}

				}

			}

		} catch (UnsupportedEncodingException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void createCountry(int mcc, String countryName) {
		Country country = new Country(mcc, countryName);
		PersistenceUtil.persist(country);
		//System.out.println("Subscriber registered");
	}

}
