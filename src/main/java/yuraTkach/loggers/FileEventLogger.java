package yuraTkach.loggers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger{
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    public void logEvent(yuraTkach.events.Event event) {
        try {
            FileUtils.writeStringToFile(file,event.toString()+"\n", Charset.forName("UTF-8"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void init() throws IOException {

        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + fileName);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }
}
