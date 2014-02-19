package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import entity.BaseData;
import entity.CellTable;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import persistence.PersistenceUtil;

public class BaseDataAndCellTableConfig extends SuperConfig {

	private String eventCauseID, cellID, duration, imsi, failureClassID, TAC,
	neVersion, baseDate;
	private int mccmncID;
	private String hier3_ID, hier32_ID, hier321_ID;


	public BaseDataAndCellTableConfig(String fname){
		super(fname);
		initialise();
	}

	public BaseDataAndCellTableConfig(){
		super();
		initialise();
	}



	private void initialise(){
		try {
			Workbook workbook = WorkbookSingleton.getWorkbook(workbookFileName);
			Sheet currentSheet = workbook.getSheet(ColumnIndexes.BASEDATA__SHEETNO);

			Cell[] row;

			for (int i = 1; i < currentSheet.getRows(); i++) {
				row = currentSheet.getRow(i);
				if (row.length > 0) {
					eventCauseID = (row[ColumnIndexes.BASEDATA_EVENTID_COLNO].getContents())+ 
							row[ColumnIndexes.BASEDATA_CAUSECODE_COLNO].getContents();
					mccmncID = PersistenceUtil.findMCCMNCByName(
							Integer.parseInt(row[ColumnIndexes.BASEDATA_MARKET_COLNO].getContents()),
							Integer.parseInt(row[ColumnIndexes.BASEDATA_OPERATOR_COLNO].getContents()))
							.getMccmncID();
					cellID = row[ColumnIndexes.BASEDATA_CELLID_COLNO].getContents();
					duration = row[ColumnIndexes.BASEDATA_DURATION_COLNO].getContents();
					imsi = row[ColumnIndexes.BASEDATA_IMSI_COLNO].getContents();
					failureClassID = row[ColumnIndexes.BASEDATA_FAILURECLASS_COLNO].getContents();
					TAC = row[ColumnIndexes.BASEDATA_UETYPE_COLNO].getContents();
					neVersion = row[ColumnIndexes.BASEDATA_NEVERSION_COLNO].getContents();
					baseDate = row[ColumnIndexes.BASEDATA_DATE_COLNO].getContents();
					hier3_ID = row[ColumnIndexes.BASEDATA_HIER3ID_COLNO].getContents();
					hier32_ID = row[ColumnIndexes.BASEDATA_HIER32ID_COLNO].getContents();
					hier321_ID = row[ColumnIndexes.BASEDATA_HIER321ID_COLNO].getContents();

					BaseData baseData = new BaseData(eventCauseID,mccmncID, Integer.parseInt(cellID),
							Integer.parseInt(duration), imsi,failureClassID, TAC, neVersion,
							parseDate(baseDate));
					PersistenceUtil.persist(baseData);

					CellTable cellTable = new CellTable(Integer.parseInt(cellID), hier3_ID,
							hier32_ID, hier321_ID);
					PersistenceUtil.persist(cellTable);
				}
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static java.sql.Date parseDate(String dateSample)throws ParseException {

		String oldFormat = "M/dd/yy HH:mm";
		String newFormat = "yyyy/MM/dd HH:mm";

		SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
		SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
		String newDateFormat = "";

		try {
			newDateFormat = sdf2.format(sdf1.parse(dateSample));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.util.Date date = sdf2.parse(newDateFormat);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return sqlDate;

	}

}
