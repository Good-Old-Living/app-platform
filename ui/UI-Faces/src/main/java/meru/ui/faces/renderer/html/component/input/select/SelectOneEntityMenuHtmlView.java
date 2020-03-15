package meru.ui.faces.renderer.html.component.input.select;

import java.io.IOException;

import meru.el.EL;
import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.html.ForEachEntityHtmlView;
import meru.ui.faces.tag.component.input.select.SelectOneEntityMenu;

public class SelectOneEntityMenuHtmlView extends ForEachEntityHtmlView<Object> {

  private SelectOneEntityMenu mSelectOneMenu;
  private String mSelectedId;
  private String mAttrValue;

  public SelectOneEntityMenuHtmlView(SelectOneEntityMenu entityMenu) {
    super(entityMenu.getEntity(),
          entityMenu.getFilter());

    mSelectOneMenu = entityMenu;
  }

  @Override
  protected void preWriteEntities() throws IOException {

    String value = mSelectOneMenu.getValue();
    if (value != null && EL.isBindVariable(value)) {
      value = EL.getBindVariable(value);
      String val = value.substring(0,
                                   value.lastIndexOf('.') + 1)
          + "id";
      Object id = getVariableValue(val);
      if (id != null) {
        mSelectedId = id.toString();
      }
    }

    if (mSelectedId == null) {
      String def = mSelectOneMenu.getDefault();
      if (def != null) {
        mSelectedId = def;
      }
    }

    int index = value.lastIndexOf('.') + 1;
    mAttrValue = value.substring(index);

    // writeOption(0,
    // "-- Select --");
    writeOption(null,
                "");
  }

  @Override
  protected void writeNoEntity() throws IOException {

  }

  @Override
  protected void writeEntity(Object entity) throws IOException {

    String id = ClassHelper.getFieldValue(entity,
                                          "id")
                           .toString();
    Object dispVal = ClassHelper.getFieldValue(entity,
                                               mAttrValue);

    writeOption(id,
                dispVal.toString());
  }

  private void writeOption(String id,
                           String value) {

    mHtmlBuilder.startElement("option");

    if (id != null) {
      mHtmlBuilder.addAttribute("value",
                                id);

      if (id.equals(mSelectedId)) {
        mHtmlBuilder.addAttribute("selected",
                                  "selected");
      }

    }

    mHtmlBuilder.addText(value)
                .endElement();

  }
}
