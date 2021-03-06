/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.regoheatpump.internal.rego6xx;

/**
 * The {@link CommandFactory} is responsible for creating different commands that can
 * be send to a rego 6xx unit.
 *
 * @author Boris Krivonog - Initial contribution
 */
public class CommandFactory {
    private static final byte DeviceAddress = (byte) 0x81;

    public static byte[] createReadRegoVersionCommand() {
        return createReadCommand((byte) 0x7f, (short) 0, (short) 0);
    }

    public static byte[] createReadFromSystemRegisterCommand(short address) {
        return createReadCommand((byte) 0x02, address, (short) 0);
    }

    public static byte[] createReadFromDisplayCommand(short displayLine) {
        return createReadCommand((byte) 0x20, displayLine, (short) 0);
    }

    public static byte[] createReadLastErrorCommand() {
        return createReadCommand((byte) 0x40, (short) 0, (short) 0);
    }

    public static byte[] createReadFromFrontPanelCommand(short address) {
        return createReadCommand((byte) 0x00, address, (short) 0);
    }

    private static byte[] createReadCommand(byte source, short address, short data) {
        byte[] addressBytes = ValueConverter.shortToSevenBitFormat(address);
        byte[] dataBytes = ValueConverter.shortToSevenBitFormat(data);
        return new byte[] { DeviceAddress, source, addressBytes[0], addressBytes[1], addressBytes[2], dataBytes[0],
                dataBytes[1], dataBytes[2], Checksum.calculate(addressBytes, dataBytes) };
    }
}
