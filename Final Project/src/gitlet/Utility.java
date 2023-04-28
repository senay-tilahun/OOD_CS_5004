package gitlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class to aid with various tasks.
 */
public class Utility {

    static String getId(Serializable serGitObjects) throws NoSuchAlgorithmException {
        String objectString = serGitObjects.toString();
        byte[] objectBytes = objectString.getBytes(StandardCharsets.UTF_8);

        MessageDigest msgDgt = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = msgDgt.digest(objectBytes);

        StringBuilder builder = new StringBuilder();
        for (byte b : hashBytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /**
     * Method to write contents to file
     * @param filename file to write to
     * @param toRead contents to read
     */
    static void writeContentsToFile(File filename, Object... toRead) {
        try {
            checkFileIsNotDirectory(filename);
            BufferedOutputStream stream = getOutputStream(filename);
            writeObjectsToStream(toRead, stream);
            stream.close();
        } catch (IOException | ClassCastException ex) {
            ex.printStackTrace();
//      throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Method to check if a file is a directory or not
     * @param file file to check
     */
    static void checkFileIsNotDirectory(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directory already exists.");
        }
    }

    /**
     * Method to get output stream of a file
     * @param file the file given
     * @return output stream of the file
     * @throws IOException if not possible to return
     */
    static BufferedOutputStream getOutputStream(File file) throws IOException {
        return new BufferedOutputStream(Files.newOutputStream(file.toPath()));
    }

    /**
     * Method to write objects to stream
     * @param contents objects to write
     * @param stream steam to write to
     * @throws IOException if not possible
     */
    static void writeObjectsToStream(Object[] contents, BufferedOutputStream stream)
        throws IOException {
        for (Object obj : contents) {
            if (obj instanceof byte[]) {
                stream.write((byte[]) obj);
            } else {
                String string = (String) obj;
                byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
                stream.write(bytes);
            }
        }
    }

    /**
     * Method to read objects form file
     * @param filename file to read from
     * @return the results of the read
     */
    static <T extends Serializable> T readObjectFromFile(File filename,
        Class<T> expectedClass) {
        try {
            ObjectInputStream inStream = getObjectInputStream(filename);
            T resultSt = expectedClass.cast(readObjectFromStream(inStream));
            inStream.close();
            return resultSt;
        } catch (IOException | ClassCastException | ClassNotFoundException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /**
     * Method to get ObjectInputStream
     * @param file to get from
     * @return a new ObjectInputStream
     * @throws IOException if file gives issues
     */
    static ObjectInputStream getObjectInputStream(File file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }

    /**
     * Method to read objects from stream
     * @param inStream input stream
     * @return the read object
     * @throws IOException
     * @throws ClassCastException
     * @throws ClassNotFoundException
     */
    static Serializable readObjectFromStream(ObjectInputStream inStream)
        throws IOException, ClassCastException, ClassNotFoundException {
        return (Serializable) inStream.readObject();
    }


    /**
     * Method to write object
     * @param file file to
     * @param objGitLet
     * @throws IOException
     */
    static void writeObject(File file, Serializable objGitLet) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(stream);
        objectStream.writeObject(objGitLet);
        objectStream.close();
        byte[] serializedBytes = stream.toByteArray();
        ByteBuffer buffer = ByteBuffer.allocate(serializedBytes.length);
        buffer.put(serializedBytes);
        buffer.flip();
        byte[] byteArr = buffer.array();
        writeContentsToFile(file, (Object) byteArr);
    }

}
