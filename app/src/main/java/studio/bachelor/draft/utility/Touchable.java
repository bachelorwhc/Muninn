package studio.bachelor.draft.utility;

/**
 * Created by BACHELOR on 2016/03/02.
 */
public interface Touchable {
    boolean canBeTouched(Position position, double threshold);
    double getDistanceTo(Position position);
}
