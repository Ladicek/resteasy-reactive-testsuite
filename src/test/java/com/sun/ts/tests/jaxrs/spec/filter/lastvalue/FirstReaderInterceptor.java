/*
 * Copyright (c) 2013, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.ts.tests.jaxrs.spec.filter.lastvalue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;

import jakarta.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

@Provider
@Priority(100)
public class FirstReaderInterceptor implements ReaderInterceptor {

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context)
      throws IOException, WebApplicationException {
    MultivaluedMap<String, String> headers = context.getHeaders();
    String header = headers.getFirst(Resource.HEADERNAME);
    if (header != null && header.equals(getClass().getName())) {
      context.setAnnotations(Resource.class.getAnnotations());
      context.setInputStream(
          new ByteArrayInputStream(getClass().getName().getBytes()));
      context.setMediaType(MediaType.TEXT_HTML_TYPE);
      context.setType(LinkedList.class);
    }
    return context.proceed();
  }

}
