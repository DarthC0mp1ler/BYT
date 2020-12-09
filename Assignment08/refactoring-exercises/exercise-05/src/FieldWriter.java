public class FieldWriter implements Writer{

    private Writer quotedWriter;

    FieldWriter() {
           quotedWriter = new QuotedWriter();
    }

   public void write(String field) {
        if (field.indexOf(',') != -1 || field.indexOf('\"') != -1)
            quotedWriter.write(field);
        else
            System.out.print(field);
    }
}
