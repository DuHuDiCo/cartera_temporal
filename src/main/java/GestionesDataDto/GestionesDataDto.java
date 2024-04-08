
package GestionesDataDto;

import org.springframework.web.multipart.MultipartFile;


public class GestionesDataDto {
    
    private String delimitante;
    
    private String multipartFile;

    

    public GestionesDataDto() {
    }

    public String getDelimitante() {
        return delimitante;
    }

    public void setDelimitante(String delimitante) {
        this.delimitante = delimitante;
    }

    public String getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(String multipartFile) {
        this.multipartFile = multipartFile;
    }

   
    
    

}
