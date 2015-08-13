package tk.jviewer.wsp.jc.service.reader;

import java.io.*;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Reads the specified instance of {@link InputStream} and transforms the result into the string.
 */
public class JcStreamReader {

    private InputStream inputStream;

    /**
     * Creates the new instance of reader with specified input stream.
     * @param inputStream the input stream.
     */
    public JcStreamReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Returns the reading result of input stream in the string representation.
     * @return see description.
     * @throws IllegalStateException in case of {@link IOException} during reading.
     */
    public String read() throws IllegalStateException {
        String result = "";
        try {
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
        // We should remove the last line breaking
        return !result.isEmpty() ? result.substring(0, result.length() -1) : EMPTY;
    }
}
