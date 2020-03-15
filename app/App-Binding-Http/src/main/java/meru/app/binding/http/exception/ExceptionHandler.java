package meru.app.binding.http.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.BindingMessage;
import meru.app.mediatype.MediaTypeFactory;
import meru.sys.JVM;

public abstract class ExceptionHandler {

  public final void handle(Exception e,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

    handleException(e,
                    request,
                    response);
  }

  public abstract void handleException(Exception e,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException;

  protected void sendBindingOK(BindingMessage bindingMessage,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {

    response.setStatus(HttpServletResponse.SC_OK);
    sendBindingMessage(bindingMessage,
                       request,
                       response);

  }

  protected void sendBindingError(BindingMessage bindingMessage,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {

    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    sendBindingMessage(bindingMessage,
                       request,
                       response);

  }

  protected void sendBindingMessage(BindingMessage bindingMessage,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {

    String accept = request.getHeader("accept");
    String message = bindingMessage.getMessage();

    if (accept != null && accept.equals("application/json")) {

      message = MediaTypeFactory.getMediaType("application/json").encode(bindingMessage);

      response.setContentType("application/json");

    } else {
      message = bindingMessage.getMessage() + JVM.SystemProperty.NEW_LINE;
      if (bindingMessage.getEntity() != null) {
        message += bindingMessage.getEntity().toString();
      }
    }

    response.getWriter().print(message);

  }
}
