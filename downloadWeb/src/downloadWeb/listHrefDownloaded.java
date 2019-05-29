package downloadWeb;

import java.awt.List;
import java.util.ArrayList;

public class listHrefDownloaded {
	public static ArrayList listHref = new ArrayList();
	
	public boolean findItemInTheList(String itemToFind) {
        if (listHref.contains(itemToFind))
        	return true;
        return false;
    }
        
}
