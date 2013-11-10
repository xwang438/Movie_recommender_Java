import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class creates a new dataset containing the movies and the ratings by each user
 * based on the dataset "ratings.dat"
 * All ratings are contained in the file "ratings.dat" and are in the following format:
 * UserID::MovieID::Rating::Timestamp
 * - UserIDs range between 1 and 6040 
 * - MovieIDs range between 1 and 3952
 * - Ratings are made on a 5-star scale (whole-star ratings only)
 * - Timestamp is represented in seconds since the epoch as returned by time(2)
 * - Each user has at least 20 ratings
 * There are 3952 movies
 */
public class movie_user_ratings {
	private static final int usernum = 6040;
	/**
	 * Put the movies and ratings into a hashmap containing:
	 * MovieID(key) : ratings by users from 1 to 6040
	 * If a user did not rate a movie, put 0 automatically
	 */
	public static HashMap<Integer, Integer[]> movie_ratings(){
		ArrayList<Integer[]> trainset = training_test.train_test().get(0);
		HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
		for(int i = 0; i < trainset.size(); i++){
			if(!map.containsKey(trainset.get(i)[0])){
				Integer[] s = new Integer[usernum];
				for(int n = 0; n < s.length; n++)
					s[n] = 0;
				map.put(trainset.get(i)[0], s);
			}
			Integer[] ratings = map.get(trainset.get(i)[0]);
			ratings[trainset.get(i)[1] - 1] = trainset.get(i)[2];
		}
		return map;
	}
	public static void main(String[] args){
		HashMap<Integer, Integer[]> map = movie_ratings();
	}
}
