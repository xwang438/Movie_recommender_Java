import java.util.HashMap;
import java.util.ArrayList;
/**
 * This class get the movies rated by each user and put the result into a HashMap.
 * And put the ratings for each movie into a HashMap.
 * 
 * 100000 ratings by 943 users on 1682 items
 * So there are 943 lines in "user_rated_movie.txt"
 */

public class user_rated_movie {
	/** 
	 * @param filename
	 *            the name of the input dataset, "ratings.dat"
	 * @param movieNumber
	 *            the number of the movies in the training set
	 * @param usernum
	 *            the number of users in the training set
	 */
	private static final int movieNumber = 1682;
	private static final int usernum = 943;
	private static ArrayList<String[]> train = read_file.readInData("training", '\t');

	/**
	 * Create a HashMap, the user number is the key, their corresponding rated movies are the values
	 */
	public static HashMap<Integer, ArrayList<Integer>> createMap(){
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < train.size(); i++){
			if(!map.containsKey(Integer.parseInt(train.get(i)[0]))){
				map.put(Integer.parseInt(train.get(i)[0]), new ArrayList<Integer>());
			}
			ArrayList<Integer> sublist = map.get(train.get(i)[0]);
			sublist.add(Integer.parseInt(train.get(i)[1]));
		}
		return map;
	}
	/**
	 * Create a HashMap, the user number is the key, their corresponding ratings are the values
	 * If a user did not rate a movie, put 0 instead.
	 */
	public static HashMap<Integer, Integer[]> createRating(){
		HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
		for(int i = 0; i < train.size(); i++){
			if(!map.containsKey(Integer.parseInt(train.get(i)[0]))){
				Integer[] ratings = new Integer[movieNumber];
				for(int j = 0; j < ratings.length; j++)
					ratings[j] = 0;
				map.put(Integer.parseInt(train.get(i)[0]), ratings);
			}
			Integer[] sublist = map.get(Integer.parseInt(train.get(i)[0]));
			sublist[Integer.parseInt(train.get(i)[1]) - 1] = Integer.parseInt(train.get(i)[2]);
		}
		return map;
	}

	/**
	 * Get the ratings by a certain user
	 */
	public static Integer[] user_ratings(int user){
		HashMap<Integer, Integer[]> map = createRating();
		return map.get(user);
	}
	
	/**
	 * Put the movies and ratings into a HashMap containing:
	 * MovieID(key) : ratings by users from 1 to 1682
	 * If a user did not rate a movie, put 0 automatically
	 */
	public static HashMap<Integer, Integer[]> movie_ratings(){
		HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
		for(int i = 0; i < train.size(); i++){
			if(!map.containsKey(Integer.parseInt(train.get(i)[1]))){
				Integer[] s = new Integer[usernum];
				for(int n = 0; n < s.length; n++)
					s[n] = 0;
				map.put(Integer.parseInt(train.get(i)[1]), s);
			}
			Integer[] ratings = map.get(Integer.parseInt(train.get(i)[1]));
			ratings[Integer.parseInt(train.get(i)[0]) - 1] = Integer.parseInt(train.get(i)[2]);
		}
		return map;
	}
}

