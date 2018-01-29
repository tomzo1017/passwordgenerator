import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class login {
	private static Scanner userinput = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
	String username = getUser();
	String password = getPW();
	System.out.println("Logging in........");
	boolean passvalidation = false;
	boolean uservalidation = validateUser(username);
	if(uservalidation){
		passvalidation = validatePW(username,password);
	}
	System.out.println(passvalidation);
	
	}
	// ASk for username
	public static String getUser(){
		System.out.print("Username:");
		String user = userinput.nextLine();
		return user;
	}
	//ASk for password
	public static String getPW(){
		System.out.print("Password:");
		String pw = userinput.nextLine();
		return pw;
	}
	// See if user exists
	public static boolean validateUser(String username) 
			throws IOException{
		File file = new File("user/userlist.txt");
		Path path = null;
		boolean result = false;
		//See if user list exists and if not, create test user and return false
		if(file.exists() && !file.isDirectory()){
			path = FileSystems.getDefault().getPath("user", "userlist.txt");
		}else{
			//Initialise
			File dir = new File("user");
			dir.mkdir();
			PrintWriter writer = new PrintWriter("user/userlist.txt","UTF-8");
			writer.println("test");
			writer.close();
			PrintWriter writer2 = new PrintWriter("user/test.txt","UTF-8");
			writer2.println("test");
			writer2.close();
			path = FileSystems.getDefault().getPath("user", "userlist.txt");
			return false;
		}
		
		Charset cs = Charset.forName("UTF-8");
		java.util.List<String> users;
		users = Files.readAllLines(path, cs);
		
		for(String string:users){
			if(string.trim().contains(username)){
				result = true;
			}
		}
		return result;
	}
	//Check if the password for the user given is correct
	public static boolean validatePW(String username,String password)
			throws IOException{
		String thisuser = (username+".txt");
		boolean result = false;		
		BufferedReader reader = new BufferedReader(new FileReader("user/"+thisuser));
		String pass = reader.readLine();
		reader.close();
		if(pass.equals(password)){
			result = true;
		}
		return result;
	}

	public static void checkExpiration(String username) 
			throws IOException, FileNotFoundException {
		String[] UserList = readLines("users/"+username+"passlist");
		}
					
	public static String[] readLines(String tiedostonimi)
		throws IOException, FileNotFoundException{

		FileReader fileReader = new FileReader(tiedostonimi);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
				
		while ((line = bufferedReader.readLine()) != null){
			lines.add(line);					
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
		}
		
	public static void passExpirator(String[] passlist){
		char delimiter = '£';
		char helper;
		String pw,date,helper2;
		boolean toggle = false;
		for(int i = 0;i<passlist.length;i++){
			helper2 = passlist[i];
			for(int j=0;j<helper2.length();i++){
				if(!toggle && !(helper2.charAt(j)==delimiter)){
					pw+=helper2.charAt(j);
				}
				if(){
					
				}
			}
				
		LocalDate currentDate = LocalDate.now();
		LocalDate passwordDate = LocalDate.of(2016, 9, 14); //placeholder - tommin date
		LocalDate expiryDate = passwordDate.plusDays(60);
		
		System.out.println("Mallina: vuosi/kuukausi/päivä");
		System.out.println("Päivämäärä: " + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(currentDate));
		System.out.println("Salasanan luontipäivä: " + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(passwordDate));
		System.out.println("Salasanan vanhentumispäivä: " + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(expiryDate));
		
		if (currentDate.isAfter(expiryDate)){
			System.out.println("Salasanasi on yli 60 päivää vanha - on suositeltavaa, että vaihdat salasanasi.");
			
		} else {
			System.out.println("Salasanasi on ok.");
		}
		}
	}
}

