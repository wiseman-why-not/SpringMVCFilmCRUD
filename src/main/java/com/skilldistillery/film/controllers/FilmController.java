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
		return "home.jsp";
	}

	@RequestMapping("FilmSelect.do")
	public ModelAndView searchByFilmId(@RequestParam("filmId") int filmId) {
		dao = new FilmDaoImpl();
		ModelAndView mv = new ModelAndView();
		System.out.println(filmId);
		Film film = dao.findFilmById(filmId);
		mv.setViewName("FilmSearch.jsp");
		mv.addObject("film", film);
		return mv;
	}

	@RequestMapping("CreateFilm.do")
	public ModelAndView createFilm(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("releaseYear") int releaseYear, @RequestParam("rentalDuration") int rentalDuration,
			@RequestParam("rentalRate") double rentalRate, @RequestParam("lengthOfMovie") int length,
			@RequestParam("replacementCost") double replacementCost, @RequestParam("rating") String rating,
			@RequestParam("specialFeatures") String specialFeatures) {
		Film film = new Film(title, description, releaseYear, 1, rentalDuration, rentalRate, length, replacementCost,
				rating, specialFeatures);
		dao = new FilmDaoImpl();
		ModelAndView mv = new ModelAndView();
		film = dao.createFilm(film);
		mv.setViewName("FilmSearch.jsp");
		mv.addObject("film", film);
		return mv;
	}

	@RequestMapping("Delete.do")
	public String deleteFilm(@RequestParam("Delete") int filmId) {
		dao = new FilmDaoImpl();
		//ModelAndView mv = new ModelAndView();
		dao.deleteFilm(filmId);
		return "deleteFilm.jsp";
		

	}
	@RequestMapping("Update.do")
	public String updateFilm(@RequestParam("Update") Film film) {
		dao = new FilmDaoImpl();
		//ModelAndView mv = new ModelAndView();
		dao.updateFilm(film);
		return "deleteFilm.jsp";
	}
		
		
		@RequestMapping("FilmEdit.do")
		public ModelAndView filmEdit( int filmId) {
			ModelAndView mv = new ModelAndView();
			Film film = dao.findFilmById(filmId);
			System.out.println(film);
			mv.setViewName("FilmEdit.jsp");
			mv.addObject(film);
			return mv;
		}
			
			
	
}
