package com.sahoo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sahoo.entity.Menu;
import com.sahoo.exception.MyException;
import com.sahoo.repo.MenuRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuService {
	private static final Logger log = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	MenuRepo repo;

	public java.util.List<Menu> getAllMenu() throws MyException {

		List<Menu> list = null;
		try {

			list = repo.findAll();
			log.info("All menu list will featch .... ");
			if (list.size() <= 0) {
				log.error("Failed to find the requested menu");
				throw new MyException(" There is no menu found , please try the correct way");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Optional<Menu> getMenuById(int mid) {
		
		log.info("inside getMenuById()");
		return repo.findById(mid);
		 
	}

	public Menu saveMenu(Menu menu) {

		log.info(" inside savemenu() ");

		return repo.save(menu);

	}

	public Menu updateMenu(Menu menu) {
		Menu oldMenu = null;
		try {
			log.info(" inside updateMenu() ");
			Optional<Menu> optionalMenu = repo.findById(menu.getMenuId());
			if (optionalMenu.isPresent()) {
				oldMenu = optionalMenu.get();
				oldMenu.setMenuName(menu.getMenuName());
				oldMenu.setServiceType(menu.getServiceType());
				oldMenu.setCustomerName(menu.getCustomerName());
				oldMenu.setMenuPrice(menu.getMenuPrice());
				repo.save(oldMenu);
			} else {
				return new Menu();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return oldMenu;
	}

	public String deleteMenuById(int menuId) {
		log.info("inside deleteMenuById()");
		repo.deleteById(menuId);
		return "menu is deleted by registered with " + menuId;
	}

}
