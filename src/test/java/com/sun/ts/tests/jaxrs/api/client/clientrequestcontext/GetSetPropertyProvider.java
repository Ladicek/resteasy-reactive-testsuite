/*
 * Copyright (c) 2012, 2020 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.ts.tests.jaxrs.api.client.clientrequestcontext;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.Response;

import com.sun.ts.lib.harness.EETest.Fault;

public class GetSetPropertyProvider extends ContextProvider {
  private AtomicInteger counter;

  public GetSetPropertyProvider(AtomicInteger counter) {
    super();
    this.counter = counter;
  }

  @Override
  protected void checkFilterContext(ClientRequestContext context) throws Fault {
    Object property = context.getProperty("PROPERTY");
    String entity = property == null ? "NULL" : property.toString();
    if (counter.incrementAndGet() == 2) {
      Response r = Response.ok(entity).build();
      context.abortWith(r);
    } else
      context.setProperty("PROPERTY", "value");
  }

}
