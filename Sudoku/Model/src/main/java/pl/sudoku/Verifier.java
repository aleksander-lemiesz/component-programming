package pl.sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class for verifying if the chosen content is correct (either row, column or box).
 */
public class Verifier implements Cloneable, Serializable {
    /**
     * Stores the content of verifier.
     *
     * @content is used to verify the sudoku parts
     */
    private List<SudokuField> content;
    private static final Logger logger = Logger.getLogger(Verifier.class);

    /**
     * Constructor for verifier.
     *
     * @param newContent the content that is about to be verified (box, column or row)
     */
    Verifier(ArrayList<SudokuField> newContent) {
        content = new ArrayList<>(newContent);
    }

    /**
     * Getter for content.
     *
     * @return content that will be verified
     */
    public List<SudokuField> getContent() {
        List<SudokuField> contentCopy = new ArrayList<>(content);
        return contentCopy;
    }

    /**
     * Method for verifying the content and its correctness.
     *
     * @return info (true or false), whether the content was found correct or not
     */
    boolean verify() {
        for (int i = 0; i < content.size(); i++) {
            for (int j = 0; j < content.size(); j++) {
                if (i != j && (content.get(i).getValue() == 0 || content.get(j).getValue() == 0)) {
                    continue;
                }
                if (i != j && content.get(i).getValue() == content.get(j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("content", content)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Verifier)) {
            return false;
        }
        Verifier verifier = (Verifier) o;
        return new EqualsBuilder()
                .append(content, verifier.content)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(content)
                .toHashCode();
    }

    @Override
    public Verifier clone() {
        Verifier clone;
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);) {

            objectOutputStream.writeObject(this);

            var byteArrayInputStream =
                    new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            var objectInputStream = new ObjectInputStream(byteArrayInputStream);

            clone = (Verifier) objectInputStream.readObject();

            byteArrayInputStream.close();
            objectInputStream.close();

            return clone;

        } catch (Exception e) {
            String log4jConfPath = "E:\\Files\\Studia\\4thSemester\\ComponentProgramming"
                    + "\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            //String log4jConfPath = "D:\\PL_Files\\Component"
            //        + "\\cp_mkw_th_1015_02\\Sudoku\\log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            logger.error(e.toString());
            return null;
        }
    }
}
