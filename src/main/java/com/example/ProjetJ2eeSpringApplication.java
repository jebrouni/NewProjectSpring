package com.example;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.dao.EtudiantRepository;
import com.example.enteties.Etudiant;

@SpringBootApplication
public class ProjetJ2eeSpringApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext cnx= SpringApplication.run(ProjetJ2eeSpringApplication.class, args);
		EtudiantRepository etudiantRepositry=cnx.getBean(EtudiantRepository.class);
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
//		etudiantRepositry.save(
//				new Etudiant("soufian",df.parse("1992-02-19"),"soufian@gmail.com","soufian.jpg"));
//		etudiantRepositry.save(
//				new Etudiant("wiam",df.parse("2004-07-04"),"wiam@gmail.com","wiam.jpg"));
//		etudiantRepositry.save(
//				new Etudiant("jihad",df.parse("1993-05-12"),"jihadn@gmail.com","jihad.jpg"));
		Page<Etudiant> ets= etudiantRepositry.chercheEtudiants("%W%", PageRequest.of(0, 5));
	ets.forEach(e->{System.out.println(e.getNom());});
	}

}
