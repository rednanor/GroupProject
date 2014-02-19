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
import entity.Manufacturer;


public class ManufacturerConfig {
	
	
	public ManufacturerConfig(){
		//loading
		try{
			String inputFile = "data.xls";
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(inputFile),
					workbookSettings);
			
			// Gets the sheets from workbook
							Sheet currentSheet = w.getSheet(3);
							Cell[] row = null;

							// Gets the cells from the column
							for (int i = 1; i < currentSheet.getRows(); i++) {
								row = currentSheet.getRow(i);
								//System.out.println(row[2].getContents()); 
								if (PersistenceUtil.findManufacturerByName(row[2].getContents())==null){
									createManufacturer((row[2].getContents()));
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
		
		

	
//	public void viewManufacturer(){
//		List<Manufacturer> manufacturers = PersistenceUtil.findAllManufacturers();
//		for(Manufacturer s:manufacturers){
//			System.out.println("Manufacturer "+s.getManufacturerID()+"" +s.getManufacturerName()+ " exists.");
//		}
//	}
//	
	public void createManufacturer(String manufacturerName){
		
		Manufacturer manufacturer = new Manufacturer(manufacturerName);
		PersistenceUtil.persist(manufacturer);
	//	System.out.println("Manufacturer registered");
		//viewManufacturer();
	}
	
	/*public void deleteManufacturer(int id){
		Manufacturer manufacturer = PersistenceUtil.findManufacturerById(id);
		PersistenceUtil.remove(manufacturer);
		System.out.println("Manufacturer removed");
		viewManufacturer();
	}*/
}
