package main;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import entity.BaseData;
import entity.CellTable;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import persistence.PersistenceUtil;

public class BaseDataAndCellTableConfig {

	private String eventCauseID, cellID, duration, imsi, failureClassID, TAC,
			neVersion, baseDate;
	private int mccmncID;
	private String hier3_ID, hier32_ID, hier321_ID;

	public BaseDataAndCellTableConfig() {

		

		try {

			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);

			// Gets the sheets from workbook
			for (int sheetNum = 0; sheetNum < 4; sheetNum++) {

				Sheet currentSheet = w.getSheet(sheetNum);
				Cell[] row = null;

				// Gets the cells from sheet
				for (int i = 1; i < currentSheet.getRows(); i++) {
					row = currentSheet.getRow(i);

					if (row.length > 0) {
						if (sheetNum == 0) {

							eventCauseID = (row[1].getContents())
									+ row[8].getContents();
							mccmncID = PersistenceUtil.findMCCMNCByName(
									Integer.parseInt(row[4].getContents()),
									Integer.parseInt(row[5].getContents()))
									.getMccmncID();
							cellID = row[6].getContents();
							duration = row[7].getContents();
							imsi = row[10].getContents();
							failureClassID = row[2].getContents();
							TAC = row[3].getContents();
							neVersion = row[9].getContents();
							baseDate = row[0].getContents();
							hier3_ID = row[11].getContents();
							hier32_ID = row[12].getContents();
							hier321_ID = row[13].getContents();

							BaseData baseData = new BaseData(eventCauseID,
									mccmncID, Integer.parseInt(cellID),
									Integer.parseInt(duration), imsi,
									failureClassID, TAC, neVersion,
									parseDate(baseDate));
							PersistenceUtil.persist(baseData);

							// createCellTable(Integer.parseInt(cellID),
							// row[11].getContents(), row[12].getContents(),
							// row[13].getContents());
							CellTable cellTable = new CellTable(
									Integer.parseInt(cellID), hier3_ID,
									hier32_ID, hier321_ID);
							PersistenceUtil.persist(cellTable);

						}

						if (sheetNum == 1) {

						}

						if (sheetNum == 2) {

						}

						if (sheetNum == 3) {

						}

						if (sheetNum == 4) {

						}

					}

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

	public static java.sql.Date parseDate(String dateSample)
			throws ParseException {

		// String dateSample = row[0].getContents();

		String oldFormat = "M/dd/yy HH:mm";
		String newFormat = "yyyy/MM/dd HH:mm";

		SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
		SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
		String nda = "";

		try {
			nda = sdf2.format(sdf1.parse(dateSample));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		java.util.Date date = sdf2.parse(nda);
		java.sql.Date sql = new java.sql.Date(date.getTime());

		return sql;

	}

}
