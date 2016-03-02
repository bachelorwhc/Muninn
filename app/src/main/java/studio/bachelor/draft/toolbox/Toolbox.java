package studio.bachelor.draft.toolbox;

import java.util.ArrayList;
import java.util.List;

import studio.bachelor.draft.DraftDirector;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class Toolbox{
    static private final DraftDirector director = DraftDirector.instance;
    static private final Toolbox instance = new Toolbox();
    static public Toolbox getInstance() {
        return instance;
    }

    public final List<Tool> tools = new ArrayList<Tool>();

    private Toolbox() {
        tools.add(new Deleter());
    }
}
