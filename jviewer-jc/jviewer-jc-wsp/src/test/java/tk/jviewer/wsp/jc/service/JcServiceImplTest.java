package tk.jviewer.wsp.jc.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import tk.jviewer.services.jc_v1_00.JcFault_Exception;
import tk.jviewer.services.jc_v1_00.JcResult;
import tk.jviewer.services.jc_v1_00.JcServicePortType;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:cxf-applicationContext.xml")
public class JcServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private JcServicePortType jcService;

    @Test
    public void test_compileAndExecute_compilationSuccess() throws JcFault_Exception {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\");\n" +
                "    }");
        sourceCode.append("}");
        JcResult result = jcService.compileAndExecute(sourceCode.toString());
        assertFalse(result.isErrorOccurred());
        assertEquals("TEST FOO", result.getOutput());
    }

    @Test
    public void test_compileAndExecute_compilationFailed() throws JcFault_Exception {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\")\n" +
                "    }");
        sourceCode.append("}");
        JcResult result = jcService.compileAndExecute(sourceCode.toString());
        assertTrue(result.isErrorOccurred());
        assertThat(result.getOutput(), CoreMatchers.containsString("error: ';' expected"));
    }
}
