package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.model.Biodata;
import com.example.demo.model.BiodataRowMapper;

public class DaoBiodata {
	@Autowired
	JdbcTemplate jdbc;
	
	public int insertBiodata(Biodata biodata) {
		return jdbc.update("insert into biodata(nik,nama,alamat,id_salary) values('"+biodata.getNik()+"','"+biodata.getNama()+"','"+biodata.getAlamat()+"','"+biodata.getId_salary()+"')");
		
	}
	
	public List<Biodata> getBiodata() {
		String sql = "Select * from biodata";
		List<Biodata> biodata = jdbc.query(sql, new BiodataRowMapper());
		return biodata;
	}
}
