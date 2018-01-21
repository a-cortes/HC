package tests;

import com.real_estates.housedata.model.House;
import com.real_estates.util.HouseFactory;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.real_estates.orchestration.AppConfig;
import com.real_estates.housedata.service.HouseService;

public class DBTest {

	@Test
	public void test() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HouseService hibService = (HouseService) context.getBean("houseService");
		House h = HouseFactory.createStubHouse();
		hibService.saveHouse(h);
	}

}
