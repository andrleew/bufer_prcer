package sources;

import java.util.LinkedList;

public class Fam {
    private String fam;
    private String husbandIndi;
    private String wifeIndi;
    private LinkedList<String> childIndi;

    public Fam(){
        fam = null;
        husbandIndi = null;
        wifeIndi = null;
        childIndi = new LinkedList<String>();
    }
    public Fam(String fam, String husbandIndi, String wife_indi, LinkedList<String> child_indi){
        this.fam = fam;
        this.husbandIndi = husbandIndi;
        this.wifeIndi = wife_indi;
        this.childIndi = child_indi;
    }

    public void setFam(String fam) { this.fam = fam; }
    public void setHusbandIndi(String husbandIndi) { this.husbandIndi = husbandIndi;}
    public void setWifeIndi(String wifeIndi){ this.wifeIndi = wifeIndi; }
    public void setChildIndi(LinkedList<String> childIndi){ this.childIndi = childIndi; }

    public LinkedList<String> getChildIndi() { return childIndi; }
    public String getHusbandIndi() {return husbandIndi; }
    public String getFam() { return fam; }
    public String getWifeIndi(){ return wifeIndi; }

    @Override
    public int hashCode() {
        return (fam.hashCode() + husbandIndi.hashCode() + wifeIndi.hashCode()) %
                (childIndi.hashCode() + fam.hashCode() - husbandIndi.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public String toString() {
        return husbandIndi + " " + wifeIndi + " " + childIndi;
    }
}
