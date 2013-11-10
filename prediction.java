import java.util.ArrayList;

/**
 * This class predicts a user's rating on a movie

 */

public class prediction {
	private int user;
	private int movie;
	/** 
	 * @param user
	 *            the userID that will make prediction for
	 * @param movie
	 *            the movieID that will predict the rating
	 */
	public prediction(int u, int m){
		user = u;
		movie = m;
	}
	/**
	 * Compute the prediction for the given movie
	 * Firstly find the movies that are in the same genre, and had been rated by the given user
	 * Then compute the similarity between the movies
	 * Finally make the prediction based on the similarity and the user's ratings on the similar movies.
	 */
	public int predict(){
		user_rated_movie urm = new user_rated_movie(user);
		ArrayList<Integer> movies_by_user = urm.rated_movies();
		ArrayList<Integer> movies_genre = movie_genre.sameGenreMovies(movie);
		ArrayList<Integer> movies_rated_genre = new ArrayList<Integer>();
		for(int i = 0; i < movies_by_user.size(); i++){
			if(movies_genre.contains(movies_by_user.get(i)))
				movies_rated_genre.add(movies_by_user.get(i));
		}
		System.out.println("Finding movies in the same genre is done!" + movies_rated_genre.size());
		
		ArrayList<Double> similarity = new ArrayList<Double>();
		Integer[] user_ratings = urm.user_ratings();
		ArrayList<Integer> ratings = new ArrayList<Integer>();
		for(int i = 0; i < movies_rated_genre.size(); i++){
			item_based_cf ibc = new item_based_cf(movies_rated_genre.get(i), movie);
			double sim = ibc.pearson_sim();
			System.out.println("similarity: " + sim);
			similarity.add(sim);
			ratings.add(user_ratings[movies_rated_genre.get(i) - 1]);
		}
		System.out.println("Finish computing similarities!");
		
		double upper = 0, lower = 0;
		for(int i = 0; i < movies_rated_genre.size(); i++){
			upper += similarity.get(i) * ratings.get(i);
			lower += Math.abs(similarity.get(i));
		}
		int predict_data = (int) (upper / lower);
		return predict_data;
	}
	/**
	 * Compute the Root Squared Mean Error (RSME) for the movie 1: Toy Story (1995)
	 * 
	 */
	public double error(){
		ArrayList<Integer[]> test = training_test.train_test().get(1);
		ArrayList<Integer[]> predicts = new ArrayList<Integer[]>();
		for(int i = 0; i < test.size(); i++){
			if(test.get(i)[0] == 1){
				prediction p = new prediction(test.get(i)[1], 1);
				int pp = p.predict();
				Integer[] pair = {pp, test.get(i)[2]};
				predicts.add(pair);
			}
		}
	    double upper = 0;
		for(int i = 0; i < predicts.size(); i++){
			upper += Math.pow((predicts.get(i)[1] = predicts.get(i)[0]), 2);
		}
		double rsme = Math.sqrt(upper / predicts.size());
		System.out.println("RSME error: " + rsme);
		return rsme;
	}
	public static void main(String[] args){
		prediction p = new prediction(1, 2);
		p.error();
	}
}
