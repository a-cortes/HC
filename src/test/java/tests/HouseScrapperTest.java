package tests;

import org.junit.Test;

import com.hcrawler.scrapers.MercadoLibreScrapperMethods;
import com.housedata.model.House;

public class HouseScrapperTest {

	@Test
	public void test() {
		House test = new House();
		test.setUrl("https://casa.mercadolibre.com.mx/MLM-609669510-en-juriquilla-_JM");
		MercadoLibreScrapperMethods.completeHouseInfo(test);
	}

}