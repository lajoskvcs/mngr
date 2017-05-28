package app.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The helper class for resolve <code>.properties</code> files.
 */
public class PropertyResolver {
    /**
     * This variable inject the {@code Logger} into the {@code Class}.
     */
    private static Logger logger = LoggerFactory.getLogger(PropertyResolver.class);

    /**
     * Returns {@link java.util.Properties Properties} from the given <code>.properties</code> file.
     * @param filename The name of the <code>.properties</code> file
     * @return The {@link java.util.Properties Properties} from the file
     */
    public static Properties getProperties( String filename ) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = PropertyResolver.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                logger.error("Unable to find " + filename);
                return null;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
