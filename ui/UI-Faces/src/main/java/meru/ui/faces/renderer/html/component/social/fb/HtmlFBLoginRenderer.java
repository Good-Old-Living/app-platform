package meru.ui.faces.renderer.html.component.social.fb;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.social.fb.FBLogin;

public class HtmlFBLoginRenderer extends HtmlComponentRenderer<FBLogin> {

    public HtmlFBLoginRenderer() {
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
                 .addClassAttribute("fbIcon")
                 .startElement("span")
                 .addHtmlText("Facebook")
                 .endElement()
                 .endElement();

      return false;
    }
}
