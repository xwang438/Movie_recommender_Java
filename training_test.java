import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class segments the ratings dataset into training data and test data
 * Randomly take 70% of the ratings as training data and 30% as test data
 *
 * 100000 ratings by 943 users on 1682 items.Each user has rated at least 20 movies. 
 * Users and items numbered consecutively from 1.
 * 
 * u.data: user id | item id | rating | timestamp. 
 */
public class training_test {
	/**
	 * Get the training data and test data
	 */
	public static ArrayList<ArrayList<String[]>> train_test(){
		ArrayList<String[]> list = read_file.readInData("u.data", '\t');
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
		ArrayList<String[]> testlist = new ArrayList<String[]>();
		ArrayList<String[]> trainlist = new ArrayList<String[]>();
		for(int i = 0; i < list.size(); i++)
		    trainlist.add(list.get(i));
		for(int i = 0; i < testset.size(); i++){
			testlist.add(list.get(i));
			trainlist.remove(i);
		}
		ArrayList<ArrayList<String[]>> result = new ArrayList<ArrayList<String[]>>();
		result.add(trainlist);
		result.add(testlist);
		return result;
	}
	/**
	 * Create the new files containing the training data and test data separately
	 */
	public static void createFile(){
		ArrayList<ArrayList<String[]>> list = train_test();
		ArrayList<String[]> train = list.get(0);
		ArrayList<String[]> test = list.get(1);
		BufferedWriter bw = null;
		try{
            bw = new BufferedWriter(new FileWriter("training"));
            for(int i = 1; i < train.size(); i++){
            	for(String s : train.get(i)){
                     bw.write(s);
         	         bw.write("\t");
            	}
            	bw.write("\n");
            }
            bw.close();
            bw = new BufferedWriter(new FileWriter("test"));
            for(int i = 1; i < test.size(); i++){
            	for(String s : test.get(i)){
                     bw.write(s);
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
		createFile();
	}
}

