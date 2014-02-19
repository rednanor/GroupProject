package main;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;

import persistence.PersistenceUtil;
import entity.AccessCapability;
import entity.BaseData;
import entity.UE_AccessCapability;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class AccessCapabilityConfig {

	public AccessCapabilityConfig() {
		try {

			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);

			// Gets the sheets from workbook

			Sheet currentSheet = w.getSheet(3);
			Cell[] row = null;

			// Gets the cells from sheet
			for (int i = 1; i < currentSheet.getRows(); i++) {
				row = currentSheet.getRow(i);

				if (row.length > 0) {

					String str = row[3].getContents();
					String[] array = str.split(", ");
					for (int j = 0; j < array.length; j++) {
						if (PersistenceUtil.findAccessCapability(array[j]) == null) {
							createAccessCapability(array[j]);
						}
					}
					// }

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

	public void createAccessCapability(String accessCapabilityString) {
		AccessCapability accessCapability = new AccessCapability(
				accessCapabilityString);
		PersistenceUtil.persist(accessCapability);
		//System.out.println("Subscriber registered");
	}
	


}
