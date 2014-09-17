/*
 * Este código é de bem público http://en.wikipedia.org/wiki/Serialization#Java
 * e foi alterado conforme a necessidade do projeto JogadorTux.
 */

package linhaQuatro.jogadores;

import java.io.*;
import java.util.*;

public class Serialize {
   
   /**
    * @param obj Object - The object that is saved.
    * @param filename String - The filename of the file it is saved to.
    */
   public static void save(Object obj, String filename) throws IOException {
       ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream(filename));
       objstream.writeObject(obj);
       objstream.close();
   }
   
   /**
    * @param filename String - The filename for the file to be loaded
    */
   public static Object load(String filename) throws Exception {
       ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(filename));
       Object obj = objstream.readObject();
       objstream.close();
       return obj;
   }
   
   /**
    * Fetch the entire contents of a text file, and return it in a String.
    * This style of implementation does not throw Exceptions to the caller.
    *
    * @param aFile is a file which already exists and can be read.
    */
    public String getContents(File aFile) {
      //...checks on aFile are elided
      StringBuffer contents = new StringBuffer();

      //declared here only to make visible to finally clause
      BufferedReader input = null;
      try {
        //use buffering, reading one line at a time
        //FileReader always assumes default encoding is OK!
        input = new BufferedReader( new FileReader(aFile) );
        String line = null; //not declared within while loop
        /*
        * readLine is a bit quirky :
        * it returns the content of a line MINUS the newline.
        * it returns null only for the END of the stream.
        * it returns an empty String if two newlines appear in a row.
        */
        while (( line = input.readLine()) != null){
          contents.append(line);
          contents.append(System.getProperty("line.separator"));
        }
      }
      catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      catch (IOException ex){
        ex.printStackTrace();
      }
      finally {
        try {
          if (input!= null) {
            //flush and close both "input" and its underlying FileReader
            input.close();
          }
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      return contents.toString();
    }
   
    public void createSerialized(){
    	File database = new File("connect-4.data");
 	   	String content = getContents(database);
 	  
       try {
           save(content, "Connect4-database.ser");
           System.out.println("Saved: "+content);
       } catch(Exception e) {
           System.out.print("Error saving file: ");
           e.printStackTrace();
       }
    }
    
    public String loadSerialized(){
    	String content = "";
 	   	try {
			content = (String)load("Connect4-database.ser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
    }
}