package dk.sdu.mmmi.cbse.asteroidsnetbeansmodules;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.*;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {

    private static final String ADD_WRAPPER_UPDATES_FILE = "D:\\GitHub\\CBSE-Portfolio\\AsteroidsNetbeansModules\\application\\src\\test\\resources\\wrapper\\updates.xml";
    private static final String REM_WRAPPER_UPDATES_FILE = "D:\\GitHub\\CBSE-Portfolio\\AsteroidsNetbeansModules\\application\\src\\test\\resources\\nowrapper\\updates.xml";;
    private static final String UPDATES_FILE = "D:\\updates.xml";
    
    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false). 
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException {
        
        // SETUP
        List<IEntityProcessingService> processors = new CopyOnWriteArrayList<>();
        List<IGamePluginService> plugins = new CopyOnWriteArrayList<>();
        copy(get(REM_WRAPPER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        Thread.sleep(10000);
        update(processors, plugins);
        
        assertEquals("No Processors BEING", 2, processors.size());
        assertEquals("No Plugins BEGIN", 2, plugins.size());
        
        copy(get(ADD_WRAPPER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        Thread.sleep(10000);
        update(processors, plugins);
        
        assertEquals("One Processor", 6, processors.size());
        assertEquals("One Plugin", 6, plugins.size());
        
        copy(get(REM_WRAPPER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        Thread.sleep(10000);
        update(processors, plugins);
        
        assertEquals("No Processors END", 5, processors.size());
        assertEquals("No Plugins END", 5, plugins.size());
        
    }
    
    private void update(List<IEntityProcessingService> processors, List<IGamePluginService> plugins){
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
    }

}
