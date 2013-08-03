package com.rtnotifier.utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Component
@Qualifier("JSONUtil")
public class JSONUtil {
    /** The mapper. */
    @Autowired
    @Qualifier("objectMapper")
    private ObjectMapper mapper;

    /** The Constant log. */
    private static final Logger log = Logger.getLogger(JSONUtil.class.getName());

    /**
     * Map to json.
     *
     * @param objects the objects
     * @return the string
     */
    public String mapToJSON(List<?> objects) {
        return convertIt(objects);
    }

    /**
     * Map to json.
     *
     * @param obj the obj
     * @return the string
     */
    public String mapToJSON(Object obj) {
        return convertIt(obj);
    }

    /**
     * Convert it.
     *
     * @param obj the obj
     * @return the string
     */
    private String convertIt(Object obj) {

        String result = null;
        try {
            result = mapper.writeValueAsString(obj);

        } catch (JsonGenerationException e) {
            log.fine("Problem generating JSON:" + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            log.fine("Problem mapping JSON:" + e.getMessage());
        } catch (IOException e) {
            log.fine("Generic IO error:" + e.getMessage());
        }
        return result;
    }

    public  <T> Class mapFromJSON(final String json, Class clazz){
        try {
            return mapper.readValue(json, clazz.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
