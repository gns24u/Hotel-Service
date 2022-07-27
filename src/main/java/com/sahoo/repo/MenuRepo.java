package com.sahoo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahoo.entity.Menu;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer>{

}
