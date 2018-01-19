package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.acortes.hcrawler.configuration.AppConfig;
import com.acortes.hcrawler.model.House;
import com.acortes.hcrawler.service.HouseService;
import com.acortes.hcrawler.util.HouseFactory;

public class DBTest {

	@Test
	public void test() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HouseService hibService = (HouseService) context.getBean("houseService");
		House h = HouseFactory.createStubHouse();
		hibService.saveHouse(h);
	}

}
