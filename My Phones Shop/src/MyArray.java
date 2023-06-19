import java.io.*;

public class MyArray {
    private int size;
    private Phone arr[];

    public MyArray(int size){
        this.size = size;
        arr = new Phone[this.size];
    }

    public int getSize(){
        return this.size;
    }

    public int getNumPhones(){
        int counter =0;
        for (int i=0; i<arr.length;i++) if(arr[i]!=null) counter++;
        return counter;
    }

    private int getFreeSpaces(){
        int counter=0;
        for (int i=0; i<arr.length;i++) if(arr[i]!=null) counter++;

        return counter;
    }

    private int getFirstFreeSpace(){
        for (int i=0; i<arr.length;i++) if(arr[i]==null) return i;

        return -1;
    }

    public Phone getPhone(int i){
        if(i>=0 && i<arr.length) return arr[i];
        return null;
    }

    public void add(Phone p){
        if(getFreeSpaces()==0){
            Phone[] v = new Phone[arr.length*2];

            for (int i=0;i<arr.length;i++){
                v[i] = arr[i];
            }
            arr = v;

        }
        arr[getFirstFreeSpace()] = p;

    }

    public boolean remove(Phone p){
        int index = -1;

        for (int i=0; i<arr.length;i++){
            if(arr[i]!=null && arr[i].getName().equals(p.getName()) && arr[i].getBrand().equals(p.getBrand()) && arr[i].getPrice()==p.getPrice()){
                arr[i] = null;
                index=i;
                break;
            }
        }

        if(index==-1) return false;

        for (int i = index; i<arr.length-1;i++){
             if(arr[i]==null) arr[i] = arr[i+1];
        }
        return true;

    }

    public boolean importPhones(File file){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String data;
            while ((data = reader.readLine()) != null){
                String[] datas = data.split(",");
                Phone p = new Phone(datas[0],datas[1],Double.parseDouble(datas[2]));
                this.add(p);
            }
            reader.close();

        } catch (IOException e) {
            return false;
        }


        return true;
    }

    public void savePhones(){
        File file = new File("telefoni.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Phone p: arr){
                writer.write(p.getBrand()+" - "+p.getName()+" - "+p.getPrice()+"\n");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String toString(){
        String result="";

        for (Phone p : arr){
            if(p!=null) result += p.getBrand()+" - "+p.getName()+" - "+p.getPrice()+"\n";
        }
        return result;
    }



}
