package DataStruct;

import java.util.*;

/**
 *
 * @author brock
 */
public class LList<T> {

    private T el;
    private LList next;

    public LList(T... elements) {
        this(new LinkedList(Arrays.asList(elements)));
    }

    public LList(java.util.List<T> elements) {
        if (elements.size() == 0) {
            el = null;
        } else {
            el = elements.get(0);
            elements.remove(0);
            next = new LList(elements);
        }

        
    }

    public LList cdr() {
        return this.next;
    }

    public T car() {
        return this.el;
    }
}
