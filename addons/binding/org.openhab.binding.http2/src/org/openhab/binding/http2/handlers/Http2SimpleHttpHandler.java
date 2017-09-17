package org.openhab.binding.http2.handlers;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.http2.Http2BindingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author André Dörscheln
 */
public class Http2SimpleHttpHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(Http2SimpleHttpHandler.class);

    public Http2SimpleHttpHandler(@NonNull Thing thing) {
        super(thing);

    }

    @Override
    public void handleCommand(@NonNull ChannelUID channelUID, @NonNull Command command) {
        switch (channelUID.getId()) {
            case Http2BindingConstants.CHANNEL_SWITCH:
                if (command == OnOffType.ON) {
                    scheduler.schedule(() -> {
                        String urlString = (String) getConfig().get(Http2BindingConstants.PARAMETER_URL_ON);
                        String response = callUrl(channelUID, command, urlString);
                        logger.debug("Result for ON: >>>{}<<<", response);
                    }, 0, TimeUnit.SECONDS);
                } else if (command == OnOffType.OFF) {
                    scheduler.schedule(() -> {
                        String urlString = (String) getConfig().get(Http2BindingConstants.PARAMETER_URL_OFF);
                        String response = callUrl(channelUID, command, urlString);
                        logger.debug("Result for OFF: >>>{}<<<", response);
                    }, 0, TimeUnit.SECONDS);
                }
                break;
        }
    }

    private String callUrl(ChannelUID channelUID, Command command, String urlString) {
        try {
            URL url = new URL(urlString);
            try (InputStream openConnection = url.openStream()) {
                StringWriter writer = new StringWriter();
                IOUtils.copy(openConnection, writer);
                String result = writer.toString();
                return result;
            }
        } catch (Exception e) {
            logger.error("Couldn't execute channel {} for command {}", channelUID, command, e);
            return null;
        }
    }

}
