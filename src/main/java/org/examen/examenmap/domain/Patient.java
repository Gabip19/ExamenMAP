package org.examen.examenmap.domain;

public class Patient extends Entity<String> {
    private String CNP;
    private int age;
    private boolean premature;
    private String diagnosis;
    private int severity;

    public Patient(String CNP, int age, boolean premature, String diagnosis, int severity) {
        this.CNP = CNP;
        this.age = age;
        this.premature = premature;
        this.diagnosis = diagnosis;
        this.severity = severity;
        setId(CNP);
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPremature() {
        return premature;
    }

    public void setPremature(boolean premature) {
        this.premature = premature;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
