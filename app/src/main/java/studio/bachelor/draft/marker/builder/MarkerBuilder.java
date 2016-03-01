package studio.bachelor.draft.marker.builder;

import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.utility.Builder;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public abstract class MarkerBuilder implements Builder {
    static protected final DraftDirector director = DraftDirector.instance;
    protected Marker product;

    protected MarkerBuilder() {

    }

    public MarkerBuilder clearProductCache() {
        product = null;
        return this;
    }

    protected abstract void createProductIfNull();

    public Marker build() {
        createProductIfNull();
        Marker return_value = product;
        clearProductCache();
        return return_value;
    }

    public MarkerBuilder setPosition(Position position) {
        createProductIfNull();
        product.position.set(position);
        return this;
    }
}
