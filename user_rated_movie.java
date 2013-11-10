import java.util.HashMap;
import java.io.*;
import java.util.ArrayList;
/**
 * This class get the movies rated by each user and put the result into a hashmap.
 * 
 * There are 6040 users and 3952 movies, so there are 6040 lines in "user_rated_movie.txt"
 */

public class user_rated_movie {
	/** 
	 * @param filename
	 *            the name of the input dataset, "ratings.dat"
	 * @param output
	 *            the name of the output file, "user_rated_movie.txt"
	 * @param userNumber
	 *            the number of users in the dataset
	 */
	private final int userNumber;
	private final int movieNumber;
	private String output;
	private int user;
	private ArrayList<Integer[]> train = null;
	public user_rated_movie(int u){
		userNumber = 6040;
		movieNumber = 3952;
		output = "user_rated_movie.txt";
		user = u;
		train = training_test.train_test().get(0);
	}

	/**
	 * Create a HashMap, the user number is the key, their corresponding rated movies are the values
	 */
	public HashMap<Integer, ArrayList<Integer>> createMap(){
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < train.size(); i++){
			if(!map.containsKey(train.get(i)[0])){
				map.put(train.get(i)[0], new ArrayList<Integer>());
			}
			ArrayList<Integer> sublist = map.get(train.get(i)[0]);
			sublist.add(train.get(i)[1]);
		}
		return map;
	}
	/**
	 * Create a HashMap, the user number is the key, their corresponding ratings are the values
	 * If a user did not rate a movie, put 0 instead.
	 */
	public HashMap<Integer, Integer[]> createRating(){
		HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
		for(int i = 0; i < train.size(); i++){
			if(!map.containsKey(train.get(i)[0])){
				Integer[] ratings = new Integer[movieNumber];
				for(int j = 0; j < ratings.length; j++)
					ratings[j] = 0;
				map.put(train.get(i)[0], ratings);
			}
			Integer[] sublist = map.get(train.get(i)[0]);
			sublist[train.get(i)[1] - 1] = train.get(i)[2];
		}
		return map;
	}
	/**
	 * Get the rated movies by a certain user
	 */
	public ArrayList<Integer> rated_movies(){
		HashMap<Integer, ArrayList<Integer>> map = createMap();
		return map.get(user);
	}
	/**
	 * Get the ratings by a certain user
	 */
	public Integer[] user_ratings(){
		HashMap<Integer, Integer[]> map = createRating();
		return map.get(user);
	}
	/**
	 * Create a new file containing the records in HashMap
	 */
	/*
	public void createFile(){
		HashMap<Integer, ArrayList<Integer>> map = createMap();
		BufferedWriter bw = null;
		try{
            bw = new BufferedWriter(new FileWriter(output));
            for(int i = 1; i <= userNumber; i++){
            	for(int s : map.get(i)){
                     bw.write(String.valueOf(s));
         	         bw.write("\t");
            	}
            	bw.write("\n");
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
	}
	public static void main(String[] args){
		user_rated_movie urm = new user_rated_movie();
		urm.createFile();
	}
	*/
}
