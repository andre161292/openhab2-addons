package org.openhab.binding.http2;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 *
 * @author André Dörscheln
 */
public class Http2BindingConstants {

    public static final String BINDING_ID = "http2";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_SIMPLE_HTTP = new ThingTypeUID(BINDING_ID, "simpleHttp");

    // List of all Channel ids
    public static final String CHANNEL_SWITCH = "switch";

    // List of all Parameter Ids
    public static final String PARAMETER_URL_ON = "urlOn";
    public static final String PARAMETER_URL_OFF = "urlOff";

}
