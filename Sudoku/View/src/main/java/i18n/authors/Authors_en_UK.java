package i18n.authors;

import java.util.ListResourceBundle;

public class Authors_en_UK extends ListResourceBundle {

    private final Object[][] resources = {{"author1", "Barbra Borkowska"}, {"author2", "Alexander Lemiesz"}};

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}
