import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.dao.ItemMapper;
import com.model.Item;

public class ItemDaoTest {
	

	// @Test
	public void testSelect() {
		SqlSession sqlSession = getSessionFactory().openSession();
		ItemMapper im = (ItemMapper) sqlSession.getMapper(ItemMapper.class);

		Item item = new Item();
		item.setId(2);
		// item.setPrice(6000.00);

		List<Item> list = im.select(item);
		for (Item s : list) {
			System.out.println(s);
		}
	}

	// @Test
	public void testDelte() {
		SqlSession sqlSession = getSessionFactory().openSession();
		ItemMapper im = (ItemMapper) sqlSession.getMapper(ItemMapper.class);

		Item item = new Item();
		item.setId(2);
		item.setPrice(6000.00);

		im.delete(item);

	}

	@Test
	public void testUpdate() {
		SqlSession sqlSession = getSessionFactory().openSession();
		ItemMapper im = (ItemMapper) sqlSession.getMapper(ItemMapper.class);

		Item item = new Item();
		item.setId(1);
		item.setPrice(5798.00);

		im.update(item);

	}

	@Test
	public void test() {
		SqlSession sqlSession = getSessionFactory().openSession();
		ItemMapper im = (ItemMapper) sqlSession.getMapper(ItemMapper.class);

		/*
		 * DisSystemMonitoring d = new DisSystemMonitoring();
		 * d.setAgentId("123"); d.setJvmState("abc"); d.setProcessState("efg");
		 * 
		 * dm.insert(d);
		 */
		List<Item> list = im.getAll();
		for (Item s : list) {
			System.out.println(s);
		}

	}
	
	private static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "mybatis-config.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
}
