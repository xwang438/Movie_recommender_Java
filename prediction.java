import java.util.ArrayList;

/**
 * This class predicts a user's rating on a movie 
 * And recommend 3 top movies for the user
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
	 * Firstly find all the movies that have been rated by the user
     * Then make the prediction based on the movies' similarity and the user's ratings on the similar movies.
	 */
	public int predict(){
		Integer[] user_ratings = user_rated_movie.user_ratings(user);

		ArrayList<Double> similarity = new ArrayList<Double>();
		ArrayList<Integer> ratings = new ArrayList<Integer>();
		for(int i = 0; i < user_ratings.length; i++){
			if(user_ratings[i] != 0){
			    item_based_cf ibc = new item_based_cf(i + 1, movie);
			    double sim = ibc.pearson_sim();
			    similarity.add(sim);
			    ratings.add(user_ratings[i]);
			}
		}
		System.out.println("Finish computing similarities!");
		
		double upper = 0, lower = 0;
		for(int i = 0; i < ratings.size(); i++){
			upper += similarity.get(i) * ratings.get(i);
			lower += Math.abs(similarity.get(i));
		}
		int predict_data = (int) (upper / lower);
		System.out.println("prediction: " + predict_data);
		return predict_data;
	}
	/**
	 * Compute the Root Squared Mean Error (RSME) for the movie 1: Toy Story (1995)
	 * Computes the top 10 movies that can be recommended to a user
	 */
	public ArrayList<String> recommendation(){
		ArrayList<String[]> test = read_file.readInData("test", '\t');
		ArrayList<Integer[]> predicts = new ArrayList<Integer[]>();
		for(int i = 0; i < test.size(); i++){
			if(Integer.parseInt(test.get(i)[0]) == user){
				prediction p = new prediction(user, Integer.parseInt(test.get(i)[1]));
				int pp = p.predict();
				Integer[] pair = {pp, Integer.parseInt(test.get(i)[2]), Integer.parseInt(test.get(i)[1])};
				predicts.add(pair);
			}
		}
		//compute the RMSE error
	    double upper = 0;
		for(int i = 0; i < predicts.size(); i++){
			upper += Math.pow((predicts.get(i)[1] - predicts.get(i)[0]), 2);
		}
		double rsme = Math.sqrt(upper / predicts.size());
		System.out.println("RSME error: " + rsme);
        
        // choose top 3 rated movies by the user and output the movies' names
		ArrayList<Integer[]> predict_sorting = new ArrayList<Integer[]>();
		//sort the ratings by insertion sort
		for(int i = 0; i < predicts.size(); i++){
			Integer[] pair = {predicts.get(i)[0], predicts.get(i)[2]};
			if(predict_sorting.size() == 0)
				predict_sorting.add(pair);
			else if(predicts.get(i)[0] < predict_sorting.get(0)[0])
				predict_sorting.add(0, pair);
			else if(predicts.get(i)[0] > predict_sorting.get(predict_sorting.size() - 1)[0])
				predict_sorting.add(pair);
			else{
				for(int j = 0; j < predict_sorting.size() - 1; j++){
					if(predict_sorting.get(j)[0] < predicts.get(i)[0] && predict_sorting.get(j + 1)[0] >= predicts.get(i)[0])
						predict_sorting.add(j + 1, pair);
				}
			}
		}
		System.out.println("sorting prediction is done! " + predict_sorting.size());
		
		//find the top 3 movies that can be recommended to the user
		ArrayList<String> recommend = new ArrayList<String>();
		ArrayList<String[]> movies = read_file.readInData("u.item", '|');
		ArrayList<String[]> users = read_file.readInData("u.user", '|');
		if(predict_sorting.size() < 3){
			for(int i = 0; i < predict_sorting.size(); i++){
				int movieID = predict_sorting.get(i)[1];
				String movie_name = movies.get(movieID - 1)[1];
				recommend.add(movie_name);
			}
		}
		else{
			for(int i = 0; i < 10; i++){
				int movieID = predict_sorting.get(i)[1];
				String movie_name = movies.get(movieID - 1)[1];
				recommend.add(movie_name);
			}
		}
		String age = users.get(user - 1)[1];
		String sex = users.get(user - 1)[2];
		String occupation = users.get(user - 1)[3];
		System.out.println("UserID: " + user + "\n" + "Age: " + age + "; Sex: " + sex + "; occupation: " + occupation);
		System.out.println("Recommend movies for this user: ");
		for(int i = 0; i < recommend.size(); i++)
			System.out.println(recommend.get(i));
		return recommend;
	}
	
	public static void main(String[] args){
		prediction p = new prediction(7, 32);
		p.predict();
		p.recommendation();
	}
}
