package tk.jviewer.wsc.jc;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "classpath:META-INF/jviewer-jc-wsc/client-applicationContext.xml")
public class JcWsClientTestIntegration extends AbstractJUnit4SpringContextTests {

    @Autowired
    private JcWsClient wsClient;

    @Test
    public void test_compileAndExecute_compilationSuccess() {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\");\n" +
                "    }");
        sourceCode.append("}");
        assertEquals("TEST FOO", wsClient.compileAndExecute(sourceCode.toString()));
    }

    @Test
    public void test_compileAndExecute_compilationFailed() {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\")\n" +
                "    }");
        sourceCode.append("}");
        assertThat(wsClient.compileAndExecute(sourceCode.toString()), CoreMatchers.containsString("error: ';' expected"));
    }
}
