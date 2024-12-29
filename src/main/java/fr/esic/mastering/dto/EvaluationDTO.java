package fr.esic.mastering.dto;

public class EvaluationDTO {

    private Long id;
    private Long juryId;
    private Long candidatId;
    private double notePresentation;
    private double noteContenu;
    private double noteClarte;
    private double notePertinence;
    private double noteReponses;
    private double moyenne; // Inclure la moyenne calculée
    private String commentaire;

    // Constructeur complet
    public EvaluationDTO(Long id, Long juryId, Long candidatId, double notePresentation, double noteContenu,
                         double noteClarte, double notePertinence, double noteReponses, double moyenne, String commentaire) {
        this.id = id;
        this.juryId = juryId;
        this.candidatId = candidatId;
        this.notePresentation = notePresentation;
        this.noteContenu = noteContenu;
        this.noteClarte = noteClarte;
        this.notePertinence = notePertinence;
        this.noteReponses = noteReponses;
        this.moyenne = moyenne;
        this.commentaire = commentaire;
    }

    // Constructeur vide
    public EvaluationDTO() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJuryId() {
        return juryId;
    }

    public void setJuryId(Long juryId) {
        this.juryId = juryId;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public double getNotePresentation() {
        return notePresentation;
    }

    public void setNotePresentation(double notePresentation) {
        this.notePresentation = notePresentation;
    }

    public double getNoteContenu() {
        return noteContenu;
    }

    public void setNoteContenu(double noteContenu) {
        this.noteContenu = noteContenu;
    }

    public double getNoteClarte() {
        return noteClarte;
    }

    public void setNoteClarte(double noteClarte) {
        this.noteClarte = noteClarte;
    }

    public double getNotePertinence() {
        return notePertinence;
    }

    public void setNotePertinence(double notePertinence) {
        this.notePertinence = notePertinence;
    }

    public double getNoteReponses() {
        return noteReponses;
    }

    public void setNoteReponses(double noteReponses) {
        this.noteReponses = noteReponses;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    // Méthode toString() pour le débogage ou l'affichage
    @Override
    public String toString() {
        return "EvaluationDTO{" +
                "id=" + id +
                ", juryId=" + juryId +
                ", candidatId=" + candidatId +
                ", notePresentation=" + notePresentation +
                ", noteContenu=" + noteContenu +
                ", noteClarte=" + noteClarte +
                ", notePertinence=" + notePertinence +
                ", noteReponses=" + noteReponses +
                ", moyenne=" + moyenne +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
