import static org.junit.Assert.*;


import java.text.ParseException;
import main.BaseDataAndCellTableConfig;
import persistence.PersistenceUtil;


public class Test {
		
	@org.junit.Test
	public void testParseDate() throws ParseException {

			assertEquals("2013-08-12", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
			assertNotEquals("2013-07-12", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
			assertNotEquals("2011-08-12", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
			assertNotEquals("2013-08-10", BaseDataAndCellTableConfig.parseDate("8/12/13 12:00").toString());
		
	}
	
	@org.junit.Test
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
	
	@org.junit.Test
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
	
	@org.junit.Test
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
	
	@org.junit.Test
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
	

}
