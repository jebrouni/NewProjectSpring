package com.example.web;




import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Date;


import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.EtudiantRepository;
import com.example.enteties.Etudiant;



@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController {
	@Value("${dir.images}")
	private String imageDir;
	//injection de dependences
	@Autowired
     private EtudiantRepository etudiantRepository;
	@RequestMapping(value="/Index")
	//Model de spring pour stoker les resultat 
   	public String Index(Model model,
			@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Page<Etudiant> pageEtudiant=  etudiantRepository
				.chercheEtudiants("%"+mc+"%",PageRequest.of(p, 5));
		
		int pageCount=pageEtudiant.getTotalPages();
		int[] pages= new int [pageCount];
		for( int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageEtudiants", pageEtudiant);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle",mc);
		return "etudiants";
		
	}
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String  formEtudiant(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "FormEtudiant";
	}
	@RequestMapping(value="/SaveEtudiant", method=RequestMethod.POST)
	public String  save(@Valid Etudiant et,BindingResult bindingResult,
			@RequestParam(name="picture") MultipartFile file) throws Exception, IOException {
		if(bindingResult.hasErrors()) {
		
			return "FormEtudiant";
		}
		if(!(file.isEmpty())) {
			et.setPhoto(file.getOriginalFilename());
		}
		etudiantRepository.save(et);
		if(!(file.isEmpty())) {
			
     	    et.setPhoto(file.getOriginalFilename());
     		file.transferTo(new File(imageDir+et.getId()));
		}

			
		
		
		
		return "redirect:Index";
	}
	}
