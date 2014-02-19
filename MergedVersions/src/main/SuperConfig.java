package main;

public class SuperConfig {
	String workbookFileName;

	public SuperConfig() {
		workbookFileName = "data.xls";
	}

	public SuperConfig(String workbookfilename) {
		workbookFileName = workbookfilename;
	}

}
