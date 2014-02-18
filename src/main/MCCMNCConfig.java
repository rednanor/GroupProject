package main;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import persistence.PersistenceUtil;
import entity.MCCMNC;
import entity.UserEquipment;

public class MCCMNCConfig {

	public MCCMNCConfig() {
		try{
			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);
			
			// Gets the sheets from workbook
						//for (int sheetNum = 0; sheetNum < 1; sheetNum++) {

							Sheet currentSheet = w.getSheet(4);
							Cell[] row = null;

							// Gets the cells from sheet
							for (int i = 1; i < currentSheet.getRows(); i++) {
								row = currentSheet.getRow(i);

								if (row.length > 0) {
									// for (int j = 1; j < row.length; j++) {
//									System.out.println(row[0].getContents() + " "
//											+ row[1].getContents() + " "
//											+ row[3].getContents() );
									
									createMCCMNC(Integer.parseInt(row[0].getContents()),
											Integer.parseInt(row[1].getContents()),
											row[3].getContents());
						
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
		
		

	
//	public void viewMCCMNC(){
//		List<MCCMNC> mccmncs = PersistenceUtil.findAllMCCMNC();
//		for(MCCMNC s:mccmncs){
//			System.out.println("MCCMNC "+s.getMcc()+" "+s.getMnc()+ " exists.");
//		}
//	
//	}
	
	public void createMCCMNC(int mcc,int mnc,String operator){
		MCCMNC mccmnc = new MCCMNC(mcc, mnc, operator);
		PersistenceUtil.persist(mccmnc);
	//	System.out.println("MCCMNC registered");
	//	viewMCCMNC();
	}
	
//	public void deleteMCCMNC(int mccmncID){
//		MCCMNC mccmnc = PersistenceUtil.findMCCMNCById(mccmncID);
//		PersistenceUtil.remove(mccmnc);
//		System.out.println("MCCMNC removed");
//		viewMCCMNC();
//	}
	

}
