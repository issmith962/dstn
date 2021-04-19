package projects.isaacsmith.dstn.model;

public interface Section {
    int TYPE_NAME = 1;
    int TYPE_FINAL_NAME = 2;
    int TYPE_DATE = 3;

    int type();
    int sectionPosition();
    boolean isNew();
    String getText();
}
