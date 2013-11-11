import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class reads a file and put each line in the file into an ArrayList
 */

public class read_file {
	
	public static ArrayList<String[]> readInData(String filename, char c){
		BufferedReader br = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		try{
			String reader = "";
			br = new BufferedReader(new FileReader(filename));
			while((reader = br.readLine()) != null){
				int k = 0, j = 0, i = 0;
				String[] rat = new String[4];
				while(k < reader.length() && i < 3){
					if(reader.charAt(k) == c){
						rat[i++] = reader.substring(j,  k);
				        j = k + 1;
				        k = j - 1;
					}
					k++;
				}
				rat[i] = reader.substring(j);
				list.add(rat);
			}
			br.close();
		}
		catch(IOException e){
            e.printStackTrace();
        }
		return list;
	}
}

