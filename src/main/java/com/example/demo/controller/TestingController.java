package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Biodata;
import com.example.demo.model.BiodataRowMapper;

/**
 * 
 * @author Jouzu
 * Controller terpakai sebagai link url
 */

@RestController
public class TestingController {

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String hello() {
		return "<html><body><h1><b><center>Batch 8 : Mochammad Jouzu Ridzky Imansyah</center></b></body></h1></html>\r\n"
				+ "";
	}
	
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
	
	
	
	@RequestMapping("/test")
	public String Test() {
		List<Biodata> lstbioata = getBiodata();
		String dummy = " ";
		for(int i = 0; i<lstbioata.size();i++) {
			dummy += lstbioata.get(i).getNik() +" "+lstbioata.get(i).getNama() +" Memiliki Gaji " +lstbioata.get(i).getId_salary()+ ", ";
		}
		return dummy;
	}
	
	@RequestMapping("/insert")
	public String insertData() {
		Biodata biodata = new Biodata();
		biodata.setNik("12345");
		biodata.setNama("Jouzu");
		biodata.setAlamat("Jakarta");
		biodata.setId_salary(10000000);
		
		if(this.insertBiodata(biodata)>=1) {
			return "<html><body><h1><b><center>Data Berhasil Dimasukkan</center></b></body></h1></html>\r\n"
					+ "";
		}else {
			return "<html><body><h1><b><center>Masukkan Data Yang Bener Dong</center></b></body></h1></html>\r\n"
					+ "";
		}
	}
		//PR
		public int updateBiodata(String nik) {
			return jdbc.update("UPDATE `biodata` SET `nik` = '"+nik+"' WHERE id_salary = 10000000");
			
		}
		
		public int deleteBiodata(String nik) {
			return jdbc.update("DELETE FROM `biodata` WHERE `nik` = '"+nik+"';");
		}
		
		@RequestMapping("/delete")
		public String deleteBiodata() {
			deleteBiodata("4136030019");
			String text = "Selamat, data dengan NIK yang telah di input telah berhasil di hapus";
			return text;
		}
		
		@RequestMapping("/update")
		public String updateBiodata() {
			updateBiodata("4136030019");
			String text = "Data Telah Berhasil Di Update";
			return text;
		}
	
}
