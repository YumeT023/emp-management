package com.prog4.service.utils.csv;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class HttpServletWriter<T> extends CSVWriter<T> {
  public HttpServletWriter(HttpServletResponse res) throws IOException {
    super(res.getWriter());
  }
}
