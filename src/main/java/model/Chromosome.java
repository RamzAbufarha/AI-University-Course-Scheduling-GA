package model;

public class Chromosome {

    private Gene[] genes;

    public Chromosome(Gene[] genes) {
        this.genes = genes;
    }

    public Gene[] getGenes() {
        return genes;
    }
}