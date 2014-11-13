package org.intermine.web.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.intermine.webservice.server.JWTVerifier;
import org.intermine.webservice.server.JWTVerifier.Verification;
import org.intermine.webservice.server.JWTVerifier.VerificationError;

public class VerifyTokenTask extends Task {

    private String keystoreFile;
    private String optionsFile;
    private String password;
    private String token;

    /**
     * Bean-style setter for keystoreFile, as per ant spec.
     * @param filename The name of the file.
     */
    public void setKeystoreFile(String filename) {
        this.keystoreFile = filename;
    }

    /**
     * Bean-style setter for password, as per ant spec.
     * @param password The password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Bean-style setter for token, as per ant spec.
     * @param token The token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Bean-style setter for optionsFile, as per ant spec.
     * @param filename The filename.
     */
    public void setOptions(String filename) {
        this.optionsFile = filename;
    }

    private InputStream readKeystore() {
        File ksf = new File(keystoreFile);
        if (!ksf.exists() || !ksf.canRead()) {
            throw new BuildException(keystoreFile + " does not exist, or cannot be read");
        }
        try {
            return new FileInputStream(ksf);
        } catch (FileNotFoundException e) {
            throw new BuildException(e);
        }
    }

    private KeyStore createKeyStore() {
        KeyStore ks;
        try {
            ks = KeyStore.getInstance("jks");
        } catch (KeyStoreException e) {
            throw new BuildException("Error creating key-store.", e);
        }
        InputStream is = readKeystore();
        try {
            ks.load(is, password.toCharArray());
        } catch (NoSuchAlgorithmException e) {
            throw new BuildException("Error creating key-store.", e);
        } catch (CertificateException e) {
            throw new BuildException("Error creating key-store.", e);
        } catch (IOException e) {
            throw new BuildException("Error creating key-store.", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logError("Error closing keystore");
                }
            }
        }
        return ks;
    }

    @Override
    public void execute() {
        Properties options = getOptions();
        JWTVerifier verifier = new JWTVerifier(createKeyStore(), options);

        try {
            Verification result = verifier.verify(token);
            logMessage("IDENTITY = " + result.getIdentity());
        } catch (VerificationError e) {
            logError("Token failed to verify: " + e.getMessage());
        }
    }

    private Properties getOptions() {
        Properties options = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(new File(optionsFile));
        } catch (FileNotFoundException e) {
            throw new BuildException(optionsFile + " not found");
        }
        try {
            options.load(is);
        } catch (IOException e) {
            throw new BuildException();
        }
        return options;
    }

    private static void logMessage(String message) {
        PrintStream out = System.out;
        out.println(message);
    }

    private static void logError(String message) {
        PrintStream out = System.err;
        out.println(message);
    }

}
