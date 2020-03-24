package Infrastructure;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Repository<T> {
    private ArrayList<T> myList=new ArrayList<>();

    public void setMyList(ArrayList<T> myList) {
        this.myList = myList;
    }

    public Repository(){}

    /*
        Finds an item in the repository
        input: object - T
        output: the position of object( if it exists)
                -1 (if it doesn't exist)
     */
    public int findItem(T object)
    {
        for(int i=0;i<this.myList.size();i++)
            if(this.myList.get(i).equals(object) )
                return i;
        return -1;
    }
    /*
        Adds an item in the repository
        input: object - T
        output: -
        exceptions: Runtime Exception if the object already exists
     */
    public void addItem(T object){
        if(findItem(object)==-1)
            this.myList.add(object);
        else
            throw new RuntimeException("Error! The item already exists! ");

    }

    /*
        Removes an item from the repository
        input: object - T
        output: -
        exceptions: Runtime Exception if the object does not exist
     */
    public void removeItem(T object){
        if(findItem(object)!=-1)
            throw new RuntimeException("Error! The item doesn't exist, so it could not be deleted! ");
        else
            this.myList.remove(object);
    }

    /*
        Updates the item from a given index in the repository
        input: object - T, index - int
        output: -
        exceptions: Runtime Exception if the object does not exist
     */
    public void updateItemAtIndex(T object,int index){
        int idx=findItem(object);
        if(index==-1)
            throw new RuntimeException("Error! The item doesn't exist, so it could not pe updated! ");
        else
            this.myList.set(index,object);
    }

    public ArrayList<T> getAll(){
        return this.myList;
    }


    public void writeToFile(String filename) throws IOException {
        BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
        for(T object:this.myList){
            bw.write(object.toString());
            bw.newLine();
        }
        bw.close();
    }
}

