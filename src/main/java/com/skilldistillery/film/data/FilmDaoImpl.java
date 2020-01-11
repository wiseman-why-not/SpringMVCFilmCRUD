package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;


@Controller
public class FilmDaoImpl  implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	private String user = "student";
	private String pass = "student";
	private int uc;
	private boolean committed = false;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement prepStatementGenerator(Connection conn, String sqlTxt, int id) throws SQLException {
		PreparedStatement prepStmt = conn.prepareStatement(sqlTxt);
		prepStmt.setInt(1, id);

		return prepStmt;
	}

	private PreparedStatement prepStatementGenerator(Connection conn, String sqlTxt, String keyWord)
			throws SQLException {
		PreparedStatement prepStmt = conn.prepareStatement(sqlTxt);
		prepStmt.setString(1, keyWord);
		prepStmt.setString(2, keyWord);

		return prepStmt;
	}

	private PreparedStatement prepStatementGenerator(Connection conn, String sqlTxt, Film film) throws SQLException {
		PreparedStatement prepStmt = conn.prepareStatement(sqlTxt, Statement.RETURN_GENERATED_KEYS);
		prepStmt.setString(1, film.getTitle());
		prepStmt.setString(2, film.getDescription());
		prepStmt.setInt(3, film.getReleaseYear());
		prepStmt.setInt(4, film.getLanguageID());
		prepStmt.setInt(5, film.getRentalDuration());
		prepStmt.setDouble(6, film.getRentalRate());
		prepStmt.setInt(7, film.getLength());
		prepStmt.setDouble(8, film.getReplacementCost());
		prepStmt.setString(9, film.getRating());
		prepStmt.setString(10, film.getSpecialFeatures());

		conn.setAutoCommit(false);
		uc = prepStmt.executeUpdate();

		return prepStmt;
	}

	@Override
	public Film findFilmById(int filmId) {
		int id = filmId;
		String sqlTxt = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";
		Film film = null;
		List<Actor> actorList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, id);
				ResultSet rs = prepStmt.executeQuery();) {
			while (rs.next()) {
				film = new Film(rs.getInt("film.id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));
				actorList.add(new Actor(rs.getInt("actor.id"), rs.getString("first_name"), rs.getString("last_name")));
				film.setLanguageList(findLanguageById(film.getLanguageID()));
				film.setActorList(findActorsByFilmId(film.getId()));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		int id = actorId;
		String sqlTxt = "SELECT * FROM actor WHERE id = ?";
		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, id);
				ResultSet rs = prepStmt.executeQuery();) {
			while (rs.next()) {
				actor = new Actor(rs.getInt("actor.id"), rs.getString("first_name"), rs.getString("last_name"));
				actor.setFilmList(findFilmByActorId(actor.getId()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		int id = filmId;
		String sqlTxt = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";

		Actor actor = null;
		List<Actor> actorList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, id);
				ResultSet rs = prepStmt.executeQuery();) {
			while (rs.next()) {
				actor = new Actor(rs.getInt("actor.id"), rs.getString("first_name"), rs.getString("last_name"));
				actorList.add(actor);
				actor.setFilmList(findFilmByActorId(actor.getId()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actorList;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyWord) {
		String keyword = keyWord;
		String sqlTxt = "SELECT * FROM film WHERE film.title LIKE ? OR film.description LIKE ?";
		Film film = null;
		List<Film> filmList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, "%" + keyword + "%");
				ResultSet rs = prepStmt.executeQuery();) {
			while (rs.next()) {
				film = new Film(rs.getInt("film.id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));
				film.setActorList(findActorsByFilmId(film.getId()));
				film.setLanguageList(findLanguageById(film.getLanguageID()));
				filmList.add(film);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	public List<String> findLanguageById(int languageId) {
		List<String> languageList = new ArrayList<>();
		int id = languageId;
		String sqlTxt = "SELECT * FROM language WHERE id=?";

		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, id);
				ResultSet rs = prepStmt.executeQuery();) {
			while (rs.next()) {
				languageList.add(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return languageList;
	}

	public List<Film> findFilmByActorId(int actorId) {
		int localActorId = actorId;
		String sqlTxt = "SELECT * FROM film_actor JOIN film ON film_actor.film_id = film.id WHERE film_actor.actor_id = ?";
		Film film = null;
		List<Film> filmList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, localActorId);
				ResultSet rs = prepStmt.executeQuery();) {
			while (rs.next()) {
				film = new Film(rs.getInt("film.id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));

				filmList.add(film);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	@Override
	public Film createFilm(Film film) {
		int uc = 0;
		String sqlTxt = "INSERT INTO film  (title, description,release_year, language_id, rental_duration, rental_rate, length, replacement_cost , rating, special_features)"
				+ "VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement prepStmt = prepStatementGenerator(conn, sqlTxt, film);
				ResultSet keys = prepStmt.getGeneratedKeys();
				AutoRollBack autoBack = new AutoRollBack(conn, committed)) {
			if (keys.next()) {
				int filmId = keys.getInt(1);
				film.setId(filmId);
			}
			System.out.println("here");
			committed = true;
			autoBack.setcommitted(committed);
			conn.commit();
			return film;

		} catch (Exception e) {
			// Something went wrong.
			System.err.println("Error during inserts.");
			e.printStackTrace();
			// Need to rollback, which also throws SQLException.
			if (!committed) {
				try {

				} catch (Exception e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	class AutoRollBack implements AutoCloseable {

		private Connection conn;
		private boolean committed;

		public AutoRollBack(Connection conn, boolean commit) throws SQLException {
			this.conn = conn;

		}

		public void rollBack() throws SQLException {
			conn.rollback();
		}

		public void setcommitted(boolean committed) {
			this.committed = committed;
		}

		@Override
		public void close() throws SQLException {
			if (!committed) {
				conn.rollback();
			}

			conn.close();

		}

	}
}