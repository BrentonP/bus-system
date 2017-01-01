package dataaccess;

import java.io.Serializable;

/**
 * This interface ensures that anything that implements it will have a getId()
 * method. This is required for any objects that use the GenericDao Interface.
 *
 * @author Brenton Philp
 * @param <PK>
 */
public interface Identifiable<PK extends Serializable> {

    public PK getId();
}
