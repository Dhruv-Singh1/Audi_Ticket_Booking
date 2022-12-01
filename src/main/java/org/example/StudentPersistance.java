package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StudentPersistance implements Persistance{
    ArrayList<Student> students;

    public StudentPersistance(ArrayList<Student> studentArrayList) {
        this.students = studentArrayList;
    }

    public  boolean saveToFile(){
        String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/student.ser";
        try {
            ObjectOutputStream osStud = new ObjectOutputStream(new FileOutputStream(filename));
            osStud.writeObject(students);
            //  osStud.flush();
            osStud.close();
            Audi.saveEvents();
            return true;
            //   saveToFile();
        }
        catch(FileNotFoundException e){
            // System.out.println("");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    return false;
    }
}

// Control how we read in Student(s).
//    @Serial
//    private void readObject(ObjectInputStream ois)
//            throws IOException, ClassNotFoundException {
//
//        ois.defaultReadObject();
//        // how many Students to read.
//        int size = ois.readInt();
//        for (int i = 0; i < size; i++) {
//            Student s = (Student) ois.readObject();
//            students.add(s);
//        }// System.out.println("\nsession deserialized");
//    }


//    @Serial
//    private void writeObject(ObjectOutputStream oos)
//            throws IOException {
//
//        oos.defaultWriteObject();
//        // How many students we're tracking.
//        oos.writeInt(students.size());
//
//       for (Student student : students) {
//            oos.writeObject(student);
//        }
//        System.out.println("session serialized");
//    }
//