package meru.ui.faces.renderer.html.component.social.google;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.social.google.GoogleLogin;

public class HtmlGoogleLoginRenderer extends HtmlComponentRenderer<GoogleLogin> {

  public HtmlGoogleLoginRenderer() {
    super("a");
  }

  @Override
  protected void addAttributes() {
   
    addAttribute("href",
                 "javascript:;");
  }

  @Override
  protected boolean preRenderChildren() {

    htmlBuilder.startElement("div")
               .addClassAttribute("googleIcon")
               .startElement("span")
               .addHtmlText("Google")
               .endElement()
               .endElement();

    return false;
  }

}
