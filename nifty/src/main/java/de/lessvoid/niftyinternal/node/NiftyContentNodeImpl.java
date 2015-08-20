/*
 * Copyright (c) 2015, Nifty GUI Community
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.lessvoid.niftyinternal.node;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyState;
import de.lessvoid.nifty.NiftyCanvas;
import de.lessvoid.nifty.node.NiftyContentNode;
import de.lessvoid.nifty.spi.node.NiftyNodeContentImpl;
import de.lessvoid.nifty.spi.node.NiftyNodeImpl;
import de.lessvoid.nifty.spi.node.NiftyNodeStateImpl;
import de.lessvoid.nifty.types.NiftyColor;
import de.lessvoid.niftyinternal.math.Mat4;

import static de.lessvoid.nifty.NiftyState.NiftyStandardState.NiftyStateBackgroundColor;
import static de.lessvoid.nifty.NiftyState.NiftyStandardState.NiftyStateTransformation;

/**
 * Created by void on 09.08.15.
 */
public class NiftyContentNodeImpl implements NiftyNodeStateImpl, NiftyNodeContentImpl, NiftyNodeImpl<NiftyContentNode> {
  private NiftyContentNode niftyNode;

  private NiftyColor backgroundColor;
  private Mat4 screenToLocal;

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // NiftyNodeStateImpl
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public void update(final NiftyState niftyState) {
    backgroundColor = niftyState.getState(NiftyStateBackgroundColor);
    screenToLocal = niftyState.getState(NiftyStateTransformation);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // NiftyNodeContentImpl
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public void updateCanvas(final NiftyCanvas niftyCanvas) {
    niftyCanvas.setFillStyle(backgroundColor);
    niftyCanvas.fillRect(0., 0., getContentWidth(), getContentHeight());
  }

  @Override
  public int getContentWidth() {
    return niftyNode.getW();
  }

  @Override
  public int getContentHeight() {
    return niftyNode.getH();
  }

  @Override
  public Mat4 getScreenToLocal() {
    return screenToLocal;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // NiftyNodeImpl
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public void initialize(Nifty nifty, final NiftyContentNode niftyNode) {
    this.niftyNode = niftyNode;
  }

  @Override
  public NiftyContentNode getNiftyNode() {
    return niftyNode;
  }
}
