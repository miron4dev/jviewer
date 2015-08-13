package tk.jviewer.wsp.jc.service;

import tk.jviewer.wsp.jc.service.model.JcFault;
import tk.jviewer.wsp.jc.service.reader.JcStreamReader;

import javax.jws.WebService;
import java.io.File;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.RandomStringUtils.random;

/**
 * Implementation of {@link JcService}.
 */
@WebService(endpointInterface = "tk.jviewer.wsp.jc.service.JcService")
public class JcServiceImpl implements JcService {

    private static final Pattern LOOKUP_CLASS_PATTERN = Pattern.compile("public class ([\\w]++) ");
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int RESULT_SUCCESS = 0;
    private static final File TEMP_DIR = new File("tmp");

    public void init() {
        if (TEMP_DIR.exists()) {
            deleteTempDirectory();
        }
        if (!TEMP_DIR.mkdir()) {
            throw new IllegalStateException("Could not create the temporary directory.");
        }
    }

    public void destroy() {
        deleteTempDirectory();
    }

    @Override
    public String compileAndExecute(String sourceCode) {
        String className = null;
        File sourceFile = null;
        try {
            validateSources();
            className = random(15, ALPHABET);
            sourceCode = adjustSourceCode(className, sourceCode);
            sourceFile = writeToFile(className, sourceCode);
            Process compile = Runtime.getRuntime().exec("javac " + sourceFile.getPath());
            if (compile.waitFor() != RESULT_SUCCESS) {
                return new JcStreamReader(compile.getErrorStream()).read();
            } else {
                Process execute = Runtime.getRuntime().exec("java " + TEMP_DIR + "/" + className);
                if (execute.waitFor() != RESULT_SUCCESS) {
                    return new JcStreamReader(execute.getErrorStream()).read();
                }
                return new JcStreamReader(execute.getInputStream()).read();
            }
        } catch (Exception e) {
            throw new JcFault(e.getMessage(), e.getCause());
        } finally {
            if (sourceFile != null) {
                sourceFile.delete();
                new File(TEMP_DIR + "/" + className + ".class").delete();
            }
        }
    }

    private void validateSources() {
        //TODO validation
    }

    private String adjustSourceCode(String className, String sourceCode) {
        Matcher matcher = LOOKUP_CLASS_PATTERN.matcher(sourceCode);
        if (matcher.find()) {
            sourceCode = "package tmp;\n" + sourceCode;
            return sourceCode.replaceFirst(matcher.group(1), className);
        } else {
            throw new IllegalArgumentException("Could not lookup class name. Source code is invalid!");
        }
    }

    private File writeToFile(String className, String sourceCode) {
        File file = new File(TEMP_DIR + File.separator + className + ".java");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(sourceCode);
            printWriter.flush();
            return file;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void deleteTempDirectory() {
        File[] files = TEMP_DIR.listFiles();
        if (files != null) {
            for (File file: files) {
                if (!file.delete()) {
                    throw new IllegalStateException("Could not delete the file " + file.getPath());
                }
            }
        }
        if (!TEMP_DIR.delete()) {
            throw new IllegalStateException("Could not delete the temporary directory.");
        }
    }
}
