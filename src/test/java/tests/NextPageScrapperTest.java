package tests;

import org.junit.Test;

import com.real_estates.hcrawler.scrapers.MercadoLibreScrapperMethods;

public class NextPageScrapperTest {

	@Test
	public void test() {
		MercadoLibreScrapperMethods.scrapHousesFromUrl("https://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/centro-sur/");
	}

}
