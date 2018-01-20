package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.hcrawler.config.AppConfig;
import com.hcrawler.util.HouseFactory;
import com.housedata.model.House;
import com.housedata.service.HouseService;

public class DBTest {

	@Test
	public void test() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HouseService hibService = (HouseService) context.getBean("houseService");
		House h = HouseFactory.createStubHouse();
		hibService.saveHouse(h);
	}

}
