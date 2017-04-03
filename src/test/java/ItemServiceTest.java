
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.Item;
import com.service.ItemService;


public class ItemServiceTest {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		//context.getBean("DisSystemMonitoringMapper");
		ItemService itemService = (ItemService) context.getBean("ItemService");
		List<Item> list = itemService.getAll();
		
		for (Item d : list) {
			System.out.println(d);
		}
	}
}
