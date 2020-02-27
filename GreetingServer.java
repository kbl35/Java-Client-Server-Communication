// File Name GreetingServer.java
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class GreetingServer extends Thread {
   private ServerSocket serverSocket;

   public GreetingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " +
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());

            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress());

            int achoice = in.readInt();
            System.out.println(achoice);

            switch(achoice) {
            case 1:

            try {

            	ObjectInputStream inS = new ObjectInputStream(server.getInputStream());
                Student object1 = (Student) inS.readObject();
                System.out.println("Server recieved student: " + object1.getFName() + " " +  object1.getLName());

        		File file = new File("studentInfor.txt");

        		if(!file.exists()) {
        				file.createNewFile();
        		}
        		FileWriter fw = new FileWriter(file, true);
        		PrintWriter pw = new PrintWriter(fw);
        		pw.println(" ");
        		pw.append(Integer.toString(object1.getId()));
        		pw.append(",");
        		pw.append(object1.getFName());
        		pw.append(",");
        		pw.append(object1.getLName());
        		pw.append(",");
        		pw.append(Integer.toString(object1.getScore()));
        		pw.close();
        		System.out.println("saved to the file is completed.");
        		out.writeUTF("Server Added " + object1.getFName() + " to the list.");

            inS.close();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}

            //	server.close();
            	break;
            case 2:
            	int idFServer = in.readInt();
            	System.out.println("Server recieved ID: " + idFServer);
            	ArrayList<Student> studentList = new ArrayList<>();

                File file = new File("studentInfor.txt");
                try {
                	Scanner inputStream = new Scanner(file);
                	while (inputStream.hasNext()) {
                		String data = inputStream.next();
                		String[] values = data.split(",");
                		//System.out.println(values[0]);
                		Student studentObject = new Student();
                		studentObject.setId(Integer.parseInt(values[0]));
                		studentObject.setFName(values[1]);
                		studentObject.setLName(values[2]);
                		studentObject.setScore(Integer.parseInt(values[3]));
                		studentList.add(studentObject);

                	}
                	inputStream.close();
                } catch(FileNotFoundException e) {
                	e.getStackTrace();
                }

                System.out.println("This is a student information");

                boolean studentFound = false;

                for(Student listN:studentList)
                {
                	if(listN.getId() == idFServer)
                	{
                		ObjectOutputStream outO = new ObjectOutputStream(server.getOutputStream());
                   	System.out.println("Student send: " + listN.getFName());
                   	outO.writeObject(listN);
                   	outO.flush();
                    studentFound = true;
                  }

                }
                if(!studentFound)
                {
                      ObjectOutputStream outO1 = new ObjectOutputStream(server.getOutputStream());
                      System.out.println("invalid ID");
                      Student invalidStudent = new Student();
                      invalidStudent.setId(idFServer);
                      invalidStudent.setFName("ID not found");
                      invalidStudent.setLName("ID not found");
                      invalidStudent.setScore(00);
                      outO1.writeObject(invalidStudent);
                      outO1.flush();
                }
              //  server.close();
            	break;
            case 3:
            	ArrayList<Student> studentList1 = new ArrayList<>();

                File file1 = new File("studentInfor.txt");
                try {
                	Scanner inputStream = new Scanner(file1);
                	while (inputStream.hasNext()) {
                		String data = inputStream.next();
                		String[] values = data.split(",");
                		Student studentObject = new Student();
                		studentObject.setId(Integer.parseInt(values[0]));
                		studentObject.setFName(values[1]);
                		studentObject.setLName(values[2]);
                		studentObject.setScore(Integer.parseInt(values[3]));
                		studentList1.add(studentObject);

                	}
                	inputStream.close();
                } catch(FileNotFoundException e) {
                	e.getStackTrace();
                }


                System.out.println("This is a student information");
                out.writeInt(studentList1.size());
                System.out.println("This is the count#: " + studentList1.size());
                ObjectOutputStream outO = new ObjectOutputStream(server.getOutputStream());
                for(Student listN:studentList1)
                {
                   	  	System.out.println("Student send: " + listN.getFName());
                   	  	outO.writeObject(listN);
                   	  	outO.flush();
                }
                outO.close();
            //    server.close();
            	break;
            case 4:
            	int idFServer1 = in.readInt();
            	System.out.println("Server recieved ID: " + idFServer1);
            	ArrayList<Student> studentList2 = new ArrayList<>();

                File file3 = new File("studentInfor.txt");
                try {
                	Scanner inputStream = new Scanner(file3);
                	while (inputStream.hasNext()) {
                		String data = inputStream.next();
                		String[] values = data.split(",");
                		Student studentObject = new Student();
                		studentObject.setId(Integer.parseInt(values[0]));
                		studentObject.setFName(values[1]);
                		studentObject.setLName(values[2]);
                		studentObject.setScore(Integer.parseInt(values[3]));
                		studentList2.add(studentObject);

                	}
                	inputStream.close();
                } catch(FileNotFoundException e) {
                	e.getStackTrace();
                }
                file3.delete();

                ArrayList<Student> studentList3 = studentList2;

                System.out.println("this step will delete an Entry");

                Iterator<Student> itr = studentList3.iterator();
                boolean deleteAble = false;
                while (itr.hasNext()) {
                  Student loan = itr.next();
                  if (loan.getId() == idFServer1) {
                	  //System.out.println("Student ID "+ loan.getId() + " has been removed ");
                    itr.remove();
                    out.writeUTF("successful delete ID");
                    deleteAble = true;
                  }
                  //System.out.println("this happen after deletion: " + loan.getId());
                }
                if(!deleteAble){
                  out.writeUTF("ID not found");
                }


                File file4 = new File("studentInfor.txt");

                for(Student listN:studentList3)
                {

        		        if(!file4.exists()) {
        				          file4.createNewFile();
        		         }
        			       FileWriter fw = new FileWriter(file4, true);
        			       PrintWriter pw = new PrintWriter(fw);
        			       pw.println(" ");
        			       pw.append(Integer.toString(listN.getId()));
        			       pw.append(",");
                     pw.append(listN.getFName());
        		         pw.append(",");
        	           pw.append(listN.getLName());
        			       pw.append(",");
        			       pw.append(Integer.toString(listN.getScore()));
        			       pw.close();
        			       //System.out.println("Done");
                }
              //  server.close();
            	break;

            case 5:
            	int serverScore = in.readInt();
            	ArrayList<Student> studentList4 = new ArrayList<>();

                File file5 = new File("studentInfor.txt");
                try {
                	Scanner inputStream = new Scanner(file5);
                	while (inputStream.hasNext()) {
                		String data = inputStream.next();
                		String[] values = data.split(",");
                		//System.out.println(values[0]);
                		Student studentObject = new Student();
                		studentObject.setId(Integer.parseInt(values[0]));
                		studentObject.setFName(values[1]);
                		studentObject.setLName(values[2]);
                		studentObject.setScore(Integer.parseInt(values[3]));
                		studentList4.add(studentObject);

                	}
                	inputStream.close();
                } catch(FileNotFoundException e) {
                	e.getStackTrace();
                }

                System.out.println("These students are above Score:");

                ArrayList<Student> studentList5 = new ArrayList<>();

                for(Student listN:studentList4)
                {

                	if(listN.getScore() > serverScore)
                	{
                   	  	System.out.println("Student send: " + listN.getFName());
                   	  	studentList5.add(listN);
                	}
                }

                out.writeInt(studentList5.size());
                ObjectOutputStream outO2 = new ObjectOutputStream(server.getOutputStream());
                for(Student listN:studentList5)
                {
                	outO2.writeObject(listN);
               	  outO2.flush();
                }

              //  server.close();


            	break;
            default:
            	System.out.print("Invalid option.");
            }

         } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         } catch (IOException e) {
            e.printStackTrace();
            break;
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }

   public static void main(String [] args) {
      int port = 6666;
      try {
         Thread t = new GreetingServer(port);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
