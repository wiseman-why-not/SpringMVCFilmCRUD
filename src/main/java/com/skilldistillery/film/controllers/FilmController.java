package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.data.FilmDaoImpl;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private DatabaseAccessor dao;

	@RequestMapping("home.do")
	public String home() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping("FilmSelect.do")
	public ModelAndView searchByFilmId( @ RequestParam("filmId") int filmId) {
		System.out.println("here");
		dao = new FilmDaoImpl();
		ModelAndView mv = new ModelAndView();
		Film film = dao.findFilmById(filmId);
		mv.setViewName("FilmSearch.jsp");
		mv.addObject("film", film );
		return mv;
	}
}
