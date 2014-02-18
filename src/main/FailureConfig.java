package main;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;

import persistence.PersistenceUtil;
import entity.CellTable;
import entity.EventCause;
import entity.Failure;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class FailureConfig {

	public FailureConfig() {
		try {

			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);

			// Gets the sheets from workbook

			Sheet currentSheet = w.getSheet(2);
			Cell[] row = null;

			// Gets the cells from sheet
			for (int i = 1; i < currentSheet.getRows(); i++) {
				row = currentSheet.getRow(i);

				if (row.length > 0) {

					createFailure(Integer.parseInt(row[0].getContents()), row[1].getContents());

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

	public void createFailure(int failureID, String failureDescription) {
		Failure failure = new Failure(failureID, failureDescription);
		PersistenceUtil.persist(failure);
		//System.out.println("Subscriber registered");
	}

}
