import common.ImageFile;
import common.Size;

import java.io.File;
import java.io.IOException;

public class Ex {
    public static void main(String[] args) throws IOException {
        ImageFile.fileUpload("/Users/path", Size.SmallSize, null);
    }
}
