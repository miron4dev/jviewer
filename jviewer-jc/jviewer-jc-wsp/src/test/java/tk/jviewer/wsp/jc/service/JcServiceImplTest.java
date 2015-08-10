package tk.jviewer.wsp.jc.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:cxf-applicationContext.xml")
public class JcServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private JcService jcService;

    @Test
    public void test_compileAndExecute_compilationSuccess() {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\");\n" +
                "    }");
        sourceCode.append("}");
        assertEquals("TEST FOO\n", jcService.compileAndExecute(sourceCode.toString()));
    }
}
