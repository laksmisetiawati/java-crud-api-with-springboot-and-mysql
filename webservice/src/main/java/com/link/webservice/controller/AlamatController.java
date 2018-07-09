package com.link.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.link.webservice.dao.AlamatDao;
import com.link.webservice.entity.Alamat;

@RestController
@RequestMapping("/api")

public class AlamatController {
	
	@Autowired
	
	private AlamatDao ad;
	
	@RequestMapping(value="/alamat", method=RequestMethod.GET)
	
	public Page<Alamat> daftarAlamat(Pageable page){
		return ad.findAll(page);
	}
 	
}