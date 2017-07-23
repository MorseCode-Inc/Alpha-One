package inc.morsecode.web.helper;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;

import java.util.ArrayList;

/**
 * Created by morsecode on 7/23/2017.
 *
 * This class provides helper methods to include
 * things like javescript, css, and other resources
 * the html page will need
 */
@JadeHelper
public class HtmlHead {

    private ArrayList<String> jsIncludes= new ArrayList<>();

    public ArrayList<String> jsIncludes() {
        return jsIncludes;
    }

    public HtmlHead addJsInclude(String path) {
        this.jsIncludes.add(path);
        return this;
    }

}

