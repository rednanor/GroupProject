package main;

public class Driver  {

	public static void main(String[] args) {
		String fname ="C:\\SlowWorkingDB\\data.xls";
		if (args.length>0){
			fname = args[0];
		}
		CountryConfig cConfig = new CountryConfig(fname);
		AccessCapabilityConfig acConfig = new AccessCapabilityConfig(fname);
		UE_AccessCapabilityConfig ue_ACC = new UE_AccessCapabilityConfig();
		EventCauseConfig ecConfig = new EventCauseConfig();
		FailureConfig fConfig = new FailureConfig();
		ManufacturerConfig mConfig = new ManufacturerConfig();
		MCCMNCConfig mccmncConfig = new MCCMNCConfig();
		UEModelConfig ueModelConfig = new UEModelConfig();
		UserEquipmentConfig ueConfig = new UserEquipmentConfig();
		BaseDataAndCellTableConfig pe = new BaseDataAndCellTableConfig(fname);
	}
}
