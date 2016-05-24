package opet.mygarage.util;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

public class Upload {

    private static final Upload INSTANCE = new Upload();

    private Upload() {}

    public void write(Part part, String diretorio, String fileName) throws IOException {
        String filePath = "C:\\DEV\\workspace\\MyGarage\\WebContent\\resources\\images\\" + diretorio;
//        String filePath = "resources\\images\\" + diretorio;

        File fileSaveDir = new File(filePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        part.write(filePath + File.separator + fileName);        
    }

    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    public static Upload getInstance() {
        return INSTANCE;
    }

}
