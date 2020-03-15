package meru.ui.faces.renderer.html.component.wizard;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.wizard.WizardItem;

public class HtmlWizardItemRenderer extends HtmlComponentRenderer<WizardItem> {

  public HtmlWizardItemRenderer() {
    super("div");
  }

  @Override
  protected boolean preRender() {

    htmlBuilder.startElement("div")
               .addAttribute("cid", uiComponent.getId())
               .addAttribute("listener", uiComponent.getListener())
               .addAttribute("src", uiComponent.getSrc());
    String style = null;
    if (uiComponent.getCssStyle() != null) {
      style = uiComponent.getCssStyle();
    }

    htmlBuilder.startElement("h3")
               .addStyleAttribute(style)
               /*
                * .addAttribute("class", wizardItem.getCssClass())
                */
               .addAttribute("class", "wizardItemTitle")
               .addText(uiComponent.getTitle(), true)
               .endElement();

    return true;
  }

  @Override
  protected void postRenderChildren() {

    htmlBuilder.startElement("div")
               .addClassAttribute("commandButton proceed")
               .addText("Proceed", true)
               .endElement();
    
    htmlBuilder.endElement();
    

  }
}
