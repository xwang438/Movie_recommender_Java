import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class find the movies that are in the same genre 
 * based on the dataset "movies.dat"
 * Movie information is in the file "movies.dat" and is in the following format:
 * MovieID::Title::Genres
 * - Titles are identical to titles provided by the IMDB (including year of release)
 * - Genres are pipe-separated and are selected from the following genres:
 * Action, Adventure, Animation, Children's, Comedy, Crime, Documentary, Drama, Fantasy, 
 * Film-Noir, Horror, Musical, Mystery, Romance, Sci-Fi, Thriller, War, Western
 * There are 3952 movies
 */

public class movie_genre {
	/**
	 * Put the movies into an ArrayList in the format:
	 * movieId   movieName   Genre
	 */
	public static ArrayList<ArrayList<String>> readInData(){
		String filename = "movies.dat";
		BufferedReader br = null;
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		try{
			String reader = "";
			br = new BufferedReader(new FileReader(filename));
			while((reader = br.readLine()) != null){
				int k = 0, j = 0;
				ArrayList<String> sublist = new ArrayList<String>();
				while(k < reader.length()){
					if(reader.charAt(k) == ':'){
						sublist.add(reader.substring(j, k));
				        j = k + 2;
				        k = j - 1;
					}
					k++;
				}
				int jj = j;
				while(j < reader.length()){
					if(reader.charAt(j) == '|'){
						sublist.add(reader.substring(jj, j));
				        jj = j + 1;
				        j = jj - 1;
					}
					j++;
				}
				sublist.add(reader.substring(jj));
				list.add(sublist);
			}
			br.close();
		}
		catch(IOException e){
            e.printStackTrace();
        }
		return list;
	}
	
	/**
	 * Find the movies that are in the same genre with a given movie
	 */
	public static ArrayList<Integer> sameGenreMovies(int movieID){
		ArrayList<ArrayList<String>> list = readInData();
		ArrayList<Integer> movies = new ArrayList<Integer>();
		ArrayList<String> list1 = list.get(movieID - 1);
        for(int i = 0; i < list.size(); i++){
        	if(i != movieID - 1){
        		ArrayList<String> list2 = list.get(i);
        		for(int j = 2; j < list1.size(); j++){
        			for(int k = 2; k < list2.size(); k++)
        				if(list1.get(j).equals(list2.get(k))){
        					movies.add(i + 1);
        					break;
        				}
        		}
        	}
		}
        return movies;
	}
	public static void main(String[] args){
		ArrayList<Integer> movies = sameGenreMovies(1);
	}
}
