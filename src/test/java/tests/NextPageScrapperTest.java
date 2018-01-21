package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hcrawler.scrapers.MercadoLibreScrapperMethods;

public class NextPageScrapperTest {

	@Test
	public void test() {
		MercadoLibreScrapperMethods.scrapHousesFromUrl("https://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/centro-sur/");
	}

}
