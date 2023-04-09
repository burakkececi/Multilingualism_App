package business.abstracts;

import entities.Unit;

public interface IUnitService {

    /**
     * Creates Unit object.
     * @param index examine the quiz number.
     * @return Unit object.
     */
    Unit createUnit(int index);

}
