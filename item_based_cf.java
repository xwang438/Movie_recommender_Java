import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class computes item-to-item similarity between any two movies
 * Using item_based_collaborative filtering algorithm
 * The similarity is measured by Pearson correlation
 *
 * There are 6040 users and 3952 movies
 */
public class item_based_cf {
	/** 
	 * @param mvoieID
	 *            the ids of the movies that will be computed
	 * @param output
	 *            the name of the output file, "user_rated_movie.txt"
	 * @param userNumber
	 *            the number of users in the dataset
	 * @param ratings1
	 *            the ratings for the movie1
	 * @param ratings2
	 *            the ratings for the mvoie2
	 */
	private int movieID1, movieID2;
	private Integer[] ratings1;
	private Integer[] ratings2;
	public item_based_cf(int id1, int id2){
		movieID1 = id1;
		movieID2 = id2;
		HashMap<Integer, Integer[]> movie_rate_table = movie_user_ratings.movie_ratings();
		ratings1 = movie_rate_table.get(movieID1);
    	ratings2 = movie_rate_table.get(movieID2);
	}
	/**
	 * Find the users who have rated both of the two movies
	 */
    public ArrayList<Integer> commonUser(){
    	ArrayList<Integer> user_group = new ArrayList<Integer>();
    	for(int i = 0; i < ratings1.length; i++){
    		if(!ratings1[i].equals("0") && !ratings2[i].equals("0"))
    			user_group.add(i);
    	}
    	return user_group;
    }
    /**
	 * Find the average ratings for the two movies
	 */
    public double[] average_rating(){
    	int[] count = {0, 0};
    	double[] avg = {0, 0};
    	for(int i = 0; i < ratings1.length; i++){
    		if(!ratings1[i].equals("0")){
    			count[0]++;
    			avg[0] += ratings1[i];
    		}
    	}
    	for(int i = 0; i < ratings2.length; i++){
    		if(!ratings2[i].equals("0")){
    			count[1]++;
    			avg[1] += ratings2[i];
    		}
    	}
    	avg[0] = avg[0] / count[0];
    	avg[1] = avg[1] / count[1];
    	return avg;
    }
    /**
	 * Compute pearson correlation-based similarity
	 */
    public double pearson_sim(){
    	ArrayList<Integer> user_group = commonUser();
    	double[] avg_rate = average_rating();
    	double upper = 0, lower1 = 0, lower2 = 0;
    	for(int i = 0; i < user_group.size(); i++){
    		upper += (double)(ratings1[i]) - avg_rate[0] * (ratings2[i] - avg_rate[1]);
    		lower1 += Math.pow((ratings1[i] - avg_rate[0]), 2);
    		lower2 += Math.pow((ratings2[i] - avg_rate[1]), 2);
    	}
    	double sim = upper / (Math.sqrt(lower1) * Math.sqrt(lower2));
    	return sim;
    }
    public static void main(String[] args){
    	item_based_cf sim = new item_based_cf(4, 21);
    	System.out.println(sim.pearson_sim());
    }
}
