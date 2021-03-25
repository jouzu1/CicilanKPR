package com.example.demo.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Absensi;
import com.example.demo.model.Cicilan;
import com.example.demo.model.CicilanRowMapper;

@RestController
@RequestMapping("/cicilan")
public class CicilanController {
	
	@Autowired
	JdbcTemplate jdbc;
	
//	public int insertCicilan(Cicilan cicilan) {
//		return jdbc.update("insert into cicilan(id,nik,start_date,end_date) values (" + cicilan.getId() + ",'"
//				+ cicilan.getNik() + "','" + cicilan.getStart_date() + "','" + cicilan.getEnd_date() + "')");
//	}
	
	
	
	
	
	public int insertCicilan() {
		return jdbc.update("CALL ulangBulan ('2020-01-01',20000000,1.2,15)");
	}
	
	public List<Cicilan> getCicilan() {

		String sql = "Select * from dummy";

		// Meng-instance object baru dari object List
		List<Cicilan> cicilan = jdbc.query(sql, new CicilanRowMapper());
//		System.out.println(absensi);
		return cicilan;
		
	}
	
	@PostMapping("")
//	List<Cicilan> post(@Param("dateFrom"),@Param("platfon"),@Param("bunga"),@Param("lamapinjam") String dateFrom, Integer platfon, Integer bunga, Integer lamapinjam);
	public String add() {
		if (this.insertCicilan() >= 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	@GetMapping("/data")
	public List<Cicilan> list() {
		return getCicilan();
	}
	

}
