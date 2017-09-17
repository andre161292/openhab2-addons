package org.openhab.binding.http2.internal;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.http2.Http2BindingConstants;
import org.openhab.binding.http2.handlers.Http2SimpleHttpHandler;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link Http2HandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author André Dörscheln
 */
@Component(immediate = true, service = { ThingHandlerFactory.class })
public class Http2HandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections
            .singleton(Http2BindingConstants.THING_SIMPLE_HTTP);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(Http2BindingConstants.THING_SIMPLE_HTTP)) {
            return new Http2SimpleHttpHandler(thing);
        }

        return null;
    }
}
