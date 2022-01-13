package org.eclipse.jkube.quickstart.karaf;

public class BookStatus {
    private boolean issued;
    private String issuedto;

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public String getIssuedto() {
        return issuedto;
    }

    public void setIssuedto(String issuedto) {
        this.issuedto = issuedto;
    }

    @Override
    public String toString() {
        return "BookStatus{issued=" + issued + "," +
                "issuedto=" + issuedto + "}";
    }
}
