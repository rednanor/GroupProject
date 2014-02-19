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
import entity.UserEquipment;

public class UserEquipmentConfig {

	
	
	public UserEquipmentConfig(){
		//loading
		try{
			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);
			
			// Gets the sheets from workbook
						//for (int sheetNum = 0; sheetNum < 1; sheetNum++) {

							Sheet currentSheet = w.getSheet(3);
							Cell[] row = null;

							// Gets the cells from sheet
							for (int i = 1; i < currentSheet.getRows(); i++) {
								row = currentSheet.getRow(i);

								if (row.length > 0) {
									// for (int j = 1; j < row.length; j++) {
//									System.out.println(row[0].getContents() + " "
//											+ row[2].getContents() + " "
//											+ row[4].getContents() + " "
//											+ row[6].getContents() + " "
//											+ row[7].getContents() + " "
//											+ row[8].getContents());
				
									String manufacturer=row[2].getContents();
									int manufacturerId=PersistenceUtil.findManufacturerByName(manufacturer).getManufacturerID();
									int model=PersistenceUtil.findUEModelByName(row[4].getContents()).getModelID();
									//System.out.println(manufacturerId);
									//System.out.println(model);
									createUserEquipment((row[0].getContents()),manufacturerId,
											model,row[6].getContents(),row[7].getContents(),
											row[8].getContents());
						
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
		
		

//	
//	public void viewUserEquipment(){
//		List<UserEquipment> userEquipments = PersistenceUtil.findAllUserEquipment();
//		for(UserEquipment s:userEquipments){
//			System.out.println("UserEquipment "+s.getTac()+ " exists.");
//		}
//	
//	}
	
	public void createUserEquipment(String tac, int ueManufacturer, int ueModel, String ueTypeID,String ueOperatingSys, String ueInputMode){
		UserEquipment userEquipment = new UserEquipment(tac, ueManufacturer,ueModel,ueTypeID,ueOperatingSys,ueInputMode);
		PersistenceUtil.persist(userEquipment);
		//System.out.println("UserEquipment registered");
		//viewUserEquipment();
	}
//	
//	public void deleteUserEquipment(String tac){
//		UserEquipment userEquipment = PersistenceUtil.findUserEquipmentById(tac);
//		PersistenceUtil.remove(userEquipment);
//		System.out.println("UserEquipment removed");
//		viewUserEquipment();
//	}
//	
	
}
