package com.globalbooks.catalog.security;

import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPPart;
import jakarta.xml.namespace.QName;
import java.util.Iterator;
import java.util.Set;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "secret123";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // Only check inbound requests
        if (!outbound) {
            try {
                SOAPMessage soapMessage = context.getMessage();
                SOAPPart soapPart = soapMessage.getSOAPPart();
                SOAPEnvelope envelope = soapPart.getEnvelope();
                SOAPHeader header = envelope.getHeader();

                if (header == null) {
                    throw new RuntimeException("Missing SOAP header.");
                }

                Iterator<?> it = header.getChildElements(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security"));
                if (!it.hasNext()) {
                    throw new RuntimeException("Missing WS-Security header.");
                }

                SOAPElement security = (SOAPElement) it.next();
                Iterator<?> usernameTokens = security.getChildElements(new QName("UsernameToken"));
                if (!usernameTokens.hasNext()) {
                    throw new RuntimeException("Missing UsernameToken.");
                }

                SOAPElement usernameToken = (SOAPElement) usernameTokens.next();
                String user = usernameToken.getChildElements(new QName("Username")).next().toString();
                String pass = usernameToken.getChildElements(new QName("Password")).next().toString();

                if (!(USERNAME.equals(user) && PASSWORD.equals(pass))) {
                    throw new RuntimeException("Invalid Username or Password.");
                }
            } catch (Exception e) {
                throw new RuntimeException("Security validation error: " + e.getMessage(), e);
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
