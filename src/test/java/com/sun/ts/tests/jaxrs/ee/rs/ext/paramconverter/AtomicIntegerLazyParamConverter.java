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

package com.sun.ts.tests.jaxrs.ee.rs.ext.paramconverter;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverter.Lazy;

@Lazy
public class AtomicIntegerLazyParamConverter
    implements ParamConverter<AtomicInteger> {

  @Override
  public AtomicInteger fromString(String value)
      throws IllegalArgumentException {
    return new AtomicInteger(Integer.parseInt(value));
  }

  @Override
  public String toString(AtomicInteger value) throws IllegalArgumentException {
    return String.valueOf(value.get());
  }

}
