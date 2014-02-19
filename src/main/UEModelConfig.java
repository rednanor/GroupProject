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
import entity.UEModel;

public class UEModelConfig {

	public UEModelConfig(){
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
								//System.out.println(row[1].getContents());  
								createUEModel((row[4].getContents()));
									

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
//	public void viewUEModel(){
//		List<UEModel> ueModels = PersistenceUtil.findAllUEModels();
//		for(UEModel s:ueModels){
//			System.out.println("UEModel "+s.getModelID()+"" +s.getModelName()+ " exists.");
//		}
//	}
	
	public void createUEModel(String modelName){

		UEModel ueModel = new UEModel(modelName);
		PersistenceUtil.persist(ueModel);
		//System.out.println("UEModel registered");
		//viewUEModel();
	}
	
//	public void deleteUEModel(int id){
//		UEModel ueModel = PersistenceUtil.findUEModelById(id);
//		PersistenceUtil.remove(ueModel);
//		System.out.println("UEModel removed");
//		//viewUEModel();
//	}
}
