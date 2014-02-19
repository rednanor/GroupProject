package main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entity.BaseData;
import entity.CellTable;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import persistence.PersistenceUtil;

public class BaseDataAndCellTableConfig extends SuperConfig {

	private String baseDate, eventID, failureClassID, TAC, mccID, mncID,
			cellID, duration, causeCode, neVersion, imsi, hier3_ID, hier32_ID,
			hier321_ID;

	SimpleDateFormat sdf = new SimpleDateFormat("");
	java.util.Date time = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(time.getTime());

	public BaseDataAndCellTableConfig(String fname) {
		super(fname);
		initialise();
	}

	public BaseDataAndCellTableConfig() {
		super();
		initialise();
	}

	public void initialise() {

		try {

			Workbook workbook = WorkbookSingleton.getWorkbook(workbookFileName);
			Sheet currentSheet = workbook
					.getSheet(ColumnIndexes.BASEDATA__SHEETNO);

			Cell[] row;

			// Gets the sheets from workbook
			for (int i = 1; i < currentSheet.getRows(); i++) {
				row = currentSheet.getRow(i);
				if (row.length > 0) {

					cellID = row[ColumnIndexes.BASEDATA_CELLID_COLNO]
							.getContents();
					eventID = row[ColumnIndexes.BASEDATA_EVENTID_COLNO]
							.getContents();
					duration = row[ColumnIndexes.BASEDATA_DURATION_COLNO]
							.getContents();
					imsi = row[ColumnIndexes.BASEDATA_IMSI_COLNO].getContents();
					failureClassID = row[ColumnIndexes.BASEDATA_FAILURECLASS_COLNO]
							.getContents();
					TAC = row[ColumnIndexes.BASEDATA_UETYPE_COLNO]
							.getContents();
					neVersion = row[ColumnIndexes.BASEDATA_NEVERSION_COLNO]
							.getContents();
					baseDate = row[ColumnIndexes.BASEDATA_DATE_COLNO]
							.getContents();
					hier3_ID = row[ColumnIndexes.BASEDATA_HIER3ID_COLNO]
							.getContents();
					hier32_ID = row[ColumnIndexes.BASEDATA_HIER32ID_COLNO]
							.getContents();
					hier321_ID = row[ColumnIndexes.BASEDATA_HIER321ID_COLNO]
							.getContents();
					mccID = row[ColumnIndexes.BASEDATA_MARKET_COLNO]
							.getContents();
					mncID = row[ColumnIndexes.BASEDATA_OPERATOR_COLNO]
							.getContents();
					causeCode = row[ColumnIndexes.BASEDATA_CAUSECODE_COLNO]
							.getContents();

					String[] rowOfStrings = { baseDate, eventID,
							failureClassID, TAC, mccID, mncID, cellID,
							duration, causeCode, neVersion, imsi, hier3_ID,
							hier32_ID, hier321_ID };

					if (checkRowIsValid(rowOfStrings)) {

						int eventCauseID = PersistenceUtil
								.findEventIDAndCauseCode(
										Integer.parseInt(eventID),
										Integer.parseInt(causeCode))
								.getEventcauseCode();

						int mccmncID = PersistenceUtil.findMCCMNCByName(
								Integer.parseInt(mccID),
								Integer.parseInt(mncID)).getMccmncID();

						BaseData baseData = new BaseData(eventCauseID,
								mccmncID, Integer.parseInt(cellID),
								Integer.parseInt(duration), imsi,
								failureClassID, TAC, neVersion,
								parseDate(baseDate));

						PersistenceUtil.persist(baseData);

						CellTable cellTable = new CellTable(
								Integer.parseInt(cellID), hier3_ID, hier32_ID,
								hier321_ID);
						PersistenceUtil.persist(cellTable);
					}

				}

			}

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
			return null;
		}

		java.util.Date date = sdf2.parse(nda);
		java.sql.Date sql = new java.sql.Date(date.getTime());

		return sql;

	}

	public boolean checkRowIsValid(String[] rowOfCells) {

		if (checkDateFormat(rowOfCells[0])) {
			if (checkEventIdAndCauseCode(rowOfCells[1], rowOfCells[8])) {
				if (checkFailureClass(rowOfCells[2])) {
					if (checkTAC(rowOfCells[3])) {
						if (checkMCCAndMNC(rowOfCells[4], rowOfCells[5])) {
							if (checkCellIdAndDuration(rowOfCells[6],
									rowOfCells[7])) {
								if (checkIMSI(rowOfCells[10])) {
									if (checkHIERIDs(rowOfCells[11],
											rowOfCells[12], rowOfCells[13])) {
										return true;
									}
								}
							}
						}
					}
				}
			}

		}
		return false;
	}

	private boolean checkHIERIDs(String HIERID3, String HIERID32,
			String HIERID321) {

		if ((HIERID3.length() == 19 && HIERID32.length() == 19 && HIERID321
				.length() == 19)
				&& (HIERID3.matches("[0-9]+") || HIERID3.matches("[0-9]+") || HIERID3
						.matches("[0-9]+"))) {
			// System.out.println("These values are OK: "+HIERID3+" "+HIERID32+" "+HIERID321);
			return true;
		}
		// System.out.println("Broke at checkHIERIDs: " + HIERID3 + " " +
		// HIERID32
		// + " " + HIERID321);
		return false;
	}

	private boolean checkIMSI(String imsiColumnValue) {
		if (imsiColumnValue.length() == 15 && imsiColumnValue.matches("[0-9]+")) {
			return true;
		}
		System.out.println("Broke at IMSI: " + imsiColumnValue);
		return false;
	}

	private boolean checkCellIdAndDuration(String cellIdColumnValue,
			String durationColumnValue) {
		try {
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	private boolean checkMCCAndMNC(String mccColumnValue, String mncColumnValue) {
		// loop thru MCC codes until you find match
		if (PersistenceUtil.findMCCMNCByName(Integer.parseInt(mccColumnValue),
				Integer.parseInt(mncColumnValue)) != null) {
			return true;
		}

		return false;
	}

	private boolean checkTAC(String tacColumnValue) {

		if (PersistenceUtil.findTAC(tacColumnValue) != null) {
			return true;
		} else {

			return false;
		}
	}

	private boolean checkFailureClass(String failureClassColumnValue) {

		int failureClassAsInt = Integer.parseInt(failureClassColumnValue);

		if (PersistenceUtil.findFailureCode(failureClassAsInt) != null) {

			return true;

		} else {

			return false;
		}
	}

	private boolean checkEventIdAndCauseCode(String eventIdColumnValue,
			String causeCodeColumnValue) {

		try {
			Integer.parseInt(eventIdColumnValue);
			Integer.parseInt(causeCodeColumnValue);
		} catch (NumberFormatException e) {

			return false;
		}

		if (PersistenceUtil.findEventIDAndCauseCode(
				Integer.parseInt(eventIdColumnValue),
				Integer.parseInt(causeCodeColumnValue)) != null) {

			return true;

		} else {

			return false;
		}
	}

	private boolean checkDateFormat(String dateColumnValue) {

		Date dateToCheck;
		try {
			dateToCheck = parseDate(dateColumnValue);
			if (dateToCheck != null && time.after(dateToCheck)) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
