package de.lessvoid.nifty.effects.impl;

import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.effects.EffectImpl;
import de.lessvoid.nifty.effects.Falloff;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.render.NiftyImageMode;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import de.lessvoid.nifty.render.RenderStateType;
import de.lessvoid.nifty.tools.Alpha;
import de.lessvoid.nifty.tools.SizeValue;

public class ImageOverlay implements EffectImpl {
  private NiftyImage image;
  private Alpha alpha;
  private SizeValue resize;

  public void activate(final Nifty nifty, final Element element, final Properties parameter) {
    image = nifty.getRenderDevice().createImage(parameter.getProperty("filename"), false);
    String imageMode = parameter.getProperty("imageMode", null);
    if (imageMode != null) {
      image.setImageMode(NiftyImageMode.valueOf(imageMode));
    }
    alpha = new Alpha(parameter.getProperty("alpha", "#f"));
    resize = new SizeValue(parameter.getProperty("resize", "0px"));
  }

  public void execute(
      final Element element,
      final float normalizedTime,
      final Falloff falloff,
      final NiftyRenderEngine r) {
    r.saveState(RenderStateType.allStates());
    if (falloff != null) {
      r.setColorAlpha(alpha.mutiply(falloff.getFalloffValue()).getAlpha());
    } else {
      if (!r.isColorAlphaChanged()) {
        r.setColorAlpha(alpha.getAlpha());
      }
    }
    int resizeOffset = resize.getValueAsInt(element.getWidth());
    r.renderImage(
        image,
        element.getX() - resizeOffset,
        element.getY() - resizeOffset,
        element.getWidth() + resizeOffset * 2,
        element.getHeight() + resizeOffset * 2);
    r.restoreState();
  }

  public void deactivate() {
  }
}