import java.util.*;
import java.io.*;
//MENU
class MainMenu{
    public void menu(){
        System.out.println("\t*****************************************************");
        System.out.println("\tFOOD ORDERING SYSTEM DEVELOPED BY SRIHARSHA MANOHARAN");
        System.out.println("\t*****************************************************");
        System.out.println("PRESS 1: TO TAKE AN ORDER                       ");
        System.out.println("PRESS 2: TO SEE THE GIVEN ORDER                 ");
        System.out.println("PRESS 3: TO REMOVE THE ENTIRE ORDER             ");
        System.out.println("PRESS 4: TO UPDATE THE GIVEN ORDER              ");
        System.out.println("PRESS 5: TO EXIT FROM THE ORDERING SYSTEM       ");
    }
    //FOOD MENU
    public void list(){
        System.out.println();
        System.out.println();
        System.out.println("ORDER OF ITEMS AVAILABLE IN THE HOTEL");
        System.out.println("ITEM  1: DOSA");
        System.out.println("ITEM  2: IDLY");
        System.out.println("ITEM  3: POORI");
        System.out.println("ITEM  4: PONGAL");
        System.out.println("ITEM  5: SAMBAR RICE");
        System.out.println("ITEM  6: TEA");
        System.out.println("ITEM  7: COFFEE");
        System.out.println("ITEM  8: BOOST");
        System.out.println("ITEM  9: HORLICKS");
        System.out.println("ITEM 10: LEMON SODA");
        System.out.println();
    }
}
//WRITING THE FILE
class Ordertaking{
        public void createFile(){
            Scanner sc= new Scanner(System.in);
            Details obj2=new Details();
            MainMenu objl=new MainMenu();
            obj2.getInfo();
            try{
                File f1= new File(obj2.tablenumber+".txt");
                if(f1.createNewFile()){
                    FileWriter mywriter = new FileWriter(obj2.tablenumber+".txt");
                    objl.list();
                    System.out.println("\nENTER YOUR ORDER..\n");
                    System.out.println("IF YOUR ORDER IS OVER KIDNLY ENTER \"exit\"...\n");
                    while(true){
                        String data =sc.nextLine();
                        if(data.equalsIgnoreCase("exit")){
                            System.out.println("ORDER COMPLETED...");
                            break;
                        }
                        mywriter.write(data+System.lineSeparator());
                    }
                    mywriter.close();
                }
                else{
                    System.out.println("TABLLE IS ALREADY EXISTS...");
                    System.out.println("PRESS ENTER TO CONTINUE...");
                    sc.nextLine();
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
           }
}
//ORDER TAKING DETAILS
class Details{
        String name;
        String tablenumber;
        public void getInfo(){
            Scanner sc= new Scanner(System.in);
            System.out.print("ENTER YOUR NAME-");
            name=sc.nextLine();
            System.out.print("ENTER THE TABLE NUMBER-");
            tablenumber=sc.nextLine();

        }
}
//DISPLAYING THE TAKEN ORDER
class Display_order{
        public void  vieworder(String  s) throws Exception {
            File file= new File(s+".txt");
            Scanner sc = new Scanner(file);
            if(file.length()==0){
                    System.out.println("ORDER IS NOT TAKEN IN THE TABLE..");
            }
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            
        }
}
//REMOVE THE GIVEN ORDER
class Remove{
    public void remove(String table){
        File file = new File(table+".txt");
        if(file.exists()){
            file.delete();
            System.out.println("SUCCESSFULLY REMOVED THE ORDER");
            return;
        }
        else{
            System.out.println("GIVEN THE INVALID TABLE NUMBER!!");
        }
    }
}
//CODE EXIT
class CodeExit{
    public void out(){
        System.out.println("\t********************************");
        System.out.println("\t********************************");
        System.out.println("\t********************************");
        System.out.println("\t--THANK YOU FOR USING MY SOFTWARE--");
        System.out.println("\t--DEVELOPED BY SRIHARSHA MANOHARAN--");
        System.out.println("\t********************************");
        System.out.println("\t********************************");
        System.out.println("\t********************************");
        
        System.exit(0);
    }
}
//REWRITING THE FILE
class Updateitem{
    public void update(String s){
        File  f2=new File(s+".txt");
        if(!f2.exists()){
            System.out.println("INVALID TABLE NUMBER!!");
        }
        try(FileWriter writer= new FileWriter(f2)){
            Scanner sc = new Scanner(System.in);
            System.out.println("ENTER THE ITEMS TO ADD IF IT IS OVER TYPE \"exit\"");
            while (true) {
                String newitem=sc.nextLine();
                if(newitem.equalsIgnoreCase("exit")){
                    System.out.println("ITEMS ARE SUCCESSFULLY TAKEN");
                    break;
                }
                writer.write(newitem+System.lineSeparator());
            }
        }
        catch(Exception e){
            System.out.println("ERROR");
        }
    }
}
//REMOVE THE ITEMS IN THE LIST 
class Removeitem{
        public void Removeitem_(String tab,String remove){
                File fl = new File(tab+".txt");
                if(!fl.exists()){
                    System.out.println("INVALID TABLE NUMBER");
                    return;
                }
                List<String> lines= new ArrayList<>();
                try(BufferedReader reader = new BufferedReader(new FileReader(fl))){
                    String currentLine;
                    while((currentLine=reader.readLine())!=null){
                        lines.add(currentLine.replaceAll(remove,"").trim());
                    }
                }
                catch(Exception e){
                    System.out.println("THE GIVEN ITEM IS NOT IN THE ORDER!");
                }
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fl))){
                    for(String line:lines){
                        writer.write(line);
                        writer.newLine();
                    }
                }
                catch(Exception e){
                    System.out.println("ERROR ON FILE WRITING");
                }

        }
}
//MAIN FUNCTION
public class FoodOrdering{
    public static void main(String arv[]) {
        System.out.flush();
        Scanner sc= new Scanner(System.in);
        Display_order disp= new Display_order();
        MainMenu obj1= new MainMenu();
        obj1.menu();
        int i=0;try{
            while(i<10){
                System.out.print("PLEASE ENTER THE CHOICE--");
                 i=Integer.parseInt(sc.nextLine());
                 switch (i) {
                    //CREATING THE NEW FILE
                    case 1:{
                        Ordertaking obj3=new Ordertaking();
                        obj3.createFile();
                        System.out.print("\033[H\033[2J");
                        obj1.menu();
                        break;
                    }
                    //DISPLAYING THE NEW ORDER
                    case 2:{
                        System.out.print("ENTER THE TABLE NUMBER :");
                        String s=sc.nextLine();
                        try{
                            disp.vieworder(s);
                        } catch(Exception e){
                            System.out.println("\nENTERING THE WRONG TABLE!!\n");
                        }
                        System.out.println("\nPRESS ENTER TO CONTINUE...");
                        sc.nextLine();
                        System.out.flush();
                        obj1.menu();
                        break;
                    }
                    //REMOVING THE DETAILS FROM THE FILE
                    case 3:{
                            System.out.print("ENTER THE TABLE NUMBER TO REMOVE THE ORDER-");
                            String d=sc.nextLine();
                            Remove rve= new Remove();
                            rve.remove(d);
                            System.out.println("ENTER T0 CONTINUE..");
                            sc.nextLine();
                            System.out.flush();
                            obj1.menu();
                            break;
                    }
                    //UPDATING THE FILE
                    case 4:{
                        System.out.flush();
                        System.out.print("IF YOU NEED TO ADD NEW ITEMS TYPE \"update\"(OR) REMOVE AN ITEM FROM THE LIST TYPE \"remove\"-");
                             String get =sc.nextLine();
                             switch (get) {
                                //ADDING NEW ITEMS IN THE LIST
                                case "update":{
                                    Updateitem up = new Updateitem();
                                    System.out.print("ENTER THE TABLE NUMBER TO ADD ITEMS-");
                                    String tabup =sc.nextLine();
                                    up.update(tabup);
                                    break;
                                }
                                //REMOVING THE EXISTING ITEM IN THE FILE
                                case "remove":{
                                    System.out.println("ENTER THE TABLE NUMBER-");
                                    String tab=sc.nextLine();
                                    System.out.print("ENTER THE ITEM TO REMOVE-");
                                    String remove=sc.nextLine();
                                    Removeitem rem = new Removeitem();
                                    rem.Removeitem_(tab, remove);
                                    System.out.flush();
                                    obj1.menu();
                                    break;
                                }
                                default :{
                                    System.out.println("INVALID INPUT");
                                    System.out.flush();
                                    obj1.menu();
    
                                }
                             }
                             System.out.flush();
                             break;
                    }
                    //EXIT FROM THE SOFTWARE
                    case 5:{
                        CodeExit exitt = new CodeExit();
                        exitt.out();
                    }
    
                 }
            }
        }
        catch(Exception e){
            System.out.println("KINDLY PROVIDE THE NUMBERS FROM 1-5");
        }
    }
}