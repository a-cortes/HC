package tests;

import com.realestate.hcrawler.orchestration.AppConfig;
import com.realestate.hcrawler.util.HouseFactory;
import com.realestate.housedata.model.PropertySale;
import com.realestate.housedata.service.HouseService;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class DBTest {

	@Test
	public void test() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HouseService hibService = (HouseService) context.getBean("houseService");
		PropertySale h = HouseFactory.createStubHouse();
		hibService.saveHouse(h);
	}

}
