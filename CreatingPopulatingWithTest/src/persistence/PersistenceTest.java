package persistence;

import static org.junit.Assert.*;

import java.text.ParseException;

import main.BaseDataAndCellTableConfig;

import org.junit.Test;

public class PersistenceTest {

	@Test
	public void testParseDate() throws ParseException {

		assertEquals("2013-08-12", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
		assertNotEquals("2013-07-12", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
		assertNotEquals("2011-08-12", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
		assertNotEquals("2013-08-10", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());

	}

	@Test
	public void testFindAccessCapability() {
		assertEquals("GSM 1800",PersistenceUtil.findAccessCapability("GSM 1800").getAccessCapability());
		assertEquals("GSM 900",PersistenceUtil.findAccessCapability("GSM 900").getAccessCapability());
		assertEquals("GSM850 (GSM800)",PersistenceUtil.findAccessCapability("GSM850 (GSM800)").getAccessCapability());
		assertEquals("GSM 1900",PersistenceUtil.findAccessCapability("GSM 1900").getAccessCapability());
		assertEquals("WCDMA FDD Band I",PersistenceUtil.findAccessCapability("WCDMA FDD Band I").getAccessCapability());
		assertEquals("WCDMA FDD Band II",PersistenceUtil.findAccessCapability("WCDMA FDD Band II").getAccessCapability());
		assertEquals("WCDMA FDD Band V",PersistenceUtil.findAccessCapability("WCDMA FDD Band V").getAccessCapability());
		assertEquals("WCDMA FDD Band IV",PersistenceUtil.findAccessCapability("WCDMA FDD Band IV").getAccessCapability());
		assertEquals("WCDMA FDD Band VIII",PersistenceUtil.findAccessCapability("WCDMA FDD Band VIII").getAccessCapability());
		assertEquals("GPRS",PersistenceUtil.findAccessCapability("GPRS").getAccessCapability());
		assertEquals(null,PersistenceUtil.findAccessCapability("G"));
		assertEquals(null,PersistenceUtil.findAccessCapability("WCDMA"));
		assertEquals(null,PersistenceUtil.findAccessCapability("900"));

		assertNotEquals("GSM 1900",PersistenceUtil.findAccessCapability("GSM 1800").getAccessCapability());
		assertNotEquals("GSM 1900",PersistenceUtil.findAccessCapability("GSM 900").getAccessCapability());
		assertNotEquals("GSM800 (GSM800)",PersistenceUtil.findAccessCapability("GSM850 (GSM800)").getAccessCapability());
		assertNotEquals("GSM 1800",PersistenceUtil.findAccessCapability("GSM 1900").getAccessCapability());
		assertNotEquals("WCDMA FDD Band II",PersistenceUtil.findAccessCapability("WCDMA FDD Band I").getAccessCapability());
		assertNotEquals("WCDMA FDD Band I",PersistenceUtil.findAccessCapability("WCDMA FDD Band II").getAccessCapability());
		assertNotEquals("WCDMA FDD Band VIIIII",PersistenceUtil.findAccessCapability("WCDMA FDD Band V").getAccessCapability());
		assertNotEquals("WCDMA FDD Band V",PersistenceUtil.findAccessCapability("WCDMA FDD Band IV").getAccessCapability());
		assertNotEquals("WCDMA FDD Band I",PersistenceUtil.findAccessCapability("WCDMA FDD Band VIII").getAccessCapability());
		assertNotEquals("GSM",PersistenceUtil.findAccessCapability("GPRS").getAccessCapability());

	}

	@Test
	public void testFindCountry() {

		assertEquals("Denmark",PersistenceUtil.findCountry(238).getCountryName());
		assertEquals("Sweden",PersistenceUtil.findCountry(240).getCountryName());
		assertEquals("Canada",PersistenceUtil.findCountry(302).getCountryName());
		assertEquals("United States of America",PersistenceUtil.findCountry(310).getCountryName());
		assertEquals("Guadeloupe-France",PersistenceUtil.findCountry(340).getCountryName());
		assertEquals("Antigua and Barbuda",PersistenceUtil.findCountry(344).getCountryName());
		assertEquals("India",PersistenceUtil.findCountry(405).getCountryName());
		assertEquals("Japan",PersistenceUtil.findCountry(440).getCountryName());
		assertEquals("Australia",PersistenceUtil.findCountry(505).getCountryName());

		assertEquals(null,PersistenceUtil.findCountry(506));
		assertEquals(null,PersistenceUtil.findCountry(700));
		assertEquals(null,PersistenceUtil.findCountry(-5));

		assertEquals(238,PersistenceUtil.findCountry(238).getMcc());
		assertEquals(340,PersistenceUtil.findCountry(340).getMcc());
		assertEquals(505,PersistenceUtil.findCountry(505).getMcc());


		assertNotEquals("Sweden",PersistenceUtil.findCountry(238).getCountryName());
		assertNotEquals("Denmark",PersistenceUtil.findCountry(240).getCountryName());
		assertNotEquals("France",PersistenceUtil.findCountry(302).getCountryName());
		assertNotEquals("India",PersistenceUtil.findCountry(310).getCountryName());
		assertNotEquals("America",PersistenceUtil.findCountry(340).getCountryName());
		assertNotEquals("Japan",PersistenceUtil.findCountry(344).getCountryName());
		assertNotEquals("USA",PersistenceUtil.findCountry(405).getCountryName());
		assertNotEquals("Antigua and Barbuda",PersistenceUtil.findCountry(440).getCountryName());
		assertNotEquals("India",PersistenceUtil.findCountry(505).getCountryName());


	}

	@Test
	public void testFindManufacturerByName() {

		assertEquals("Mitsubishi",PersistenceUtil.findManufacturerByName("Mitsubishi").getManufacturerName());
		assertEquals("Mitsubishi Electric France",PersistenceUtil.findManufacturerByName("Mitsubishi Electric France").getManufacturerName());
		assertEquals("Matsushita",PersistenceUtil.findManufacturerByName("Matsushita").getManufacturerName());

		assertEquals(1,PersistenceUtil.findManufacturerByName("Mitsubishi").getManufacturerID());
		assertEquals(72,PersistenceUtil.findManufacturerByName("Mitsubishi Electric France").getManufacturerID());
		assertEquals(36,PersistenceUtil.findManufacturerByName("Matsushita").getManufacturerID());

		assertEquals(null,PersistenceUtil.findManufacturerByName("M"));
		assertEquals(null,PersistenceUtil.findManufacturerByName("ABC"));
		assertEquals(null,PersistenceUtil.findManufacturerByName("123"));

		assertNotEquals("Toshiba",PersistenceUtil.findManufacturerByName("Mitsubishi").getManufacturerName());
		assertNotEquals("Mitsubishi",PersistenceUtil.findManufacturerByName("Mitsubishi Electric France").getManufacturerName());
		assertNotEquals("Telian",PersistenceUtil.findManufacturerByName("Matsushita").getManufacturerName());

		assertNotEquals(10,PersistenceUtil.findManufacturerByName("Mitsubishi").getManufacturerID());
		assertNotEquals(7,PersistenceUtil.findManufacturerByName("Mitsubishi Electric France").getManufacturerID());
		assertNotEquals(65,PersistenceUtil.findManufacturerByName("Matsushita").getManufacturerID());



	}

	@Test
	public void testFindUEModelByName() {

		assertEquals("Ferry",PersistenceUtil.findUEModelByName("Ferry").getModelName());
		assertEquals("Dolphin 9900",PersistenceUtil.findUEModelByName("Dolphin 9900").getModelName());
		assertEquals("Mitsubishi GSM MT20 D Type MT 1172 F02A",PersistenceUtil.findUEModelByName("Mitsubishi GSM MT20 D Type MT 1172 F02A").getModelName());
		assertEquals("G410",PersistenceUtil.findUEModelByName("G410").getModelName());

		assertEquals(1,PersistenceUtil.findUEModelByName("G410").getModelID());
		assertEquals(91,PersistenceUtil.findUEModelByName("Mitsubishi GSM MT20 D Type MT 1172 F02A").getModelID());
		assertEquals(44,PersistenceUtil.findUEModelByName("P7").getModelID());

		assertEquals(null,PersistenceUtil.findUEModelByName("567"));
		assertEquals(null,PersistenceUtil.findUEModelByName("MT 1172 F02A"));
		assertEquals(null,PersistenceUtil.findUEModelByName("defgh"));

		assertNotEquals("Dolphin",PersistenceUtil.findUEModelByName("Ferry").getModelName());
		assertNotEquals("9900",PersistenceUtil.findUEModelByName("Dolphin 9900").getModelName());
		assertNotEquals("Mitsubishi GSM MT20",PersistenceUtil.findUEModelByName("Mitsubishi GSM MT20 D Type MT 1172 F02A").getModelName());
		assertNotEquals("G418",PersistenceUtil.findUEModelByName("G410").getModelName());

		assertNotEquals(15,PersistenceUtil.findUEModelByName("G410").getModelID());
		assertNotEquals(6,PersistenceUtil.findUEModelByName("Mitsubishi GSM MT20 D Type MT 1172 F02A").getModelID());
		assertNotEquals(40,PersistenceUtil.findUEModelByName("P7").getModelID());


	}

	@Test
	public void testFindEventID() {
		assertEquals(4097,PersistenceUtil.findEventID(4097).getEventID());
		assertEquals(4125,PersistenceUtil.findEventID(4125).getEventID());
		assertEquals(4106,PersistenceUtil.findEventID(4106).getEventID());

		assertEquals(null,PersistenceUtil.findEventID(-1));
		assertEquals(null,PersistenceUtil.findEventID(4126));
		assertEquals(null,PersistenceUtil.findEventID(2000));

		assertNotEquals(497,PersistenceUtil.findEventID(4097).getEventID());
		assertNotEquals(41295,PersistenceUtil.findEventID(4125).getEventID());
		assertNotEquals(-2,PersistenceUtil.findEventID(4106).getEventID());
	}

	@Test
	public void testFindTAC() {
		assertEquals("21060800", PersistenceUtil.findTAC("21060800").getTac());
		assertNotEquals("21060801", PersistenceUtil.findTAC("21060800").getTac());
		assertEquals("33000153", PersistenceUtil.findTAC("33000153").getTac());
		assertNotEquals("21060801", PersistenceUtil.findTAC("33000153").getTac());
		assertEquals("33000253", PersistenceUtil.findTAC("33000253").getTac());
		assertNotEquals("33000153", PersistenceUtil.findTAC("33000253").getTac());
		assertNotEquals("null", PersistenceUtil.findTAC("21060800").getTac());
		assertEquals("100100", PersistenceUtil.findTAC("100100").getTac());
		assertNotEquals("null", PersistenceUtil.findTAC("100100").getTac());
		assertEquals("100600", PersistenceUtil.findTAC("100600").getTac());
		assertEquals("104400", PersistenceUtil.findTAC("104400").getTac());
		assertEquals("107200", PersistenceUtil.findTAC("107200").getTac());
		assertEquals("33000853", PersistenceUtil.findTAC("33000853").getTac());
		assertEquals("33002053", PersistenceUtil.findTAC("33002053").getTac());
		assertEquals("33002295", PersistenceUtil.findTAC("33002295").getTac());
		assertNotEquals("100600", PersistenceUtil.findTAC("33002295").getTac());
		assertEquals(null, PersistenceUtil.findTAC("330295"));

		assertEquals(50, PersistenceUtil.findTAC("106400").getUeModel());
		assertEquals("TOUCH_SCREEN", PersistenceUtil.findTAC("107200").getUeInputMode());
		assertNotEquals("TOUCH_SCREEN", PersistenceUtil.findTAC("107100").getUeInputMode());

	}

	@Test
	public void testFindFailureCode() {

		assertEquals(0,PersistenceUtil.findFailureCode(0).getFailureID());
		assertEquals(1,PersistenceUtil.findFailureCode(1).getFailureID());
		assertEquals(2,PersistenceUtil.findFailureCode(2).getFailureID());
		assertEquals(3,PersistenceUtil.findFailureCode(3).getFailureID());
		assertEquals(4,PersistenceUtil.findFailureCode(4).getFailureID());
		assertEquals(null,PersistenceUtil.findFailureCode(5));

		assertEquals("EMERGENCY",PersistenceUtil.findFailureCode(0).getFailureDescription());
		assertEquals("HIGH PRIORITY ACCESS",PersistenceUtil.findFailureCode(1).getFailureDescription());
		assertEquals("MT ACCESS",PersistenceUtil.findFailureCode(2).getFailureDescription());
		assertEquals("MO SIGNALLING",PersistenceUtil.findFailureCode(3).getFailureDescription());
		assertEquals("MO DATA",PersistenceUtil.findFailureCode(4).getFailureDescription());
		assertNotEquals("EMERGENCY",PersistenceUtil.findFailureCode(1).getFailureDescription());
		assertNotEquals("HIGH PRIORITY ACCESS",PersistenceUtil.findFailureCode(3).getFailureDescription());
		assertNotEquals("MT ACCESS",PersistenceUtil.findFailureCode(0).getFailureDescription());	
	}


	@Test
	public void testfindEventIDAndCauseCode(){
		assertEquals(1,PersistenceUtil.findEventIDAndCauseCode(4097, 0).getEventcauseCode());
		assertEquals("RRC CONN SETUP-LACK OF RESOURCES",PersistenceUtil.findEventIDAndCauseCode(4097, 5).getCauseDescription());
		assertEquals(20,PersistenceUtil.findEventIDAndCauseCode(4098, 2).getEventcauseCode());
		assertEquals(37,PersistenceUtil.findEventIDAndCauseCode(4125, 15).getEventcauseCode());
		assertNotEquals(15,PersistenceUtil.findEventIDAndCauseCode(4125, 15).getEventcauseCode());
		assertEquals(51,PersistenceUtil.findEventIDAndCauseCode(4125, 29).getEventcauseCode());

		assertEquals("UE CTXT RELEASE-HANDOVER TRIGGERED",PersistenceUtil.findEventIDAndCauseCode(4125, 15).getCauseDescription());
		assertEquals("UE CTXT RELEASE-REDUCE LOAD IN SERVING CELL",PersistenceUtil.findEventIDAndCauseCode(4125, 29).getCauseDescription());
		assertEquals("INITIAL CTXT SETUP-CSFB UNDEFINED MOB FREQ REL",PersistenceUtil.findEventIDAndCauseCode(4106, 24).getCauseDescription());
		assertEquals(null,PersistenceUtil.findEventIDAndCauseCode(0, 0));
		assertEquals(80,PersistenceUtil.findEventIDAndCauseCode(4106, 24).getEventcauseCode());
		assertNotEquals(15,PersistenceUtil.findEventIDAndCauseCode(4106, 21).getEventcauseCode());
		assertNotEquals(15,PersistenceUtil.findEventIDAndCauseCode(4106, 15).getEventcauseCode());
		assertNotEquals(15,PersistenceUtil.findEventIDAndCauseCode(4106, 2).getEventcauseCode());
		assertNotEquals(25,PersistenceUtil.findEventIDAndCauseCode(4125, 15).getEventcauseCode());	
	}

	@Test
	public void testFindMCCMNCByName(){

		assertEquals("TDC-DK",PersistenceUtil.findMCCMNCByName(238, 1).getOperator());
		assertEquals("AINMT Sverige AB SE ",PersistenceUtil.findMCCMNCByName(240, 3).getOperator());
		assertEquals("Ice Wireless CA ",PersistenceUtil.findMCCMNCByName(302, 62).getOperator());
		assertEquals("Wireless Solutions International US ",PersistenceUtil.findMCCMNCByName(310, 550).getOperator());
		assertEquals("AT&T Wireless-Antigua AG ",PersistenceUtil.findMCCMNCByName(344, 930).getOperator());
		assertEquals("Reliance Infocomm-IN",PersistenceUtil.findMCCMNCByName(405, 4).getOperator());
		assertEquals("NTT DoCoMo",PersistenceUtil.findMCCMNCByName(440, 11).getOperator());
		assertEquals("Optus Ltd. AU ",PersistenceUtil.findMCCMNCByName(505, 90).getOperator());
		assertEquals("Telstra",PersistenceUtil.findMCCMNCByName(505, 72).getOperator());
		assertNotEquals("Wireless Solutions International US ",PersistenceUtil.findMCCMNCByName(310, 560).getOperator());
		assertNotEquals("AT&T Wireless-Antigua AG ",PersistenceUtil.findMCCMNCByName(344, 30).getOperator());
		assertNotEquals("Reliance Infocomm-IN",PersistenceUtil.findMCCMNCByName(440, 9).getOperator());
		assertNotEquals("NTT DoCoMo",PersistenceUtil.findMCCMNCByName(505, 62).getOperator());
		assertNotEquals("Optus Ltd. AU ",PersistenceUtil.findMCCMNCByName(505, 71).getOperator());
		assertNotEquals("Telstra",PersistenceUtil.findMCCMNCByName(505, 90).getOperator());
		
		assertEquals(null,PersistenceUtil.findMCCMNCByName(505, 11));
		assertNotEquals(null,PersistenceUtil.findMCCMNCByName(505, 72));
		assertEquals(16,PersistenceUtil.findMCCMNCByName(310, 540).getMccmncID());
		assertEquals(2,PersistenceUtil.findMCCMNCByName(238, 2).getMccmncID());
		assertEquals(11,PersistenceUtil.findMCCMNCByName(302, 62).getMccmncID());
		assertEquals(26,PersistenceUtil.findMCCMNCByName(344, 920).getMccmncID());
		assertEquals(32,PersistenceUtil.findMCCMNCByName(405, 5).getMccmncID());
		assertEquals(41,PersistenceUtil.findMCCMNCByName(505, 90).getMccmncID());
		assertEquals(16,PersistenceUtil.findMCCMNCByName(310, 540).getMccmncID());
		assertNotEquals(2,PersistenceUtil.findMCCMNCByName(238, 3).getMccmncID());
		assertNotEquals(11,PersistenceUtil.findMCCMNCByName(310, 10).getMccmncID());
		assertNotEquals(26,PersistenceUtil.findMCCMNCByName(405, 4).getMccmncID());
		assertNotEquals(32,PersistenceUtil.findMCCMNCByName(240, 20).getMccmncID());
		assertNotEquals(41,PersistenceUtil.findMCCMNCByName(340, 3).getMccmncID());


	}

}
