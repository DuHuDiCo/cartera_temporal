
package com.cartera_temp.cartera_temp.Utils;

import com.cartera_temp.cartera_temp.ServiceImpl.CustomMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SaveFiles {
    
    
    public   MultipartFile convertirFile(String base64){
        
        final String[] base64Array = base64.split(",");
        
        String dataUir, data;

        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            dataUir = "data:image/jpg;base64";
            data = base64Array[0];
        }
        
        MultipartFile multipartFile = new CustomMultipartFile(data, dataUir);
        return multipartFile;
    }

}
