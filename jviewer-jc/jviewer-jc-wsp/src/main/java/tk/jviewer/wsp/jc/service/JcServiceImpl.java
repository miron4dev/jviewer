package tk.jviewer.wsp.jc.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import tk.jviewer.services.jc_v1_00.JcFault_Exception;
import tk.jviewer.services.jc_v1_00.JcResult;
import tk.jviewer.services.jc_v1_00.JcServicePortType;
import tk.jviewer.wsp.jc.service.reader.JcStreamReader;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.io.File.separator;
import static org.apache.commons.lang3.RandomStringUtils.random;

/**
 * Implementation of {@link JcServicePortType}.
 */
public class JcServiceImpl implements JcServicePortType {

    private static final List<String> UNALLOWED_SOURCE_SNIPPETS = Arrays.asList("import javax.swing", "import javafx");
    private static final Pattern LOOKUP_CLASS_PATTERN = Pattern.compile("public class ([\\w]++) ");
    private static final String ALPHABET = "abcdefgghijklmnopqrstuvwxyz";
    private static final int RESULT_SUCCESS = 0;
    private static final File TEMP_DIR = new File("tmp");

    public void init() {
        deleteDirectory(TEMP_DIR);
        if (!TEMP_DIR.mkdir()) {
            throw new IllegalStateException("Could not create the temporary directory.");
        }
    }

    public void destroy() {
        deleteDirectory(TEMP_DIR);
    }

    @Override
    public JcResult compileAndExecute(String sourceCode) throws JcFault_Exception {
        File workingDirectory = null;
        try {
            validateSources(sourceCode);
            workingDirectory = new File(TEMP_DIR + separator + random(15, ALPHABET));
            if (!workingDirectory.mkdir()) {
                throw new RuntimeException("Could not create the unique directory " + workingDirectory.getName());
            }
            String className = lookupClassName(sourceCode);
            File sourceFile = writeToFile(workingDirectory, className, sourceCode);

            Process compile = Runtime.getRuntime().exec("javac " + sourceFile.getPath());
            JcResult result = new JcResult();
            if (compile.waitFor() != RESULT_SUCCESS) {
                result.setOutput(new JcStreamReader(compile.getErrorStream()).read());
                result.setErrorOccurred(true);
                return result;
            } else {
                Process execute = Runtime.getRuntime().exec("java -classpath " + workingDirectory + " " + className);
                if (execute.waitFor() != RESULT_SUCCESS) {
                    result.setOutput(new JcStreamReader(execute.getErrorStream()).read());
                    result.setErrorOccurred(true);
                }
                result.setOutput(new JcStreamReader(execute.getInputStream()).read());
                result.setErrorOccurred(false);
                return result;
            }
        } catch (Exception e) {
            throw new JcFault_Exception(e.getMessage(), e);
        } finally {
            deleteDirectory(workingDirectory);
        }
    }

    private void validateSources(String sourceCode) throws IllegalArgumentException {
        for (String unallowedSnippet: UNALLOWED_SOURCE_SNIPPETS) {
            if (sourceCode.contains(unallowedSnippet)) {
                throw new IllegalArgumentException("Execution of swing or javafx applications is not allowed. Forbidden code snippet: " + unallowedSnippet);
            }
        }
    }

    private String lookupClassName(String sourceCode) throws IllegalArgumentException {
        Matcher matcher = LOOKUP_CLASS_PATTERN.matcher(sourceCode);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Could not lookup class name. Source code is invalid!");
        }
    }

    private File writeToFile(File workingDirectory, String className, String sourceCode) throws IllegalStateException {
        File file = new File(workingDirectory + separator + className + ".java");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(sourceCode);
            printWriter.flush();
            return file;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void deleteDirectory(File directory) throws IllegalStateException {
        if (directory == null || !directory.exists()) {
            return;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file: files) {
                if (!file.delete()) {
                    throw new IllegalStateException("Could not delete file " + file.getPath());
                }
            }
        }
        if (!directory.delete()) {
            throw new IllegalStateException("Could not delete directory " + directory.getName());
        }
    }
}
