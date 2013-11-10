import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class segments the ratings dataset into training data and test data
 * Randomly take 70% of the ratings as training data and 30% as test data
 *
 * There are 6040 users and 3952 movies
 * Use 100 movies to do experiment
 */
public class training_test {
	/**
	 * Put the movies, users, ratings and timestamp in each line into an ArrayList containing:
	 * MovieID, userID, rating, Timestamp
	 */
	public static ArrayList<Integer[]> readInData(){
		String filename = "ratings.dat";
		BufferedReader br = null;
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		try{
			String reader = "";
			br = new BufferedReader(new FileReader(filename));
			while((reader = br.readLine()) != null){
				int k = 0, j = 0, i = 0;
				Integer[] rat = new Integer[4];
				while(k < reader.length() && i < 4){
					if(reader.charAt(k) == ':'){
						rat[i++] = Integer.parseInt(reader.substring(j,  k));
				        j = k + 2;
				        k = j - 1;
					}
					k++;
				}
				rat[i] = Integer.parseInt(reader.substring(j));
				list.add(rat);
			}
			br.close();
		}
		catch(IOException e){
            e.printStackTrace();
        }
		return list;
	}
	/**
	 * Get the training data and test data
	 */
	public static ArrayList<ArrayList<Integer[]>> train_test(){
		ArrayList<Integer[]> list = readInData();
		int size = list.size();
		int test = (int)(size * 0.3);
		int[] randselect = new int[size];
		for(int i = 0; i < randselect.length; i++)
			randselect[i] = i;
		ArrayList<Integer> testset = new ArrayList<Integer>();
		int count = size;
		while(testset.size() < test){
		    for(int i = 0; i < test; i++){
			    Random rand = new Random();
			    int value = rand.nextInt(count);
			    testset.add(randselect[value]);
			    randselect[value] = randselect[count - 1];
			    count--;
		    }
		}
		ArrayList<Integer[]> testlist = new ArrayList<Integer[]>();
		ArrayList<Integer[]> trainlist = new ArrayList<Integer[]>();
		for(int i = 0; i < list.size(); i++)
		    trainlist.add(list.get(i));
		for(int i = 0; i < testset.size(); i++){
			testlist.add(list.get(i));
			trainlist.remove(i);
		}
		ArrayList<ArrayList<Integer[]>> result = new ArrayList<ArrayList<Integer[]>>();
		result.add(trainlist);
		result.add(testlist);
		return result;
	}
	/**
	 * Create the new files containing the training data and test data separately
	 */
	public static void createFile(){
		ArrayList<ArrayList<Integer[]>> list = train_test();
		ArrayList<Integer[]> train = list.get(0);
		ArrayList<Integer[]> test = list.get(1);
		BufferedWriter bw = null;
		try{
            bw = new BufferedWriter(new FileWriter("training"));
            for(int i = 1; i < train.size(); i++){
            	for(int s : train.get(i)){
                     bw.write(String.valueOf(s));
         	         bw.write("\t");
            	}
            	bw.write("\n");
            }
            bw.close();
            bw = new BufferedWriter(new FileWriter("test"));
            for(int i = 1; i < test.size(); i++){
            	for(int s : test.get(i)){
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
		ArrayList<ArrayList<Integer[]>> set = train_test();
		
		createFile();
	}
}
