/**
 * Waffle (https://github.com/dblock/waffle)
 *
 * Copyright (c) 2010 - 2015 Application Security, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Application Security, Inc.
 */
package waffle.servlet.spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import waffle.windows.auth.impl.WindowsAuthProviderImpl;

/**
 * @author dblock[at]dblock[dot]org
 */
public class SecurityFilterProviderCollectionTests {

    @Test
    public void testDefaultCollection() throws ClassNotFoundException {
        final SecurityFilterProviderCollection coll = new SecurityFilterProviderCollection(
                new WindowsAuthProviderImpl());
        Assert.assertEquals(2, coll.size());
        Assert.assertNotNull(coll.getByClassName(NegotiateSecurityFilterProvider.class.getName()));
        Assert.assertNotNull(coll.getByClassName(BasicSecurityFilterProvider.class.getName()));
    }

    @Test(expected = ClassNotFoundException.class)
    public void testGetByClassNameInvalid() throws ClassNotFoundException {
        final SecurityFilterProviderCollection coll = new SecurityFilterProviderCollection(
                new WindowsAuthProviderImpl());
        coll.getByClassName("classDoesNotExist");
    }

    @Test
    public void testIsSecurityPackageSupported() {
        final SecurityFilterProviderCollection coll = new SecurityFilterProviderCollection(
                new WindowsAuthProviderImpl());
        Assert.assertTrue(coll.isSecurityPackageSupported("NTLM"));
        Assert.assertTrue(coll.isSecurityPackageSupported("Negotiate"));
        Assert.assertTrue(coll.isSecurityPackageSupported("Basic"));
        Assert.assertFalse(coll.isSecurityPackageSupported(""));
        Assert.assertFalse(coll.isSecurityPackageSupported("Invalid"));
    }
}
