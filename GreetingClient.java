// File Name GreetingClient.java


import java.net.*;
import java.io.*;
import java.util.Scanner;



public class GreetingClient {

   public static void main(String [] args) {
		String serverName = "localhost";
	   //String serverName = "zeus.cs.txstate.edu";
	   int port = 6666;

      try {
         char choice;
         do{

         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);

         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);

         out.writeUTF("Hello from " + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);

         System.out.println("Server says " + in.readUTF());

         System.out.println("Please choose your option:");
         System.out.println("a - Add new student's information.");
         System.out.println("b - Retrieve student's information from ID#.");
         System.out.println("c - Display all students informations from Server.");
         System.out.println("d - Delete the student entry with that ID.");
         System.out.println("e - Retrieve all students whose score above sent score.");
         System.out.println("q - To exit program.");

         Scanner choose = new Scanner(System.in);
         System.out.print("Your choice: ");
         choice = choose.next().charAt(0);
         while(choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd' &&
              choice != 'e' && choice != 'q')
              {
                System.out.println("Invalid choice, Please select your choice again.");
                System.out.print("Your choice: ");
                choice = choose.next().charAt(0);
              }


         switch (choice) {
         case 'a':
        	 {
        	out.writeInt(1);
        	Student st = new Student();

          Scanner inFor = new Scanner(System.in);
            int studentID;
            String input;
            System.out.print("Enter your ID: ");
            input = inFor.nextLine();
            while(input.length() != 6){
              System.out.print("Plese Enter your ID with 6 digits (ex:123456): ");
              input = inFor.nextLine();
            }
            studentID = Integer.valueOf(input);
            st.setId(studentID);

       	  	System.out.print("Enter your First Name: ");
       	  	String studentFName = inFor.next();
       	  	st.setFName(studentFName);

       	  	System.out.print("Enter your Last Name: ");
       	  	String studentLName = inFor.next();
       	  	st.setLName(studentLName);

       	  	System.out.print("Enter your score: ");
       	  	int studentScore = inFor.nextInt();
       	  	st.setScore(studentScore);;

       	  	//inFor.close();
       	  	ObjectOutputStream outO = new ObjectOutputStream(outToServer);
            System.out.println("=======================================================");
       	  	System.out.println("Student send: " + st.getFName());
       	  	outO.writeObject(st);
       	  	outO.flush();

            System.out.println("=======================================================");
       	  	System.out.println(in.readUTF());
            System.out.println("=======================================================");
       	  	break;
        	 }
         case 'b':
        	 out.writeInt(2);

        	 Scanner idIn = new Scanner(System.in);

           int studentId;
           String input;
           System.out.print("Please enter a student iD#: ");
           input = idIn.nextLine();
           while(input.length() != 6){
             System.out.print("Plese Enter an ID with 6 digits (ex:123456): ");
             input = idIn.nextLine();
           }
           studentId = Integer.valueOf(input);
        	 out.writeInt(studentId);

        	 ObjectInputStream inSt = new ObjectInputStream(inFromServer);
           Student objectReceive = (Student) inSt.readObject();

           System.out.println("=======================================================");
           System.out.println("Student ID: " + objectReceive.getId());
     		   System.out.println("Student First Name: " + objectReceive.getFName());
     		   System.out.println("Student Last name: " + objectReceive.getLName());
     		   System.out.println("Student's Score: " + objectReceive.getScore());
     		   System.out.println("=======================================================");

        	 break;

         case 'c':
        	 out.writeInt(3);
        	 System.out.println("This is a list of all students.");
        	 int count = in.readInt();
        	 System.out.println("This is the count#: " + count);
        	 ObjectInputStream inSt1 = new ObjectInputStream(inFromServer);
        	 System.out.println("=======================================================");
        	 for(int i = 0; i < count;i++ )
        	 {

            Student objectReceive1 = (Student) inSt1.readObject();

            System.out.println("Student ID: " + objectReceive1.getId());
         		System.out.println("Student First Name: " + objectReceive1.getFName());
         		System.out.println("Student Last name: " + objectReceive1.getLName());
         		System.out.println("Student's Score: " + objectReceive1.getScore());
         		System.out.println("=======================================================");

        	 }
        	 inSt1.close();
        	 break;

         case 'd':
        	 out.writeInt(4);
           Scanner idIn4 = new Scanner(System.in);

           int studentId4;
           String input4;
           System.out.print("Delete Entry accosiate with ID#: ");
           input4 = idIn4.nextLine();
           while(input4.length() != 6){
             System.out.print("Plese Enter an ID with 6 digits (ex:123456): ");
             input4 = idIn4.nextLine();
           }
           studentId4 = Integer.valueOf(input4);
        	 out.writeInt(studentId4);
           System.out.println("=======================================================");
           System.out.println("Server respond: " + in.readUTF());
           System.out.println("=======================================================");
        	 break;
         case 'e':
        	 out.writeInt(5);
        	 System.out.print("Enter a score: ");
        	 Scanner scoreT = new Scanner(System.in);
        	 int sc = scoreT.nextInt();
        	 out.writeInt(sc);
        	 int count1 = in.readInt();
        	 ObjectInputStream inSt2 = new ObjectInputStream(inFromServer);

        	 for(int i = 0; i < count1; i++)
        	 {
        		 Student objectReceive2 = (Student) inSt2.readObject();

             System.out.println("=======================================================");
             System.out.println("Student ID: " + objectReceive2.getId());
         		 System.out.println("Student First Name: " + objectReceive2.getFName());
         		 System.out.println("Student Last name: " + objectReceive2.getLName());
         		 System.out.println("Student's Score: " + objectReceive2.getScore());
         		 System.out.println("=======================================================");
        	 }
        	 break;

        default:
          out.writeInt(6);
        	System.out.println("Good bye!");
          throw new NumberFormatException();
         }

       } while(choice != 'q');


      //   client.close();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
      } catch(NumberFormatException e){
      System.out.println("You will be exited the program!");
      }
   }
}
