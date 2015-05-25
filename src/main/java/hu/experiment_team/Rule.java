package hu.experiment_team;

/**
 *  This model will contains the rule as String.
 *  @author Jakab Ádám
 * */
public class Rule {
    /**
     * String of the new rule.
     * */
    private String mRule;

    /**
     * Constructs the model of a rule.
     * @param rule The new rule as String
     * */
    public Rule(String rule) {
        this.mRule = rule;
    }

     /**
     * This method returns the <code>rule</code> string.
     * @return The actual rule as <code>rule</code> string
     */
    public String getRule() {
        return this.mRule;
    }

}
