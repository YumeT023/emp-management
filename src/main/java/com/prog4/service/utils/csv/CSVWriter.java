package com.prog4.service.utils.csv;

import java.io.PrintWriter;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CSVWriter<T> {
  protected PrintWriter writer;

  public String writeRow(String... records) {
    return String.join(", ", records);
  }

  public void writeAll(List<T> entries) {
    writer.println(writeRow(getColumns()));
    for (T entry : entries) {
      writer.println(write(entry));
    }
  }

  abstract String[] getColumns();

  abstract String write(T subject);
}
