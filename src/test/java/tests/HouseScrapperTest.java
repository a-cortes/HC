package tests;

import org.junit.Test;

import com.realestate.hcrawler.scrapers.MercadoLibreScrapperMethods;
import com.realestate.housedata.model.PropertySale;

public class HouseScrapperTest {

	@Test
	public void test() {
		PropertySale test = new PropertySale();
		test.setUrl("https://casa.mercadolibre.com.mx/MLM-609669510-en-juriquilla-_JM");
		MercadoLibreScrapperMethods.completeHouseInfo(test);
	}

}
