package sources;

public class Indi {
    private String givn;
    private String ind;
    private char sex;

    public Indi() {}

    public Indi(String givn, String ind, char sex){
        this.givn = givn;
        this.ind = ind;
        this.sex = sex;
    }

    public boolean equals(Indi i) {
        if (i == null) return false;
        else if (i == this) return true;
        else return i.givn.equals(this.givn)
                    && i.getSex() == this.getSex();
    }

    public char getSex() { return sex; }
    public String getInd() { return ind; }
    public String getGivn() { return givn; }

    public void setInd(String ind) { this.ind = ind; }
    public void setGivn(String givn) { this.givn = givn; }
    public void setSex(char sex) { this.sex = sex; }

    @Override
    public boolean equals(Object o) {
        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return (givn.hashCode()) + (sex == 'M' ? 1 : 0);
    }

    @Override
    public String toString() {
        return givn;
    }
}

