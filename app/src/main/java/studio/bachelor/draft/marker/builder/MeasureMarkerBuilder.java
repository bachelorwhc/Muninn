package studio.bachelor.draft.marker.builder;

import studio.bachelor.draft.marker.LinkMarker;
import studio.bachelor.draft.marker.Marker;
import studio.bachelor.draft.marker.MeasureMarker;
import studio.bachelor.draft.utility.Position;

/**
 * Created by BACHELOR on 2016/03/08.
 */
public class MeasureMarkerBuilder extends LinkMarkerBuilder {
    /**
     * {@inheritDoc}
     */
    protected void createProductIfNull() {
        if(product == null)
            product = new MeasureMarker();
    }
//
//    /**
//     * {@inheritDoc}
//     * @param position {@inheritDoc}
//     * @return {@inheritDoc}
//     */
//    public MeasureMarkerBuilder setPosition(Position position){
//        return (MeasureMarkerBuilder)super.setPosition(position);
//    }
//
//    /**
//     *為{@link studio.bachelor.draft.marker.LinkMarker}設定連結對象。
//     * @param marker 欲連結之{@link studio.bachelor.draft.marker.Marker}。
//     * @return Builder自身。
//     */
//    public MeasureMarkerBuilder setLink(Marker marker){
//        return (MeasureMarkerBuilder)super.setLink(marker);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    public MeasureMarkerBuilder clearProductCache() {
//        return (MeasureMarkerBuilder)super.clearProductCache();
//    }
}
