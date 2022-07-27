package com.sahoo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sahoo.entity.Menu;
import com.sahoo.exception.MyException;
import com.sahoo.repo.MenuRepo;
import com.sahoo.service.MenuService;

@SpringBootTest
class HotelServiceApplicationTests {

	@Autowired
	MenuRepo repo;
	
	@Autowired
	MenuService service;
	
	  @Test void contextLoads() { }
	 
		
		  @Test 
		  public void testsave() 
		  {
		  Menu menu = new Menu();  
		  menu.setMenuId(1);
		  menu.setMenuName("chicken");
		  menu.setServiceType("good");
		  menu.setCustomerName("Gajendra");
		  menu.setMenuPrice(100); 
		  service.saveMenu(menu);
		  assertNotNull(repo.findById(menu.getMenuId()).get());
		  
		  }
		  @Test
		  public void testGetAllMenu() throws MyException
		  {
			  List<Menu> list = service.getAllMenu();
			  assertThat(list).size().isGreaterThan(0);
			  
		  }
		  
		  @Test
		  public void testGetMenuById()
		  {
			  Menu menu =service.getMenuById(1).get();
			  assertEquals(100, menu.getMenuPrice());
		  }
		  
		  @Test
		  public void testUpdateMenu()
		  {
			  Menu menu =service.getMenuById(1).get();
			  menu.setMenuPrice(100);
			  service.saveMenu(menu);
			  assertNotEquals(200, repo.findById(1).get().getMenuPrice());
		  }
		  
		  @Test
		 public void testDelete()
		 {
			  service.deleteMenuById(1);
			  assertThat(repo.existsById(1)).isFalse();
		 }
	
}
