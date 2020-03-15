package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.BindingMessage;

public class GenericExceptionHandler extends ExceptionHandler {

  @Override
  public void handleException(Exception e,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
    e.printStackTrace();
    BindingMessage bindingMsg = new BindingMessage(BindingMessage.BindingMessageType.Error,
                                                   e.getMessage());
    sendBindingError(bindingMsg,
                     request,
                     response);
  }

}
