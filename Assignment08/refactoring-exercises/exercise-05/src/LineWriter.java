public class LineWriter{

    private Writer fieldWriter;

    LineWriter(){
        fieldWriter = new FieldWriter();
    }

    public void writeLine(String[] fields) {
        if (fields.length == 0)
            System.out.println();
        else {
            fieldWriter.write(fields[0]);

            for (int i = 1; i < fields.length; i++) {
                System.out.print(",");
                fieldWriter.write(fields[i]);
            }
            System.out.println();
        }
    }

}
