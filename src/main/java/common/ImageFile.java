package common;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class ImageFile {

    /**
     * 1개의 이미지 파일을 가져오기 위한 메서드
     * @param imgName 가져올 이미지 파일의 이름을 작성(확장명은 적지 않음).
     * @param path 가져올 이미지 파일의 디렉토리 작성.
     * @return
     * @throws IOException
     */
    public static String getImageFile(String imgName, String path) {
        String data = "";

        File file = new File(path + File.separator);

        if(file.isDirectory()) {
            for(File dirFile : file.listFiles()) {
                char[] s = dirFile.getName().toCharArray();
                if(s[0] == '.') {
                    continue;
                }

                if(dirFile.getName().contains(".")) {
                    String[] name = dirFile.getName().split("\\.");
                    if(imgName.equals(name[0])) {
                        byte[] result = null;
                        try {
                            result = FileUtils.readFileToByteArray(new File(path + File.separator + dirFile.getName()));

                            String src = new String(Base64.getEncoder().encode(result));
                            String ext = dirFile.getName().split("\\.")[1];

                            data = "data:image/" + ext + ";base64," + src;
                        } catch (IOException ie) {
                            System.err.println("실패!");
                        }
                    }
                }
            }
        }
        // else문: path 경로가 존재하지 않음
        else {
            System.err.println("존재하지 않는 경로");
        }

        return data;
    }

    /**
     * 파일 업로드를 위한 메소드. 파일 크기는 Size클래스를 이용해 설정
     * file name은 file1, 2, 3로 지정
     * @param path 파일을 업로드할 디렉토리
     * @param uploadSize 파일 업로드 최대 크기
     * @param req 파일을 받기 위한 request
     * @throws IOException
     */
    public static void fileUpload(String path, Size uploadSize, HttpServletRequest req) throws IOException {
        int size = uploadSize.getValue();
        String entType = "UTF-8";

        File file = new File(path + File.separator);

        if(file.isDirectory()) {
            MultipartRequest request = new MultipartRequest(req, path, size, entType, new DefaultFileRenamePolicy());
            File[] files = {request.getFile("file1"), request.getFile("file2"), request.getFile("file3")};
            for(File fName : files)
                System.out.println("fName: " + fName);
        } else {
            System.err.println("파일 업로드 실패!");
        }
    }

    /**
     * 여러개의 이미지 파일을 가져오기 위한 메서드
     * @param path 가져올 이미지 파일들의 디렉토리 작성.
     * @return
     */
    public static ArrayList<String> getImageFiles(String path) {
        ArrayList<String> list = new ArrayList<>();

        File file = new File(path + File.separator);

        if(file.isDirectory()) {
            for(File dirFile : file.listFiles()) {
                char[] s = dirFile.getName().toCharArray();
                if(s[0] == '.') {
                    continue;
                }

                if(dirFile.getName().contains(".")) {
                    String[] name = dirFile.getName().split("\\.");
                    byte[] result = null;
                    try {
                        result = FileUtils.readFileToByteArray(new File(path + File.separator + dirFile.getName()));

                        String src = new String(Base64.getEncoder().encode(result));
                        String ext = dirFile.getName().split("\\.")[1];

                        String data = "data:image/" + ext + ";base64," + src;

                        list.add(data);
                    } catch (IOException ie) {
                        System.err.println("실패!");
                    }
                }
            }
        }
        // else문: path 경로가 존재하지 않음
        else {
            System.err.println("존재하지 않는 경로");
        }

        return list;
    }
}
