package inc.morsecode.web.resource;

import inc.morsecode.json.JsonParser;
import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.spec.json.JsonStructure;
import inc.morsecode.web.data.FormStore;
import inc.morsecode.web.form.FormElement;
import inc.morsecode.web.form.WebForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by morsecode on 7/24/2017.
 */
@Component
public class FileFormStore implements FormStore {

    @Value("${store.form.filesystem.path:/forms}")
    private String filesystemPath;

    /**
     * Fetch a WebForm from the database
     * @param name
     * @return
     */
    @Override
    public WebForm readForm(String name) throws IOException {
        WebForm form= new WebForm();

        File formDir= new File(filesystemPath +"/"+ name);
        if (!formDir.exists() || !formDir.isDirectory()) {
            // does not exist
            throw new FileNotFoundException(filesystemPath +"/"+ name +": must be a directory.");
        }

        try {
            JsonStructure json= JsonParser.parse(new File(formDir, "form.json"));

            Arrays.stream(formDir.listFiles((dir, filename) -> !"form.json".equals(filename) && filename.endsWith(".json"))).forEach( file -> {
                    try {
                        FormElement elem = readElement(name, file.getName().replaceAll(".json", ""));
                        form.add(elem);
                    } catch (IOException iox){
                        // TODO: error logging
                        System.err.println("Corrupt Form Element: "+ formDir.getAbsolutePath() +"/"+ file.getName());
                        iox.printStackTrace();
                    }
            }
            );

        } catch (MalformedJsonException mfx) {
            throw new IOException("Corrupt Form: "+ formDir.getAbsolutePath() +"/"+ "form.json", mfx);
        }

        return form;
    }

    @Override
    public FormElement readElement(String form, String name) throws IOException {
        FormElement element= new FormElement();

        File formDir= new File(filesystemPath +"/"+ form);
        if (!formDir.exists() || !formDir.isDirectory()) {
            // does not exist
            throw new FileNotFoundException(filesystemPath +"/"+ form +": must be a directory.");
        }

        try {
            JsonStructure json= JsonParser.parse(new File(formDir, name +".json"));

            element.set(json);

            return element;
        } catch (MalformedJsonException mfx) {
            throw new IOException("Corrupt File: "+ formDir.getAbsolutePath() +"/"+ "form.json", mfx);
        }

    }

    @Override
    public boolean write(String form, FormElement e) throws IOException {

        File formDir= new File(filesystemPath +"/"+ form);

        if (!formDir.exists()) {
            formDir.mkdirs();
        }

        File file= new File(formDir, e.getName() +".json");

        FileWriter writer= new FileWriter(file);
        writer.write(e.toJson().toString());
        writer.close();

        return true;

    }

    @Override
    public boolean write(WebForm form) throws IOException {

        File formDir= new File(filesystemPath +"/"+ form);

        if (!formDir.exists()) {
            formDir.mkdirs();
        }

        File file= new File(formDir, "form.json");

        FileWriter writer= new FileWriter(file);
        writer.write(form.toJson().toString());
        writer.close();

        for (FormElement e : form.getElements()) {

        }

        return true;

    }

    @Override
    public boolean delete(String form, FormElement e) {
        return false;
    }


    @Override
    public boolean elementExists(String form, String name) {
        return new File(filesystemPath +"/"+ name +"/form.json").exists();
    }

    @Override
    public boolean formExists(String name) {
        return new File(filesystemPath +"/"+ name +"/form.json").exists();
    }
}
