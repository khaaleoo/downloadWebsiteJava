package downloadWeb;

import java.io.File;

public class createDirectories {
     
    public static void createDirectories(String FOLDER) {
         
        File newFolder = new File(FOLDER);
         
        boolean created =  newFolder.mkdirs();
         
        if(created)
            System.out.println("Folder was created !");
        else
            System.out.println("Unable to create folder");
    }
}