package com.sahoo.controller;

import java.util.List;
import java.util.Optional;

import javax.xml.ws.soap.Addressing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahoo.entity.Menu;
import com.sahoo.service.MenuService;

@RestController
@RequestMapping("menu")
@ControllerAdvice
public class MenuController {

	private static final Logger log = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	MenuService service;

	@PostMapping("/")
	public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu) {
		log.info("Menu is submiting ");
		try {
			if (menu.getMenuName().length() == 0 || menu.getCustomerName().length() == 0 || menu.getMenuPrice() <= 0
					|| menu.getServiceType().length() == 0) {
				log.warn("inside saveMenu(), You are giving wrong input , please look into your Menu details");
				return (ResponseEntity<Menu>) new ResponseEntity("Hi You are giving wrong input , please look into it",
						HttpStatus.BAD_REQUEST);
			}

			this.service.saveMenu(menu);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
		return ResponseEntity.ok(menu);
	}

	@GetMapping("/")
	public ResponseEntity<List<Menu>> getMenus() {
		List<Menu> list = null;
		try {
			list = service.getAllMenu();
			log.info("Retriving all the Menu");
			if (list.size() <= 0) {
				log.warn("There is No privious menu has processed , please proceed for new Menu");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.of(Optional.of(list));

	}

	@GetMapping("{id}")
	public ResponseEntity<Menu> getMenuById(@PathVariable("id") int menuId) {

		Optional<Menu> menu = null;
		try {
			menu = service.getMenuById(menuId);
			log.info("You are featching menu by featching MenuID =" + menuId);
			if (menu == null) {
				log.warn("inside getMenuById(),You are giving wrong MenuId , please correct it");

				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.of(menu);

	}

	@PutMapping("/updateMenu")
	public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu) {
		try {

			service.updateMenu(menu);
			log.info("Requested Menu updated successfully");
			return ResponseEntity.ok(menu);
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMenu(@PathVariable("id") int menuId) {
		try {
			log.info("inside deleteMenu()");
			this.service.deleteMenuById(menuId);
			// return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return new ResponseEntity("Requested Menu is deleted  by providing menu 'ID' is " + menuId + ", thanks",
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

}
