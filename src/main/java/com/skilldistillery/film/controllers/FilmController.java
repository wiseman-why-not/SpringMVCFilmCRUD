package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public ModelAndView searchByFilmId(@RequestParam("filmId") String keyword) {
		dao = new FilmDaoImpl();
		ModelAndView mv = new ModelAndView();
		
		try {
			
			Film film = dao.findFilmById(Integer.parseInt(keyword));
			mv.setViewName("FilmSearch.jsp");
			mv.addObject("film", film);
			return mv;
		} catch (NumberFormatException e) {
			List<Film> listOfFilms = new ArrayList<>();
			listOfFilms = dao.findFilmByKeyword(keyword);
			mv.setViewName("Keyword.jsp");
			mv.addObject("films", listOfFilms);
			return mv;
		}
		
	}
	
//	@RequestMapping("Keyword.do")
//	public ModelAndView searchByKeyword(@RequestParam("filmId") String keyword) {
//		dao = new FilmDaoImpl();
//		ModelAndView mv = new ModelAndView();
//		List<Film> listOfFilms = new ArrayList<>();
//		listOfFilms = dao.findFilmByKeyword(keyword);
//		mv.setViewName("FilmSearch.jsp");
//		mv.addObject("film", listOfFilms);
//		return mv;
//	}

//	@RequestMapping("CreateFilm.do")
//	public ModelAndView createFilm(@RequestParam("title") String title, @RequestParam("description") String description,
//			@RequestParam("releaseYear") int releaseYear, @RequestParam("rentalDuration") int rentalDuration,
//			@RequestParam("rentalRate") double rentalRate, @RequestParam("lengthOfMovie") int length,
//			@RequestParam("replacementCost") double replacementCost, @RequestParam("rating") String rating,
//			@RequestParam("specialFeatures") String specialFeatures) {
//		Film film = new Film(title, description, releaseYear, 1, rentalDuration, rentalRate, length, replacementCost,
//				rating, specialFeatures);
//		dao = new FilmDaoImpl();
//		ModelAndView mv = new ModelAndView();
//		film = dao.createFilm(film);
//		mv.setViewName("FilmSearch.jsp");
//		mv.addObject("film", film);
//		return mv;
//	}
	@RequestMapping("CreateFilm.do")
	public ModelAndView createFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		System.out.println("******** FILM ID (***** : " + film.getFilmId());
		if (film.getFilmId() != 0) {
			mv.setViewName("home.jsp");
			updateFilm(film);
			return mv;
		}
			
		
		Film filmChange = new Film(film.getTitle(), film.getDescription(), film.getReleaseYear(), 1, film.getRentalDuration(), film.getRentalRate(), film.getLength(), film.getReplacementCost(),
				film.getRating(), film.getSpecialFeatures());
		dao = new FilmDaoImpl();
		film = dao.createFilm(filmChange);
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
		if(dao.updateFilm(film)) {
			return "home.jsp";
			
		}else {
			return "Error.jsp";
		}
	}
		
		
		@RequestMapping("FilmEdit.do")
		public ModelAndView filmEdit( int filmId) {
			ModelAndView mv = new ModelAndView();
			if (filmId == 0) { 
				Film newFilm = new Film("placeholder", "placeholder", 2020, 1, 2, 2.5, 60, 20, "PG", "Trailers");
				mv.setViewName("FilmEdit.jsp");
				mv.addObject(newFilm);
				return mv;
			}
			Film film = dao.findFilmById(filmId);

			
			mv.setViewName("FilmEdit.jsp");
			mv.addObject(film);
			return mv;
		}
			
			
	
}
