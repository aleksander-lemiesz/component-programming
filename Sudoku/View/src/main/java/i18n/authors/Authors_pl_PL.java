package i18n.authors;

import java.util.ListResourceBundle;

public class Authors_pl_PL extends ListResourceBundle {

    private final Object[][] resources = {{"author1", "Basia Borkowska"}, {"author2", "Aleksander Lemiesz"}};

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}
