import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class computes the top 10 movies that can be recommended to a user

 */
public class recommendation {
	private int user;
	/** 
	 * @param user
	 *            the userID that will make recommendation for
	 */
	public recommendation(int u){
		user = u;
	}
	/**
	 * This method is to get the recommended movies for a given user
	 * Firstly find the movies that are rated by the user in the test dataset
	 * Then compute the predictions on the movies by the user
	 * Finally choose top 10 rated movies by the user and output the movies' names
	 */
	public ArrayList<String> recommend_movies(){
		ArrayList<Integer[]> test = training_test.train_test().get(1);
		ArrayList<Integer> test_movies = null;
		//find the movies that are rated by the user in the test dataset
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < test.size(); i++){
			if(!map.containsKey(test.get(i)[1])){
			    map.put(test.get(i)[1], new ArrayList<Integer>());
			}
			test_movies = map.get(test.get(i)[1]);
			test_movies.add(test.get(i)[0]);
		}
		test_movies = map.get(user);
		System.out.println("test movie size: " + test_movies.size());
		
		//make predictions on the movies
		ArrayList<Integer[]> predict_rating = new ArrayList<Integer[]>();
		for(int i = 0; i < test_movies.size(); i++){
			prediction p = new prediction(1, test_movies.get(i));
			int predict_data = p.predict();
			System.out.println("prediction: " + predict_data);
			Integer[] pp = {test_movies.get(i), predict_data};
			predict_rating.add(pp);
		}
		System.out.println("prediction is done! " + predict_rating.size());
		
		ArrayList<Integer[]> predict_sorting = new ArrayList<Integer[]>();
		//sort the ratings by insertion sort
		for(int i = 0; i < predict_rating.size(); i++){
			if(predict_sorting.size() == 0)
				predict_sorting.add(predict_rating.get(i));
			else if(predict_rating.get(i)[1] < predict_sorting.get(0)[1])
			    predict_sorting.add(0, predict_rating.get(i));
			else if(predict_rating.get(i)[1] > predict_sorting.get(predict_sorting.size() - 1)[1])
				predict_sorting.add(predict_rating.get(i));
			else{
				for(int j = 0; j < predict_sorting.size() - 1; j++){
					if(predict_sorting.get(j)[1] < predict_rating.get(i)[1] && predict_sorting.get(j + 1)[1] > predict_rating.get(i)[1])
						predict_sorting.add(j + 1, predict_rating.get(i));
				}
			}
		}
		System.out.println("sorting prediction is done! " + predict_sorting.size());
		
		//find the top 10 movies that can be recommended to the user
		ArrayList<String> recommend = new ArrayList<String>();
		ArrayList<ArrayList<String>> movies = movie_genre.readInData();
		if(predict_sorting.size() < 10){
			for(int i = 0; i < predict_sorting.size(); i++){
				int movieID = predict_sorting.get(i)[0];
				String movie_name = movies.get(movieID - 1).get(1);
				recommend.add(movie_name);
			}
		}
		else{
			for(int i = 0; i < 10; i++){
				int movieID = predict_sorting.get(i)[0];
				String movie_name = movies.get(movieID - 1).get(1);
				recommend.add(movie_name);
			}
		}
		return recommend;
	}
	public static void main(String[] args){
		recommendation r = new recommendation(1);
		ArrayList<String> movies = r.recommend_movies();
		for(String movie : movies)
			System.out.println(movie);
	}
}
